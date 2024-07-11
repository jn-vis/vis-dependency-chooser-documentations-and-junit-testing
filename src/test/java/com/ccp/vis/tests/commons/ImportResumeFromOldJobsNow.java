package com.ccp.vis.tests.commons;

import java.util.function.Consumer;

import com.ccp.constantes.CcpConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.especifications.db.bulk.CcpEntityOperationType;
import com.ccp.especifications.http.CcpHttpHandler;
import com.ccp.especifications.http.CcpHttpResponse;
import com.ccp.jn.async.commons.JnAsyncCommitAndAudit;
import com.ccp.jn.vis.sync.service.SyncServiceVisResume;
import com.ccp.json.transformers.CcpJsonTransformerGenerateFieldHash;
import com.ccp.json.transformers.CcpJsonTransformerGenerateRandomToken;
import com.ccp.json.transformers.CcpJsonTransformerPutPasswordField;
import com.jn.commons.entities.JnEntityLoginAnswers;
import com.jn.commons.entities.JnEntityLoginEmail;
import com.jn.commons.entities.JnEntityLoginPassword;
import com.jn.commons.entities.JnEntityLoginSessionCurrent;
import com.jn.commons.entities.JnEntityLoginToken;
import com.jn.commons.utils.JnValidateSession;
import com.vis.commons.entities.VisEntityResume;

public class ImportResumeFromOldJobsNow implements Consumer<CcpJsonRepresentation>{

	public static final ImportResumeFromOldJobsNow INSTANCE = new ImportResumeFromOldJobsNow();			
	CcpHttpHandler http = new CcpHttpHandler(200, CcpConstants.DO_NOTHING);

	private ImportResumeFromOldJobsNow() {}
	
	@SuppressWarnings("unchecked")
	public void accept(CcpJsonRepresentation candidate) {
		CcpJsonRepresentation resumeFile = candidate.getInnerJson("curriculo")
				.renameField("conteudo", "resumeBase64")
				.renameField("arquivo", "fileName")
				.getJsonPiece("resumeBase64", "fileName")
		;
		CcpJsonRepresentation allResumeData = candidate
		.renameField("disponibilidade", VisEntityResume.Fields.disponibility.name())
		.renameField("profissaoDesejada", VisEntityResume.Fields.desiredJob.name())
		.renameField("empresas",VisEntityResume.Fields.companiesNotAllowed.name())
		.renameField("ultimaProfissao", VisEntityResume.Fields.lastJob.name())
		.renameField("experiencia", VisEntityResume.Fields.experience.name())
		.renameField("pretensaoClt", VisEntityResume.Fields.clt.name())
		.renameField("pretensaoPj", VisEntityResume.Fields.pj.name())
		.renameField("bitcoin", VisEntityResume.Fields.btc.name())
		.renameField("observacao", "observations")
		.put("name", "NOME DO CANDIDATO")
		.putAll(resumeFile)
		.getTransformedJson(AddDddsInResume.INSTANCE, AddDefaultValuesInResume.INSTANCE)
		.getJsonPiece(
				VisEntityResume.Fields.companiesNotAllowed.name()
				,VisEntityResume.Fields.disponibility.name()
				,VisEntityResume.Fields.desiredJob.name()
				,VisEntityResume.Fields.experience.name()
				,VisEntityResume.Fields.lastJob.name()
				,VisEntityResume.Fields.clt.name()
				,VisEntityResume.Fields.btc.name()
				,VisEntityResume.Fields.ddd.name()
				,VisEntityResume.Fields.pj.name()
				,"resumeBase64"
				,"observations"
				,"fileName"
				,"name"
				);
	
		String email = candidate.getAsString("id");
		
		CcpJsonRepresentation createLogin = this.createLogin(email);
		
		CcpJsonRepresentation jsonWithSessionToken = this.executeLogin(email);
		
		CcpJsonRepresentation putAll = allResumeData.putAll(jsonWithSessionToken).putAll(createLogin);
		
		CcpJsonRepresentation resume = putAll.getTransformed(JnValidateSession.INSTANCE);
		
		SyncServiceVisResume.INSTANCE.save(resume);
		
		Integer status = candidate.getAsIntegerNumber("status");
		
		boolean inactiveResume = Integer.valueOf(0).equals(status);
		
		if(inactiveResume) {
			SyncServiceVisResume.INSTANCE.changeStatus(resume);
		}
	}

	private CcpJsonRepresentation executeLogin(String email) {
	
		String path = "http://localhost:8080/login/{email}".replace("{email}", email);
		
		String asUgglyJson = CcpConstants.EMPTY_JSON.put("password", "Jobsnow1!").asUgglyJson();
		
		CcpHttpResponse response = this.http.ccpHttp.executeHttpRequest(path, "POST", CcpConstants.EMPTY_JSON, asUgglyJson, 200);
		
		CcpJsonRepresentation asSingleJson = response.asSingleJson();
		return asSingleJson;
	}
	
	@SuppressWarnings("unchecked")
	private CcpJsonRepresentation createLogin(String email) {
		var tokenGenerator = new CcpJsonTransformerGenerateRandomToken(8, "token");
		var passwordGenerator = new CcpJsonTransformerPutPasswordField("password");
		var fieldHashGenerator = new CcpJsonTransformerGenerateFieldHash("email", "originalEmail");
		CcpJsonRepresentation transformed = CcpConstants.EMPTY_JSON
		.put("userAgent", "Apache-HttpClient/4.5.4 (Java/17.0.9)")
		.put("password", "Jobsnow1!")
		.put("ip", "localhost:8080")
		.put("channel", "linkedin")
		.put("goal", "jobs")
		.put("email", email)
		.getTransformedJson(
				tokenGenerator,
				fieldHashGenerator,
				passwordGenerator
		);
		
		JnAsyncCommitAndAudit.INSTANCE.executeBulk(transformed, CcpEntityOperationType.create, 
				JnEntityLoginPassword.INSTANCE,
				JnEntityLoginAnswers.INSTANCE,
				JnEntityLoginToken.INSTANCE,
				JnEntityLoginEmail.INSTANCE
				);
		
		JnEntityLoginSessionCurrent.INSTANCE.delete(transformed);
		
		CcpJsonRepresentation renameField = transformed.renameField("originalEmail", "email");
		return renameField;
	}
}

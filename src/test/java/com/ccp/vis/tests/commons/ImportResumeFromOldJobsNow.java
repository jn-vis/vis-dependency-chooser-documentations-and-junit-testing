package com.ccp.vis.tests.commons;

import java.util.function.Consumer;

import com.ccp.constantes.CcpConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.especifications.http.CcpHttpHandler;
import com.ccp.especifications.http.CcpHttpResponse;
import com.ccp.jn.vis.sync.service.SyncServiceVisResume;
import com.jn.commons.entities.JnEntityLoginAnswers;
import com.jn.commons.entities.JnEntityLoginEmail;
import com.jn.commons.utils.JnValidateSession;
import com.vis.commons.entities.VisEntityResume;

public class ImportResumeFromOldJobsNow implements Consumer<CcpJsonRepresentation>{

	public static final ImportResumeFromOldJobsNow INSTANCE = new ImportResumeFromOldJobsNow();			
	CcpHttpHandler http = new CcpHttpHandler(200, CcpConstants.DO_NOTHING);

	private ImportResumeFromOldJobsNow() {}
	
	public void accept(CcpJsonRepresentation candidate) {
		CcpJsonRepresentation resumeFile = candidate.getInnerJson("curriculo")
				.renameField("conteudo", "resumeBase64")
				.renameField("arquivo", "fileName")
				.getJsonPiece("resumeBase64", "fileName")
		;
		CcpJsonRepresentation data = candidate
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
		.getTransformed(AddDdds.INSTANCE)
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
	
		JnEntityLoginAnswers.INSTANCE.createOrUpdate(BaseTest.ANSWERS_JSON);
		JnEntityLoginEmail.INSTANCE.createOrUpdate(BaseTest.REQUEST_TO_LOGIN);
		CcpJsonRepresentation put = BaseTest.REQUEST_TO_LOGIN.put("password", "Jobsnow1!");
		String asUgglyJson = put.asUgglyJson();
		String email = data.getAsString("id");
		
		String path = "http://localhost:8080/login/{email}/password".replace("{email}", email);
		
		CcpHttpResponse response = this.http.ccpHttp.executeHttpRequest(path, "POST", CcpConstants.EMPTY_JSON, asUgglyJson);
		
		CcpJsonRepresentation asSingleJson = response.asSingleJson();
		
		CcpJsonRepresentation putAll = data.putAll(asSingleJson);
		
		CcpJsonRepresentation resume = putAll.getTransformed(JnValidateSession.INSTANCE);
		
		SyncServiceVisResume.INSTANCE.save(resume);
		
		Integer status = candidate.getAsIntegerNumber("status");
		
		boolean inactiveResume = Integer.valueOf(0).equals(status);
		
		if(inactiveResume) {
			SyncServiceVisResume.INSTANCE.changeStatus(resume);
		}
	}
}

package com.ccp.vis.tests.commons;

import java.util.Arrays;
import java.util.function.Consumer;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.jn.vis.sync.service.SyncServiceVisResume;
import com.ccp.json.transformers.CcpJsonTransformerGenerateFieldHash;
import com.jn.commons.utils.JnValidateSession;
import com.vis.commons.entities.VisEntityResume;

public class ImportResumeFromOldJobsNow implements Consumer<CcpJsonRepresentation>{

	public static final ImportResumeFromOldJobsNow INSTANCE = new ImportResumeFromOldJobsNow();			

	private ImportResumeFromOldJobsNow() {}
	
	@SuppressWarnings("unchecked")
	public void accept(CcpJsonRepresentation candidate) {
		var fieldHashGenerator = new CcpJsonTransformerGenerateFieldHash("email", "originalEmail");
		CcpJsonRepresentation resumeFile = candidate.getInnerJson("curriculo")
				.renameField("conteudo", "resumeBase64")
				.renameField("arquivo", "fileName")
				.getJsonPiece("resumeBase64", "fileName")
		;
		CcpJsonRepresentation resume = candidate
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
		.copyIfNotContains(VisEntityResume.Fields.lastJob.name(), VisEntityResume.Fields.desiredJob.name())
		.putIfNotContains(VisEntityResume.Fields.companiesNotAllowed.name(), Arrays.asList())
		.whenHasNotField(VisEntityResume.Fields.experience.name(), AddExperience.INSTANCE)
		.putIfNotContains(VisEntityResume.Fields.disabilities.name(), Arrays.asList())
		.getTransformedJson(
				CreateLoginAndSession.INSTANCE,
				JnValidateSession.INSTANCE,
				AddDddsInResume.INSTANCE,
				fieldHashGenerator
				)
		.getJsonPiece(
				VisEntityResume.Fields.companiesNotAllowed.name()
				,VisEntityResume.Fields.disponibility.name()
				,VisEntityResume.Fields.disabilities.name()
				,VisEntityResume.Fields.desiredJob.name()
				,VisEntityResume.Fields.experience.name()
				,VisEntityResume.Fields.lastJob.name()
				,VisEntityResume.Fields.email.name()
				,VisEntityResume.Fields.clt.name()
				,VisEntityResume.Fields.btc.name()
				,VisEntityResume.Fields.ddd.name()
				,VisEntityResume.Fields.pj.name()
				,"originalEmail"
				,"resumeBase64"
				,"observations"
				,"fileName"
				,"name"
				);
	
		SyncServiceVisResume.INSTANCE.save(resume);
		
		Integer status = candidate.getAsIntegerNumber("status");
		
		boolean inactiveResume = Integer.valueOf(0).equals(status);
		
		if(inactiveResume) {
			SyncServiceVisResume.INSTANCE.changeStatus(resume);
		}
	}
}

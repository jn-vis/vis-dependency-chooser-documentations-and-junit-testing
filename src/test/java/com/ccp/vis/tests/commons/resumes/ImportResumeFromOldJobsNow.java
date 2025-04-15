package com.ccp.vis.tests.commons.resumes;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.especifications.db.query.CcpDbQueryOptions;
import com.ccp.especifications.db.query.CcpQueryExecutor;
import com.jn.business.login.JnBusinessValidateSession;
import com.vis.commons.entities.VisEntityResume;

public class ImportResumeFromOldJobsNow implements Consumer<CcpJsonRepresentation>{
	public static final ImportResumeFromOldJobsNow INSTANCE = new ImportResumeFromOldJobsNow();
	private Set<String> ids;			
	int contador;

	private ImportResumeFromOldJobsNow() {
		CcpQueryExecutor queryExecutor = CcpDependencyInjection.getDependency(CcpQueryExecutor.class);
		CcpDbQueryOptions query = 
				CcpDbQueryOptions.INSTANCE
					.matchAll()
					.maxResults()
				;
		String[] resourcesNames = VisEntityResume.ENTITY.getEntitiesToSelect();
		this.ids = queryExecutor.getResultAsList(query, resourcesNames, "email").stream().map(x -> x.getAsString("id")).collect(Collectors.toSet());
	}
	
	public void accept(CcpJsonRepresentation candidate) {
		
		boolean alreadyInserted = this.contador++ < this.ids.size();
		
		if(alreadyInserted) {
//			return;
		}
		
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
		.putIfNotContains(VisEntityResume.Fields.disabilities.name(), Arrays.asList())
		.putIfNotContains(VisEntityResume.Fields.disponibility.name(), 0)
		.putIfNotContains(VisEntityResume.Fields.desiredJob.name(), "-")
		.putIfNotContains(VisEntityResume.Fields.lastJob.name(), "-")
		.putIfNotContains("observations", "-")
		.getTransformedJson(
				ResumeTransformations.AddBtcValue,
				ResumeTransformations.AddCltValue,
				ResumeTransformations.AddDddsInResume,
				ResumeTransformations.AddDesiredJob,
				ResumeTransformations.AddDisponibility,
				ResumeTransformations.AddExperience,
				ResumeTransformations.AddLastJob,
				ResumeTransformations.AddMinCltValue,
				ResumeTransformations.AddMinPjValue,
				ResumeTransformations.AddObservations,
				ResumeTransformations.CreateLoginAndSession,
				JnBusinessValidateSession.INSTANCE
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
	
		System.out.println(resume);
		
//		SyncServiceVisResume.INSTANCE.save(resume);
		
		Integer status = candidate.getAsIntegerNumber("status");
		
		boolean inactiveResume = Integer.valueOf(0).equals(status);
		
		if(inactiveResume) {
//			SyncServiceVisResume.INSTANCE.changeStatus(resume);
		}
	}
}

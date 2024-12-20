package com.ccp.vis.tests.resume.validations.endpoints;

import org.junit.Test;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.especifications.db.crud.CcpGetEntityId;
import com.ccp.especifications.http.CcpHttpResponseType;
import com.ccp.process.CcpDefaultProcessStatus;
import com.ccp.vis.tests.commons.VisTemplateDeTestes;
import com.jn.commons.entities.JnEntityAsyncTask;
import com.jn.commons.entities.JnEntityEmailMessageSent;

public class ValidationsEndpointsCreateResume  extends VisTemplateDeTestes{

	@Test
	public void salvarCurriculoComArquivoInvalido() {
		String uri = ENDPOINT_URL + "/resume/{email}";
		CcpJsonRepresentation body = super.getJsonDoArquivo("documentation/tests/resume/curriculoComArquivoInvalido.json");
		CcpJsonRepresentation jsonDeRetornoDoTeste = super.testarEndpoint(CcpDefaultProcessStatus.CREATED, body, uri, CcpHttpResponseType.singleRecord);
		
		 new CcpGetEntityId(jsonDeRetornoDoTeste)
			.toBeginProcedureAnd()
			.ifThisIdIsNotPresentInEntity(JnEntityAsyncTask.ENTITY).returnStatus(SaveResumeStatus.naoCadastrouMensageria).and()
			.ifThisIdIsNotPresentInEntity(JnEntityEmailMessageSent.ENTITY).returnStatus(SaveResumeStatus.naoEnviouEmail).and()
			;
	}
	@Test
	public void salvarCurriculoComArquivoValido() {
		String uri = ENDPOINT_URL + "/resume/{email}";
		CcpJsonRepresentation body = super.getJsonDoArquivo("documentation/tests/resume/curriculoComArquivoValido.json");
		CcpJsonRepresentation jsonDeRetornoDoTeste = super.testarEndpoint(CcpDefaultProcessStatus.CREATED, body, uri, CcpHttpResponseType.singleRecord);
		
		 new CcpGetEntityId(jsonDeRetornoDoTeste)
			.toBeginProcedureAnd()
			.ifThisIdIsNotPresentInEntity(JnEntityAsyncTask.ENTITY).returnStatus(SaveResumeStatus.naoCadastrouMensageria).and()
			.ifThisIdIsNotPresentInEntity(JnEntityEmailMessageSent.ENTITY).returnStatus(SaveResumeStatus.naoEnviouEmail).and()
			;
	}

	protected String getMethod() {
		return "POST";
	}
	
}

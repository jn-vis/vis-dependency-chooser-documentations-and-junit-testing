package com.ccp.vis.tests.resume.validations.endpoints;

import org.junit.Test;

import com.ccp.constantes.CcpOtherConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.especifications.db.crud.CcpGetEntityId;
import com.ccp.jn.sync.status.login.StatusCreateLoginEmail;
import com.ccp.process.CcpDefaultProcessStatus;
import com.ccp.vis.tests.commons.LoginActions;
import com.ccp.vis.tests.commons.VisTemplateDeTestes;
import com.jn.commons.entities.JnEntityAsyncTask;
import com.jn.commons.entities.JnEntityEmailMessageSent;
import com.jn.commons.status.StatusExecuteLogin;

public class ValidationsEndpointsCreateResume  extends VisTemplateDeTestes{

	private final String email = "onias85@gmail.com";
	private final String uri = "resume/" + this.email + "/language/portuguese";
	
	public String getUri() {
		return this.uri;
	}

	@Test
	public void testarEmailInvalido() {
		String scenarioName = new Object(){}.getClass().getEnclosingMethod().getName();
		CcpJsonRepresentation body = super.getJsonFile("documentation/tests/resume/curriculoComArquivoInvalido.json");
		super.getJsonResponseFromEndpoint(CcpDefaultProcessStatus.BAD_REQUEST, scenarioName, body, this.uri.replace("@", ""));
	}

	@Test
	public void testarRequisicaoSemTokenDeSessao() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		CcpJsonRepresentation body = super.getJsonFile("documentation/tests/resume/curriculoComArquivoInvalido.json");
		super.getJsonResponseFromEndpoint(StatusExecuteLogin.missingSessionToken, scenarioName, body, this.uri, CcpOtherConstants.EMPTY_JSON);
	}
	
	
	@Test
	public void testarRequisicaoComTokenFalso() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		CcpJsonRepresentation body = super.getJsonFile("documentation/tests/resume/curriculoComArquivoInvalido.json");
		CcpJsonRepresentation bodyWithFakeSessionToken = body.put("sessionToken", "tokenFalsoSafadoQualquer");
		super.getJsonResponseFromEndpoint(StatusExecuteLogin.invalidSession, scenarioName, bodyWithFakeSessionToken, this.uri);

	}

	protected CcpJsonRepresentation getHeaders() {
		return super.getHeaders().put("sessionToken", "NFDP8DV9987EVMBW1H3N56OEGYMFZB");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void faltandoCadastrarSenha() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		this.getJsonResponseFromEndpoint(StatusExecuteLogin.missingSavePassword, scenarioName, this.pathToJsonFile, LoginActions.deletePassword);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void salvarCurriculoComArquivoInvalido() {
		
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		CcpJsonRepresentation jsonDeRetornoDoTeste = this.getJsonResponseFromEndpoint(CcpDefaultProcessStatus.UNPROCESSABLE_ENTITY, scenarioName, "documentation/tests/resume/curriculoComArquivoInvalido.json");
		
		 new CcpGetEntityId(jsonDeRetornoDoTeste)
			.toBeginProcedureAnd()
			.ifThisIdIsNotPresentInEntity(JnEntityAsyncTask.ENTITY).returnStatus(SaveResumeStatus.naoCadastrouMensageria).and()
			.ifThisIdIsNotPresentInEntity(JnEntityEmailMessageSent.ENTITY).returnStatus(SaveResumeStatus.naoEnviouEmail).and()
			;
	}
	
	private String pathToJsonFile = "documentation/tests/resume/curriculoParaSalvar.json";
	
	@SuppressWarnings("unchecked")
	@Test
	public void salvarCurriculoComArquivoValido() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();

		CcpJsonRepresentation responseFromEndpoint = this.getJsonResponseFromEndpoint(CcpDefaultProcessStatus.CREATED, scenarioName, this.pathToJsonFile);
		
		 new CcpGetEntityId(responseFromEndpoint)
			.toBeginProcedureAnd()
			.ifThisIdIsNotPresentInEntity(JnEntityAsyncTask.ENTITY).returnStatus(SaveResumeStatus.naoCadastrouMensageria).and()
			.ifThisIdIsNotPresentInEntity(JnEntityEmailMessageSent.ENTITY).returnStatus(SaveResumeStatus.naoEnviouEmail)
			.andFinallyReturningTheseFields("x")
			;
	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void faltandoCadastrarPreRegistro() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		this.getJsonResponseFromEndpoint(StatusCreateLoginEmail.missingSaveAnswers, scenarioName, this.pathToJsonFile, LoginActions.deleteAsnswers);
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void senhaBloqueada() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		this.getJsonResponseFromEndpoint(StatusExecuteLogin.lockedPassword, scenarioName, this.pathToJsonFile, LoginActions.lockPassword);
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void tokenBloqueado() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		this.getJsonResponseFromEndpoint(StatusExecuteLogin.lockedToken, scenarioName, this.pathToJsonFile, LoginActions.lockToken);
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void faltandoCadastrarEmail() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		this.getJsonResponseFromEndpoint(StatusExecuteLogin.missingSavingEmail, scenarioName, this.pathToJsonFile, LoginActions.deleteEmail);

	}
	
	protected String getMethod() {
		return "POST";
	}
	
}

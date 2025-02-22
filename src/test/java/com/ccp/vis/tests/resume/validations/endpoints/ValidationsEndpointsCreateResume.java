package com.ccp.vis.tests.resume.validations.endpoints;

import java.util.function.Function;

import org.junit.Test;

import com.ccp.constantes.CcpOtherConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.especifications.db.crud.CcpGetEntityId;
import com.ccp.exceptions.process.CcpFlow;
import com.ccp.flow.CcpTreeFlow;
import com.ccp.jn.sync.service.SyncServiceJnLogin;
import com.ccp.jn.sync.status.login.StatusCreateLoginEmail;
import com.ccp.process.CcpDefaultProcessStatus;
import com.ccp.vis.tests.commons.VisTemplateDeTestes;
import com.jn.commons.entities.JnEntityAsyncTask;
import com.jn.commons.entities.JnEntityEmailMessageSent;
import com.jn.commons.entities.JnEntityLoginToken;
import com.jn.commons.status.StatusExecuteLogin;

public class ValidationsEndpointsCreateResume  extends VisTemplateDeTestes{

	private final String email = "onias85@gmail.com";
	private final String uri = "resume/" + this.email + "/language/portuguese";
	
	@Test
	public void testarEmailInvalido() {
		String scenarioName = new Object(){}.getClass().getEnclosingMethod().getName();
		CcpJsonRepresentation body = super.getJsonDoArquivo("documentation/tests/resume/curriculoComArquivoInvalido.json");
		super.testarEndpoint(CcpDefaultProcessStatus.BAD_REQUEST, scenarioName, body, this.uri.replace("@", ""));
	}

	@Test
	public void testarRequisicaoSemTokenDeSessao() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		CcpJsonRepresentation body = super.getJsonDoArquivo("documentation/tests/resume/curriculoComArquivoInvalido.json");
		super.testarEndpoint(StatusExecuteLogin.missingSessionToken, scenarioName, body, this.uri, CcpOtherConstants.EMPTY_JSON);
	}
	
	
	@Test
	public void testarRequisicaoComTokenFalso() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		CcpJsonRepresentation body = super.getJsonDoArquivo("documentation/tests/resume/curriculoComArquivoInvalido.json");
		CcpJsonRepresentation bodyWithFakeSessionToken = body.put("sessionToken", "tokenFalsoSafadoQualquer");
		super.testarEndpoint(StatusExecuteLogin.invalidSession, scenarioName, bodyWithFakeSessionToken, this.uri);

	}

	protected CcpJsonRepresentation getHeaders() {
		return super.getHeaders().put("sessionToken", "NFDP8DV9987EVMBW1H3N56OEGYMFZB");
	}
	
	@Test
	public void salvarCurriculoComArquivoInvalido() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		CcpJsonRepresentation body = super.getJsonDoArquivo("documentation/tests/resume/curriculoComArquivoInvalido.json");
		
		CcpJsonRepresentation jsonDeRetornoDoTeste = super.testarEndpoint(CcpDefaultProcessStatus.CREATED, scenarioName, body, this.uri);
		
		 new CcpGetEntityId(jsonDeRetornoDoTeste)
			.toBeginProcedureAnd()
			.ifThisIdIsNotPresentInEntity(JnEntityAsyncTask.ENTITY).returnStatus(SaveResumeStatus.naoCadastrouMensageria).and()
			.ifThisIdIsNotPresentInEntity(JnEntityEmailMessageSent.ENTITY).returnStatus(SaveResumeStatus.naoEnviouEmail).and()
			;
	}
	
	@Test
	public void salvarCurriculoComArquivoValido() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		CcpJsonRepresentation body = super.getJsonDoArquivo("documentation/tests/resume/curriculoComArquivoValido.json");
		CcpJsonRepresentation jsonDeRetornoDoTeste = super.testarEndpoint(CcpDefaultProcessStatus.CREATED, scenarioName, body, this.uri);
		
		 new CcpGetEntityId(jsonDeRetornoDoTeste)
			.toBeginProcedureAnd()
			.ifThisIdIsNotPresentInEntity(JnEntityAsyncTask.ENTITY).returnStatus(SaveResumeStatus.naoCadastrouMensageria).and()
			.ifThisIdIsNotPresentInEntity(JnEntityEmailMessageSent.ENTITY).returnStatus(SaveResumeStatus.naoEnviouEmail).and()
			;
	}
	
	
	@Test
	public void faltandoCadastrarSenha() {
		
	}

	@Test
	public void faltandoCadastrarPreRegistro() {
		
	}
	
	@Test
	public void senhaBloqueada() {
		
	}

	@Test
	public void tokenBloqueado() {
		
	}

	@Test
	public void usuarioJaLogado() {
	}
	
	@Test
	public void errarParaDepoisAcertarToken() {
		
	}

	@Test
	public void efetuarDesbloqueioDeSenha() {
		
	}
	
	@Test
	public void tokenRecemBloqueado() {
		
	}
	
	@Test
	public void faltandoCadastrarEmail() {
		CcpJsonRepresentation json = CcpOtherConstants.EMPTY_JSON
			.put(JnEntityLoginToken.Fields.email.name(), "onias85@gmail.com")
			.put(JnEntityLoginToken.Fields.userAgent.name(), "teste")
			.put(JnEntityLoginToken.Fields.ip.name(), "teste")
			;
				
		try {
			
			SyncServiceJnLogin.INSTANCE.existsLoginEmail(json);
		} catch (CcpFlow e) {
			System.out.println();
		}	
	}
	
	@Test
	public void senhaRecemBloqueada() {
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void errarParaDepoisAcertarSenha() {
		
		Function<CcpJsonRepresentation, CcpJsonRepresentation> logInTheSystem = json -> SyncServiceJnLogin.INSTANCE.executeLogin(json);
		Function<CcpJsonRepresentation, CcpJsonRepresentation> createLoginEmail = json -> SyncServiceJnLogin.INSTANCE.createLoginEmail(json);
		Function<CcpJsonRepresentation, CcpJsonRepresentation> readTokenFromReceivedEmail = json -> this.readTokenFromReceivedEmail(json);
		Function<CcpJsonRepresentation, CcpJsonRepresentation> createLoginToken = json -> SyncServiceJnLogin.INSTANCE.createLoginToken(json);
		Function<CcpJsonRepresentation, CcpJsonRepresentation> updatePassword = json -> SyncServiceJnLogin.INSTANCE.updatePassword(json);
		Function<CcpJsonRepresentation, CcpJsonRepresentation> saveAnswers = json -> SyncServiceJnLogin.INSTANCE.saveAnswers(json);
		
 		CcpJsonRepresentation parameters = CcpOtherConstants.EMPTY_JSON;
		
		CcpTreeFlow
		.beginThisStatement()
		.tryToExecuteTheGivenFinalTargetProcess(logInTheSystem).usingTheGivenJson(parameters)
		.butIfThisExecutionReturns(StatusExecuteLogin.missingEmail).executeTheGivenProcesses(createLoginEmail)
		.and()
		.ifThisExecutionReturns(StatusCreateLoginEmail.missingPassword).executeTheGivenProcesses(createLoginToken, readTokenFromReceivedEmail, updatePassword)
		.and()
		.ifThisExecutionReturns(StatusCreateLoginEmail.missingAnswers).executeTheGivenProcesses(saveAnswers)
		.and()
		.endThisStatement()
		
		;
	}
	
	private CcpJsonRepresentation readTokenFromReceivedEmail(CcpJsonRepresentation json) {
		//TODO
		return json;
	}
	
	protected String getMethod() {
		return "POST";
	}
	
}

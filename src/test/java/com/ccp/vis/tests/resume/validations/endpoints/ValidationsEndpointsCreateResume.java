package com.ccp.vis.tests.resume.validations.endpoints;

import org.junit.Test;

import com.ccp.constantes.CcpOtherConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.especifications.db.crud.CcpGetEntityId;
import com.ccp.process.CcpDefaultProcessStatus;
import com.ccp.vis.tests.commons.VisTemplateDeTestes;
import com.jn.commons.entities.JnEntityAsyncTask;
import com.jn.commons.entities.JnEntityEmailMessageSent;
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
		
	}
	
	@Test
	public void senhaRecemBloqueada() {
		
	}

	@Test
	public void errarParaDepoisAcertarSenha() {
		
	}

	protected String getMethod() {
		return "POST";
	}
	
}

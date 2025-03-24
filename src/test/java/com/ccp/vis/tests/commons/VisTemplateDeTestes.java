package com.ccp.vis.tests.commons;

import java.util.function.Function;

import com.ccp.constantes.CcpOtherConstants;
import com.ccp.constantes.CcpStringConstants;
import com.ccp.decorators.CcpFileDecorator;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.decorators.CcpTimeDecorator;
import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.especifications.db.utils.CcpDbRequester;
import com.ccp.especifications.db.utils.CcpEntityCrudOperationType;
import com.ccp.especifications.http.CcpHttpHandler;
import com.ccp.especifications.http.CcpHttpResponse;
import com.ccp.especifications.http.CcpHttpResponseType;
import com.ccp.exceptions.process.CcpFlowDisturb;
import com.ccp.flow.CcpTreeFlow;
import com.ccp.http.CcpHttpMethods;
import com.ccp.implementations.db.bulk.elasticsearch.CcpElasticSerchDbBulk;
import com.ccp.implementations.db.crud.elasticsearch.CcpElasticSearchCrud;
import com.ccp.implementations.db.utils.elasticsearch.CcpElasticSearchDbRequest;
import com.ccp.implementations.http.apache.mime.CcpApacheMimeHttp;
import com.ccp.implementations.json.gson.CcpGsonJsonHandler;
import com.ccp.implementations.password.mindrot.CcpMindrotPasswordHandler;
import com.ccp.jn.async.business.factory.CcpJnAsyncBusinessFactory;
import com.ccp.jn.sync.status.login.StatusCreateLoginEmail;
import com.ccp.local.testings.implementations.CcpLocalInstances;
import com.ccp.local.testings.implementations.cache.CcpLocalCacheInstances;
import com.ccp.process.CcpProcessStatus;
import com.jn.commons.entities.JnEntityLoginAnswers;
import com.jn.commons.entities.JnEntityLoginEmail;
import com.jn.commons.entities.JnEntityLoginPassword;
import com.jn.commons.entities.JnEntityLoginPasswordAttempts;
import com.jn.commons.entities.JnEntityLoginToken;
import com.jn.commons.entities.JnEntityLoginTokenAttempts;
import com.jn.commons.status.StatusExecuteLogin;

public abstract class VisTemplateDeTestes {
	protected final String ENDPOINT_URL = "http://localhost:8081/";

	static {
		CcpJnAsyncBusinessFactory asyncBusiness = new CcpJnAsyncBusinessFactory();
		CcpDependencyInjection.loadAllDependencies(
				CcpLocalInstances.mensageriaSender.getLocalImplementation(asyncBusiness),
				new CcpElasticSearchDbRequest(), 
				new CcpMindrotPasswordHandler(),
				CcpLocalCacheInstances.mock,
				new CcpElasticSerchDbBulk(),
				new CcpElasticSearchCrud(),
				new CcpGsonJsonHandler(), 
				new CcpApacheMimeHttp(), 
				CcpLocalInstances.email
				);
		
		String pathToCreateEntityScript = "documentation\\database\\elasticsearch\\scripts\\entities\\create";
		String pathToJavaClasses = "..\\vis-business-commons\\src\\main\\java\\com\\vis\\commons\\entities";
		String mappingJnEntitiesErrors = "c:\\logs\\mappingJnEntitiesErrors.json";
		String insertErrors = "c:\\logs\\insertErrors.json";
		CcpDbRequester database = CcpDependencyInjection.getDependency(CcpDbRequester.class);
		database.createTables(pathToCreateEntityScript, pathToJavaClasses, mappingJnEntitiesErrors, insertErrors);
	}
	
	public final Object getInnerClass(){
		class InnerClass{
			
		}
		return new InnerClass();
	}
	
	
	protected abstract CcpHttpMethods getMethod();

	protected CcpJsonRepresentation getHeaders() {
		return CcpOtherConstants.EMPTY_JSON;
	}

	protected CcpJsonRepresentation testarEndpoint(String uri, String scenarioName, CcpProcessStatus expectedStatus) {
		CcpJsonRepresentation testarEndpoint = this.getJsonResponseFromEndpoint(expectedStatus, scenarioName, CcpOtherConstants.EMPTY_JSON, uri);
		return testarEndpoint;
	}

	protected CcpJsonRepresentation getJsonResponseFromEndpoint(CcpProcessStatus status, String scenarioName, CcpJsonRepresentation body, String uri) {

		CcpJsonRepresentation headers = this.getHeaders();

		CcpJsonRepresentation executeHttpRequest = this.getJsonResponseFromEndpoint(status, scenarioName, body, uri, headers);

		return executeHttpRequest;
	}


	protected CcpJsonRepresentation getJsonResponseFromEndpoint(CcpProcessStatus status, String scenarioName, CcpJsonRepresentation body, String uri,
			CcpJsonRepresentation headers) {
		CcpHttpMethods method = this.getMethod();
		int expectedStatus = status.asNumber();
		CcpHttpHandler http = new CcpHttpHandler(expectedStatus, CcpOtherConstants.DO_NOTHING);
		String path = this.ENDPOINT_URL + uri;
		String name = this.getClass().getName();
		String asUgglyJson = body.asUgglyJson();

		CcpHttpResponse response = http.ccpHttp.executeHttpRequest(path, method, headers, asUgglyJson);

		CcpJsonRepresentation executeHttpRequest = http.executeHttpRequest(name, path, method, headers, asUgglyJson, CcpHttpResponseType.singleRecord, response);

		int actualStatus = response.httpStatus;

		this.logRequestAndResponse(path, method, status, scenarioName, actualStatus, body, headers, executeHttpRequest);
		String message = executeHttpRequest.isInnerJson("message") == false ? executeHttpRequest.getAsString("message") : executeHttpRequest.getValueFromPath("", "message", "statusName");
		status.verifyStatus(actualStatus, message);
		return executeHttpRequest;
	}

	private <V> void logRequestAndResponse(String url, CcpHttpMethods method, CcpProcessStatus status, String scenarioName, int actualStatus,
			CcpJsonRepresentation body, CcpJsonRepresentation headers, V executeHttpRequest) {

		CcpJsonRepresentation md = CcpOtherConstants.EMPTY_JSON.put("x", executeHttpRequest);

		if (executeHttpRequest instanceof CcpJsonRepresentation json) {
			md = json;
		}

		String date = new CcpTimeDecorator().getFormattedDateTime("dd/MM/yyyy HH:mm:ss");

		int expectedStatus = status.asNumber();
		CcpJsonRepresentation put = CcpOtherConstants.EMPTY_JSON.put("url", url).put("method", method).put("actualStatus", actualStatus)
				.put("expectedStatus", expectedStatus).put("headers", headers).put("request", body).put("response", md)
				.put("timestamp", date);
		String asPrettyJson = put.asPrettyJson();

		String testName = this.getClass().getSimpleName();
		new CcpStringDecorator("c:\\rh\\vis\\logs\\").folder().createNewFolderIfNotExists(testName)
				.writeInTheFile(scenarioName + ".json", asPrettyJson);
	}
	
 
	public CcpJsonRepresentation getJsonFile(String path) {
		CcpStringDecorator ccpStringDecorator =	new CcpStringDecorator(path);
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation json = file.asSingleJson();
		return json;

	}
	//tryToExecuteThisProcess
	//usingThisFollowingJson
	//butIfHappens
	//
	
	public CcpJsonRepresentation executeThisFlow(
			Function<CcpJsonRepresentation, CcpJsonRepresentation> first
			, CcpJsonRepresentation flow
			, CcpJsonRepresentation json
			) {
		
		
		try {
			CcpJsonRepresentation apply = first.apply(json);
			return apply;
		} catch (CcpFlowDisturb e) {
			Function<CcpJsonRepresentation, CcpJsonRepresentation> nextFlow = flow.getAsObject(e.status.name());
			nextFlow.apply(json);
			CcpJsonRepresentation executeThisFlow = this.executeThisFlow(first, flow, json);
			return executeThisFlow;
		}
	}

	@SuppressWarnings("unchecked")
	protected final CcpJsonRepresentation createLogin(Function<CcpJsonRepresentation, CcpJsonRepresentation>... whatToNext) {
		
		CcpJsonRepresentation sessionValuesToTest = this.getSessionValuesToTest();
		
		CcpJsonRepresentation endThisStatement = CcpTreeFlow// fluxo de arvore
		.beginThisStatement()//iniciando comando
		.tryToExecuteTheGivenFinalTargetProcess(LoginActions.executeLogin).usingTheGivenJson(sessionValuesToTest)// executar um processo alvo, usando um json fornecido
		.butIfThisExecutionReturns(StatusExecuteLogin.missingSavingEmail).thenExecuteTheGivenProcesses(LoginActions.createLoginEmail)// se esta execução retornar que o e-mail está faltando, entao ele vai executar o processo de criação de e-mail
		.and()//e
		.ifThisExecutionReturns(StatusCreateLoginEmail.loginConflict)
		.thenExecuteTheGivenProcesses(
				LoginActions.executeLogout,
				JnEntityLoginEmail.ENTITY.getOperationCallback(CcpEntityCrudOperationType.delete),
				JnEntityLoginPassword.ENTITY.getOperationCallback(CcpEntityCrudOperationType.delete),
				JnEntityLoginPasswordAttempts.ENTITY.getOperationCallback(CcpEntityCrudOperationType.delete),
				JnEntityLoginAnswers.ENTITY.getOperationCallback(CcpEntityCrudOperationType.delete),
				JnEntityLoginPassword.ENTITY.getTwinEntity().getOperationCallback(CcpEntityCrudOperationType.delete),
				JnEntityLoginToken.ENTITY.getTwinEntity().getOperationCallback(CcpEntityCrudOperationType.delete),
				JnEntityLoginTokenAttempts.ENTITY.getOperationCallback(CcpEntityCrudOperationType.delete),
				JnEntityLoginToken.ENTITY.getOperationCallback(CcpEntityCrudOperationType.delete)

				 ).and()
		.ifThisExecutionReturns(StatusCreateLoginEmail.missingSavePassword).thenExecuteTheGivenProcesses(
				LoginActions.saveAnswers, LoginActions.createLoginToken, LoginActions.readTokenFromReceivedEmail, 
				LoginActions.savePassword, LoginActions.renameTokenField, LoginActions.executeLogout)// se ele retornar que está faltando a senha, vai executar os processos de 
		.and()// e
		.ifThisExecutionReturns(StatusCreateLoginEmail.missingSaveAnswers).thenExecuteTheGivenProcesses(LoginActions.saveAnswers)
		.and()
		.endThisStatement(whatToNext);
		
		return endThisStatement;
	}
	//LATER melhorar a intuitividade dos bounds no retorno do json

	@SuppressWarnings("unchecked")
	protected final CcpJsonRepresentation getJsonResponseFromEndpoint(CcpProcessStatus processStatus, String scenarioName,
			String pathToJsonFile, Function<CcpJsonRepresentation, CcpJsonRepresentation>... whatToNext) {
		CcpJsonRepresentation jsonFile = this.getJsonFile(pathToJsonFile);
		CcpJsonRepresentation loginData = this.createLogin(whatToNext);
		CcpJsonRepresentation body = jsonFile;
		CcpJsonRepresentation headers = loginData;
		
		String uri = this.getUri();
		CcpJsonRepresentation responseFromEndpoint = this.getJsonResponseFromEndpoint(processStatus, scenarioName, body, uri, headers);
		JnEntityLoginEmail.ENTITY.delete(body);
		JnEntityLoginPassword.ENTITY.delete(body);
		JnEntityLoginPasswordAttempts.ENTITY.delete(body);
		JnEntityLoginAnswers.ENTITY.delete(body);
		JnEntityLoginPassword.ENTITY.getTwinEntity().delete(body);
		JnEntityLoginTokenAttempts.ENTITY.delete(body);
		JnEntityLoginToken.ENTITY.getTwinEntity().delete(body);
		JnEntityLoginToken.ENTITY.delete(body);
		
		return responseFromEndpoint;
	}

	protected abstract String getUri();

	protected final CcpJsonRepresentation getSessionValuesToTest() {
		CcpJsonRepresentation json = CcpOtherConstants.EMPTY_JSON
				.put(JnEntityLoginToken.Fields.email.name(), "onias85@gmail.com")
				.put(JnEntityLoginToken.Fields.userAgent.name(), "Apache-HttpClient/4.5.4 (Java/17.0.9)")
				.put(CcpStringConstants.LANGUAGE.value, "portuguese")
				.put(JnEntityLoginToken.Fields.ip.name(), "localhost")
				;

		return json;
	}

}

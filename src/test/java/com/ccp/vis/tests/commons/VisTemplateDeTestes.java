package com.ccp.vis.tests.commons;

import com.ccp.constantes.CcpOtherConstants;
import com.ccp.decorators.CcpFileDecorator;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.decorators.CcpTimeDecorator;
import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.especifications.db.utils.CcpDbRequester;
import com.ccp.especifications.http.CcpHttpHandler;
import com.ccp.especifications.http.CcpHttpResponse;
import com.ccp.especifications.http.CcpHttpResponseTransform;
import com.ccp.especifications.http.CcpHttpResponseType;
import com.ccp.implementations.db.bulk.elasticsearch.CcpElasticSerchDbBulk;
import com.ccp.implementations.db.crud.elasticsearch.CcpElasticSearchCrud;
import com.ccp.implementations.db.utils.elasticsearch.CcpElasticSearchDbRequest;
import com.ccp.implementations.http.apache.mime.CcpApacheMimeHttp;
import com.ccp.implementations.json.gson.CcpGsonJsonHandler;
import com.ccp.implementations.password.mindrot.CcpMindrotPasswordHandler;
import com.ccp.local.testings.implementations.cache.CcpLocalCacheInstances;
import com.ccp.process.CcpProcessStatus;

public abstract class VisTemplateDeTestes {
	protected final String ENDPOINT_URL = "http://localhost:8081/";

	static {
		CcpDependencyInjection.loadAllDependencies(
				new CcpElasticSearchDbRequest(), 
				new CcpMindrotPasswordHandler(),
				CcpLocalCacheInstances.mock,
				new CcpElasticSearchCrud(),
				new CcpGsonJsonHandler(), 
				new CcpApacheMimeHttp(), 
				new CcpElasticSerchDbBulk()
				
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
	
	
	protected abstract String getMethod();

	protected CcpJsonRepresentation getHeaders() {
		return CcpOtherConstants.EMPTY_JSON;
	}

	protected CcpJsonRepresentation testarEndpoint(String uri, String scenarioName, CcpProcessStatus expectedStatus) {
		CcpJsonRepresentation testarEndpoint = this.testarEndpoint(expectedStatus, scenarioName, CcpOtherConstants.EMPTY_JSON, uri,
				CcpHttpResponseType.singleRecord);
		return testarEndpoint;
	}

	protected <V> V testarEndpoint(CcpProcessStatus status, String scenarioName, CcpJsonRepresentation body, String uri,
			CcpHttpResponseTransform<V> transformer) {

		String method = this.getMethod();
		CcpJsonRepresentation headers = this.getHeaders();

		int expectedStatus = status.asNumber();
		CcpHttpHandler http = new CcpHttpHandler(expectedStatus, CcpOtherConstants.DO_NOTHING);
		String path = this.ENDPOINT_URL + uri;
		String name = this.getClass().getName();
		String asUgglyJson = body.asUgglyJson();

		CcpHttpResponse response = http.ccpHttp.executeHttpRequest(path, method, headers, asUgglyJson);

		V executeHttpRequest = http.executeHttpRequest(name, path, method, headers, asUgglyJson, transformer, response);

		int actualStatus = response.httpStatus;

		this.logRequestAndResponse(path, method, status, scenarioName, actualStatus, body, headers, executeHttpRequest);

		status.verifyStatus(actualStatus);

		return executeHttpRequest;
	}

	private <V> void logRequestAndResponse(String url, String method, CcpProcessStatus status, String scenarioName, int actualStatus,
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
	
 
	public CcpJsonRepresentation getJsonDoArquivo(String path) {
		CcpStringDecorator ccpStringDecorator =	new CcpStringDecorator(path);
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation json = file.asSingleJson();
		return json;

	}
}

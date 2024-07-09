package com.ccp.vis.tests.commons;

import com.ccp.constantes.CcpConstants;
import com.ccp.decorators.CcpFileDecorator;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.especifications.db.utils.CcpDbRequester;
import com.ccp.implementations.cache.gcp.memcache.CcpGcpMemCache;
import com.ccp.implementations.db.bulk.elasticsearch.CcpElasticSerchDbBulk;
import com.ccp.implementations.db.crud.elasticsearch.CcpElasticSearchCrud;
import com.ccp.implementations.db.utils.elasticsearch.CcpElasticSearchDbRequest;
import com.ccp.implementations.email.sendgrid.CcpSendGridEmailSender;
import com.ccp.implementations.file.bucket.gcp.CcpGcpFileBucket;
import com.ccp.implementations.http.apache.mime.CcpApacheMimeHttp;
import com.ccp.implementations.json.gson.CcpGsonJsonHandler;
import com.ccp.implementations.mensageria.sender.gcp.pubsub.CcpGcpPubSubMensageriaSender;
import com.ccp.implementations.text.extractor.apache.tika.CcpApacheTikaTextExtractor;
import com.ccp.local.testings.implementations.CcpLocalInstances;
import com.ccp.validation.CcpJsonInvalid;
import com.ccp.vis.async.business.factory.CcpVisAsyncBusinessFactory;
import com.jn.commons.utils.JnGenerateRandomToken;

public class BaseTest {

	public final static String SESSION_TOKEN = CcpConstants.EMPTY_JSON.getTransformed(
			new JnGenerateRandomToken(8, "token")).getAsString("token");
	
	public final static CcpJsonRepresentation REQUEST_TO_LOGIN = CcpConstants.EMPTY_JSON
			.put("userAgent", "Apache-HttpClient/4.5.4 (Java/17.0.9)")
			.put("sessionToken", SESSION_TOKEN)
			.put("ip", "localhost:8080")
			;

	public final static CcpJsonRepresentation ANSWERS_JSON =  REQUEST_TO_LOGIN.put("goal", "jobs").put("channel", "linkedin");

	static {
		boolean localEnviroment = new CcpStringDecorator("c:\\rh").file().exists();

		CcpVisAsyncBusinessFactory businessInstanceProvider = new CcpVisAsyncBusinessFactory();
		
		CcpDependencyInjection.loadAllDependencies(
				new CcpApacheMimeHttp(), 
				new CcpGsonJsonHandler(), 
				new CcpElasticSearchCrud(),
				new CcpElasticSearchDbRequest(),
				new CcpApacheTikaTextExtractor(),
				localEnviroment ? CcpLocalInstances.bucket.getLocalImplementation(businessInstanceProvider) : new CcpGcpFileBucket(),
				localEnviroment ? CcpLocalInstances.email.getLocalImplementation(businessInstanceProvider) : new CcpSendGridEmailSender(),
				localEnviroment ? CcpLocalInstances.cache.getLocalImplementation(businessInstanceProvider) : new CcpGcpMemCache(),
				localEnviroment ? CcpLocalInstances.mensageriaSender.getLocalImplementation(businessInstanceProvider) : new CcpGcpPubSubMensageriaSender()
				);	
		
		CcpDependencyInjection.loadAllDependencies(new CcpGsonJsonHandler(), new CcpElasticSearchCrud(),
				new CcpElasticSearchDbRequest(), new CcpApacheMimeHttp(),
				new CcpElasticSerchDbBulk());
		
		createTables();
	}
	private static void createTables() {
		String pathToCreateEntityScript = "documentation\\database\\elasticsearch\\scripts\\entities\\create";
		String pathToJavaClasses = "..\\vis-business-commons\\src\\main\\java\\com\\vis\\commons\\entities";
		String mappingJnEntitiesErrors = "c:\\logs\\mappingJnEntitiesErrors.json";
		String insertErrors = "c:\\logs\\insertErrors.json";
		CcpDbRequester database = CcpDependencyInjection.getDependency(CcpDbRequester.class);
		database.createTables(pathToCreateEntityScript, pathToJavaClasses, mappingJnEntitiesErrors, insertErrors);
	}
	protected void saveErrors(CcpFileDecorator file, CcpJsonInvalid e) {
		String path = file.getPath().replace(".json", "_errors.json");
		String message = e.getMessage();
		CcpFileDecorator reset = new CcpStringDecorator(path).file().reset();
		reset.append(message);
		throw new RuntimeException(e);
	}
}

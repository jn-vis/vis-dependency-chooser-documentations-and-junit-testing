package com.ccp.vis.tests.commons;

import com.ccp.constantes.CcpConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.implementations.db.crud.elasticsearch.CcpElasticSearchCrud;
import com.ccp.implementations.db.utils.elasticsearch.CcpElasticSearchDbRequest;
import com.ccp.implementations.http.apache.mime.CcpApacheMimeHttp;
import com.ccp.implementations.json.gson.CcpGsonJsonHandler;
import com.ccp.implementations.mensageria.sender.gcp.pubsub.CcpGcpPubSubMensageriaSender;
import com.ccp.implementations.mensageria.sender.gcp.pubsub.local.CcpLocalEndpointMensageriaSender;
import com.jn.commons.utils.JnGenerateRandomToken;

public class BaseTest {
	static {
		boolean localEnviroment = new CcpStringDecorator("c:\\rh").file().exists();

		CcpDependencyInjection.loadAllDependencies(new CcpGsonJsonHandler(), new CcpElasticSearchCrud(),
				new CcpElasticSearchDbRequest(), new CcpApacheMimeHttp(),
				localEnviroment ? new CcpLocalEndpointMensageriaSender() : new CcpGcpPubSubMensageriaSender()

				);	
	}

	public final static String SESSION_TOKEN = CcpConstants.EMPTY_JSON.getTransformed(
			new JnGenerateRandomToken(8, "token")).getAsString("token");
	
	public CcpJsonRepresentation REQUEST_TO_LOGIN = CcpConstants.EMPTY_JSON
			.put("userAgent", "Apache-HttpClient/4.5.4 (Java/17.0.9)")
			.put("sessionToken", SESSION_TOKEN)
			.put("ip", "localhost:8080")
			;
			

}

package com.ccp.vis.tests.commons;

import java.util.function.Function;

import com.ccp.constantes.CcpConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.especifications.db.bulk.CcpEntityOperationType;
import com.ccp.especifications.http.CcpHttpHandler;
import com.ccp.especifications.http.CcpHttpResponse;
import com.ccp.jn.async.commons.JnAsyncCommitAndAudit;
import com.ccp.json.transformers.CcpJsonTransformerGenerateFieldHash;
import com.ccp.json.transformers.CcpJsonTransformerGenerateRandomToken;
import com.ccp.json.transformers.CcpJsonTransformerPutPasswordField;
import com.jn.commons.entities.JnEntityLoginAnswers;
import com.jn.commons.entities.JnEntityLoginEmail;
import com.jn.commons.entities.JnEntityLoginPassword;
import com.jn.commons.entities.JnEntityLoginSessionCurrent;
import com.jn.commons.entities.JnEntityLoginToken;

public class CreateLoginAndSession implements Function<CcpJsonRepresentation, CcpJsonRepresentation>{

	private CreateLoginAndSession() {}
	
	public static final CreateLoginAndSession INSTANCE = new CreateLoginAndSession();
	
	
	CcpHttpHandler http = new CcpHttpHandler(200, CcpConstants.DO_NOTHING);

	public CcpJsonRepresentation apply(CcpJsonRepresentation candidate) {
		String email = candidate.getAsString("id");
		
		CcpJsonRepresentation createLogin = this.createLogin(email);
		
		CcpJsonRepresentation jsonWithSessionToken = this.executeLogin(email);
		
		CcpJsonRepresentation putAll = candidate.putAll(jsonWithSessionToken).putAll(createLogin);
		
		return putAll;
	}

	private CcpJsonRepresentation executeLogin(String email) {
	
		String path = "http://localhost:8080/login/{email}".replace("{email}", email);
		
		String asUgglyJson = CcpConstants.EMPTY_JSON.put("password", "Jobsnow1!").asUgglyJson();
		
		CcpHttpResponse response = this.http.ccpHttp.executeHttpRequest(path, "POST", CcpConstants.EMPTY_JSON, asUgglyJson, 200);
		
		CcpJsonRepresentation asSingleJson = response.asSingleJson();
		return asSingleJson;
	}
	
	@SuppressWarnings("unchecked")
	private CcpJsonRepresentation createLogin(String email) {
		var tokenGenerator = new CcpJsonTransformerGenerateRandomToken(8, "token");
		var passwordGenerator = new CcpJsonTransformerPutPasswordField("password");
		var fieldHashGenerator = new CcpJsonTransformerGenerateFieldHash("email", "originalEmail");
		CcpJsonRepresentation transformed = CcpConstants.EMPTY_JSON
		.put("userAgent", "Apache-HttpClient/4.5.4 (Java/17.0.9)")
		.put("password", "Jobsnow1!")
		.put("ip", "localhost:8080")
		.put("channel", "linkedin")
		.put("goal", "jobs")
		.put("email", email)
		.getTransformedJson(
				tokenGenerator,
				fieldHashGenerator,
				passwordGenerator
		);
		
		JnAsyncCommitAndAudit.INSTANCE.executeBulk(transformed, CcpEntityOperationType.create, 
				JnEntityLoginPassword.INSTANCE,
				JnEntityLoginAnswers.INSTANCE,
				JnEntityLoginToken.INSTANCE,
				JnEntityLoginEmail.INSTANCE
				);
		
		JnEntityLoginSessionCurrent.INSTANCE.delete(transformed);
		
		CcpJsonRepresentation renameField = transformed.renameField("originalEmail", "email");
		return renameField;
	}

}

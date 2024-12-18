package com.ccp.vis.tests.resume.validations.endpoints;

import org.junit.Test;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.especifications.http.CcpHttpResponseType;
import com.ccp.process.CcpProcessStatus;
import com.ccp.vis.tests.commons.VisTemplateDeTestes;

public class ValidationsEndpointsUpdateResume  extends VisTemplateDeTestes{

	@Test
	public void saveResume() {
		
		String uri = ENDPOINT_URL + "/resume/{email}";
		CcpJsonRepresentation headers = super.getHeaders();
		CcpJsonRepresentation jsonDeRetornoDoTeste = super.testarEndpoint(CcpProcessStatus.UPDATED, headers, uri, CcpHttpResponseType.singleRecord);
		
	}

	protected String getMethod() {
		return "PATCH";
	}
	
}

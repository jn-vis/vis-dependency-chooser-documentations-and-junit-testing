package com.ccp.vis.tests.resume.validations.endpoints;

import org.junit.Test;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.process.CcpDefaultProcessStatus;
import com.ccp.vis.tests.commons.VisTemplateDeTestes;

public class ValidationsEndpointsUpdateResume  extends VisTemplateDeTestes{

	@Test
	public void saveResume() {
		String scenarioName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		String uri = ENDPOINT_URL + "/resume/{email}";
		CcpJsonRepresentation headers = super.getHeaders();
		CcpJsonRepresentation jsonDeRetornoDoTeste = super.getJsonResponseFromEndpoint(CcpDefaultProcessStatus.UPDATED, scenarioName, headers, uri);
		System.out.println(jsonDeRetornoDoTeste);
	}

	protected String getMethod() {
		return "PATCH";
	}
	
}

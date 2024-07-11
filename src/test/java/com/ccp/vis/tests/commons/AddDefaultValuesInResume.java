package com.ccp.vis.tests.commons;

import java.util.Arrays;
import java.util.function.Function;

import com.ccp.decorators.CcpJsonRepresentation;
import com.vis.commons.entities.VisEntityResume;

public class AddDefaultValuesInResume implements Function<CcpJsonRepresentation, CcpJsonRepresentation>{

	public static final AddDefaultValuesInResume INSTANCE = new AddDefaultValuesInResume();

	private AddDefaultValuesInResume(){}
	
	public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
		
		CcpJsonRepresentation addCompaniesNotAllowed = json.putIfNotContains(VisEntityResume.Fields.companiesNotAllowed.name(), Arrays.asList());
		
		CcpJsonRepresentation addDisabilities = addCompaniesNotAllowed.putIfNotContains(VisEntityResume.Fields.disabilities.name(), Arrays.asList());
	
		CcpJsonRepresentation addDesiredJob = addDisabilities.copyIfNotContains(VisEntityResume.Fields.lastJob.name(), VisEntityResume.Fields.desiredJob.name());
		
		CcpJsonRepresentation addExperience = addDesiredJob.whenHasNotField(VisEntityResume.Fields.experience.name(), AddExperience.INSTANCE);
		
		return addExperience;
	}

}

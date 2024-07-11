package com.ccp.vis.tests.commons;

import java.util.Calendar;
import java.util.function.Function;

import com.ccp.decorators.CcpJsonRepresentation;
import com.vis.commons.entities.VisEntityResume;

public class AddExperience implements Function<CcpJsonRepresentation, CcpJsonRepresentation>{

	private AddExperience() {}
	
	public static final AddExperience INSTANCE = new AddExperience();
			
	
	public CcpJsonRepresentation apply(CcpJsonRepresentation json) {

		Long dataDeInclusao = json.getAsLongNumber("dataDeInclusao");
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTimeInMillis(dataDeInclusao);
		
		int year = cal.get(Calendar.YEAR);
		
		CcpJsonRepresentation put = json.put(VisEntityResume.Fields.experience.name(), year);
		
		return put;
	}

}

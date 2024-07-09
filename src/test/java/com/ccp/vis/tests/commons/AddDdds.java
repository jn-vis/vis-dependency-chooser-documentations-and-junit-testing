package com.ccp.vis.tests.commons;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.ccp.decorators.CcpJsonRepresentation;
import com.vis.commons.entities.VisEntityResume;

public class AddDdds implements Function<CcpJsonRepresentation, CcpJsonRepresentation> {
	
	public static final AddDdds INSTANCE = new AddDdds();

	private AddDdds() {}
	
	public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
		boolean mudanca = json.getAsBoolean("mudanca");

		if (mudanca) {
			List<String> ddds = Arrays.asList("10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22",
					"24", "27", "28", "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46",
					"47", "48", "49", "51", "53", "54", "55", "61", "62", "63", "64", "65", "66", "67", "68", "69",
					"71", "73", "74", "75", "77", "79", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91",
					"92", "93", "94", "95", "96", "97", "98", "99");

			CcpJsonRepresentation put = json.put(VisEntityResume.Fields.ddd.name(), ddds);

			return put;
		}

		boolean homeoffice = json.getAsBoolean("homeoffice");

		if (homeoffice) {
			List<String> ddds = Arrays.asList("10");
			CcpJsonRepresentation put = json.put(VisEntityResume.Fields.ddd.name(), ddds);
			return put;
		}

		String ddd = json.getAsString(VisEntityResume.Fields.ddd.name());
		List<String> ddds = Arrays.asList(ddd);
		CcpJsonRepresentation put = json.put(VisEntityResume.Fields.ddd.name(), ddds);
		return put;
	}

}

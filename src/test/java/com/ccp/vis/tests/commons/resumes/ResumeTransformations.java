package com.ccp.vis.tests.commons.resumes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.ccp.constantes.CcpOtherConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.especifications.db.bulk.CcpEntityBulkOperationType;
import com.ccp.especifications.http.CcpHttpHandler;
import com.ccp.especifications.http.CcpHttpResponse;
import com.ccp.http.CcpHttpMethods;
import com.ccp.json.transformers.CcpTransformers;
import com.jn.db.bulk.JnExecuteBulkOperation;
import com.jn.entities.JnEntityLoginAnswers;
import com.jn.entities.JnEntityLoginEmail;
import com.jn.entities.JnEntityLoginPassword;
import com.jn.entities.JnEntityLoginSessionValidation;
import com.jn.entities.JnEntityLoginToken;
import com.vis.commons.entities.VisEntityResume;

public enum ResumeTransformations implements CcpTransformers{
	AddDddsInResume {
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			
			List<String> ddds = Arrays.asList("10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22",
					"24", "27", "28", "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46",
					"47", "48", "49", "51", "53", "54", "55", "61", "62", "63", "64", "65", "66", "67", "68", "69",
					"71", "73", "74", "75", "77", "79", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91",
					"92", "93", "94", "95", "96", "97", "98", "99");
			
			boolean mudanca = json.getAsBoolean("mudanca");

			if (mudanca) {

				CcpJsonRepresentation put = json.put(VisEntityResume.Fields.ddd.name(), ddds);

				return put;
			}

			boolean homeoffice = json.getAsBoolean("homeoffice");

			if (homeoffice) {
				List<String> ddd10 = Arrays.asList("10");
				CcpJsonRepresentation put = json.put(VisEntityResume.Fields.ddd.name(), ddd10);
				return put;
			}

			try {
				Integer ddd = json.getAsIntegerNumber("ddd");
				boolean equals = Integer.valueOf(0).equals(ddd);
				if(equals) {
					CcpJsonRepresentation put = json.put(VisEntityResume.Fields.ddd.name(), ddds);
					return put;
				}
			} catch (Exception e) {

			}
			
			String ddd = json.getAsString(VisEntityResume.Fields.ddd.name());
			List<String> ddd10 = Arrays.asList(ddd).stream().filter(x -> new CcpStringDecorator(x).isLongNumber()).collect(Collectors.toList());
			CcpJsonRepresentation put = json.put(VisEntityResume.Fields.ddd.name(), ddd10);
			return put;
		}
	},
	AddExperience {
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			
			boolean containsAllFields = json.containsAllFields(VisEntityResume.Fields.experience.name());
			if(containsAllFields) {
				return json;
			}
			
			Long dataDeInclusao = json.getAsLongNumber("dataDeInclusao");
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTimeInMillis(dataDeInclusao);
			
			int year = cal.get(Calendar.YEAR);
			
			CcpJsonRepresentation put = json.put(VisEntityResume.Fields.experience.name(), year);
			
			return put;
		}
	},
	AddDisponibility {
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation put = this.addLongValue(json, VisEntityResume.Fields.disponibility.name(), 0L);
			return put;
		}
	},
	CreateLoginAndSession {
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			String email = json.getAsString("id");
			
			CcpJsonRepresentation createLogin = this.createLogin(email);
			try {
				CcpJsonRepresentation jsonWithSessionToken = this.executeLogin(email);
				
				CcpJsonRepresentation putAll = json.putAll(jsonWithSessionToken).putAll(createLogin);
				
				return putAll;
			} catch (Exception e) {
				new CcpStringDecorator("c:\\logs\\resumes").folder().createNewFolderIfNotExists("wrongEmails").createNewFileIfNotExists(email);
				CcpJsonRepresentation putAll = json.putAll(createLogin);
				return putAll;
			}
		}
		
		private CcpJsonRepresentation executeLogin(String email) {
			
			String path = "http://localhost:8080/login/{email}".replace("{email}", email);
			
			String asUgglyJson = CcpOtherConstants.EMPTY_JSON.put(JnEntityLoginPassword.Fields.password.name(), "Jobsnow1!").asUgglyJson();

			CcpHttpHandler http = new CcpHttpHandler(200, CcpOtherConstants.DO_NOTHING);
			
			CcpHttpResponse response = http.ccpHttp.executeHttpRequest(path, CcpHttpMethods.POST, CcpOtherConstants.EMPTY_JSON, asUgglyJson, 200);
			
			CcpJsonRepresentation asSingleJson = response.asSingleJson();
			return asSingleJson;
		}
		
		private CcpJsonRepresentation createLogin(String email) {
			CcpJsonRepresentation transformed = CcpOtherConstants.EMPTY_JSON
			.put(JnEntityLoginSessionValidation.Fields.userAgent.name(), "Apache-HttpClient/4.5.4 (Java/17.0.9)")
			.put(JnEntityLoginPassword.Fields.password.name(), "Jobsnow1!")
			.put(JnEntityLoginToken.Fields.ip.name(), "localhost:8080")
			.put(JnEntityLoginAnswers.Fields.channel.name(), "linkedin")
			.put(JnEntityLoginAnswers.Fields.goal.name(), "jobs")
			.put(JnEntityLoginAnswers.Fields.email.name(), email)
			;
			
			JnExecuteBulkOperation.INSTANCE.executeBulk(transformed, CcpEntityBulkOperationType.create, 
					JnEntityLoginPassword.ENTITY,
					JnEntityLoginAnswers.ENTITY,
					JnEntityLoginToken.ENTITY,
					JnEntityLoginEmail.ENTITY
					);
			
			JnEntityLoginSessionValidation.ENTITY.delete(transformed);
			
			CcpJsonRepresentation renameField = transformed.renameField("originalEmail", "email");
			return renameField;
		}

	},
	AddCltValue {
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation put = this.addRequiredAtLeastOne(json, VisEntityResume.Fields.clt.name(), 1000, 
					VisEntityResume.Fields.clt.name(),
					VisEntityResume.Fields.pj.name()
					);
			return put;
		}
	},
	AddBtcValue {
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation put = this.putMinValue(json, VisEntityResume.Fields.btc.name(), 1000);
			return put;
		}
	},
	AddMinCltValue {
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation put = this.putMinValue(json, VisEntityResume.Fields.clt.name(), 1000);
			return put;
		}
	},
	AddMinPjValue {
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation put = this.putMinValue(json, VisEntityResume.Fields.clt.name(), 1000);
			return put;
		}
	},
	AddDesiredJob {
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation put = this.substring(json, VisEntityResume.Fields.desiredJob.name(), 100);
			return put;
		}
	},
	AddLastJob {
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation put = this.substring(json, VisEntityResume.Fields.lastJob.name(), 100);
			return put;
		}
	},
	AddObservations {
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation put = this.substring(json, "observations", 500);
			return put;
		}
	},
	;
	abstract public CcpJsonRepresentation apply(CcpJsonRepresentation json);

}

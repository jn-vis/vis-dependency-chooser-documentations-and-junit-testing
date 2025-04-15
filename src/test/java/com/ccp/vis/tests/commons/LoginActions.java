package com.ccp.vis.tests.commons;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

import com.ccp.constantes.CcpOtherConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.especifications.db.utils.CcpEntity;
import com.ccp.exceptions.db.utils.CcpEntityPrimaryKeyIsMissing;
import com.ccp.exceptions.process.CcpFlowDisturb;
import com.jn.business.login.JnBusinessSendUserToken;
import com.jn.entities.JnEntityEmailMessageSent;
import com.jn.entities.JnEntityLoginAnswers;
import com.jn.entities.JnEntityLoginEmail;
import com.jn.entities.JnEntityLoginPassword;
import com.jn.entities.JnEntityLoginSessionConflict;
import com.jn.entities.JnEntityLoginSessionValidation;
import com.jn.entities.JnEntityLoginToken;
import com.jn.services.JnServiceLogin;

public enum LoginActions implements Function<CcpJsonRepresentation, CcpJsonRepresentation> {
	saveAnswers(JnEntityLoginAnswers.ENTITY),
	executeLogin(JnEntityLoginSessionConflict.ENTITY, JnEntityLoginSessionValidation.ENTITY),
	savePassword(JnEntityLoginPassword.ENTITY),
	executeLogout(JnEntityLoginSessionValidation.ENTITY.getTwinEntity()),
	createLoginToken(JnEntityLoginToken.ENTITY, JnEntityEmailMessageSent.ENTITY),
	createLoginEmail(JnEntityLoginEmail.ENTITY),
	renameTokenField{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation renameField = json.renameField("sessionToken", "token");
			return renameField;
		}
	},
	readTokenFromReceivedEmail{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			String originalToken = new CcpStringDecorator("c:\\logs\\email\\"+ JnBusinessSendUserToken.class.getName() + ".json")
			.file().asSingleJson().getAsString("originalToken");
			CcpJsonRepresentation put = json.put("token", originalToken);
			return put;
		}
	},
	;
	private final CcpEntity[] entities;
	
	private LoginActions(CcpEntity... entities) {
		this.entities = entities;
	}

	/*
	 * 
		executeLogin
		createLoginEmail
		executeLogin
		saveAnswers
		createLoginToken
		savePassword
	 */
	public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
		try {
			LoginActions[] values = values();
			System.out.println("Executando: " + this);
			for (LoginActions loginActions : values) {
				if(loginActions.entities.length == 0) {
					continue;
				}
				CcpJsonRepresentation jsonWithSubjectType = json.put(JnEntityEmailMessageSent.Fields.subjectType.name(), JnBusinessSendUserToken.class.getName());
				loginActions.printAllStatus(jsonWithSubjectType);
			}
			System.out.println("-----------------------------------------");
			
			boolean equals = LoginActions.executeLogout.equals(this);
			if(equals) {
//				System.out.println();
			}
			Class<? extends JnServiceLogin> clazz = JnServiceLogin.INSTANCE.getClass();
			Method declaredMethod = clazz.getDeclaredMethod(this.name(), CcpJsonRepresentation.class);
//			System.out.println("tentando executar: " + this);
			Object invoke = declaredMethod.invoke(JnServiceLogin.INSTANCE, json);
			CcpJsonRepresentation jsn = (CcpJsonRepresentation)invoke;
			return jsn;
		}catch(InvocationTargetException e) {
			if(e.getCause() instanceof CcpFlowDisturb flowDisturb) {
//				System.out.println("Desvio de fluxo: " + flowDisturb.status);
				throw flowDisturb;
			}
			throw new RuntimeException(e);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void printAllStatus(CcpJsonRepresentation json) {
		if(this.entities.length == 0) {
			return;
		}

		CcpJsonRepresentation allStatus = CcpOtherConstants.EMPTY_JSON;
		
		for (CcpEntity entity : this.entities) {
			String entityName = entity.getEntityName();
			try {
				boolean exists = entity.exists(json);
				allStatus = allStatus.put(entityName, exists);
			} catch (CcpEntityPrimaryKeyIsMissing e) {
				allStatus = allStatus.put(entityName, false);
			}
		}
		System.out.println(this + ": " + allStatus);
	}
}

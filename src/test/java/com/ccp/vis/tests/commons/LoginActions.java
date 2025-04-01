package com.ccp.vis.tests.commons;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.exceptions.process.CcpFlowDisturb;
import com.ccp.jn.sync.service.JnSyncServiceLogin;
import com.jn.commons.utils.JnAsyncBusiness;

public enum LoginActions implements Function<CcpJsonRepresentation, CcpJsonRepresentation> {
	saveAnswers,
	executeLogin,
	savePassword,
	executeLogout,
	createLoginToken,
	createLoginEmail,
	renameTokenField{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation renameField = json.renameField("sessionToken", "token");
			return renameField;
		}
	},
	readTokenFromReceivedEmail{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			String originalToken = new CcpStringDecorator("c:\\logs\\email\\"+ JnAsyncBusiness.sendUserToken.name() + ".json")
			.file().asSingleJson().getAsString("originalToken");
			CcpJsonRepresentation put = json.put("token", originalToken);
			return put;
		}
	},
	;

	public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
		try {
			boolean equals = LoginActions.createLoginToken.equals(this);
			if(equals) {
				System.out.println();
			}
			Class<? extends JnSyncServiceLogin> clazz = JnSyncServiceLogin.INSTANCE.getClass();
			Method declaredMethod = clazz.getDeclaredMethod(this.name(), CcpJsonRepresentation.class);
			System.out.println("tentando executar: " + this);
			Object invoke = declaredMethod.invoke(JnSyncServiceLogin.INSTANCE, json);
			CcpJsonRepresentation jsn = (CcpJsonRepresentation)invoke;
			return jsn;
		}catch(InvocationTargetException e) {
			if(e.getCause() instanceof CcpFlowDisturb flowDisturb) {
				System.out.println("Desvio de fluxo: " + flowDisturb.status);
				throw flowDisturb;
			}
			throw new RuntimeException(e);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

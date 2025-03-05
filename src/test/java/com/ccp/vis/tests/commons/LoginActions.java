package com.ccp.vis.tests.commons;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.exceptions.process.CcpFlowDiversion;
import com.ccp.jn.sync.service.SyncServiceJnLogin;

enum LoginActions implements Function<CcpJsonRepresentation, CcpJsonRepresentation> {
	saveAnswers,
	executeLogin,
	savePassword,
	executeLogout,
	createLoginToken,
	createLoginEmail,
	readTokenFromReceivedEmail{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			//FIXME
			return null;
		}
		
	};

	public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
		try {
			Class<? extends SyncServiceJnLogin> clazz = SyncServiceJnLogin.INSTANCE.getClass();
			Method declaredMethod = clazz.getDeclaredMethod(this.name(), CcpJsonRepresentation.class);
			Object invoke = declaredMethod.invoke(SyncServiceJnLogin.INSTANCE, json);
			return (CcpJsonRepresentation)invoke;
		}catch(InvocationTargetException e) {
			if(e.getCause() instanceof CcpFlowDiversion flowDiversion) {
				throw flowDiversion;
			}
			throw new RuntimeException(e);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

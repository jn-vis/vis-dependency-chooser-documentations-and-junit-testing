package com.ccp.vis.tests.commons;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.especifications.db.utils.CcpEntity;
import com.ccp.exceptions.process.CcpFlowDisturb;
import com.ccp.jn.sync.service.SyncServiceJnLogin;
import com.jn.commons.entities.JnEntityLoginAnswers;
import com.jn.commons.entities.JnEntityLoginEmail;
import com.jn.commons.entities.JnEntityLoginPassword;
import com.jn.commons.entities.JnEntityLoginToken;

public enum LoginActions implements Function<CcpJsonRepresentation, CcpJsonRepresentation> {
	saveAnswers,
	executeLogin,
	savePassword,
	executeLogout,
	createLoginToken,
	createLoginEmail,
	readTokenFromReceivedEmail{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			//FIXME
			return json;
		}
		
	},
	deletePassword{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation event = JnEntityLoginPassword.ENTITY.delete(json);
			return event;
		}
	},
	deleteAsnswers{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation event = JnEntityLoginAnswers.ENTITY.delete(json);
			return event;
		}
	},
	lockPassword{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpEntity twinEntity = JnEntityLoginPassword.ENTITY.getTwinEntity();
			CcpJsonRepresentation event = twinEntity.createOrUpdate(json);
			return event;
		}
	},
	lockToken{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpEntity twinEntity = JnEntityLoginToken.ENTITY.getTwinEntity();
			CcpJsonRepresentation event = twinEntity.createOrUpdate(json);
			return event;
		}
	},
	deleteEmail{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpJsonRepresentation event = JnEntityLoginEmail.ENTITY.delete(json);
			return event;
		}
	},
	unlockToken{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpEntity twinEntity = JnEntityLoginToken.ENTITY.getTwinEntity();
			CcpJsonRepresentation event = twinEntity.delete(json);
			return event;
		}
	},
	unlockPassword{
		public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
			CcpEntity twinEntity = JnEntityLoginPassword.ENTITY.getTwinEntity();
			CcpJsonRepresentation event = twinEntity.delete(json);
			return event;
		}
	},

	;

	public CcpJsonRepresentation apply(CcpJsonRepresentation json) {
		try {
			Class<? extends SyncServiceJnLogin> clazz = SyncServiceJnLogin.INSTANCE.getClass();
			Method declaredMethod = clazz.getDeclaredMethod(this.name(), CcpJsonRepresentation.class);
			Object invoke = declaredMethod.invoke(SyncServiceJnLogin.INSTANCE, json);
			return (CcpJsonRepresentation)invoke;
		}catch(InvocationTargetException e) {
			if(e.getCause() instanceof CcpFlowDisturb flowDiversion) {
				throw flowDiversion;
			}
			throw new RuntimeException(e);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

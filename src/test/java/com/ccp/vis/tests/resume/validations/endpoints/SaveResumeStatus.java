package com.ccp.vis.tests.resume.validations.endpoints;

import com.ccp.process.CcpProcessStatus;

public enum SaveResumeStatus  implements CcpProcessStatus{
	naoCadastrouMensageria,
	naoEnviouEmail
	;
	public int asNumber() {
		return 0;
	}
}

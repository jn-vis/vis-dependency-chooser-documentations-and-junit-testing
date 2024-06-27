package com.ccp.vis.tests.position;

import org.junit.Test;

import com.ccp.decorators.CcpFileDecorator;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.jn.vis.sync.service.SyncServiceVisPosition;
import com.ccp.validation.CcpJsonInvalid;
import com.ccp.vis.tests.commons.BaseTest;

public class SavePosition extends BaseTest {

	@Test
	public void salvarCurriculo() {
		CcpStringDecorator ccpStringDecorator = new CcpStringDecorator("documentation/tests/vagaParaSalvar.json");
		CcpFileDecorator file = ccpStringDecorator
				.file();
		CcpJsonRepresentation position = file.asSingleJson();
		try {
			SyncServiceVisPosition.INSTANCE.save(position);
		} catch (CcpJsonInvalid e) {
			super.saveErrors(file, e);
		}
	}

}

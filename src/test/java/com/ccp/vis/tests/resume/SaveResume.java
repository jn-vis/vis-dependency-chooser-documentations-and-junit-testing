package com.ccp.vis.tests.resume;

import org.junit.Test;

import com.ccp.decorators.CcpFileDecorator;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.jn.vis.sync.service.SyncServiceVisResume;
import com.ccp.validation.CcpJsonInvalid;
import com.ccp.vis.tests.commons.BaseTest;

public class SaveResume extends BaseTest {

	@Test
	public void salvarCurriculo() {
		CcpStringDecorator ccpStringDecorator = new CcpStringDecorator("documentation/tests/curriculoParaSalvar.json");
		CcpFileDecorator file = ccpStringDecorator
				.file();
		CcpJsonRepresentation resume = file.asSingleJson();
		try {
			SyncServiceVisResume.INSTANCE.save(resume);
		} catch (CcpJsonInvalid e) {
			super.saveErrors(file, e);
		}
	}
}

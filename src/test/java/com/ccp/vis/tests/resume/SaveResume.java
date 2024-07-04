package com.ccp.vis.tests.resume;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import com.ccp.decorators.CcpFileDecorator;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.jn.vis.sync.service.SyncServiceVisResume;
import com.ccp.validation.CcpJsonInvalid;
import com.ccp.vis.async.business.resume.VisAsyncBusinessResumeSave;
import com.ccp.vis.tests.commons.BaseTest;

public class SaveResume extends BaseTest {

	@Test
	public void salvarCurriculo() {
		CcpStringDecorator ccpStringDecorator = new CcpStringDecorator("documentation/tests/curriculoParaSalvar.json");
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation resume = file.asSingleJson();
		VisAsyncBusinessResumeSave.INSTANCE.apply(resume);
		try {
			SyncServiceVisResume.INSTANCE.save(resume);
		} catch (CcpJsonInvalid e) {
			super.saveErrors(file, e);
		}
	}
	
	@Test
	public void faltandoCampoDisponibilidadeEdeficiencia() {
		CcpStringDecorator ccpStringDecorator = new CcpStringDecorator("documentation/tests/resume/faltandoCampoDisponibilidadeEdeficiencia.json");
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation resume = file.asSingleJson();
		try {
			SyncServiceVisResume.INSTANCE.save(resume);
		} catch (CcpJsonInvalid e) {
			CcpJsonRepresentation errorAsJson = e.getErrorAsJson();
			ArrayList<Map<String, Object>> valueFromPath = errorAsJson
					.getValueFromPath(new ArrayList<>(), "errors", "SimpleObject.requiredFields", "wrongFields");
			Map<String, Object> primeiroErro = valueFromPath.get(0);
			CcpJsonRepresentation json = new CcpJsonRepresentation(primeiroErro);
			String faltandoDisponibility = json.getAsString("name");
			Map<String, Object> segundoErro = valueFromPath.get(1);
			CcpJsonRepresentation jsonDois = new CcpJsonRepresentation(segundoErro);
			String faltandoDisabilities = jsonDois.getAsString("name");

			assertTrue("disabilities".equals(faltandoDisabilities));
			assertTrue("disponibility".equals(faltandoDisponibility));

		}		
	}
	
	@Test
	public void teste() {
		CcpStringDecorator ccpStringDecorator = new CcpStringDecorator("documentation/tests/resume/curriculoParaSalvar.json");
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation resume = file.asSingleJson();
		VisAsyncBusinessResumeSave.INSTANCE.apply(resume);
		
	}
}

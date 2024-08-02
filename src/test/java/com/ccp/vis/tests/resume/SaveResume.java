package com.ccp.vis.tests.resume;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import com.ccp.constantes.CcpConstants;
import com.ccp.decorators.CcpFileDecorator;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.especifications.db.query.CcpDbQueryOptions;
import com.ccp.especifications.db.query.CcpQueryExecutor;
import com.ccp.especifications.http.CcpHttpHandler;
import com.ccp.especifications.http.CcpHttpResponse;
import com.ccp.jn.vis.sync.service.SyncServiceVisResume;
import com.ccp.validation.CcpJsonInvalid;
import com.ccp.vis.async.business.resume.VisAsyncBusinessResumeSave;
import com.ccp.vis.tests.commons.BaseTest;
import com.ccp.vis.tests.commons.resumes.ImportResumeFromOldJobsNow;
import com.vis.commons.entities.VisEntityResume;

public class SaveResume extends BaseTest {
	
	@Test
	public void importarCurriculosDoJobsNowAntigo() {
		CcpQueryExecutor queryExecutor = CcpDependencyInjection.getDependency(CcpQueryExecutor.class);
		CcpDbQueryOptions query = 
				CcpDbQueryOptions.INSTANCE
					.matchAll()
				;
		String[] resourcesNames = new String[] {"profissionais2"};
		queryExecutor.consumeQueryResult(
				query, 
				resourcesNames, 
				"10m", 
				100, 
				ImportResumeFromOldJobsNow.INSTANCE
				);
	}
	
	@Test
	public void excluirCurriculoSalvo() {
		CcpJsonRepresentation resume = CcpConstants.EMPTY_JSON.put(VisEntityResume.Fields.email.name(), "-79081bc8055a58031ea2e22346151515c8899848");
		SyncServiceVisResume.INSTANCE.delete(resume);
	}
	
	@Test
	public void salvarCurriculo() {
		CcpHttpHandler http = new CcpHttpHandler(200, CcpConstants.DO_NOTHING);
		String path = "http://localhost:9200/profissionais2/_doc/onias85@gmail.com/_source";
		String asUgglyJson = "";

		CcpHttpResponse response = http.ccpHttp.executeHttpRequest(path, "GET", CcpConstants.EMPTY_JSON, asUgglyJson);
			
		CcpJsonRepresentation asSingleJson = response.asSingleJson();
		
		CcpStringDecorator ccpStringDecorator =
				new CcpStringDecorator("documentation/tests/resume/"
						+ "curriculoParaSalvar.json");
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation curriculo = asSingleJson.getInnerJson("curriculo");
		String conteudo = curriculo.getAsString("conteudo");
		CcpJsonRepresentation resume = file.asSingleJson().put("resumeBase64", conteudo);
		
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
	
	@Test
	public void dddIncorreto() {
		CcpStringDecorator ccpStringDecorator = new CcpStringDecorator("documentation/tests/resume/dddIncorreto.json");
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation resume = file.asSingleJson();
		try {
			SyncServiceVisResume.INSTANCE.save(resume);
		} catch (CcpJsonInvalid e) {
			super.saveErrors(file, e);
		}
	}
	
	
	@Test
	public void faltandoCampoDisponibilidade() {
		CcpStringDecorator ccpStringDecorator = new CcpStringDecorator("documentation/tests/resume/faltandoCampoDisponibilidade.json");
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation resume = file.asSingleJson();
		try {
			SyncServiceVisResume.INSTANCE.save(resume);
		} catch (CcpJsonInvalid e) {
			CcpJsonRepresentation errorAsJson = e.getErrorAsJson();
			ArrayList<Map<String, Object>> valueFromPath = errorAsJson
					.getValueFromPath(new ArrayList<>(), "errors", "SimpleObject.requiredFields", "wrongFields");
			Map<String, Object> disponibilidadeErro = valueFromPath.get(0);
			CcpJsonRepresentation json = new CcpJsonRepresentation(disponibilidadeErro);
			String faltandoDisponibility = json.getAsString("name");
			
			assertTrue("disponibility".equals(faltandoDisponibility));
		}
	}
	
	@Test
	public void faltandoCampoDeficiencia() {
		CcpStringDecorator ccpStringDecorator = new CcpStringDecorator("documentation/tests/resume/faltandoCampoDeficiencia.json");
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation resume = file.asSingleJson();
		try {
			SyncServiceVisResume.INSTANCE.save(resume);
		} catch (CcpJsonInvalid e) {
			CcpJsonRepresentation errorAsJson = e.getErrorAsJson();
			ArrayList<Map<String, Object>> valueFromPath = errorAsJson
					.getValueFromPath(new ArrayList<>(), "errors", "SimpleObject.requiredFields", "wrongFields");
			Map<String, Object> deficienciaErro = valueFromPath.get(0);
			CcpJsonRepresentation json = new CcpJsonRepresentation(deficienciaErro);
			String faltandoDisabilities = json.getAsString("name");
			
			assertTrue("disabilities".equals(faltandoDisabilities));
		}
	}

	@Test
	public void faltandoCampoDdd() {
		CcpStringDecorator ccpStringDecorator = new CcpStringDecorator("documentation/tests/resume/faltandoCampoDdd.json");
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation resume = file.asSingleJson();
		try {
			SyncServiceVisResume.INSTANCE.save(resume);
		} catch (CcpJsonInvalid e) {
			CcpJsonRepresentation errorAsJson = e.getErrorAsJson();
			ArrayList<Map<String, Object>> valueFromPath = errorAsJson
					.getValueFromPath(new ArrayList<>(), "errors", "SimpleObject.requiredFields", "wrongFields");
			Map<String, Object> dddErro = valueFromPath.get(0);
			CcpJsonRepresentation json = new CcpJsonRepresentation(dddErro);
			String faltandoDdd = json.getAsString("name");
			
			assertTrue("ddd".equals(faltandoDdd));
		}
	}
	
	@Test
	public void faltandoCampoResumeMeiaQuatro () {
		CcpStringDecorator ccpStringDecorator = new CcpStringDecorator("documentation/tests/resume/faltandoCampoResumeMeiaQuatro.json");
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation resume = file.asSingleJson();
		try {
			SyncServiceVisResume.INSTANCE.save(resume);
		} catch (CcpJsonInvalid e) {
			CcpJsonRepresentation errorAsJson = e.getErrorAsJson();
			ArrayList<Map<String, Object>> valueFromPath = errorAsJson
					.getValueFromPath(new ArrayList<>(), "errors", "SimpleText.requiredFields", "wrongFields");
			Map<String, Object> baseMeiaQuatroErro = valueFromPath.get(0);
			CcpJsonRepresentation json = new CcpJsonRepresentation(baseMeiaQuatroErro);
			String faltandoResumeMeiaQuatro = json.getAsString("name");
			
			assertTrue("Resume64".equals(faltandoResumeMeiaQuatro));
		}
	}
	
	@Test
	public void faltandoCampoResumeText() {
		CcpStringDecorator ccpStringDecorator = new CcpStringDecorator("documentation/tests/resume/faltandoCampoResumeText.json");
		CcpFileDecorator file = ccpStringDecorator.file();
		CcpJsonRepresentation resume = file.asSingleJson();
		try {
			SyncServiceVisResume.INSTANCE.save(resume);
		} catch (CcpJsonInvalid e) {
			CcpJsonRepresentation erroAsJson = e.getErrorAsJson();
			ArrayList<Map<String, Object>> valueFromPath = erroAsJson
					.getValueFromPath(new ArrayList<>(), "errors", "SimpleText.requiredFields", "wrongFields");
			Map<String, Object> resumeTextErro = valueFromPath.get(0);
			CcpJsonRepresentation json = new CcpJsonRepresentation(resumeTextErro);
			String faltandoResumeText = json.getAsString("name");
			
			assertTrue("ResumeText".equals(faltandoResumeText));
		}
	}

}

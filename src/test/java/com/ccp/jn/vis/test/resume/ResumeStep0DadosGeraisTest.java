package com.ccp.jn.vis.test.resume;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ccp.constantes.CcpConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.implementations.db.dao.elasticsearch.CcpElasticSearchDao;
import com.ccp.implementations.db.setup.elasticsearch.CcpElasticSearchDbSetup;
import com.ccp.implementations.db.utils.elasticsearch.CcpElasticSearchDbRequest;
import com.ccp.implementations.http.apache.mime.CcpApacheMimeHttp;
import com.ccp.implementations.json.gson.CcpGsonJsonHandler;

public class ResumeStep0DadosGeraisTest {		//extends TemplateDeTestes{
	/*
	 * onlyHomeOffice: boolean
	 * ddds: int[]
	 * pcd: boolean
	 * disability: keyWord[]
	 * companiesNotAllowed: text[]
	 * disponibility: int
	 * observations: text
	 * 
	 * 
	 */

	// resolvendo o extends TemplateDeTestes
	{
		CcpDependencyInjection.loadAllDependencies(new CcpGsonJsonHandler(), new CcpElasticSearchDao(),
				new CcpElasticSearchDbRequest(), new CcpApacheMimeHttp(), new CcpElasticSearchDbSetup());		
	}
	
	@Test
	public void testarFaltandoCampoOnlyHomeOffice() {

		Object valorRecebidoParaOCampo = true;
		boolean onlyHomeOffice = false;
		Integer ddds = null;
		boolean pcd = false;
		String disability = null;
		String companiesNotAllowed = null;
		Integer disponibility = null;
		String observations = null;
		
		String nomeDoCampo = "onlyHomeOffice";
		
		if (nomeDoCampo.equals("onlyHomeOffice") && (valorRecebidoParaOCampo instanceof Boolean)) {
			onlyHomeOffice = (boolean) valorRecebidoParaOCampo;
		}
		
		if (nomeDoCampo.equals("ddds") && (valorRecebidoParaOCampo instanceof Integer)) {
			ddds = (int) valorRecebidoParaOCampo;
		}
		
		if (nomeDoCampo.equals("pcd") && (valorRecebidoParaOCampo instanceof Boolean)) {
			pcd = (boolean) valorRecebidoParaOCampo;
		}
		
		if (nomeDoCampo.equals("disability") && (valorRecebidoParaOCampo instanceof String)) {
			disability = (String) valorRecebidoParaOCampo;
		}
		
		if (nomeDoCampo.equals("companiesNotAllowed") && (valorRecebidoParaOCampo instanceof String)) {
			companiesNotAllowed = (String) valorRecebidoParaOCampo;
		}
		
		if (nomeDoCampo.equals("disponibility") && (valorRecebidoParaOCampo instanceof Integer)) {
			disponibility = (int) valorRecebidoParaOCampo;
		}
		
		if (nomeDoCampo.equals("observations") && (valorRecebidoParaOCampo instanceof String)) {
			observations = (String) valorRecebidoParaOCampo;
		}
		
		
		CcpJsonRepresentation json = CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", onlyHomeOffice)
				.put("ddds", ddds)
				.put("pcd", pcd)
				.put("disability", disability)
				.put("companiesNotAllowed", companiesNotAllowed)
				.put("disponibility", disponibility)
				.put("observations", observations); 
//		assertTrue(json.containsKey("onlyHomeOffice") == false);
//		assertTrue(json.containsKey("disponibility") == false);
		
//		System.out.println(onlyHomeOffice && "-" &&
//				.append(ddds);// && "" &&
//				pcd && "-" &&
//				disability && "-" &&
//				companiesNotAllowed && "-" &&
//				disponibility && "-" &&
//				observations);
		
	}

	protected String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

}

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
	 1) Testes genericos:
	 * A) Teste de presença de todos os fields
	 * B) Teste de tipagem de todos os fields
	 * C) Teste do Lenght de todos os campos keyword e text
	 * D) Teste de não duplicatas dos arrays
	 2) Testes específicos:
	 * E) Teste de domínio dos DDDs e deficiencias
	 * F) Teste de não preenchimento das deficiencias se PCD for false
	 */

	// resolvendo o extends TemplateDeTestes
	{
		CcpDependencyInjection.loadAllDependencies(new CcpGsonJsonHandler(), new CcpElasticSearchDao(),
				new CcpElasticSearchDbRequest(), new CcpApacheMimeHttp(), new CcpElasticSearchDbSetup());		
	}
	
	@Test
	public void testeDois () {
		
//		CcpJsonRepresentation jsonCompleto = CcpConstants.EMPTY_JSON
//				.put("onlyHomeOffice", "Onias")
//				.put("pcd", 1)
//				.addToList("ddds", 11).addToList("ddds", 55).addToList("ddds", "x")
//				;
		
		CcpJsonRepresentation jsonCompleto = new CcpJsonRepresentation("{"
				+ "'onlyHomeOffice': 'Onias', 'pcd': 1, 'ddds': [11, 55] }");
		
		boolean testeDoOnlyHomeOffice = jsonCompleto.getAsMetadata("onlyHomeOffice").isBoolean() == false;
		assertTrue(testeDoOnlyHomeOffice);
		boolean testeDoPcd = jsonCompleto.getAsMetadata("pcd").isBoolean() == false;
		assertTrue(testeDoPcd);
		boolean testeDosDdds = jsonCompleto.getAsArrayMetadata("ddds").isLongNumberList();
		assertTrue(testeDosDdds);
		
		
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
		
		if ("onlyHomeOffice".equals(nomeDoCampo) && (valorRecebidoParaOCampo instanceof Boolean)) {
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

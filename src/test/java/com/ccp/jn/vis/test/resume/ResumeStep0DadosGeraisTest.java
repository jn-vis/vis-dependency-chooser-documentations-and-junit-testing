package com.ccp.jn.vis.test.resume;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

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
	 * disabilities: keyWord[]
	 * companiesNotAllowed: text[]
	 * disponibility: int
	 * observations: text
	 1) Testes genericos:
	 * A) Teste de presença de todos os fields(json, fields)
	 * B) Teste de tipagem de todos os fields(json, key, type)
	 * C) Teste do Lenght de todos os campos keyword e text (json, maxLenght, fields), (json, minLenght, fields), (json, maxLenght, fields), (json, minLenght, fields), , (json, lenght, fields)
	 * D) Teste de não duplicatas dos arrays (json, fields)
	 2) Testes específicos:
	 * E) Teste de domínio dos DDDs e deficiencias (json, domain[], fields), (json, domain[], fields),
	 * F) Teste de não preenchimento das deficiencias se PCD for false
	 */

	// resolvendo o extends TemplateDeTestes
	{
		CcpDependencyInjection.loadAllDependencies(new CcpGsonJsonHandler(), new CcpElasticSearchDao(),
				new CcpElasticSearchDbRequest(), new CcpApacheMimeHttp(), new CcpElasticSearchDbSetup());		
	}

	@Test
	public void testFieldsPresent() {
		CcpJsonRepresentation json =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)/// TODO JASSON USE SUA CRIATIVIDADE PARA TESTAR PARA CRIAR O JSON À SUA MANEIRA
				;
		
		
		boolean x = json.containsAllKeys("onlyHomeOffice", "ddds", "pcd", "disabilities", "companiesNotAllowed",
				"disponibility", "observations");
		assertTrue(x);
	}

	@Test
	public void testFieldsNonDuplicatedItems() {
		CcpJsonRepresentation json =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)/// TODO JASSON USE SUA CRIATIVIDADE PARA TESTAR PARA CRIAR O JSON À SUA MANEIRA
				;
		
		
		boolean x = json.validateTheFollowingFields("ddds", "disabilities", "companiesNotAllowed").asArrays().withHasNotDuplicatedItems();
		assertTrue(x);

	}

	@Test
	public void testFieldsRestrictedNumbers() {
		CcpJsonRepresentation json =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)/// TODO JASSON USE SUA CRIATIVIDADE PARA TESTAR PARA CRIAR O JSON À SUA MANEIRA
				;
		
		
		Collection<Double> allowed = Arrays.asList(11d, 55d);
		boolean x = json.validateTheFollowingFields("ddds").withAllowed().numbers().inArrayFields(allowed);
		assertTrue(x);

	}
	
	@Test
	public void testFieldsRestrictedStrings() {
		CcpJsonRepresentation json =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)/// TODO JASSON USE SUA CRIATIVIDADE PARA TESTAR PARA CRIAR O JSON À SUA MANEIRA
				;
		
		
		Collection<String> allowed = Arrays.asList("visual", "auditivo", "motora");
		
		boolean x = json.validateTheFollowingFields("disabilities").withAllowed().strings().inArrayFields(allowed);
		assertTrue(x);
	}

	@Test
	public void testFieldsTypes() {
		CcpJsonRepresentation json =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)/// TODO JASSON USE SUA CRIATIVIDADE PARA TESTAR PARA CRIAR O JSON À SUA MANEIRA
				;
		
		boolean x = json.validateTheFollowingFields("companiesNotAllowed", "disabilities", "ddds").thatTheTypes().areList();
		boolean y = json.validateTheFollowingFields("pcd", "onlyHomeOffice").thatTheTypes().areBoolean();
		boolean z = json.validateTheFollowingFields("disponibility").thatTheTypes().areLong();
		assertTrue(z);
		assertTrue(y);
		assertTrue(x);
	}

	@Test
	public void testMaxLenght() {
		CcpJsonRepresentation json = CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)/// TODO JASSON USE SUA CRIATIVIDADE PARA TESTAR PARA CRIAR O JSON À SUA MANEIRA
				;
		boolean x = json.validateTheFollowingFields("observations").withRange().fromObjects().asStringWithLenght().isLessOrEqualsTo(500);
		assertTrue(x);
		
	}
	
}

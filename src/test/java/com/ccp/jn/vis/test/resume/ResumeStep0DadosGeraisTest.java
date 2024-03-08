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
				.put("onlyHomeOffice", true)
				;
		
		
		boolean x = json.containsAllKeys("onlyHomeOffice", "ddds", "pcd", "disabilities", "companiesNotAllowed",
				"disponibility", "observations");
		assertTrue(x);
	}

	@Test
	public void testFieldsNonDuplicatedItems() {
		CcpJsonRepresentation giveOneJson =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)
				;
		
		boolean x = giveOneJson.itIsTrueThatTheFollowingFields("ddds", "disabilities", "companiesNotAllowed").ifTheyAreAllArrayValuesThenEachOne().hasNonDuplicatedItems();
		assertTrue(x);
	}

	@Test
	public void testAllowedValues() {
		CcpJsonRepresentation giveOneJson =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)
				;
		boolean x = giveOneJson.itIsTrueThatTheFollowingFields("ddds").ifTheyAreAllArrayValuesThenEachOne().isNumberAndItIsContainedAtTheList(11d, 55d);
		assertTrue(x);
		boolean y = giveOneJson.itIsTrueThatTheFollowingFields("disabilities").ifTheyAreAllArrayValuesThenEachOne().isTextAndItIsContainedAtTheList("visual", "auditivo", "motora");
		assertTrue(y);
	}

	@Test
	public void testFieldsTypes() {
		CcpJsonRepresentation giveOneJson =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)
				;
		boolean x = giveOneJson.itIsTrueThatTheFollowingFields("companiesNotAllowed", "disabilities", "ddds").areAllOfTheType().list();
		assertTrue(x);
		boolean y = giveOneJson.itIsTrueThatTheFollowingFields("pcd", "onlyHomeOffice").areAllOfTheType().bool();
		assertTrue(y);
		boolean z = giveOneJson.itIsTrueThatTheFollowingFields("disponibility").areAllOfTheType().longNumber();
		assertTrue(z);
	}

	@Test
	public void testMaxLenght() {
		CcpJsonRepresentation giveOneJson = CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)
				;
		boolean x = giveOneJson.itIsTrueThatTheFollowingFields("observations").ifTheyAreAll().textsThenEachOneHasTheSizeThatIs().equalsOrLessThan(500d);
		assertTrue(x);
	}
}

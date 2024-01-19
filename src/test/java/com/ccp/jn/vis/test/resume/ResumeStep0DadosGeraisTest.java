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
				.put("onlyHomeOffice", true)// TODO JASSON, FAÇA TESTES DE PREENCHIMENTOS VARIADOS DO JSON
				;
		
		
		boolean x = json.containsAllKeys("onlyHomeOffice", "ddds", "pcd", "disabilities", "companiesNotAllowed",
				"disponibility", "observations");
		assertTrue(x);
	}

	@Test
	public void testFieldsNonDuplicatedItems() {
		CcpJsonRepresentation giveOneJson =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)// TODO JASSON, FAÇA TESTES DE PREENCHIMENTOS VARIADOS DO JSON
				;
		
		boolean x = giveOneJson.itIsTruthThatTheFollowingFields("ddds", "disabilities", "companiesNotAllowed").ifTheyAreArrayValuesSoEachOne().hasNonDuplicatedItems();
		assertTrue(x);
	}

	@Test
	public void testAllowedValues() {
		CcpJsonRepresentation giveOneJson =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)// TODO JASSON, FAÇA TESTES DE PREENCHIMENTOS VARIADOS DO JSON
				;
		boolean x = giveOneJson.itIsTruthThatTheFollowingFields("ddds").ifTheyAreArrayValuesSoEachOne().isNumberAndItIsContainedAtTheList(11d, 55d);
		assertTrue(x);
		boolean y = giveOneJson.itIsTruthThatTheFollowingFields("disabilities").ifTheyAreArrayValuesSoEachOne().isTextAndItIsContainedAtTheList("visual", "auditivo", "motora");
		assertTrue(y);
	}

	@Test
	public void testFieldsTypes() {
		CcpJsonRepresentation giveOneJson =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)// TODO JASSON, FAÇA TESTES DE PREENCHIMENTOS VARIADOS DO JSON
				;
		boolean x = giveOneJson.itIsTruthThatTheFollowingFields("companiesNotAllowed", "disabilities", "ddds").areOfTheType().list();
		assertTrue(x);
		boolean y = giveOneJson.itIsTruthThatTheFollowingFields("pcd", "onlyHomeOffice").areOfTheType().bool();
		assertTrue(y);
		boolean z = giveOneJson.itIsTruthThatTheFollowingFields("disponibility").areOfTheType().longNumber();
		assertTrue(z);
	}

	@Test
	public void testMaxLenght() {
		CcpJsonRepresentation giveOneJson = CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)// TODO JASSON, FAÇA TESTES DE PREENCHIMENTOS VARIADOS DO JSON
				;
		boolean x = giveOneJson.itIsTruthThatTheFollowingFields("observations").ifTheyAre().textsSoEachOneHasTheSizeThatIs().equalsOrLessThan(500);
		assertTrue(x);
	}
	
	public static void main(String[] args) {
		CcpJsonRepresentation giveOneJson = CcpConstants.EMPTY_JSON;
		
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").areOfTheType().bool());
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").areOfTheType().doubleNumber());
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").areOfTheType().json());
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").areOfTheType().list());
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").areOfTheType().longNumber());
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().hasNonDuplicatedItems());
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().hasTheSizeThatIs().equalsTo(0));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().hasTheSizeThatIs().equalsOrGreaterThan(1));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().hasTheSizeThatIs().equalsOrLessThan(2));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().hasTheSizeThatIs().greaterThan(3));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().hasTheSizeThatIs().lessThan(4));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isOfTheType().bool());
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isOfTheType().doubleNumber());
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isOfTheType().json());
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isOfTheType().list());
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isOfTheType().longNumber());
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isTextAndItIsContainedAtTheList("item1", "item2"));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isTextAnd().hasTheSizeThatIs().equalsTo(0));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isTextAnd().hasTheSizeThatIs().equalsOrGreaterThan(1));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isTextAnd().hasTheSizeThatIs().equalsOrLessThan(2));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isTextAnd().hasTheSizeThatIs().greaterThan(3));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isTextAnd().hasTheSizeThatIs().lessThan(4));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isNumberAndItIsContainedAtTheList(1,2));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isNumberAndItIs().equalsTo(0));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isNumberAndItIs().equalsOrGreaterThan(1));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isNumberAndItIs().equalsOrLessThan(2));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isNumberAndItIs().greaterThan(3));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesSoEachOne().isNumberAndItIs().lessThan(4));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().textsSoEachOneIsContainedAtTheList("item1", "item2"));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().textsSoEachOneHasTheSizeThatIs().equalsTo(0));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().textsSoEachOneHasTheSizeThatIs().equalsOrGreaterThan(1));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().textsSoEachOneHasTheSizeThatIs().equalsOrLessThan(2));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().textsSoEachOneHasTheSizeThatIs().greaterThan(3));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().textsSoEachOneHasTheSizeThatIs().lessThan(4));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().numbersSoEachOneIsContainedAtTheList(1, 2));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().numbersSoEachOneIs().equalsTo(0));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().numbersSoEachOneIs().equalsOrGreaterThan(1));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().numbersSoEachOneIs().equalsOrLessThan(2));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().numbersSoEachOneIs().greaterThan(3));
		assertTrue(giveOneJson.itIsTruthThatTheFollowingFields("field1", "field2").ifTheyAre().numbersSoEachOneIs().lessThan(4));

	}
}

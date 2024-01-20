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
		
		boolean x = giveOneJson.itIsTrueThatTheFollowingFields("ddds", "disabilities", "companiesNotAllowed").ifTheyAreArrayValuesThenEachOne().hasNonDuplicatedItems();
		assertTrue(x);
	}

	@Test
	public void testAllowedValues() {
		CcpJsonRepresentation giveOneJson =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)// TODO JASSON, FAÇA TESTES DE PREENCHIMENTOS VARIADOS DO JSON
				;
		boolean x = giveOneJson.itIsTrueThatTheFollowingFields("ddds").ifTheyAreArrayValuesThenEachOne().isNumberAndItIsContainedAtTheList(11d, 55d);
		assertTrue(x);
		boolean y = giveOneJson.itIsTrueThatTheFollowingFields("disabilities").ifTheyAreArrayValuesThenEachOne().isTextAndItIsContainedAtTheList("visual", "auditivo", "motora");
		assertTrue(y);
	}

	@Test
	public void testFieldsTypes() {
		CcpJsonRepresentation giveOneJson =  CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)// TODO JASSON, FAÇA TESTES DE PREENCHIMENTOS VARIADOS DO JSON
				;
		boolean x = giveOneJson.itIsTrueThatTheFollowingFields("companiesNotAllowed", "disabilities", "ddds").areOfTheType().list();
		assertTrue(x);
		boolean y = giveOneJson.itIsTrueThatTheFollowingFields("pcd", "onlyHomeOffice").areOfTheType().bool();
		assertTrue(y);
		boolean z = giveOneJson.itIsTrueThatTheFollowingFields("disponibility").areOfTheType().longNumber();
		assertTrue(z);
	}

	@Test
	public void testMaxLenght() {
		CcpJsonRepresentation giveOneJson = CcpConstants.EMPTY_JSON
				.put("onlyHomeOffice", true)// TODO JASSON, FAÇA TESTES DE PREENCHIMENTOS VARIADOS DO JSON
				;
		boolean x = giveOneJson.itIsTrueThatTheFollowingFields("observations").ifTheyAre().textsThenEachOneHasTheSizeThatIs().equalsOrLessThan(500);
		assertTrue(x);
	}
	
	public static void main(String[] args) {
		CcpJsonRepresentation givenOneJson = CcpConstants.EMPTY_JSON;
		
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").areOfTheType().bool());
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").areOfTheType().doubleNumber());
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").areOfTheType().json());
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").areOfTheType().list());
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").areOfTheType().longNumber());
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().hasNonDuplicatedItems());
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().hasTheSizeThatIs().equalsTo(0));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().hasTheSizeThatIs().equalsOrGreaterThan(1));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().hasTheSizeThatIs().equalsOrLessThan(2));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().hasTheSizeThatIs().greaterThan(3));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().hasTheSizeThatIs().lessThan(4));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isOfTheType().bool());
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isOfTheType().doubleNumber());
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isOfTheType().json());
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isOfTheType().list());
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isOfTheType().longNumber());
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isTextAndItIsContainedAtTheList("item1", "item2"));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isTextAnd().hasTheSizeThatIs().equalsTo(0));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isTextAnd().hasTheSizeThatIs().equalsOrGreaterThan(1));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isTextAnd().hasTheSizeThatIs().equalsOrLessThan(2));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isTextAnd().hasTheSizeThatIs().greaterThan(3));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isTextAnd().hasTheSizeThatIs().lessThan(4));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isNumberAndItIsContainedAtTheList(1,2));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isNumberAndItIs().equalsTo(0));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isNumberAndItIs().equalsOrGreaterThan(1));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isNumberAndItIs().equalsOrLessThan(2));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isNumberAndItIs().greaterThan(3));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAreArrayValuesThenEachOne().isNumberAndItIs().lessThan(4));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().textsThenEachOneIsContainedAtTheList("item1", "item2"));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().textsThenEachOneHasTheSizeThatIs().equalsTo(0));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().textsThenEachOneHasTheSizeThatIs().equalsOrGreaterThan(1));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().textsThenEachOneHasTheSizeThatIs().equalsOrLessThan(2));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().textsThenEachOneHasTheSizeThatIs().greaterThan(3));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().textsThenEachOneHasTheSizeThatIs().lessThan(4));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().numbersThenEachOneIsContainedAtTheList(1, 2));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().numbersThenEachOneIs().equalsTo(0));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().numbersThenEachOneIs().equalsOrGreaterThan(1));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().numbersThenEachOneIs().equalsOrLessThan(2));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().numbersThenEachOneIs().greaterThan(3));
		assertTrue(givenOneJson.itIsTrueThatTheFollowingFields("field1", "field2").ifTheyAre().numbersThenEachOneIs().lessThan(4));

	}
}

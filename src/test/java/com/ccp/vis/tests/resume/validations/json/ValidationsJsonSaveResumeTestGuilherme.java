package com.ccp.vis.tests.resume.validations.json;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.especifications.db.utils.CcpEntityCrudOperationType;
import com.ccp.validation.CcpJsonInvalid;
import com.ccp.validation.annotations.CcpJsonFieldsValidation;
import com.ccp.vis.tests.commons.BaseTest;
import com.jn.mensageria.JnMensageriaSender;
import com.vis.commons.entities.VisEntityResume;
import com.vis.commons.json.validations.VisJsonValidationResume;

//@FixMethodOrder(MethodSorters.JVM)
public class ValidationsJsonSaveResumeTestGuilherme extends BaseTest {

    CcpJsonFieldsValidation annotation = VisJsonValidationResume.class.getAnnotation(CcpJsonFieldsValidation.class);

    /*

    //erro
	@Test
	public void testSimpleObject() {
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "companiesNotAllowed");
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "ddd");
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "desiredJob");
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "disponibility");
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "disabilities");
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "experience");
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "lastJob");
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "name");
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "fileName");
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "observations");
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "resumeBase64");
		this.validateSimpleObjectValidationsRequiredFields("SimpleObject.requiredFields", "email");
		
		//erro: java.lang.AssertionError
		this.validateSimpleObjectValidationsRequiredAtLeastOne("SimpleObject.requiredFields", VisEntityResume.Fields.clt.name(), "pj");
 		
		//?
		this.validateSimpleObjectValidationsRequiredNonRepeatedLists("SimpleObject.nonRepeatedLists", "ddd");
		//?
		this.validateSimpleObjectValidationsRequiredNonRepeatedLists("SimpleObject.nonRepeatedLists", "disabilities");
		//?
		this.validateSimpleObjectValidationsRequiredNonRepeatedLists("SimpleObject.nonRepeatedLists", "companiesNotAllowed");
		
		this.validateSimpleObjectValidationsRequiredIntegerFields("SimpleObject.integerFields", "disponibility", "AAA");
		
		//erro: java.lang.RuntimeException: The value 'AAA' from the field 'experience' is not a double
		this.validateSimpleObjectValidationsRequiredDoubleFields("SimpleObject.doubleFields", "experience", "AAA");
		
		
	} 

	private void validateSimpleObjectValidationsRequiredFields(String rule, String fieldsTest) {
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[0].getMethodName() +"_"+ fieldsTest
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testSimpleObject"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.removeField(fieldsTest);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> 
			   new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> 
			   itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			boolean contains = names.containsAll(asList);
			if(contains == false) {
				System.out.println();
			}
			assertTrue(contains);
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}
	
	private void validateSimpleObjectValidationsRequiredAtLeastOne(String rule, String fieldsTest, String fieldsTest1) {
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[0].getMethodName() +"_"+ fieldsTest
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testSimpleObject"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.removeField(fieldsTest).removeField(fieldsTest1);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<String> missingFields = e.result.getValueFromPath(new ArrayList<String>(), "errors",rule, "wrongFields");
			assertTrue(missingFields.contains(fieldsTest) && missingFields.contains(fieldsTest1));
			System.out.println("Campo Afetado: " + missingFields+"\n");
			}
		}
	
	private void validateSimpleObjectValidationsRequiredNonRepeatedLists(String rule, String fieldsTest) {
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[0].getMethodName() +"_"+ fieldsTest
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testSimpleObject"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.removeField(fieldsTest);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> 
			   new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> 
			   itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			boolean contains = names.containsAll(asList);
			if(contains == false) {
				System.out.println();
			}
			assertTrue(contains);
			System.out.println("Campo Afetado: " + missingFields+"\n");			
		}
	}
	
	private void validateSimpleObjectValidationsRequiredIntegerFields(String rule, String fieldsTest, String value) {
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[0].getMethodName() +"_"+ fieldsTest
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testSimpleObject"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.put(fieldsTest, value);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			boolean contains = names.containsAll(asList);
			
			System.out.println("Campo Afetado: " + missingFields+"\n");
			
			if(contains == false) {
				System.out.println();
			}
			assertTrue(contains);
	
		}
	}

	private void validateSimpleObjectValidationsRequiredDoubleFields(String rule, String fieldsTest, String value) {
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[0].getMethodName() +"_"+ fieldsTest
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testSimpleObject"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.put(fieldsTest, value);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			boolean contains = names.containsAll(asList);
			
			System.out.println("Campo Afetado: " + missingFields+"\n");
			
			if(contains == false) {
				System.out.println();
			}
			assertTrue(contains);
	
		}
	}
	
	
	@Test
	public void testAllowedValues() {

		this.validateTestObjectTextSizeArrayWithAllowedNumbers("AllowedValues.arrayWithAllowedNumbers", "ddd", "100");
		
		//erro: java.lang.AssertionError
		this.validateTestObjectTextSizeArrayWithAllowedTexts("AllowedValues.arrayWithAllowedNumbers", "disabilities", "cegueira");

	}

	private void validateTestObjectTextSizeArrayWithAllowedNumbers(String rule, String fieldsTest, String fieldValue) {
		System.out.println("Regra:" 	+ annotation.allowedValues()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[0].getMethodName() 
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testAllowedValues"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.put(fieldsTest, fieldValue);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> 
			   new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> 
			   itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			System.out.println("Campo Afetado: " + missingFields+"\n");
			boolean contains = names.containsAll(asList);
			if(contains == false) {
				System.out.println();
			}
			
			assertTrue(contains);
			
		}
	}
	
	private void validateTestObjectTextSizeArrayWithAllowedTexts(String rule, String fieldsTest, String fieldValue) {
		System.out.println("Regra:" 	+ annotation.allowedValues()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[0].getMethodName() 
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testAllowedValues"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.put(fieldsTest, fieldValue);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> 
			   new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> 
			   itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			System.out.println("Campo Afetado: " + missingFields+"\n");
			boolean contains = names.containsAll(asList);
			if(contains == false) {
				System.out.println();
			}
			
			assertTrue(names.contains(fieldsTest));
			
		}
	}
	
	
	@Test
	public void testObjectTextSize() {
 
		this.validateTestObjectTextSize("ObjectTextSize.equalsOrLessThan", 101, VisEntityResume.Fields.desiredJob.name(), VisEntityResume.Fields.lastJob.name());
		this.validateTestObjectTextSize("ObjectTextSize.equalsOrLessThan", 1000, VisEntityResume.Fields.desiredJob.name(), VisEntityResume.Fields.lastJob.name());
		
		this.validateTestObjectTextSize("ObjectTextSize.equalsOrGreaterThan", 511, "resumeBase64");
		this.validateTestObjectTextSize("ObjectTextSize.equalsOrGreaterThan", 411, "resumeBase64");

		this.validateTestObjectTextSize("ObjectTextSize.equalsOrLessThan", 4_200_001, "resumeBase64");
		this.validateTestObjectTextSize("ObjectTextSize.equalsOrLessThan", 5_200_000, "resumeBase64");

		this.validateTestObjectTextSize("ObjectTextSize.equalsOrLessThan", 101, "name");
		this.validateTestObjectTextSize("ObjectTextSize.equalsOrLessThan", 200, "name");

		this.validateTestObjectTextSize("ObjectTextSize.equalsOrGreaterThan", 1, "name");
		this.validateTestObjectTextSize("ObjectTextSize.equalsOrGreaterThan", 2, "name");

		this.validateTestObjectTextSize("ObjectTextSize.equalsOrLessThan", 42, "email");
		this.validateTestObjectTextSize("ObjectTextSize.equalsOrLessThan", 200, "email");
	}

	private void validateTestObjectTextSize(String rule, int size, String... fieldsTest) {
		String fieldValue = "A".repeat(size);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testObjectTextSize"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.put(fieldValue, fieldsTest);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> 
			   new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> 
			   itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			System.out.println("Campo Afetado: " + missingFields+"\n");
			boolean contains = names.containsAll(asList);
			if(contains == false) {
				System.out.println();
			}
			
			assertTrue(contains);
			
		}
	}
	
	//Erro
	@Test
	public void testSimpleArray() {
		//erro:	java.lang.NullPointerException: Cannot invoke "java.util.List.stream()" because the return value of "com.ccp.decorators.CcpJsonRepresentation.getAsObjectList(String)" is null
		this.validateSimpleArrayNotEmptyArray("SimpleArray.notEmptyArray", "ddd", "");
		//erro:java.lang.AssertionError
		this.validateSimpleArrayIntegerItems("SimpleArray.notEmptyArray", "ddd", "teste");	
	}
	
	private void validateSimpleArrayNotEmptyArray(String rule, String fieldsTest, String fieldValue) {
		System.out.println("Regra:" 	+ annotation.objectTextSize()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[0].getMethodName() 
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testSimpleArray"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.put(fieldsTest, fieldValue);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> 
			   new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> 
			   itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			System.out.println("Campo Afetado: " + missingFields+"\n");
			boolean contains = names.containsAll(asList);
			if(contains == false) {
				System.out.println();
			}
			
			assertTrue(contains);
			
		}
	}
	
	private void validateSimpleArrayIntegerItems(String rule, String fieldsTest, String fieldValue) {
		System.out.println("Regra:" 	+ annotation.objectTextSize()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[0].getMethodName() 
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testSimpleArray"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.put(fieldsTest, fieldValue);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> 
			   new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> 
			   itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			System.out.println("Campo Afetado: " + missingFields+"\n");
			boolean contains = names.containsAll(asList);
			if(contains == false) {
				System.out.println();
			}
			
			assertTrue(contains);
			
		}
	}
	
	//Erro
	@Test
	public void testYear() {
 
		this.validateYearEqualsOrLessThan("Year.equalsOrLessThan", "experience", "71");
		
		//erro: java.lang.AssertionError
		this.validateYearEqualsOrGreaterThan("Year.equalsOrGreaterThan", "experience", -10);	
	}
	
	private void validateYearEqualsOrLessThan(String rule, String fieldsTest, String fieldValue) {
		System.out.println("Regra:" 	+ annotation.objectTextSize()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testYear"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.put(fieldsTest, fieldValue);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> 
			   new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> 
			   itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			System.out.println("Campo Afetado: " + missingFields+"\n");
			boolean contains = names.containsAll(asList);
			if(contains == false) {
				System.out.println();
			}			
			assertTrue(contains);
			
		}
	}	
	
	private void validateYearEqualsOrGreaterThan(String rule, String fieldsTest, Integer fieldValue) {
		System.out.println("Regra:" 	+ annotation.objectTextSize()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testYear"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.put(fieldsTest, fieldValue);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> 
			   new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> 
			   itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			System.out.println("Campo Afetado: " + missingFields+"\n");
			boolean contains = names.containsAll(asList);
			if(contains == false) {
				System.out.println();
			}
			assertTrue(names.contains(fieldsTest));
			//assertTrue(contains);
			
		}
	}	
	
	//OK
	@Test
	public void testObjectNumbers() {
 
		this.validateObjectNumbersEqualsOrLessThan("ObjectNumbers.equalsOrLessThan", "btc", "100001");
		this.validateObjectNumbersEqualsOrLessThan("ObjectNumbers.equalsOrLessThan", "clt", "100001");
		this.validateObjectNumbersEqualsOrLessThan("ObjectNumbers.equalsOrLessThan", "pj", "100001");
		
		this.validateObjectNumbersEqualsOrGreaterThan("ObjectNumbers.equalsOrGreaterThan", "btc", "999");
		this.validateObjectNumbersEqualsOrGreaterThan("ObjectNumbers.equalsOrGreaterThan", "clt", "999");
		this.validateObjectNumbersEqualsOrGreaterThan("ObjectNumbers.equalsOrGreaterThan", "pj", "999");
	}
	
	private void validateObjectNumbersEqualsOrLessThan(String rule, String fieldsTest, String fieldValue) {
		System.out.println("Regra:" 	+ annotation.objectTextSize()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testObjectNumbers"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.put(fieldsTest, fieldValue);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> 
			   new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> 
			   itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			System.out.println("Campo Afetado: " + missingFields+"\n");
			boolean contains = names.containsAll(asList);
			if(contains == false) {
				System.out.println();
			}
			
			assertTrue(contains);
			
		}
	}	
	
	private void validateObjectNumbersEqualsOrGreaterThan(String rule, String fieldsTest, String fieldValue) {
		System.out.println("Regra:" 	+ annotation.objectTextSize()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ java.util.Arrays.stream(Thread.currentThread().getStackTrace())
				 		 						   .filter(e -> e.getMethodName().equals("testObjectNumbers"))
				 		 						   .findFirst().map(StackTraceElement::getLineNumber).orElse(-1)
						  );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		CcpJsonRepresentation resume = super.getJson(filePath);
		resume = resume.put(fieldsTest, fieldValue);			
		try {
			CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(resume);
			System.out.println(apply);
		}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors",rule,"wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> 
			   new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> 
			   itemDaLista.getAsString("name")).collect(Collectors.toSet());
			List<String> asList = Arrays.asList(fieldsTest);
			System.out.println("Campo Afetado: " + missingFields+"\n");
			boolean contains = names.containsAll(asList);
			if(contains == false) {
				System.out.println();
			}
			
			assertTrue(contains);
			
		}
	}	

	*/

//------------------------------------------------------------------------------------------------------------------------------	
//------------------------------------------------------------------------------------------------------------------------------	
//------------------------------------------------------------------------------------------------------------------------------	
	
	
//------------------------------------------------------simpleObject-requiredFields---------------------------------------------
	@Test
	public void testRemoveFieldCompaniesNotAllowed() {
		String fieldTest  = "companiesNotAllowed";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
						 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
						 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			 super.saveErrors(filePath, e);
		   //em vez desse:	 
//			 List<Map<String, Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			//usar esse: 
			 List<CcpJsonRepresentation> missingFields = e.result.getInnerJsonListFromPath("errors","SimpleObject.requiredFields","wrongFields");
			 
			 Set<String> names = missingFields.stream()
					 //.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
					 .map(itemDaLista -> 
			    itemDaLista.getAsString("name")).collect(Collectors.toSet());
			 
			 assertTrue(names.contains(fieldTest));
			 
			 System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testRemoveFieldDdd() {
		String fieldTest  = "ddd";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testRemoveFieldDesiredJob() {
		String fieldTest  = "desiredJob";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
						 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
						 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testRemoveFieldDisponibility() {
		String fieldTest  = "disponibility";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testRemoveFieldDisabilities() {
		String fieldTest  = "disabilities";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testRemoveFieldExperience() {
		String fieldTest  = "experience";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testRemoveFieldLastJob() {
		String fieldTest  = "lastJob";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testRemoveFieldName() {
		String fieldTest  = "name";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testRemoveFieldFileName() {
		String fieldTest  = "fileName";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testRemoveFieldObservations() {
		String fieldTest  = "observations";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testRemoveFieldResumeBase64() {
		String fieldTest  = "resumeBase64";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testRemoveFieldEmail() {
		String fieldTest  = "email";
		System.out.println("Regra:" 	+ annotation.simpleObject()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.requiredFields","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

//-------------------------------------------------simpleObject-requiredAtLeastOne----------------------------------------------
	@Test
	public void testRemoveFieldsCltPj() {		
		String fieldTest  = VisEntityResume.Fields.clt.name();
		String fieldTest1  = "pj";
		System.out.println("Regra:" 	+ annotation.simpleObject()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest).removeFields(fieldTest1);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<String> missingFields = e.result.getValueFromPath(new ArrayList<String>(), "errors","SimpleObject.requiredAtLeastOne", "wrongFields");
			assertTrue(missingFields.contains(fieldTest) && missingFields.contains(fieldTest1));
			System.out.println("Campo Afetado: " + missingFields+"\n");
			}
		}

//-------------------------------------------------simpleObject-nonRepeatedLists----------------------------------------------	
	@Test
	public void testDddNonRepeatdLists() {
			String fieldTest  = "ddd";
			Object fieldValue  = Arrays.asList("11", "11");
			System.out.println("Regra:" 	+ annotation.simpleObject()[2].rule()
					 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
					 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
							 );
			String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
			try {
				 CcpJsonRepresentation resume = super.getJson(filePath);
				 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
				 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				 System.out.println(apply);
				}
			catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String, Object>> missingFields = 
				e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.nonRepeatedLists","wrongFields");
				Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				assertTrue(names.contains(fieldTest));
				System.out.println("Campo Afetado: " + missingFields+"\n");
			}
		}

	@Test
	public void testDisabilitiesNonRepeatdLists() {
			String fieldTest  = "disabilities";
			Object fieldValue  = Arrays.asList("visual", "visual");
			System.out.println("Regra:" 	+ annotation.simpleObject()[2].rule()
					 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
					 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
							 );
			String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
			try {
				 CcpJsonRepresentation resume = super.getJson(filePath);
				 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
				 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				 System.out.println(apply);
				}
			catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String, Object>> missingFields = 
				e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.nonRepeatedLists","wrongFields");
				Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				assertTrue(names.contains(fieldTest));
				System.out.println("Campo Afetado: " + missingFields+"\n");
			}
		}
	
	@Test
	public void testCompaniesNotAllowedNonRepeatdLists() {
			String fieldTest  = "companiesNotAllowed";
			Object fieldValue  = Arrays.asList("Certisign", "Certisign");
			System.out.println("Regra:" 	+ annotation.simpleObject()[2].rule()
					 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
					 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
							 );
			String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
			try {
				 CcpJsonRepresentation resume = super.getJson(filePath);
				 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
				 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				 System.out.println(apply);
				}
			catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String, Object>> missingFields = 
				e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.nonRepeatedLists","wrongFields");
				Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				assertTrue(names.contains(fieldTest));
				System.out.println("Campo Afetado: " + missingFields+"\n");
			}
		}
	
//-------------------------------------------------simpleObject-integerFields----------------------------------------------	
	@Test
	public void testWrongValueIntDisponibility() {
			String fieldTest  = "disponibility";
			String fieldValue  = "AA";
			System.out.println("Regra:" 	+ annotation.simpleObject()[3].rule()
					 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
					 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
							 );
			String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
			try {
				 CcpJsonRepresentation resume = super.getJson(filePath);
				 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
				 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				 System.out.println(apply);
				}
			catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String, Object>> missingFields = 
				e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.integerFields","wrongFields");
				Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				assertTrue(names.contains(fieldTest));
				System.out.println("Campo Afetado: " + missingFields+"\n");
			}
		}

	@Test
	public void testWrongValueDoubleExperience() {
			String fieldTest  = "experience";
			String fieldValue  = "AA";
			System.out.println("Regra:" 	+ annotation.simpleObject()[3].rule()
					 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
					 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
							 );
			String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
			try {
				 CcpJsonRepresentation resume = super.getJson(filePath);
				 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
				 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				 System.out.println(apply);
				}
			catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String, Object>> missingFields = 
				e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleObject.integerFields","wrongFields");
				Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				assertTrue(names.contains(fieldTest));
				System.out.println("Campo Afetado: " + missingFields+"\n");
			}
		}

//-------------------------------------------------allowedValues-arrayWithAllowedNumbers----------------------------------------------	
	@Test
	public void testAllowedValuesDdd() {
			String fieldTest  = "ddd";
			String fieldValue  = "100";
			System.out.println("Regra:" 	+ annotation.allowedValues()[0].rule()
					 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
					 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
							 );
			String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
			try {
				 CcpJsonRepresentation resume = super.getJson(filePath);
				 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
				 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				 System.out.println(apply);
				}
			catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String, Object>> missingFields = 
				e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","AllowedValues.arrayWithAllowedNumbers","wrongFields");
				Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				assertTrue(names.contains(fieldTest));
				System.out.println("Campo Afetado: " + missingFields+"\n");
			}
		}

//-------------------------------------------------allowedValues-arrayWithAllowedTexts----------------------------------------------	
	@Test
	public void testAllowedValuesDisabilities() {
			String fieldTest  = "disabilities";
			String fieldValue  = "cegueira";
			System.out.println("Regra:" 	+ annotation.allowedValues()[1].rule()
					 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
					 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
							 );
			String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
			try {
				 CcpJsonRepresentation resume = super.getJson(filePath);
				 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
				 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				 System.out.println(apply);
				}
			catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String, Object>> missingFields = 
				e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","AllowedValues.arrayWithAllowedTexts","wrongFields");
				Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				assertTrue(names.contains(fieldTest));
				System.out.println("Campo Afetado: " + missingFields+"\n");
			}
		}	
	
//-------------------------------------------------objectTextSize-equalsOrLessThan----------------------------------------------
	@Test
	//regra nao aparece no Json
	public void testDesiredJobExceedLimitAbove() {
			String fieldTest  = "desiredJob";
			String fieldValue = "A".repeat(101);
			System.out.println("Regra:" 	+ annotation.objectTextSize()[0].rule()
					 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
					 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
							 );
			String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
			try {
				 CcpJsonRepresentation resume = super.getJson(filePath);
				 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
				 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				 System.out.println(apply);
				}
			catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String, Object>> missingFields = 
				e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectTextSize.equalsOrLessThan","wrongFields");
				Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				assertTrue(names.contains(fieldTest));
				System.out.println("Campo Afetado: " + missingFields+"\n");
			}
		}
	
	public void testLastDesiredJobLimitAbove() {
		String fieldTest  = "desiredJob";
		String fieldValue = "A".repeat(101);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectTextSize.equalsOrLessThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testObservationsExceedLimitAbove() {
		String fieldTest  = "observations";
		String fieldValue = "A".repeat(501);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectTextSize.equalsOrLessThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	public void testBase64ExceedLimitAbove() {
		String fieldTest  = "resumeBase64";
		String fieldValue = "A".repeat(4_200_001);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[2].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectTextSize.equalsOrLessThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}	
	
	@Test
	public void testBase64ExceedLimitUnder() {
		String fieldTest  = "resumeBase64";
		String fieldValue = "A".repeat(511);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[3].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectTextSize.equalsOrGreaterThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}	

	@Test
	public void testNameExceedLimitUnder() {
		String fieldTest  = "name";
		String fieldValue = "A".repeat(2);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[4].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectTextSize.equalsOrGreaterThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}	
	
	@Test
	public void testNameExceedLimitAbove() {
		String fieldTest  = "name";
		String fieldValue = "A".repeat(101);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[5].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectTextSize.equalsOrLessThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}
	
	@Test
	public void testEmailExceedLimitUnder() {
		String fieldTest  = "email";
		String fieldValue = "A".repeat(42);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[6].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectTextSize.equalsOrLessThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

//-------------------------------------------------simpleArray------------------------------------------------------------------
	//Validado pela regra allowedValues - AllowedValuesValidations.arrayWithAllowedNumbers
	@Test
	public void testDddNotEmptyArray() {
		String fieldTest  = "ddd";
		Object fieldValue  = Arrays.asList("", "");
		//Mesmo com a regra allowedValues - AllowedValuesValidations.arrayWithAllowedNumbers comentada, nao ocorre validacao
		//Object fieldValue  = EmptyArrays.EMPTY_STRINGS;
		System.out.println("Regra:" 	+ annotation.simpleArray()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleArray.notEmptyArray","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			
			assertTrue(names.isEmpty());
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}
	//Validado pela regra allowedValues - AllowedValuesValidations.arrayWithAllowedNumbers
	@Test
	public void testDddIntegerItems() {
		String fieldTest  = "ddd";
		//List<Double> fieldValue  = Arrays.asList(11.98, 12.55);
		Object fieldValue  = Arrays.asList("$%", "AA");
		System.out.println("Regra:" 	+ annotation.simpleArray()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","SimpleArray.integerItems","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.isEmpty());			
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

//-------------------------------------------------year-------------------------------------------------------------------------
	@Test
	public void testExperienceExceedLimitAbove() {
		String fieldTest  = "experience";
		Double fieldValue  = 90.0;
		System.out.println("Regra:" 	+ annotation.year()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","Year.equalsOrLessThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}
	
	@Test
	public void testExperienceExceedLimitUnder() {
		String fieldTest  = "experience";
		Double fieldValue  = -10.0;
		System.out.println("Regra:" 	+ annotation.year()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","Year.equalsOrGreaterThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			//assertTrue(names.contains(fieldTest));
			assertTrue(names.isEmpty());
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}
	
//-------------------------------------------------objectNumbers-------------------------------------------------------------------------
	
	@Test
	//NotMapped
	public void testBtcExceedLimitUnder() {
		String fieldTest  = "btc";
		String fieldValue  = "999";
		System.out.println("Regra:" 	+ annotation.objectNumbers()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectNumbers.equalsOrGreaterThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}
	
	@Test
	//NotMapped
	public void testCltExceedLimitUnder() {
		String fieldTest  = VisEntityResume.Fields.clt.name();
		String fieldValue  = "999";
		System.out.println("Regra:" 	+ annotation.objectNumbers()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectNumbers.equalsOrGreaterThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}
	
	@Test
	//NotMapped
	public void testPjExceedLimitUnder() {
		String fieldTest  = "pj";
		String fieldValue  = "999";
		System.out.println("Regra:" 	+ annotation.objectNumbers()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectNumbers.equalsOrGreaterThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}
	
	@Test
	//NotMapped
	public void testBtcExceedLimitAbove() {
		String fieldTest  = "btc";
		String fieldValue  = "100001";
		System.out.println("Regra:" 	+ annotation.objectNumbers()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectNumbers.equalsOrLessThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}

	@Test
	//NotMapped
	public void testCltExceedLimitAbove() {
		String fieldTest  = VisEntityResume.Fields.clt.name();
		String fieldValue  = "100001";
		System.out.println("Regra:" 	+ annotation.objectNumbers()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectNumbers.equalsOrLessThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}
	
	@Test
	//NotMapped
	public void testPjExceedLimitAbove() {
		String fieldTest  = "pj";
		String fieldValue  = "100001";
		System.out.println("Regra:" 	+ annotation.objectNumbers()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","ObjectNumbers.equalsOrLessThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			assertTrue(names.contains(fieldTest));
			System.out.println("Campo Afetado: " + missingFields+"\n");
		}
	}
	
	
	
	//NAO MAPEADO NO FRAMEWORK
	//ERRO: java.lang.RuntimeException: The value 'AAA' from the field 'experience' is not a double
	@Test
	public void testExperienceTypeMissMatch() {
		String fieldTest  = "experience";
		String fieldValue  = "AAA";
		System.out.println("Regra:" 	+ annotation.year()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<Map<String, Object>> missingFields = 
			e.result.getValueFromPath(new ArrayList<Map<String, Object>>(),"errors","Year.equalsOrLessThan","wrongFields");
			Set<String> names = missingFields.stream().map(itemDaLista -> new CcpJsonRepresentation(itemDaLista)).map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
			System.out.println("Campo Afetado: " + missingFields+"\n");
			
			assertTrue(names.contains(fieldTest));
			//

		}
	}
	
}
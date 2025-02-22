package com.ccp.vis.tests.resume.validations.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.ccp.decorators.CcpFileDecorator;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.decorators.CcpTimeDecorator;
import com.ccp.jn.sync.mensageria.JnSyncMensageriaSender;
import com.ccp.validation.CcpJsonInvalid;
import com.ccp.validation.annotations.AllowedValues;
import com.ccp.validation.annotations.CcpJsonFieldsValidation;
import com.ccp.validation.annotations.SimpleObject;
import com.ccp.validation.enums.SimpleObjectValidations;
import com.ccp.vis.async.commons.FrequencyOptions;
import com.ccp.vis.tests.commons.BaseTest;
import com.ccp.vis.tests.commons.resumes.ResumeTransformations;
import com.vis.commons.cache.tasks.PutSkillsInJson;
import com.vis.commons.json.validations.VisJsonValidationResume;
import com.vis.commons.utils.VisAsyncBusiness;


//pode adicionar a dependencia ou nao e' necessa'rio?
//@FixMethodOrder(MethodSorters.JVM)
public class ValidationsJsonSaveResumeTestGuilherme extends BaseTest {

    CcpJsonFieldsValidation annotation = VisJsonValidationResume.class.getAnnotation(CcpJsonFieldsValidation.class);
    Object fieldTest;
    
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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

	
	//TestRemoveFields
	@Test
	public void testRemoveFieldsCltPj() {		
		String fieldTest  = "clt";
		String fieldTest1  = "pj";
		System.out.println("Regra:" 	+ annotation.simpleObject()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.removeFields(fieldTest).removeFields(fieldTest1);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
			 System.out.println(apply);
			}
		catch (CcpJsonInvalid e) {
			super.saveErrors(filePath, e);
			List<String> missingFields = e.result.getValueFromPath(new ArrayList<String>(), "errors","SimpleObject.requiredAtLeastOne", "wrongFields");
			assertTrue(missingFields.contains(fieldTest) && missingFields.contains(fieldTest1));
			System.out.println("Campo Afetado: " + missingFields+"\n");
			}
		}
			
	//TestWrongValue
	@Test
	public void testWrongValueDdd() {
			String fieldTest  = "ddd";
			String fieldValue  = "AA";
			System.out.println("Regra:" 	+ annotation.allowedValues()[1].rule()
					 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
					 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
							 );
			String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
			try {
				 CcpJsonRepresentation resume = super.getJson(filePath);
				 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
				 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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

	@Test
	public void testWrongValueDisponibility() {
		String fieldTest  = "disponibility";
		String fieldValue  = "AAAAA";
		System.out.println("Regra:" 	+ annotation.simpleObject()[3].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testWrongValueDisabilities() {
		String fieldTest  = "disabilities";
		String fieldValue  = "AAAAA";
		System.out.println("Regra:" 	+ annotation.allowedValues()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	//NotMapped
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
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testDesiredJobExceedLimit() {
		String fieldTest  = "desiredJob";
		String fieldValue  = "A".repeat(101);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testLastJobExceedLimit() {
		String fieldTest  = "lastJob";
		String fieldValue  = "A".repeat(101);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testNameExceedLimitAbove() {
		String fieldTest  = "name";
		String fieldValue  = "A".repeat(101);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[4].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testNameExceedLimitUnder() {
		String fieldTest  = "name";
		String fieldValue  = "AA";
		System.out.println("Regra:" 	+ annotation.objectTextSize()[5].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testEmailExceedLimitAbove() {
		String fieldTest  = "email";
		String fieldValue  = "A".repeat(50);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[6].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
		String fieldValue  = "A".repeat(501);
		System.out.println("Regra:" 	+ annotation.objectTextSize()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
		String fieldValue  = "dGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGV0ZXN0ZXRlc3RldGVzdGU=";
		System.out.println("Regra:" 	+ annotation.objectTextSize()[2].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
		String fieldValue  = "dGVzdGU=";
		System.out.println("Regra:" 	+ annotation.objectTextSize()[3].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testDddRepeated() {
		String fieldTest  = "ddd";
		String fieldValue  = "98";
		System.out.println("Regra:" 	+ annotation.objectTextSize()[3].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation insertField = resume.putFilledTemplate(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(insertField);
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
	public void testDisabilitiesRepeated() {
		String fieldTest  = "resumeBase64";
		String fieldValue  = "dGVzdGU=";
		System.out.println("Regra:" 	+ annotation.objectTextSize()[3].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testCompaniesNotAllowedRepeated() {
		String fieldTest  = "resumeBase64";
		String fieldValue  = "dGVzdGU=";
		System.out.println("Regra:" 	+ annotation.objectTextSize()[3].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testPjExceedLimitAbove() {
		String fieldTest  = "pj";
		Integer fieldValue  = 999;
		System.out.println("Regra:" 	+ annotation.objectNumbers()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testPjExceedLimitUnder() {
		String fieldTest  = "pj";
		Integer fieldValue  = -100001;
		System.out.println("Regra:" 	+ annotation.year()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testBtcExceedLimitAbove() {
		String fieldTest  = "btc";
		Integer fieldValue  = 999;
		System.out.println("Regra:" 	+ annotation.objectNumbers()[0].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	public void testBtcExceedLimitUnder() {
		String fieldTest  = "btc";
		Integer fieldValue  = -100001;
		System.out.println("Regra:" 	+ annotation.year()[1].rule()
				 		 + "\nTeste:"   + Thread.currentThread().getStackTrace()[1].getMethodName() 
				 		 + "\nLinha:" 	+ Thread.currentThread().getStackTrace()[1].getLineNumber()
						 );
		String filePath = "documentation/tests/resume/" + "curriculoParaSalvar.json";
		try {
			 CcpJsonRepresentation resume = super.getJson(filePath);
			 CcpJsonRepresentation removeField = resume.put(fieldTest, fieldValue);
			 CcpJsonRepresentation apply = new JnSyncMensageriaSender(VisAsyncBusiness.resume).apply(removeField);
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
	
	
}

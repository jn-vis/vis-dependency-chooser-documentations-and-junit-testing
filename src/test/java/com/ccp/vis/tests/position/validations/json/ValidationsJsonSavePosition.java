package com.ccp.vis.tests.position.validations.json;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.especifications.db.utils.CcpEntityCrudOperationType;
import com.ccp.validation.CcpJsonInvalid;
import com.ccp.vis.tests.commons.BaseTest;
import com.jn.mensageria.JnMensageriaSender;
import com.vis.commons.entities.VisEntityResume;

public class ValidationsJsonSavePosition extends BaseTest {
	
	@Test
	public void testRemoveField() {

		String filePath = "documentation/tests/resume/curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("companiesNotAllowed")
													  .removeFields("ddd")
													  .removeFields("desiredJob")
													  .removeFields("disponibility")
													  .removeFields("disabilities")
													  .removeFields("experience")
													  .removeFields("lastJob")
													  .removeFields("name")
													  .removeFields("fileName")
													  .removeFields("observations")
													  .removeFields("resumeBase64")
													  .removeFields("originalEmail")
													  .removeFields("email")
//													  .removeFields(VisEntityResume.Fields.clt.name())
//													  .removeFields("topic")
//													  .removeFields("resumeText") 
													  ;
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("companiesNotAllowed"));
				assertTrue(names.contains("ddd"));
				assertTrue(names.contains("desiredJob"));
				assertTrue(names.contains("disponibility"));
				assertTrue(names.contains("disabilities"));
				assertTrue(names.contains("experience"));
				assertTrue(names.contains("lastJob"));
				assertTrue(names.contains("name"));
				assertTrue(names.contains("fileName"));
				assertTrue(names.contains("observations"));
				assertTrue(names.contains("resumeBase64"));
				assertTrue(names.contains("originalEmail"));
				assertTrue(names.contains("email"));
//				assertTrue(names.contains(VisEntityResume.Fields.clt.name()));
//				assertTrue(names.contains("topic"));
//				assertTrue(names.contains("resumeText"));
		}
	}

	@Test
	public void testRemoveFieldCompaniesNotAllowed() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("companiesNotAllowed");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("companiesNotAllowed"));
		}
	}
	
	@Test
	public void testRemoveFieldDdd() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("ddd");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);
				
				assertTrue(names.contains("ddd"));
				
		}
	}
	
	@Test
	public void testRemoveFieldDesiredJob() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("desiredJob");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("desiredJob"));
		}
	}
	
	@Test
	public void testRemoveFieldDisponibility() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("disponibility");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("disponibility"));
		}
	}
	
	@Test
	public void testRemoveFieldDisabilities() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("disabilities");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("disabilities"));
		}
	}
	
	@Test
	public void testRemoveFieldExperience() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("experience");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("experience"));
		}
	}
	
	@Test
	public void testRemoveFieldLastJob() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("lastJob");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("lastJob"));
		}
	}
	
	@Test
	public void testRemoveFieldName() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("name");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("name"));				
		}
	}
	
	@Test
	public void testRemoveFieldFileName() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("fileName");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("fileName"));				
		}
	}
	
	@Test
	public void testRemoveFieldObservations() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume .removeFields("observations");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("observations"));
		}
	}
	
	@Test
	public void testRemoveFieldResumeBase64() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("resumeBase64");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("resumeBase64"));
		}
	}
	
	@Test
	public void testRemoveFieldOriginalEmail() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("originalEmail", "ddd");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("originalEmail"));
		}
	}
	
	@Test
	public void testRemoveFieldEmail() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("email");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("email"));
		}
	}
	
	
	//ao usar map apresenta erro de cast
	@Test
	public void testRemoveFieldCltPj() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields(VisEntityResume.Fields.clt.name())
													  .removeFields(VisEntityResume.Fields.pj.name())
													  ;
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);			
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
	
				List<String> missingFields = e.result.getValueFromPath(new ArrayList<String>(), "errors", "SimpleObject.requiredAtLeastOne", "wrongFields");
					System.out.println(missingFields);				
				    
				System.out.println(missingFields);

				assertTrue(missingFields.contains(VisEntityResume.Fields.clt.name()));
				assertTrue(missingFields.contains(VisEntityResume.Fields.pj.name()));

		}
	}
	
	@Test
	public void testRemoveFieldResumeText() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {
			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation removeField = resume.removeFields("resumeText");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(removeField);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("resumeText"));

		}
		
	}
	
	
//##############Test_Wrong_Values##################################################################################################################################################################################
	
	//teste nao funciona, quebra a aplicao ao passar o mesmo valor do curriculo
	
	@Test
	public void testWrongValueCompaniesNotAllowed() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.put("companiesNotAllowed","VW");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);
				assertTrue(false);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("companiesNotAllowed"));
		}
	}
	
	
	@Test
	public void testWrongValueDdd() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.put("ddd","a");
				System.out.println("resume.fieldSet: " + resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println("appply: " + apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","AllowedValues.arrayWithAllowedNumbers","wrongFields");				
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);
								
				assertTrue(names.contains("ddd"));
				
		}
	}

	//possivel erro do framework
	@Test
	public void testWrongValueDesiredJob() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.put("desiredJob","trabalho desejado");
				System.out.println("antes do fieldSet");
				System.out.println(resume.fieldSet());
				System.out.println("antes do apply");
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println("depois do apply");
				System.out.println("error..." + apply);
			} catch (CcpJsonInvalid e) {
				System.out.println("entrou no catch");
				super.saveErrors(filePath, e);
				List<Map<String,Object>> missingFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(missingFields);
				Set<String> names = missingFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);
				
				assertTrue(names.contains("desiredJob"));
		}
	}

	
	@Test
	public void testWrongValueDisponibility() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.removeFields("disponibility");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("disponibility"));
		}
	}
	
	@Test
	public void testWrongValueDisabilities() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.removeFields("disabilities");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("disabilities"));
		}
	}
	
	@Test
	public void testWrongValueExperience() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.removeFields("experience");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("experience"));
		}
	}
	
	@Test
	public void testWrongValueLastJob() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.removeFields("lastJob");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("lastJob"));
		}
	}
	
	@Test
	public void testWrongValueName() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.removeFields("name");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("name"));				
		}
	}
	
	@Test
	public void testWrongValueFileName() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.removeFields("fileName");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("fileName"));				
		}
	}
	
	@Test
	public void testWrongValueObservations() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume .removeFields("observations");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("observations"));
		}
	}
	
	@Test
	public void testWrongValueResumeBase64() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.removeFields("resumeBase64");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("resumeBase64"));
		}
	}
	
	@Test
	public void testWrongValueOriginalEmail() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.removeFields("originalEmail");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("originalEmail"));
		}
	}
	
	@Test
	public void testWrongValueEmail() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.removeFields("email");
				System.out.println(resume.fieldSet());
			CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredFields","wrongFields");
				System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());
				System.out.println(names);

				assertTrue(names.contains("email"));
		}
	}
				
	@Test
	public void testWrongValueCltPj() {

		String filePath = "documentation/tests/resume/"	+ "curriculoParaSalvar.json";
		try {			
			CcpJsonRepresentation resume = super.getJson(filePath);
			CcpJsonRepresentation insertValue = resume.put(VisEntityResume.Fields.clt.name(), "2")
													  ;
				System.out.println(resume.fieldSet());
			
				CcpJsonRepresentation apply = new JnMensageriaSender(VisEntityResume.ENTITY, CcpEntityCrudOperationType.save).apply(insertValue);
				System.out.println(apply);			
			} catch (CcpJsonInvalid e) {
				super.saveErrors(filePath, e);
				List<Map<String,Object>> wrongFields = e.result.getValueFromPath(new ArrayList<Map<String,Object>>(), "errors","SimpleObject.requiredAtLeastOne","wrongFields");
					System.out.println(wrongFields);
				Set<String> names = wrongFields.stream()
						.map(itemDaLista -> new CcpJsonRepresentation(itemDaLista))
						.map(itemDaLista -> itemDaLista.getAsString("name")).collect(Collectors.toSet());

				
/*				List<String> wrongFields = e.result.getValueFromPath(new ArrayList<String>(), "errors", "SimpleObject.requiredAtLeastOne", "wrongFields");
					System.out.println(wrongFields);				
				Set<String> names = wrongFields.stream()
				    .map(fieldName -> fieldName) 
				    .collect(Collectors.toSet());
*/
					System.out.println(names);

				assertTrue(names.contains(VisEntityResume.Fields.clt.name()));
				assertTrue(names.contains(VisEntityResume.Fields.pj.name()));

		}
	}
	
/*
	@Test
	public void salvarVaga() {
		CcpFileDecorator vagasFile = new CcpStringDecorator("c:\\logs\\vagas.json").file();
		List<CcpJsonRepresentation> vagas = vagasFile.asJsonList();
		int k = 1;
		CcpFileDecorator vagas2File = new CcpStringDecorator("c:\\logs\\vagas3.json").file().reset();
		for (CcpJsonRepresentation vaga : vagas) {
			CcpJsonRepresentation converterVaga = this.converterVaga(vaga);
			vagas2File.append(converterVaga.asUgglyJson());
			System.out.println(k++);
		}
		
		
	}
	
	CcpJsonRepresentation converterVaga(CcpJsonRepresentation vaga) {
		
		String nomeDoArquivo = vaga.getValueFromPath("curriculo", "arquivo");
		CcpJsonRepresentation putTitle = vaga.removeField("curriculo").put("title", nomeDoArquivo);
		Long time = 1704078000000L;
		Long timestamp = time + (long)(Math.random() * 32_000_000_000L);
		putTitle = putTitle.put("timestamp", timestamp);
		
		String[] seniorities = new String[] {"JR", "PL", "SR", "ES"};
		int seniorityIndex = (int)(timestamp % seniorities.length);
		String seniority = seniorities[seniorityIndex];
		CcpJsonRepresentation putSeniority = putTitle.put("seniority", seniority);
		
		PutSkillsInJson putSkillsInJson = new PutSkillsInJson("description", "requiredSkill");
		CcpJsonRepresentation putRequiredSkills = putSeniority.getTransformedJson(putSkillsInJson);
		
		CcpJsonRepresentation putPj = putRequiredSkills.renameField("pretensaoPj", "pj");
		CcpJsonRepresentation putClt = putPj.renameField("pretensaoClt", VisEntityResume.Fields.clt.name());
		CcpJsonRepresentation putBitcoin = putClt.renameField("bitcoin", "btc");
		
		boolean pcd = putBitcoin.getAsBoolean("pcd");
		CcpJsonRepresentation putPcd = putBitcoin.put("pcd", pcd);
		
		FrequencyOptions[] frequencies = FrequencyOptions.values();
		int frequencyIndex = (int)(timestamp % frequencies.length);
		String frequency = frequencies[frequencyIndex].name();
		CcpJsonRepresentation putFrequency = putPcd.put("frequency", frequency);
		
		Long expireDate = timestamp + (30 * 86_400_000);
		CcpJsonRepresentation putExpireDate = putFrequency.put("expireDate", expireDate);
		
		String email = putExpireDate.getAsString("email");
		CcpJsonRepresentation putEmail = putExpireDate.put("email", email);
		CcpJsonRepresentation putDisponibility = putEmail.renameField("disponibilidade", "disponibility");
		CcpJsonRepresentation putDesiredSkill = putDisponibility.put("desiredSkill", new ArrayList<Object>());
		CcpJsonRepresentation putDdd = ResumeTransformations.AddDddsInResume.apply(putDesiredSkill);
		CcpTimeDecorator ccpTimeDecorator = new CcpTimeDecorator(timestamp);
		String formattedDateTime = ccpTimeDecorator.getFormattedDateTime("dd/MM/yyyy HH:mm:ss.SSS");
		CcpJsonRepresentation putDate = putDdd.put("date", formattedDateTime);
		CcpJsonRepresentation putSortFields = putDate.put("sortFields", new ArrayList<Object>());
		
		return putSortFields;
	}
*/
}

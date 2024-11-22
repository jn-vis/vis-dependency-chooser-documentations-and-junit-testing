package com.ccp.vis.tests.position.validations.json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ccp.decorators.CcpFileDecorator;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.decorators.CcpTimeDecorator;
import com.ccp.vis.async.commons.FrequencyOptions;
import com.ccp.vis.tests.commons.BaseTest;
import com.ccp.vis.tests.commons.resumes.ResumeTransformations;
import com.jn.vis.commons.cache.tasks.PutSkillsInJson;

public class ValidationsJsonSavePosition extends BaseTest {

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
		CcpJsonRepresentation putClt = putPj.renameField("pretensaoClt", "clt");
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

}

package com.ccp.jn.vis.test.asserting;

import static org.junit.Assert.assertTrue;

import java.util.function.Predicate;

import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpTimeDecorator;

public class VerificacaoDeStatusDaTarefaAssincrona extends TemplateDeTestes{

	@SuppressWarnings("unchecked")
	public void getCaminhoFeliz(String asyncTaskId, CcpJsonRepresentation jsonToTest, Predicate<CcpJsonRepresentation>... predicates) {
		
		CcpJsonRepresentation json = this.getCaminhoFeliz("async/task/" + asyncTaskId);
		
		for(int k = 0; k < 10; k++) {
			new CcpTimeDecorator().sleep(1000);
			
			boolean stillIsRunning = json.containsKey("finished") == false;
			
			if(stillIsRunning) {
				continue;
			}
			
			boolean success = json.getAsBoolean("success");
			assertTrue(success);
			
			for (Predicate<CcpJsonRepresentation> predicate : predicates) {
				boolean test = predicate.test(jsonToTest);
				assertTrue(test);
			}
			return;

		}
	}
	
	
	protected String getMethod() {
		return "GET";
	}

}

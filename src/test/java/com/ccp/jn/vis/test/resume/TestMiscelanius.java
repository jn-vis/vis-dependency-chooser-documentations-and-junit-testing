package com.ccp.jn.vis.test.resume;

import static org.junit.Assert.assertTrue;

import com.ccp.decorators.CcpJsonRepresentation;

public enum TestMiscelanius {

	fieldsPresent {

		@Override
		public void test(CcpJsonRepresentation json, String... fields) {
			assertTrue(json.containsAllKeys(fields));			
		}
		
	}, 
	nonDuplicatedItems {

		@Override
		public void test(CcpJsonRepresentation json, String... fields) {
			for (String field : fields) {
				boolean hasNotDuplicatedItems = json.getAsArrayMetadata(field).hasNotDuplicatedItems();
				assertTrue(hasNotDuplicatedItems);
			}
		}},
	
	;
	public abstract void test (CcpJsonRepresentation json, String...fields);
}

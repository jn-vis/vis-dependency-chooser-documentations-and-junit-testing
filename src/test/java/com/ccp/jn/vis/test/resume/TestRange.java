package com.ccp.jn.vis.test.resume;

import static org.junit.Assert.assertTrue;

import com.ccp.decorators.CcpJsonRepresentation;

public enum TestRange {
	
	stringLenghtMin {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).text().lenght().isGreaterThan(number);
				assertTrue(x);
			}
		}
	},
	stringLenghtMinOrEquals {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).text().lenght().isGreaterOrEqualsThan(number);
				assertTrue(x);
			}
		}
	},
	stringLenghtMax {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).text().lenght().isLessThan(number);
				assertTrue(x);
			}
		}
	},
	stringLenghtMaxOrEquals {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).text().lenght().isLessOrEqualsThan(number);
				assertTrue(x);
			}
		}
	},
	stringLenghtEquals {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).text().lenght().isEqualsTo(number);
				assertTrue(x);
			}
		}
	},
	arrayLenghtMin {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).text().lenght().isLessThan(number);
				assertTrue(x);
			}
		}
	},
	arrayLenghtMinOrEquals {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).text().lenght().isGreaterThan(number);
				assertTrue(x);
			}
		}
	},
	arrayLenghtMax {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).text().lenght().isGreaterThan(number);
				assertTrue(x);
			}
		}
	},
	arrayLenghtMaxOrEquals {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).text().lenght().isGreaterThan(number);
				assertTrue(x);
			}
		}
	},
	arrayLenghtEquals {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).text().lenght().isGreaterThan(number);
				assertTrue(x);
			}
		}
	},
	numberLenghtMin {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).number().isGreaterThan(number);
				assertTrue(x);
			}
		}
	},
	numberLenghtMinOrEquals {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).number().isGreaterOrEqualsThan(number);
				assertTrue(x);
			}
		}
	},
	numberLenghtMax {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).number().isLessThan(number);
				assertTrue(x);
			}
		}
	},
	numberLenghtMaxOrEquals {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).number().isLessOrEqualsThan(number);
				assertTrue(x);
			}
		}
	},
	numberLenghtEquals {
		@Override
		public void test(CcpJsonRepresentation json, int number, String... fields) {
			for (String field : fields) {
				boolean x = json.getAsMetadata(field).number().isEqualsTo(number);
				assertTrue(x);
			}
		}
	},

	;
	
	public abstract void test (CcpJsonRepresentation json, int number, String...fields);
}

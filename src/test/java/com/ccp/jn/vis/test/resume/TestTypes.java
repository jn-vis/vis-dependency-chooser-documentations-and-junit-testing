package com.ccp.jn.vis.test.resume;

import com.ccp.decorators.CcpJsonRepresentation;

public enum TestTypes {

	booleanType {
		@Override
		public void test(CcpJsonRepresentation json, String key) {
			// TODO Auto-generated method stub
			
		}
	},
	longType {
		@Override
		public void test(CcpJsonRepresentation json, String key) {
			// TODO Auto-generated method stub
			
		}
	},
	doubleType {
		@Override
		public void test(CcpJsonRepresentation json, String key) {
			// TODO Auto-generated method stub
			
		}
	},
	listType {
		@Override
		public void test(CcpJsonRepresentation json, String key) {
			// TODO Auto-generated method stub
			
		}
	},
	jsonType {
		@Override
		public void test(CcpJsonRepresentation json, String key) {
			// TODO Auto-generated method stub
			
		}
	},
	;
	public abstract void test(CcpJsonRepresentation json, String key);
}

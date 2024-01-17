package com.ccp.jn.vis.test.resume;

import java.util.Collection;

import com.ccp.decorators.CcpJsonRepresentation;

public enum TestDomainNumber {

	array {
		@Override
		public void test(CcpJsonRepresentation json, Collection<Double> domain, String... fields) {
			// TODO Auto-generated method stub
			
		}
	},
	item {
		@Override
		public void test(CcpJsonRepresentation json, Collection<Double> domain, String... fields) {
			// TODO Auto-generated method stub
			
		}
	},
	;
	public abstract void test (CcpJsonRepresentation json, Collection<Double> domain, String...fields);
}

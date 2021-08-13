package com.exasol.csv.functional_test.selector;

public enum Id implements Selector {

	DATATABLE("table-form:table_data");
	
	private final String id;
	
	private Id(String id) {
		this.id = id;
	}

	@Override
	public String asString() {
		return this.id;
	}
	
}

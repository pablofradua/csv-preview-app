package com.exasol.csv.functional_test.selector;

public enum TagName implements Selector {

	TABLE_ROW("tr");
	
	private final String tag;
	
	private TagName(String tag) {
		this.tag = tag;
	}

	@Override
	public String asString() {
		return this.tag;
	}
}

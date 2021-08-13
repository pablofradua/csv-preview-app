package com.exasol.csv.functional_test.selector;

public enum CssSelector implements Selector{

	INPUT_FILE("[type='file']");
	
	private final String selector;
	
	private CssSelector(String selector) {
		this.selector = selector;
	}

	public String asString() {
		return selector;
	}
}

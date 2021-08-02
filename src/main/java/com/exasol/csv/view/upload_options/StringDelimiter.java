package com.exasol.csv.view.upload_options;

public enum StringDelimiter {

	DOUBLE_QUOTES('\"', "Double Quotes"),
	SINGLE_QUOTES('\'', "Single Quotes");
	
	private final char value;
	private final String label;
	
	private StringDelimiter(char value, String label) {
		this.value = value;
		this.label = label;
	}

	public char getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
}

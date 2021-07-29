package com.exasol.csv.view.file;

public enum StringDelimeter {

	DOUBLE_QUOTES('\"'),
	SINGLE_QUOTES('\'');
	
	private final char value;
	
	private StringDelimeter(char value) {
		this.value = value;
	}

	public char getValue() {
		return value;
	}
}

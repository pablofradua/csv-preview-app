package com.exasol.csv.view.file;

/**
 * Allowed values for setting the field delimeter, when reading CSV files.
 * @author pablo
 *
 */
public enum ColumnSeparator {

	COMMA(','),
	SEMICOLON(';'),
	TAB_STOPS('\t');
	
	private final char value;
	
	private ColumnSeparator(char value) {
		this.value = value;
	}

	public char getValue() {
		return value;
	}
}

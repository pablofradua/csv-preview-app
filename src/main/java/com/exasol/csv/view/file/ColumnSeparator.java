package com.exasol.csv.view.file;

/**
 * Allowed values for setting the field delimeter, when reading CSV files.
 * @author pablo
 *
 */
public enum ColumnSeparator {

	COMMA(',', "Comma"),
	SEMICOLON(';', "Semicolon"),
	TAB_STOPS('\t', "Tabstop");
	
	private final char value;
	
	private final String label;
	
	private ColumnSeparator(char value, String label) {
		this.value = value;
		this.label = label;
	}

	public char getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}
}

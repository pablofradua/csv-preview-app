package com.exasol.csv.view.file;

/**
 * Allowed values for setting the row/line separator, when reading CSV files.
 * Looks like Apache Commons CSV already chooses the proper one, so this should be unnecessary.
 * @author pablo
 *
 */
public enum RowSeparator {

	LF(0x0A, "Line Feed"),
	CR(0x0D, "Carriage Return"),
	CRLF(0x0D+0x0A, "Carriage Return Line Feed");
	
	private final char value;
	
	private final String label;
	
	private RowSeparator(int value, String label) {
		this.value = (char) value;
		this.label = label;
	}

	public char getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
}

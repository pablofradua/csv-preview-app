package com.exasol.csv.view.file;

/**
 * Allowed values for setting the row/line separator, when reading CSV files.
 * Looks like Apache Commons CSV already chooses the proper one, so this should be unnecessary.
 * @author pablo
 *
 */
public enum RowSeparator {

	LF(0x0A),
	CR(0x0D),
	CRLF(0x0D+0x0A);
	
	private final char value;
	
	private RowSeparator(int value) {
		this.value = (char) value;
	}

	public char getValue() {
		return value;
	}
}

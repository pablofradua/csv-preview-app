package com.exasol.csv.view.upload_options;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import lombok.Data;

@Data
public class UploadOptions implements Serializable {

	private ColumnSeparator columnSeparator;
	private RowSeparator rowSeparator;
	private StringDelimiter stringDelimiter;
	private Charset charset;
	private HeaderOrigin headerOrigin;
	
	public UploadOptions() {
		this.columnSeparator = ColumnSeparator.COMMA;
		this.rowSeparator = RowSeparator.LF;
		this.stringDelimiter = StringDelimiter.DOUBLE_QUOTES;
		this.charset = StandardCharsets.UTF_8;
		this.headerOrigin = HeaderOrigin.FIRST_ROW;
	}
	
	public void setParseColumnHeadersFromFirstRow(boolean firstRowContainsColumnHeaders) {
		this.headerOrigin = firstRowContainsColumnHeaders ? HeaderOrigin.FIRST_ROW : HeaderOrigin.AUTO;
	}
	
	public boolean getParseColumnHeadersFromFirstRow() {
		return this.headerOrigin == HeaderOrigin.FIRST_ROW;
	}
	
	public Collection<Charset> availableCharsets() {
		return Charset.availableCharsets().values();
	}
}
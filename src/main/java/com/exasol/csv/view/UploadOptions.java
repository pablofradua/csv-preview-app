package com.exasol.csv.view;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.exasol.csv.view.file.ColumnSeparator;
import com.exasol.csv.view.file.RowSeparator;
import com.exasol.csv.view.file.StringDelimeter;

import lombok.Data;

@Data
public class UploadOptions implements Serializable {

	private ColumnSeparator columnSeparator;
	private RowSeparator rowSeparator;
	private StringDelimeter stringDelimeter;
	private Charset charset;
	
	public UploadOptions() {
		this.columnSeparator = ColumnSeparator.COMMA;
		this.rowSeparator = RowSeparator.LF;
		this.stringDelimeter = StringDelimeter.DOUBLE_QUOTES;
		this.charset = StandardCharsets.UTF_8;
	}
}

package com.exasol.csv.view;

import java.io.Serializable;

import com.exasol.csv.view.file.ColumnSeparator;
import com.exasol.csv.view.file.RowSeparator;

import lombok.Data;

@Data
public class UploadOptions implements Serializable {

	private ColumnSeparator columnSeparator;
	private RowSeparator rowSeparator;
	
	public UploadOptions() {
		this.columnSeparator = ColumnSeparator.COMMA;
		this.rowSeparator = RowSeparator.LF;
	}
}

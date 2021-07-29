package com.exasol.csv.view;

import java.io.Serializable;

import com.exasol.csv.view.file.ColumnSeparator;

import lombok.Data;

@Data
public class UploadOptions implements Serializable {

	private ColumnSeparator columnSeparator;
	
	public UploadOptions() {
		this.columnSeparator = ColumnSeparator.COMMA;
	}
}

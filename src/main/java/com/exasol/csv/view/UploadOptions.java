package com.exasol.csv.view;

import java.io.Serializable;

import lombok.Data;

@Data
public class UploadOptions implements Serializable {

	private String columnSeparator;
}

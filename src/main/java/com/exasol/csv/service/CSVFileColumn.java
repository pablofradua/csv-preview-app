package com.exasol.csv.service;

import java.io.Serializable;

import lombok.Value;

@Value
public class CSVFileColumn implements Serializable{

	private String columnName;
	private int displayPriority;
	
}

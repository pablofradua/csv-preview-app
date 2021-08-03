package com.exasol.csv.service;

import java.io.Serializable;

import lombok.Value;

/**
 * Represents a csv datatable column
 * @author pfradua
 *
 */
@Value
public class CSVFileColumn implements Serializable{

	/**
	 * Column display name
	 */
	private String columnName;
	
	/**
	 * Column display priority, for sorting (0 being the first)
	 */
	private int displayPriority;
	
}

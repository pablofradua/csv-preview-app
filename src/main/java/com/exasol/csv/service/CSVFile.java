package com.exasol.csv.service;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * Represents a CSV datatable file
 * @author pfradua
 *
 */
@Value
@Builder
public class CSVFile implements Serializable {
	@NonNull private String filename;
    @NonNull private List<CSVFileColumn> columns;
    @NonNull private List<List<String>> rows;
}

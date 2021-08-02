package com.exasol.csv.service;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CSVFile implements Serializable {
	@NonNull String filename;
    @NonNull List<String> columnNames;
    @NonNull List<List<String>> rows;
}

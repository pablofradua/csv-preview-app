package com.exasol.csv.view;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.primefaces.model.file.UploadedFile;

public class FileConverter {

	private static final int FIRST_LINE = 0;
	
	private static final CSVRecordMapper MAPPER = new CSVRecordMapper();

	public CSVFile convert(UploadedFile uploadedFile, UploadOptions uploadOptions) {
		try (Reader fileReader = new InputStreamReader(uploadedFile.getInputStream())) {
			CSVParser parser = CSVFormat.EXCEL
					.withDelimiter(uploadOptions.getColumnSeparator().getValue())
					.withQuote(uploadOptions.getStringDelimeter().getValue())
					.parse(fileReader);
			var allLines = parser.getRecords().stream().map(MAPPER::toList).collect(toList());
			return CSVFile.builder()
					.filename(uploadedFile.getFileName())
					.columnNames(allLines.get(FIRST_LINE))
					.rows(allLines.stream().skip(1).collect(toList()))
					.build();
		} catch (IOException e) {
			throw new CouldNotReadFileException(e);
		}
	}

}

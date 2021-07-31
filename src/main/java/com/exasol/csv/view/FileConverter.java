package com.exasol.csv.view;

import static com.exasol.csv.view.file.HeaderOrigin.FIRST_ROW;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.csv.CSVParser;

import com.exasol.csv.view.file.HeaderOrigin;

public class FileConverter {

	private static final CSVRecordMapper MAPPER = new CSVRecordMapper();
	private static final CSVFormatFactory CSV_FORMAT_FACTORY = new CSVFormatFactory();

	public CSVFile convert(String filename, InputStream fileContents, UploadOptions uploadOptions) {
		try (Reader fileReader = new InputStreamReader(fileContents, uploadOptions.getCharset())) {
			CSVParser parser = CSV_FORMAT_FACTORY.getInstance(uploadOptions).parse(fileReader);
			HeaderOrigin headerOrigin = uploadOptions.getHeaderOrigin();
			List<List<String>> rows =  parser.getRecords().stream().map(MAPPER::toList).collect(toList());
			List<String> columnNames = headerOrigin == FIRST_ROW ? parser.getHeaderNames() : generateColumnsNames(rows);
			return CSVFile.builder()
					.filename(filename)
					.columnNames(columnNames)
					.rows(rows)
					.build();
		} catch (IOException e) {
			throw new CouldNotReadFileException(e);
		}
	}

	private List<String> generateColumnsNames(List<List<String>> rows) {
		return IntStream.rangeClosed(1, rows.get(0).size()).mapToObj(i->"Column "+i).collect(toList());
	}

}

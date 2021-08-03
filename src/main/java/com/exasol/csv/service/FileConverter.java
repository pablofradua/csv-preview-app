package com.exasol.csv.service;

import static com.exasol.csv.view.upload_options.HeaderOrigin.FIRST_ROW;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.csv.CSVParser;

import com.exasol.csv.view.upload_options.HeaderOrigin;
import com.exasol.csv.view.upload_options.UploadOptions;

public class FileConverter {

	private static final CSVRecordMapper MAPPER = new CSVRecordMapper();
	private static final CSVFormatFactory CSV_FORMAT_FACTORY = new CSVFormatFactory();

	public CSVFile convert(String filename, InputStream fileContents, UploadOptions uploadOptions) {
		try (Reader fileReader = new InputStreamReader(fileContents, uploadOptions.getCharset())) {
			CSVParser parser = CSV_FORMAT_FACTORY.getInstance(uploadOptions).parse(fileReader);
			HeaderOrigin headerOrigin = uploadOptions.getHeaderOrigin();
			List<List<String>> rows =  parser.getRecords().stream().map(MAPPER::toList).collect(toList());
			List<CSVFileColumn> columns = headerOrigin == FIRST_ROW ? getColumnsFromFile(parser) : generateColumns(rows);
			return CSVFile.builder()
					.filename(filename)
					.columns(columns)
					.rows(rows)
					.build();
		} catch (IOException e) {
			throw new CouldNotReadFileException(e);
		}
	}

	private List<CSVFileColumn> getColumnsFromFile(CSVParser parser) {
		List<String> columnNames = parser.getHeaderNames();
		return columnNames.stream().sorted(String::compareTo).map(x->new CSVFileColumn(x, columnNames.indexOf(x))).collect(toList());
	}

	private List<CSVFileColumn> generateColumns(List<List<String>> rows) {
		return IntStream.rangeClosed(1, rows.get(0).size()).mapToObj(i->new CSVFileColumn("Column "+i, i)).collect(toList());
	}

}

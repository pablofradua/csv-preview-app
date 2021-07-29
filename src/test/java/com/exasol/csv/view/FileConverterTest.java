package com.exasol.csv.view;

import static java.nio.file.Files.newInputStream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.primefaces.model.file.UploadedFile;

import com.exasol.csv.view.file.ColumnSeparator;

@ExtendWith(MockitoExtension.class)
class FileConverterTest {

	private static final String DEFAULT_FILE_NAME = "basic_file.csv";
	private static final String SEMICOLONS_MOCK_FILE = "semicolons_file.csv";
	private static final String TABSTOPS_MOCK_FILE = "tabstops_file.csv";
	private static final List<String> EXPECTED_COLUMN_NAMES = List.of("seq","first","last","age","gender","birthday");
	private static final List<List<String>> EXPECTED_VALUES = getExpectedValues();
	
	@Mock private UploadedFile uploadedFile;
	private CSVFile csvFile;
	
	private FileConverter fileConverter;
	
	private UploadOptions uploadOptions;
	
	public FileConverterTest() {
		this.fileConverter = new FileConverter();
		this.uploadOptions = new UploadOptions();
	}
	
	private static List<List<String>> getExpectedValues() {
		var expectedValues = new ArrayList<List<String>>();
		expectedValues.add(List.of("1","Beulah","Ingram","29","Female","12/30/1965"));
		expectedValues.add(List.of("2","Noah","Joseph","35","Male","3/24/1963"));
		expectedValues.add(List.of("3","Lily","Oliver","45","Female","2/27/2003"));
		return expectedValues;
	}
	
	@AfterEach
	void checkMocksWereCalled() throws IOException {
		verify(this.uploadedFile).getFileName();
		verify(this.uploadedFile).getInputStream();
	}

	@Test
	void testDefaultInputsConverter() throws IOException {
		givenSomeFileUploadInput();
		whenExtractingTheFileColumns();
		expectFilenameIsExtracted();
		expectColumnNamesAndRowsAreExtracted();
	}

	private void givenSomeFileUploadInput() throws IOException {
		setupMockFile(DEFAULT_FILE_NAME);
	}

	private void setupMockFile(String filename) throws IOException {
		when(this.uploadedFile.getFileName()).thenReturn(filename);
		when(this.uploadedFile.getInputStream()).thenReturn(newInputStream(Paths.get("src", "test", "resources", filename)));
	}

	private void whenExtractingTheFileColumns() {
		this.csvFile = this.fileConverter.convert(this.uploadedFile, this.uploadOptions);
	}

	private void expectFilenameIsExtracted() {
		assertThat(this.csvFile.getFilename()).isEqualTo(DEFAULT_FILE_NAME);
	}

	private void expectColumnNamesAndRowsAreExtracted(){
		assertThat(this.csvFile.getColumnNames()).containsExactlyInAnyOrderElementsOf(EXPECTED_COLUMN_NAMES);
		assertThat(this.csvFile.getRows()).containsExactlyInAnyOrderElementsOf(EXPECTED_VALUES);
	}

	@Test
	void testSemicolonsDelimetedConverter() throws IOException {
		givenSomeFileUploadInputDelimetedBySemiColons();
		givenSemicolonsAsColumnSeparator();
		whenExtractingTheFileColumns();
		expectColumnNamesAndRowsAreExtracted();
	}

	private void givenSomeFileUploadInputDelimetedBySemiColons() throws IOException {
		setupMockFile(SEMICOLONS_MOCK_FILE);
	}

	private void givenSemicolonsAsColumnSeparator() {
		this.uploadOptions.setColumnSeparator(ColumnSeparator.SEMICOLON);
	}

	@Test
	void testTabstopsDelimetedConverter() throws IOException {
		givenSomeFileUploadInputDelimetedByTabstops();
		givenTabstopsAsColumnSeparator();
		whenExtractingTheFileColumns();
		expectColumnNamesAndRowsAreExtracted();
	}

	private void givenSomeFileUploadInputDelimetedByTabstops() throws IOException {
		setupMockFile(TABSTOPS_MOCK_FILE);
	}

	private void givenTabstopsAsColumnSeparator() {
		this.uploadOptions.setColumnSeparator(ColumnSeparator.TAB_STOPS);
	}
}

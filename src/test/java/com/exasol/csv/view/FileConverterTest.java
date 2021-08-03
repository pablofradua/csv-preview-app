package com.exasol.csv.view;

import static java.nio.file.Files.newInputStream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.primefaces.model.file.UploadedFile;

import com.exasol.csv.service.CSVFile;
import com.exasol.csv.service.CSVFileColumn;
import com.exasol.csv.service.CouldNotReadFileException;
import com.exasol.csv.service.FileConverter;
import com.exasol.csv.view.upload_options.ColumnSeparator;
import com.exasol.csv.view.upload_options.HeaderOrigin;
import com.exasol.csv.view.upload_options.StringDelimiter;
import com.exasol.csv.view.upload_options.UploadOptions;

@ExtendWith(MockitoExtension.class)
class FileConverterTest {

	private static final String DEFAULT_FILE = "basic_file.csv";
	private static final String WINDOWS_FILE = "basic_file_windows.csv";
	private static final String MAC_FILE = "basic_file_mac.csv";
	private static final String SEMICOLONS_MOCK_FILE = "semicolons_file.csv";
	private static final String TABSTOPS_MOCK_FILE = "tabstops_file.csv";
	private static final String SINGLE_QUOTES_FILE = "single_quotes_file.csv";
	private static final String ANSI_FILE = "basic_file_ansi.csv";
	private static final String NO_HEADER_FILE = "no_header_file.csv";

	private static final List<String> EXPECTED_COLUMN_NAMES = List.of("age","birthday","first","gender","last","seq");
	private static final List<String> EXPECTED_GENERATED_COLUMN_NAMES = List.of("Column 1","Column 2","Column 3","Column 4","Column 5","Column 6");
	private static final List<List<String>> EXPECTED_VALUES = getExpectedValues();
	private static final List<List<String>> EXPECTED_ANSI_VALUES = getExpectedAnsiValues();
	
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
	
	private static List<List<String>> getExpectedAnsiValues() {
		var expectedValues = new ArrayList<List<String>>();
		expectedValues.add(List.of("1","Beulah","Ingram ñ","29","Female","12/30/1965"));
		return expectedValues;
	}
	
	@BeforeEach
	void initUploadOptions() {
		this.uploadOptions = new UploadOptions();
	}
	
	@AfterEach
	void checkMocksWereCalled() throws IOException{
		verify(this.uploadedFile).getFileName();
		verify(this.uploadedFile).getInputStream();
	}

	@Test
	void testDefaultInputsConverter(){
		givenSomeFileUploadInput();
		whenExtractingTheFileColumns();
		expectFilenameIsExtracted();
		expectColumnNamesAndRowsAreExtracted();
	}

	private void givenSomeFileUploadInput(){
		setupMockFile(DEFAULT_FILE);
	}

	private void setupMockFile(String filename) {
		when(this.uploadedFile.getFileName()).thenReturn(filename);
		try {
			when(this.uploadedFile.getInputStream()).thenReturn(newInputStream(Paths.get("src", "test", "resources", filename)));
		}catch(IOException e) {
			throw new CantSetupMockFileException(filename, e);
		}
	}

	private void whenExtractingTheFileColumns() {
		try {
			this.csvFile = this.fileConverter.convert(this.uploadedFile.getFileName(), this.uploadedFile.getInputStream(), this.uploadOptions);
		} catch (IOException e) {
			throw new CouldNotReadFileException(e);
		}
	}

	private void expectFilenameIsExtracted() {
		assertThat(this.csvFile.getFilename()).isEqualTo(DEFAULT_FILE);
	}

	private void expectColumnNamesAndRowsAreExtracted(){
		assertThat(this.csvFile.getColumns().stream().map(CSVFileColumn::getColumnName)).containsExactlyElementsOf(EXPECTED_COLUMN_NAMES);
		assertThat(this.csvFile.getRows()).containsExactlyElementsOf(EXPECTED_VALUES);
	}

	@Test
	void testSemicolonsDelimetedConverter(){
		givenSomeFileUploadInputDelimetedBySemiColons();
		givenSemicolonsAsColumnSeparator();
		whenExtractingTheFileColumns();
		expectColumnNamesAndRowsAreExtracted();
	}

	private void givenSomeFileUploadInputDelimetedBySemiColons(){
		setupMockFile(SEMICOLONS_MOCK_FILE);
	}

	private void givenSemicolonsAsColumnSeparator() {
		this.uploadOptions.setColumnSeparator(ColumnSeparator.SEMICOLON);
	}

	@Test
	void testTabstopsDelimetedConverter(){
		givenSomeFileUploadInputDelimetedByTabstops();
		givenTabstopsAsColumnSeparator();
		whenExtractingTheFileColumns();
		expectColumnNamesAndRowsAreExtracted();
	}

	private void givenSomeFileUploadInputDelimetedByTabstops(){
		setupMockFile(TABSTOPS_MOCK_FILE);
	}

	private void givenTabstopsAsColumnSeparator() {
		this.uploadOptions.setColumnSeparator(ColumnSeparator.TAB_STOPS);
	}

	@Test
	void testWindowsFile(){
		givenSomeWindowsFile();
		whenExtractingTheFileColumns();
		expectColumnNamesAndRowsAreExtracted();
	}

	private void givenSomeWindowsFile(){
		setupMockFile(WINDOWS_FILE);
	}

	@Test
	void testMacFile(){
		givenSomeMacFile();
		whenExtractingTheFileColumns();
		expectColumnNamesAndRowsAreExtracted();
	}

	private void givenSomeMacFile(){
		setupMockFile(MAC_FILE);
	}

	@Test
	void testSingleQuotesFile(){
		givenSomeSingleQuotesFile();
		givenSingleQuotesAsStringDelimeter();
		whenExtractingTheFileColumns();
		expectColumnNamesAndRowsAreExtracted();
	}

	private void givenSomeSingleQuotesFile() {
		setupMockFile(SINGLE_QUOTES_FILE);
	}

	private void givenSingleQuotesAsStringDelimeter() {
		this.uploadOptions.setStringDelimiter(StringDelimiter.SINGLE_QUOTES);
	}

	@Test
	void testAnsiFile(){
		givenAnAnsiFile();
		givenAnsiAsCharset();
		whenExtractingTheFileColumns();
		expectAnsiColumnNamesAndRowsAreExtracted();
	}

	private void givenAnAnsiFile() {
		setupMockFile(ANSI_FILE);
	}

	private void givenAnsiAsCharset() {
		this.uploadOptions.setCharset(StandardCharsets.ISO_8859_1);
	}

	private void expectAnsiColumnNamesAndRowsAreExtracted() {
		assertThat(this.csvFile.getColumns().stream().map(CSVFileColumn::getColumnName)).containsExactlyElementsOf(EXPECTED_COLUMN_NAMES);
		assertThat(this.csvFile.getRows()).containsExactlyElementsOf(EXPECTED_ANSI_VALUES);
	}

	@Test
	void testFirstRowContainsData(){
		givenANoHeaderFile();
		givenFirstRowContainsData();
		whenExtractingTheFileColumns();
		expectColumnNamesAreGenerated();
		expectRowsAreExtracted();
	}

	private void givenANoHeaderFile() {
		setupMockFile(NO_HEADER_FILE);
	}

	private void givenFirstRowContainsData() {
		this.uploadOptions.setHeaderOrigin(HeaderOrigin.AUTO);
	}

	private void expectColumnNamesAreGenerated() {
		assertThat(this.csvFile.getColumns().stream().map(CSVFileColumn::getColumnName)).containsExactlyElementsOf(EXPECTED_GENERATED_COLUMN_NAMES);
	}

	private void expectRowsAreExtracted() {
		assertThat(this.csvFile.getRows()).containsExactlyElementsOf(EXPECTED_VALUES);
	}

}

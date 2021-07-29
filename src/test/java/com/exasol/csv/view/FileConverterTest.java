package com.exasol.csv.view;

import static java.nio.file.Files.newInputStream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.primefaces.model.file.UploadedFile;

@ExtendWith(MockitoExtension.class)
class FileConverterTest {

	private static final String MY_MOCK_FILE_NAME = "my_mock_file.csv";
	private static final List<String> EXPECTED_COLUMN_NAMES = List.of("seq","first","last","age","gender","birthday");
	private static final List<List<String>> EXPECTED_VALUES = getExpectedValues();
	
	@Mock private UploadedFile uploadedFile;
	private CSVFile csvFile;
	
	private FileConverter fileConverter;
	
	private UploadOptions uploadOptions;
	
	public FileConverterTest() {
		this.fileConverter = new FileConverter();
	}
	
	private static List<List<String>> getExpectedValues() {
		var expectedValues = new ArrayList<List<String>>();
		expectedValues.add(List.of("1","Beulah","Ingram","29","Female","12/30/1965"));
		expectedValues.add(List.of("2","Noah","Joseph","35","Male","3/24/1963"));
		expectedValues.add(List.of("3","Lily","Oliver","45","Female","2/27/2003"));
		return expectedValues;
	}

	@Test
	void testFileToLists() throws IOException {
		givenSomeFileUploadInput();
		givenDefaultOptions();
		whenExtractingTheFileColumns();
		expectTheFileColumnsAreExtracted();
	}

	private void givenSomeFileUploadInput() throws IOException {
		when(this.uploadedFile.getFileName()).thenReturn(MY_MOCK_FILE_NAME);
		when(this.uploadedFile.getInputStream()).thenReturn(newInputStream(Paths.get("src", "test", "resources", MY_MOCK_FILE_NAME)));
	}

	private void givenDefaultOptions() {
		this.uploadOptions = new UploadOptions();
	}

	private void whenExtractingTheFileColumns() throws IOException {
		this.csvFile = this.fileConverter.convert(this.uploadedFile, this.uploadOptions);
	}

	private void expectTheFileColumnsAreExtracted() throws IOException {
		assertThat(this.csvFile.getFilename()).isEqualTo(MY_MOCK_FILE_NAME);
		assertThat(this.csvFile.getColumnNames()).containsExactlyInAnyOrderElementsOf(EXPECTED_COLUMN_NAMES);
		assertThat(this.csvFile.getRows()).containsExactlyInAnyOrderElementsOf(EXPECTED_VALUES);
		verify(this.uploadedFile).getFileName();
		verify(this.uploadedFile).getInputStream();
	}
}

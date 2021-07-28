package com.exasol.csv.view;

import static java.nio.file.Files.newInputStream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.primefaces.model.file.UploadedFile;

@ExtendWith(MockitoExtension.class)
class FileConverterTest {

	private static final String MY_MOCK_FILE_CSV = "my_mock_file.csv";
	private static final List<String> EXPECTED_COLUMN_NAMES = List.of("seq","first","last","age","gender","birthday");
	
	@Mock private UploadedFile uploadedFile;
	private FileDataPanel fileDataPanel;
	
	private FileConverter fileConverter;
	
	public FileConverterTest() {
		this.fileConverter = new FileConverter();
	}
	
	@Test
	void testFileToLists() throws IOException {
		givenSomeFileUploadInput();
		whenExtractingTheFileColumns();
		expectTheFileColumnsAreExtracted();
	}

	private void givenSomeFileUploadInput() throws IOException {
		when(this.uploadedFile.getFileName()).thenReturn(MY_MOCK_FILE_CSV);
		when(this.uploadedFile.getInputStream()).thenReturn(newInputStream(Paths.get("src", "test", "resources", MY_MOCK_FILE_CSV)));
	}

	private void whenExtractingTheFileColumns() throws IOException {
		this.fileDataPanel = this.fileConverter.convert(this.uploadedFile);
	}

	private void expectTheFileColumnsAreExtracted() throws IOException {
		assertThat(this.fileDataPanel.getColumnNames()).containsExactlyInAnyOrderElementsOf(EXPECTED_COLUMN_NAMES);
		verify(this.uploadedFile).getFileName();
		verify(this.uploadedFile).getInputStream();
	}
}

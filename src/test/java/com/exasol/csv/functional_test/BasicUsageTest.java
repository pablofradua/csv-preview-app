package com.exasol.csv.functional_test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Configuration;

class BasicUsageTest {
	
	@BeforeAll
	static void setUpConfiguration() {
		Configuration.browser = "firefox";
		Configuration.headless = true;
	}

	@Test
	void whenUploadingAFileTheContentsShouldBeLoadedInTheDatatable() {
		whenInTheIndexPage();
		whenUploadingAFile();
		expectAllRecordsAreLoaded();
	}

	private void whenInTheIndexPage() {
		open("http://localhost:8080/csv-preview-app");
	}

	private void whenUploadingAFile() {
		Path testFileLocation = Paths.get("src", "test", "resources", "basic_file.csv");
		$(By.cssSelector("[type='file']")).uploadFile(testFileLocation.toFile());
		$(By.className("ui-fileupload-upload")).click();
	}

	private void expectAllRecordsAreLoaded() {
		int datatableRecordsSize = $(By.id("table-form:table_data")).findAll(By.tagName("tr")).size();
		assertThat(datatableRecordsSize).isEqualTo(3);
	}
}

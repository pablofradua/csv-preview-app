package com.exasol.csv.functional_test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.exasol.csv.functional_test.selector.ClassName.UPLOAD_BUTTON;
import static com.exasol.csv.functional_test.selector.CssSelector.INPUT_FILE;
import static com.exasol.csv.functional_test.selector.Id.DATATABLE;
import static com.exasol.csv.functional_test.selector.TagName.TABLE_ROW;
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
		$(By.cssSelector(INPUT_FILE.asString())).uploadFile(testFileLocation.toFile());
		$(By.className(UPLOAD_BUTTON.asString())).click();
	}

	private void expectAllRecordsAreLoaded() {
		int datatableRecordsSize = $(By.id(DATATABLE.asString())).findAll(By.tagName(TABLE_ROW.asString())).size();
		assertThat(datatableRecordsSize).isEqualTo(3);
	}
}

package com.exasol.csv.view;

import static com.exasol.csv.view.message.Messages.addErrorMessage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.util.LangUtils;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named
public class FileDataPanel implements Serializable{
	
	private final FileConverter fileConverter;

	@Getter
	@Setter
	private CSVFile csvFile;
	
	@Getter
	@Setter
	private UploadedFile uploadedFile;
	
	@Getter
	private UploadOptions uploadOptions;
	
	@Getter
	@Setter
	private ByteArrayOutputStream fileContentsBackup;
	
	@Getter
	@Setter List<List<String>> filteredRows;
	
	public FileDataPanel() {
		this.fileConverter = new FileConverter();
		this.uploadOptions = new UploadOptions();
		this.filteredRows = new ArrayList<>();
	}
	
	public boolean isFileLoaded() {
		return this.csvFile!=null;
	}
	
	public boolean isNoFileLoaded() {
		return !isFileLoaded();
	}
	
	public void handleUploadOptionChange() {
		try(InputStream fileContents = new ByteArrayInputStream(this.fileContentsBackup.toByteArray())){
			CSVFile csvFile = this.fileConverter.convert(this.csvFile.getFilename(), fileContents, this.uploadOptions);
			this.setCsvFile(csvFile);
		} catch (IOException e) {
			addErrorMessage("Could not read the file");
		} catch (CouldNotReadFileException e) {
			addErrorMessage("Could not convert the file");
		}
	}
	
	public boolean filterFile(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }

        List<String> rowValues = (List<String>) value;
        return rowValues.stream().anyMatch(rowValue->rowValue.toLowerCase().contains(filterText));
	}

}

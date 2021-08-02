package com.exasol.csv.view;

import static com.exasol.csv.view.message.Messages.addErrorMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.exasol.csv.service.CSVFile;
import com.exasol.csv.service.CouldNotReadFileException;
import com.exasol.csv.service.FileConverter;

@Named
@RequestScoped
public class UploadOptionsListener {

	private FileDataPanel fileDataPanel;
	private FileConverter fileConverter;
	
	protected UploadOptionsListener() {
		this.fileConverter = new FileConverter();
	}
	
	@Inject
	public UploadOptionsListener(FileDataPanel fileDataPanel) {
		this.fileDataPanel = fileDataPanel;
		this.fileConverter = new FileConverter();
	}
	
	public void handleUploadOptionChange() {
		try(InputStream fileContents = new ByteArrayInputStream(this.fileDataPanel.getFileContentsBackup().toByteArray())){
			CSVFile csvFile = this.fileConverter.convert(this.fileDataPanel.getCsvFile().getFilename(), fileContents, this.fileDataPanel.getUploadOptions());
			this.fileDataPanel.setCsvFile(csvFile);
		} catch (IOException e) {
			addErrorMessage("Could not read the file");
		} catch (CouldNotReadFileException e) {
			addErrorMessage("Could not convert the file");
		}
	}
}

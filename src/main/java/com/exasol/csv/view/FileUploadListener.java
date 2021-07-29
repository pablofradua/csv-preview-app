package com.exasol.csv.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

@Named
@RequestScoped
public class FileUploadListener {

	private final FileDataPanel fileDataPanel;
	private final FileConverter fileConverter;
	
	@Inject
	public FileUploadListener(FileDataPanel fileDataPanel) {
		this.fileDataPanel = fileDataPanel;
		this.fileConverter = new FileConverter();
	}
	
	public void handleFileUpload(FileUploadEvent fileUploadEvent) {
		CSVFile csvFile = this.fileConverter.convert(fileUploadEvent.getFile(), this.fileDataPanel.getUploadOptions());
		this.fileDataPanel.setCsvFile(csvFile);
	}
}

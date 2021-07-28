package com.exasol.csv.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

@Named
@RequestScoped
public class FileUploadListener {

	private final FileDataPanel fileDataPanel;
	
	@Inject
	public FileUploadListener(FileDataPanel fileDataPanel) {
		this.fileDataPanel = fileDataPanel;
	}
	
	public void handleFileUpload(FileUploadEvent fileUploadEvent) {
		fileDataPanel.setFilename(fileUploadEvent.getFile().getFileName());
	}
}

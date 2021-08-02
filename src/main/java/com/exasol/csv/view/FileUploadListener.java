package com.exasol.csv.view;

import static com.exasol.csv.view.message.Messages.addErrorMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import com.exasol.csv.service.CSVFile;
import com.exasol.csv.service.CouldNotCloneFileContentsException;
import com.exasol.csv.service.CouldNotReadFileException;
import com.exasol.csv.service.FileConverter;

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
		try{
			ByteArrayOutputStream fileContentsBackup = this.backupFileContents(fileUploadEvent.getFile().getInputStream());
			this.fileDataPanel.setFileContentsBackup(fileContentsBackup);
			CSVFile csvFile = this.fileConverter.convert(fileUploadEvent.getFile().getFileName(), fileUploadEvent.getFile().getInputStream(), this.fileDataPanel.getUploadOptions());
			this.fileDataPanel.setCsvFile(csvFile);
		} catch (IOException e) {
			addErrorMessage("Could not read the file");
		} catch (CouldNotReadFileException e) {
			addErrorMessage("Could not convert the file");
		}
	}

	private ByteArrayOutputStream backupFileContents(InputStream fileContents) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try{
			fileContents.transferTo(byteArrayOutputStream);
		} catch (IOException e) {
			throw new CouldNotCloneFileContentsException(e);
		}
		return byteArrayOutputStream;
	}
}

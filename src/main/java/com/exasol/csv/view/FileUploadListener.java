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

/**
 * Listener that reacts to the file upload action, reading and parsing the CSV file
 * @author pfradua
 *
 */
@Named
@RequestScoped
public class FileUploadListener {

	private final CSVPreview csvPreview;
	private final FileConverter fileConverter;
	
	@Inject
	public FileUploadListener(CSVPreview csvPreview, FileConverter fileConverter) {
		this.csvPreview = csvPreview;
		this.fileConverter = fileConverter;
	}
	
	public void handleFileUpload(FileUploadEvent fileUploadEvent) {
		try{
			ByteArrayOutputStream fileContentsBackup = this.backupFileContents(fileUploadEvent.getFile().getInputStream());
			this.csvPreview.setFileContentsBackup(fileContentsBackup);
			CSVFile csvFile = this.fileConverter.convert(fileUploadEvent.getFile().getFileName(), fileUploadEvent.getFile().getInputStream(), this.csvPreview.getUploadOptions());
			this.csvPreview.setCsvFile(csvFile);
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

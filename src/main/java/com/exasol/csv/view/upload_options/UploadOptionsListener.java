package com.exasol.csv.view.upload_options;

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
import com.exasol.csv.view.CSVPreview;

/**
 * Listener that reacts to changes in the file upload configuration, refreshing the datatable according to them.
 * @author pfradua
 *
 */
@Named
@RequestScoped
public class UploadOptionsListener {

	private CSVPreview csvPreview;
	private FileConverter fileConverter;
	
	protected UploadOptionsListener() {
		super();
	}
	
	@Inject
	public UploadOptionsListener(CSVPreview csvPreview, FileConverter fileConverter) {
		this.csvPreview = csvPreview;
		this.fileConverter = fileConverter;
	}
	
	public void handleUploadOptionChange() {
		try(InputStream fileContents = new ByteArrayInputStream(this.csvPreview.getFileContentsBackup().toByteArray())){
			CSVFile csvFile = this.fileConverter.convert(this.csvPreview.getCsvFile().getFilename(), fileContents, this.csvPreview.getUploadOptions());
			this.csvPreview.setCsvFile(csvFile);
		} catch (IOException e) {
			addErrorMessage("Could not read the file");
		} catch (CouldNotReadFileException e) {
			addErrorMessage("Could not convert the file");
		}
	}
}

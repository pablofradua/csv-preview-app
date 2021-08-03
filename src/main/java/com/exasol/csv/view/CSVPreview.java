package com.exasol.csv.view;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import com.exasol.csv.service.CSVFile;
import com.exasol.csv.view.upload_options.UploadOptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Backing bean for the index.xhtml page, mainly a data class.
 * @author pfradua
 *
 */
@ViewScoped
@Named("csvPreview")
public class CSVPreview implements Serializable{
	
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
	@Setter 
	private List<List<String>> filteredRows;
	
	public CSVPreview() {
		this.uploadOptions = new UploadOptions();
		this.filteredRows = new ArrayList<>();
	}
	
	public boolean isFileLoaded() {
		return this.csvFile!=null;
	}
	
	public boolean isNoFileLoaded() {
		return !isFileLoaded();
	}

}

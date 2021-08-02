package com.exasol.csv.view;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import com.exasol.csv.service.CSVFile;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named
public class FileDataPanel implements Serializable{
	
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

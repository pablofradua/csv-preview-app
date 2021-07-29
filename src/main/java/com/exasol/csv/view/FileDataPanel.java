package com.exasol.csv.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

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
	
	public boolean isFileLoaded() {
		return this.csvFile!=null;
	}
	
	public boolean isNoFileLoaded() {
		return !isFileLoaded();
	}
}

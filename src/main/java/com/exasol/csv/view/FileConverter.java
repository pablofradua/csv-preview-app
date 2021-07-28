package com.exasol.csv.view;

import org.primefaces.model.file.UploadedFile;

public class FileConverter {

	public FileDataPanel convert(UploadedFile uploadedFile) {
		return new FileDataPanel();
	}

}

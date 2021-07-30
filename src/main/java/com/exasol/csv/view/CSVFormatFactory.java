package com.exasol.csv.view;

import org.apache.commons.csv.CSVFormat;

import com.exasol.csv.view.file.HeaderOrigin;

class CSVFormatFactory {

	public CSVFormat getInstance(UploadOptions uploadOptions) {
		if (uploadOptions.getHeaderOrigin() == HeaderOrigin.FIRST_ROW) {
			return CSVFormat.EXCEL
					.withDelimiter(uploadOptions.getColumnSeparator().getValue())
					.withQuote(uploadOptions.getStringDelimeter().getValue())
					.withFirstRecordAsHeader();
		}else {
			return CSVFormat.EXCEL
					.withDelimiter(uploadOptions.getColumnSeparator().getValue())
					.withQuote(uploadOptions.getStringDelimeter().getValue());
		}
	}
}

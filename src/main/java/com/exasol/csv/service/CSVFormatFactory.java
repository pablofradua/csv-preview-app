package com.exasol.csv.service;

import org.apache.commons.csv.CSVFormat;

import com.exasol.csv.view.upload_options.HeaderOrigin;
import com.exasol.csv.view.upload_options.UploadOptions;

/**
 * Creates {@link CSVFormat} instances
 * @author pfradua
 *
 */
class CSVFormatFactory {

	/**
	 * Returns a {@link CSVFormat} instance, applying a set of {@link UploadOptions}
	 * @param uploadOptions
	 * @return a new {@link CSVFormat} instance
	 */
	public CSVFormat getInstance(UploadOptions uploadOptions) {
		if (uploadOptions.getHeaderOrigin() == HeaderOrigin.FIRST_ROW) {
			return CSVFormat.EXCEL
					.withIgnoreEmptyLines()
					.withDelimiter(uploadOptions.getColumnSeparator().getValue())
					.withQuote(uploadOptions.getStringDelimiter().getValue())
					.withFirstRecordAsHeader();
		}else {
			return CSVFormat.EXCEL
					.withIgnoreEmptyLines()
					.withDelimiter(uploadOptions.getColumnSeparator().getValue())
					.withQuote(uploadOptions.getStringDelimiter().getValue());
		}
	}
}

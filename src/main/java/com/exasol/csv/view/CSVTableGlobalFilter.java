package com.exasol.csv.view;

import java.util.List;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.util.LangUtils;

@Named("csvTableGlobalFilter")
@RequestScoped
public class CSVTableGlobalFilter {

	private final FileDataPanel fileDataPanel;

	@Inject
	public CSVTableGlobalFilter(FileDataPanel fileDataPanel) {
		this.fileDataPanel = fileDataPanel;
	}

	public boolean filterFile(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (LangUtils.isValueBlank(filterText)) {
			return true;
		}

		List<String> rowValues = (List<String>) value;
		return rowValues.stream().anyMatch(rowValue -> rowValue.toLowerCase().contains(filterText));
	}
}

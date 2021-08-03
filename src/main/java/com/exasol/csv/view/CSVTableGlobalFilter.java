package com.exasol.csv.view;

import java.util.List;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.util.LangUtils;

@Named("csvTableGlobalFilter")
@RequestScoped
public class CSVTableGlobalFilter {

	public boolean filterFile(List<String> rowValues, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (LangUtils.isValueBlank(filterText)) {
			return true;
		}

		return rowValues.stream().anyMatch(rowValue -> rowValue.toLowerCase().contains(filterText));
	}
}

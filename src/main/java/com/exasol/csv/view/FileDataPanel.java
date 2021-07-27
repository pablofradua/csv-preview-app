package com.exasol.csv.view;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;
import java.util.stream.IntStream;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Getter;

@ViewScoped
@Named
public class FileDataPanel implements Serializable{

	@Getter
	private List<String> columnNames;

	@Getter
	private List<List<String>> fileRows;

	public FileDataPanel() {
		this.columnNames = getDummyColumnNames();
		this.fileRows = getDummyFileRows();
	}

	private List<String> getDummyColumnNames() {		
		return IntStream.range(0, 3).mapToObj(i->"Column_"+i).collect(toList());
	}

	private List<List<String>> getDummyFileRows() {		
		return IntStream.rangeClosed(0, 100).mapToObj(i->List.of("Value_"+i+"_0", "Value_"+i+"_1", "Value_"+i+"_2")).collect(toList());
	}
}

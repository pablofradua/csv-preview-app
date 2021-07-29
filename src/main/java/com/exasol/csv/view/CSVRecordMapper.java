package com.exasol.csv.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

/**
 * Maps a org.apache.commons.csv.CSVRecord values to a List<String>
 * @author pablo
 *
 */
class CSVRecordMapper {

	public List<String> toList(CSVRecord csvRecord) {
		var lineValues = new ArrayList<String>();
		csvRecord.forEach(lineValues::add);
		return lineValues;
	}

}

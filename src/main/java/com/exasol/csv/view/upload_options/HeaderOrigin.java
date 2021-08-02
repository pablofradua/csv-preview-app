package com.exasol.csv.view.upload_options;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Shows the first row's purpose, header for the next rows or data itself
 * @author pablo
 *
 */
public enum HeaderOrigin {

	FIRST_ROW {

		@Override
		public List<String> getHeader(List<List<String>> rows) {
			return rows.get(0);
		}
		
	},
	AUTO {

		@Override
		public List<String> getHeader(List<List<String>> rows) {
			return IntStream.rangeClosed(1,rows.get(0).size()).mapToObj(i->"Column "+i).collect(toList());
		}
		
	};
	
	public abstract List<String> getHeader(List<List<String>> rows);
}

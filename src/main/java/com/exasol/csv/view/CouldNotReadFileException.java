package com.exasol.csv.view;

import java.io.IOException;

public class CouldNotReadFileException extends RuntimeException {

	public CouldNotReadFileException(IOException e) {
		super(e);
	}

}

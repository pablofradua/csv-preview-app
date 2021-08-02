package com.exasol.csv.service;

import java.io.IOException;

public class CouldNotReadFileException extends RuntimeException {

	public CouldNotReadFileException(IOException e) {
		super(e);
	}

}

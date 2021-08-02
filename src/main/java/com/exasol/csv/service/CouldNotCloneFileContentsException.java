package com.exasol.csv.service;

import java.io.IOException;

public class CouldNotCloneFileContentsException extends RuntimeException {

	public CouldNotCloneFileContentsException(IOException e) {
		super(e);
	}

}

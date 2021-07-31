package com.exasol.csv.view;

import java.io.IOException;

public class CouldNotCloneFileContentsException extends RuntimeException {

	public CouldNotCloneFileContentsException(IOException e) {
		super(e);
	}

}

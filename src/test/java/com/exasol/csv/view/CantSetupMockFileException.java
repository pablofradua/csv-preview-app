package com.exasol.csv.view;

import java.io.IOException;
import java.text.MessageFormat;

public class CantSetupMockFileException extends RuntimeException {

	private static final MessageFormat ERROR_MESSAGE = new MessageFormat("Can''t setup the \"{0}\" mock file");

	public CantSetupMockFileException(String filename, IOException e) {
		super(getErrorMessage(filename), e);
	}

	private static String getErrorMessage(String filename) {
		return ERROR_MESSAGE.format(new Object[]{filename});
	}

}

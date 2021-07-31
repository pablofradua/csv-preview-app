package com.exasol.csv.view.message;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class Messages {

	private Messages() {
		super();
	}

	public static void addErrorMessage(String summary) {
		var errorMessage = new FacesMessage(summary);
		errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, errorMessage);
	}
}

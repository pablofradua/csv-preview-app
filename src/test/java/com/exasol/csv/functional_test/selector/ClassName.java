package com.exasol.csv.functional_test.selector;

public enum ClassName implements Selector {

	UPLOAD_BUTTON("ui-fileupload-upload");
	
	private final String className;
	
	private ClassName(String className) {
		this.className = className;
	}

	@Override
	public String asString() {
		return this.className;
	}
	
}

package com.exasol.csv.view.converter;

import java.nio.charset.Charset;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Converts from {@link Charset} to {@link String}, and vice versa
 * @author pfradua
 *
 */
@FacesConverter("com.exasol.csv.view.CharsetConverter")
public class CharsetConverter implements Converter<Charset> {

	@Override
	public Charset getAsObject(FacesContext context, UIComponent component, String charsetName) {
		return Charset.availableCharsets().get(charsetName);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Charset charset) {
		return charset.name();
	}

}

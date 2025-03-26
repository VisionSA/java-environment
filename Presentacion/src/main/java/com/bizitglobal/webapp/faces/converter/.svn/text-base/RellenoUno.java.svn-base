package com.bizitglobal.webapp.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.bizitglobal.webapp.faces.util.*;


public class RellenoUno implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		return arg2;
	}


	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		String result = null;
		if (arg2.toString() != null) {
			result = Util.completar(arg2.toString(), 4);
		}

		return result;
	}

}

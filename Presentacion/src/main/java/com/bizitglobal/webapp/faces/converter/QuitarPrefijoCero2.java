package com.bizitglobal.webapp.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.bizitglobal.webapp.faces.util.Util;


public class QuitarPrefijoCero2 implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {

		return Util.completar(arg2, 2);
	}


	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		String result = null;

		if (arg2 != null && arg2 != null) {

			result = Util.quitarRelleno(arg2);
		}

		return result;
	}

}

package com.bizitglobal.webapp.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.bizitglobal.webapp.faces.util.*;


public class FechaCuponConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		if (arg2.length() == 10) {
			String anio = arg2.substring(8, 10);
			String mes = arg2.substring(3, 5);
			String dia = arg2.substring(0, 2);
			arg2 = anio + mes + dia;

		}
		return arg2;
	}


	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		String fechaCorta = null;

		if (arg2 != null && arg2.toString() != null) {
			fechaCorta = arg2.toString();
			if (fechaCorta.length() == 6) {
				String anio = fechaCorta.substring(0, 2);
				String mes = fechaCorta.substring(2, 4);
				String dia = fechaCorta.substring(4, 6);
				fechaCorta = dia + "/" + mes + "/20" + anio;
			}
		}
		return fechaCorta;
	}

}

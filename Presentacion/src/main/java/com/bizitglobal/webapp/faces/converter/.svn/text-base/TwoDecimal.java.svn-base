package com.bizitglobal.webapp.faces.converter;

import java.text.NumberFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;


public class TwoDecimal implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		return arg2;
	}


	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		String result = null;

		if (arg2.toString() != null) {
			// BigDecimal number = new BigDecimal(arg2.toString());
			// number.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			// result = "$ "+number.toString();
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);
			result = "$ " + numberFormat.format(new Double(arg2.toString()).doubleValue());
		}

		return result;
	}

}

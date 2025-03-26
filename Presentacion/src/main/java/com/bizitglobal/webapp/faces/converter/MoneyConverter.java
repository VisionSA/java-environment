package com.bizitglobal.webapp.faces.converter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;


public class MoneyConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		return arg2;
	}


	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		String result = null;

		if (arg2 != null && arg2.toString() != null) {

			NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.GERMANY);
			// NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY );
			BigDecimal amount = new BigDecimal(arg2.toString());

			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			if (new Double(arg2.toString()).doubleValue() < 0) {
				result = numberFormatter.format(amount).replace("-", "[") + "]";
			} else
				result = numberFormatter.format(amount);
			// result = "$ "+numberFormatter.format(new Double(arg2.toString()).doubleValue());
		}

		return result;
	}

}

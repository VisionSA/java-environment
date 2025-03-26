package com.bizitglobal.webapp.faces.beans.transacciones.popup;

import javax.faces.model.SelectItem;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;


public class IndividuoPopupUtil {

	public static SelectItem armarDomPago(Domicilio domicilio) {
		return new SelectItem(domicilio.getIdDomicilio(), domicilio.getCalleNombre() + " " + domicilio.getCalleNumero());
	}
}

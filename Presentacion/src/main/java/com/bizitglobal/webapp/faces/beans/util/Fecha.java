package com.bizitglobal.webapp.faces.beans.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Hernan
 * @deprecated Se debe usar la clase de el paquete commons
 */
public class Fecha {
	private static Calendar fecha = Calendar.getInstance();


	public Fecha() {
		this.fecha = Calendar.getInstance();
	}


	public Fecha(Calendar fecha) {
		super();
		this.fecha = fecha;
	}


	public static Timestamp addDias(Timestamp fechaBase, int dias) {
		fecha.setTime(new Date(fechaBase.getTime()));
		fecha.add(Calendar.DATE, dias);
		return new Timestamp(fecha.getTime().getTime());
	}

}

package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.transacciones.negocio.Origenen;




@SuppressWarnings({"rawtypes","unchecked"})
public class CodigoAutorizacionUtil {
	private Logger log = Logger.getLogger(CodigoAutorizacionUtil.class);


	public static List armarOrigen(List origenList) {
		List result = new ArrayList();
		if (!origenList.isEmpty()) {
			Iterator iterator = origenList.iterator();
			while (iterator.hasNext()) {
				Origenen element = (Origenen) iterator.next();
				result.add(new SelectItem(element.getIdOrigenes(), element.getDescripcion()));
			}
		}
		return result;
	}
}

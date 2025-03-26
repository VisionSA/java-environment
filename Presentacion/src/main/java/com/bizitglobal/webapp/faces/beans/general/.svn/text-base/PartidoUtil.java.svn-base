package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.negocio.Provincia;


@SuppressWarnings({"rawtypes","unchecked"})
public class PartidoUtil {
	private static Logger log = Logger.getLogger(PartidoUtil.class);


	public static List filtrarProvincias(List listaProvincia, Long idPais) {
		List result = new ArrayList();
		if (!listaProvincia.isEmpty()) {
			Iterator iterator = listaProvincia.iterator();
			while (iterator.hasNext()) {
				Provincia element = (Provincia) iterator.next();
				if (element.getPais().getIdPais().equals(idPais)) {
					result.add(new SelectItem(element.getIdProvincia(), element.getNombre()));
				}
			}
		}
		return result;
	}

}

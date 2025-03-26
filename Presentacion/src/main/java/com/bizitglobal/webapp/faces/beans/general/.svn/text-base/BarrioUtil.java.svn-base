package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.negocio.Localidad;
import com.bizitglobal.tarjetafiel.general.negocio.Provincia;


@SuppressWarnings({"rawtypes","unchecked"})
public class BarrioUtil {
	private Logger log = Logger.getLogger(BarrioUtil.class);


	public static List filtrarProvincias(List listaProvincia, Long idPais) {
		List result = new ArrayList();
		if (!listaProvincia.isEmpty()) {
			Iterator iterator = listaProvincia.iterator();
			while (iterator.hasNext()) {
				Provincia element = (Provincia) iterator.next();

				if (element.getPais().getIdPais().equals(idPais))
					result.add(new SelectItem(element.getIdProvincia(), element.getNombre()));
			}
		}
		return result;
	}


	public static List filtrarLocalidadesProvincia(List listaLocalidades, Long idProvinciaSeleccionada) {
		List result = new ArrayList();

		if (!listaLocalidades.isEmpty()) {
			Iterator iterator = listaLocalidades.iterator();
			while (iterator.hasNext()) {
				Localidad element = (Localidad) iterator.next();

				if (element.getProvincia().getIdProvincia().equals(idProvinciaSeleccionada))
					result.add(new SelectItem(element.getIdLocalidad(), element.getNombre()));
			}
		}
		return result;
	}
}

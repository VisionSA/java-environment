package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.general.negocio.Provincia;



@SuppressWarnings({"rawtypes","unchecked"})
public class LocalidadUtil {
	private Logger log = Logger.getLogger(LocalidadUtil.class);


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


	public static List filtrarPartido(List partidoList, Long idProvinciaSeleccionada) {
		List result = new ArrayList();

		if (!partidoList.isEmpty()) {
			Iterator iterator = partidoList.iterator();
			while (iterator.hasNext()) {
				Partido element = (Partido) iterator.next();

				if (element.getProvincia().getIdProvincia().equals(idProvinciaSeleccionada))
					result.add(new SelectItem(element.getIdPartido(), element.getDescripcion()));
			}
		}

		return result;
	}
}

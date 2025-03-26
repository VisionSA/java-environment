package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bizitglobal.tarjetafiel.general.negocio.Autonomo;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class AutonomoUtil {

	public static List cargarListaAutonomos(List list) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Autonomos"));

		if (!list.isEmpty()) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				Autonomo element = (Autonomo) iterator.next();

				SelectItem item = new SelectItem();

				item.setValue(element.getIdAutonomo());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}
}

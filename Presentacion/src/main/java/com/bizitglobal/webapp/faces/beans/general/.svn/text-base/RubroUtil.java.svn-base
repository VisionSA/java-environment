package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bizitglobal.tarjetafiel.general.negocio.Rubro;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class RubroUtil {

	public static List cargarListaRubros(List list) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Rubros"));

		if (!list.isEmpty()) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				Rubro element = (Rubro) iterator.next();

				SelectItem item = new SelectItem();

				item.setValue(element.getIdRubro());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}
}

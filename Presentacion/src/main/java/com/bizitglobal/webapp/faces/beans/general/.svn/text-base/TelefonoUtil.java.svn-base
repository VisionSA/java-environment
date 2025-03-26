package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bizitglobal.tarjetafiel.general.negocio.TipoTelefono;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class TelefonoUtil {

	public static List cargarListaTelefonos(List list) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Tipo Teléfono"));

		if (!list.isEmpty()) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				TipoTelefono element = (TipoTelefono) iterator.next();

				SelectItem item = new SelectItem();

				item.setValue(element.getIdTipoTelefono());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}
}

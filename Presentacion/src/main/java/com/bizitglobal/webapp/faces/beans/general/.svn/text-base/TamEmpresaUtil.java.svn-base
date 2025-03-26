package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bizitglobal.tarjetafiel.general.negocio.TamEmpresa;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class TamEmpresaUtil {

	public static List cargarListaTamEmpresa(List list) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Tamaño"));

		if (!list.isEmpty()) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				TamEmpresa element = (TamEmpresa) iterator.next();

				SelectItem item = new SelectItem();

				item.setValue(element.getIdTamanioEmp());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}
}

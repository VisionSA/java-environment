package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;


@SuppressWarnings({"rawtypes","unchecked"})
public class ImpuestoUtil {
	private Logger log = Logger.getLogger(ImpuestoUtil.class);


	public static List filtrarCategorias(List listaCategorias, Long idTipoImp) {
		List result = new ArrayList();
		if (!listaCategorias.isEmpty()) {
			Iterator iterator = listaCategorias.iterator();
			while (iterator.hasNext()) {
				Categoria element = (Categoria) iterator.next();
				if (element.getTipoImpuesto().getIdTipoImpuesto().equals(idTipoImp))
					result.add(new SelectItem(element.getId(), element.getLabel()));
			}
		}
		return result;
	}
}

package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;




@SuppressWarnings({"rawtypes","unchecked"})
public class ListaPrecioUtil {
	private Logger log = Logger.getLogger(ListaPrecioUtil.class);


	public static List armarSucursales(List sucursalList) {
		List result = new ArrayList();

		Iterator iterator = sucursalList.iterator();
		while (iterator.hasNext()) {
			SucursalFiel element = (SucursalFiel) iterator.next();

			result.add(new SelectItem(element.getId(), element.getNombre()));

		}
		return result;
	}
}

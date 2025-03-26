package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ListaPrecio;




@SuppressWarnings({"rawtypes","unchecked"})
public class ComercioListaPrecioUtil {
	private Logger log = Logger.getLogger(ComercioListaPrecioUtil.class);


	public static List armarCodComercio(List codComercioList) {
		List result = new ArrayList();
		Iterator iterator = codComercioList.iterator();
		while (iterator.hasNext()) {
			CodComercio element = (CodComercio) iterator.next();
			result.add(new SelectItem(element.getId(), element.getCodigo() + "-"));
		}
		return result;
	}


	public static List armarListaPrecio(List listaPrecioList) {
		List result = new ArrayList();
		Iterator iterator = listaPrecioList.iterator();
		while (iterator.hasNext()) {
			ListaPrecio element = (ListaPrecio) iterator.next();

			result.add(new SelectItem(element.getIdListaprecios(), element.getDescripcion()));
		}
		return result;
	}
}

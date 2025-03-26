package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;




@SuppressWarnings({"rawtypes","unchecked"})
public class ClienteUtil {
	private Logger log = Logger.getLogger(ClienteUtil.class);


	public static List armarIndividuo(List individuoList) {
		List result = new ArrayList();
		Iterator iterator = individuoList.iterator();
		while (iterator.hasNext()) {
			IndividuoEvaluacion element = (IndividuoEvaluacion) iterator.next();

			result.add(new SelectItem(element.getIdIndividuo(), element.getApellido() + ", " + element.getNombres()));
		}
		return result;
	}


	public static List armarSucursal(List sucursalList) {
		List result = new ArrayList();
		Iterator iterator = sucursalList.iterator();
		while (iterator.hasNext()) {
			SucursalFiel element = (SucursalFiel) iterator.next();

			result.add(new SelectItem(element.getIdSucursal(), element.getNombre()));
		}
		return result;
	}
}

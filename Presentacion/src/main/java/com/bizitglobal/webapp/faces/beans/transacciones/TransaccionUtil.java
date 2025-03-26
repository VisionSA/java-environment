package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;




@SuppressWarnings({"rawtypes","unchecked"})
public class TransaccionUtil {
	private Logger log = Logger.getLogger(TransaccionUtil.class);


	public static List armarClienteTransaccion(List clienteList) {
		List result = new ArrayList();

		Iterator iterator = clienteList.iterator();
		while (iterator.hasNext()) {
			ClienteTransaccion element = (ClienteTransaccion) iterator.next();
			element.getIndividuo().getApellido();
			element.getIndividuo().getNombres();
			String nombre = element.getIndividuo().getApellido() + ", " + element.getIndividuo().getNombres();
			result.add(new SelectItem(element.getIdCliente(), nombre));
		}
		return result;
	}


	public static List armarConcepto(List conceptoList) {
		List result = new ArrayList();
		Iterator iterator = conceptoList.iterator();
		while (iterator.hasNext()) {
			Concepto element = (Concepto) iterator.next();

			result.add(new SelectItem(element.getIdConcepto(), element.getDescripcion()));
		}
		return result;
	}
}

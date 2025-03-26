package com.bizitglobal.webapp.faces.beans.evaluacion.Util;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.PromotorTelefono;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;


@SuppressWarnings({"rawtypes"})
public class PromotorEvaluacionUtil {
	private static final Logger log = Logger.getLogger(PromotorEvaluacionUtil.class);


	public static PromotorTelefono buscarPromotorTelefono(Set set, Telefono tel) {
		PromotorTelefono result = null;
		Iterator iterador = set.iterator();
		while (iterador.hasNext()) {
			PromotorTelefono p = (PromotorTelefono) iterador.next();
			Telefono t = p.getTelefono();
			if (t.equals(tel))
				result = p;
		}

		return result;
	}


	/*
	 * Este metodo se utiliza para eliminar un telefono de la lista.
	 */
	public static Set eliminarTelefono(Set listaTelefono, Telefono idTelefono) {

		PromotorTelefono telefono = buscarPromotorTelefono(listaTelefono, idTelefono);

		if (telefono != null) {
			listaTelefono.remove(telefono);

			if (listaTelefono.isEmpty()) {
				listaTelefono = new HashSet();
			}
		}

		return listaTelefono;
	}


	public static List eliminarPromotorTelefono(List listaPromotorTelefono, Long idTelefono) {

		PromotorTelefono promotorTelefono = buscarPromotorTelefono(listaPromotorTelefono, idTelefono);

		if (promotorTelefono != null) {
			listaPromotorTelefono.remove(promotorTelefono);

			if (listaPromotorTelefono.isEmpty()) {
				listaPromotorTelefono = new ArrayList();
			}
		}

		return listaPromotorTelefono;
	}


	/*
	 * Este metodo se utiliza para buscar un objeto telefono del set Promo Telefono
	 */
	public static PromotorTelefono buscarPromotorTelefono(List listaPromotorTelefono, Long idTelefono) {
		PromotorTelefono result = null;

		if (!listaPromotorTelefono.isEmpty()) {
			Iterator iterator = listaPromotorTelefono.iterator();
			while (iterator.hasNext()) {
				PromotorTelefono element = (PromotorTelefono) iterator.next();

				if (element.getTelefono().getIdTelefono().equals(idTelefono)) {
					result = element;
				}
			}
		}

		return result;
	}

}

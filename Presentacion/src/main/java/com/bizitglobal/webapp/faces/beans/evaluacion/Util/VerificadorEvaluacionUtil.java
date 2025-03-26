package com.bizitglobal.webapp.faces.beans.evaluacion.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.VerificadorTelefono;
import com.bizitglobal.tarjetafiel.general.dao.TipoTelefonoDao;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.tarjetafiel.general.negocio.TipoTelefono;


@SuppressWarnings({"rawtypes","unchecked"})
public class VerificadorEvaluacionUtil {
	private static final Logger log = Logger.getLogger(VerificadorEvaluacionUtil.class);


	/*
	 * Este metodo se utiliza para traer todos los tipos de telefonos desde la base de datos y armar el selectitem.
	 */
	public static List cargarTipoTelefono(TipoTelefonoDao tipoTelefonoDao) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Tipo Teléfono"));

		List tipoTel = tipoTelefonoDao.listarTodos();
		if (!tipoTel.isEmpty()) {

			Iterator iterator = tipoTel.iterator();
			while (iterator.hasNext()) {
				TipoTelefono element = (TipoTelefono) iterator.next();
				SelectItem item = new SelectItem();

				item.setValue(new Integer(element.getIdTipoTelefono().intValue()));
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}


	/*
	 * Este metodo se utiliza para eliminar un telefono de la lista.
	 */
	public static List eliminarTelefono(List listaTelefono, Long idTelefono) {

		Telefono telefono = buscarTelefono(listaTelefono, idTelefono);

		if (telefono != null) {
			listaTelefono.remove(telefono);

			if (listaTelefono.isEmpty()) {
				listaTelefono = new ArrayList();
			}
		}

		return listaTelefono;
	}


	/*
	 * Este metodo se utiliza para buscar un objeto telefono
	 */
	public static Telefono buscarTelefono(List listaTelefono, Long idTelefono) {
		Telefono result = null;

		if (!listaTelefono.isEmpty()) {
			Iterator iterator = listaTelefono.iterator();
			while (iterator.hasNext()) {
				Telefono element = (Telefono) iterator.next();

				if (element.getIdTelefono().equals(idTelefono)) {
					result = element;
				}
			}
		}

		return result;
	}


	public static VerificadorTelefono buscarVerificadorTelefono(List listaVerificadorTelefono, Long idTelefono) {
		VerificadorTelefono result = null;
		if (!listaVerificadorTelefono.isEmpty()) {
			Iterator iterador = listaVerificadorTelefono.iterator();
			while (iterador.hasNext()) {
				VerificadorTelefono element = (VerificadorTelefono) iterador.next();

				if (element.getIdVerifTelefono().equals(idTelefono)) {
					result = element;
					break;
				}
			}
		}
		return result;
	}


	public static List eliminarVerificadorTelefono(List listaVerificadorTelefono, Long idTelefono) {

		VerificadorTelefono verificadorTelefono = buscarVerificadorTelefono(listaVerificadorTelefono, idTelefono);

		if (verificadorTelefono != null) {
			listaVerificadorTelefono.remove(verificadorTelefono);

			if (listaVerificadorTelefono.isEmpty()) {
				listaVerificadorTelefono = new ArrayList();
			}
		}

		return listaVerificadorTelefono;
	}

}

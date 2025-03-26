package com.bizitglobal.webapp.faces.beans.evaluacion.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ViviendaEstado;
import com.bizitglobal.tarjetafiel.general.negocio.PropietarioVivienda;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDocumento;
import com.bizitglobal.tarjetafiel.general.negocio.TipoVivienda;
import com.bizitglobal.tarjetafiel.general.negocio.TipoZona;


@SuppressWarnings({"rawtypes","unchecked"})
public class InformeParticularUtil {

	public static IndividuoDomicilio buscarDomicilio(List domicilios, Long idDomicilio) {
		IndividuoDomicilio result = null;

		if (!domicilios.isEmpty()) {
			Iterator iterator = domicilios.iterator();
			while (iterator.hasNext()) {
				IndividuoDomicilio element = (IndividuoDomicilio) iterator.next();

				if (element.getIdIndivDomicilio().equals(idDomicilio))
					result = element;
			}
		}
		return result;
	}


	public static List cargarTipoDocumento(List listaTipoDocumento) {
		List result = new ArrayList();

		result.add(new SelectItem(new Long(0), "Seleccionar Tipo Documento"));
		if (!listaTipoDocumento.isEmpty()) {
			Iterator iterator = listaTipoDocumento.iterator();
			while (iterator.hasNext()) {
				TipoDocumento element = (TipoDocumento) iterator.next();
				SelectItem item = new SelectItem(element.getIdTipoDocumento(), element.getDescripcion());
				result.add(item);
			}
		}
		return result;
	}


	public static List cargarEstadoVivienda(List listaEstadoVivienda) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Estado Vivienda"));
		if (!listaEstadoVivienda.isEmpty()) {
			Iterator iterator = listaEstadoVivienda.iterator();
			while (iterator.hasNext()) {
				ViviendaEstado element = (ViviendaEstado) iterator.next();
				SelectItem item = new SelectItem(element.getIdVivEstado(), element.getDescripcion());
				result.add(item);
			}
		}
		return result;
	}


	public static List cargarPropietarioVivienda(List listaPropietarioVivienda) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Propietario Vivienda"));
		if (!listaPropietarioVivienda.isEmpty()) {
			Iterator iterator = listaPropietarioVivienda.iterator();
			while (iterator.hasNext()) {
				PropietarioVivienda element = (PropietarioVivienda) iterator.next();
				SelectItem item = new SelectItem(element.getIdPropVivienda(), element.getDescripcion());
				result.add(item);
			}
		}
		return result;
	}


	public static List cargarTipoVivienda(List listaTipoVivienda) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Tipo Vivienda"));
		if (!listaTipoVivienda.isEmpty()) {
			Iterator iterator = listaTipoVivienda.iterator();
			while (iterator.hasNext()) {
				TipoVivienda element = (TipoVivienda) iterator.next();
				SelectItem item = new SelectItem(element.getIdTipoVivienda(), element.getDescripcion());
				result.add(item);
			}
		}
		return result;
	}


	public static List cargarZona(List listaZona) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Zona"));
		if (!listaZona.isEmpty()) {
			Iterator iterator = listaZona.iterator();
			while (iterator.hasNext()) {
				TipoZona element = (TipoZona) iterator.next();
				SelectItem item = new SelectItem(element.getIdTipoZona(), element.getDescripcion());
				result.add(item);
			}
		}
		return result;
	}


	public static List eliminarDomicilio(List listaDomicilio, Long id) {

		listaDomicilio.remove(buscarDomicilio(listaDomicilio, id));

		if (listaDomicilio.isEmpty())
			listaDomicilio = new ArrayList();

		return listaDomicilio;
	}
}

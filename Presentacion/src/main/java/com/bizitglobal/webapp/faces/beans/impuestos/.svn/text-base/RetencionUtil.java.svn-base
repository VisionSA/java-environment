package com.bizitglobal.webapp.faces.beans.impuestos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.negocio.Provincia;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisTipoImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TramosRetencionException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Aplicable;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisTipoImpuesto;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TramosRetencion;
import com.bizitglobal.tarjetafiel.impuestos.service.JurisTipoImpuestoService;
import com.bizitglobal.tarjetafiel.impuestos.service.TramosRetencionService;


@SuppressWarnings({"rawtypes","unchecked"})
public class RetencionUtil {

	public static List cargarCategorias(List categorias) {
		List result = new ArrayList();
		if (!categorias.isEmpty()) {
			Iterator iter = categorias.iterator();
			while (iter.hasNext()) {
				Categoria imp = (Categoria) iter.next();
				SelectItem item = new SelectItem();
				item.setValue(imp.getIdCategoria());
				item.setLabel(imp.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}


	/**
	 * @deprecated
	 */
	public static List cargarAplicables(List aplicables) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccione Aplicable"));
		if (!aplicables.isEmpty()) {
			Iterator iter = aplicables.iterator();
			while (iter.hasNext()) {
				Aplicable imp = (Aplicable) iter.next();
				SelectItem item = new SelectItem();
				item.setValue(imp.getIdAplicable());
				item.setLabel(imp.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}


	/**
	 * @deprecated
	 */
	public static List cargarProvincias(List provincias) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccione Provincia"));
		if (!provincias.isEmpty()) {
			Iterator iter = provincias.iterator();
			while (iter.hasNext()) {
				Provincia imp = (Provincia) iter.next();
				SelectItem item = new SelectItem();
				item.setValue(imp.getIdProvincia());
				item.setLabel(imp.getNombre());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarJurisdicciones(Long idTipoImp, JurisTipoImpuestoService jurisTipoImpuestoService) {
		List result = new ArrayList();
		Filtro filtro = new Filtro("tipoImpuesto.idTipoImpuesto", Filtro.IGUAL, idTipoImp);
		try {
			Iterator iter = jurisTipoImpuestoService.getJurisTipoImpuesto(filtro).iterator();
			while (iter.hasNext()) {
				JurisTipoImpuesto jurisTipo = (JurisTipoImpuesto) iter.next();
				result.add(new SelectItem(jurisTipo.getJurisdiccion().getIdJurisdiccion(), jurisTipo.getJurisdiccion().getDescripcion()));
			}
		} catch (JurisTipoImpuestoException e) {
			e.printStackTrace();
		}
		return result;
	}


	public static List borrarTramo(List listaTramo, Long idTramo) {
		boolean encontrado = false;

		if (!listaTramo.isEmpty()) {
			Iterator iter = listaTramo.iterator();
			while (iter.hasNext() && !encontrado) {
				TramosRetencion temp = (TramosRetencion) iter.next();
				if (temp.getIdTramoRetencion().equals(idTramo)) {
					listaTramo.remove(temp);
					encontrado = true;
				}
			}
		}

		return listaTramo;
	}


	public static void abTramos(List listaTramos, List tramos, Retencion retencion, TramosRetencionService service) {
		List listaIds = new ArrayList();

		// Grabar si cambiaron las listas.
		if (!listaTramos.isEmpty()) {
			Iterator iter = listaTramos.iterator();
			while (iter.hasNext()) {
				TramosRetencion imp = (TramosRetencion) iter.next();
				imp.setRetencion(retencion);
				listaIds.add(imp.getIdTramoRetencion()); // Agregamos el id a una lista para borrar.
				try {
					if (tramos == null || !tramos.contains(imp.getIdTramoRetencion())) {
						service.grabarTramosRetencion(imp);
					}
				} catch (TramosRetencionException e) {
					e.printStackTrace();
				}
			}
		}

		// Borrar si cambiaron las listas
		if (tramos != null && !tramos.isEmpty()) {
			Iterator iter2 = tramos.iterator();
			while (iter2.hasNext()) {
				Long id = (Long) iter2.next();

				try {
					if (!listaIds.contains(id)) {
						service.borrarTramosRetencion(id);
					}
				} catch (TramosRetencionException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static List idsTramosLeidas(Set lista) {
		List result = new ArrayList();
		if (!lista.isEmpty()) {
			Iterator iter = lista.iterator();
			while (iter.hasNext()) {
				TramosRetencion temp = (TramosRetencion) iter.next();
				result.add(temp.getIdTramoRetencion());
			}
		}

		return result;
	}


	public static void borrarTramos(Set lista, TramosRetencionService service) {
		if (!lista.isEmpty()) {
			Iterator iter = lista.iterator();
			while (iter.hasNext()) {
				TramosRetencion temp = (TramosRetencion) iter.next();
				try {
					service.borrarTramosRetencion(temp.getIdTramoRetencion());
				} catch (TramosRetencionException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

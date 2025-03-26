package com.bizitglobal.webapp.faces.beans.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.FlujoTabla;
import com.bizitglobal.workflow.exception.ProcesoAtributoException;
import com.bizitglobal.workflow.exception.TareaException;
import com.bizitglobal.workflow.negocio.AtributoProcesoTarea;
import com.bizitglobal.workflow.negocio.OperadorCondicion;
import com.bizitglobal.workflow.negocio.ProcesoAtributo;
import com.bizitglobal.workflow.negocio.Tarea;
import com.bizitglobal.workflow.negocio.TareaProceso;
import com.bizitglobal.workflow.negocio.TipoCondicion;
import com.bizitglobal.workflow.service.ProcesoAtributoService;
import com.bizitglobal.workflow.service.TareaService;


@SuppressWarnings({"rawtypes","unchecked"})
public class FlujosUtil {
	private static final Logger log = Logger.getLogger(FlujosUtil.class);


	public static List cargarListaTareas(TareaService tareaService, List tareaProcesoList, String tituloTarea) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccione Tarea"));
		Iterator tareaProcesoIter = tareaProcesoList.iterator();
		while (tareaProcesoIter.hasNext()) {
			TareaProceso tareaProceso = (TareaProceso) tareaProcesoIter.next();
			try {
				Tarea tarea = tareaService.leerTarea(tareaProceso.getIdTarea());
				if (!tarea.getTitulo().equals(new String(tituloTarea))) {
					result.add(new SelectItem(tareaProceso.getIdTareaProceso(), tarea.getTitulo()));
				}
			} catch (TareaException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public static List cargarListaTiposCondiciones(List tipoCondList) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccione condición"));
		Iterator tipoCondicionIter = tipoCondList.iterator();
		while (tipoCondicionIter.hasNext()) {
			TipoCondicion tipoCondicion = (TipoCondicion) tipoCondicionIter.next();
			result.add(new SelectItem(tipoCondicion.getIdTipoCondicion(), tipoCondicion.getDescripcion()));
		}
		return result;
	}


	public static List recargarLista(List tareaFlujosList, Long idFlujoTab) {
		if (!tareaFlujosList.isEmpty()) {
			Iterator tareaFlujoIter = tareaFlujosList.iterator();
			while (tareaFlujoIter.hasNext()) {
				FlujoTabla flujoTabla = (FlujoTabla) tareaFlujoIter.next();
				if (flujoTabla.getFlujo().getIdFlujo().equals(idFlujoTab)) {
					tareaFlujosList.remove(flujoTabla);
				}
			}
		}
		return tareaFlujosList;
	}


	public static List traerAtributos(ProcesoAtributoService procesoAtributoService, List atributoProcesoTareaList) {
		log.info("Ejecutando --> getProcesosAtributos()");
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Atributo"));
		Iterator atributoProcesoTareaIter = atributoProcesoTareaList.iterator();

		while (atributoProcesoTareaIter.hasNext()) {
			AtributoProcesoTarea atributoProcesoTarea = (AtributoProcesoTarea) atributoProcesoTareaIter.next();
			log.info(atributoProcesoTarea.toString());

			try {
				// Filtro filtro = new Filtro(AtributoProcesoTarea.ID_PROCESO_ATRIBUTO, Filtro.IGUAL, atributoProcesoTarea.getIdProcesoAtributo());
				List procesoAtributoList = procesoAtributoService.listarProcesoAtributo(
						new Filtro(AtributoProcesoTarea.ID_PROCESO_ATRIBUTO, Filtro.IGUAL, atributoProcesoTarea.getIdProcesoAtributo()));
				Iterator procesoAtributoIter = procesoAtributoList.iterator();
				while (procesoAtributoIter.hasNext()) {
					ProcesoAtributo procesoAtributo = (ProcesoAtributo) procesoAtributoIter.next();
					result.add(new SelectItem(procesoAtributo.getIdProcesoAtributo(), procesoAtributo.getNombre()));
				}
			} catch (ProcesoAtributoException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public static List traerParetesis(String[] parentesis) {
		List result = new ArrayList();
		for (int i = 0; i < parentesis.length; i++) {
			result.add(new SelectItem(new Integer(i), parentesis[i]));
		}
		return result;
	}


	public static List cargarCondiciones(List condiciones) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Condición"));
		if (!condiciones.isEmpty()) {
			Iterator iterator = condiciones.iterator();
			while (iterator.hasNext()) {
				OperadorCondicion element = (OperadorCondicion) iterator.next();
				result.add(new SelectItem(element.getIdOperadorCondicion(), element.getDescripcion()));
			}
		}
		return result;
	}
}

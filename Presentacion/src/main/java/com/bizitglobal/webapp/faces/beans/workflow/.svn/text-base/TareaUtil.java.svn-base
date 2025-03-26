package com.bizitglobal.webapp.faces.beans.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.exeption.RolException;
import com.bizitglobal.tarjetafiel.operador.negocio.Rol;
import com.bizitglobal.tarjetafiel.operador.service.RolService;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.workflow.exception.FormExternoException;
import com.bizitglobal.workflow.negocio.FormExterno;
import com.bizitglobal.workflow.negocio.TipoTarea;
import com.bizitglobal.workflow.service.FormExternoService;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class TareaUtil {
	private static final Logger log = Logger.getLogger(TareaUtil.class);


	public static List cargarListaTipoTareas(List tTareaList) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione"));
		if (!tTareaList.isEmpty()) {
			Iterator tTareaIter = tTareaList.iterator();
			while (tTareaIter.hasNext()) {
				SelectItem item = new SelectItem();
				TipoTarea aux = (TipoTarea) tTareaIter.next();
				item.setValue(aux.getIdTipoTarea());
				item.setLabel(aux.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}


	public static TipoTarea getTipoTarea(List tTareaList, Long idTipoTarea) {
		if (!tTareaList.isEmpty()) {
			Iterator tTareaIter = tTareaList.iterator();
			while (tTareaIter.hasNext()) {
				TipoTarea tipoTarea = (TipoTarea) tTareaIter.next();
				if (tipoTarea.getIdTipoTarea().equals(idTipoTarea)) {
					return tipoTarea;
				}
			}
		}
		return new TipoTarea();
	}


	public static List cargarListaRoles(RolService rolService) {
		List result = new ArrayList();
		List rolList = null;

		try {
			rolList = rolService.getRoles();
		} catch (RolException e) {
			e.printStackTrace();
			return null;
		}
		result.add(Util.primerSelectItem("Seleccione"));
		if (!rolList.isEmpty()) {
			Iterator roles = rolList.iterator();
			while (roles.hasNext()) {
				SelectItem item = new SelectItem();
				Rol aux = (Rol) roles.next();
				item.setValue(aux.getIdRol());
				item.setLabel(aux.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}


	public static List caragarListaFormulario(FormExternoService externoService) {
		log.info("Ejecutando--> Cargar Listado Doc Cont");
		List result = new ArrayList();
		List formExt = null;

		try {
			formExt = externoService.listarFormExterno(new Filtro());
		} catch (FormExternoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.add(Util.primerSelectItem("Seleccione"));
		if (!formExt.isEmpty()) {
			log.info("Ejecutando--> Iterator de lista Formulario");
			Iterator formularios = formExt.iterator();
			while (formularios.hasNext()) {
				SelectItem item = new SelectItem();
				FormExterno formExterno = (FormExterno) formularios.next();
				item.setValue(formExterno.getIdFormExterno());
				item.setLabel(formExterno.getLabel());
				result.add(item);
			}
		}
		return result;
	}
}

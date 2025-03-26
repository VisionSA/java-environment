package com.bizitglobal.webapp.faces.beans.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.operador.negocio.Rol;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.workflow.exception.TareaException;
import com.bizitglobal.workflow.negocio.ParametroForm;
import com.bizitglobal.workflow.negocio.Tarea;
import com.bizitglobal.workflow.negocio.TareaProceso;
import com.bizitglobal.workflow.negocio.TareaProcesoRol;
import com.bizitglobal.workflow.service.TareaService;


@SuppressWarnings({"rawtypes","unchecked"})
public class ProcesoUtil {
	private static final Logger log = Logger.getLogger(ProcesoUtil.class);


	public static List cargarListaOperadores(List operadorList) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccione"));
		if (!operadorList.isEmpty()) {
			Iterator operadores = operadorList.iterator();
			while (operadores.hasNext()) {
				Operador aux = (Operador) operadores.next();
				result.add(new SelectItem(aux.getCodigo(), aux.getApellido()));
			}
		}

		return result;
	}


	public static List cargarListaRoles(List rolList) {
		List result = new ArrayList();
		if (!rolList.isEmpty()) {
			Iterator roles = rolList.iterator();
			while (roles.hasNext()) {
				Rol aux = (Rol) roles.next();
				result.add(new SelectItem(aux.getIdRol(), aux.getDescripcion()));
			}
		}

		return result;
	}


	public static List cargarParametrosFormularios(List param) {
		List result = new ArrayList();

		if (!param.isEmpty()) {
			Iterator iterParam = param.iterator();
			while (iterParam.hasNext()) {
				ParametroForm aux = (ParametroForm) iterParam.next();
				// result.add(aux.getIdProcesoAtributo().toString());
			}
		}

		return result;
	}


	public static List cargarRolesTarea(List rolles) {
		List result = new ArrayList();
		if (!rolles.isEmpty()) {
			Iterator iterRol = rolles.iterator();
			while (iterRol.hasNext()) {
				TareaProcesoRol aux = (TareaProcesoRol) iterRol.next();
				result.add(aux.getIdRol().toString());
			}
		}

		return result;
	}


	public static List cargarListaTareas(TareaService tareaService) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione Tarea"));
		List tareaList = null;
		try {
			tareaList = tareaService.listarTarea(new Filtro(Tarea.ACTIVO, Filtro.LIKE, "S"));
		} catch (TareaException e) {
			e.printStackTrace();
			return null;
		}

		if (!tareaList.isEmpty()) {
			Iterator iterTareas = tareaList.iterator();
			while (iterTareas.hasNext()) {
				SelectItem item = new SelectItem();
				Tarea aux = (Tarea) iterTareas.next();
				item.setValue(aux.getIdTarea());
				item.setLabel(aux.getTitulo());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarTablaTareas(List tablaTareas, List tareaProcesos, TareaService tareaService) {
		List result = tablaTareas;
		if (!tareaProcesos.isEmpty()) {
			if (tablaTareas.size() != tareaProcesos.size()) {
				result = new ArrayList();
				Iterator iterTarPro = tareaProcesos.iterator();
				while (iterTarPro.hasNext()) {
					TareaProceso tProceso = (TareaProceso) iterTarPro.next();
					try {
						result.add(tareaService.leerTarea(tProceso.getIdTarea()));
					} catch (TareaException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return result;
	}


	public static Rol getRol(List rolList, Long idRol) {
		if (!rolList.isEmpty()) {
			Iterator iterRol = rolList.iterator();
			while (iterRol.hasNext()) {
				Rol rol = (Rol) iterRol.next();
				if (rol.getIdRol().equals(idRol)) {
					return rol;
				}
			}
		}
		return new Rol(idRol);
	}


	public static Operador getOperador(List operadorList, Long codigo) {
		if (!operadorList.isEmpty()) {
			Iterator iterOper = operadorList.iterator();
			while (iterOper.hasNext()) {
				Operador operador = (Operador) iterOper.next();
				if (operador.getCodigo().equals(codigo)) {
					return operador;
				}
			}
		}
		return new Operador(codigo);
	}

	// public static IniParametro paramBase(Proceso proceso, ProcesoAtributo atributo){
	// IniParametro parametro = new IniParametro();
	// parametro.setIdProceso(proceso.getIdProceso());
	// parametro.setIdProcAtributo(atributo.getIdProcesoAtributo());
	// parametro.setNombre("Nombre del tramite");
	//
	// return parametro;
	// }
	//
	// public static ProcesoAtributo atributoBase(Proceso proceso){
	// ProcesoAtributo atributo = new ProcesoAtributo(new Long(0));
	// atributo.setNombre("Base");
	// atributo.setDescripcion("Necesario para iniciar un tramite");
	// atributo.setTipoAtributo(new TipoAtributo(new Long(1)));
	// atributo.setProceso(proceso);
	// atributo.setRequerido(true);
	// atributo.setLocal(true);
	// atributo.setSoloLectura(false);
	// atributo.setVisible(false);
	// atributo.setLongitud(new Integer(50));
	// atributo.setColumna(new Columna());
	// atributo.setValorDefecto(" ");
	//
	// return atributo;
	// }
}

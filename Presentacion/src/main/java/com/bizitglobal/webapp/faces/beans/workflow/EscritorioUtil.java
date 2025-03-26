package com.bizitglobal.webapp.faces.beans.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.operador.negocio.Rol;
import com.bizitglobal.tarjetafiel.operador.service.OperadorService;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.TareaDetalleTabla;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.TramiteTabla;
import com.bizitglobal.webapp.faces.service.workflow.WorkflowServiceFaces;
import com.bizitglobal.workflow.exception.DetalleTramiteException;
import com.bizitglobal.workflow.exception.ProcesoException;
import com.bizitglobal.workflow.exception.TramiteException;
import com.bizitglobal.workflow.exception.TramiteParametroException;
import com.bizitglobal.workflow.negocio.DetalleTramite;
import com.bizitglobal.workflow.negocio.DetalleTramiteHis;
import com.bizitglobal.workflow.negocio.Estado;
import com.bizitglobal.workflow.negocio.TareaProcesoRol;
import com.bizitglobal.workflow.negocio.Tramite;
import com.bizitglobal.workflow.negocio.TramiteParametro;


@SuppressWarnings({"rawtypes","unchecked"})
public class EscritorioUtil {
	private static final Logger log = Logger.getLogger(EscritorioUtil.class);


	public static List cargarListaTramites(Operador operador, WorkflowServiceFaces workflowService, List estados) throws TramiteException,
			ProcesoException, TramiteParametroException {
		// List tramites = new ArrayList();
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor(Tramite.ID_OPERADOR_SUP, Filtro.IGUAL, operador.getCodigo());
		// tramites = workflowService.getTramiteService().listarTramite(filtro);

		return leerTramites(workflowService, estados, workflowService.getTramiteService().listarTramite(filtro));
	}


	public static List leerTramites(WorkflowServiceFaces workflowService, List estados, List tramites) throws ProcesoException,
			TramiteParametroException {
		List result = new ArrayList();
		Iterator iterTra = tramites.iterator();
		while (iterTra.hasNext()) {
			TramiteTabla tramiteTabla = new TramiteTabla();
			Tramite tramite = (Tramite) iterTra.next();
			tramite.setEstado(buscarEstado(tramite.getEstado(), estados));
			tramite.setProceso(workflowService.getProcesoService().leerProceso(tramite.getProceso().getIdProceso()));
			Filtro filtroParam = new Filtro(TramiteParametro.ID_TRAMITE, Filtro.IGUAL, tramite.getIdTramite());
			/* @I5547 */filtroParam.agregarOrderBy(TramiteParametro.ID_INI_PARAMETRO);
			/* @F5547 */// Se que el primer parametro es el nombre del tramite por que los controlo cuando se graban
			TramiteParametro parametroNombre = (TramiteParametro) workflowService.getTramiteParametroService().listarTramiteParametro(filtroParam)
					.get(0);
			tramiteTabla.setNombreTramite(parametroNombre.getValor());
			tramiteTabla.setTramite(tramite);
			if (tramite.getEstado().getIdEstado().equals(new Long(4))
					|| tramite.getEstado().getIdEstado().equals(new Long(6))) {
				tramiteTabla.setVerCancelarTramite(false);
			}
			result.add(tramiteTabla);
		}
		return result;
	}


	public static Estado buscarEstado(Estado estado, List estados) {
		if (!estados.isEmpty()) {
			Iterator iterEstado = estados.iterator();
			while (iterEstado.hasNext()) {
				Estado auxEstado = (Estado) iterEstado.next();
				if (auxEstado.equals(estado)) {
					return auxEstado;
				}
			}
		}
		return estado;
	}


	public static List cargarListaTareasTramite(Tramite tramite, WorkflowServiceFaces workflowService, OperadorService operadorService, List estados)
			throws Exception {
		List tareas = new ArrayList();
		tareas = workflowService.getDetalleTramiteService().listarDetalleTramite(
				new Filtro(DetalleTramite.ID_TRAMITE, Filtro.IGUAL, tramite.getIdTramite()));
		return armarDetalles(workflowService, operadorService, estados, tareas, new Rol(null));
	}


	/**
	 * Carga la lista de de tareas que tengan su estado en nuevo.
	 * 
	 * @param operadorService
	 * @param workflowServiceFaces
	 * @param estados
	 * @return
	 * @throws Exception
	 */
	public static List cargarListaPendientes(OperadorService operadorService, WorkflowServiceFaces workflowServiceFaces, List estados, Rol rol)
			throws Exception {
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor(DetalleTramite.ID_ESTADO, Filtro.DISTINTO, new Long(2));
		filtro.agregarCampoOperValor(DetalleTramite.ID_ESTADO, Filtro.DISTINTO, new Long(3));
		filtro.agregarCampoOperValor(DetalleTramite.ID_ESTADO, Filtro.DISTINTO, new Long(4));
		filtro.agregarCampoOperValor(DetalleTramite.ID_ESTADO, Filtro.DISTINTO, new Long(6));

		return armarDetallesPendiente(workflowServiceFaces, operadorService, estados, workflowServiceFaces.getDetalleTramiteService()
				.listarDetalleTramite(filtro), rol);
	}


	public static List cargarListaTomadas(Operador operador, OperadorService operadorService, WorkflowServiceFaces workflowServiceFaces,
			List estados, Rol rol) throws Exception {
		List tareasTomadas = new ArrayList();
		try {
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor(DetalleTramite.ID_ESTADO, Filtro.IGUAL, new Long(3));
			filtro.agregarCampoOperValor(DetalleTramite.ID_OPERADOR_RESPONSABLE, Filtro.IGUAL, operador.getCodigo());
			filtro.agregarOrderBy(DetalleTramite.FECHA_INICIO_REAL);
			tareasTomadas.addAll(workflowServiceFaces.getDetalleTramiteService().listarDetalleTramite(filtro));
			filtro.reset();
			filtro.agregarCampoOperValor(DetalleTramite.ID_ESTADO, Filtro.IGUAL, new Long(2));
			filtro.agregarCampoOperValor(DetalleTramite.ID_OPERADOR_RESPONSABLE, Filtro.IGUAL, operador.getCodigo());
			filtro.agregarOrderBy(DetalleTramite.FECHA_INICIO_REAL);
			tareasTomadas.addAll(workflowServiceFaces.getDetalleTramiteService().listarDetalleTramite(filtro));

		} catch (DetalleTramiteException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return armarDetalles(workflowServiceFaces, operadorService, estados, tareasTomadas, rol);
	}


	private static List armarDetalles(WorkflowServiceFaces workflowServiceFaces, OperadorService operadorService, List estados, List tareas, Rol rol)
			throws Exception {
		List result = new ArrayList();
		List rolContenido = new ArrayList();
		Filtro filtro = new Filtro();
		Iterator iterTarPen = tareas.iterator();
		while (iterTarPen.hasNext()) {
			TareaDetalleTabla tareaTabla = new TareaDetalleTabla();
			DetalleTramite dTramite = (DetalleTramite) iterTarPen.next();
			dTramite.setEstado(buscarEstado(dTramite.getEstado(), estados));
			try {
				dTramite.setTareaProceso(workflowServiceFaces.getTareaProcesoService().leerTareaProceso(
						dTramite.getTareaProceso().getIdTareaProceso()));
				// solo cargo la lista si el detalle es del rol que esta logeado o esta en null en el caso de un supervisor
				rolContenido.clear();
				if (rol.getIdRol() != null) {// esto cotrola que roles hay asignados a la tarea
					filtro.reset();
					filtro.agregarCampoOperValor(TareaProcesoRol.ID_ROL, Filtro.IGUAL, rol.getIdRol());
					filtro.agregarCampoOperValor(TareaProcesoRol.ID_TAREA_PROCESO, Filtro.IGUAL, dTramite.getTareaProceso().getIdTareaProceso());
					rolContenido = workflowServiceFaces.getTareaProcesoRolService().listarTareaProcesoRol(filtro);
				}
				if (!rolContenido.isEmpty() || rol.getIdRol() == null) {// si es para el supervisor los cargo todos
					dTramite.setTramite(workflowServiceFaces.getTramiteService().leerTramite(dTramite.getTramite().getIdTramite()));
					dTramite.getTramite().setOperadorSup(operadorService.leerOperador(
							dTramite.getTramite().getOperadorSup().getCodigo()));
					dTramite.getTramite().setProceso(workflowServiceFaces.getProcesoService().leerProceso(
							dTramite.getTramite().getProceso().getIdProceso()));
					if (dTramite.getOperadorResponsable() == null || dTramite.getOperadorResponsable().getCodigo() == null) {
						dTramite.setOperadorResponsable(new Operador());
					} else {
						dTramite.setOperadorResponsable(operadorService.leerOperador(
								dTramite.getOperadorResponsable().getCodigo()));
					}
					dTramite.getTramite().setEstado(buscarEstado(dTramite.getTramite().getEstado(), estados));
					if (rol.getIdRol() == null || !dTramite.getTramite().getEstado().getIdEstado().equals(new Long(6))) {
						tareaTabla.setDetalleTramite(dTramite);
						tareaTabla.setTarea(workflowServiceFaces.getTareaService().leerTarea(dTramite.getTareaProceso().getIdTarea()));
						Filtro filtroParam = new Filtro(TramiteParametro.ID_TRAMITE, Filtro.IGUAL, dTramite.getTramite().getIdTramite());
						/* @I5547 */filtroParam.agregarOrderBy(TramiteParametro.ID_INI_PARAMETRO);
						/* @F5547 */List parametroList = workflowServiceFaces.getTramiteParametroService().listarTramiteParametro(filtroParam);
						TramiteParametro parametroNombre = (TramiteParametro) parametroList.get(0);
						tareaTabla.setNombreTramite(parametroNombre.getValor());
						log.info("Detalle agregado -> " + tareaTabla.getDetalleTramite());
						result.add(tareaTabla);
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}
		return result;
	}


	private static List armarDetallesPendiente(WorkflowServiceFaces workflowServiceFaces, OperadorService operadorService, List estados, List tareas,
			Rol rol) throws Exception {
		List result = new ArrayList();
		List rolContenido = new ArrayList();
		Filtro filtro = new Filtro();
		Iterator iterTarPen = tareas.iterator();
		while (iterTarPen.hasNext()) {
			TareaDetalleTabla tareaTabla = new TareaDetalleTabla();
			DetalleTramite dTramite = (DetalleTramite) iterTarPen.next();
			dTramite.setEstado(buscarEstado(dTramite.getEstado(), estados));
			filtro.reset();
			filtro.agregarCampoOperValor(DetalleTramite.ID_DETALLE_TRAMITE, Filtro.IGUAL, dTramite.getIdDetalleTramite());
			List detalleTramiteHiss = workflowServiceFaces.getDetalleTramiteHisService().listarDetalleTramiteHis(filtro);
			Iterator iterTarPen1 = detalleTramiteHiss.iterator();
			while (iterTarPen1.hasNext()) {
				DetalleTramiteHis detalleTramiteHis = (DetalleTramiteHis) iterTarPen1.next();
				Operador operador = operadorService.leerOperador(detalleTramiteHis.getOperador().getCodigo());
				tareaTabla.setOperadorInciaTramite(operador.getApellido().trim() + ", " + operador.getNombre());
			}

			try {
				dTramite.setTareaProceso(workflowServiceFaces.getTareaProcesoService().leerTareaProceso(
						dTramite.getTareaProceso().getIdTareaProceso()));
				// solo cargo la lista si el detalle es del rol que esta logeado o esta en null en el caso de un supervisor
				rolContenido.clear();
				if (rol.getIdRol() != null) {// esto cotrola que roles hay asignados a la tarea
					filtro.reset();
					filtro.agregarCampoOperValor(TareaProcesoRol.ID_ROL, Filtro.IGUAL, rol.getIdRol());
					filtro.agregarCampoOperValor(TareaProcesoRol.ID_TAREA_PROCESO, Filtro.IGUAL, dTramite.getTareaProceso().getIdTareaProceso());
					rolContenido = workflowServiceFaces.getTareaProcesoRolService().listarTareaProcesoRol(filtro);
				}
				if (!rolContenido.isEmpty() || rol.getIdRol() == null) {// si es para el supervisor los cargo todos
					dTramite.setTramite(workflowServiceFaces.getTramiteService().leerTramite(dTramite.getTramite().getIdTramite()));
					dTramite.getTramite().setOperadorSup(operadorService.leerOperador(
							dTramite.getTramite().getOperadorSup().getCodigo()));
					dTramite.getTramite().setProceso(workflowServiceFaces.getProcesoService().leerProceso(
							dTramite.getTramite().getProceso().getIdProceso()));
					if (dTramite.getOperadorResponsable() == null || dTramite.getOperadorResponsable().getCodigo() == null) {
						dTramite.setOperadorResponsable(new Operador());
					} else {
						dTramite.setOperadorResponsable(operadorService.leerOperador(
								dTramite.getOperadorResponsable().getCodigo()));
					}
					dTramite.getTramite().setEstado(buscarEstado(dTramite.getTramite().getEstado(), estados));
					if (rol.getIdRol() == null || !dTramite.getTramite().getEstado().getIdEstado().equals(new Long(6))) {
						tareaTabla.setDetalleTramite(dTramite);
						tareaTabla.setTarea(workflowServiceFaces.getTareaService().leerTarea(dTramite.getTareaProceso().getIdTarea()));
						Filtro filtroParam = new Filtro(TramiteParametro.ID_TRAMITE, Filtro.IGUAL, dTramite.getTramite().getIdTramite());
						/* @I5547 */filtroParam.agregarOrderBy(TramiteParametro.ID_INI_PARAMETRO);
						/* @F5547 */List parametroList = workflowServiceFaces.getTramiteParametroService().listarTramiteParametro(filtroParam);
						TramiteParametro parametroNombre = (TramiteParametro) parametroList.get(0);
						tareaTabla.setNombreTramite(parametroNombre.getValor());
						log.info("Detalle agregado -> " + tareaTabla.getDetalleTramite());
						result.add(tareaTabla);
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}
		return result;
	}

}

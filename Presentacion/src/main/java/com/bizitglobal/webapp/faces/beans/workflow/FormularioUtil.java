package com.bizitglobal.webapp.faces.beans.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.operador.service.OperadorService;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.DetalleHisWrapper;
import com.bizitglobal.webapp.faces.service.workflow.WorkflowServiceFaces;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.workflow.negocio.AtributoProcesoTarea;
import com.bizitglobal.workflow.negocio.DetalleTramite;
import com.bizitglobal.workflow.negocio.DetalleTramiteHis;
import com.bizitglobal.workflow.negocio.FormExterno;
import com.bizitglobal.workflow.negocio.ParametroForm;
import com.bizitglobal.workflow.negocio.ProcesoAtributo;
import com.bizitglobal.workflow.negocio.Tarea;
import com.bizitglobal.workflow.negocio.Tramite;


@SuppressWarnings({"rawtypes","unchecked"})
public class FormularioUtil {
	private static final Logger log = Logger.getLogger(FormularioUtil.class);


	public static Map cargarMapParam(FormExterno formExterno, DetalleTramite detalleTramite, WorkflowServiceFaces workflowService) {
		Map result = new HashMap();
		List paramList = new ArrayList();
		try {
			Filtro filtroParam = new Filtro(ParametroForm.ID_FORM_EXTERNO, Filtro.IGUAL, formExterno.getIdFormExterno());
			filtroParam.agregarCampoOperValor(ParametroForm.TAREA_PROCESO_ID, Filtro.IGUAL, detalleTramite.getTareaProceso().getIdTareaProceso());
			paramList = workflowService.getParametroFormService().listarParametroForm(filtroParam);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (!paramList.isEmpty()) {
			Iterator iterParam = paramList.iterator();
			while (iterParam.hasNext()) {
				ParametroForm parametroForm = (ParametroForm) iterParam.next();
				ProcesoAtributo atributo = null;
				try {
					AtributoProcesoTarea apt = workflowService.getAtributoProcesoTareaService().leerAtributoProcesoTarea(
							parametroForm.getIdAtributoTareaPro());
					atributo = workflowService.getProcesoAtributoService().leerProcesoAtributo(
							apt.getIdProcesoAtributo());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (atributo != null) {
					String valor = null;
					try {
						valor = workflowService.getAtributoValorService().buscarValorTramite(
								detalleTramite.getTramite().getIdTramite(), atributo.getNombre());
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					if (valor != null) {
						if (atributo.getTipoAtributo().getIdTipoAtributo().equals(new Long(9))) {
							valor = Session.getHomePath() + valor;
						}
						if (parametroForm.getAlias() == null || parametroForm.getAlias().trim().equals("")) {
							log.info("Parametro -> " + atributo.getNombre() + " : " + valor);
							result.put(atributo.getNombre(), valor);
						} else {
							log.info("Parametro Alias -> " + parametroForm.getAlias() + " : " + valor);
							result.put(parametroForm.getAlias().trim(), valor);
						}
					}
				}
			}
		}
		return result;
	}


	public static List cargarListaTramites(Tramite tramite, WorkflowServiceFaces workflowService, OperadorService operadorService, List estados) {
		List result = new ArrayList();
		List detalleHis = new ArrayList();
		try {
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor(DetalleTramiteHis.ID_TRAMITE, Filtro.IGUAL, tramite.getIdTramite());
			filtro.agregarOrderBy(DetalleTramiteHis.ID_DETALLE_TRAMITE_HIS);
			filtro.agregarOrderBy(DetalleTramiteHis.TIMESTAMP);
			detalleHis = workflowService.getDetalleTramiteHisService().listarDetalleTramiteHis(filtro);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (!detalleHis.isEmpty()) {
			Iterator iterHis = detalleHis.iterator();
			while (iterHis.hasNext()) {
				DetalleTramiteHis detalleTramiteHis = (DetalleTramiteHis) iterHis.next();
				detalleTramiteHis.setEstado(EscritorioUtil.buscarEstado(detalleTramiteHis.getEstado(), estados));
				Tarea tarea = new Tarea();
				try {
					Operador operador = operadorService.leerOperador(detalleTramiteHis.getOperador().getCodigo());
					detalleTramiteHis.setOperador(operador);
					if (detalleTramiteHis.getOperadorResponsable() == null || detalleTramiteHis.getOperadorResponsable().getCodigo() == null) {
						detalleTramiteHis.setOperadorResponsable(new Operador());
					} else {
						if (detalleTramiteHis.getOperadorResponsable().getCodigo().equals(operador.getCodigo())) {
							detalleTramiteHis.setOperadorResponsable(operador);
						} else {
							detalleTramiteHis.setOperador(operadorService.leerOperador(detalleTramiteHis.getOperador().getCodigo()));
						}
					}
					detalleTramiteHis.setTareaProceso(workflowService.getTareaProcesoService().
							leerTareaProceso(detalleTramiteHis.getTareaProceso().getIdTareaProceso()));
					tarea = workflowService.getTareaService().leerTarea(detalleTramiteHis.getTareaProceso().getIdTarea());
				} catch (Exception e) {
					e.printStackTrace();
				}
				DetalleHisWrapper detalleHisW = new DetalleHisWrapper(detalleTramiteHis);
				detalleHisW.setTarea(tarea);
				result.add(detalleHisW);
			}
		}
		return result;
	}
}

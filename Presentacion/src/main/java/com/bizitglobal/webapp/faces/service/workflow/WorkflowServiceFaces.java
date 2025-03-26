package com.bizitglobal.webapp.faces.service.workflow;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.operador.service.RolService;
import com.bizitglobal.webapp.faces.service.BaseService;
import com.bizitglobal.workflow.service.AccesoTareaHisService;
import com.bizitglobal.workflow.service.AlarmaCondicionService;
import com.bizitglobal.workflow.service.AlarmaService;
import com.bizitglobal.workflow.service.AlertaService;
import com.bizitglobal.workflow.service.AtributoProcesoTareaService;
import com.bizitglobal.workflow.service.AtributoValorHisService;
import com.bizitglobal.workflow.service.AtributoValorService;
import com.bizitglobal.workflow.service.ClaseJavaService;
import com.bizitglobal.workflow.service.ColumnaService;
import com.bizitglobal.workflow.service.DetalleTramiteAlarmaService;
import com.bizitglobal.workflow.service.DetalleTramiteHisService;
import com.bizitglobal.workflow.service.DetalleTramiteService;
import com.bizitglobal.workflow.service.EstadoService;
import com.bizitglobal.workflow.service.EvaluaCondicionService;
import com.bizitglobal.workflow.service.FlujoCondicionService;
import com.bizitglobal.workflow.service.FlujoService;
import com.bizitglobal.workflow.service.FormExternoService;
import com.bizitglobal.workflow.service.IniParametroService;
import com.bizitglobal.workflow.service.OperadorCondicionService;
import com.bizitglobal.workflow.service.ParametroFormService;
import com.bizitglobal.workflow.service.PrioridadService;
import com.bizitglobal.workflow.service.ProcesoAtributoService;
import com.bizitglobal.workflow.service.ProcesoService;
import com.bizitglobal.workflow.service.RelacionTablaService;
import com.bizitglobal.workflow.service.TablaService;
import com.bizitglobal.workflow.service.TareaAlarmaService;
import com.bizitglobal.workflow.service.TareaProcesoRolService;
import com.bizitglobal.workflow.service.TareaProcesoService;
import com.bizitglobal.workflow.service.TareaService;
import com.bizitglobal.workflow.service.TipoAlertaService;
import com.bizitglobal.workflow.service.TipoAtributoService;
import com.bizitglobal.workflow.service.TipoCondicionService;
import com.bizitglobal.workflow.service.TipoTareaService;
import com.bizitglobal.workflow.service.TramiteParametroService;
import com.bizitglobal.workflow.service.TramiteService;


public class WorkflowServiceFaces extends BaseService {
	private static final Logger log = Logger.getLogger(WorkflowServiceFaces.class);

	private static final String ACCESO_TAREA_HIS_SERVICE_NAME = "accesoTareaHisService";
	private static final String ALARMA_CONDICION_SERVICE_NAME = "alarmaCondicionService";
	private static final String ALARMA_SERVICE_NAME = "alarmaService";
	private static final String ALERTA_SERVICE_NAME = "alertaService";
	private static final String ATRIBUTO_PROCESO_TAREA_SERVICE_NAME = "atributoProcesoTareaService";
	private static final String ATRIBUTO_VALOR_SERVICE_NAME = "atributoValorService";
	private static final String ATRIBUTO_VALOR_HISTORICO_SERVICE_NAME = "atributoValorHisService";
	private static final String COLUMNA_SERVICE_NAME = "columnaService";
	private static final String CLASE_JAVA_SERVICE_NEME = "claseJavaService";
	private static final String DETALLE_TRAMITE_ALARMA_SERVICE_NAME = "detalleTramiteAlarmaService";
	private static final String DETALLE_TRAMITE_SERVICE_NAME = "detalleTramiteService";
	private static final String DETALLE_TRAMITE__HIS_SERVICE_NAME = "detalleTramiteHisService";
	private static final String ESTADO_SERVICE_NAME = "estadoService";
	private static final String EVALUA_CONDICION_SERVICE_NAME = "evaluaCondicionService";
	private static final String FLUJO_CONDICION_SERVICE_NAME = "flujoCondicionService";
	private static final String FLUJO_SERVICE_NAME = "flujoService";
	private static final String FORM_EXTERNO_SERVICE_NAME = "formExternoService";
	private static final String INI_PARAMETRO_SERVICE_NAME = "iniParametroService";
	private static final String OPERADOR_CONDICION_SERVICE_NAME = "operadorCondicionService";
	private static final String PARAMETRO_FORM_SERVICE_NAME = "parametroFormService";
	private static final String PRIORIDAD_SERVICE_NAME = "prioridadService";
	private static final String PROCESO_ATRIBUTO_SERVICE_NAME = "procesoAtributoService";
	private static final String PROCESO_SERVICE_NAME = "procesoService";
	private static final String RELACION_TABLA_SERVICE_NAME = "relacionTablaService";
	private static final String TABLA_SERVICE_NAME = "tablaService";
	private static final String TAREA_ALARMA_SERVICE_NAME = "tareaAlarmaService";
	private static final String TAREA_PROCESO_ROL_SERVICE_NAME = "tareaProcesoRolService";
	private static final String TAREA_PROCESO_SERVICE_NAME = "tareaProcesoService";
	private static final String TAREA_SERVICE_NAME = "tareaService";
	private static final String TIPO_ALERTA_SERVICE_NAME = "tipoAlertaService";
	private static final String TIPO_ATRIBUTO_SERVICE_NAME = "tipoAtributoService";
	private static final String TIPO_CONDICION_SERVICE_NAME = "tipoCondicionService";
	private static final String TIPO_TAREA_SERVICE_NAME = "tipoTareaService";
	private static final String TRAMITE_SERVICE_NAME = "tramiteService";
	private static final String TRAMITE_PARAMETRO_SERVICE_NAME = "tramiteParametroService";
	private static final String ROL_SERVICE_NAME = "rolService";

	/*
	 * Objetos services para el modulo.
	 */

	private AccesoTareaHisService accesoTareaHisService = null;
	private AlarmaCondicionService alarmaCondicionService = null;
	private AlarmaService alarmaService = null;
	private AlertaService alertaService = null;
	private AtributoProcesoTareaService atributoProcesoTareaService = null;
	private AtributoValorService atributoValorService = null;
	private AtributoValorHisService atributoValorHisService = null;
	private ColumnaService columnaService = null;
	private ClaseJavaService claseJavaService = null;
	private DetalleTramiteAlarmaService detalleTramiteAlarmaService = null;
	private DetalleTramiteService detalleTramiteService = null;
	private DetalleTramiteHisService detalleTramiteHisService = null;
	private EstadoService estadoService = null;
	private EvaluaCondicionService evaluaCondicionService = null;
	private FlujoCondicionService flujoCondicionService = null;
	private FlujoService flujoService = null;
	private FormExternoService formExternoService = null;
	private IniParametroService iniParametroService = null;
	private OperadorCondicionService operadorCondicionService = null;
	private ParametroFormService parametroFormService = null;
	private PrioridadService prioridadService = null;
	private ProcesoAtributoService procesoAtributoService = null;
	private ProcesoService procesoService = null;
	private RelacionTablaService relacionTablaService = null;
	private TablaService tablaService = null;
	private TareaAlarmaService tareaAlarmaService = null;
	private TareaProcesoService tareaProcesoService = null;
	private TareaProcesoRolService tareaProcesoRolService = null;
	private TareaService tareaService = null;
	private TipoAlertaService tipoAlertaService = null;
	private TipoAtributoService tipoAtributoService = null;
	private TipoCondicionService tipoCondicionService = null;
	private TipoTareaService tipoTareaService = null;
	private TramiteService tramiteService = null;
	private TramiteParametroService tramiteParametroService = null;
	private RolService rolService = null;


	public WorkflowServiceFaces() {
		super();
		log.info("Construyendo el service del workflow!!!");
		this.accesoTareaHisService = (AccesoTareaHisService) this.lookupService(ACCESO_TAREA_HIS_SERVICE_NAME);
		this.alarmaCondicionService = (AlarmaCondicionService) this.lookupService(ALARMA_CONDICION_SERVICE_NAME);
		this.alarmaService = (AlarmaService) this.lookupService(ALARMA_SERVICE_NAME);
		this.alertaService = (AlertaService) this.lookupService(ALERTA_SERVICE_NAME);
		this.atributoProcesoTareaService = (AtributoProcesoTareaService) this.lookupService(ATRIBUTO_PROCESO_TAREA_SERVICE_NAME);
		this.atributoValorService = (AtributoValorService) this.lookupService(ATRIBUTO_VALOR_SERVICE_NAME);
		this.atributoValorHisService = (AtributoValorHisService) this.lookupService(ATRIBUTO_VALOR_HISTORICO_SERVICE_NAME);
		this.columnaService = (ColumnaService) this.lookupService(COLUMNA_SERVICE_NAME);
		this.claseJavaService = (ClaseJavaService) this.lookupService(CLASE_JAVA_SERVICE_NEME);
		this.detalleTramiteAlarmaService = (DetalleTramiteAlarmaService) this.lookupService(DETALLE_TRAMITE_ALARMA_SERVICE_NAME);
		this.detalleTramiteService = (DetalleTramiteService) this.lookupService(DETALLE_TRAMITE_SERVICE_NAME);
		this.detalleTramiteHisService = (DetalleTramiteHisService) this.lookupService(DETALLE_TRAMITE__HIS_SERVICE_NAME);
		this.estadoService = (EstadoService) this.lookupService(ESTADO_SERVICE_NAME);
		this.evaluaCondicionService = (EvaluaCondicionService) this.lookupService(EVALUA_CONDICION_SERVICE_NAME);
		this.flujoCondicionService = (FlujoCondicionService) this.lookupService(FLUJO_CONDICION_SERVICE_NAME);
		this.flujoService = (FlujoService) this.lookupService(FLUJO_SERVICE_NAME);
		this.formExternoService = (FormExternoService) this.lookupService(FORM_EXTERNO_SERVICE_NAME);
		this.iniParametroService = (IniParametroService) this.lookupService(INI_PARAMETRO_SERVICE_NAME);
		this.operadorCondicionService = (OperadorCondicionService) this.lookupService(OPERADOR_CONDICION_SERVICE_NAME);
		this.parametroFormService = (ParametroFormService) this.lookupService(PARAMETRO_FORM_SERVICE_NAME);
		this.prioridadService = (PrioridadService) this.lookupService(PRIORIDAD_SERVICE_NAME);
		this.procesoAtributoService = (ProcesoAtributoService) this.lookupService(PROCESO_ATRIBUTO_SERVICE_NAME);
		this.procesoService = (ProcesoService) this.lookupService(PROCESO_SERVICE_NAME);
		this.relacionTablaService = (RelacionTablaService) this.lookupService(RELACION_TABLA_SERVICE_NAME);
		this.tablaService = (TablaService) this.lookupService(TABLA_SERVICE_NAME);
		this.tareaAlarmaService = (TareaAlarmaService) this.lookupService(TAREA_ALARMA_SERVICE_NAME);
		this.tareaProcesoService = (TareaProcesoService) this.lookupService(TAREA_PROCESO_SERVICE_NAME);
		this.tareaProcesoRolService = (TareaProcesoRolService) this.lookupService(TAREA_PROCESO_ROL_SERVICE_NAME);
		this.tareaService = (TareaService) this.lookupService(TAREA_SERVICE_NAME);
		this.tipoAlertaService = (TipoAlertaService) this.lookupService(TIPO_ALERTA_SERVICE_NAME);
		this.tipoAtributoService = (TipoAtributoService) this.lookupService(TIPO_ATRIBUTO_SERVICE_NAME);
		this.tipoCondicionService = (TipoCondicionService) this.lookupService(TIPO_CONDICION_SERVICE_NAME);
		this.tipoTareaService = (TipoTareaService) this.lookupService(TIPO_TAREA_SERVICE_NAME);
		this.tramiteService = (TramiteService) this.lookupService(TRAMITE_SERVICE_NAME);
		this.tramiteParametroService = (TramiteParametroService) this.lookupService(TRAMITE_PARAMETRO_SERVICE_NAME);
		this.rolService = (RolService) this.lookupService(ROL_SERVICE_NAME);
	}


	public AccesoTareaHisService getAccesoTareaHisService() {
		return accesoTareaHisService;
	}


	public AlarmaCondicionService getAlarmaCondicionService() {
		return alarmaCondicionService;
	}


	public AlarmaService getAlarmaService() {
		return alarmaService;
	}


	public AlertaService getAlertaService() {
		return alertaService;
	}


	public AtributoProcesoTareaService getAtributoProcesoTareaService() {
		return atributoProcesoTareaService;
	}


	public AtributoValorHisService getAtributoValorHisService() {
		return atributoValorHisService;
	}


	public AtributoValorService getAtributoValorService() {
		return atributoValorService;
	}


	public ClaseJavaService getClaseJavaService() {
		return claseJavaService;
	}


	public ColumnaService getColumnaService() {
		return columnaService;
	}


	public DetalleTramiteAlarmaService getDetalleTramiteAlarmaService() {
		return detalleTramiteAlarmaService;
	}


	public DetalleTramiteHisService getDetalleTramiteHisService() {
		return detalleTramiteHisService;
	}


	public DetalleTramiteService getDetalleTramiteService() {
		return detalleTramiteService;
	}


	public EstadoService getEstadoService() {
		return estadoService;
	}


	public EvaluaCondicionService getEvaluaCondicionService() {
		return evaluaCondicionService;
	}


	public FlujoCondicionService getFlujoCondicionService() {
		return flujoCondicionService;
	}


	public FlujoService getFlujoService() {
		return flujoService;
	}


	public FormExternoService getFormExternoService() {
		return formExternoService;
	}


	public IniParametroService getIniParametroService() {
		return iniParametroService;
	}


	public OperadorCondicionService getOperadorCondicionService() {
		return operadorCondicionService;
	}


	public ParametroFormService getParametroFormService() {
		return parametroFormService;
	}


	public PrioridadService getPrioridadService() {
		return prioridadService;
	}


	public ProcesoAtributoService getProcesoAtributoService() {
		return procesoAtributoService;
	}


	public ProcesoService getProcesoService() {
		return procesoService;
	}


	public RelacionTablaService getRelacionTablaService() {
		return relacionTablaService;
	}


	public RolService getRolService() {
		return rolService;
	}


	public TablaService getTablaService() {
		return tablaService;
	}


	public TareaAlarmaService getTareaAlarmaService() {
		return tareaAlarmaService;
	}


	public TareaProcesoService getTareaProcesoService() {
		return tareaProcesoService;
	}


	public TareaService getTareaService() {
		return tareaService;
	}


	public TipoAlertaService getTipoAlertaService() {
		return tipoAlertaService;
	}


	public TipoAtributoService getTipoAtributoService() {
		return tipoAtributoService;
	}


	public TipoCondicionService getTipoCondicionService() {
		return tipoCondicionService;
	}


	public TipoTareaService getTipoTareaService() {
		return tipoTareaService;
	}


	public TramiteParametroService getTramiteParametroService() {
		return tramiteParametroService;
	}


	public TramiteService getTramiteService() {
		return tramiteService;
	}


	public TareaProcesoRolService getTareaProcesoRolService() {
		return tareaProcesoRolService;
	}

}

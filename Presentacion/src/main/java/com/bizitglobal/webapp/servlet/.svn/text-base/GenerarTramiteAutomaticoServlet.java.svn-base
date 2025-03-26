package com.bizitglobal.webapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.operador.exeption.OperadorException;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.operador.service.OperadorService;
import com.bizitglobal.tarjetafiel.operador.service.RolService;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.PlasticoClienteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.BloqueoPlastico;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoEstado;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoOperacion;
import com.bizitglobal.tarjetafiel.transacciones.service.ClienteTransaccionService;
import com.bizitglobal.tarjetafiel.transacciones.service.LiqClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.PlasticoClienteService;
import com.bizitglobal.tarjetafiel.transacciones.service.PlasticoEstadosService;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.TramiteWrapper;
import com.bizitglobal.workflow.exception.AtributoValorException;
import com.bizitglobal.workflow.exception.EstadoException;
import com.bizitglobal.workflow.exception.IniParametroException;
import com.bizitglobal.workflow.exception.ProcesoAtributoException;
import com.bizitglobal.workflow.exception.ProcesoException;
import com.bizitglobal.workflow.exception.TareaException;
import com.bizitglobal.workflow.exception.TareaProcesoException;
import com.bizitglobal.workflow.exception.TramiteException;
import com.bizitglobal.workflow.negocio.AtributoValor;
import com.bizitglobal.workflow.negocio.AtributoValorHis;
import com.bizitglobal.workflow.negocio.DetalleTramite;
import com.bizitglobal.workflow.negocio.DetalleTramiteHis;
import com.bizitglobal.workflow.negocio.IniParametro;
import com.bizitglobal.workflow.negocio.Proceso;
import com.bizitglobal.workflow.negocio.ProcesoAtributo;
import com.bizitglobal.workflow.negocio.Tarea;
import com.bizitglobal.workflow.negocio.TareaProceso;
import com.bizitglobal.workflow.negocio.Tramite;
import com.bizitglobal.workflow.negocio.TramiteParametro;
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


public class GenerarTramiteAutomaticoServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 6584463924824216356L;
	private static final Logger log = Logger.getLogger(GenerarTramiteAutomaticoServlet.class);

	private static final String PLASTICO_CLIENTE_SERVICE_NAME = "plasticoClienteService";
	private static final String CLIENTE_SERVICE_NAME = "clienteTransaccionService";
	private static final String PLASTICO_ESTADO_SERVICE_NAME = "plasticoEstadosServiceTarget";

	private static final String PROCESO_SERVICE_NAME = "procesoService";

	private PlasticoClienteService plasticoClienteService = null;
	private ClienteTransaccionService clienteTransaccionService = null;
	private PlasticoEstadosService plasticoEstadosService = null;
	private List<PlasticoCliente> listaCuenta = new ArrayList<PlasticoCliente>();
	private ClienteTransaccion ultimoTitular;
	private PlasticoOperacion plasticoOperacion;
	private OperadorService operadorService;

	private Proceso proceso;
	private Tramite tramite;
	private String label;
	private DetalleTramite detalleTramite;
	private AtributoValor atributoValor;
	private TramiteParametro tramiteParametro;
	private DetalleTramiteHis detalleTramiteHis;
	private AtributoValorHis atributoValorHis;
	private Long duracionTarea = new Long(0);
	private Filtro filtroAtribRestantes;

	private List parametroList;
	private List parametroWrapperList;
	private List parametroWrapperListDos;
	private List tareaProcesoList;
	private List auxTareaProcesoList;

	private boolean usarCU;
	private String cu;
	Operador operadorAutomatico;

	// private WorkflowServiceFaces workflowService = new WorkflowServiceFaces();

	private ProcesoService procesoService = null;
	private RolService rolService = null;


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GenerarTramiteAutomaticoServlet() {
		super();
	}


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}


	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}


	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			out.print("Test!!!<br>");

			ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			operadorAutomatico = ((OperadorService) appContext.getBean("operadorService")).leerOperador(1262l);
			operadorService = (OperadorService) appContext.getBean("operadorService");
			plasticoClienteService = (PlasticoClienteService) appContext.getBean(PLASTICO_CLIENTE_SERVICE_NAME);
			clienteTransaccionService = (ClienteTransaccionService) appContext.getBean(CLIENTE_SERVICE_NAME);
			plasticoEstadosService = (PlasticoEstadosService) appContext.getBean(PLASTICO_ESTADO_SERVICE_NAME);

			// super();
			log.info("Construyendo el service del workflow!!!");
			this.accesoTareaHisService = (AccesoTareaHisService) appContext.getBean(ACCESO_TAREA_HIS_SERVICE_NAME);
			this.alarmaCondicionService = (AlarmaCondicionService) appContext.getBean(ALARMA_CONDICION_SERVICE_NAME);
			this.alarmaService = (AlarmaService) appContext.getBean(ALARMA_SERVICE_NAME);
			this.alertaService = (AlertaService) appContext.getBean(ALERTA_SERVICE_NAME);
			this.atributoProcesoTareaService = (AtributoProcesoTareaService) appContext.getBean(ATRIBUTO_PROCESO_TAREA_SERVICE_NAME);
			this.atributoValorService = (AtributoValorService) appContext.getBean(ATRIBUTO_VALOR_SERVICE_NAME);
			this.atributoValorHisService = (AtributoValorHisService) appContext.getBean(ATRIBUTO_VALOR_HISTORICO_SERVICE_NAME);
			this.columnaService = (ColumnaService) appContext.getBean(COLUMNA_SERVICE_NAME);
			this.claseJavaService = (ClaseJavaService) appContext.getBean(CLASE_JAVA_SERVICE_NEME);
			this.detalleTramiteAlarmaService = (DetalleTramiteAlarmaService) appContext.getBean(DETALLE_TRAMITE_ALARMA_SERVICE_NAME);
			this.detalleTramiteService = (DetalleTramiteService) appContext.getBean(DETALLE_TRAMITE_SERVICE_NAME);
			this.detalleTramiteHisService = (DetalleTramiteHisService) appContext.getBean(DETALLE_TRAMITE__HIS_SERVICE_NAME);
			this.estadoService = (EstadoService) appContext.getBean(ESTADO_SERVICE_NAME);
			this.evaluaCondicionService = (EvaluaCondicionService) appContext.getBean(EVALUA_CONDICION_SERVICE_NAME);
			this.flujoCondicionService = (FlujoCondicionService) appContext.getBean(FLUJO_CONDICION_SERVICE_NAME);
			this.flujoService = (FlujoService) appContext.getBean(FLUJO_SERVICE_NAME);
			this.formExternoService = (FormExternoService) appContext.getBean(FORM_EXTERNO_SERVICE_NAME);
			this.iniParametroService = (IniParametroService) appContext.getBean(INI_PARAMETRO_SERVICE_NAME);
			this.operadorCondicionService = (OperadorCondicionService) appContext.getBean(OPERADOR_CONDICION_SERVICE_NAME);
			this.parametroFormService = (ParametroFormService) appContext.getBean(PARAMETRO_FORM_SERVICE_NAME);
			this.prioridadService = (PrioridadService) appContext.getBean(PRIORIDAD_SERVICE_NAME);
			this.procesoAtributoService = (ProcesoAtributoService) appContext.getBean(PROCESO_ATRIBUTO_SERVICE_NAME);
			this.procesoService = (ProcesoService) appContext.getBean(PROCESO_SERVICE_NAME);
			this.relacionTablaService = (RelacionTablaService) appContext.getBean(RELACION_TABLA_SERVICE_NAME);
			this.tablaService = (TablaService) appContext.getBean(TABLA_SERVICE_NAME);
			this.tareaAlarmaService = (TareaAlarmaService) appContext.getBean(TAREA_ALARMA_SERVICE_NAME);
			this.tareaProcesoService = (TareaProcesoService) appContext.getBean(TAREA_PROCESO_SERVICE_NAME);
			this.tareaProcesoRolService = (TareaProcesoRolService) appContext.getBean(TAREA_PROCESO_ROL_SERVICE_NAME);
			this.tareaService = (TareaService) appContext.getBean(TAREA_SERVICE_NAME);
			this.tipoAlertaService = (TipoAlertaService) appContext.getBean(TIPO_ALERTA_SERVICE_NAME);
			this.tipoAtributoService = (TipoAtributoService) appContext.getBean(TIPO_ATRIBUTO_SERVICE_NAME);
			this.tipoCondicionService = (TipoCondicionService) appContext.getBean(TIPO_CONDICION_SERVICE_NAME);
			this.tipoTareaService = (TipoTareaService) appContext.getBean(TIPO_TAREA_SERVICE_NAME);
			this.tramiteService = (TramiteService) appContext.getBean(TRAMITE_SERVICE_NAME);
			this.tramiteParametroService = (TramiteParametroService) appContext.getBean(TRAMITE_PARAMETRO_SERVICE_NAME);
			this.rolService = (RolService) appContext.getBean(ROL_SERVICE_NAME);
			this.liqClienteService = (LiqClienteService) appContext.getBean(LIQ_CLIENTE_NAME);

			List<BloqueoPlastico> bloqueoPlasticos = liqClienteService.buscarDniParaBloquear();

			Iterator iterator = bloqueoPlasticos.iterator();
			while (iterator.hasNext()) {
				BloqueoPlastico bloqueoPlastico = (BloqueoPlastico) iterator.next();

				// this.procesoService = (ProcesoService)appContext.getBean(PROCESO_SERVICE_NAME);
				// this.procesoService = (ProcesoService)appContext.getBean(PROCESO_SERVICE_NAME);

				List auxProc = new ArrayList();
				// Proceso proceso = new Proceso();
				parametroList = new ArrayList();
				parametroWrapperList = new ArrayList();
				parametroWrapperListDos = new ArrayList();
				tareaProcesoList = new ArrayList();
				auxTareaProcesoList = new ArrayList();

				Long duracionTarea = new Long(0);
				String cu = "";
				Boolean opcionA = false;
				boolean lista = false;

				Filtro filtro = new Filtro(Proceso.TITULO, Filtro.LIKEXS, "Reemplazo de Plastico");
				// Filtro filtro = new Filtro(Proceso.TITULO,Filtro.LIKEXS,"Modificacion de Estructura de Cuenta");
				// Filtro filtro = new Filtro(Proceso.TITULO,Filtro.LIKEXS,"Revision de linea de Credito");

				filtro.agregarCampoOperValor(Proceso.ACTIVO, Filtro.LIKE, new String("S"));
				// Aca buscamos el proceso que capturamos desde el menu.
				auxProc = getProcesoService().listarProceso(filtro);
				if (!auxProc.isEmpty()) {
					proceso = (Proceso) auxProc.get(0);
					// Una vez que tenemos el proceso empezamos a armarlo con los objetos correspondientes
					proceso.setRol(getRolService().leerRol(proceso.getRol().getIdRol()));
					proceso.setSupervisorDef(operadorService.leerOperador(proceso.getSupervisorDef().getCodigo()));
					String supervisor = proceso.getSupervisorDef().getApellido() + ", " + proceso.getSupervisorDef().getNombre();

					if (proceso.getActivo())
						opcionA = true;
					// Aqui buscamos todos los objetos IniParametros que estan relacionados con el proeso.
					filtro = new Filtro(IniParametro.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
					/* @I5547 */filtro.agregarOrderBy(IniParametro.ID_INI_PARAMETRO);
					/* @F5547 */parametroList = getIniParametroService().listarIniParametro(filtro);

					if (!parametroList.isEmpty()) {
						Iterator parametroIter = parametroList.iterator();
						while (parametroIter.hasNext()) {
							IniParametro parametro = (IniParametro) parametroIter.next();
							/*
							 * Creo un objeto ParametroTramite y cargo todos los objetos asociados al parametro. En este objeto armo el paramtetro.
							 */
							// Traemos el proceso atributo.
							ProcesoAtributo atributo = getProcesoAtributoService().leerProcesoAtributo(parametro.getIdProcAtributo());
							// Una vez q tenemos el procesoAtributo le cargamos el objeto de tipo de atributo.
							atributo.setTipoAtributo(getTipoAtributoService().leerTipoAtributo(atributo.getTipoAtributo().getIdTipoAtributo()));
							List valores = new ArrayList();
							if (atributo.getLista()) {
								try {
									valores = getProcesoAtributoService().sqlLista(
											atributo, new Long(0));
								} catch (Exception e) {
									e.printStackTrace();
									// error.agregar("No se pudo obtener la lista del atributo '" + atributo.getNombre() + "'.");
								}
							}
							if (!parametro.isBuscaValor())
							{
								TramiteWrapper tramiteWrapper = new TramiteWrapper(parametro, atributo, valores);
								tramiteWrapper.setProceso(proceso);
								if (!lista) {
									parametroWrapperList.add(tramiteWrapper);
									lista = true;
								}
								else {
									parametroWrapperListDos.add(tramiteWrapper);
									lista = false;
								}
							}
						}
						if (parametroWrapperList.size() > parametroWrapperListDos.size()) {
							TramiteWrapper tramiteWrapper = new TramiteWrapper();
							tramiteWrapper.setCompletaPanel(true);
							tramiteWrapper.setBoolNombre(false);
							parametroWrapperListDos.add(tramiteWrapper);
						}

					}

					grabarTramite(bloqueoPlastico);
				}

			}

			List<BloqueoPlastico> bloqueoPlasticos1 = liqClienteService.buscarClienteTramiteAtomatico();

			Iterator iterator1 = bloqueoPlasticos1.iterator();
			while (iterator1.hasNext()) {
				BloqueoPlastico bloqueoPlastico = (BloqueoPlastico) iterator1.next();

				operadorAutomatico = ((OperadorService) appContext.getBean("operadorService")).leerOperador(bloqueoPlastico.getOperador());
				// operadorAutomatico = ((OperadorService)appContext.getBean("operadorService")).leerOperador(bloqueoPlastico.);
				// this.procesoService = (ProcesoService)appContext.getBean(PROCESO_SERVICE_NAME);
				// this.procesoService = (ProcesoService)appContext.getBean(PROCESO_SERVICE_NAME);

				List auxProc = new ArrayList();
				// Proceso proceso = new Proceso();
				parametroList = new ArrayList();
				parametroWrapperList = new ArrayList();
				parametroWrapperListDos = new ArrayList();
				tareaProcesoList = new ArrayList();
				auxTareaProcesoList = new ArrayList();

				Long duracionTarea = new Long(0);
				String cu = "";
				Boolean opcionA = false;
				boolean lista = false;

				Filtro filtro = new Filtro(Proceso.TITULO, Filtro.LIKEXS, bloqueoPlastico.getTipoProceso().trim());

				// Filtro filtro = new Filtro(Proceso.TITULO,Filtro.LIKEXS,"Reemplazo de Plastico");
				// Filtro filtro = new Filtro(Proceso.TITULO,Filtro.LIKEXS,"Modificacion de Estructura de Cuenta");
				// Filtro filtro = new Filtro(Proceso.TITULO,Filtro.LIKEXS,"Revision de linea de Credito");

				filtro.agregarCampoOperValor(Proceso.ACTIVO, Filtro.LIKE, new String("S"));
				// Aca buscamos el proceso que capturamos desde el menu.
				auxProc = getProcesoService().listarProceso(filtro);
				if (!auxProc.isEmpty()) {
					proceso = (Proceso) auxProc.get(0);
					// Una vez que tenemos el proceso empezamos a armarlo con los objetos correspondientes
					proceso.setRol(getRolService().leerRol(proceso.getRol().getIdRol()));
					proceso.setSupervisorDef(operadorService.leerOperador(proceso.getSupervisorDef().getCodigo()));
					String supervisor = proceso.getSupervisorDef().getApellido() + ", " + proceso.getSupervisorDef().getNombre();

					if (proceso.getActivo())
						opcionA = true;
					// Aqui buscamos todos los objetos IniParametros que estan relacionados con el proeso.
					filtro = new Filtro(IniParametro.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
					/* @I5547 */filtro.agregarOrderBy(IniParametro.ID_INI_PARAMETRO);
					/* @F5547 */parametroList = getIniParametroService().listarIniParametro(filtro);

					if (!parametroList.isEmpty()) {
						Iterator parametroIter = parametroList.iterator();
						while (parametroIter.hasNext()) {
							IniParametro parametro = (IniParametro) parametroIter.next();
							/*
							 * Creo un objeto ParametroTramite y cargo todos los objetos asociados al parametro. En este objeto armo el paramtetro.
							 */
							// Traemos el proceso atributo.
							ProcesoAtributo atributo = getProcesoAtributoService().leerProcesoAtributo(parametro.getIdProcAtributo());
							// Una vez q tenemos el procesoAtributo le cargamos el objeto de tipo de atributo.
							atributo.setTipoAtributo(getTipoAtributoService().leerTipoAtributo(atributo.getTipoAtributo().getIdTipoAtributo()));
							List valores = new ArrayList();
							if (atributo.getLista()) {
								try {
									valores = getProcesoAtributoService().sqlLista(
											atributo, new Long(0));
								} catch (Exception e) {
									e.printStackTrace();
									// error.agregar("No se pudo obtener la lista del atributo '" + atributo.getNombre() + "'.");
								}
							}
							if (!parametro.isBuscaValor())
							{
								TramiteWrapper tramiteWrapper = new TramiteWrapper(parametro, atributo, valores);
								tramiteWrapper.setProceso(proceso);
								if (!lista) {
									parametroWrapperList.add(tramiteWrapper);
									lista = true;
								}
								else {
									parametroWrapperListDos.add(tramiteWrapper);
									lista = false;
								}
							}
						}
						if (parametroWrapperList.size() > parametroWrapperListDos.size()) {
							TramiteWrapper tramiteWrapper = new TramiteWrapper();
							tramiteWrapper.setCompletaPanel(true);
							tramiteWrapper.setBoolNombre(false);
							parametroWrapperListDos.add(tramiteWrapper);
						}

					}

					grabarTramite(bloqueoPlastico);
				}

			}

		} catch (Exception e) {
			out.print("Error Activacion automatica de plasticos!!!");
			e.printStackTrace();
		} finally {
			out.close();
		}

	}


	public String grabarTramite(BloqueoPlastico bloqueoPlastico) throws ProcesoException {
		tramite = new Tramite();
		if (getUsarCU())
		{
			// tramite.setCu(this.getCu());
			tramite.setCu(bloqueoPlastico.getCuil());
			tramite.setTipoClave(proceso.getTipoClave());
		}
		detalleTramite = new DetalleTramite();
		detalleTramiteHis = new DetalleTramiteHis();
		atributoValorHis = new AtributoValorHis();

		if (validar(bloqueoPlastico)) {
			armarDetalleTramite();
			armarValor(bloqueoPlastico);
			armarTramiteParametro(bloqueoPlastico);
			armarDetalleTramiteHistorico();
			log.info("Ejecutando --> armarTramite()");

			try {
				tramite.setProceso(proceso);

				tramite.setFechaInicio(new Timestamp(Calendar.getInstance().getTime().getTime()));
				log.info("Fecha inicio: " + tramite.getFechaInicio());
				/*
				 * Aca filtramos todas las tareas-procesos para poder calcular la cantidad total de la duracion de la tarea para el tramite.
				 */

				Iterator tarProcIterator = auxTareaProcesoList.iterator();
				while (tarProcIterator.hasNext()) {
					TareaProceso tareaProceso = (TareaProceso) tarProcIterator.next();

					Tarea tarea = getTareaService().leerTarea(tareaProceso.getIdTarea());
					duracionTarea = new Long(duracionTarea.intValue() + tarea.getDuracion().intValue());
					log.info("Total horas tarea: " + duracionTarea);
				}

				Calendar auxCalendar = Fecha.calcularFechaFin(new Timestamp(Calendar.getInstance().getTime().getTime()), duracionTarea);
				tramite.setFechaFin(new Timestamp(auxCalendar.getTime().getTime()));
				tramite.setOperadorSup(operadorService.leerOperador(proceso.getSupervisorDef().getCodigo()));
				// tramite.setOperador(Session.getOperador());
				tramite.setOperador(operadorAutomatico);
				tramite.setEstado(getEstadoService().leerEstado(new Long(1)));
				tramite.setTimestamp(tramite.getFechaInicio());
				getTramiteService().grabarTramite(tramite);

				List atributosRestantes = new ArrayList();
				try {
					StringBuffer mensaje = new StringBuffer();
					atributosRestantes = getProcesoAtributoService().listarProcesoAtributo(filtroAtribRestantes);
					Iterator iter = atributosRestantes.iterator();
					while (iter.hasNext()) {
						ProcesoAtributo atributo = (ProcesoAtributo) iter.next();
						AtributoValor atributoValor = new AtributoValor();
						atributoValor.setProcesoAtributo(atributo);
						// tramite.getAtributoValor().add(atributoValor);
						atributoValor.setTramite(tramite);

						try {
							atributoValor.setValor(atributo.getValorDefecto());
							if (!atributo.getLocal() && atributo.getValor()) {
								atributoValor.setValor(getProcesoAtributoService().sqlValor(atributo, tramite.getIdTramite()));
							} else {
								atributoValor.setValor(atributo.getValorDefecto());
							}
						} catch (ProcesoAtributoException e) {
							mensaje.append("\n Error al leer el valor del atributo \"" + atributo.getNombre() + "\".");
							e.printStackTrace();
						}
						try {
							getAtributoValorService().grabarOActualizarAtributoValor(atributoValor);
						} catch (AtributoValorException e) {
							mensaje.append("\n Error al intentar grabar el valor del atributo \"" + atributo.getNombre() + "\".");
							e.printStackTrace();
						}
					}

					// Se impactan en la base los parametros que toman su valor con Script SQL.
					this.ImpactarAtributo(this.proceso, tramite.getIdTramite());

					// Actualiza el bloqueo de las app
					if (bloqueoPlastico.getNroDocumento() == null) {
						liqClienteService.updateTramiteAutomatico(bloqueoPlastico.getTramite());
					} else {
						liqClienteService.updateBloqueoPasticoApp(bloqueoPlastico.getNroDocumento());
					}

					// popup.setPopup(popup.ICONO_OK, "El tramite a sido iniciado con exito. \n El numero de tramite asignado es: " +
					// tramite.getIdTramite()
					// + mensaje.toString());
				} catch (ProcesoAtributoException e) {
					// popup.setPopup(popup.ICONO_OK, "El tramite a sido iniciado con exito. \n El numero de tramite asignado es: " +
					// tramite.getIdTramite()
					// + "\n Ocurrio un error al intentar grabar el listado de atributos.");
					e.printStackTrace();
				}

			} catch (TareaException e) {
				// popup.setPopup(popup.ICONO_FALLA, "Fallo al iniciar el tramite.");
				e.printStackTrace();
			} catch (OperadorException e) {
				// popup.setPopup(popup.ICONO_FALLA, "Fallo al iniciar el tramite.");
				e.printStackTrace();
			} catch (TramiteException e) {
				// popup.setPopup(popup.ICONO_FALLA, "Fallo al iniciar el tramite.");
				e.printStackTrace();
			} catch (EstadoException e) {
				// popup.setPopup(popup.ICONO_FALLA, "Fallo al iniciar el tramite.");
				e.printStackTrace();
			}
		}
		return null;
	}


	public void ImpactarAtributo(Proceso proceso, Long idTramite)
	{
		Filtro filtro = new Filtro(IniParametro.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
		filtro.agregarCampoOperValor(IniParametro.BUSCA_VALOR, Filtro.IGUAL, "'S'");
		/* @I5547 */filtro.agregarOrderBy(IniParametro.ID_INI_PARAMETRO);
		/* @F5547 */try {
			List iniparametroList = getIniParametroService().listarIniParametro(filtro);

			if (!iniparametroList.isEmpty()) {
				Iterator parametroIter = iniparametroList.iterator();
				while (parametroIter.hasNext()) {
					IniParametro parametro = (IniParametro) parametroIter.next();
					ProcesoAtributo atributo = getProcesoAtributoService().leerProcesoAtributo(parametro.getIdProcAtributo());
					getProcesoAtributoService().sqlGrabar(atributo, idTramite);
				}
			}
		} catch (IniParametroException e) {
			// popup.setPopup(popup.ICONO_OK, "El tramite a sido iniciado con exito. \n El numero de tramite asignado es: " + tramite.getIdTramite()
			// + "\n Ocurrio un error al intentar recuperar parametros.");
			e.printStackTrace();
		} catch (ProcesoAtributoException e) {
			// popup.setPopup(popup.ICONO_OK, "El tramite a sido iniciado con exito. \n El numero de tramite asignado es: " + tramite.getIdTramite()
			// + "\n Ocurrio un error al intentar recuperar parametros.");
			e.printStackTrace();
		}

	}


	/*
	 * este metodo arma el detalle historico y luego lo devuelve para guradarlo en la base.
	 */
	private void armarDetalleTramiteHistorico() {
		log.info("Ejecutando --> armarDetalleTramiteHistorico()");
		detalleTramiteHis = new DetalleTramiteHis();
		detalleTramiteHis.setFechaInicio(detalleTramite.getFechaInicio());
		detalleTramiteHis.setFechaFin(detalleTramite.getFechaFin());
		detalleTramiteHis.setProgreso(detalleTramite.getProgreso());
		detalleTramiteHis.setComentario(detalleTramite.getComentario());
		detalleTramiteHis.setEstado(detalleTramite.getEstado());
		detalleTramiteHis.setTareaProceso(detalleTramite.getTareaProceso());
		detalleTramiteHis.setTimestamp(detalleTramite.getTimestamp());
		// detalleTramiteHis.setOperador(Session.getOperador());
		detalleTramiteHis.setOperador(operadorAutomatico);

		detalleTramite.getDetalleTramiteHis().add(detalleTramiteHis);

	}


	private void armarTramiteParametro(BloqueoPlastico bloqueoPlastico) {
		log.info("Ejecutando --> armarTramiteParametro()");
		Iterator tramWrapIterator = parametroWrapperList.iterator();
		while (tramWrapIterator.hasNext()) {
			TramiteWrapper wrapper = (TramiteWrapper) tramWrapIterator.next();
			tramiteParametro = new TramiteParametro();
			tramiteParametro.setIdIniParametro(wrapper.getParametro().getIdIniParametro());
			// tramiteParametro.setValor(wrapper.devolverValor());
			tramiteParametro.setValor(bloqueoPlastico.getApellidoNombres());

			tramite.getTramitesParam().add(tramiteParametro);
		}

		tramWrapIterator = parametroWrapperListDos.iterator();
		while (tramWrapIterator.hasNext()) {
			TramiteWrapper wrapper = (TramiteWrapper) tramWrapIterator.next();
			if (!wrapper.isCompletaPanel()) {
				tramiteParametro = new TramiteParametro();
				tramiteParametro.setIdIniParametro(wrapper.getParametro().getIdIniParametro());
				// tramiteParametro.setValor(wrapper.devolverValor());
				tramiteParametro.setValor(bloqueoPlastico.getIdTitular() + "");
				tramite.getTramitesParam().add(tramiteParametro);
			}
		}
	}


	/*
	 * este metodo se utiliza para armar el atributo valor.
	 */
	private void armarValor(BloqueoPlastico bloqueoPlastico) {
		log.info("Ejecutando --> armarValor()");
		filtroAtribRestantes = new Filtro(ProcesoAtributo.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
		Iterator tramWrapIterator = parametroWrapperList.iterator();
		while (tramWrapIterator.hasNext()) {
			TramiteWrapper wrapper = (TramiteWrapper) tramWrapIterator.next();
			AtributoValor atributoValor = new AtributoValor();
			try {
				atributoValor.setProcesoAtributo(getProcesoAtributoService().leerProcesoAtributo(wrapper.getParametro().getIdProcAtributo()));
				if (wrapper.getParametro().isBuscaValor())
				{
					atributoValor.setValor(getProcesoAtributoService().sqlValor(wrapper.getAtributo(), tramite.getIdTramite()));
				} else
				{
					atributoValor.setValor(wrapper.devolverValor());
					// atributoValor.setValor(bloqueoPlastico.getApellidoNombres());
				}
				tramite.getAtributoValor().add(atributoValor);
				filtroAtribRestantes.agregarCampoOperValor(ProcesoAtributo.ID_PROCESO_ATRIBUTO, Filtro.DISTINTO, atributoValor.getProcesoAtributo()
						.getIdProcesoAtributo());
			} catch (ProcesoAtributoException e) {
				e.printStackTrace();
			}
		}

		tramWrapIterator = parametroWrapperListDos.iterator();
		while (tramWrapIterator.hasNext()) {
			TramiteWrapper wrapper = (TramiteWrapper) tramWrapIterator.next();
			AtributoValor atributoValor = new AtributoValor();
			try {
				if (!wrapper.isCompletaPanel()) {
					atributoValor.setProcesoAtributo(getProcesoAtributoService().leerProcesoAtributo(wrapper.getParametro().getIdProcAtributo()));
					atributoValor.setValor(wrapper.devolverValor());
					// atributoValor.setValor(bloqueoPlastico.getIdTitular()+"");
					// atributoValor.setValor(bloqueoPlastico.getCuil());
					tramite.getAtributoValor().add(atributoValor);
					filtroAtribRestantes.agregarCampoOperValor(ProcesoAtributo.ID_PROCESO_ATRIBUTO, Filtro.DISTINTO, atributoValor
							.getProcesoAtributo().getIdProcesoAtributo());
				}
			} catch (ProcesoAtributoException e) {
				e.printStackTrace();
			}
		}
	}


	public boolean validar(BloqueoPlastico bloqueoPlastico) {
		log.info("Ejecutando --> validar()");
		// //error.borrar();
		// Map atributosMap = new HashMap();
		// if(!parametroWrapperList.isEmpty()){
		// Iterator iterator = parametroWrapperList.iterator();
		// while (iterator.hasNext()) {
		// TramiteWrapper wrapper = (TramiteWrapper) iterator.next();
		// //atributosMap.put(wrapper.getAtributo().getNombre(), wrapper.devolverValor());
		// atributosMap.put(wrapper.getAtributo().getNombre(), bloqueoPlastico.getApellidoNombres());
		//
		// }
		// }
		// if(!parametroWrapperListDos.isEmpty()){
		// Iterator iterator = parametroWrapperListDos.iterator();
		// while (iterator.hasNext()) {
		// TramiteWrapper wrapper = (TramiteWrapper) iterator.next();
		// if (!wrapper.isCompletaPanel()) {
		// //atributosMap.put(wrapper.getAtributo().getNombre(), wrapper.devolverValor());
		// atributosMap.put(wrapper.getAtributo().getNombre(), bloqueoPlastico.getIdTitular());
		// }
		// }
		// }
		//
		//
		//
		// ooooooooo

		Map atributosMap = new HashMap();
		if (!parametroWrapperList.isEmpty()) {
			Iterator iterator = parametroWrapperList.iterator();
			while (iterator.hasNext()) {
				TramiteWrapper wrapper = (TramiteWrapper) iterator.next();
				if (wrapper.getAtributo().getNombre().equals("Base")) {
					atributosMap.put(wrapper.getAtributo().getNombre(), bloqueoPlastico.getApellidoNombres());
					wrapper.setValorTexto(bloqueoPlastico.getApellidoNombres());
				}
				if (wrapper.getAtributo().getNombre().equals("promotor")) {
					atributosMap.put(wrapper.getAtributo().getNombre(), 1);
					wrapper.setValorTexto(1 + "");
				}
				if (wrapper.getAtributo().getNombre().equals("tipoIndividuo")) {
					atributosMap.put(wrapper.getAtributo().getNombre(), 1);
					wrapper.setValorTexto(1 + "");
				}

				if (wrapper.getAtributo().getNombre().equals("nroCuenta")) {
					atributosMap.put(wrapper.getAtributo().getNombre(), bloqueoPlastico.getIdTitular());
					wrapper.setValorNroEntero(new Integer(bloqueoPlastico.getIdTitular().toString()));
				}

				if (wrapper.getAtributo().getNombre().equals("nota_cred")) {
					atributosMap.put(wrapper.getAtributo().getNombre(), bloqueoPlastico.getObservacionTexto());
					wrapper.setValorTexto(bloqueoPlastico.getObservacionTexto());
				}

				// atributosMap.put(wrapper.getAtributo().getNombre(), wrapper.devolverValor());
			}
		}
		if (!parametroWrapperListDos.isEmpty()) {
			Iterator iterator = parametroWrapperListDos.iterator();
			while (iterator.hasNext()) {
				TramiteWrapper wrapper = (TramiteWrapper) iterator.next();
				if (!wrapper.isCompletaPanel()) {
					if (wrapper.getAtributo().getNombre().equals("nroCuenta")) {
						atributosMap.put(wrapper.getAtributo().getNombre(), bloqueoPlastico.getIdTitular());
						wrapper.setValorNroEntero(new Integer(bloqueoPlastico.getIdTitular().toString()));
					}
					if (wrapper.getAtributo().getNombre().equals("tipoIndividuo")) {
						atributosMap.put(wrapper.getAtributo().getNombre(), 1);
						wrapper.setValorTexto(1 + "");
					}

					if (wrapper.getAtributo().getNombre().equals("cuil")) {
						atributosMap.put(wrapper.getAtributo().getNombre(), bloqueoPlastico.getCuil());
						wrapper.setValorCuit(bloqueoPlastico.getCuil());
					}

					if (wrapper.getAtributo().getNombre().equals("nota_cred")) {
						atributosMap.put(wrapper.getAtributo().getNombre(), bloqueoPlastico.getObservacionTexto());
						wrapper.setValorTexto(bloqueoPlastico.getObservacionTexto());
					}

					// atributosMap.put(wrapper.getAtributo().getNombre(), wrapper.devolverValor());
				}
			}
		}

		// if(!parametroWrapperList.isEmpty()){
		// log.info("Validando Lista Wrapper Uno");
		// Iterator iterator = parametroWrapperList.iterator();
		// while (iterator.hasNext()) {
		// TramiteWrapper wrapper = (TramiteWrapper) iterator.next();
		// String logError = wrapper.validar(getProcesoAtributoService(), atributosMap);
		// if(logError != null){
		// //error.agregar(logError);
		// }
		// }
		// }
		// else{
		// //error.agregar(Error.TRAMITES_WRAPPER_LIST_VACIA);
		// }

		// if(!parametroWrapperListDos.isEmpty()){
		// log.info("Validando Lisa Wrapper Dos");
		// Iterator iterator = parametroWrapperListDos.iterator();
		// while (iterator.hasNext()) {
		// TramiteWrapper wrapper = (TramiteWrapper) iterator.next();
		// String logError = wrapper.validar(getProcesoAtributoService(), atributosMap);
		// if(logError != null){
		// //error.agregar(logError);
		// }
		// }
		// }
		Filtro filtro;
		try {
			log.info("Validando procesos asociados");
			filtro = new Filtro(TareaProceso.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
			auxTareaProcesoList = getTareaProcesoService().listarTareaProceso(filtro);
			if (auxTareaProcesoList.isEmpty()) {
				// error.agregar(Error.TAREA_PROCESO_NO_EXISTENTE);
			}
		} catch (TareaProcesoException e1) {
			e1.printStackTrace();
		}

		try {
			log.info("Validando que haya una tarea proceso al menos de orden 1");
			filtro = new Filtro(TareaProceso.ID_PROCESO, Filtro.IGUAL, proceso.getIdProceso());
			filtro.agregarCampoOperValor(TareaProceso.ORDEN, Filtro.IGUAL, new Long(1));
			tareaProcesoList = getTareaProcesoService().listarTareaProceso(filtro);
			if (tareaProcesoList.isEmpty()) {
				// error.agregar(Error.TAREA_PROCESO_NO_INICIAL);
			}
		} catch (TareaProcesoException e) {

			e.printStackTrace();
		}

		// try {
		// if(this.getUsarCU()){
		// if(cu != null || !cu.equals("")){
		// if (cu.length() == 11) {
		// CuitValido cuitValido = new CuitValido(cu);
		// } else {
		// //error.agregar(Error.TRAMITES_CU_LONGITUD);
		// }
		// }
		// else{
		// //error.agregar(Error.TRAMITES_CU_REQUERIDO);
		// }
		// }
		// } catch (CuitNoValidoException e) {
		// //error.agregar(Error.TRAMITES_CU_INVALIDO);
		// }

		return true;
	}


	public boolean getUsarCU()
	{
		return proceso.getRequiereCU() != null && proceso.getRequiereCU().compareTo("S") == 0 ? true : false;
	}


	public String getCu() {
		return cu;
	}


	public void setCu(String cu) {
		this.cu = cu;
	}


	private void armarDetalleTramite() {
		log.info("Ejecutando --> armarDetalleTramite()");
		try {
			TareaProceso tareaProceso = (TareaProceso) tareaProcesoList.get(0);
			Tarea tarea = getTareaService().leerTarea(tareaProceso.getIdTarea());
			detalleTramite.setTramite(new Tramite());
			detalleTramite.setFechaInicio(new Timestamp(Calendar.getInstance().getTime().getTime()));
			Calendar fechaFin = Fecha.calcularFechaFin(new Timestamp(Calendar.getInstance().getTime().getTime()), new Long(tarea.getDuracion()
					.longValue()));
			detalleTramite.setFechaFin(new Timestamp(fechaFin.getTime().getTime()));
			detalleTramite.setEstado(getEstadoService().leerEstado(new Long(1)));
			detalleTramite.setTareaProceso(tareaProceso);
			detalleTramite.setTimestamp(detalleTramite.getFechaInicio());
			detalleTramite.setProgreso(new Integer(0));
			tramite.getDetalleTramite().add(detalleTramite);

		} catch (EstadoException e) {
			e.printStackTrace();
		} catch (TareaException e) {
			e.printStackTrace();
		}
	}


	private void activarCuenta(Operador operadorAutomatico, PlasticoEstado estadoActivado, PlasticoEstado estadoDesactivado)
			throws Exception, PlasticoClienteException {
		ultimoTitular.setDiaCierre(clienteTransaccionService.getDiaPagoCliente(ultimoTitular.getIdCliente()));
		plasticoClienteService.activarPlasticos(listaCuenta, ultimoTitular, estadoActivado, estadoDesactivado, plasticoOperacion, operadorAutomatico);
	}


	private void nuevoCiclo(PlasticoCliente p) throws ClienteTransaccionException {
		listaCuenta.clear();
		if (p.esPlasticoTitular())
			ultimoTitular = p.getClienteTransaccion();
		else
			ultimoTitular = clienteTransaccionService.leerCliente(p.getClienteTransaccion().getIdTitular());
		plasticoOperacion = p.getOperacion();
		listaCuenta.add(p);
	}


	private boolean pertenece(PlasticoCliente p) {
		Long idTitular = p.esPlasticoTitular() ? p.getClienteTransaccion().getIdCliente() : p.getClienteTransaccion().getIdTitular();
		return (ultimoTitular.getIdCliente().equals(idTitular))
				&& plasticoOperacion.getIdPlasticoOperacion().equals(p.getOperacion().getIdPlasticoOperacion());
	}

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
	// private static final String PROCESO_SERVICE_NAME = "procesoService";
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
	private static final String LIQ_CLIENTE_NAME = "liqClienteService";

	/*
	 * Objetos services para el modulo.
	 */

	private LiqClienteService liqClienteService = null;
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
	// private ProcesoService procesoService = null;
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


	// private RolService rolService = null;

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


	public LiqClienteService getLiqClienteService() {
		return liqClienteService;
	}


	public void setLiqClienteService(LiqClienteService liqClienteService) {
		this.liqClienteService = liqClienteService;
	}

}

package com.bizitglobal.webapp.faces.beans.workflow;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.operador.negocio.Rol;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.TareaDetalleTabla;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.TramiteTabla;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.workflow.exception.DetalleTramiteException;
import com.bizitglobal.workflow.exception.EstadoException;
import com.bizitglobal.workflow.exception.ProcesoException;
import com.bizitglobal.workflow.exception.TramiteException;
import com.bizitglobal.workflow.negocio.AtributoProcesoTarea;
import com.bizitglobal.workflow.negocio.AtributoValor;
import com.bizitglobal.workflow.negocio.DetalleTramite;
import com.bizitglobal.workflow.negocio.Estado;
import com.bizitglobal.workflow.negocio.ProcesoAtributo;
import com.bizitglobal.workflow.negocio.Tramite;


@SuppressWarnings({"rawtypes","unchecked"})
public class EscritorioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(EscritorioBean.class);
	private String paginaOrigen;
	private Operador operador;
	private Rol rol;
	private Date fechaSelec;
	private List supervisores;
	private Long supervisorSeleccionado;
	private HtmlSelectOneMenu supervisorHtml = new HtmlSelectOneMenu();
	private List procesos;
	private String procesoSeleccionado;
	private String paramInicio;
	private Date fechaDesde;
	private Date fechaHasta;
	private String selectRadioButton;
	private boolean vistaSuper;
	private String tituloTramite;
	private Long nroTramite;
	private String nroCU;

	// LISTAS DE LAS TABLAS
	private List tablaTramitesAll;
	private List tablaTramites;
	private List tablaTareasPendientes;
	private List tablaTareasTomadas;
	private List tablaDetalleTareas;

	// LISTAS AUXILIARES
	private List estados;

	private Long idEstado;
	private Integer indexTab;
	private boolean tabEscritorio;
	private boolean tabTatea;
	private boolean verLeyendaTramite;

	private String focoHidden;


	public EscritorioBean() {
		super.tituloLargo = "Tarjeta Fiel - Administración de tramites";
		super.tituloCorto = "Escritorio";
		try {
			estados = workflowService.getEstadoService().listarEstado(new Filtro());
		} catch (EstadoException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean getVistaSuper() {
		return vistaSuper;
	}


	public void setVistaSuper(boolean vistaSuper) {
		this.vistaSuper = vistaSuper;
	}


	public String getPaginaOrigen() {
		return paginaOrigen;
	}


	public void setPaginaOrigen(String paginaOrigen) {
		this.paginaOrigen = paginaOrigen;
	}


	public boolean getVerLeyendaTramite() {
		return verLeyendaTramite;
	}


	public Integer getIndexTab() {
		return indexTab;
	}


	public void setIndexTab(Integer indexTab) {
		this.indexTab = indexTab;
	}


	public boolean getTabEscritorio() {
		return tabEscritorio;
	}


	public void setTabEscritorio(boolean tabEscritorio) {
		this.tabEscritorio = tabEscritorio;
	}


	public boolean getTabTatea() {
		return tabTatea;
	}


	public void setTabTatea(boolean tabTatea) {
		this.tabTatea = tabTatea;
	}


	public Operador getOperador() {
		return operador;
	}


	public void setOperador(Operador operador) {
		this.operador = operador;
	}


	public Date getFechaSelec() {
		return fechaSelec;
	}


	public void setFechaSelec(Date fechaSelec) {
		this.fechaSelec = fechaSelec;
	}


	public List getTablaDetalleTareas() {
		return tablaDetalleTareas;
	}


	public void setTablaDetalleTareas(List tablaDetalleTareas) {
		this.tablaDetalleTareas = tablaDetalleTareas;
	}


	public List getTablaTareasTomadas() {
		return tablaTareasTomadas;
	}


	public void setTablaTareasTomadas(List tablaTareasTomadas) {
		this.tablaTareasTomadas = tablaTareasTomadas;
	}


	public List getTablaTareasPendientes() {
		return tablaTareasPendientes;
	}


	public void setTablaTareasPendientes(List tablaTareasPendientes) {
		this.tablaTareasPendientes = tablaTareasPendientes;
	}


	public List getTablaTramites() {
		return tablaTramites;
	}


	public void setTablaTramites(List tablaTramites) {
		this.tablaTramites = tablaTramites;
	}


	public List getProcesos() {
		return procesos;
	}


	public void setProcesos(List procesos) {
		this.procesos = procesos;
	}


	public String getProcesoSeleccionado() {
		return procesoSeleccionado;
	}


	public void setProcesoSeleccionado(String procesoSeleccionado) {
		this.procesoSeleccionado = procesoSeleccionado;
	}


	public List getSupervisores() {
		return supervisores;
	}


	public void setSupervisores(List supervisores) {
		this.supervisores = supervisores;
	}


	public HtmlSelectOneMenu getSupervisorHtml() {
		return supervisorHtml;
	}


	public void setSupervisorHtml(HtmlSelectOneMenu supervisorHtml) {
		this.supervisorHtml = supervisorHtml;
	}


	public Long getSupervisorSeleccionado() {
		return supervisorSeleccionado;
	}


	public void setSupervisorSeleccionado(Long supervisorSeleccionado) {
		this.supervisorSeleccionado = supervisorSeleccionado;
	}


	public String getParamInicio() {
		return paramInicio;
	}


	public void setParamInicio(String paramInicio) {
		this.paramInicio = paramInicio;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	public String getSelectRadioButton() {
		return selectRadioButton;
	}


	public void setSelectRadioButton(String selectRadioButton) {
		this.selectRadioButton = selectRadioButton;
	}


	public int getIdTab() {
		return (vistaSuper) ? 1 : 0;
	}


	public String getTituloTramite() {
		return tituloTramite;
	}


	public Long getNroTramite() {
		return nroTramite;
	}


	public void setNroTramite(Long nroTramite) {
		this.nroTramite = nroTramite;
	}


	public String getNroCU() {
		return nroCU;
	}


	public void setNroCU(String nroCU) {
		this.nroCU = nroCU;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public void borrar() {
		borrarBaseBean();
		operador = Session.getOperador();
		paramInicio = new String();
		supervisores = new ArrayList();
		supervisorSeleccionado = new Long(0);
		procesos = new ArrayList();
		procesoSeleccionado = "";
		Calendar fecha = Calendar.getInstance();
		fechaHasta = new Timestamp(fecha.getTime().getTime());
		fecha.add(Calendar.MONTH, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());
		selectRadioButton = new String("1");

		tablaTramites = new ArrayList();
		tablaTareasPendientes = new ArrayList();
		tablaTareasTomadas = new ArrayList();
		tablaDetalleTareas = new ArrayList();

		verLeyendaTramite = false;
		indexTab = new Integer(0);
		tabEscritorio = false;
		tabTatea = true;
		vistaSuper = false;
		supervisorHtml.setValue(new Long(0));
		tituloTramite = "";
		nroTramite = new Long(0);
		focoHidden = "tab1:nroTramite";
		idEstado = new Long(0);
	}


	public String inicializar() {
		borrar();
		paginaOrigen = "escritorio";
		rol = operador.getRol();
		try {
			tablaTramitesAll = EscritorioUtil.cargarListaTramites(operador, workflowService, estados);
		} catch (Exception e) {
			e.printStackTrace();
			error.agregar("Error al leer los tramites.");
		}
		filtrarAllTram();
		try {
			tablaTareasPendientes = EscritorioUtil.cargarListaPendientes(operadorService.getOperadorService(), workflowService, estados, rol);
		} catch (Exception e) {
			e.printStackTrace();
			error.agregar("Ocurrio un error al leer las tareas pendientes.");
		}
		try {
			tablaTareasTomadas = EscritorioUtil.cargarListaTomadas(operador, operadorService.getOperadorService(), workflowService, estados, rol);
		} catch (Exception e) {
			e.printStackTrace();
			error.agregar("Ocurrio un error al leer las tareas tomadas.");
		}
		// log.info("Tamanio tablaTareasPendientes :"+ tablaTareasPendientes.size());
		// log.info("Tamanio tablaTareasTomadas :"+ tablaTareasTomadas.size());

		return paginaOrigen;
	}


	public String volver() {
		if (paginaOrigen.equals("escritorio")) {
			return inicializar();
		} else {
			return buscarTramites();
		}
	}


	public boolean validar() {

		return false;
	}


	public String tomarTarea() {
		Long idDetalle = new Long(Session.getRequestParameter("idDetalle"));
		try {
			DetalleTramite dTVerif = workflowService.getDetalleTramiteService().leerDetalleTramite(idDetalle);
			if (dTVerif.getEstado().getIdEstado().equals(new Long(2))
					|| dTVerif.getEstado().getIdEstado().equals(new Long(3))) {
				error.agregar("La tarea que intenta tomar ya ha sido asignada a otro usuario. Refresque la pantalla");
				return null;
			}
		} catch (DetalleTramiteException e3) {
			e3.printStackTrace();
		}

		int indice = tablaTareasPendientes.indexOf(new TareaDetalleTabla(new DetalleTramite(idDetalle)));
		TareaDetalleTabla tareaDetalleTabla = (TareaDetalleTabla) tablaTareasPendientes.get(indice);
		DetalleTramite detalleTramite = tareaDetalleTabla.getDetalleTramite();
		detalleTramite.setOperadorResponsable(operador);
		detalleTramite.setOperador(operador);
		detalleTramite.setFechaInicioReal(new Timestamp(Calendar.getInstance().getTime().getTime()));
		if (detalleTramite.getTramite().getFechaInicioReal() == null) {
			detalleTramite.getTramite().setFechaInicioReal(new Timestamp(Calendar.getInstance().getTime().getTime()));
			// ACA PONER LA LOGICA DE INICIALIZACION DE LOS ATRIBUTOS PARA EL TRAMITE
			// creo que ya lo estoy haciendo cuando creo el tramite. VERIFICAR
		}
		try {
			detalleTramite.setEstado(EscritorioUtil.buscarEstado(new Estado(new Long(2)), estados));
			workflowService.getDetalleTramiteService().actualizarDetalleTramite(detalleTramite);
			log.info("Actualizando el DetalleTramite -> " + detalleTramite);
			if (detalleTramite.getTramite().getEstado().getIdEstado().equals(new Long(1))) {
				detalleTramite.getTramite().setEstado(EscritorioUtil.buscarEstado(new Estado(new Long(2)), estados));
				workflowService.getTramiteService().actualizarTramite(detalleTramite.getTramite());
				log.info("Actualizando el Tramite -> " + detalleTramite.getTramite());
			}
			tablaTareasPendientes.remove(indice);
			tareaDetalleTabla.setDetalleTramite(detalleTramite);
			tablaTareasTomadas.add(tareaDetalleTabla);
			vistaSuper = false;
		} catch (DetalleTramiteException e2) {
			e2.printStackTrace();
		} catch (TramiteException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// verifico si en esta tarea se debe saetear algun valor de un atributo
		try {
			List atributosTareas = workflowService.getAtributoProcesoTareaService().listarAtributoProcesoTarea(
					new Filtro(AtributoProcesoTarea.ID_TAREA_PROCESO, Filtro.IGUAL, detalleTramite.getTareaProceso().getIdTareaProceso()));
			if (!atributosTareas.isEmpty()) {
				Iterator iter = atributosTareas.iterator();
				while (iter.hasNext()) {
					AtributoProcesoTarea element = (AtributoProcesoTarea) iter.next();
					if (element.getResetea()) {
						Filtro filtro = new Filtro(AtributoValor.ID_PROCESO_ATRIBUTO, Filtro.IGUAL, element.getIdProcesoAtributo());
						filtro.agregarCampoOperValor(AtributoValor.ID_TRAMITE, Filtro.IGUAL, detalleTramite.getTramite().getIdTramite());
						List valorList = workflowService.getAtributoValorService().listarAtributoValor(filtro);
						AtributoValor valor = new AtributoValor();
						if (!valorList.isEmpty()) {
							valor = (AtributoValor) valorList.get(0);
						} else {
							valor.setProcesoAtributo(new ProcesoAtributo(element.getIdProcesoAtributo()));
							valor.setTramite(detalleTramite.getTramite());
						}
						valor.setValor(element.getValor());
						workflowService.getAtributoValorService().grabarOActualizarAtributoValor(valor);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public String verTarea() {
		vistaSuper = false;
		Long idDetalle = new Long(Session.getRequestParameter("idDetalle"));
		int indice = tablaTareasTomadas.indexOf(new TareaDetalleTabla(new DetalleTramite(idDetalle)));
		TareaDetalleTabla tareaDetalleTabla = (TareaDetalleTabla) tablaTareasTomadas.get(indice);
		log.info("Llamando al formulario de Tarea");
		FormularioBean formularioBean = (FormularioBean) Session.getBean("FormularioBean");
		formularioBean.setVistaAdmin(false);
		return formularioBean.inicializar(tareaDetalleTabla, estados);
	}


	public String verTareaSuper() {
		vistaSuper = true;
		Long idDetalle = new Long(Session.getRequestParameter("idDetalle"));
		int indice = tablaDetalleTareas.indexOf(new TareaDetalleTabla(new DetalleTramite(idDetalle)));
		TareaDetalleTabla tareaDetalleTabla = (TareaDetalleTabla) tablaDetalleTareas.get(indice);
		log.info("Llamando al formulario de Tarea como Supervisor");
		FormularioBean formularioBean = (FormularioBean) Session.getBean("FormularioBean");
		formularioBean.setVistaAdmin(false);
		return formularioBean.inicializarSuperTarea(tareaDetalleTabla, estados);
	}


	public String verTareaAdmin() {
		vistaSuper = true;
		Long idDetalle = new Long(Session.getRequestParameter("idDetalle"));
		int indice = tablaDetalleTareas.indexOf(new TareaDetalleTabla(new DetalleTramite(idDetalle)));
		TareaDetalleTabla tareaDetalleTabla = (TareaDetalleTabla) tablaDetalleTareas.get(indice);
		log.info("Llamando al formulario de Tarea como Supervisor");
		FormularioBean formularioBean = (FormularioBean) Session.getBean("FormularioBean");
		formularioBean.setVistaAdmin(true);
		return formularioBean.inicializarSuperTarea(tareaDetalleTabla, estados);
	}


	public String mostrarTareas() {
		Long idTramite = new Long(Session.getRequestParameter("idTramite"));
		int indice = tablaTramites.indexOf(new TramiteTabla(new Tramite(idTramite), ""));
		log.info("Indice del Array => " + indice);
		TramiteTabla tramiteTabla = (TramiteTabla) tablaTramites.get(indice);
		tituloTramite = tramiteTabla.getTramite().getIdTramite() + " - " + tramiteTabla.getNombreTramite() + " ("
				+ tramiteTabla.getTramite().getProceso().getTitulo() + ")";
		try {
			tablaDetalleTareas = EscritorioUtil.cargarListaTareasTramite(tramiteTabla.getTramite(), workflowService,
					operadorService.getOperadorService(), estados);
		} catch (Exception e) {
			e.printStackTrace();
			error.agregar("Ocurrio un error al leer las tareas.");
		}
		return "";
	}


	public String mostrarTramite() {
		vistaSuper = true;
		Long idTramite = new Long(Session.getRequestParameter("idTramite"));
		int indice = tablaTramites.indexOf(new TramiteTabla(new Tramite(idTramite), ""));
		TramiteTabla tramiteTabla = (TramiteTabla) tablaTramites.get(indice);
		log.info("Llamando al formulario de Ttramite como Supervisor");
		FormularioBean formularioBean = (FormularioBean) Session.getBean("FormularioBean");
		formularioBean.setVistaAdmin(false);
		return formularioBean.inicializarSuperTramite(tramiteTabla, estados);
	}


	public String mostrarTramiteAdmin() {
		vistaSuper = true;
		Long idTramite = new Long(Session.getRequestParameter("idTramite"));
		int indice = tablaTramites.indexOf(new TramiteTabla(new Tramite(idTramite), ""));
		TramiteTabla tramiteTabla = (TramiteTabla) tablaTramites.get(indice);
		log.info("Llamando al formulario de Ttramite como Supervisor");
		FormularioBean formularioBean = (FormularioBean) Session.getBean("FormularioBean");
		formularioBean.setVistaAdmin(true);
		return formularioBean.inicializarSuperTramite(tramiteTabla, estados);
	}


	public String cancelarTramite() {
		Long idTramite = new Long(Session.getRequestParameter("idTramite"));
		int indice = tablaTramites.indexOf(new TramiteTabla(new Tramite(idTramite), ""));
		log.info("Indice del Array => " + indice);
		TramiteTabla tramiteTabla = (TramiteTabla) tablaTramites.get(indice);
		log.info("Cancelando el Ttramite: " + tramiteTabla.getNombreTramite());

		Tramite tramite = tramiteTabla.getTramite();
		tramite.setOperador(operador);
		tramite.setFechaFinReal(new Timestamp(Calendar.getInstance().getTime().getTime()));
		tramite.setEstado(EscritorioUtil.buscarEstado(new Estado(new Long(4)), estados));
		tramite.setTimestamp(tramite.getFechaFinReal());
		try {
			workflowService.getTramiteService().actualizarTramite(tramite);
			Filtro filtro = new Filtro(DetalleTramite.ID_TRAMITE, Filtro.IGUAL, tramite.getIdTramite());
			filtro.agregarCampoOperValor(DetalleTramite.ID_ESTADO, Filtro.DISTINTO, "6");
			Iterator iter = workflowService.getDetalleTramiteService().listarDetalleTramite(filtro).iterator();
			while (iter.hasNext()) {
				DetalleTramite detalleTramite = (DetalleTramite) iter.next();
				detalleTramite.setOperador(operador);
				detalleTramite.setFechaFinReal(new Timestamp(Calendar.getInstance().getTime().getTime()));
				detalleTramite.setEstado(EscritorioUtil.buscarEstado(new Estado(new Long(4)), estados));
				if (detalleTramite.getOperadorResponsable().getCodigo() != null) {
					detalleTramite.setOperadorResponsable(
							operadorService.getOperadorService().leerOperador(detalleTramite.getOperadorResponsable().getCodigo()));
				}
				workflowService.getDetalleTramiteService().actualizarDetalleTramite(detalleTramite);
			}
			tablaTramites.remove(indice);
		} catch (DetalleTramiteException e2) {
			e2.printStackTrace();
		} catch (TramiteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}


	public String filtrarTramites() {
		idEstado = new Long(Session.getRequestParameter("idEstado"));
		filtrarAllTram();
		return "";
	}


	private void filtrarAllTram() {
		tablaTramites.clear();
		Iterator iter = tablaTramitesAll.iterator();
		if (idEstado.equals(new Long(0))) {
			Long estadoTram;
			while (iter.hasNext()) {
				TramiteTabla tramiteTabla = (TramiteTabla) iter.next();
				estadoTram = tramiteTabla.getTramite().getEstado().getIdEstado();
				if (estadoTram.equals(new Long(1))
						|| estadoTram.equals(new Long(2))
						|| estadoTram.equals(new Long(3))) {
					tablaTramites.add(tramiteTabla);
				}
			}
		} else {
			while (iter.hasNext()) {
				TramiteTabla tramiteTabla = (TramiteTabla) iter.next();
				if (tramiteTabla.getTramite().getEstado().getIdEstado().equals(idEstado)) {
					tablaTramites.add(tramiteTabla);
				}
			}
		}
		if (tablaTramites.isEmpty()) {
			verLeyendaTramite = true;
		} else {
			verLeyendaTramite = false;
		}
	}


	public String inicializarAdmin() {
		borrar();
		tablaTramitesAll = new ArrayList();
		paginaOrigen = "adminTramites";
		try {
			supervisores.add(new SelectItem(new Long(0), "Todos"));
			supervisores.addAll(workflowService.getProcesoService().buscarSupervisores());
			procesos.add(new SelectItem("", "Todos"));
			procesos.addAll(workflowService.getProcesoService().listarProcesos(new Long(0)));
		} catch (ProcesoException e) {
			e.printStackTrace();
		}
		// tablaTramitesAll = EscritorioUtil.cargarListaTramites(operador,workflowService, estados);
		// tablaTramites = new ArrayList(tablaTramitesAll);
		// tablaTareasPendientes = EscritorioUtil.cargarListaPendientes(operadorService.getOperadorService(), workflowService, estados, rol);
		// tablaTareasTomadas = EscritorioUtil.cargarListaTomadas(operador, operadorService.getOperadorService(), workflowService, estados, rol);
		// log.info("Tamaño tablaTareasPendientes :"+ tablaTareasPendientes.size());
		// log.info("Tamaño tablaTareasTomadas :"+ tablaTareasTomadas.size());

		return paginaOrigen;
	}


	public void cambioSupervisor(ValueChangeEvent event) {
		Long valorSeleccionado = new Long(supervisorHtml.getValue().toString());
		try {
			procesos.clear();
			procesos.add(new SelectItem("", "Todos"));
			procesos.addAll(workflowService.getProcesoService().listarProcesos(valorSeleccionado));
		} catch (ProcesoException e) {
			e.printStackTrace();
		}
	}


	public String buscarTramites() {
		String param = Session.getRequestParameter("idEstado");
		idEstado = null;
		if (param != null) {
			if (!param.equals("")) {
				idEstado = new Long(param);
			}
		}
		tablaDetalleTareas.clear();
		tablaTramitesAll.clear();
		error.borrar();
		if (fechaDesde == null || fechaHasta == null) {
			error.agregar("Debe ingresar un rango de fechas");
			return "";
		}
		try {
			tablaTramitesAll = workflowService.getTramiteService().buscarTodos(
					paramInicio, procesoSeleccionado, supervisorSeleccionado, idEstado,
					new Timestamp(fechaDesde.getTime()), new Timestamp(fechaHasta.getTime()),
					Integer.parseInt(selectRadioButton));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (TramiteException e) {
			e.printStackTrace();
		}
		try {
			tablaTramites = EscritorioUtil.leerTramites(workflowService, estados, tablaTramitesAll);
		} catch (Exception e) {
			e.printStackTrace();
			error.agregar("Ocurrio un error al buscar los tramites.");
		}
		return paginaOrigen;
	}


	public String buscarXnro() {
		focoHidden = "tab1:nroTramite";
		tablaDetalleTareas.clear();
		tablaTramitesAll.clear();
		error.borrar();

		if (nroTramite == null || nroTramite.equals(new Long(0))) {
			error.agregar("Debe ingresar un valor.");
			return "";
		}
		try {
			tablaTramitesAll.add(workflowService.getTramiteService().leerTramite(nroTramite));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (TramiteException e) {
			e.printStackTrace();
		}
		try {
			tablaTramites = EscritorioUtil.leerTramites(workflowService, estados, tablaTramitesAll);
		} catch (Exception e) {
			e.printStackTrace();
			error.agregar("Ocurrio un error al buscar el tramite.");
		}
		return paginaOrigen;
	}


	public String buscarXcu() {
		focoHidden = "tab1:nroCU";
		tablaDetalleTareas.clear();
		tablaTramitesAll.clear();
		error.borrar();

		if (nroCU == null || nroCU.equals(new Long(0))) {
			error.agregar("Debe ingresar un valor.");
			return "";
		}
		try {
			tablaTramitesAll = workflowService.getTramiteService().leerTramiteCU(nroCU);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (TramiteException e) {
			e.printStackTrace();
		}
		try {
			tablaTramites = EscritorioUtil.leerTramites(workflowService, estados, tablaTramitesAll);
		} catch (Exception e) {
			e.printStackTrace();
			error.agregar("Ocurrio un error al buscar el tramite.");
		}
		return paginaOrigen;
	}
}

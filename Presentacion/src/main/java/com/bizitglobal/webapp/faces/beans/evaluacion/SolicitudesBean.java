package com.bizitglobal.webapp.faces.beans.evaluacion;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EstadosException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.PromotorException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudImprimibleException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Estados;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.NroVerificador;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Promotor;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudImprimible;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudIndividuo;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.Util.SolicitudesEvaluacionUtil;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.IndividuosAsignadosWrapper;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.SolicitudesSeleccionables;
import com.bizitglobal.webapp.faces.util.Convertidores;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class SolicitudesBean extends BaseBean {
	private static Logger log = Logger.getLogger(SolicitudesBean.class);

	public final static int MAX_SOLICITUDES = 5;

	// atributos a ser utilizados por el filtro;
	private String nroSolicitud = "";
	private String promotor = "";

	// atributos para la pagina listarSolicitudes
	private boolean mostrarIndividuos = false;
	private List listaSolicitudes;
	private List listDeIndividuosAsignados;
	private Solicitud solicitud;
	private String idSolicitudHidden;
	private String idIndHidden;

	private boolean selSolicitudes;
	private boolean selPromotorSolicitudes;

	private List solicitudesSeleccionables;
	private List solicitudesPromotorSeleccionables;

	private List lstPromotores;
	private Long promotorSeleccionado;
	private Long promotorReasignarSeleccionado;

	private List lstOpciones;
	private Long opcionSeleccionada;

	private String cantidad;
	private String desde;
	private String hasta;

	private String popupReport;


	public Long getPromotorReasignarSeleccionado() {
		log.info("getPromotorReasignarSeleccionado:" + promotorReasignarSeleccionado);
		return promotorReasignarSeleccionado;
	}


	public void setPromotorReasignarSeleccionado(Long promotorReasignarSeleccionado) {
		log.info("setPromotorReasignarSeleccionado:" + promotorReasignarSeleccionado);
		if (promotorReasignarSeleccionado != null) {
			this.promotorReasignarSeleccionado = promotorReasignarSeleccionado;
		}
	}


	public Long getPromotorSeleccionado() {
		log.info("getPromotorSeleccionado:" + promotorSeleccionado);
		return promotorSeleccionado;
	}


	public void setPromotorSeleccionado(Long promotorSeleccionado) {
		log.info("setPromotorSeleccionado:" + promotorSeleccionado);
		if (promotorSeleccionado != null) {
			this.promotorSeleccionado = promotorSeleccionado;
		}
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public List getLstPromotores() {
		return lstPromotores;
	}


	public void setLstPromotores(List lstPromotores) {
		this.lstPromotores = lstPromotores;
	}


	public List getLstOpciones() {
		return lstOpciones;
	}


	public void setLstOpciones(List lstOpciones) {
		this.lstOpciones = lstOpciones;
	}


	public Long getOpcionSeleccionada() {
		log.info("getOpcionSeleccionada:" + opcionSeleccionada);
		return opcionSeleccionada;
	}


	public void setOpcionSeleccionada(Long opcionSeleccionada) {
		log.info("setOpcionSeleccionada:" + opcionSeleccionada);
		if (opcionSeleccionada != null) {
			this.opcionSeleccionada = opcionSeleccionada;
		}
	}


	public boolean getSelPromotorSolicitudes() {
		return selPromotorSolicitudes;
	}


	public void setSelPromotorSolicitudes(boolean selPromotorSolicitudes) {
		this.selPromotorSolicitudes = selPromotorSolicitudes;
	}


	public List getSolicitudesPromotorSeleccionables() {
		return solicitudesPromotorSeleccionables;
	}


	public void setSolicitudesPromotorSeleccionables(
			List solicitudesPromotorSeleccionables) {
		this.solicitudesPromotorSeleccionables = solicitudesPromotorSeleccionables;
	}


	public String getCantidad() {
		return cantidad;
	}


	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}


	public String getDesde() {
		if (cantidad != null && !cantidad.equals("") && !cantidad.equals("0")) {
			long tmp = evaluacionService.getSolicitudDao().maxNroSolicitud().longValue() + 1;
			desde = String.valueOf(tmp);
			String ceros = "0000000";
			desde = ceros.substring(0, 7 - desde.length()).concat(desde);
			desde = desde + NroVerificador.generarDV(String.valueOf(tmp));
		} else {
			desde = "";
		}

		return desde;
	}


	public void setDesde(String desde) {
		this.desde = desde;
	}


	public String getHasta() {
		if (cantidad != null && !cantidad.equals("") && !cantidad.equals("0")) {
			long tmp = evaluacionService.getSolicitudDao().maxNroSolicitud().longValue() + Long.valueOf(cantidad).longValue();
			hasta = String.valueOf(tmp);
			String ceros = "0000000";
			hasta = ceros.substring(0, 7 - hasta.length()).concat(hasta);
			hasta = hasta + NroVerificador.generarDV(String.valueOf(tmp));
		} else {
			hasta = "";
		}
		return hasta;
	}


	public void setHasta(String hasta) {
		this.hasta = hasta;
	}


	public boolean getSelSolicitudes() {
		return selSolicitudes;
	}


	public void setSelSolicitudes(boolean selSolicitudes) {
		this.selSolicitudes = selSolicitudes;
	}


	public List getSolicitudesSeleccionables() {
		return solicitudesSeleccionables;
	}


	public void setSolicitudesSeleccionables(List solicitudesSeleccionables) {
		this.solicitudesSeleccionables = solicitudesSeleccionables;
	}


	public SolicitudesBean() {
		super();
		borrar();

		super.setTituloLargo("Tarjeta Fiel - Solicitudes");
		super.setTituloCorto("Generacion y Asignacion de Solicitudes");
	}


	public void borrar() {
		log.info("Borrar de SolicitudBean");
		error.borrar();

		mostrarIndividuos = false;
		this.lstOpciones = cargarListaOpciones();
		this.lstPromotores = cargarListaPromotores();
		this.opcionSeleccionada = new Long(1);
		this.promotorSeleccionado = new Long(0);
		this.promotorReasignarSeleccionado = new Long(0);

		this.selSolicitudes = false;
		this.selPromotorSolicitudes = false;
		this.solicitudesSeleccionables = null;
		this.solicitudesPromotorSeleccionables = null;
		this.cantidad = null;
		this.desde = null;
		this.hasta = null;

		popupReport = "";

		super.setTituloLargo("Tarjeta Fiel - Solicitudes");
		super.setTituloCorto("Generacion y Asignacion de Solicitudes");
	}


	public String inicializar() {
		borrar();

		cargarSolicitudes();

		super.setTituloLargo("Tarjeta Fiel - Solicitudes");
		super.setTituloCorto("Generacion y Asignacion de Solicitudes");
		log.info("Termino inicializar");
		return "generarSolicitudes";
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public String imprimirSeleccionadas() {
		Long idOperador = Session.getOperador().getCodigo();
		HttpServletRequest request = Session.getRequest();
		List lista = new ArrayList();

		if (!solicitudesSeleccionables.isEmpty()) {
			try {
				evaluacionService.getSolicitudImprimibleService().borrarSolicitudes(idOperador);
			} catch (SolicitudImprimibleException e) {
				e.printStackTrace();
			}

			Iterator iter = solicitudesSeleccionables.iterator();
			while (iter.hasNext()) {
				SolicitudesSeleccionables aux = (SolicitudesSeleccionables) iter.next();
				if (isSeleccionada(aux)) {
					// log.info("Imprimiendo solicitud "+aux.getSolicitud().getNroSolicitud());
					Solicitud tmp = aux.getSolicitud();
					lista.add(tmp);

					try {
						tmp.setEstaImpreso("S");
						evaluacionService.getSolicitudService().actualizarSolicitud(tmp);
					} catch (SolicitudException e) {
						e.printStackTrace();
					}
				}
			}

			if (lista != null || !lista.isEmpty()) {
				int k = 1;
				SolicitudImprimible sol = null;
				Iterator ite = lista.iterator();
				while (ite.hasNext()) {
					Solicitud tmp = (Solicitud) ite.next();
					if (k == 1) {
						sol = new SolicitudImprimible();
						sol.setIdOperador(idOperador);
					}
					switch (k) {
					case 1:
						sol.setNroSolicitud1(tmp.getNroSolicitud() + tmp.getDV());
						break;
					case 2:
						sol.setNroSolicitud2(tmp.getNroSolicitud() + tmp.getDV());
						break;
					case 3:
						sol.setNroSolicitud3(tmp.getNroSolicitud() + tmp.getDV());
						break;
					case 4:
						sol.setNroSolicitud4(tmp.getNroSolicitud() + tmp.getDV());
						break;
					case 5:
						sol.setNroSolicitud5(tmp.getNroSolicitud() + tmp.getDV());
						break;
					case 6:
						sol.setNroSolicitud6(tmp.getNroSolicitud() + tmp.getDV());
						break;
					case 7:
						sol.setNroSolicitud7(tmp.getNroSolicitud() + tmp.getDV());
						break;
					case 8:
						sol.setNroSolicitud8(tmp.getNroSolicitud() + tmp.getDV());
						break;
					case 9:
						sol.setNroSolicitud9(tmp.getNroSolicitud() + tmp.getDV());
						break;
					case 10:
						sol.setNroSolicitud10(tmp.getNroSolicitud() + tmp.getDV());
						break;
					case 11:
						sol.setNroSolicitud11(tmp.getNroSolicitud() + tmp.getDV());
						break;
					case 12:
						sol.setNroSolicitud12(tmp.getNroSolicitud() + tmp.getDV());
						break;
					}
					if (k == MAX_SOLICITUDES) {
						k = 1;
						try {
							evaluacionService.getSolicitudImprimibleService().cargarSolicitudes(sol);
						} catch (SolicitudImprimibleException e) {
							e.printStackTrace();
						}
					} else {
						k++;
					}
				}

				if (k > 1) {
					try {
						evaluacionService.getSolicitudImprimibleService().cargarSolicitudes(sol);
					} catch (SolicitudImprimibleException e) {
						e.printStackTrace();
					}
				}

			}

			//
			// LLamar reporte de ireport
			//
			String p1 = "?idOperador=" + Session.getOperador().getCodigo();
			// String p2 = "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			String page = request.getContextPath() + "/report/Impresion_nro_solicitud.jrxml";

			cargarSolicitudes();
			cargarSolicitudesPromotor();
			popupReport = "popup('" + page + p1 + "',1000,850)";
			ejecutarJavaScript(popupReport);

			log.info(popupReport);
		}
		return null;
	}


	public String irAVerIndividuo() {
		IndividuoEvaluacionBean beans = (IndividuoEvaluacionBean) Session.getBean("IndividuoEvaluacionBean");
		Session.getRequestParameter("idIndHidden");
		log.info("el numero oculto es:" + Session.getRequestParameter("idIndHidden"));
		log.info("el tipo de individuo Buscado será: " + Session.getRequestParameter("tipoIndividuo"));
		Integer valor = null;
		if (Session.getRequestParameter("tipoIndividuo").compareTo("Titular") == 0) {
			valor = new Integer(1);
		}
		if (Session.getRequestParameter("tipoIndividuo").compareTo("Garante") == 0) {
			valor = new Integer(2);
		}
		if (Session.getRequestParameter("tipoIndividuo").compareTo("Adicional") == 0) {
			valor = new Integer(3);
		}
		beans.inicializar(valor.intValue(), idSolicitudHidden, new Long(Session.getRequestParameter("idIndHidden")));
		String path = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath();
		path += "/tarjetafiel/evaluacion/amIndividuo.jsf";
		ejecutarJavaScript("popup('" + path + "',1000,600), 'titlebar=no';");
		return null;
	}


	public String cargarSolicitudes() {
		Filtro filtro = new Filtro();

		log.info("****Cargar tabla con opcion: " + opcionSeleccionada);
		if (opcionSeleccionada.equals(new Long(1))) { // Todas
			filtro.agregarCampoOperValor("estados.idEstado", Filtro.DISTINTO, "3"); // Cargadas
			filtro.agregarCampoOperValor("estados.idEstado", Filtro.DISTINTO, "4"); // Canceladas
			filtro.agregarCampoOperValor("estados.idEstado", Filtro.DISTINTO, "5"); // Aceptadas
		} else if (opcionSeleccionada.equals(new Long(2))) { // Asignadas
			filtro.agregarCampoOperValor("estados.idEstado", Filtro.IGUAL, "2");
		} else if (opcionSeleccionada.equals(new Long(3))) { // No Asignadas
			filtro.agregarCampoOperValor("estados.idEstado", Filtro.IGUAL, "1");
		}
		// Se usa para el proceso de modificacion de estrutura de cuenta
		filtro.agregarCampoOperValor("idTipoSolicitud", Filtro.DISTINTO, "2");
		filtro.agregarOrderBy("nroSolicitud");
		log.info(filtro.getHQL());

		try {

			solicitudesSeleccionables = SolicitudesEvaluacionUtil.solicitudesSeleccionables(
					evaluacionService.getSolicitudService().getSolicitud(filtro));
		} catch (SolicitudException e) {
			e.printStackTrace();
		}

		// agrego una para memorizar si selecciono todas o no
		// SolicitudesSeleccionables temp = new SolicitudesSeleccionables();
		// temp.setPromotor("Fila para seleccionar todos.");
		// solicitudesSeleccionables.add(0, temp);
		return null;
	}


	public String cargarSolicitudesPromotor() {
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("estados.idEstado", Filtro.IGUAL, "2");// Asignadas
		filtro.agregarCampoOperValor("promotor.idPromotor", Filtro.IGUAL, promotorSeleccionado);
		// Se usa para el proceso de modificacion de estrutura de cuenta
		filtro.agregarCampoOperValor("idTipoSolicitud", Filtro.DISTINTO, "2");
		log.info("Cargar tabla de promotor: " + promotorSeleccionado);

		try {
			solicitudesPromotorSeleccionables = SolicitudesEvaluacionUtil.solicitudesSeleccionables(
					evaluacionService.getSolicitudService().getSolicitud(filtro));
		} catch (SolicitudException e) {
			e.printStackTrace();
		}

		// agrego una para memorizar si selecciono todas o no
		// SolicitudesSeleccionables temp = new SolicitudesSeleccionables();
		// temp.setPromotor("Fila para seleccionar todos.");
		// solicitudesPromotorSeleccionables.add(0, temp);
		return null;
	}


	public void cambiarOpcion(ValueChangeEvent event) throws AbortProcessingException {
		log.info("Cambiando a opcion: " + event.getNewValue());
		setOpcionSeleccionada((Long) event.getNewValue());

		cargarSolicitudes();

		Session.renderResponse();
	}


	public void cambiarPromotor(ValueChangeEvent event) throws AbortProcessingException {
		log.info("Cambiando a promotor: " + event.getNewValue());

		setPromotorSeleccionado((Long) event.getNewValue());

		cargarSolicitudesPromotor();

		Session.renderResponse();
	}


	public void cambiarPromotorReasignar(ValueChangeEvent event) throws AbortProcessingException {
		log.info("Cambiando a promotor para reasigar: " + event.getNewValue());

		setPromotorReasignarSeleccionado((Long) event.getNewValue());

		Session.renderResponse();
	}


	public String generarSolicitudes() {
		List solicitudes = null;
		Estados estadoNoAsignado = null;
		Date d = new Date();
		Timestamp ts = new Timestamp(d.getTime());
		log.info("Generando " + cantidad + " solicitudes...");

		try {
			solicitudes = evaluacionService.getSolicitudService().generarSolicitudes(Session.getOperador(), Long.valueOf(cantidad));
		} catch (SolicitudException e) {
			e.printStackTrace();
			return null;
		}

		try {
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("idEstado", Filtro.IGUAL, "1");// No Asignada
			List tmp = evaluacionService.getEstadosService().getEstados(filtro);
			estadoNoAsignado = (Estados) tmp.get(0);
		} catch (EstadosException e) {
			e.printStackTrace();
		}

		if (!solicitudes.isEmpty()) {
			Iterator iter = solicitudes.iterator();
			while (iter.hasNext()) {
				Solicitud sol = (Solicitud) iter.next();
				sol.setEstados(estadoNoAsignado);
				sol.setTimestamp(ts);

				try {
					evaluacionService.getSolicitudService().grabarSolicitud(sol);
					log.info("Creando solicitud nro: " + sol.getNroSolicitud() + sol.getDV());
				} catch (SolicitudException e) {
					e.printStackTrace();
					return null;
				}
			}

			this.inicializar();
		}
		return null;
	}


	public String asignarSeleccionadas() {
		return asignarSeleccionadasLista(solicitudesSeleccionables, promotorSeleccionado);
	}


	public String reasignarSeleccionadas() {
		return asignarSeleccionadasLista(solicitudesPromotorSeleccionables, promotorReasignarSeleccionado);
	}


	public String asignarSeleccionadasLista(List lista, Long idPromotor) {
		Estados estadoAsignado = null;
		Promotor promotor = null;
		try {
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("idEstado", Filtro.IGUAL, "2");// Asignada
			List tmp = evaluacionService.getEstadosService().getEstados(filtro);
			estadoAsignado = (Estados) tmp.get(0);
		} catch (EstadosException e) {
			e.printStackTrace();
		}

		log.info("Promotor seleccionado: " + idPromotor);

		try {
			promotor = evaluacionService.getPromotorService().leerPromotor(idPromotor);
		} catch (PromotorException e) {
			e.printStackTrace();
		}

		if (!lista.isEmpty()) {
			Iterator iter = lista.iterator();
			while (iter.hasNext()) {
				SolicitudesSeleccionables aux = (SolicitudesSeleccionables) iter.next();
				if (isSeleccionada(aux)) {
					log.info("Asignando solicitud " + aux.getSolicitud().getNroSolicitud() + " al promotor " + promotor.getApellido() + ", "
							+ promotor.getNombre());
					Solicitud tmp = aux.getSolicitud();
					tmp.setEstados(estadoAsignado);
					tmp.setPromotor(promotor);
					try {
						evaluacionService.getSolicitudService().actualizarSolicitud(tmp);
					} catch (SolicitudException e) {
						e.printStackTrace();
					}
				}
			}
			cargarSolicitudes();
			cargarSolicitudesPromotor();
		}

		return null;
	}


	public String desasignarSeleccionadas() {
		Estados estadoNoAsignado = null;

		try {
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("idEstado", Filtro.IGUAL, "1");// No Asignada
			List tmp = evaluacionService.getEstadosService().getEstados(filtro);
			estadoNoAsignado = (Estados) tmp.get(0);
		} catch (EstadosException e) {
			e.printStackTrace();
		}

		if (!solicitudesPromotorSeleccionables.isEmpty()) {
			Iterator iter = solicitudesPromotorSeleccionables.iterator();
			while (iter.hasNext()) {
				SolicitudesSeleccionables aux = (SolicitudesSeleccionables) iter.next();
				if (isSeleccionada(aux)) {
					log.info("Desasignando solicitud " + aux.getSolicitud().getNroSolicitud());
					Solicitud tmp = aux.getSolicitud();
					tmp.setEstados(estadoNoAsignado);
					tmp.setPromotor(null);
					try {
						evaluacionService.getSolicitudService().actualizarSolicitud(tmp);
					} catch (SolicitudException e) {
						e.printStackTrace();
					}
				}
			}
			cargarSolicitudes();
			cargarSolicitudesPromotor();
		}

		return null;
	}


	private boolean isSeleccionada(SolicitudesSeleccionables solicitud) {
		return solicitud.getSeleccionado() && !solicitud.getPromotor().equals("Fila para seleccionar todos.");
	}


	private List cargarListaOpciones() {
		List result = new ArrayList();
		log.info("Cargando Opcciones");

		SelectItem item = new SelectItem();
		item.setValue(new Long(1));
		item.setLabel("Todas");
		result.add(item);
		item = new SelectItem();
		item.setValue(new Long(2));
		item.setLabel("Asignadas");
		result.add(item);
		item = new SelectItem();
		item.setValue(new Long(3));
		item.setLabel("No Asignadas");
		result.add(item);
		return result;
	}


	private List cargarListaPromotores() {
		List result = new ArrayList();
		List promotores = null;

		try {
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("estado", Filtro.LIKEXS, "A");
			promotores = evaluacionService.getPromotorService().getPromotor(filtro);
		} catch (PromotorException e) {
			e.printStackTrace();
		}

		if (!promotores.isEmpty()) {
			Iterator iter = promotores.iterator();
			while (iter.hasNext()) {
				SelectItem item = new SelectItem();
				Promotor aux = (Promotor) iter.next();
				item.setValue(aux.getIdPromotor());
				item.setLabel(new String(aux.getApellido() + ", " + aux.getNombre()));
				result.add(item);
			}

		}

		SelectItem aux = new SelectItem();
		aux.setValue(new Long(0));
		aux.setLabel("Seleccion un Promotor.");
		result.add(0, aux);
		return result;
	}


	public String listarSolicitudes() {
		listaSolicitudes = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (nroSolicitud != null && !nroSolicitud.equals("")) {
				nroSolicitud = Convertidores.completarALaIzquierda(new Integer(nroSolicitud).intValue(), 8, "000");
				String num = nroSolicitud.substring(7);
				nroSolicitud = nroSolicitud.substring(0, 7);
				filtro.agregarCampoOperValor("DV", Filtro.IGUAL, new Long(num));
				filtro.agregarCampoOperValor("nroSolicitud", Filtro.IGUAL, new Long(this.nroSolicitud));
			}
			if (promotor != null && !promotor.equals(""))
				filtro.agregarCampoOperValor("promotor.apellido", Filtro.LIKE, promotor);
			listaSolicitudes = SolicitudesEvaluacionUtil.solicitudesSeleccionables(evaluacionService.getSolicitudService().getSolicitud(filtro));
			// Iterator iter = listaSolicitudes.iterator();
			// while (iter.hasNext())
			// {
			// Solicitud element = (Solicitud)iter.next();
			//
			// //Agregar aqui todo lo necesario de extraer
			// }
			nroSolicitud = "";
			promotor = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/evaluacion/lstSolicitudes.jsf");
		return null;
	}


	public String irAListarSolicitudes() {
		borrar();
		tituloCorto = "Listado de Solicitudes";
		listaSolicitudes = null;
		Session.redirect("/tarjetafiel/evaluacion/lstSolicitudes.jsf");
		return "";
	}


	public String verIndividuos() {
		listDeIndividuosAsignados = new ArrayList();
		mostrarIndividuos = true;
		try {
			String nroFiltro = idSolicitudHidden.substring(0, 7);
			log.info("El numero filtro es: " + nroFiltro);
			String dv = idSolicitudHidden.substring(7);
			log.info("El dv es: " + dv);
			Filtro filtroSolicitud = new Filtro();
			filtroSolicitud.agregarCampoOperValor("nroSolicitud", Filtro.IGUAL, nroFiltro);
			filtroSolicitud.agregarCampoOperValor("DV", Filtro.IGUAL, dv);
			List listSol = evaluacionService.getSolicitudService().getSolicitud(filtroSolicitud);
			solicitud = (Solicitud) listSol.get(0);
			log.info("Seleccionada la solicitud de id: " + idSolicitudHidden);
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("solicitud.idSolicitud", Filtro.IGUAL, solicitud.getIdSolicitud());
			log.info("La solicitud tiene id: " + solicitud.getIdSolicitud());
			log.info("La solicitud Seleccionada tiene: " + solicitud.getSolicIndividuos().size());
			List solicitudesIndividuos = evaluacionService.getSolicitudIndividuoService().getSolicitudIndividuo(filtro);
			Iterator iter = solicitudesIndividuos.iterator();
			while (iter.hasNext()) {
				SolicitudIndividuo solInd = (SolicitudIndividuo) iter.next();
				solInd.getIndividuoEvaluacion().getIdIndividuo();
				solInd.getIndividuoEvaluacion().getApellido();
				solInd.getIndividuoEvaluacion().getApellidoMaterno();
				IndividuosAsignadosWrapper elem = new IndividuosAsignadosWrapper(solInd.getIndividuoEvaluacion(), solInd.getTipoIndividuo()
						.getDescripcion());
				log.info("se agrega el elemento de id: " + elem.getIdIndividuo());
				listDeIndividuosAsignados.add(elem);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolicitudIndividuoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolicitudException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public String verListado() {
		mostrarIndividuos = false;
		return null;
	}


	public String getNroSolicitud() {
		return nroSolicitud;
	}


	public void setNroSolicitud(String nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}


	public String getPromotor() {
		return promotor;
	}


	public void setPromotor(String promotor) {
		this.promotor = promotor;
	}


	public boolean isMostrarIndividuos() {
		return mostrarIndividuos;
	}


	public void setMostrarIndividuos(boolean mostrarIndividuos) {
		this.mostrarIndividuos = mostrarIndividuos;
	}


	public List getListaSolicitudes() {
		return listaSolicitudes;
	}


	public void setListaSolicitudes(List listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}


	public List getListDeIndividuosAsignados() {
		return listDeIndividuosAsignados;
	}


	public void setListDeIndividuosAsignados(List listDeIndividuosAsignados) {
		this.listDeIndividuosAsignados = listDeIndividuosAsignados;
	}


	public Solicitud getSolicitud() {
		return solicitud;
	}


	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}


	public String getIdSolicitudHidden() {
		return idSolicitudHidden;
	}


	public void setIdSolicitudHidden(String idSolicitudHidden) {
		this.idSolicitudHidden = idSolicitudHidden;
	}


	public String getIdIndHidden() {
		return idIndHidden;
	}


	public void setIdIndHidden(String idIndHidden) {
		this.idIndHidden = idIndHidden;
	}

}

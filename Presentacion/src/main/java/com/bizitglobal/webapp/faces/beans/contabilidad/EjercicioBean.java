package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;

import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.tarjetafiel.general.exception.SucursalFielException;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;


@SuppressWarnings({"rawtypes","unchecked"})
public class EjercicioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(EjercicioBean.class);

	public static int documentoWrapper = 0;
	/** Alterna la vista entreel panel de alta y el listado */
	private boolean panelAlta;
	/** Oculta el boton cerrar ejercicio en caso de un alta, permanece visible si es un modificacion */
	private boolean modificacion;
	private boolean grabaEjercicio;
	private boolean modificacionDisabled;
	/** True si es un alta de ejercicio */
	private boolean alta;
	private boolean estadoEjercicio;
	private boolean modificacionFechaPeriodo;
	private Ejercicio ejercicioAEditar; // aqui el ejercicio a ser editado

	/** Permite setear un estado al ejercicio al momento del alta. */
	private boolean ejercicioAbierto;
	private Date fechaInicio;
	private Date fechaCierre;
	private Date fechaPeriodo;
	private String estado;
	private String valorPorDefecto;
	private boolean ejercicioDefault;
	private String observaciones;
	/** Una lista con los ejercicios existentes. Contiene objetos wrapperEjercicio */
	private List ejercicios;
	private List sucursalesDeFiel;
	private List sucursalesParaAlta;
	private List sucursalesDeFielAuxiliar;
	private HtmlSelectOneMenu idSucursalDeFielSeleccionada;
	private HtmlSelectOneMenu idSucursalParaAlta;
	private Long idSucSeleccionada = new Long(1);
	private Long idSucSeleccionadaParaFiel = new Long(1);


	public EjercicioBean() {
		super();
		borrar();
		sucursalesDeFiel = new ArrayList();
		sucursalesParaAlta = new ArrayList();
		try {
			sucursalesDeFielAuxiliar = generalService.getSucursalFielService().getSucursales();
			sucursalesDeFiel.add(new SelectItem(new Long(0), "Todas"));
			sucursalesParaAlta.add(new SelectItem(new Long(0), new String("Elija una sucursal")));
			Iterator iterDeSucursales = sucursalesDeFielAuxiliar.iterator();
			while (iterDeSucursales.hasNext()) {
				SucursalFiel su = (SucursalFiel) iterDeSucursales.next();
				sucursalesDeFiel.add(new SelectItem(su.getIdSucursal(), su.getNombre()));
				sucursalesParaAlta.add(new SelectItem(su.getIdSucursal(), su.getNombre()));
			}
		} catch (SucursalFielException e1) {
			log.info("No existen en la base de datos sucursales de Fiel");
			e1.printStackTrace();
		}
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE EJERCICIO
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		idSucursalDeFielSeleccionada.setValue(new Long(1));
		idSucSeleccionadaParaFiel = new Long(1);
		listarEjercicios();
		return "gestionarEjercicio";
	}


	public String listarEjercicios() {
		ejercicios = new ArrayList();
		Filtro filtro = new Filtro();
		idSucSeleccionadaParaFiel = (Long) idSucursalDeFielSeleccionada.getValue();
		if (!idSucSeleccionadaParaFiel.equals(new Long(0))) {
			filtro.agregarCampoOperValor("sucursalFiel", Filtro.IGUAL, idSucSeleccionadaParaFiel);
		}
		try {
			List ejerciciosAuxiliares = contabilidadService.getEjercicioService().getEjercicio(filtro);
			Iterator iterDeEjerciciosAuxiliares = ejerciciosAuxiliares.iterator();
			while (iterDeEjerciciosAuxiliares.hasNext()) {
				Ejercicio ejer = (Ejercicio) iterDeEjerciciosAuxiliares.next();
				ejer.getIdEjercicio();
				ejer.getFechaCierre();
				ejer.getFechaInicio();
				ejer.getFechaPeriodo();
				ejer.getEstado();
				ejer.getSucursalFiel();
				WrapperEjercicio wraper = new WrapperEjercicio(ejer);
				ejercicios.add(wraper);
			}
		} catch (EjercicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public void borrar() {
		error.borrar();
		alta = false;
		panelAlta = false;
		modificacion = false;
		modificacionFechaPeriodo = false;
		modificacionDisabled = false;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Gestion de Ejercicios";
		idSucursalDeFielSeleccionada = new HtmlSelectOneMenu();
		idSucursalParaAlta = new HtmlSelectOneMenu();
		idSucursalDeFielSeleccionada.setValue(new Long(1));
		idSucursalParaAlta.setValue(new Long(1));
		fechaCierre = new Date(Calendar.getInstance().getTimeInMillis());
		fechaInicio = new Date(Calendar.getInstance().getTimeInMillis());
		fechaPeriodo = new Date(Calendar.getInstance().getTimeInMillis());
		ejercicioAEditar = null;
		ejercicioAbierto = true;
		ejercicioDefault = false;
		observaciones = "";
		idSucSeleccionada = new Long(1);
		idSucSeleccionadaParaFiel = new Long(1);
		ejercicios = new ArrayList();
		popup.borrar();
		listarEjercicios();

	}


	public void cambiarElListado(ValueChangeEvent event) {
		log.info("Ejecutando ==> cambiarElListado");
		listarEjercicios();
	}


	public String grabarEjercicio() {
		if (validar()) {
			log.info("Inicializamos el metodo grabar Ejercicio");
			SucursalFiel suc = new SucursalFiel();
			log.info("Inicializamos el metodo grabar Ejercicio");
			if (alta) {
				Iterator su = sucursalesDeFielAuxiliar.iterator();
				while (su.hasNext()) {
					SucursalFiel sucundum = (SucursalFiel) su.next();
					if (sucundum.getIdSucursal().equals(new Long(1))) {
						suc = sucundum;
					}
				}
				estado = "C";
				if (ejercicioAbierto)
					estado = "A";
				try {
					log.info("voy a construir el ejercicio");
					valorPorDefecto = "N";
					if (ejercicioDefault)
					{
						quitarValorDefectoAnterior();
						valorPorDefecto = "S";
					}
					Ejercicio ejercicioNuevo = new Ejercicio(null, new Integer(suc.getIdSucursal().intValue()), fechaInicio, fechaCierre,
							fechaPeriodo, estado, observaciones, valorPorDefecto);
					contabilidadService.getEjercicioService().grabarEjercicio(ejercicioNuevo);
					alta = false;
					popup.setPopup(popup.ICONO_OK, "El Ejercicio ha sido almacenado exitosamente.");
				} catch (com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioCreateException e) {
					error.agregar(e.getMensajeDeError());
					log.info(e.getMensajeDeError());
					e.printStackTrace();
				} catch (EjercicioException e1) {
					log.info("Ocurrio un error al intentar guardar el ejercicio");
					popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Ejercicio.");
					e1.printStackTrace();
				}
			} else {
				try {
					log.info("voy a modificar el ejercicio");
					
					// AGREGO LO NUEVO
					
					log.info("Ejecutando ==> modificarEjercicio()");
					if (Session.getRequestParameter("idEjeEdi") != null) {
						
					
					Long idEje = new Long(Session.getRequestParameter("idEjeEdi"));
					log.info("idEje " + idEje);
					Iterator it = ejercicios.iterator();
					while (it.hasNext()) {
						WrapperEjercicio wrap = (WrapperEjercicio) it.next();
						if (wrap.getIdEjer() == idEje.intValue()) {
							ejercicioAEditar = wrap.getEjercicio();
							idSucSeleccionada = new Long(wrap.getEjercicio().getSucursalFiel().intValue());
							estado = wrap.getEjercicio().getEstado();
							fechaInicio = new Date(wrap.getEjercicio().getFechaInicio().getTime());
							fechaCierre = new Date(wrap.getEjercicio().getFechaCierre().getTime());
							fechaPeriodo = new Date(wrap.getEjercicio().getFechaPeriodo().getTime());
							log.info("getIdEjercicio " + wrap.getEjercicio().getIdEjercicio());
							observaciones = wrap.getEjercicio().getObservaciones();
							
							valorPorDefecto = wrap.getEjercicio().getActual();
							if (wrap.getEjercicio().getEstado().compareTo("A") == 0) {
								estadoEjercicio = true;
								ejercicioAbierto = true;
							} else {
								ejercicioAbierto = false;
								modificacion = false;
								estadoEjercicio = false;
								modificacionFechaPeriodo = false;
							}
							if (wrap.getEjercicio().getActual().compareTo("S") == 0)
								ejercicioDefault = false;
							else
								ejercicioDefault = true;
							break;
						}
					}
					
					//HASTA AQUI
					} 
					
					
					
					ejercicioAEditar.setObservaciones(observaciones);
					//ejercicioAEditar.setObservaciones("");
					ejercicioAEditar.setFechaPeriodo(fechaPeriodo);
					valorPorDefecto = "N";
					if (ejercicioDefault && estadoEjercicio) {
						quitarValorDefectoAnterior();
						valorPorDefecto = "S";
					}
					//ejercicioAEditar.setActual(valorPorDefecto);
					ejercicioAEditar.setActual("S");
					if (estadoEjercicio) {
						contabilidadService.getEjercicioService().actualizarEjercicio(ejercicioAEditar);
					}
					
					alta = false;
					modificacion = false;
					modificacionDisabled = false;
					panelAlta = false;
					popup.setPopup(popup.ICONO_OK, "El Ejercicio ha sido almacenado exitosamente.");
				} catch (EjercicioException e1) {
					log.info("Ocurrio un error al intentar modificar el ejercicio");
					popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Ejercicio.");
					e1.printStackTrace();
				}
			}
		}
		return "";
	}


	private void quitarValorDefectoAnterior() {
		valorPorDefecto = "S";
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("actual", Filtro.LIKE, valorPorDefecto);
		try {
			List ejerciciosporDefecto = contabilidadService.getEjercicioService().getEjercicio(filtro);
			if (ejerciciosporDefecto.size() > 0) {
				Ejercicio ejercicioporDefecto = (Ejercicio) ejerciciosporDefecto.get(0);
				if (ejercicioporDefecto != null) {
					ejercicioporDefecto.setActual("N");
					contabilidadService.getEjercicioService().actualizarEjercicio(ejercicioporDefecto);
				}
			}
		} catch (EjercicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String volverAlListado() {
		borrar();
		tituloCorto = "Gestión de Ejercicios";
		listarEjercicios();
		Session.redirect("/tarjetafiel/contabilidad/gestionarEjercicio.jsf");
		return "";
	}


	public String volverAlMenu() {
		borrar();
		panelAlta = false;
		modificacion = false;
		modificacionDisabled = false;
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaInicio);
		int mes = cal.get(Calendar.MONTH);
//		if (mes != 5) {
//			error.agregar("La fecha de Inicio debe estar comprendida en el mes de junio.");
//		}
		if (fechaInicio.after(fechaCierre)) {
			error.agregar(Error.FECHA_ORIGEN_MAYOR_FECHA_CIERRE);
		}
		if (fechaInicio.compareTo(fechaPeriodo) > 0) {
			error.agregar(Error.FECHA_PERIODO_MENOR_FECHA_ORIGEN);
		}
		if (fechaPeriodo.after(fechaCierre)) {
			error.agregar(Error.FECHA_PERIODO_MAYOR_FECHA_CIERRE);
		}
		if (idSucSeleccionada.equals(new Long(0))) {
			error.agregar(Error.SUCURSAL_NO_SELECCIONADA);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String cerrarEjercicio() {
		try {
			log.info("voy a modificar el ejercicio");
			ejercicioAEditar.setObservaciones(observaciones);
			ejercicioAEditar.setFechaPeriodo(fechaPeriodo);
			ejercicioAEditar.setEstado("C");
			contabilidadService.getEjercicioService().actualizarEjercicio(ejercicioAEditar);
			alta = false;
			modificacion = false;
			modificacionDisabled = false;
			panelAlta = false;
			modificacionFechaPeriodo = false;
			popup.setPopup(popup.ICONO_OK, "El Ejercicio ha sido almacenado exitosamente.");
		} catch (EjercicioException e1) {
			log.info("Ocurrio un error al intentar modificar el ejercicio");
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Ejercicio.");
			e1.printStackTrace();
		}
		return "";
	}


	public String agregarEjercicio() {
		panelAlta = true;
		modificacion = false;
		modificacionDisabled = false;
		modificacionFechaPeriodo = true;
		grabaEjercicio = true;
		alta = true;
		tituloCorto = "Alta de Ejercicio";
		popup.borrar();
		return "";
	}


	public String modificarEjercicio() {
		alta = false;
		panelAlta = true;
		modificacion = true;
		modificacionFechaPeriodo = true;
		modificacionDisabled = true;
		grabaEjercicio = false;
		popup.borrar();
		log.info("Ejecutando ==> modificarEjercicio()");
		Long idEje = new Long(Session.getRequestParameter("idEjeEdi"));
		Iterator it = ejercicios.iterator();
		while (it.hasNext()) {
			WrapperEjercicio wrap = (WrapperEjercicio) it.next();
			if (wrap.getIdEjer() == idEje.intValue()) {
				ejercicioAEditar = wrap.getEjercicio();
				idSucSeleccionada = new Long(wrap.getEjercicio().getSucursalFiel().intValue());
				estado = wrap.getEjercicio().getEstado();
				fechaInicio = new Date(wrap.getEjercicio().getFechaInicio().getTime());
				fechaCierre = new Date(wrap.getEjercicio().getFechaCierre().getTime());
				fechaPeriodo = new Date(wrap.getEjercicio().getFechaPeriodo().getTime());
				observaciones = wrap.getEjercicio().getObservaciones();
				valorPorDefecto = wrap.getEjercicio().getActual();
				if (wrap.getEjercicio().getEstado().compareTo("A") == 0) {
					estadoEjercicio = true;
					ejercicioAbierto = true;
					modificacion = true;
					grabaEjercicio = true;
				} else {
					ejercicioAbierto = false;
					modificacion = false;
					estadoEjercicio = false;
					modificacionFechaPeriodo = false;
					grabaEjercicio = false;
				}
				if (wrap.getEjercicio().getActual().compareTo("S") == 0)
					ejercicioDefault = true;
				else
					ejercicioDefault = false;
				break;
			}
		}
		tituloCorto = "Modificar Ejercicio";
		return null;
	}


	public String irAGestionarEjercicio() {
		borrar();
		tituloCorto = "Gestión De Ejercicios";
		listarEjercicios();
		Session.redirect("/tarjetafiel/contabilidad/gestionarEjercicio.jsf");
		return "";
	}


	public boolean isModificacion() {
		return modificacion;
	}


	public void setModificacion(boolean modificacion) {
		this.modificacion = modificacion;
	}


	public boolean isPanelAlta() {
		return panelAlta;
	}


	public void setPanelAlta(boolean panelAlta) {
		this.panelAlta = panelAlta;
	}


	public Date getFechaCierre() {
		return fechaCierre;
	}


	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaPeriodo() {
		return fechaPeriodo;
	}


	public void setFechaPeriodo(Date fechaPeriodo) {
		this.fechaPeriodo = fechaPeriodo;
	}


	public boolean isEjercicioAbierto() {
		return ejercicioAbierto;
	}


	public void setEjercicioAbierto(boolean ejercicioAbierto) {
		this.ejercicioAbierto = ejercicioAbierto;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public List getEjercicios() {
		return ejercicios;
	}


	public void setEjercicios(List ejercicios) {
		this.ejercicios = ejercicios;
	}

	public class WrapperEjercicio {

		private Ejercicio ejercicio;
		private int idEjer;


		public WrapperEjercicio(Ejercicio ejercicio) {
			this.ejercicio = ejercicio;
			idEjer = ++documentoWrapper;
		}


		public Ejercicio getEjercicio() {
			return ejercicio;
		}


		public void setEjercicio(Ejercicio ejercicio) {
			this.ejercicio = ejercicio;
		}


		public int getIdEjer() {
			return idEjer;
		}


		public void setIdEjer(int idEjer) {
			this.idEjer = idEjer;
		}

	}


	public List getSucursalesDeFiel() {
		return sucursalesDeFiel;
	}


	public void setSucursalesDeFiel(List sucursalesDeFiel) {
		this.sucursalesDeFiel = sucursalesDeFiel;
	}


	public List getSucursalesParaAlta() {
		return sucursalesParaAlta;
	}


	public void setSucursalesParaAlta(List sucursalesParaAlta) {
		this.sucursalesParaAlta = sucursalesParaAlta;
	}


	public Long getIdSucSeleccionada() {
		return idSucSeleccionada;
	}


	public void setIdSucSeleccionada(Long idSucSeleccionada) {
		this.idSucSeleccionada = idSucSeleccionada;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public boolean isEstadoEjercicio() {
		return estadoEjercicio;
	}


	public void setEstadoEjercicio(boolean estadoEjercicio) {
		this.estadoEjercicio = estadoEjercicio;
	}


	public Ejercicio getEjercicioAEditar() {
		return ejercicioAEditar;
	}


	public void setEjercicioAEditar(Ejercicio ejercicioAEditar) {
		this.ejercicioAEditar = ejercicioAEditar;
	}


	public boolean isModificacionDisabled() {
		return modificacionDisabled;
	}
	
	


	public boolean isGrabaEjercicio() {
		return grabaEjercicio;
	}


	public void setGrabaEjercicio(boolean grabaEjercicio) {
		this.grabaEjercicio = grabaEjercicio;
	}


	public void setModificacionDisabled(boolean modificacionDisabled) {
		this.modificacionDisabled = modificacionDisabled;
	}


	public Long getIdSucSeleccionadaParaFiel() {
		return idSucSeleccionadaParaFiel;
	}


	public void setIdSucSeleccionadaParaFiel(Long idSucSeleccionadaParaFiel) {
		this.idSucSeleccionadaParaFiel = idSucSeleccionadaParaFiel;
	}


	public HtmlSelectOneMenu getIdSucursalDeFielSeleccionada() {
		return idSucursalDeFielSeleccionada;
	}


	public void setIdSucursalDeFielSeleccionada(
			HtmlSelectOneMenu idSucursalDeFielSeleccionada) {
		this.idSucursalDeFielSeleccionada = idSucursalDeFielSeleccionada;
	}


	public HtmlSelectOneMenu getIdSucursalParaAlta() {
		return idSucursalParaAlta;
	}


	public void setIdSucursalParaAlta(HtmlSelectOneMenu idSucursalParaAlta) {
		this.idSucursalParaAlta = idSucursalParaAlta;
	}


	public boolean isModificacionFechaPeriodo() {
		return modificacionFechaPeriodo;
	}


	public void setModificacionFechaPeriodo(boolean modificacionFechaPeriodo) {
		this.modificacionFechaPeriodo = modificacionFechaPeriodo;
	}


	public boolean isEjercicioDefault() {
		return ejercicioDefault;
	}


	public void setEjercicioDefault(boolean ejercicioDefault) {
		this.ejercicioDefault = ejercicioDefault;
	}

}

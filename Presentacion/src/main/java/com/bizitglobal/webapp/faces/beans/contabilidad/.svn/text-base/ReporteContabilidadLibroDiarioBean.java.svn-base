package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


@SuppressWarnings({"rawtypes","unchecked"})
public class ReporteContabilidadLibroDiarioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ReporteContabilidadLibroDiarioBean.class);

	private Date fechaDesde;
	private Date fechaHasta;
	private Date fechaInicioAux;
	private Date fechaCierreAux;
	private List ejercicios;
	private List ejerciciosSelectItem; // todos los ejercicios como select item
	// objetos para trabajar con el select item de ejercicios
	private Long idEjercicioSeleccionado;
	private HtmlSelectOneMenu idEjercicioSeleccionadoItem;
	Ejercicio ejerActual;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");

	private boolean banderaFechasInicio = false; // truco para lograr que cambie las fechas de los ejercicios.!!!
	private boolean banderaFechasCierre = false;


	public ReporteContabilidadLibroDiarioBean() {
		super();
		fechaDesde = null;
		fechaHasta = null;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(Date fechaDesde) {
		if (!banderaFechasInicio) {
			this.fechaDesde = fechaDesde;
		} else {
			banderaFechasInicio = false;
		}
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		if (!banderaFechasCierre) {
			this.fechaHasta = fechaHasta;
		} else {
			banderaFechasCierre = false;
		}
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public void borrar() {
		popupReport = new String("");
		tituloLargo = "";
		tituloCorto = "";
		error.borrar();
	}


	public String inicializar() {
		borrar();

		tituloLargo = "CONTABILIDAD";
		tituloCorto = "Reporte Libro Diario";
		ejercicios = new ArrayList();
		ejerciciosSelectItem = new ArrayList();
		Filtro filtro = new Filtro();
		try {
			ejercicios = contabilidadService.getEjercicioService().getEjercicio(filtro);
			Iterator iterDeEjerciciosAuxiliares = ejercicios.iterator();
			while (iterDeEjerciciosAuxiliares.hasNext()) {
				Ejercicio ejer = (Ejercicio) iterDeEjerciciosAuxiliares.next();
				ejer.getIdEjercicio();
				ejer.getFechaCierre();
				ejer.getFechaInicio();
				ejer.getFechaPeriodo();
				ejer.getEstado();
				ejer.getSucursalFiel();
			}
		} catch (EjercicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator i = ejercicios.iterator();
		ejerciciosSelectItem.add(new SelectItem(new Long(0), "Seleccione un ejercicio"));
		while (i.hasNext()) {
			Ejercicio ej = (Ejercicio) i.next();
			ejerciciosSelectItem.add(new SelectItem(new Long(ej.getIdEjercicio().intValue()), "Ejercicio: " + ej.getIdEjercicio()
					+ " | Fecha Inicio: " + ej.getFechaInicio() + " | Fecha Cierre: " + ej.getFechaCierre()));
			if (ej.getActual().compareTo("S") == 0) {
				idEjercicioSeleccionado = new Long(ej.getIdEjercicio().longValue());
				idEjercicioSeleccionadoItem = new HtmlSelectOneMenu();
				idEjercicioSeleccionadoItem.setValue(idEjercicioSeleccionado);
				ejerActual = ej;
			}
		}
		if (ejerActual != null) {
			fechaInicioAux = ejerActual.getFechaInicio();
			fechaCierreAux = ejerActual.getFechaCierre();
			fechaDesde = new Date(fechaInicioAux.getTime());
			fechaHasta = new Date(fechaCierreAux.getTime());
		}

		return "reporteContabilidadLibroDiario";
	}


	public boolean validar() {
		error.borrar();
		return false;
	}


	public boolean validarRangoProveedor() {
		error.borrar();

		return (error.cantidad() == 0) ? true : false;
	}


	public void acomodarFechas(ValueChangeEvent e) {
		popupReport = "";
		idEjercicioSeleccionado = (Long) idEjercicioSeleccionadoItem.getValue();
		Iterator iter = ejercicios.iterator();
		while (iter.hasNext()) {
			Ejercicio ej = (Ejercicio) iter.next();
			if (ej.getIdEjercicio().compareTo(new Integer(idEjercicioSeleccionado.intValue())) == 0) {
				ejerActual = ej;
				break;
			}
		}
		if (ejerActual != null) {
			banderaFechasInicio = true;
			banderaFechasCierre = true;
			fechaInicioAux = ejerActual.getFechaInicio();
			fechaCierreAux = ejerActual.getFechaCierre();
			fechaDesde = new Date(fechaInicioAux.getTime());
			fechaHasta = new Date(fechaCierreAux.getTime());
		} else {
			log.info("el ejercicio es nulo");
		}

	}


	public boolean validarFecha() {
		error.borrar();

		if (Validador.esNulo(getFechaDesde()) || getFechaDesde().equals(new Date(0))
				|| Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		} else {
			if (getFechaDesde().after(getFechaHasta())) {
				error.agregar("La fecha desde no puede ser mayor a la fecha hasta");
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public boolean validarFechaHasta() {
		error.borrar();

		if (Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_HASTA_REQUERIDA);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String generar(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		error.borrar();
		popupReport = new String("");

		if (ejerActual != null || validarFecha()) {
			Integer inte = new Integer(1);
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?ejercicio=" + (Long) idEjercicioSeleccionadoItem.getValue();
			String p2 = "&fecha_desde=" + dateFormat.format(fechaDesde);
			String p3 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			String p4 = "&id_sucursal" + inte;
			String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

			String page = request.getContextPath() + "/report/ContabilidadLibroDiario.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + "',1000,600)";

			log.info(popupReport);
		} else {
			error.agregar("Error en el rango de fechas.");
			return null;
		}
		return null;
	}


	public String generarAExcel(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		error.borrar();
		popupReport = new String("");
		if (ejerActual != null || validarFecha()) {
			Integer inte = new Integer(1);
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?ejercicio=" + (Long) idEjercicioSeleccionadoItem.getValue();
			String p2 = "&fecha_desde=" + dateFormat.format(fechaDesde);
			String p3 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			String p4 = "&id_sucursal" + inte;
			String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p6 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/ContabilidadLibroDiarioExcel.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + "',1000,600)";

			log.info(popupReport);
		} else {
			error.agregar("Error en el rango de fechas.");
			return null;
		}

		return null;
	}


	public HtmlSelectOneMenu getIdEjercicioSeleccionadoItem() {
		return idEjercicioSeleccionadoItem;
	}


	public void setIdEjercicioSeleccionadoItem(
			HtmlSelectOneMenu idEjercicioSeleccionadoItem) {
		this.idEjercicioSeleccionadoItem = idEjercicioSeleccionadoItem;
	}


	public Long getIdEjercicioSeleccionado() {
		return idEjercicioSeleccionado;
	}


	public void setIdEjercicioSeleccionado(Long idEjercicioSeleccionado) {
		this.idEjercicioSeleccionado = idEjercicioSeleccionado;
	}


	public List getEjerciciosSelectItem() {
		return ejerciciosSelectItem;
	}


	public void setEjerciciosSelectItem(List ejerciciosSelectItem) {
		this.ejerciciosSelectItem = ejerciciosSelectItem;
	}

}

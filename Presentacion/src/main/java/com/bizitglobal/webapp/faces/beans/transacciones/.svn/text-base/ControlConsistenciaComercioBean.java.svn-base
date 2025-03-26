package com.bizitglobal.webapp.faces.beans.transacciones;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


public class ControlConsistenciaComercioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ControlConsistenciaComercioBean.class);

	private Date fechaHasta;
	private String popupReport = new String("");


	public ControlConsistenciaComercioBean() {
	}


	public void borrar() {
		error.borrar();
		fechaHasta = new Date();
		popupReport = new String("");
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Control Consistencia Comercio";
	}


	public String inicializar() {
		borrar();
		return "controlConsistenciaComercio";
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public String cancelar() {

		borrar();
		return "inicio";
	}


	public String generarPDF() {

		HttpServletRequest request = Session.getRequest();
		if (validarFecha()) {
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?Fecha_hasta="
					+ dateFormat.format(getFechaHasta());
			String p2 = "&URLImagen=" + Session.getHomePath()
					+ "/img/fiel/logo_fiel.jpg";
			String page = request.getContextPath() + "/report/ControlConsistenciaComercio.jrxml";
			popupReport = "popup('" + page + p1 + p2 + "',1000,600)";
			log.info(popupReport);
		} else {
			error.agregar("Error en el rango de fechas.");
		}
		return null;
	}


	public boolean validarFecha() {
		error.borrar();
		if (Validador.esNulo(getFechaHasta())
				|| getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}
package com.bizitglobal.webapp.faces.beans.transacciones;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.fondos.LibroMayorFondosBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


public class ItemsNoConciliadosBean extends BaseBean {
	private static final Logger log = Logger.getLogger(LibroMayorFondosBean.class);
	public static int renglon = 0;
	private Date fechaDesde;
	private Date fechaHasta;

	private String popupReport = new String("");


	public ItemsNoConciliadosBean() {
	}


	public void borrar() {
		error.borrar();
		fechaDesde = new Date();
		fechaHasta = new Date();
		popupReport = new String("");
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Items no conciliados";
	}


	public String inicializar() {
		borrar();
		return "itemsNoConciliados";
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
			String p1 = "?Fecha_desde="
					+ dateFormat.format(getFechaDesde());
			String p2 = "&Fecha_hasta="
					+ dateFormat.format(getFechaHasta());
			String p3 = "&URLImagen=" + Session.getHomePath()
					+ "/img/fiel/logo_fiel.jpg";

			String page = request.getContextPath() + "/report/itemsNoConciliados.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";
			log.info(popupReport);

		} else {
			error.agregar("Error en el rango de fechas.");
		}
		return null;
	}


	public boolean validarFecha() {
		error.borrar();
		if (Validador.esNulo(getFechaDesde())
				|| getFechaDesde().equals(new Date(0))
				|| Validador.esNulo(getFechaHasta())
				|| getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		} else {
			if (getFechaDesde().after(getFechaHasta())) {
				error
						.agregar("La fecha desde no puede ser mayor a la fecha hasta");
			}
		}
		return (error.cantidad() == 0) ? true : false;
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


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}

}
package com.bizitglobal.webapp.faces.beans.transacciones;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;

public class ReclamosBCRABean extends BaseBean{


	private static final Logger log = Logger.getLogger(ReclamosBCRABean.class);
	private Date fechaDesde;
	private Date fechaHasta;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");


	public ReclamosBCRABean() {
		super();
		fechaDesde = null;
		fechaHasta = null;
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


	public void borrar() {

		Calendar fecha = Calendar.getInstance();
		fechaHasta = fecha.getTime();
		fecha.add(Calendar.DAY_OF_MONTH, -1);
		// fecha.add(Calendar.MONTH, -12);
		fechaDesde = fecha.getTime();

		popupReport = new String("");
	}
	

	public String inicializar() {
		borrar();

		tituloLargo = "REPORTES";
		tituloCorto = "Reclamos BCRA";

		return "reclamosBCRA";
	}



	public boolean validar() {
		error.borrar();
		return false;

	}
	
	
	public boolean validarFecha() {
		error.borrar();

		if (Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String generar(ActionEvent event){
		
		popupReport = new String("");
		if(validarFecha()){
			HttpServletRequest request = Session.getRequest();
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "";
			String p2 = "";
			String p3 = "";
			p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
			p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			p3 = "&AExcel=excel";
	
			String page = request.getContextPath() + "/report/Reclamos.jrxml";
	
			popupReport = "popup('" + page + p1 + p2 + p3 +  "',1000,600)";
			log.info(popupReport);
		}
		
		return "reclamosBCRA";
	}

	
}

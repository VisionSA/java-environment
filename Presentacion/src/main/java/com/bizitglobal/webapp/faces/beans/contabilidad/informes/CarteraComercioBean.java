package com.bizitglobal.webapp.faces.beans.contabilidad.informes;

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

public class CarteraComercioBean extends BaseBean{
	private static final Logger log = Logger.getLogger(CarteraComercioBean.class);
	private Date fechaHasta;
	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");
	
	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public CarteraComercioBean () {
		super();
		fechaHasta = null;
	}

	public String getPopupReport() {
			return popupReport;
		}

	public void setPopupReport(String popupReport) {
			this.popupReport = popupReport;
		}

	
	@Override
	public void borrar() {
		Calendar fecha = Calendar.getInstance();
		fechaHasta = fecha.getTime();
		fecha.add(Calendar.DAY_OF_MONTH, -1);		

		popupReport = new String("");
	}

	public boolean validarFecha() {
		error.borrar();

		if (Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		}
		return (error.cantidad() == 0) ? true : false;
	}
	
	@Override
	public String inicializar() {
		borrar();

		tituloLargo = "INFORMES";
		tituloCorto = "Cartera de Comercio";

		return "carteraComercio";
	}

	@Override
	public boolean validar() {
		error.borrar();
		// TODO Auto-generated method stub
		return false;
	}
	
	public void generarReporteCarteraComercio(ActionEvent event){
		popupReport = new String("");
		if(validarFecha()){
			HttpServletRequest request = Session.getRequest();
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "";
			String p2 = "";
			p1 = "?fecha_hasta=" + dateFormat.format(fechaHasta);
			p2 = "&AExcel=excel";
	
			String page = request.getContextPath() + "/report/Cartera_comercios.jrxml";
	
			popupReport = "popup('" + page + p1 + p2 + "',1000,600)";
			log.info(popupReport);
		}
		
	}

}

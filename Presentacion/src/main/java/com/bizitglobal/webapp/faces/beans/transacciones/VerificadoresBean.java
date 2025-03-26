package com.bizitglobal.webapp.faces.beans.transacciones;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.exception.ParametroSistemaDetalleException;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistemaDetalle;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;

public class VerificadoresBean extends BaseBean{
	private static final Logger log = Logger.getLogger(VerificadoresBean.class);
	
	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");
	private ParametroSistemaDetalle paramSis;
	private String ultimaFecha;
	
	public String getUltimaFecha() {
		return ultimaFecha;
	}

	public void setUltimaFecha(String ultimaFecha) {
		this.ultimaFecha = ultimaFecha;
	}
	
	public ParametroSistemaDetalle getParamSis() {
		return paramSis;
	}

	public void setParamSis(ParametroSistemaDetalle paramSis) {
		this.paramSis = paramSis;
	}
	
	public String getPopupReport() {
		return popupReport;
	}

	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}

	@Override
	public void borrar() {
		super.borrarBaseBean();
		error.borrar();		
	}
	
	@Override
	public String inicializar() {
		borrar();
		
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Generación Listado Verificadores";
		//Este parametro del sistema contiene la última fecha en la que se procesó la lista de plásticos entregados
		try {
			paramSis = generalService.getParametroSistemaDetalleService().leerParametroSistemaDetalle(33L);
			ultimaFecha = paramSis.getValor();
		} catch (ParametroSistemaDetalleException e) {
			paramSis = null;
			e.printStackTrace();
		}		
		return "generarListadoVerificadores";
	}

	@Override
	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String generarListadoVerificadores(ActionEvent event) {
		
		popupReport = new String("");
		HttpServletRequest request = Session.getRequest();

			String p1 = "?AExcel=excel";
			String page = "";

			page = request.getContextPath() + "/report/Verificadores.jrxml";

			popupReport = "popup('" + page + p1  + "',1000,600)";
			log.info(popupReport);

		return "generarListadoVerificadores";
	}


}

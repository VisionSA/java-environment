package com.bizitglobal.webapp.faces.beans.transacciones;


import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.transacciones.exception.CtaCteClienteException;
import com.bizitglobal.webapp.faces.beans.BaseBean;


public class GenerarArchivosCDBCRABean extends BaseBean{
	private static Logger log = Logger.getLogger(GenerarArchivosCDBCRABean.class);
	private Date fechaCD;
	private String nroCD;
	private String tipoCD;
	private String mensaje;

	private PropertieFile prop = null;
	private String popupReport = new String("");
	

	public GenerarArchivosCDBCRABean() {
		super();
		fechaCD = null;
		nroCD = null;
		tipoCD = "N";
		mensaje="";
	}
	
	public Date getFechaCD() {
		return fechaCD;
	}

	public void setFechaCD(Date fechaCD) {
		this.fechaCD = fechaCD;
	}

	public String getNroCD() {
		return nroCD;
	}

	public void setNroCD(String nroCD) {
		this.nroCD = nroCD;
	}

	public String getTipoCD() {
		return tipoCD;
	}

	public void setTipoCD(String tipoCD) {
		this.tipoCD = tipoCD;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public PropertieFile getProp() {
		return prop;
	}

	public void setProp(PropertieFile prop) {
		this.prop = prop;
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
		fechaCD = fecha.getTime();
		nroCD = null;
		tipoCD = "N";
		mensaje="";
		fecha.add(Calendar.DAY_OF_MONTH, -1);
		popupReport = new String("");
	}
	
	@Override
	public String inicializar() {
		borrar();

		tituloLargo = "Tarjeta Fiel";
		tituloCorto = "Generación Archivos CD BCRA";

		return "cdBancoCentral";
	}

	@Override
	public boolean validar() {
		error.borrar();
		return false;
	}
	
	public String generar(){
		try {
			transaccionesService.getCtaCteClienteService().generarArchivosCDBCRA(fechaCD,nroCD,tipoCD);
			mensaje="Se generaron los archivos correctamente.";
		} catch (CtaCteClienteException e) {
			e.printStackTrace();
			mensaje="Error al generar los archivos.";
		}
		log.info(mensaje);
		
		return "cdBancoCentral";
	}
	

}

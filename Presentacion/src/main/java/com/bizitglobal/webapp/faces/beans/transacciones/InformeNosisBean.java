package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.ContextoProperties;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;

public class InformeNosisBean extends BaseBean{
	
	private static final Logger log = Logger.getLogger(InformeNosisBean.class);
	private Date fechaDesde;
	private Date fechaHasta;
	private String popupReport = new String("");
	
	public InformeNosisBean() {
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
		fechaDesde = fecha.getTime();

		popupReport = new String("");		
	}


	public boolean validar() {
		error.borrar();
		return false;
	}

	
	public String inicializar() {
		borrar();

		tituloLargo = "REPORTES";
		tituloCorto = "Informe Nosis";

		return "informeNosis";
	}
	
	
	public boolean validarFecha() {
		error.borrar();

		if (Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		}
		return (error.cantidad() == 0) ? true : false;
	}
	
	
	public String generar(ActionEvent event){
		
		String archivo = transaccionesService.getClienteTransaccionService().generarInformeNosis(fechaDesde,fechaHasta);
		verArchivo(archivo);
		return "informeNosis";
	}
	
	public String verArchivo(String archivo) {
		error.borrar();
		if ("".equals(archivo)) {
			log.error("No se encuentra el lote para obtener el archivo");
			error.agregar("No se encuentra el lote para obtener el archivo");
			return "informeNosis";
		}

		String url;
		try {
			url = getServerUrl();
		} catch (Exception e) {
			log.error("No se puede encontrar la url de la aplicacion");
			error.agregar("Error al intentar obtener la ruta de la aplicacion");
			return "informeNosis";
		}

		String serverFileFolder = "";
		try {
			serverFileFolder = getServerFileFolder(serverFileFolder);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("No se puede encontrar la url del contenedor de archivos del servidor");
			error.agregar("Error al intentar obtener la ruta del contenedor de archivos del servidor");
			return "informeNosis";
		}
		ejecutarJavaScript("window.open('http://" + url + serverFileFolder + archivo + "','popupEmbozo','left=20,top=20, width=1000,height=400')");

		return "informeNosis";
	}
	
	private String getServerUrl() throws Exception {
		String url = "";
		Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (request instanceof HttpServletRequest)
		{
			url = ((HttpServletRequest) request).getServerName() + ":" + ((HttpServletRequest) request).getServerPort();
		} else
		{
			throw new Exception("Error al intentar obtener la ruta de la aplicacion");
		}
		return url;
	}
	
	private String getServerFileFolder(String serverFileFolder) throws IOException {
		serverFileFolder = ContextoProperties.getProperty("directorioArchivos");

		serverFileFolder = serverFileFolder.replace("/webapps", "");
		return serverFileFolder;
	}

}

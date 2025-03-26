package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
//import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.ContextoProperties;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;

@SuppressWarnings({"rawtypes","unchecked"})
public class ClearingBean extends BaseBean{
	
	private static final Logger log = Logger.getLogger(ClearingBean.class);
	
	private FileReader fr = null;
	private BufferedReader br = null;
	
	private List tipoBusquedaItems = new ArrayList();
	private HtmlSelectOneMenu tipoAccion = new HtmlSelectOneMenu();
	private Long reporteSeleccionado;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");
	private String key = "catalina.home";


	public ClearingBean() {
		super();
		borrar();
		key = System.getProperty(key);
	}

	@Override
	public void borrar() {
		error.borrar();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Informes Clearing";
		popupReport = new String("");
		reporteSeleccionado = new Long(0);
		tipoBusquedaItems.clear();
		tipoBusquedaItems.add(new SelectItem(new Long(0), "Seleccione"));
		tipoBusquedaItems.add(new SelectItem(new Long(1), "Nosis"));
		tipoBusquedaItems.add(new SelectItem(new Long(2), "Credixa"));
		tipoBusquedaItems.add(new SelectItem(new Long(3), "Veraz"));
		tipoBusquedaItems.add(new SelectItem(new Long(4), "Codesa Afectaciones"));
		tipoBusquedaItems.add(new SelectItem(new Long(5), "Codesa Desafectaciones"));
	}

	@Override
	public boolean validar() {		
		error.borrar();
		return false;
	}

	@Override
	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "informesClearing";
	}
	
	public String generarInforme() {
		error.borrar();
		popupReport = new String("");
		Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		HttpServletRequest request = Session.getRequest();
		String p1 = "?AExcel=excel";
		Date fecha = new Date();
		String p2 = "&fecha=" + dateFormat.format(fecha);
		String page = "";

		switch(Integer.parseInt(tipoAccion.getValue().toString())){
			case 1:
				page = request.getContextPath() + "/report/Nosis.jrxml";
				popupReport = "popup('" + page + p1 +"',1000,600)";
				log.info(popupReport);
				break;
			case 2:
				page = request.getContextPath() + "/report/Credixa.jrxml";
				popupReport = "popup('" + page + p1 +"',1000,600)";
				break;
			case 3:
				String archivo = transaccionesService.getClienteTransaccionService().generarClearingVeraz();
				verArchivo(archivo);
				break;
			case 4:
				page = request.getContextPath() + "/report/Codesa_afectacion.jrxml";
				popupReport = "popup('" + page + p1 + p2 + "',1000,600)";
				break;
			case 5:
				page = request.getContextPath() + "/report/Codesa_desafectacion.jrxml";
				popupReport = "popup('" + page + p1 + p2 + "',1000,600)";
				break;
		}
		
		return "informesClearing";
	}
	
	public String verArchivo(String archivo) {
		error.borrar();
		if ("".equals(archivo)) {
			log.error("No se encuentra el lote para obtener el archivo");
			error.agregar("No se encuentra el lote para obtener el archivo");
			return "informesClearing";
		}

		String url;
		try {
			url = getServerUrl();
		} catch (Exception e) {
			log.error("No se puede encontrar la url de la aplicacion");
			error.agregar("Error al intentar obtener la ruta de la aplicacion");
			return "informesClearing";
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

		return "informesClearing";
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

	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}
	
	public List getTipoBusquedaItems() {
		return tipoBusquedaItems;
	}

	public void setTipoBusquedaItems(List tipoBusquedaItems) {
		this.tipoBusquedaItems = tipoBusquedaItems;
	}


	public HtmlSelectOneMenu getTipoAccion() {
		return tipoAccion;
	}


	public void setTipoAccion(HtmlSelectOneMenu tipoAccion) {
		this.tipoAccion = tipoAccion;
	}


	public Long getReporteSeleccionado() {
		return reporteSeleccionado;
	}


	public void setReporteSeleccionado(Long reporteSeleccionado) {
		this.reporteSeleccionado = reporteSeleccionado;
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}

	public FileReader getFr() {
		return fr;
	}

	public void setFr(FileReader fr) {
		this.fr = fr;
	}

}

package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


@SuppressWarnings({"rawtypes","unchecked"})
public class SolicitudesWebBean extends BaseBean {

	private static final Logger log = Logger.getLogger(SolicitudesWebBean.class);
	private Date fechaDesde;
	private Date fechaHasta;
	private Calendar fecha;

	private FileReader fr = null;
	private FileWriter fw = null;
	private BufferedReader br = null;

	private List tipoBusquedaItems = new ArrayList();
	private HtmlSelectOneMenu tipoAccion = new HtmlSelectOneMenu();
	private Long reporteSeleccionado;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");
	private PropertieFile prop = null;
	private String key = "catalina.home";
	

	public String generarXls() {
		
		error.borrar();
		popupReport = new String("");
		HttpServletRequest request = Session.getRequest();
		String p1 = "";
		
		p1 = "?AExcel=excel"; 
		//+ dateFormat.format(fechaDesde);
		//p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
		//p3 = "&AExcel=excel";

		String page = "";
		page = request.getContextPath() + "/report/SolicitudWeb.jrxml";

		
		popupReport = "popup('" + page + p1 + "',1000,600)";
		
		log.info(popupReport);

		return "SolicitudWeb";
	}
	
	
	

	/**
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	/**
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}


	/**
	 * @param fechaHasta
	 *            the fechaHasta to set
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	/**
	 * @return the fechaHasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	/**
	 * @param fw
	 *            the fw to set
	 */
	public void setFw(FileWriter fw) {
		this.fw = fw;
	}


	/**
	 * @return the fw
	 */
	public FileWriter getFw() {
		return fw;
	}


	/**
	 * @param fr
	 *            the fr to set
	 */
	public void setFr(FileReader fr) {
		this.fr = fr;
	}


	/**
	 * @return the fr
	 */
	public FileReader getFr() {
		return fr;
	}


	/**
	 * @param br
	 *            the br to set
	 */
	public void setBr(BufferedReader br) {
		this.br = br;
	}


	/**
	 * @return the br
	 */
	public BufferedReader getBr() {
		return br;
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




	@Override
	public void borrar() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public boolean validar() {
		// TODO Auto-generated method stub
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
		return "SolicitudesWeb";
	}

}

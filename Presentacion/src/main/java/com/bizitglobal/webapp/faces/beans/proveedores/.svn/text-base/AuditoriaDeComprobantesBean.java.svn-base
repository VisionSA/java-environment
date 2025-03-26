package com.bizitglobal.webapp.faces.beans.proveedores;

import java.sql.Timestamp;
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


public class AuditoriaDeComprobantesBean extends BaseBean {
	private static final Logger log = Logger.getLogger(AuditoriaDeComprobantesBean.class);
	private Long operadorDesde;
	private Long operadorHasta;
	private Date fechaDesde;
	private Date fechaHasta;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");


	public AuditoriaDeComprobantesBean() {
		super();
		operadorDesde = null;
		operadorHasta = null;
		fechaDesde = null;
		fechaHasta = null;
	}


	public Long getOperadorDesde() {
		return operadorDesde;
	}


	public void setOperadorDesde(Long operadorDesde) {
		this.operadorDesde = operadorDesde;
	}


	public Long getOperadorHasta() {
		return operadorHasta;
	}


	public void setOperadorHasta(Long operadorHasta) {
		this.operadorHasta = operadorHasta;
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
		fechaHasta = new Timestamp(new java.util.Date().getTime());

		Calendar fecha = Calendar.getInstance();
		Date date = new Date(fechaHasta.getTime());
		fecha.setTime(date);
		fecha.add(Calendar.YEAR, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());

		operadorDesde = null;
		operadorHasta = null;
		popupReport = new String("");
	}


	public String inicializar() {
		borrar();
		popupReport = new String("");
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Reporte Auditoria de Comprobantes";

		return "reporteAuditoriaDeComprobantes";
	}


	public boolean validar() {
		error.borrar();

		if (Validador.esNulo(getOperadorDesde()) || getOperadorDesde().equals(new Long(0)) || Validador.esNulo(getOperadorHasta())
				|| getOperadorHasta().equals(new Long(0))) {
			error.agregar(Error.OPERADOR_REQUERIDO);
		}
		if (Validador.esNulo(getFechaDesde()) || getFechaDesde().equals(new Date(0)) || Validador.esNulo(getFechaHasta())
				|| getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		} else {
			if (getFechaDesde().after(getFechaHasta())) {
				error.agregar(Error.REPORTE_FECHA_MENOR);
			}
		}

		if (error.cantidad() != 0) {
			return false;
		} else {
			long idOperador = Session.getOperador().getCodigo().longValue();
			/*
			 * if (proveedoresService.getProveedorReporteDao().InicializarReporteAuditoriaComp(idOperador, , , fechaDesde, fechaHasta)){ return true;
			 * }else{ return false; }
			 */
		}

		return true;
	}


	public void generar(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validar()) {
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?fecha_desde=" + dateFormat.format(getFechaDesde());
			String p2 = "&fecha_hasta=" + dateFormat.format(getFechaHasta());
			String p3 = "&op_desde=" + operadorDesde.toString();
			String p4 = "&op_hasta=" + operadorHasta.toString();
			String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

			String page = request.getContextPath() + "/report/ProvAuditoriaDeComp.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + "',1000,600)";

			log.info(popupReport);
		}
	}


	public void generarAExcel(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validar()) {
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?fecha_desde=" + dateFormat.format(getFechaDesde());
			String p2 = "&fecha_hasta=" + dateFormat.format(getFechaHasta());
			String p3 = "&op_desde=" + operadorDesde.toString();
			String p4 = "&op_hasta=" + operadorHasta.toString();
			String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p6 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/ProvAuditoriaDeComp.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + "',1000,600)";

			log.info(popupReport);
		}
	}
}

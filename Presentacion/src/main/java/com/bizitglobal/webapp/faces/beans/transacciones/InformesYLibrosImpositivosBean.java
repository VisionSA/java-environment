package com.bizitglobal.webapp.faces.beans.transacciones;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.fondos.LibroMayorFondosBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.webapp.faces.util.Error;


@SuppressWarnings({"rawtypes"})
public class InformesYLibrosImpositivosBean extends BaseBean {
	private static final Logger log = Logger.getLogger(LibroMayorFondosBean.class);
	public static int renglon = 0;

	/**
	 * Este es un campo oculto para guardar los id de los elementos modificados.
	 */
	private LinkedList renglones;

	private String cuentaABuscarEnLibro;
	private Date fechaDesde;
	private Date fechaHasta;
	
	private String cliente;
	private String resumido = "detallado"; //Por defecto detallado
	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");
	private String listaCuentas = "";


	public InformesYLibrosImpositivosBean() {
	}


	public void borrar() {
		error.borrar();
		popupReport = new String("");
	}


	public String inicializar() {
		borrar();
		fechaDesde = new Date();
		fechaHasta = new Date();
		return "informesYLibrosImpositivos";
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public String cancelar() {

		borrar();
		return "inicio";
	}


	public boolean validarFecha() {
		error.borrar();

		if (Validador.esNulo(getFechaDesde()) || getFechaDesde().equals(new Date(0))
				|| Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		} else {
			if (getFechaDesde().after(getFechaHasta())) {
				error.agregar("La fecha desde no puede ser mayor a la fecha hasta");
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String inicializarReporte() {
		borrar();
		fechaDesde = new Date();
		fechaHasta = new Date();
		return "reporteFondosLibroMayor";

	}


	public boolean validarDatosRequeridos() {
		if (listaCuentas == null || listaCuentas.compareTo("") == 0) {
			error.agregar("Ingrese los números de cuenta buscados, separados por coma.");
			return false;
		}
		return true;
	}


	public String generarPDF() {

		borrar();
		HttpServletRequest request = Session.getRequest();
		if (validarFecha()) {
			if (cliente != null && resumido != null) {
				Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String p1 = "?Fecha_desde=" + dateFormat.format(getFechaDesde());
				String p2 = "&Fecha_hasta=" + dateFormat.format(getFechaHasta());
				String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

				String page;

				if (cliente.equals("cliente")) { //Reporte Cliente
					if (resumido.equals("resumido")) { 
						page = request.getContextPath() + "/report/SubDiarioResumido_CLIE.jrxml";
					} else { //Detallado
						page = request.getContextPath() + "/report/SubDiarioDetallados_CLIE.jrxml";
					}
				} else { // Reporte Comercio
					if (resumido.equals("resumido")) { 
						page = request.getContextPath() + "/report/SubDiarioResumido_COM.jrxml";
					} else {  //Detallado
						page = request.getContextPath() + "/report/SubDiarioDetallados_COM.jrxml";
					}
				}

				popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";
				log.info(popupReport);
			} else {
				error.agregar("Debe seleccionar el tipo de informe para poder proceder");
			}
		} else {
			error.agregar("Error en el rango de fechas.");
		}
		return null;
	}


	public String generarAExcel() {
		borrar();
		HttpServletRequest request = Session.getRequest();
		if (validarFecha()) {
			if (cliente != null && resumido != null) {
				Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String p1 = "?Fecha_desde=" + dateFormat.format(getFechaDesde());
				String p2 = "&Fecha_hasta=" + dateFormat.format(getFechaHasta());
				String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
				String p4 = "&AExcel=excel";

				String page;

				if (cliente.equals("cliente")) { //Reporte Cliente
					if (resumido.equals("resumido")) {
						page = request.getContextPath() + "/report/SubDiarioResumido_ClieExel.jrxml";
					} else { //Detallado
						page = request.getContextPath() + "/report/SubDiarioDetallados_ClieExel.jrxml";
					}
				} else { // Reporte Comercio
					if (resumido.equals("resumido")) {
						page = request.getContextPath() + "/report/SubDiarioResumido_ComExel.jrxml";
					} else { //Detallado
						page = request.getContextPath() + "/report/SubDiarioDetallado_ComExel.jrxml";
					}
				}

				popupReport = "popup('" + page + p1 + p2 + p3 + p4 + "',1000,600)";
				log.info(popupReport);
			} else {
				error.agregar("Debe seleccionar el tipo de informe para poder proceder");
			}
		} else {
			error.agregar("Error en el rango de fechas.");
		}
		return null;
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


	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public String getResumido() {
		return resumido;
	}


	public void setResumido(String resumido) {
		this.resumido = resumido;
	}

}

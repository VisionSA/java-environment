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


public class LibrosEImpuestosSellosBean extends BaseBean {
	private static final Logger log = Logger
			.getLogger(LibroMayorFondosBean.class);
	public static int renglon = 0;

	private Date fechaDesde;
	private Date fechaHasta;
	private String cliente;
	private String resumido;

	private String popupReport = new String("");


	public LibrosEImpuestosSellosBean() {
	}


	public void borrar() {
		error.borrar();
		fechaDesde = null;
		fechaHasta = new Date();
		popupReport = new String("");
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Libros e Impuestos de Sellos";
	}


	public String inicializar() {
		borrar();
		return "librosEImpuestosSellos";
	}


	public boolean validar() {
		return false;
	}


	public String cancelar() {

		borrar();
		return "inicio";
	}


	public String generarPDF() {

		HttpServletRequest request = Session.getRequest();
		if (validarFecha()) {
			if (resumido != null) {
				Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String p1 = "?Fecha_desde="
						+ dateFormat.format(getFechaDesde());
				String p2 = "&Fecha_hasta="
						+ dateFormat.format(getFechaHasta());
				String p3 = "&URLImagen=" + Session.getHomePath()
						+ "/img/fiel/logo_fiel.jpg";

				String page = "";

				// if (cliente.equals("1")) {
				if (resumido.equals("1")) {
					page = request.getContextPath()
							+ "/report/Libros_Impuestos_de_Sellos_Resumido_CLIENTE.jrxml";
				} else {

					page = request.getContextPath()
							+ "/report/Libros_Impuestos_de_Sellos_Detallados_CLIENTE.jrxml";
				}
				/*
				 * } else { if (resumido.equals("1")) { page = request.getContextPath() +
				 * "/report/Libros_Impuestos_de_Sellos_Resumido_COMERCIO.jrxml"; } else { page = request.getContextPath() +
				 * "/report/Libros_Impuestos_de_Sellos_Detallados_COMERCIO.jrxml"; } }
				 */
				// if (cliente.equals("1")) {
				popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";
				log.info(popupReport);
				/*
				 * }else{ popupReport = new String(""); error.agregar("Pantalla de Comercios en Construcción"); }
				 */
			} else {
				error.agregar("Debe seleccionar el tipo de informe para poder proceder");
			}
		} else {
			error.agregar("Error en el rango de fechas.");
		}
		return null;
	}


	public String generarAExcel() {

		HttpServletRequest request = Session.getRequest();
		if (validarFecha()) {
			if (resumido != null) {
				Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String p1 = "?Fecha_desde="
						+ dateFormat.format(getFechaDesde());
				String p2 = "&Fecha_hasta="
						+ dateFormat.format(getFechaHasta());
				String p3 = "&URLImagen=" + Session.getHomePath()
						+ "/img/fiel/logo_fiel.jpg";
				String p4 = "&AExcel=excel";

				String page;

				// if (cliente.equals("1")) {
				if (resumido.equals("1")) {
					page = request.getContextPath()
							+ "/report/Libros_Impuestos_de_Sellos_Resumido_ClienteExel.jrxml";
				} else {
					page = request.getContextPath()
							+ "/report/Libros_Impuestos_de_Sellos_Detallados_ClienteExel.jrxml";
				}
				/*
				 * } else { if (resumido.equals("1")) { page = request.getContextPath() +
				 * "/report/Libros_Impuestos_de_Sellos_resumido_ComercioExel.jrxml"; } else { page = request.getContextPath() +
				 * "/report/Libros_Impuestos_de_Sellos_Detallado_ComercioExel.jrxml"; } }
				 */

				popupReport = "popup('" + page + p1 + p2 + p3 + p4
						+ "',1000,600)";
				log.info(popupReport);
				/*
				 * }else{ popupReport = new String(""); error.agregar("Pantalla de Comercios en Construcción"); }
				 */
			} else {
				error
						.agregar("Debe seleccionar el tipo de informe para poder proceder");
			}
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
				error.agregar("La fecha desde no puede ser mayor a la fecha hasta");
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


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}

}
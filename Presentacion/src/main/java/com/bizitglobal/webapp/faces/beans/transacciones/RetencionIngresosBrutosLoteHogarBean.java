package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.transacciones.exception.RetencionComercioSICOREException;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


public class RetencionIngresosBrutosLoteHogarBean extends BaseBean {
	private static final Logger log = Logger.getLogger(RetencionIngresosBrutosLoteHogarBean.class);
	private String cuit;
	private String cuitBusqueda;
	private Date fechaDesde;
	private Date fechaHasta;
	private Calendar fecha;

	FileReader fr = null;
	FileWriter fw = null;
	BufferedReader br = null;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");


	public RetencionIngresosBrutosLoteHogarBean() {
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


	public String getCuit() {
		return cuit;
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public void borrar() {
		/*
		 * fecha.setTime(fechaHasta); fecha.add(Calendar.MONTH, -1); fechaDesde = new Timestamp(fecha.getTime().getTime());
		 */
		fechaHasta = new Timestamp(new java.util.Date().getTime());
		fecha = Calendar.getInstance();
		fecha.setTime(new Date(fechaHasta.getTime()));
		fecha.add(Calendar.YEAR, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());
		popupReport = new String("");
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Retenciones Ingresos Brutos - Lote Hogar";
		error.borrar();
	}


	public boolean validar() {
		error.borrar();
		return false;
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


	public boolean validarFechaHasta() {
		error.borrar();

		if (Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_HASTA_REQUERIDA);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String inicializar() {
		/*
		 * fecha.setTime(fechaHasta); fecha.add(Calendar.MONTH, -1); fechaDesde = new Timestamp(fecha.getTime().getTime()); tituloLargo =
		 * "TARJETA FIEL"; tituloCorto = "Archivo SICORE Retencion Comercios";
		 */
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "retencionIngresosBrutosLoteHogar";
	}


	public void generarRetencionIngresosBrutosLoteHogar(ActionEvent event) {
		List sicoreList = new ArrayList();
		if (validarFecha()) {

			try {
				sicoreList = transaccionesService.getRetencionComercioSICOREService().obtenerRetencionLoteHogarIngBrutos(
						new Timestamp(fechaDesde.getTime()), new Timestamp(fechaHasta.getTime()));
			} catch (RetencionComercioSICOREException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String key = "catalina.home";
			key = System.getProperty(key);
			PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
			String linea = null;
			try {
				File f = null;
				try {
					/* @F29 */f = new File(key + "/" + prop.getProperties("directorioArchivos") + "/" + "RET_IIBB.txt");
					f.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				/* @F29 */fw = new FileWriter(key + "/" + prop.getProperties("directorioArchivos") + "/" + "RET_IIBB.txt");
				if (!sicoreList.isEmpty()) {
					Iterator iter = sicoreList.iterator();
					while (iter.hasNext()) {
						linea = iter.next().toString() + "\r\n";
						if (linea != null) {
							fw.write(linea);
						}
					}
				}
				fw.close();
				/* @F29 */ejecutarJavaScript("popup('" + "/../archivos/" + "RET_IIBB.txt" + "',700,400), 'titlebar=no';");
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}
}

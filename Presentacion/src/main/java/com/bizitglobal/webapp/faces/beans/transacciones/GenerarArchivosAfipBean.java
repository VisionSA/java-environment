package com.bizitglobal.webapp.faces.beans.transacciones;

import javax.faces.event.ActionEvent;
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
public class GenerarArchivosAfipBean extends BaseBean {

	private static final Logger log = Logger.getLogger(GenerarArchivosAfipBean.class);
	private Date fechaDesde;
	private Date fechaHasta;
	private Calendar fecha;

	private FileReader fr = null;
	private FileWriter fw = null;
	private BufferedReader br = null;

	private List tipoBusquedaItems = new ArrayList();
	private HtmlSelectOneMenu tipoAccion = new HtmlSelectOneMenu();
	private Long idTipoAccionSeleccionada;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");
	private PropertieFile prop = null;
	private String key = "catalina.home";


	public GenerarArchivosAfipBean() {
		super();
		borrar();
		key = System.getProperty(key);
		prop = new PropertieFile(key + "/webapps/contexto.properties");
	}


	@Override
	public void borrar() {
		super.borrarBaseBean();
		popup.borrar();
		error.borrar();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Generación Archivos AFIP";
		fechaHasta = new Timestamp(new java.util.Date().getTime());
		fecha = Calendar.getInstance();
		fecha.setTime(new Date(fechaHasta.getTime()));
		fecha.add(Calendar.MONTH, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());
		popupReport = new String("");
		idTipoAccionSeleccionada = new Long(0);
		tipoBusquedaItems.clear();
		tipoBusquedaItems.add(new SelectItem(new Long(0), "Seleccione"));
		tipoBusquedaItems.add(new SelectItem(new Long(1), "Comercios"));
		tipoBusquedaItems.add(new SelectItem(new Long(2), "Usuarios Titulares Nacionales"));
		tipoBusquedaItems.add(new SelectItem(new Long(3), "Usuarios Adicionales"));
		tipoBusquedaItems.add(new SelectItem(new Long(4), "Usuarios Titulares Extranjeros"));
	}


	public void borrarVerificador() {
		super.borrarBaseBean();
		popup.borrar();
		error.borrar();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Plásticos entregados por verificadores";
		fechaHasta = new Timestamp(new java.util.Date().getTime());
		fecha = Calendar.getInstance();
		fecha.setTime(new Date(fechaHasta.getTime()));
		fecha.add(Calendar.MONTH, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());
		popupReport = new String("");

	}


	@Override
	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "generarArchivosAfip";
	}


	public String inicializarVerificador() {
		borrarBaseBean();
		borrarVerificador();
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "generarListadoVerificadores";
	}


	@Override
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


	public String generar() {
		error.borrar();
		List lstInforme = new ArrayList();
		if (validarFecha()) {
			Long idTipoaccion = new Long(tipoAccion.getValue().toString());
			if (idTipoaccion.equals(new Long(1))) {

				try {
					lstInforme = transaccionesService.getArchivosAFIPService().obtenerInformeComercios(new Timestamp(fechaDesde.getTime()),
							new Timestamp(fechaHasta.getTime()));
				} catch (Exception e1) {
					error.agregar(e1.getMessage());
				}

				if (lstInforme != null && lstInforme.size() > 0) {
					generarArchivoComercio(lstInforme, "ArchivoComercio");
				}
			} else if (idTipoaccion.equals(new Long(2))) {

				try {
					lstInforme = transaccionesService.getArchivosAFIPService().obtenerInformeTitulares(new Timestamp(fechaDesde.getTime()),
							new Timestamp(fechaHasta.getTime()));
				} catch (Exception e1) {
					error.agregar(e1.getMessage());
				}

				if (lstInforme != null && lstInforme.size() > 0) {
					generarArchivoComercio(lstInforme, "ArchivoTitularesNacionales");
				}
			} else if (idTipoaccion.equals(new Long(3))) {

				try {
					lstInforme = transaccionesService.getArchivosAFIPService().obtenerInformeAdicionales(new Timestamp(fechaDesde.getTime()),
							new Timestamp(fechaHasta.getTime()));
				} catch (Exception e1) {
					error.agregar(e1.getMessage());
				}

				if (lstInforme != null && lstInforme.size() > 0) {
					generarArchivoComercio(lstInforme, "ArchivoAdicionales");
				}
			} else if (idTipoaccion.equals(new Long(4))) {

				try {
					lstInforme = transaccionesService.getArchivosAFIPService().obtenerInformeTitularesExtrankeros(
							new Timestamp(fechaDesde.getTime()), new Timestamp(fechaHasta.getTime()));
				} catch (Exception e1) {
					error.agregar(e1.getMessage());
				}
				if (lstInforme != null && lstInforme.size() > 0) {
					generarArchivoComercio(lstInforme, "ArchivoTitularesExtranjeros");
				} else {
					error.agregar("No hay Titulares Extranjeros en el Sistema");
				}
			}
		}
		return "generarArchivosAfip";
	}


	// public void generarListadoVerificadores(ActionEvent event) {
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


	private void generarArchivoComercio(List lstInforme, String nombreArchivo) {
		String linea = null;
		String fecha = "";

		Format dateFormat = new SimpleDateFormat("ddMMyyyy");
		fecha = dateFormat.format(new Date());
		try {
			File f = null;
			try {
				f = new File(key + File.separator + prop.getProperties("directorioArchivos") + File.separator + nombreArchivo + fecha + ".txt");
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			fw = new FileWriter(key + File.separator + prop.getProperties("directorioArchivos") + File.separator + nombreArchivo + fecha + ".txt");
			if (!lstInforme.isEmpty()) {
				Iterator iter = lstInforme.iterator();
				while (iter.hasNext()) {
					linea = iter.next().toString() + "\r\n";
					if (linea != null) {
						fw.write(linea);
					}
				}
			}
			fw.close();
			// ejecutarJavaScript("popup('" + "/../archivos/"+ nombreArchivo + fecha + ".txt" + "',700,400), 'titlebar=no';");
			if (f != null) {
				HttpServletResponse response = Session.getResponse();
				FileInputStream archivo2 = new FileInputStream(f.getPath());
				int longitud = archivo2.available();
				byte[] datos = new byte[longitud];
				archivo2.read(datos);
				archivo2.close();
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=\"" + f.getName() + "\"");
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(datos, 0, longitud);
				ouputStream.flush();
				ouputStream.close();
				Session.responseComplete();
			}
		} catch (Exception e) {
			e.getMessage();
		}
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


	public Long getIdTipoAccionSeleccionada() {
		return idTipoAccionSeleccionada;
	}


	public void setIdTipoAccionSeleccionada(Long idTipoAccionSeleccionada) {
		this.idTipoAccionSeleccionada = idTipoAccionSeleccionada;
	}

}

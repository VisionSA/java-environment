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
public class AfipRICompraVentaBean extends BaseBean {

	private static final Logger log = Logger.getLogger(AfipRICompraVentaBean.class);
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


	public AfipRICompraVentaBean() {
		super();
		borrar();
		key = System.getProperty(key);
		prop = new PropertieFile(key + "/webapps/contexto.properties");
	}


	@Override
	public void borrar() {
		error.borrar();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Generación Archivos AFIP RI Compra - Venta";
		fechaHasta = new Timestamp(new java.util.Date().getTime());
		fecha = Calendar.getInstance();
		fecha.setTime(new Date(fechaHasta.getTime()));
		fecha.add(Calendar.MONTH, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());
		popupReport = new String("");
		reporteSeleccionado = new Long(0);
		tipoBusquedaItems.clear();
		tipoBusquedaItems.add(new SelectItem(new Long(0), "Ventas - Comprobante"));
		tipoBusquedaItems.add(new SelectItem(new Long(1), "Ventas - Alícuota"));
		tipoBusquedaItems.add(new SelectItem(new Long(2), "Compras - Comprobante"));
		tipoBusquedaItems.add(new SelectItem(new Long(3), "Compras - Alícuota"));
	}


	@Override
	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "afipRICompraVenta";
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


	public String generarXls() {
		error.borrar();
		popupReport = new String("");
		HttpServletRequest request = Session.getRequest();

		Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String p1 = "";
		String p2 = "";
		String p3 = "";
		
		p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
		p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
		p3 = "&AExcel=excel";

		String page = "";
		switch(Integer.parseInt(tipoAccion.getValue().toString())){
			case 0:
				page = request.getContextPath() + "/report/Afip_Ventas_Comp.jrxml";
				break;
			case 1:
				page = request.getContextPath() + "/report/Afip_Ventas_Alicuota.jrxml";
				break;
			case 2:
				page = request.getContextPath() + "/report/Afip_Compras_Comp.jrxml";
				break;
			case 3:
				page = request.getContextPath() + "/report/Afip_Compras_Alicuota.jrxml";
				break;
		}
		
		
		popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";
		
		log.info(popupReport);



		
		return "afipRICompraVenta";
	}

	
	public String generarTxt() {
		error.borrar();
		List<String> lista;
		
		try{
			switch(Integer.parseInt(tipoAccion.getValue().toString())){
				case 0:
					lista = transaccionesService.getArchivosAFIPService().generarArchivoVentasComprobante(fechaDesde, fechaHasta);
					generarArchivo(lista,"Ventas-Comprobante");
					return "afipRICompraVenta";
				case 1:
					lista = transaccionesService.getArchivosAFIPService().generarArchivoVentasAlicuota(fechaDesde, fechaHasta);
					generarArchivo(lista,"Ventas-Alicuota");
					return "afipRICompraVenta";
				case 2:
					lista = transaccionesService.getArchivosAFIPService().generarArchivoComprasComprobante(fechaDesde, fechaHasta);
					generarArchivo(lista,"Compras-Comprobante");
					return "afipRICompraVenta";
				case 3:
					lista = transaccionesService.getArchivosAFIPService().generarArchivoComprasAlicuota(fechaDesde, fechaHasta);
					generarArchivo(lista,"Compras-Alicuota");
					return "afipRICompraVenta";
			}
		}
		catch (Exception e){
			e.printStackTrace();
			log.info("Error al obtener lista para generar archivo");
		}
		
		return "afipRICompraVenta";
	}
	
	


	private void generarArchivo(List lstInforme, String nombreArchivo) {
		
		String linea = null;
		
		try {
			File f = null;
			try {
				f = new File(key + File.separator + prop.getProperties("directorioArchivos") + "/AfipRI/" + nombreArchivo + ".txt");
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			fw = new FileWriter(key + File.separator + prop.getProperties("directorioArchivos") + "/AfipRI/" + nombreArchivo + ".txt");
			if (!lstInforme.isEmpty()) {
				Iterator iter = lstInforme.iterator();
				while (iter.hasNext()) {
					linea = iter.next().toString() + "\r\n";
					fw.write(linea);
				}
			}
			fw.close();
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


	public Long getReporteSeleccionado() {
		return reporteSeleccionado;
	}


	public void setReporteSeleccionado(Long reporteSeleccionado) {
		this.reporteSeleccionado = reporteSeleccionado;
	}

}

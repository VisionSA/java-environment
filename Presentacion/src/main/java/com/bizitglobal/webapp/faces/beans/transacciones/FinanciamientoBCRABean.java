package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.transacciones.exception.CtaCteClienteException;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;

public class FinanciamientoBCRABean extends BaseBean{
	private static Logger log = Logger.getLogger(FinanciamientoBCRABean.class);
	private Date fechaHasta;
	private String mensaje;

	private FileWriter fw = null;
	private String key = "catalina.home";
	private PropertieFile prop = null;
	private String popupReport = new String("");
	
	

	public FinanciamientoBCRABean () {
		super();
		fechaHasta = null;
		mensaje="";
		key = System.getProperty(key);
		prop = new PropertieFile(key + "/webapps/contexto.properties");
	}
	
	public Date getfechaHasta() {
		return fechaHasta;
	}

	public void setfechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public FileWriter getFw() {
		return fw;
	}

	public void setFw(FileWriter fw) {
		this.fw = fw;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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
		fechaHasta = fecha.getTime();
		mensaje="";
		fecha.add(Calendar.DAY_OF_MONTH, -1);
		popupReport = new String("");
	}
	
	@Override
	public String inicializar() {
		borrar();

		tituloLargo = "Tarjeta Fiel";
		tituloCorto = "Generación Archivo Financiamiento BCRA";

		return "financiamientoBCRA";
	}

	@Override
	public boolean validar() {
		error.borrar();
		return false;
	}
	
	public String generar(){

		try {
			List<StringBuffer> listaFinanciamiento =  transaccionesService.getCtaCteClienteService().generarArchivosFinanciacionBCRA(fechaHasta);
			generarArchivoComercio(listaFinanciamiento, "TARJCRED");
			
		} catch (CtaCteClienteException e) {
			e.printStackTrace();
		}
		
		return "financiamientoBCRA";
	}
	
	private void generarArchivoComercio(List<StringBuffer> lstInforme, String nombreArchivo) {
		String linea = null;

		try {
			File f = null;
			try {
				f = new File(key + File.separator + prop.getProperties("directorioArchivos") + "/FinanciamientoBCRA/" + nombreArchivo + ".txt");
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			fw = new FileWriter(key + File.separator + prop.getProperties("directorioArchivos") + "/FinanciamientoBCRA/" + nombreArchivo + ".txt");
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
	

}

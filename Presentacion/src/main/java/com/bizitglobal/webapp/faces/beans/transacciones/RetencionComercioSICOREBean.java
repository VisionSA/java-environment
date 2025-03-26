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


@SuppressWarnings({"rawtypes"})
public class RetencionComercioSICOREBean extends BaseBean {
	private static final Logger log = Logger.getLogger(RetencionComercioSICOREBean.class);
	private String cuit;
	private String cuitBusqueda;
	private Date fechaDesde;
	private Date fechaHasta;
	private Long idDesde;
	private Long idHasta;
	private String selectRadioButton;
	private Calendar fecha;

	FileReader fr = null;
	FileWriter fw = null;
	BufferedReader br = null;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");


	public RetencionComercioSICOREBean() {
		super();
		cuit = null;
		fechaDesde = null;
		fechaHasta = null;
		cuitBusqueda = null;
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


	public void setCuit(String cuit) {
		if (cuitBusqueda != null) {
			this.cuit = cuitBusqueda;
			cuitBusqueda = null;
		} else {
			this.cuit = cuit;
		}
	}


	public String getCuitBusqueda() {
		return cuitBusqueda;
	}


	public void setCuitBusqueda(String cuitBusqueda) {
		this.cuitBusqueda = cuitBusqueda;
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public String getSelectRadioButton() {
		return selectRadioButton;
	}


	public void setSelectRadioButton(String selectRadioButton) {
		this.selectRadioButton = selectRadioButton;
	}


	public void borrar() {
		/*
		 * fecha.setTime(fechaHasta); fecha.add(Calendar.MONTH, -1); fechaDesde = new Timestamp(fecha.getTime().getTime());
		 */
		cuit = null;
		fechaHasta = new Timestamp(new java.util.Date().getTime());

		fecha = Calendar.getInstance();
		fecha.setTime(new Date(fechaHasta.getTime()));
		fecha.add(Calendar.YEAR, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());

		cuitBusqueda = null;
		popupReport = new String("");
		idDesde = null;
		idHasta = null;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Archivo SICORE Retencion Comercios";
		error.borrar();

	}


	public boolean validar() {
		error.borrar();
		return false;
	}


	/*
	 * public boolean validarRangoProveedor() { error.borrar();
	 * 
	 * if (Validador.esNulo(idDesde) || Validador.esNulo(idHasta) || idDesde.equals(new Long(0)) || idHasta.equals(new Long(0))) {
	 * error.agregar("Debe ingresar un rango de codigos. El 0(cero) no es valido"); }else { if (idDesde.longValue() > idHasta.longValue()) {
	 * error.agregar("Rango invalido."); } } return (error.cantidad()==0)? true : false; }
	 */

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


	public Long getIdDesde() {
		return idDesde;
	}


	public void setIdDesde(Long idDesde) {
		this.idDesde = idDesde;
	}


	public Long getIdHasta() {
		return idHasta;
	}


	public void setIdHasta(Long idHasta) {
		this.idHasta = idHasta;
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

		// return "amConcepto";
		// Session.redirect("/tarjetafiel/transacciones/retencionComercioSICORE.jsf");
		return "retencionComercioSICORE";
	}


	/*
	 * public String generar(ActionEvent event) { HttpServletRequest request = Session.getRequest(); error.borrar(); popupReport = new String(""); try
	 * { CuitValido cuitValido = new CuitValido(cuit); }catch (CuitNoValidoException e1) { error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
	 * e1.printStackTrace(); return null; }catch (Exception e) { error.agregar(Error.PROVEEDOR_CUIT_INVALIDO); e.printStackTrace(); return null; }
	 * Proveedor proveedor = null; try { List proveedores = proveedoresService.getProveedorService().getProveedores(new
	 * Filtro("cuit",Filtro.IGUAL,cuit)); if(!proveedores.isEmpty()) { proveedor = (Proveedor)proveedores.get(0); }else {
	 * error.agregar("El número de CUIT no corresponde a ningun proveedor cargado."); return null; } } catch (ProveedorException e) {
	 * e.printStackTrace(); error.agregar("Error al leer el proveedor."); return null; }
	 * 
	 * if(proveedor!=null || validarFecha()) { Format dateFormat = new SimpleDateFormat("yyyy-MM-dd"); String p1 =
	 * "?id_prov_desde="+proveedor.getIdProveedor(); String p2 = "&fecha_desde="+dateFormat.format(fechaDesde); String p3 =
	 * "&fecha_hasta="+dateFormat.format(fechaHasta); String p4 = "&id_prov_hasta="+proveedor.getIdProveedor(); String p5 =
	 * "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
	 * 
	 * String page = request.getContextPath()+"/report/ProveedoresCtaCte.jrxml";
	 * 
	 * popupReport = "popup('"+page+p1+p2+p3+p4+p5+"',1000,600)";
	 * 
	 * log.info(popupReport); }else { error.agregar("Error en el rango de fechas."); return null; } return null; }
	 */

	/*
	 * public String buscarProveedorPopup() { log.info("Buscar proveedor!!!"); popupReport = new String(""); BuscarProveedorBean bean =
	 * (BuscarProveedorBean)Session.getBean("BuscarProveedorBean"); bean.inicializar(BuscarProveedorBean.REPORTE_PROV_CTA_CTE);
	 * 
	 * String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath(); path +=
	 * "/tarjetafiel/proveedores/popup/buscarProveedor.jsf"; ejecutarJavaScript("popup('"+ path +"',700,400), 'titlebar=no';"); return null; }
	 */

	public void generarRetencionComercioSICORE(ActionEvent event) {
		List sicoreList = new ArrayList();
		if (validarFecha()) {

			try {
				sicoreList = transaccionesService.getRetencionComercioSICOREService().obtenerSICORE(new Timestamp(fechaDesde.getTime()),
						new Timestamp(fechaHasta.getTime()));
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
					f = new File(key + "/" + prop.getProperties("directorioArchivos") + "/" + "SICORE_RET_COM.txt");
					f.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				fw = new FileWriter(key + "/" + prop.getProperties("directorioArchivos") + "/" + "SICORE_RET_COM.txt");
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
				ejecutarJavaScript("popup('" + "/../archivos/" + "SICORE_RET_COM.txt" + "',700,400), 'titlebar=no';");
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}
}

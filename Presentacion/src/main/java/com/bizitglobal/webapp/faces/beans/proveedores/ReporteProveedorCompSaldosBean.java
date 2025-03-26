package com.bizitglobal.webapp.faces.beans.proveedores;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


public class ReporteProveedorCompSaldosBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ReporteProveedorCompSaldosBean.class);
	private String idProveedor;
	private String idProveedorBusqueda;
	private Date fechaDesde;
	private Date fechaHasta;
	private Proveedor proveedor;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");


	public ReporteProveedorCompSaldosBean() {
		super();
		idProveedor = null;
		idProveedorBusqueda = null;
		fechaDesde = null;
		fechaHasta = null;
		proveedor = null;
	}


	public String getIdProveedorBusqueda() {
		return idProveedorBusqueda;
	}


	public void setIdProveedorBusqueda(String idProveedorBusqueda) {
		this.idProveedorBusqueda = idProveedorBusqueda;
	}


	public String getIdProveedor() {
		return idProveedor;
	}


	public void setIdProveedor(String idProveedor) {
		if (idProveedorBusqueda != null) {
			this.idProveedor = idProveedorBusqueda;
			idProveedorBusqueda = null;
		} else {
			this.idProveedor = idProveedor;
		}
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
		idProveedor = null;
		idProveedorBusqueda = null;

		fechaHasta = new Timestamp(new java.util.Date().getTime());

		Calendar fecha = Calendar.getInstance();
		Date date = new Date(fechaHasta.getTime());
		fecha.setTime(date);
		fecha.add(Calendar.YEAR, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());

		popupReport = new String("");
	}


	public String inicializar() {
		borrar();

		tituloLargo = "PROVEEDORES";
		tituloCorto = "Reporte Composición de Saldo";

		if (Session.getBean("BuscarProveedorBean") != null) {
			BuscarProveedorBean bean = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
			bean.borrar();
		}

		return "reporteProveedoresComSaldo";
	}


	public boolean validar() {
		error.borrar();

		if (Validador.esNulo(proveedor)) {
			error.agregar(Error.PROVEEDOR_CODIGO_INVALIDO);
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
			long idProveedor = proveedor.getIdProveedor().longValue();
			if (proveedoresService.getProveedorReporteDao().InicializarReporteCompSaldo(idOperador, idProveedor, fechaDesde, fechaHasta)) {
				return true;
			} else {
				return false;
			}
		}
	}


	public String generar(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		proveedor = null;
		error.borrar();
		popupReport = new String("");

		try {
			proveedor = proveedoresService.getProveedorService().leerProveedor(new Long(idProveedor));
		} catch (ProveedorException e) {
			e.printStackTrace();
		}

		if (validar()) {
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?id_proveedor=" + idProveedor;
			String p2 = "&fecha_desde=" + dateFormat.format(fechaDesde);
			String p3 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			String p4 = "&id_operador=" + Session.getOperador().getCodigo();
			String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

			String page = request.getContextPath() + "/report/ProveedoresCompSaldo.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + "',1000,600)";

			log.info(popupReport);
		}
		return null;
	}


	public String generarAExcel(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		proveedor = null;
		error.borrar();
		popupReport = new String("");

		try {
			proveedor = proveedoresService.getProveedorService().leerProveedor(new Long(idProveedor));
		} catch (ProveedorException e) {
			e.printStackTrace();
		}

		if (validar()) {
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?id_proveedor=" + idProveedor;
			String p2 = "&fecha_desde=" + dateFormat.format(fechaDesde);
			String p3 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			String p4 = "&id_operador=" + Session.getOperador().getCodigo();
			String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p6 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/ProveedoresCompSaldoExcel.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + "',1000,600)";

			log.info(popupReport);
		}
		return null;
	}


	public String buscarProveedorPopup() {
		log.info("Buscar proveedor!!!");
		BuscarProveedorBean bean = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
		bean.inicializar(BuscarProveedorBean.REPORTE_PROV_COMP_SALDO);

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/popup/buscarProveedor.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}

}

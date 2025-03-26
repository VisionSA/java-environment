package com.bizitglobal.webapp.faces.beans.transacciones;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


@SuppressWarnings({"rawtypes","unchecked"})
public class TransaccionesNoLiquidadasComercioBean extends BaseBean {
	private static final Logger log = Logger.getLogger(TransaccionesNoLiquidadasComercioBean.class);

	private Date fechaHasta;
	private String popupReport = new String("");
	private String codComercio = "";
	private String cuit = "";
	private String razonSocial = "";
	private String sucursal = "";
	private String estadosConciliacion = "";

	private List<String> selectedItems = new ArrayList();
	private List<SelectItem> selectItems;


	public TransaccionesNoLiquidadasComercioBean() {
	}


	public void borrar() {

		popupReport = new String("");
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Transacciones No Liquidadas Comercio";
		borrar2();
	}


	private void borrar2() {
		error.borrar();
		fechaHasta = new Date();
		selectedItems.add("P");
		selectedItems.add("C");
		selectedItems.add("X");
		codComercio = "";
		cuit = "";
		sucursal = "";
		estadosConciliacion = "";
	}


	public String inicializar() {
		borrar();
		return "transaccionesNoLiquidadasComercio";
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public String cancelar() {

		borrar();
		return "inicio";
	}


	public String generarPDF() {

		HttpServletRequest request = Session.getRequest();

		if (validarFecha()) {
			for (String selectedItem : selectedItems) {
				estadosConciliacion += selectedItem;
			}
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?Fecha_hasta="
					+ dateFormat.format(getFechaHasta());
			String p2 = "&URLImagen=" + Session.getHomePath()
					+ "/img/fiel/logo_fiel.jpg";
			String p3 = "&Estado_impacto=" + estadosConciliacion;
			String p4 = "&Comercio=" + codComercio;
			String p5 = "&Sucursal=" + sucursal;
			String p6 = "&Cuit=" + cuit;
			String page = request.getContextPath() + "/report/TransaccionesNoLiquidadasComercio.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + "',1000,600)";
			log.info(popupReport);
		}
		borrar2();
		return null;
	}


	public boolean validarFecha() {
		error.borrar();
		if (Validador.esNulo(getFechaHasta())
				|| getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public List<SelectItem> getSelectItems() {
		if (selectItems == null) {
			selectItems = new ArrayList<SelectItem>();
			selectItems.add(new SelectItem("P", "Pendientes"));
			selectItems.add(new SelectItem("C", "Conciliados"));
			selectItems.add(new SelectItem("X", "No Conciliados"));

		}
		return selectItems;
	}


	public List<String> getSelectedItems() {

		return selectedItems;
	}


	public void setSelectedItems(List<String> selectedItems) {
		this.selectedItems = selectedItems;
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	public String getCodComercio() {
		return codComercio;
	}


	public void setCodComercio(String codComercio) {
		this.codComercio = codComercio;
	}


	public String getCuit() {
		return cuit;
	}


	public void setCuit(String cuit) {
		this.cuit = cuit;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public String getEstadosConciliacion() {
		return estadosConciliacion;
	}


	public void setEstadosConciliacion(String estadosConciliacion) {
		this.estadosConciliacion = estadosConciliacion;
	}


	public String getSucursal() {
		return sucursal;
	}


	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

}
package com.bizitglobal.webapp.faces.beans.proveedores.wrappers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings({"rawtypes"})
public class ProveedorCalendar {
	private String fecha;
	// private String cuit;
	private Long idProveedor;
	private String razonSocial;
	private String alias;
	private String diaPago;
	private float fniTotal;
	private float opTotal;
	private List fni;
	private List op;

	NumberFormat numberFormat = NumberFormat.getInstance();


	public ProveedorCalendar(String fecha) {
		this(fecha, null, null, null, null, 0, 0, new ArrayList(), new ArrayList());
	}


	public ProveedorCalendar() {
		this(null, null, null, null, null, 0, 0, new ArrayList(), new ArrayList());
	}


	public ProveedorCalendar(String fecha, Long idProveedor, String razonSocial, String alias, String diaPago, float fniTotal, float opTotal,
			List fni, List op) {
		super();
		this.fecha = fecha;
		this.idProveedor = idProveedor;
		this.razonSocial = razonSocial;
		this.alias = alias;
		this.diaPago = diaPago;
		this.fniTotal = fniTotal;
		this.opTotal = opTotal;
		this.fni = fni;
		this.op = op;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public List getFni() {
		return fni;
	}


	public void setFni(List fni) {
		this.fni = fni;
	}


	public float getFniTotal() {
		return fniTotal;
	}


	public void setFniTotal(float fniTotal) {
		this.fniTotal = fniTotal;
	}


	public List getOp() {
		return op;
	}


	public void setOp(List op) {
		this.op = op;
	}


	public float getOpTotal() {
		return opTotal;
	}


	public void setOpTotal(float opTota) {
		this.opTotal = opTota;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public boolean esVacio() {
		return (fni.size() == 0 && op.size() == 0) ? true : false;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public boolean equals(Object unObject) {
		boolean result = false;
		if (unObject instanceof ProveedorCalendario) {
			ProveedorCalendar aux = (ProveedorCalendar) unObject;
			if (aux.fecha.equals(fecha)) {
				result = true;
			}
		}

		return result;
	}


	public String getFniText() {
		numberFormat.setMaximumFractionDigits(2);
		return numberFormat.format(new Double(fniTotal + "").doubleValue());
	}


	public String getOpText() {
		numberFormat.setMaximumFractionDigits(2);
		return numberFormat.format(new Double(opTotal + "").doubleValue());
	}


	public Long getIdProveedor() {
		return idProveedor;
	}


	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}


	public String getDiaPago() {
		return diaPago;
	}


	public void setDiaPago(String diaPago) {
		this.diaPago = diaPago;
	}

}

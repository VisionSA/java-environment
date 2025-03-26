package com.bizitglobal.webapp.faces.beans.proveedores.wrappers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings({"rawtypes"})
public class ProveedorCalendario {
	private String fecha;
	private String cuit;
	private String razonSocial;
	private String alias;
	private float fniTotal;
	private float fiTotal;
	private float opTotal;
	private List fni;
	private List fi;
	private List op;

	NumberFormat numberFormat = NumberFormat.getInstance();


	public ProveedorCalendario(String fecha) {
		this(fecha, null, null, null, 0, 0, 0, new ArrayList(), new ArrayList(), new ArrayList());
	}


	public ProveedorCalendario() {
		this(null, null, null, null, 0, 0, 0, new ArrayList(), new ArrayList(), new ArrayList());
	}


	public ProveedorCalendario(String fecha, String cuit, String razonSocial, String alias, float fniTotal, float fiTotal,
			float opTota, List fni, List fi, List op) {
		super();
		this.fecha = fecha;
		this.cuit = cuit;
		this.razonSocial = razonSocial;
		this.alias = alias;
		this.fniTotal = fniTotal;
		this.fiTotal = fiTotal;
		this.opTotal = opTota;
		this.fni = fni;
		this.fi = fi;
		this.op = op;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getCuit() {
		return cuit;
	}


	public void setCuit(String cuit) {
		this.cuit = cuit;
	}


	public List getFi() {
		return fi;
	}


	public void setFi(List fi) {
		this.fi = fi;
	}


	public float getFiTotal() {
		return fiTotal;
	}


	public void setFiTotal(float fiTotal) {
		this.fiTotal = fiTotal;
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
		return (fni.size() == 0 && fi.size() == 0 && op.size() == 0) ? true : false;
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
			ProveedorCalendario aux = (ProveedorCalendario) unObject;
			if (aux.fecha.equals(fecha)) {
				result = true;
			}
		}

		return result;
	}


	public String getFiText() {
		numberFormat.setMaximumFractionDigits(2);
		return numberFormat.format(new Double(fiTotal + "").doubleValue());
	}


	public String getFniText() {
		numberFormat.setMaximumFractionDigits(2);
		return numberFormat.format(new Double(fniTotal + "").doubleValue());
	}


	public String getOpText() {
		numberFormat.setMaximumFractionDigits(2);
		return numberFormat.format(new Double(opTotal + "").doubleValue());
	}
}

package com.bizitglobal.webapp.faces.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;


public class Imputacion {
	private Long idImputacion;
	private CuotasImputables cuotasImpOrden;
	private CuotasImputables cuotasImpComprobante;
	private Float monto;

	// Propiedades solamente utilizadas en el listado de imputaciones.
	private String ordenTipo = null;
	private String comprobanteTipo = null;
	private Timestamp fechaEmision = null;
	private String razonSocial;
	private String nroCuit;


	// private List listaOrdenes;
	// private List listaComprobantes;

	public Imputacion() {
		this(new CuotasImputables(), new CuotasImputables(), new Float(0));
	}


	// public Imputacion(List listaOrdenes, List listaComprobantes) {
	// this();
	// this.listaComprobantes = listaComprobantes;
	// this.listaOrdenes = listaOrdenes;
	// }

	public Imputacion(CuotasImputables orden, CuotasImputables comprobante, Float monto) {
		super();
		this.cuotasImpOrden = orden;
		this.cuotasImpComprobante = comprobante;
		this.monto = monto;
		this.idImputacion = new Long(0);
	}


	public CuotasImputables getCuotasImpComprobante() {
		return cuotasImpComprobante;
	}


	public void setCuotasImpComprobante(CuotasImputables cuotasImpComprobante) {
		this.cuotasImpComprobante = cuotasImpComprobante;
	}


	public CuotasImputables getCuotasImpOrden() {
		return cuotasImpOrden;
	}


	public void setCuotasImpOrden(CuotasImputables cuotasImpOrden) {
		this.cuotasImpOrden = cuotasImpOrden;
	}


	public Float getMonto() {
		BigDecimal montoMostrar = new BigDecimal(monto.floatValue()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return new Float(montoMostrar.floatValue());
	}


	public void setMonto(Float monto) {
		this.monto = monto;
	}


	// public List getListaComprobantes() {
	// return listaComprobantes;
	// }
	//
	// public void setListaComprobantes(List listaComprobantes) {
	// this.listaComprobantes = listaComprobantes;
	// }
	//
	// public List getListaOrdenes() {
	// return listaOrdenes;
	// }
	//
	// public void setListaOrdenes(List listaOrdenes) {
	// this.listaOrdenes = listaOrdenes;
	// }

	public Long getIdImputacion() {
		return idImputacion;
	}


	public void setIdImputacion(Long idImputacion) {
		this.idImputacion = idImputacion;
	}


	public String getComprobanteText() {
		// String result = "-";
		// boolean encontrado = false;
		// if(!listaComprobantes.isEmpty()) {
		// Iterator iter = listaComprobantes.iterator();
		// while(iter.hasNext() && !encontrado) {
		// SelectItem item = (SelectItem)iter.next();
		// if(new Long(item.getValue().toString()).equals(comprobante)) {
		// result = item.getLabel();
		// encontrado = true;
		// }
		// }
		// }
		// return result;
		return cuotasImpComprobante.getTipo();
	}


	public String getOrdenText() {
		// String result = "-";
		// boolean encontrado = false;
		// if(!listaOrdenes.isEmpty()) {
		// Iterator iter = listaOrdenes.iterator();
		// while(iter.hasNext() && !encontrado) {
		// SelectItem item = (SelectItem)iter.next();
		// if(new Long(item.getValue().toString()).equals(orden)) {
		// result = item.getLabel();
		// encontrado = true;
		// }
		// }
		// }
		// return result;
		return cuotasImpOrden.getTipo();
	}


	public boolean equals(Object unObj) {
		boolean result = false;
		if (unObj instanceof Imputacion) {
			Imputacion imp = (Imputacion) unObj;
			if (imp.getCuotasImpComprobante().equals(cuotasImpComprobante) &&
					imp.getCuotasImpOrden().equals(cuotasImpOrden)) {
				result = true;
			}
		}

		return result;
	}


	public String getComprobanteTipo() {
		return comprobanteTipo;
	}


	public void setComprobanteTipo(String comprobanteTipo) {
		this.comprobanteTipo = comprobanteTipo;
	}


	public String getOrdenTipo() {
		return ordenTipo;
	}


	public void setOrdenTipo(String ordenTipo) {
		this.ordenTipo = ordenTipo;
	}


	public String getFechaEmision() {
		Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(fechaEmision);
	}


	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}


	public String getNroCuit() {
		return nroCuit;
	}


	public void setNroCuit(String nroCuit) {
		this.nroCuit = nroCuit;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
}

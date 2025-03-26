package com.bizitglobal.webapp.faces.util;

import java.math.BigDecimal;

import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorImpuesto;


public class ImpConMonto {
	private Impuesto impuesto;
	private ProveedorImpuesto provImpuesto;


	public ImpConMonto() {
		impuesto = new Impuesto();
		provImpuesto = new ProveedorImpuesto();
		provImpuesto.setImporte(new BigDecimal(0));
	}


	public ImpConMonto(Impuesto impuesto, ProveedorImpuesto provImpuesto) {
		super();
		this.impuesto = impuesto;
		this.provImpuesto = provImpuesto;
	}


	public ImpConMonto(Impuesto impuesto) {
		this.impuesto = impuesto;
		provImpuesto = new ProveedorImpuesto();
		provImpuesto.setImporte(new BigDecimal(0));
	}


	public Impuesto getImpuesto() {
		return impuesto;
	}


	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}


	public ProveedorImpuesto getProvImpuesto() {
		if (provImpuesto.getImporte().equals(new BigDecimal(0)) || provImpuesto.getImporte() == null) {
			return null;
		} else {
			provImpuesto.setImpuesto(impuesto);
			provImpuesto.setEsPercepcion(impuesto.getPercepcion().toString());
			return provImpuesto;
		}

	}


	public void setProvImpuesto(ProveedorImpuesto provImpuesto) {
		this.provImpuesto = provImpuesto;
	}


	public BigDecimal getMonto() {
		if (provImpuesto.getImporte() == null) {
			provImpuesto.setImporte(new BigDecimal(0));
		}
		return provImpuesto.getImporte().setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}


	public void setMonto(BigDecimal monto) {
		provImpuesto.setImporte(monto);
	}
}
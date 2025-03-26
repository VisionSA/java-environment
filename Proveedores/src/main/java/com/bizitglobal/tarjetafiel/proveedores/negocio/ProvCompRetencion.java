package com.bizitglobal.tarjetafiel.proveedores.negocio;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;

public class ProvCompRetencion {
	private Long idRetencion;
	private Comprobante comprobante;
	private Retencion retencion;
	private Float porcAplicado;
	private BigDecimal monto;
	private Set exclusiones; 
	
	public ProvCompRetencion() {
		this(null,null,null,null);
	}

	public ProvCompRetencion(Comprobante comprobante, Retencion retencion, Float porcAplicado, BigDecimal monto) {
		super();
		this.comprobante = comprobante;
		this.retencion = retencion;
		this.porcAplicado = porcAplicado;
		this.monto = monto;
		this.exclusiones = new HashSet();
	}

	public Set getExclusiones() {
		return exclusiones;
	}

	public void setExclusiones(Set exclusiones) {
		this.exclusiones = exclusiones;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

	public Long getIdRetencion() {
		return idRetencion;
	}

	public void setIdRetencion(Long idRetencion) {
		this.idRetencion = idRetencion;
	}

	public Retencion getRetencion() {
		return retencion;
	}

	public void setRetencion(Retencion retencion) {
		this.retencion = retencion;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Float getPorcAplicado() {
		return porcAplicado;
	}

	public void setPorcAplicado(Float porcAplicado) {
		this.porcAplicado = porcAplicado;
	}


	
}

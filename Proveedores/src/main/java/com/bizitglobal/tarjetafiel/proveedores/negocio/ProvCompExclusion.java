package com.bizitglobal.tarjetafiel.proveedores.negocio;

import java.math.BigDecimal;

import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;

public class ProvCompExclusion {
	private Long idExclusion;
	private ProvCompRetencion retencion;
	private Exclusion exclusion;
	private Float porcAplicado;
	private BigDecimal monto;
	
	public ProvCompExclusion() {
		this(null,null,null,null,null);
	}
	
	

	public ProvCompExclusion(Long idExclusion, ProvCompRetencion retencion, Exclusion exclusion, Float porcAplicado, BigDecimal monto) {
		super();
		this.idExclusion = idExclusion;
		this.retencion = retencion;
		this.exclusion = exclusion;
		this.porcAplicado = porcAplicado;
		this.monto = monto;
	}

	public ProvCompRetencion getRetencion() {
		return retencion;
	}

	public void setRetencion(ProvCompRetencion retencion) {
		this.retencion = retencion;
	}

	public Exclusion getExclusion() {
		return exclusion;
	}

	public void setExclusion(Exclusion exclusion) {
		this.exclusion = exclusion;
	}

	public Long getIdExclusion() {
		return idExclusion;
	}

	public void setIdExclusion(Long idExclusion) {
		this.idExclusion = idExclusion;
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

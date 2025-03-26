package com.bizitglobal.tarjetafiel.impuestos.negocio;

import java.math.BigDecimal;

public class TramosRetencion {
	private Long idTramoRetencion;
	private BigDecimal montoDesde;
	private BigDecimal montoHasta;
	private BigDecimal montoMinimo;
	private Float porcRetencion;
	private BigDecimal sobreExedente;
	private Retencion retencion;
	
	public TramosRetencion() {
		this(null,null,null,null,null,null,null);
	}
	
	public TramosRetencion(Long idTramoRetencion, BigDecimal montoDesde, 
			BigDecimal montoHasta, BigDecimal montoMinimo, Float porcRetencion, 
			BigDecimal sobreExedente, Retencion retencion) {
		super();
		this.idTramoRetencion = idTramoRetencion;
		this.montoDesde = montoDesde;
		this.montoHasta = montoHasta;
		this.montoMinimo = montoMinimo;
		this.porcRetencion = porcRetencion;
		this.sobreExedente = sobreExedente;
		this.retencion = retencion;
	}

	public TramosRetencion(Long id){
	   	 this(id,null,null,null,null,null,new Retencion());
	}
	
	public Long getIdTramoRetencion() {
		return idTramoRetencion;
	}
	
	public void setIdTramoRetencion(Long idTramoRetencion) {
		this.idTramoRetencion = idTramoRetencion;
	}
	
	public BigDecimal getMontoMinimo() {
		return montoMinimo;
	}
	
	public void setMontoMinimo(BigDecimal montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
		
	public Float getPorcRetencion() {
		return porcRetencion;
	}

	public void setPorcRetencion(Float porcRetencion) {
		this.porcRetencion = porcRetencion;
	}

	public Retencion getRetencion() {
		return retencion;
	}
	
	public void setRetencion(Retencion retencion) {
		this.retencion = retencion;
	}
	
	public BigDecimal getSobreExedente() {
		return sobreExedente;
	}
	
	public void setSobreExedente(BigDecimal sobreExedente) {
		this.sobreExedente = sobreExedente;
	}
	
	public String toString() {
		return "Id:"+idTramoRetencion+"|MDesde:"+montoDesde+"|MHasta:"+montoHasta;
	}
	
	public boolean equals(Object unTramoRetencion) {
		boolean result = false;
		if(unTramoRetencion instanceof TramosRetencion) {
			TramosRetencion aux = (TramosRetencion)unTramoRetencion;
			if(aux.getIdTramoRetencion().equals(idTramoRetencion)) {
				result = true;
			}
		}
		
		return result;
	}

	public BigDecimal getMontoDesde() {
		return montoDesde;
	}

	public void setMontoDesde(BigDecimal montoDesde) {
		this.montoDesde = montoDesde;
	}

	public BigDecimal getMontoHasta() {
		return montoHasta;
	}

	public void setMontoHasta(BigDecimal montoHasta) {
		this.montoHasta = montoHasta;
	}

}

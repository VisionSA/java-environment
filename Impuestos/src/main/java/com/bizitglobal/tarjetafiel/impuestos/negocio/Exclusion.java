package com.bizitglobal.tarjetafiel.impuestos.negocio;

import java.sql.Timestamp;
import java.util.Set;

public class Exclusion {
	private Long idExclusion;
	private String descripcion;
	private Timestamp fechaDesde;
	private Timestamp fechaHasta;
	private Float porcentaje;
	private Individuo individuo;
	private String nroCertificado;
	private String periodoFiscal;
	private String resolucionGral;
	private String estado;
	private String nroLegajo;
	private Timestamp fechaEmcion;
	private TipoImpuesto tipoImpuesto;
	
	public Exclusion() {
		this(null,null,null,null,null,null,null,null,null,null,null,null,null);
	}

	public Exclusion(Long idExclusion, String descripcion, Timestamp fechaDesde, 
			Timestamp fechaHasta, Float porcentaje, Individuo individuo, 
			String nroCertificado, String periodoFiscal, String resolucionGral, 
			String estado, String nroLegajo, Timestamp fechaEmcion, TipoImpuesto tipoImpuesto) {
		super();
		this.idExclusion = idExclusion;
		this.descripcion = descripcion;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.porcentaje = porcentaje;
		this.individuo = individuo;
		this.nroCertificado = nroCertificado;
		this.periodoFiscal = periodoFiscal;
		this.resolucionGral = resolucionGral;
		this.estado = estado;
		this.nroLegajo = nroLegajo;
		this.fechaEmcion = fechaEmcion;
		this.tipoImpuesto = tipoImpuesto;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Timestamp fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Timestamp getFechaEmcion() {
		return fechaEmcion;
	}

	public void setFechaEmcion(Timestamp fechaEmcion) {
		this.fechaEmcion = fechaEmcion;
	}

	public Timestamp getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Timestamp fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Long getIdExclusion() {
		return idExclusion;
	}

	public void setIdExclusion(Long idExclusion) {
		this.idExclusion = idExclusion;
	}

	public Individuo getIndividuo() {
		return individuo;
	}

	public void setIndividuo(Individuo individuo) {
		this.individuo = individuo;
	}

	public String getNroCertificado() {
		return nroCertificado;
	}

	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	public String getNroLegajo() {
		return nroLegajo;
	}

	public void setNroLegajo(String nroLegajo) {
		this.nroLegajo = nroLegajo;
	}

	public String getPeriodoFiscal() {
		return periodoFiscal;
	}

	public void setPeriodoFiscal(String periodoFiscal) {
		this.periodoFiscal = periodoFiscal;
	}

	public Float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getResolucionGral() {
		return resolucionGral;
	}

	public void setResolucionGral(String resolucionGral) {
		this.resolucionGral = resolucionGral;
	}
	
	public TipoImpuesto getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	
	
	public String toString() {
		return "Id:"+idExclusion+"Descripcion:"+descripcion+"FDesde:"+fechaDesde+"FHasta:"+fechaHasta;
	}
	
	public boolean equals(Object unaExclusion) {
		boolean result = false;
		if(unaExclusion instanceof Exclusion) {
			Exclusion aux = (Exclusion)unaExclusion;
			if(aux.getIdExclusion().equals(idExclusion)) {
				result = true;
			}
		}
		
		return result;
	}

}

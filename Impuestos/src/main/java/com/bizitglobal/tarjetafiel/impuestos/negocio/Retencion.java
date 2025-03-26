package com.bizitglobal.tarjetafiel.impuestos.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Provincia;

public class Retencion implements Negocio {
	private Long idRetencion;
  	private String descripcion;
  	private String codigoRegimen;
  	private String codigoNorma;
  	private String acumulaPagos;
  	private Long idConceptoDetalle;
  	private BigDecimal minimoImponible;
  	private BigDecimal minimoRetencion;
  	private String cuentaContable;
  	private Timestamp vigenciaHasta;
  	private Timestamp vigenciaDesde = null;  	
  	private Provincia provincia;
  	private JurisdiccionActividad juridiccionActividad;
  	private Aplicable aplicable;
  	
  	private Set tramosRetenciones = new HashSet();	
  	
  	public Retencion() {
  		this(null,null,null,null,null,null,null,null,null,null,null,null);
  	}

	public Retencion(Long idRetencion, String descripcion, String codigoRegimen, 
			String codigoNorma, String acumulaPagos, BigDecimal minimoImponible, 
			BigDecimal minimoRetencion, String cuentaContable, Timestamp vigenciaHasta, 
			Provincia provincia, JurisdiccionActividad juridiccionActividad, 
			Aplicable aplicable) {
		super();
		this.idRetencion = idRetencion;
		this.descripcion = descripcion;
		this.codigoRegimen = codigoRegimen;
		this.codigoNorma = codigoNorma;
		this.acumulaPagos = acumulaPagos;
		this.minimoImponible = minimoImponible;
		this.minimoRetencion = minimoRetencion;
		this.cuentaContable = cuentaContable;
		this.vigenciaHasta = vigenciaHasta;
		this.provincia = provincia;
		this.juridiccionActividad = juridiccionActividad;
		this.aplicable = aplicable;
	}
     
	public Retencion(Long idRetencion){
		
		this(idRetencion,null,null,null,null,null,null,null,null,null,null,null);
		
	}

	public String getCodigoNorma() {
		return codigoNorma;
	}

	public void setCodigoNorma(String codigoNorma) {
		this.codigoNorma = codigoNorma;
	}

	public Long getId() {
		return idRetencion;
	}
	
	
	public String getAcumulaPagos() {
		return acumulaPagos;
	}

	public void setAcumulaPagos(String acumulaPagos) {
		this.acumulaPagos = acumulaPagos;
	}

	public Aplicable getAplicable() {
		return aplicable;
	}

	public void setAplicable(Aplicable aplicable) {
		this.aplicable = aplicable;
	}

	public String getCodigoRegimen() {
		return codigoRegimen;
	}

	public String getLabel() {
		return descripcion;
	}
	
	
	public void setCodigoRegimen(String codigoRegimen) {
		this.codigoRegimen = codigoRegimen;
	}

	public String getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getIdRetencion() {
		return idRetencion;
	}

	public void setIdRetencion(Long idRetencion) {
		this.idRetencion = idRetencion;
	}

	public JurisdiccionActividad getJuridiccionActividad() {
		return juridiccionActividad;
	}

	public void setJuridiccionActividad(JurisdiccionActividad juridiccionActividad) {
		this.juridiccionActividad = juridiccionActividad;
	}

	public BigDecimal getMinimoImponible() {
		return minimoImponible;
	}

	public void setMinimoImponible(BigDecimal minimoImponible) {
		this.minimoImponible = minimoImponible;
	}

	public BigDecimal getMinimoRetencion() {
		return minimoRetencion;
	}

	public void setMinimoRetencion(BigDecimal minimoRetencion) {
		this.minimoRetencion = minimoRetencion;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Timestamp getVigenciaHasta() {
		return vigenciaHasta;
	}

	public void setVigenciaHasta(Timestamp vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}
	
	public Set getTramosRetenciones() {
		return tramosRetenciones;
	}

	public void setTramosRetenciones(Set tramosRetenciones) {
		this.tramosRetenciones = tramosRetenciones;
	}

	public Timestamp getVigenciaDesde() {
		return vigenciaDesde;
	}

	public void setVigenciaDesde(Timestamp vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}	
	
	
	
	public Long getIdConceptoDetalle() {
		return idConceptoDetalle;
	}

	public void setIdConceptoDetalle(Long idConceptoDetalle) {
		this.idConceptoDetalle = idConceptoDetalle;
	}

	public String toString() {
		return "Id:"+idRetencion+"|Descripcion:"+descripcion;
	}

	public boolean equals(Object unaRetencion) {
		boolean result = false;
		
		if(unaRetencion instanceof Retencion) {
			Retencion aux = (Retencion)unaRetencion;
			if(aux.getIdRetencion().equals(idRetencion)) {
				result = true;
			}
		}
		
		return result;
	}

}

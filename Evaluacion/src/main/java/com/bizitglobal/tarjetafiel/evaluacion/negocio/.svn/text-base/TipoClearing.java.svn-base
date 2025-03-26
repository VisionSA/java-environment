package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class TipoClearing implements Negocio {
	
	private Long idTipoClearing = new Long(0);
	private String descripcion = "";
	private String esLocal = "";
	private String ulr = "";
	private Set clearings;

	public TipoClearing() {
		this(null,null,null,null,null);
	}
	
	public TipoClearing(Long idTipoClearing) {
		this(idTipoClearing,null,null,null,null);
	}

	public TipoClearing(Long idTipoClearing, String descripcion, String esLocal, String ulr, Set clearings) {
		super();
		this.idTipoClearing = idTipoClearing;
		this.descripcion = descripcion;
		this.esLocal = esLocal;
		this.ulr = ulr;
		this.clearings = clearings;
	}

	public Long getId() {
		return idTipoClearing;
	}
	
	public Long getIdTipoClearing() {
		return idTipoClearing;
	}
	
	public void setIdTipoClearing(Long idTipoClearing) {
		this.idTipoClearing = idTipoClearing;
	}

	public String getLabel() {
		return descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEsLocal() {
		return esLocal;
	}
	
	public void setEsLocal(String esLocal) {
		this.esLocal = esLocal;
	}

	public String getUlr() {
		return ulr;
	}
	
	public void setUlr(String ulr) {
		this.ulr = ulr;
	}

	private Set getClearings() {
		return clearings;
	}
	
	private void setClearings(Set clearings) {
		this.clearings = clearings;
	}
	
	public String toString() {
		
		return "Tipo Clearing: " + 
				"id: " + idTipoClearing + 
				", descripcion: " + descripcion + 
				", es Local: " + esLocal + 
				", url: " + ulr;
	}

}


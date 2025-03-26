package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class Observo implements Negocio {
	
	private Long idObservo = new Long(0);
	private String descripcion = "";
	private Set obsLaborales;

	public Observo() {
		this(null,null,null);
	}
	
	public Observo(Long idObservo) {
		this(idObservo, null, null);
	}

	public Observo(Long idObservo, String descripcion, Set obsLaborales) {
		super();
		this.idObservo = idObservo;
		this.descripcion = descripcion;
		this.obsLaborales = obsLaborales;
	}

	public Long getId() {
		return idObservo;
	}
	public Long getIdObservo() {
		return idObservo;
	}
	public void setIdObservo(Long idObservo) {
		this.idObservo = idObservo;
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

	private Set getObsLaborales() {
		return obsLaborales;
	}
	private void setObsLaborales(Set obsLaborales) {
		this.obsLaborales = obsLaborales;
	}
	
	public String toString() {
		
		return "Observo: " + 
				"id: " + idObservo + 
				", descripcion" + descripcion;
	}
}


package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class ViviendaEstado implements Negocio {
	private Long idVivEstado = new Long(0);
	private String descripcion = "";
	private Set individuos;
	private Set infoParticulares;

	public ViviendaEstado() {
		this(null,null,null,null);
	}
	
	public ViviendaEstado(Long idVivEstado) {
		this(idVivEstado,null,null,null);
	}

	public ViviendaEstado(Long idVivEstado, String descripcion, Set individuos, Set infoParticulares) {
		super();
		this.idVivEstado = idVivEstado;
		this.descripcion = descripcion;
		this.individuos = individuos;
		this.infoParticulares = infoParticulares;
	}

	public Long getId() {
		return idVivEstado;
	}
	
	public Long getIdVivEstado() {
		return idVivEstado;
	}
	
	public void setIdVivEstado(Long idVivEstado) {
		this.idVivEstado = idVivEstado;
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
	
	public Set getIndividuos() {
		return individuos;
	}

	public void setIndividuos(Set individuos) {
		this.individuos = individuos;
	}

	public Set getInfoParticulares() {
		return infoParticulares;
	}

	public void setInfoParticulares(Set infoParticulares) {
		this.infoParticulares = infoParticulares;
	}

	public String toString() {
		
		return "Vivienda Estado: " + 
				"id: " + idVivEstado + 
				", descripcion: " + descripcion;
	}

}


package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class CentroCostos implements Negocio {
	private Long idCentroCostos = new Long(0);
	private String descripcion = "";

	public CentroCostos() {
		this(new Long(0),null);
	}
	
	public CentroCostos(Long idCentroCostos) {
		this(idCentroCostos,null);
	}
	
	public CentroCostos(Long idCentroCostos, String descripcion) {
		super();
		this.idCentroCostos = idCentroCostos;
		this.descripcion = descripcion;
	}
	public Long getId() {
		return idCentroCostos;
	}
	public Long getIdCentroCostos() {
		return idCentroCostos;
	}
	public void setIdCentroCostos(Long idCentroCostos) {
		this.idCentroCostos = idCentroCostos;
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

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof CentroCostos) {
			CentroCostos aux = (CentroCostos)obj;
			if(aux.getId().equals(idCentroCostos)) {
				result = true;
			}
		}
		return result;
	}
}
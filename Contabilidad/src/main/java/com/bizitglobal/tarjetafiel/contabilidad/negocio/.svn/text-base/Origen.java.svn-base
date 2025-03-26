package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class Origen implements Negocio {
	private Long idOrigen = new Long(0);
	private String descripcion = "";

	public Origen() {
		this(new Long(0),null);
	}
	
	public Origen(Long idOrigen) {
		this(idOrigen,null);
	}
	
	public Origen(Long idOrigen, String descripcion) {
		super();
		this.idOrigen = idOrigen;
		this.descripcion = descripcion;
	}
	public Long getId() {
		return idOrigen;
	}
	public Long getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(Long idOrigen) {
		this.idOrigen = idOrigen;
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
		if(obj instanceof Origen) {
			Origen aux = (Origen)obj;
			if(aux.getId().equals(idOrigen)) {
				result = true;
			}
		}
		return result;
	}
}
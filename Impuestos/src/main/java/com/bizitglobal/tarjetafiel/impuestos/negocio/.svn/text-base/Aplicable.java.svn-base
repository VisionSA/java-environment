package com.bizitglobal.tarjetafiel.impuestos.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;


public class Aplicable implements Negocio {
	private Long idAplicable;
	private String descripcion;
	
	public Aplicable() {
		this(null,null);
	}
	
	public Aplicable(Long id) {
		this(id,null);
	}
	
	public Aplicable(Long idAplicable, String descripcion) {
		super();
		this.idAplicable = idAplicable;
		this.descripcion = descripcion;
	}
	
	public Long getId() {
		return idAplicable;
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
	
	public Long getIdAplicable() {
		return idAplicable;
	}
	
	public void setIdAplicable(Long idAplicable) {
		this.idAplicable = idAplicable;
	}
	
	public String toString() {
		return "Id:"+idAplicable+"|Descripcion:"+descripcion;
	}
	
	public boolean equals(Object unAplicable) {
		boolean result = false;
		if(unAplicable instanceof Aplicable) {
			Aplicable aux = (Aplicable)unAplicable;
			if(aux.getIdAplicable().equals(idAplicable)) {
				result = true;
			}
		}
		return result;
	}

}

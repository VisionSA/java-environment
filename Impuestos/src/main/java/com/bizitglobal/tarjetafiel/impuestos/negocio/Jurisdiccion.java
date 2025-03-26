package com.bizitglobal.tarjetafiel.impuestos.negocio;

import java.util.Set;
import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class Jurisdiccion implements Negocio{
	private Long idJurisdiccion;
  	private String descripcion;
  	private Set jurisActividades; 
  	
	public Jurisdiccion() {
		this(null,null);
	}
	
	public Jurisdiccion(Long id) {
		this(id,null);
	}
	
	public Jurisdiccion(Long idJurisdiccion, String descripcion) {
		super();
		this.idJurisdiccion = idJurisdiccion;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Long getIdJurisdiccion() {
		return idJurisdiccion;
	}
	
	public void setIdJurisdiccion(Long idJurisdiccion) {
		this.idJurisdiccion = idJurisdiccion;
	}
	
	public Set getJurisActividades() {
		return jurisActividades;
	}

	public void setJurisActividades(Set jurisActividades) {
		this.jurisActividades = jurisActividades;
	}

	public String toString() {
		return "Id:"+idJurisdiccion+"|Descripcion:"+descripcion;
	}
	
	public boolean equals(Object unaJurisdiccion) {
		boolean result = false;
		if(unaJurisdiccion instanceof Jurisdiccion) {
			Jurisdiccion aux = (Jurisdiccion)unaJurisdiccion;
			if(aux.getIdJurisdiccion().equals(idJurisdiccion)) {
				result = true;
			}
		}
		
		return result;
	}

	public Long getId() {
		return idJurisdiccion;
	}

	public String getLabel() {
		return descripcion;
	}

}

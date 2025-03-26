package com.bizitglobal.tarjetafiel.impuestos.negocio;

import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class JurisdiccionActividad implements Negocio{
	private Long idJurisdiccionActividad;
  	private String descActividad;
  	private Jurisdiccion jurisdiccion;
  	private Categoria categoria;
  	private Set retenciones;
  	private Aplicable aplicable;
  	
  	public JurisdiccionActividad() {
  		this(null,null,null,null);
  	}
  	
	public JurisdiccionActividad(Long idJurisdiccionActividad, String  descActividad, 
			Jurisdiccion jurisdiccion, Categoria categoria) {
		super();
		this.idJurisdiccionActividad = idJurisdiccionActividad;
		this.descActividad = descActividad;
		this.jurisdiccion = jurisdiccion;
		this.categoria = categoria;
	}

	public Long getId() {
		return idJurisdiccionActividad;
	}
	
	public String getLabel() {
		return descActividad;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescActividad() {
		return descActividad;
	}

	public void setDescActividad(String descActividad) {
		this.descActividad = descActividad;
	}

	public Long getIdJurisdiccionActividad() {
		return idJurisdiccionActividad;
	}
	
	public void setIdJurisdiccionActividad(Long idJurisdiccionActividad) {
		this.idJurisdiccionActividad = idJurisdiccionActividad;
	}
	
	public Jurisdiccion getJurisdiccion() {
		return jurisdiccion;
	}
	
	public void setJurisdiccion(Jurisdiccion jurisdiccion) {
		this.jurisdiccion = jurisdiccion;
	}
	
	public Set getRetenciones() {
		return retenciones;
	}

	public void setRetenciones(Set retenciones) {
		this.retenciones = retenciones;
	}

	
	public String toString() {
		return "Id:"+idJurisdiccionActividad +"|"+
				"Desc. Actividad:"+descActividad +"|"+
				"Categoria:"+categoria;
	}
	
	public boolean equals(Object unaJurisActividad) {
		boolean result = false;
		if(unaJurisActividad instanceof JurisdiccionActividad) {
			JurisdiccionActividad aux = (JurisdiccionActividad)unaJurisActividad;
			if(aux.getIdJurisdiccionActividad().equals(idJurisdiccionActividad)) {
				return result;
			}
			
		}
		return result;
	}

	public Aplicable getAplicable() {
		return aplicable;
	}

	public void setAplicable(Aplicable aplicable) {
		this.aplicable = aplicable;
	}

}

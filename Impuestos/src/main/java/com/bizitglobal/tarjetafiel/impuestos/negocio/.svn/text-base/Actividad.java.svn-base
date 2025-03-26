package com.bizitglobal.tarjetafiel.impuestos.negocio;

import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

/**@deprecated
 * @author hernan
 *
 */
public class Actividad implements Negocio{
	private Long idActividad;
	private String descripcion;
	private Set jurisdiccionActividades;
	
	public Actividad() {
		this(null,null);
	}
	
	public Actividad(Long idActividad, String descripcion) {
		super();
		this.idActividad = idActividad;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Long getIdActividad() {
		return idActividad;
	}
	
	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}
	
	public Set getJurisdiccionActividades() {
		return jurisdiccionActividades;
	}

	public void setJurisdiccionActividades(Set jurisdiccionActividades) {
		this.jurisdiccionActividades = jurisdiccionActividades;
	}

	
	
	public String toString() {
		return "Id:"+idActividad+"|Descripcion:"+descripcion;
	}
	
	public boolean equals(Object unaActividad) {
		boolean result = false;
		if(unaActividad instanceof Actividad) {
			Actividad aux = (Actividad)unaActividad;
			if(aux.getIdActividad().equals(idActividad)) {
				result = true;
			}
		}
		
		return result;
	}

	public Long getId() {
		return idActividad;
	}

	public String getLabel() {
		return descripcion;
	}
	
}

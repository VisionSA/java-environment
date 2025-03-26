package com.bizitglobal.tarjetafiel.operador.negocio;

import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class Rol implements Negocio {
	private Long idRol;
	private String descripcion;
	private Set menuItems = new HashSet();
	
	public Rol() {
		this(null,null);
	}
	
	public Rol(Long idRol) {
		this(idRol,null);
	}
	
	public Rol(Long idRol, String descripcion) {
		super();
		this.idRol = idRol;
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Long getIdRol() {
		return idRol;
	}
	
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public Set getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Set menuItems) {
		this.menuItems = menuItems;
	}
	
	public Long getId() {
		return idRol;
	}
	
	public String getLabel() {
		return descripcion;
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Rol) {
			Rol aux = (Rol)obj;
			if(aux.getIdRol().equals(idRol)) {
				result = true;
			}
		}
		return result;
	}
}
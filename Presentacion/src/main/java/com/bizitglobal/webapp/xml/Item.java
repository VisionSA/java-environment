package com.bizitglobal.webapp.xml;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings({"rawtypes"})
public class Item {
	private String nombre;
	private List itemPermisos;


	public Item() {
		this(null, new ArrayList());
	}


	public Item(String nombre, List itemPermisos) {
		super();
		this.nombre = nombre;
		this.itemPermisos = itemPermisos;
	}


	public List getItemPermisos() {
		return itemPermisos;
	}


	public void setItemPermisos(List itemPermisos) {
		this.itemPermisos = itemPermisos;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String toString() {
		return nombre;
	}

}

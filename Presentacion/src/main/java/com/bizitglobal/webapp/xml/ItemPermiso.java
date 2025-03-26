package com.bizitglobal.webapp.xml;

import java.io.Serializable;


public class ItemPermiso implements Serializable {
	private static final long serialVersionUID = -3856728934158848002L;
	private Integer id;
	private String clase;
	private String metodo;
	private String descripcion;
	private boolean otorgado;


	public ItemPermiso() {
		this(null, null, null, null);
	}


	public ItemPermiso(Integer id, String clase, String metodo, String descripcion) {
		super();
		this.id = id;
		this.clase = clase;
		this.metodo = metodo;
		this.descripcion = descripcion;
		this.otorgado = false;
	}


	public String getClase() {
		return clase;
	}


	public void setClase(String clase) {
		this.clase = clase;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getMetodo() {
		return metodo;
	}


	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}


	public String toString() {
		return "Id:" + id + "|Clase:" + clase + "|Metodo:" + metodo + "|Descripción:" + descripcion + "|otorgado:" + (otorgado ? "true" : "false");
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public boolean isOtorgado() {
		return otorgado;
	}


	public void setOtorgado(boolean otorgado) {
		this.otorgado = otorgado;
	}

}

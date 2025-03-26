package com.bizitglobal.tarjetafiel.operador.negocio;

public class MenuItemRelacion implements Comparable {
	private Integer idMenuItemRelacion;
	private MenuItem padre;
	private MenuItem hijo;
	
	public MenuItemRelacion() {
		this(null,null,null);
	}

	public MenuItemRelacion(Integer idMenuItemRelacion, MenuItem padre, MenuItem hijo) {
		super();
		this.idMenuItemRelacion = idMenuItemRelacion;
		this.padre = padre;
		this.hijo = hijo;
	}

	public MenuItem getHijo() {
		return hijo;
	}

	public void setHijo(MenuItem hijo) {
		this.hijo = hijo;
	}

	public Integer getIdMenuItemRelacion() {
		return idMenuItemRelacion;
	}

	public void setIdMenuItemRelacion(Integer idMenuItemRelacion) {
		this.idMenuItemRelacion = idMenuItemRelacion;
	}

	public MenuItem getPadre() {
		return padre;
	}

	public void setPadre(MenuItem padre) {
		this.padre = padre;
	}
	
	public int compareTo(Object obj) {
		MenuItemRelacion menu = (MenuItemRelacion)obj;
		return this.idMenuItemRelacion.compareTo(menu.getIdMenuItemRelacion());
	}
	
}

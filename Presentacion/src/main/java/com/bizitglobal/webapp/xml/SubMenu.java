package com.bizitglobal.webapp.xml;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings({"rawtypes"})
public class SubMenu {
	private String nombre;
	private List items;
	private List submenues;


	public SubMenu() {
		this(null, new ArrayList(), new ArrayList());
	}


	public SubMenu(String nombre, List items, List submenues) {
		super();
		this.nombre = nombre;
		this.items = items;
		this.submenues = submenues;
	}


	public List getItems() {
		return items;
	}


	public void setItems(List items) {
		this.items = items;
	}


	public List getSubmenues() {
		return submenues;
	}


	public void setSubmenues(List submenues) {
		this.submenues = submenues;
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

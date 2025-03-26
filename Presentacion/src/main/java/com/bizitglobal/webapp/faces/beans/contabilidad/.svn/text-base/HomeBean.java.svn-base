package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;


@SuppressWarnings({"rawtypes","unchecked"})


public class HomeBean {
	private String name;
	private String lastName;
	private List hobbiesItems = new ArrayList();
	private List hobbies = new ArrayList();


	public HomeBean() {
		hobbiesItems.add(new SelectItem("Cine"));
		hobbiesItems.add(new SelectItem("Teatro"));
		hobbiesItems.add(new SelectItem("Televisión"));
		hobbiesItems.add(new SelectItem("Lectura"));
		hobbiesItems.add(new SelectItem("Deporte"));
		hobbiesItems.add(new SelectItem("Fotografía"));
		hobbiesItems.add(new SelectItem("Viajes"));
	}


	public List getHobbiesItems() {
		return hobbiesItems;
	}


	public void setHobbiesItems(List hobbiesItems) {
		this.hobbiesItems = hobbiesItems;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName.toUpperCase();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name.toUpperCase();
	}


	public List getHobbies() {
		return hobbies;
	}


	public void setHobbies(List hobbies) {
		this.hobbies = hobbies;
	}


	public String inicializar() {
		/*
		 * borrarBaseBean(); borrar(); if (Session.getBean("ScrollBean") != null) { ScrollBean bean = (ScrollBean)Session.getBean("ScrollBean");
		 * bean.setHiddenScrollY(new Integer(0)); } cargarItems();
		 */
		return "pruebaajax4jsp";
	}

}

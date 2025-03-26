package com.bizitglobal.webapp.faces.beans.util;

public class ScrollBean {
	private Integer hiddenScrollY;


	public ScrollBean() {
		super();
		this.hiddenScrollY = new Integer(0);
	}


	public Integer getHiddenScrollY() {
		return hiddenScrollY;
	}


	public void setHiddenScrollY(Integer hiddenScrollY) {
		this.hiddenScrollY = hiddenScrollY;
	}


	public void borrar() {
		this.hiddenScrollY = new Integer(0);
	}


	public String inicializar() {
		this.hiddenScrollY = new Integer(0);
		return null;
	}
}

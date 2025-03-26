package com.bizitglobal.webapp.faces.beans;

public class SessionBean {
	private boolean reiniciar;


	public SessionBean() {
		this(false);
	}


	public SessionBean(boolean reiniciar) {
		super();
		this.reiniciar = reiniciar;
	}


	public boolean getReiniciar() {
		boolean copia = reiniciar;
		reiniciar = false;
		return copia;
	}


	public void setReiniciar(boolean reiniciar) {
		this.reiniciar = reiniciar;
	}

}

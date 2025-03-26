package com.bizitglobal.webapp.faces.beans.evaluacion.wrappers;

import com.bizitglobal.tarjetafiel.evaluacion.negocio.Observo;


public class WrappersObservo {

	private Observo observo;
	private boolean seleccionado;


	public WrappersObservo(Observo observo) {
		this(observo, false);
	}


	public WrappersObservo() {
		this(null, false);
	}


	public WrappersObservo(Observo observo, boolean seleccionado) {
		super();
		this.observo = observo;
		this.seleccionado = seleccionado;
	}


	public Observo getObservo() {
		return observo;
	}


	public void setObservo(Observo observo) {
		this.observo = observo;
	}


	public boolean getSeleccionado() {
		return seleccionado;
	}


	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}


	public void setDescripcion(String descripcion) {
		observo.setDescripcion(descripcion);
	}


	public String getDescripcion() {
		return observo.getDescripcion();
	}
}

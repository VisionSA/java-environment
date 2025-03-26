package com.bizitglobal.webapp.faces.beans.impuestos.wrappers;

import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;


public class TipoImpuestoWrappers {

	private Long idTipoImpuestoWrappers;
	private boolean estado;
	private Jurisdiccion jurisdiccion;


	public TipoImpuestoWrappers(Long idTipoImpuestoWrappers, boolean estado, Jurisdiccion jurisdiccion) {
		this.idTipoImpuestoWrappers = idTipoImpuestoWrappers;
		this.estado = estado;
		this.jurisdiccion = jurisdiccion;
	}


	public TipoImpuestoWrappers(Long idTipoImpuestoWrappers) {
		this(idTipoImpuestoWrappers, false, new Jurisdiccion());
	}


	public TipoImpuestoWrappers() {
		this(null, false, new Jurisdiccion());
	}


	public TipoImpuestoWrappers(Jurisdiccion jurisdiccion) {
		this(null, false, jurisdiccion);
	}


	public boolean getEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public Long getIdTipoImpuestoWrappers() {
		return idTipoImpuestoWrappers;
	}


	public void setIdTipoImpuestoWrappers(Long idTipoImpuestoWrappers) {
		this.idTipoImpuestoWrappers = idTipoImpuestoWrappers;
	}


	public Jurisdiccion getJurisdiccion() {
		return jurisdiccion;
	}


	public void setJurisdiccion(Jurisdiccion jurisdiccion) {
		this.jurisdiccion = jurisdiccion;
	}
}

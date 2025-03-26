package com.bizitglobal.webapp.faces.beans.workflow.wrappers;

import com.bizitglobal.workflow.negocio.Tramite;


/**
 * @author Hernan Esta clase representa un tramite para ser mostrado
 */
public class TramiteTabla {
	private Tramite tramite;
	private String nombreTramite;
	private boolean verCancelarTramite = true;


	public TramiteTabla() {
		super();

	}


	public TramiteTabla(Tramite tramite, String nombreTramite) {
		super();
		this.tramite = tramite;
		this.nombreTramite = nombreTramite;
	}


	public String getVerCancelar() {
		return verCancelarTramite + "";
	}


	public boolean getVerCancelarTramite() {
		return verCancelarTramite;
	}


	public void setVerCancelarTramite(boolean verCancelarTramite) {
		this.verCancelarTramite = verCancelarTramite;
	}


	public String getNombreTramite() {
		return nombreTramite;
	}


	public void setNombreTramite(String nombreTramite) {
		this.nombreTramite = nombreTramite;
	}


	public Tramite getTramite() {
		return tramite;
	}


	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}


	public boolean equals(Object obj) {
		if (obj instanceof TramiteTabla == false)
			return false;
		TramiteTabla tramiteTabla = (TramiteTabla) obj;
		return tramiteTabla.getTramite().getIdTramite().equals(tramite.getIdTramite());
	}

}

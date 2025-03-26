package com.bizitglobal.webapp.faces.beans.evaluacion.wrappers;

import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;


public class SolicitudesSeleccionables {
	private Solicitud solicitud;
	private boolean seleccionado;
	private String promotor;
	private String numero;


	public SolicitudesSeleccionables(Solicitud solicitud, boolean seleccionado) {
		setSolicitud(solicitud);
		this.seleccionado = seleccionado;
	}


	public SolicitudesSeleccionables() {
		this(null, false);
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getPromotor() {
		return promotor;
	}


	public void setPromotor(String promotor) {
		this.promotor = promotor;
	}


	public boolean getSeleccionado() {
		return seleccionado;
	}


	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}


	public Solicitud getSolicitud() {
		return solicitud;
	}


	public void setSolicitud(Solicitud solicitud) {
		if (solicitud != null && solicitud.getPromotor() != null) {
			promotor = solicitud.getPromotor().getApellido() + ", " + solicitud.getPromotor().getNombre();
		} else {
			numero = "";
			promotor = "";
		}
		if (solicitud != null) {
			numero = solicitud.getNroSolicitud() + solicitud.getDV();
		} else {
			numero = "";
		}

		this.solicitud = solicitud;
	}

}
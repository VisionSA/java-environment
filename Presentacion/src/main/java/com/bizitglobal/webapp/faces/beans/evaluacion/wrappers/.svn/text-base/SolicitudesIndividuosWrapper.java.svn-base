package com.bizitglobal.webapp.faces.beans.evaluacion.wrappers;

import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudIndividuo;


public class SolicitudesIndividuosWrapper {

	private String numeroSolicitud;
	private String tipoIndividuo;
	private Long idSolicitudIndividuo;
	private SolicitudIndividuo solicitudIndividuo;
	private boolean estado;


	public SolicitudesIndividuosWrapper(String numeroSolicitud, String tipoIndividuo, Long id) {
		this.numeroSolicitud = numeroSolicitud;
		this.tipoIndividuo = tipoIndividuo;
		this.idSolicitudIndividuo = id;
	}


	public SolicitudesIndividuosWrapper(SolicitudIndividuo solicitudIndividuo, boolean estado) {
		this.solicitudIndividuo = solicitudIndividuo;
		this.estado = estado;
	}


	public SolicitudesIndividuosWrapper() {
	}


	public String getNumeroSolicitud() {
		return numeroSolicitud;
	}


	public void setNumeroSolicitud(String numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}


	public String getTipoIndividuo() {
		return tipoIndividuo;
	}


	public void setTipoIndividuo(String tipoIndividuo) {
		this.tipoIndividuo = tipoIndividuo;
	}


	public Long getIdSolicitudIndividuo() {
		return idSolicitudIndividuo;
	}


	public void setIdSolicitudIndividuo(Long idSolicitudIndividuo) {
		this.idSolicitudIndividuo = idSolicitudIndividuo;
	}


	public SolicitudIndividuo getSolicitudIndividuo() {
		return solicitudIndividuo;
	}


	public void setSolicitudIndividuo(SolicitudIndividuo solicitudIndividuo) {
		this.solicitudIndividuo = solicitudIndividuo;
	}


	public boolean getEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public String getDescripcionTipoIndividuo() {
		return solicitudIndividuo.getTipoIndividuo().getDescripcion();
	}


	public String getDatosPersonales() {
		return (solicitudIndividuo.getIndividuoEvaluacion().getApellido() + ", " + solicitudIndividuo.getIndividuoEvaluacion().getNombres());
	}


	public boolean getEstadoSolicitud() {
		return Convertidores.getBoolean(solicitudIndividuo.getAceptado());
	}
}

package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.sql.Timestamp;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class AlertaSolicitud implements Negocio {
	private Long idAlertaSolicitud = new Long(0);
	private String evaluada = "";
	private AlertaTipoIndividuo alertaTipoIndividuo;
	private Long idOperador = new Long(0);
	private SolicitudIndividuo solicitudIndividuo;
	private String observacion = "";
	private String superada = "";
	private Timestamp timestamp = null;

	public AlertaSolicitud() {
		this(null,null,null,null,null,null,null,null);
	}
	
	public AlertaSolicitud(Long idAlertaSolicitud) {
		this(idAlertaSolicitud,null,null,null,null,null,null,null);
	}

	public AlertaSolicitud(Long idAlertaSolicitud, String evaluada, AlertaTipoIndividuo alertaTipoIndividuo, Long idOperador, SolicitudIndividuo solicitudIndividuo, String observacion, String superada, Timestamp timestamp) {
		super();
		this.idAlertaSolicitud = idAlertaSolicitud;
		this.evaluada = evaluada;
		this.alertaTipoIndividuo = alertaTipoIndividuo;
		this.idOperador = idOperador;
		this.solicitudIndividuo = solicitudIndividuo;
		this.observacion = observacion;
		this.superada = superada;
		this.timestamp = timestamp;
	}



	public Long getId() {
		return idAlertaSolicitud;
	}
	
	public String getLabel() {
		return evaluada;
	}

	public AlertaTipoIndividuo getAlertaTipoIndividuo() {
		return alertaTipoIndividuo;
	}

	public void setAlertaTipoIndividuo(AlertaTipoIndividuo alertaTipoIndividuo) {
		this.alertaTipoIndividuo = alertaTipoIndividuo;
	}

	public String getEvaluada() {
		return evaluada;
	}

	public void setEvaluada(String evaluada) {
		this.evaluada = evaluada;
	}

	public Long getIdAlertaSolicitud() {
		return idAlertaSolicitud;
	}

	public void setIdAlertaSolicitud(Long idAlertaSolicitud) {
		this.idAlertaSolicitud = idAlertaSolicitud;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public SolicitudIndividuo getSolicitudIndividuo() {
		return solicitudIndividuo;
	}

	public void setSolicitudIndividuo(SolicitudIndividuo solicitudIndividuo) {
		this.solicitudIndividuo = solicitudIndividuo;
	}

	public String getSuperada() {
		return superada;
	}

	public void setSuperada(String superada) {
		this.superada = superada;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String toString() {
		
		return  "Alerta Solicitud: " +
				"id alerta: " + idAlertaSolicitud +
				", evaluada: " + evaluada +
				", alerta tipo individuo: " + alertaTipoIndividuo.toString()+
				", id operador: " + idOperador +
				", solicitud individuo: " + solicitudIndividuo.toString() +
				", observacion: " + observacion + 
				", superada: " + superada +
				", timestamp: " + timestamp;
	}
}


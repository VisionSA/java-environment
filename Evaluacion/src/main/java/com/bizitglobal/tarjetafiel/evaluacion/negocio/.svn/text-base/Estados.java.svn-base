package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class Estados implements Negocio {
	private Long idEstado = new Long(0);
	private String descripcion = "";
	private Set evaAlertasTipoIndiv;
	private Set evaSolicitudes;

	public Estados() {
		this(null,null,null,null);
	}
	
	public Estados(Long idEstado) {
		this(idEstado,null,null,null);
	}

	public Estados(Long idEstado, String descripcion, Set evaAlertasTipoIndiv, Set evaSolicitudes) {
		super();
		this.idEstado = idEstado;
		this.descripcion = descripcion;
		this.evaAlertasTipoIndiv = evaAlertasTipoIndiv;
		this.evaSolicitudes = evaSolicitudes;
	}

	public Long getId() {
		return idEstado;
	}
	
	public Long getIdEstado() {
		return idEstado;
	}
	
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public String getLabel() {
		return descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private Set getEvaAlertasTipoIndiv() {
		return evaAlertasTipoIndiv;
	}
	
	private void setEvaAlertasTipoIndiv(Set evaAlertasTipoIndiv) {
		this.evaAlertasTipoIndiv = evaAlertasTipoIndiv;
	}

	private Set getEvaSolicitudes() {
		return evaSolicitudes;
	}
	
	private void setEvaSolicitudes(Set evaSolicitudes) {
		this.evaSolicitudes = evaSolicitudes;
	}
	
	public String toString() {
		
		return "Estados: " + 
				"id: " + idEstado +
				", descripcion: " + descripcion + 
				", alerta tipo individuo: " + evaAlertasTipoIndiv.toString() +
				", solicitud: " + evaSolicitudes.toString();
	}

}


package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class TipoIndividuo implements Negocio {
	
	private Long idTipoIndividuo = new Long(0);
	private String descripcion = "";
	private Set alertasTipoIndiv;
	private Set solicIndividuos;

	public TipoIndividuo() {
		this(null,null,null,null);
	}
	
	
	public TipoIndividuo(Long idTipoIndividuo) {
		this(idTipoIndividuo,null,null,null);
	}

	public TipoIndividuo(Long idTipoIndividuo, String descripcion, Set alertasTipoIndiv, Set solicIndividuos) {
		super();
		this.idTipoIndividuo = idTipoIndividuo;
		this.descripcion = descripcion;
		this.alertasTipoIndiv = alertasTipoIndiv;
		this.solicIndividuos = solicIndividuos;
	}


	public Long getId() {
		return idTipoIndividuo;
	}
	
	public Long getIdTipoIndividuo() {
		return idTipoIndividuo;
	}
	
	public void setIdTipoIndividuo(Long idTipoIndividuo) {
		this.idTipoIndividuo = idTipoIndividuo;
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

	private Set getAlertasTipoIndiv() {
		return alertasTipoIndiv;
	}
	
	private void setAlertasTipoIndiv(Set alertasTipoIndiv) {
		this.alertasTipoIndiv = alertasTipoIndiv;
	}

	private Set getSolicIndividuos() {
		return solicIndividuos;
	}
	
	private void setSolicIndividuos(Set solicIndividuos) {
		this.solicIndividuos = solicIndividuos;
	}
	
	public String toString() {
		
		return "Tipo IndividuoEvaluacion: " + 
				"id: " + idTipoIndividuo +
				", descripcion: " + descripcion;
	}

}


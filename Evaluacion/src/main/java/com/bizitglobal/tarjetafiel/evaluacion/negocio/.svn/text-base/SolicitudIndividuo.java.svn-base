package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class SolicitudIndividuo implements Negocio {
	
	private Long idSolicitudIndividuo = new Long(0);
	private IndividuoEvaluacion individuoEvaluacion;
	private Solicitud solicitud;
	private TipoIndividuo tipoIndividuo;
	private String observacion = "";
	private String activo=null;
	private String confTelLaboral;
	private String confTelParticular;
	private String aceptado;
	private Set clearings;
	private Set confTelefonicas;
	private Set infoLaborales;
	private Set infoParticulares;
	private String nuevo;
	
	public SolicitudIndividuo() {
		this(null,null,null,null,null,null,new HashSet(),new HashSet(),new HashSet(),new HashSet(),null,null);
	}
	
	public SolicitudIndividuo(Long idSolicitudIndividuo) {
		this(idSolicitudIndividuo,null,null,null,null,null,new HashSet(),new HashSet(),new HashSet(),new HashSet(),null,null);
	}

	public SolicitudIndividuo(Long idSolicitudIndividuo, IndividuoEvaluacion individuoEvaluacion, Solicitud solicitud, TipoIndividuo tipoIndividuo, 
			String observacion, String acaptado,Set clearings, Set confTelefonicas,Set infoLaborales, Set infoParticulares, String confTelLaboral,String confTelParticular) {
		
		super();
		this.idSolicitudIndividuo = idSolicitudIndividuo;
		this.individuoEvaluacion = individuoEvaluacion;
		this.solicitud = solicitud;
		this.tipoIndividuo = tipoIndividuo;
		this.observacion = observacion;
		this.clearings = clearings;
		this.confTelefonicas = confTelefonicas;
		this.infoLaborales = infoLaborales;
		this.infoParticulares = infoParticulares;
		this.confTelLaboral = confTelLaboral;
		this.confTelParticular = confTelParticular;
		this.aceptado = acaptado;
	}

	public Long getId() {
		return idSolicitudIndividuo;
	}
	
	public Long getIdSolicIndividuo() {
		return idSolicitudIndividuo;
	}
	
	public void setIdSolicitudIndividuo(Long idSolicitudIndividuo) {
		this.idSolicitudIndividuo = idSolicitudIndividuo;
	}

	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	public IndividuoEvaluacion getIndividuoEvaluacion() {
		return individuoEvaluacion;
	}

	public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}

	public Solicitud getEvaSolicitudes() {
		return solicitud;
	}
	
	public void setEvaSolicitudes(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public TipoIndividuo getEvaTiposIndividuos() {
		return tipoIndividuo;
	}
	
	public void setEvaTiposIndividuos(TipoIndividuo tipoIndividuo) {
		this.tipoIndividuo = tipoIndividuo;
	}

	public String getObservacion() {
		return observacion;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Set getClearings() {
		return clearings;
	}

	public void setClearings(Set clearings) {
		this.clearings = clearings;
	}

	public Set getConfTelefonicas() {
		return confTelefonicas;
	}

	public void setConfTelefonicas(Set confTelefonicas) {
		this.confTelefonicas = confTelefonicas;
	}

	public IndividuoEvaluacion getIndividuo() {
		return individuoEvaluacion;
	}

	public void setIndividuo(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}

	public Set getInfoLaborales() {
		return infoLaborales;
	}

	public void setInfoLaborales(Set infoLaborales) {
		this.infoLaborales = infoLaborales;
	}

	public Set getInfoParticulares() {
		return infoParticulares;
	}

	public void setInfoParticulares(Set infoParticulares) {
		this.infoParticulares = infoParticulares;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public TipoIndividuo getTipoIndividuo() {
		return tipoIndividuo;
	}

	public void setTipoIndividuo(TipoIndividuo tipoIndividuo) {
		this.tipoIndividuo = tipoIndividuo;
	}

	public Long getIdSolicitudIndividuo() {
		return idSolicitudIndividuo;
	}

	public String toString() {
		
		return "Solicitud IndividuoEvaluacion: " + 
				"id: " + idSolicitudIndividuo + 
				", individuoEvaluacion: " + individuoEvaluacion +
				", solicitud: " + solicitud +
				", tipo individuoEvaluacion: " + tipoIndividuo +
				", observacion: " + observacion +
				", individuo aceptado: " + aceptado;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getConfTelLaboral() {
		return confTelLaboral;
	}

	public void setConfTelLaboral(String confTelLaboral) {
		this.confTelLaboral = confTelLaboral;
	}

	public String getConfTelParticular() {
		return confTelParticular;
	}

	public void setConfTelParticular(String confTelParticular) {
		this.confTelParticular = confTelParticular;
	}

	public String getAceptado() {
		return aceptado;
	}

	public void setAceptado(String aceptado) {
		this.aceptado = aceptado;
	}

	public String getNuevo() {
		return nuevo;
	}

	public void setNuevo(String nuevo) {
		this.nuevo = nuevo;
	}
	
	
}


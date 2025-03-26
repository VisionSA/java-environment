package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.sql.Timestamp;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class ConfirmacionTelefonica implements Negocio {
	private Long idConfTelefonica = new Long(0);
	private String correcDatEmp = "";
	private String correcDatPers = "";
	private String correcDomicilio = "";
	private String correcFecIng = "";
	private String correcIngresos = "";
	private String esLaboral = "";
	private Long idOperador = new Long(0);
	private SolicitudIndividuo solicitudIndividuo;
	private String observacion = "";
	private Timestamp timestamp = null;

	public ConfirmacionTelefonica() {
		this(null,null,null,null,null,null,null,null,null,null,null);
	}
	
	public ConfirmacionTelefonica(Long idConfTelefonica) {
		this(idConfTelefonica,null,null,null,null,null,null,null,null,null,null);
	}

	public ConfirmacionTelefonica(Long idConfTelefonica, String correcDatEmp, String correcDatPers, String correcDomicilio, String correcFecIng, String correcIngresos, String esLaboral, Long idOperador, SolicitudIndividuo solicitudIndividuo, String observacion, Timestamp timestamp) {
		super();
		this.idConfTelefonica = idConfTelefonica;
		this.correcDatEmp = correcDatEmp;
		this.correcDatPers = correcDatPers;
		this.correcDomicilio = correcDomicilio;
		this.correcFecIng = correcFecIng;
		this.correcIngresos = correcIngresos;
		this.esLaboral = esLaboral;
		this.idOperador = idOperador;
		this.solicitudIndividuo = solicitudIndividuo;
		this.observacion = observacion;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return idConfTelefonica;
	}
	
	public String getLabel() {
		return correcDatEmp;
	}
	
	public String getCorrecDatEmp() {
		return correcDatEmp;
	}

	public void setCorrecDatEmp(String correcDatEmp) {
		this.correcDatEmp = correcDatEmp;
	}

	public String getCorrecDatPers() {
		return correcDatPers;
	}

	public void setCorrecDatPers(String correcDatPers) {
		this.correcDatPers = correcDatPers;
	}

	public String getCorrecDomicilio() {
		return correcDomicilio;
	}

	public void setCorrecDomicilio(String correcDomicilio) {
		this.correcDomicilio = correcDomicilio;
	}

	public String getCorrecFecIng() {
		return correcFecIng;
	}

	public void setCorrecFecIng(String correcFecIng) {
		this.correcFecIng = correcFecIng;
	}

	public String getCorrecIngresos() {
		return correcIngresos;
	}

	public void setCorrecIngresos(String correcIngresos) {
		this.correcIngresos = correcIngresos;
	}

	public String getEsLaboral() {
		return esLaboral;
	}

	public void setEsLaboral(String esLaboral) {
		this.esLaboral = esLaboral;
	}

	public Long getIdConfTelefonica() {
		return idConfTelefonica;
	}

	public void setIdConfTelefonica(Long idConfTelefonica) {
		this.idConfTelefonica = idConfTelefonica;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String toString() {
		
		return "Generar el metodo toString() de la calse ConfirmacionTelefonica cuando haga falta.";
	}
}


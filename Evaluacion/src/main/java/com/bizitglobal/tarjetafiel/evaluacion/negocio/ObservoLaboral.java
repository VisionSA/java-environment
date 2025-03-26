package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class ObservoLaboral implements Negocio {
	
	private Long idObsLaboral = new Long(0);
	private InformeLaboral informeLaboral;
	private Observo observo;
	
	public ObservoLaboral() {
		this(null,null,null);
	}
	
	public ObservoLaboral(Long idObsLaboral) {
		this(idObsLaboral,null,null);
	}

	public ObservoLaboral(Long idObsLaboral, InformeLaboral informeLaboral, Observo observo) {
		super();
		this.idObsLaboral = idObsLaboral;
		this.informeLaboral = informeLaboral;
		this.observo = observo;
	}

	public Long getId() {
		return idObsLaboral;
	}
	public Long getIdObsLaboral() {
		return idObsLaboral;
	}
	public void setIdObsLaboral(Long idObsLaboral) {
		this.idObsLaboral = idObsLaboral;
	}

	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	public InformeLaboral getInformeLaboral() {
		return informeLaboral;
	}

	public void setInformeLaboral(InformeLaboral informeLaboral) {
		this.informeLaboral = informeLaboral;
	}

	public Observo getObservo() {
		return observo;
	}

	public void setObservo(Observo observo) {
		this.observo = observo;
	}

	public String toString() {
		
		return "Observo Laboral: " +
				"id: " + idObsLaboral +
				", informe laboral: " + informeLaboral.toString() +
				" observo: " + observo.toString();
	}

}


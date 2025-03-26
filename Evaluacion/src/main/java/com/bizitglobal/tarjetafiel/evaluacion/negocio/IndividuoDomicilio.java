package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;

public class IndividuoDomicilio implements Negocio {
	
	private Domicilio domicilio;
	private Long idIndivDomicilio;
	private IndividuoEvaluacion individuoEvaluacion;

	public IndividuoDomicilio() {
		this(null,new Domicilio(),null);
	}
	
	public IndividuoDomicilio(Long idIndivDomicilio) {
		this(idIndivDomicilio,new Domicilio(),null);
	}

	public IndividuoDomicilio(Long idIndivDomicilio, Domicilio domicilio , IndividuoEvaluacion individuoEvaluacion) {
		super();
		this.idIndivDomicilio = idIndivDomicilio;
		this.domicilio = domicilio;
		this.individuoEvaluacion = individuoEvaluacion;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	public Long getIdIndivDomicilio() {
		return idIndivDomicilio;
	}
	
	public void setIdIndivDomicilio(Long idIndivDomicilio) {
		this.idIndivDomicilio = idIndivDomicilio;
	}

	public Long getId() {
		
		return idIndivDomicilio;
	}
	
	public String toString() {
		
		return "IndividuoEvaluacion Domicilio: " +
				"id: " + idIndivDomicilio +
				", domicilio: " + domicilio.toString() + 
				", individuoEvaluacion: " + individuoEvaluacion.toString();
	}

	public IndividuoEvaluacion getIndividuoEvaluacion() {
		return individuoEvaluacion;
	}

	public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}
}


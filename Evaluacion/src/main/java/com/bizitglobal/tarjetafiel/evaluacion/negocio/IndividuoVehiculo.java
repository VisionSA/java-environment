package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Vehiculo;

public class IndividuoVehiculo implements Negocio {
	
	private Long idIndiVehiculo = new Long(0);
	private IndividuoEvaluacion individuoEvaluacion;
	private Vehiculo vehiculo;

	public IndividuoVehiculo() {
		this(null,new IndividuoEvaluacion(),new Vehiculo());
	}
	
	public IndividuoVehiculo(Long idIndiVehiculo) {
		this(idIndiVehiculo,new IndividuoEvaluacion(),new Vehiculo());
	}

	public IndividuoVehiculo(Long idIndiVehiculo, IndividuoEvaluacion individuoEvaluacion, Vehiculo vehiculo) {
		super();
		this.idIndiVehiculo = idIndiVehiculo;
		this.individuoEvaluacion = individuoEvaluacion;
		this.vehiculo = vehiculo;
	}

	public Long getId() {
		return idIndiVehiculo;
	}
	
	public Long getIdIndiVehiculo() {
		return idIndiVehiculo;
	}
	
	public void setIdIndiVehiculo(Long idIndiVehiculo) {
		this.idIndiVehiculo = idIndiVehiculo;
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

	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public String toString() {
		
		return "IndividuoEvaluacion Vehiculo: " +
				"id: " + idIndiVehiculo +
				", individuoEvaluacion: " + individuoEvaluacion.toString() +
				", vehiculo: " + vehiculo.toString();
	}
}


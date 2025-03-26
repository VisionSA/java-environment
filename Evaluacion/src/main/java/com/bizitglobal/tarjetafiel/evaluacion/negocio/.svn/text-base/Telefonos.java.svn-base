package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;

public class Telefonos implements Negocio {
	
	private Long idTelefono = new Long(0);
	private IndividuoEvaluacion individuoEvaluacion;
	private Telefono telefono;

	public Telefonos() {
		this(null,new IndividuoEvaluacion(),new Telefono());
	}
	
	public Telefonos(Long idTelefono) {
		this(idTelefono,new IndividuoEvaluacion(),new Telefono());
	}

	public Telefonos(Long idTelefono, IndividuoEvaluacion individuoEvaluacion, Telefono telefono) {
		super();
		this.idTelefono = idTelefono;
		this.individuoEvaluacion = individuoEvaluacion;
		this.telefono = telefono;
	}

	public Long getId() {
		return idTelefono;
	}
	
	public Long getIdTelefono() {
		return idTelefono;
	}
	
	public void setIdTelefono(Long idTelefono) {
		this.idTelefono = idTelefono;
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

	public Telefono getTelefono() {
		return telefono;
	}
	
	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}
	
	public String toString() {
		
		return "Telefonos: " + 
				"idTelefono: " + idTelefono + 
				", individuoEvaluacion: " + individuoEvaluacion.toString() +
				", telefono: " + telefono.toString();
	}
	
	public boolean equals(Object obj){
		if ( obj instanceof Telefonos == false)
			return false;
			
		Telefonos telefonos = (Telefonos)obj;
		// Atencion! Metodo modificado. Son considerados iguales si el id del telefonoque 
		//contiene telefonos es igual
		return telefonos.getTelefono().getIdTelefono().equals(telefono.getIdTelefono());
	}
}


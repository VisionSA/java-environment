package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Localidad;

public class CobradorXLocalidad implements Negocio {
	
	private Long idCobradorXLocalidad = new Long(0);
	private Cobrador cobrador;
	private Localidad localidad;

	public CobradorXLocalidad() {
		this(null,null,null);
	}
	
	public CobradorXLocalidad(Long idCobradorXLocalidad) {
		this(idCobradorXLocalidad,null,null);
	}

	public CobradorXLocalidad(Long idCobradorXLocalidad, Cobrador cobrador, Localidad localidad) {
		super();
		this.idCobradorXLocalidad = idCobradorXLocalidad;
		this.cobrador = cobrador;
		this.localidad = localidad;
	}

	public Long getId() {
		return idCobradorXLocalidad;
	}
	
	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	public Localidad getLocalidad() {
		return localidad;
	}
	
	
	

	public Cobrador getCobrador() {
		return cobrador;
	}

	public void setCobrador(Cobrador cobrador) {
		this.cobrador = cobrador;
	}

	public Long getIdCobradorXLocalidad() {
		return idCobradorXLocalidad;
	}

	public void setIdCobradorXLocalidad(Long idCobradorXLocalidad) {
		this.idCobradorXLocalidad = idCobradorXLocalidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}


	public String toString() {
		
		return "Cobrador Localidad: " + 
				"id: " + idCobradorXLocalidad+
				", promotor: " + cobrador.toString() +
				", localidad: " + localidad.toString(); 
	}
}


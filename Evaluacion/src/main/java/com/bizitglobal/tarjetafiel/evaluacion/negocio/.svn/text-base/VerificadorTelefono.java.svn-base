package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;

public class VerificadorTelefono implements Negocio {
	
	private Long idVerifTelefono;
	private Telefono telefono;
	private Verificador verificador;

	public VerificadorTelefono() {
		this(null,null,null);
	}
	
	public VerificadorTelefono(Long idVerifTelefono) {
		this(idVerifTelefono, null, null);
	}

	public VerificadorTelefono(Long idVerifTelefono, Telefono telefono, Verificador verificador) {
		super();
		this.idVerifTelefono = idVerifTelefono;
		this.telefono = telefono;
		this.verificador = verificador;
	}

	public Long getId() {
		return idVerifTelefono;
	}
	
	public Long getIdVerifTelefono() {
		return idVerifTelefono;
	}
	
	public void setIdVerifTelefono(Long idVerifTelefono) {
		this.idVerifTelefono = idVerifTelefono;
	}

	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	public Telefono getTelefono() {
		return telefono;
	}
	
	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}
	
	public Verificador getVerificador() {
		return verificador;
	}

	public void setVerificador(Verificador verificador) {
		this.verificador = verificador;
	}

	public String toString() {
		
		return "Verificador Telefono: " + 
				"id: " + idVerifTelefono + 
				", telefono: " + telefono.toString() + 
				", verificador: " + verificador.toString();
	}

}


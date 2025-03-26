package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;

public class Verificador implements Negocio {
	private Long idVerificador;
	private String apellido = "";
	private Domicilio domicilio;
	private Email email;
	private Partido partido;
	private String nombre = "";
	private String estado;
	private Timestamp fechaBaja;
	private Set verifTelefonos;

	public Verificador() {
		this(null,null,null,null,null,null,null,null,new HashSet());
	}
	
	public Verificador(Long idVerificador) {
		this(idVerificador,null,null,null,null,null,null,null,null);
	}

	public Verificador(Long idVerificador, String apellido, Domicilio domicilio, Email email, Partido partido, 
			String nombre, String estado, Timestamp fechaBaja, Set verifTelefonos) {
		
		super();
		this.idVerificador = idVerificador;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.email = email;
		this.partido = partido;
		this.nombre = nombre;
		this.estado = estado;
		this.fechaBaja = fechaBaja;
		this.verifTelefonos = verifTelefonos;
	}

	public Long getId() {
		return idVerificador;
	}
	
	public Long getIdVerificador() {
		return idVerificador;
	}
	
	public void setIdVerificador(Long idVerificador) {
		this.idVerificador = idVerificador;
	}

	public String getLabel() {
		return idVerificador+ " - " + apellido + ", " + nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Email getEmail() {
		return email;
	}
	
	public void setEmail(Email email) {
		this.email = email;
	}

	public Partido getPartido() {
		return partido;
	}
	
	public void setPartido(Partido partidos) {
		this.partido = partidos;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getVerifTelefonos() {
		return verifTelefonos;
	}
	
	public void setVerifTelefonos(Set verifTelefonos) {
		this.verifTelefonos = verifTelefonos;
	}
	 
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Timestamp fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String toString() {
		return "Verificador: " + 
				"id: " + idVerificador + 
				", apellido: " + apellido + 
				", nombre: " + nombre + 
				". domicilio: " + domicilio + 
				", partido: " + partido + 
				", email: " + email ;
	}

}


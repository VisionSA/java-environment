package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;

public class Cobrador implements Negocio {
	private Long idCobrador = null;
	private String apellido = "";
	private Domicilio domicilio;
	private String nombre = "";
	private Email email;
	private String estado;
	private Timestamp fechaBaja;
	
	//private Set localidadesDelCobrador;

	public Cobrador() {
		//this(null,null,null,null,null,null,null,new HashSet());
		this(null,null,null,null,null,null,null);
	}
	
	public Cobrador(Long idCobrador) {
		//this(idCobrador,null,null,null,null,null,null,new HashSet());
		this(idCobrador,null,null,null,null,null,null);
	}

	public Cobrador(Long idCobrador, String apellido, Domicilio domicilio, Email email, String nombre, 
			String estado, Timestamp fechaBaja) {
		super();
		this.idCobrador = idCobrador;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.email = email;
		this.nombre = nombre;
		this.estado = estado;
		this.fechaBaja = fechaBaja;
	//	this.localidadesDelCobrador = localidadesDelCobrador;
	}

	public Long getId() {
		return idCobrador;
	}
	
	public Long getIdCobrador() {
		return idCobrador;
	}
	
	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setIdCobrador(Long idCobrador) {
		this.idCobrador = idCobrador;
	}

	public String getLabel() {
		return apellido;
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

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public Set getLocalidadesDelCobrador() {
//		return localidadesDelCobrador;
//	}
//	
//	public void setLocalidadesDelCobrador(Set localidadesDelCobrador) {
//		this.localidadesDelCobrador = localidadesDelCobrador;
//	}
	
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
		return "Promotor: " + 
				"id: " + idCobrador +
				", apellido: " + apellido +
				", nombre: " + nombre + 
				", domicilio: " + domicilio + 
				", email: " + email;
	}
}


package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Email;

public class Promotor implements Negocio {
	private Long idPromotor = null;
	private String apellido = "";
	private Domicilio domicilio;
	private String nombre = "";
	private Email email;
	private String estado;
	private Timestamp fechaBaja;
	private Set promoTelefonos;

	public Promotor() {
		this(null,null,null,null,null,null,null,new HashSet());
	}
	
	public Promotor(Long idPromotor) {
		this(idPromotor,null,null,null,null,null,null,new HashSet());
	}

	public Promotor(Long idPromotor, String apellido, Domicilio domicilio, Email email, String nombre, 
			String estado, Timestamp fechaBaja,Set promoTelefonos) {
		super();
		this.idPromotor = idPromotor;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.email = email;
		this.nombre = nombre;
		this.estado = estado;
		this.fechaBaja = fechaBaja;
		this.promoTelefonos = promoTelefonos;
	}

	public Long getId() {
		return idPromotor;
	}
	
	public Long getIdPromotor() {
		return idPromotor;
	}
	
	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setIdPromotor(Long idPromotor) {
		this.idPromotor = idPromotor;
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

	public Set getPromoTelefonos() {
		return promoTelefonos;
	}
	
	public void setPromoTelefonos(Set promoTelefonos) {
		this.promoTelefonos = promoTelefonos;
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
		return "Promotor: " + 
				"id: " + idPromotor +
				", apellido: " + apellido +
				", nombre: " + nombre + 
				", domicilio: " + domicilio + 
				", email: " + email;
	}
}


package com.bizitglobal.tarjetafiel.impuestos.negocio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Individuo {
	private Long idIndividuo;
	private String cuit;
	private String denominacion;
	private Character integranteSoc;
	private Character empleador;
	private Set individuoCategorias = new HashSet();
	private Set exclusion = new HashSet();
	
	public Individuo() {
		this(null,null,null,null,null);
	}
	
	public Individuo(Long idIndividuo, String cuit, String denominacion,
			Character integranteSoc, Character empleador) {
		super();
		this.idIndividuo = idIndividuo;
		this.cuit = cuit;
		this.denominacion = denominacion;
		this.integranteSoc = integranteSoc;
		this.empleador = empleador;
	}

	public String getCuit() {
		return cuit;
	}
	
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	public String getDenominacion() {
		return denominacion;
	}
	
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Character getEmpleador() {
		return empleador;
	}

	public void setEmpleador(Character empleador) {
		this.empleador = empleador;
	}

	public Character getIntegranteSoc() {
		return integranteSoc;
	}

	public void setIntegranteSoc(Character integranteSoc) {
		this.integranteSoc = integranteSoc;
	}

	public Long getIdIndividuo() {
		return idIndividuo;
	}

	public void setIdIndividuo(Long idIndividuo) {
		this.idIndividuo = idIndividuo;
	}
	
	public Set getIndividuoCategorias() {
		return individuoCategorias;
	}

	public void setIndividuoCategorias(Set individuoCategorias) {
		this.individuoCategorias = individuoCategorias;
	}	
	
	public Set getExclusion() {
		return exclusion;
	}

	public void setExclusion(Set exclusion) {
		this.exclusion = exclusion;
	}

	
	
	
	public String toString()  {
		return "Cuit:"+cuit+"|Denominacion:"+denominacion;
	}
	
	public boolean equals(Object unIndivuo) {
		boolean result = false;
		if(unIndivuo instanceof Individuo ) {
			Individuo aux = (Individuo)unIndivuo;
			if(aux.getIdIndividuo().equals(idIndividuo)) {
				result = true;
			}
		}
		
		return result;
	}

}

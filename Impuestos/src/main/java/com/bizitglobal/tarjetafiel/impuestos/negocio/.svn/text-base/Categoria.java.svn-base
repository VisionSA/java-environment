package com.bizitglobal.tarjetafiel.impuestos.negocio;

import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class Categoria implements Negocio{
	private Long idCategoria;
  	private String codCategoria;
  	private String descripcion;
  	private TipoImpuesto tipoImpuesto;
  	
	private Set impuestos = new HashSet();
	private Set jurisActividades = new HashSet();
  	
  	public Categoria(Long idCategoria) {
		this(idCategoria,null,null,null);
	}

	public Categoria() {
  		this(null,null,null,null);
  	}
  	
	public Categoria(Long idCategoria, String codCategoria, String descripcion, TipoImpuesto tipoImpuesto) {
		super();
		this.idCategoria = idCategoria;
		this.codCategoria = codCategoria;
		this.descripcion = descripcion;
		this.tipoImpuesto = tipoImpuesto;
	}

	public String getCodCategoria() {
		return codCategoria;
	}
	
	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Long getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public TipoImpuesto getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}
	
	public Set getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(Set impuestos) {
		this.impuestos = impuestos;
	}

	
	public String toString() {
		return "Id:"+idCategoria+"|Codigo:"+codCategoria+"|Descripcion:"+descripcion;
	}
	
	public boolean equals(Object unaCategoria) {
		boolean result = false;
		if(unaCategoria instanceof Categoria) {
			Categoria aux = (Categoria)unaCategoria;
			if(aux.getIdCategoria().equals(idCategoria)) {
				result = true;
			}
		}
		
		return result;
	}

	public Long getId() {
		return idCategoria;
	}

	public String getLabel() {
		return codCategoria + " - " + descripcion;
	}

	public Set getJurisActividades() {
		return jurisActividades;
	}

	public void setJurisActividades(Set jurisActividades) {
		this.jurisActividades = jurisActividades;
	}
}

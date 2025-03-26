package com.bizitglobal.tarjetafiel.impuestos.negocio;

import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class TipoImpuesto implements Negocio{
	private Long idTipoImpuesto;
	private String descripcion;
	private Set categorias;
	private Set jurisTipoImp;
	private Set exclusiones;
	
	public TipoImpuesto(Long idTipoImpuesto) {
		this(idTipoImpuesto,null,new HashSet(),new HashSet());
	}

	public TipoImpuesto() {
		this(null,null,new HashSet(),new HashSet());
	}
	
	public TipoImpuesto(Long idTipoImpuesto, String descripcion,Set jurisTipoImp, Set categorias) {
		super();
		this.idTipoImpuesto = idTipoImpuesto;
		this.descripcion = descripcion;
		this.categorias = categorias;
		this.jurisTipoImp = jurisTipoImp;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Long getIdTipoImpuesto() {
		return idTipoImpuesto;
	}
	
	public void setIdTipoImpuesto(Long idTipoImpuesto) {
		this.idTipoImpuesto = idTipoImpuesto;
	}

	public Set getJurisTipoImp() {
		return jurisTipoImp;
	}

	public void setJurisTipoImp(Set jurisTipoImp) {
		this.jurisTipoImp = jurisTipoImp;
	}

	public Set getCategorias() {
		return categorias;
	}

	public void setCategorias(Set categorias) {
		this.categorias = categorias;
	}

	public Set getExclusiones() {
		return exclusiones;
	}

	public void setExclusiones(Set exclusiones) {
		this.exclusiones = exclusiones;
	}

	public String toString() {
		return "Id: "+idTipoImpuesto+
				"|Descripcion: "+descripcion;
	}
	
	public boolean equals(Object unTipoImpuesto) {
		boolean result = false;
		if(unTipoImpuesto instanceof TipoImpuesto) {
			TipoImpuesto aux = (TipoImpuesto)unTipoImpuesto;
			if(aux.getIdTipoImpuesto().equals(idTipoImpuesto)) {
				result = true;
			}
		}
		
		return result;
	}

	public Long getId() {
		return idTipoImpuesto;
	}

	public String getLabel() {
		return descripcion;
	}
}

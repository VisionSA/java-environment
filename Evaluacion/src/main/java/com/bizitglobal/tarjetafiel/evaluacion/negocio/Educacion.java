package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.EstadoCivil;

public class Educacion implements Negocio {
	private Long idEducacion = new Long(0);
	private String descripcion = "";
/*@I4991*/
/*@F4991*/
	public Educacion() {
		this(null,null,null);
	}
	
	public Educacion(Long idEducacion) {
		this(idEducacion,null,null);
	}

	public Educacion(Long idEducacion, String descripcion, Set evaIndividuos) {
		super();
		this.idEducacion = idEducacion;
		this.descripcion = descripcion;
		/*@I4991*/
		/*@F4991*/
	}

	public Long getId() {
		return idEducacion;
	}
	public Long getIdEducacion() {
		return idEducacion;
	}
	public void setIdEducacion(Long idEducacion) {
		this.idEducacion = idEducacion;
	}
	public String getLabel() {
		return descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
/*@I4991*/
/*@F4991*/
	
	public String toString() {
		
		return  "Educacion: " +
				"id: " + idEducacion +
				", descripcion: " + descripcion ;
				//", individuo: " + evaIndividuos == null ? "" : evaIndividuos.toString();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Educacion) {
			Educacion aux = (Educacion)obj;
			if(aux.getIdEducacion().equals(idEducacion)) {
				return true;
			}
		}
		return false;
	}
}


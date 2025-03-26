package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class TipoAsiento implements Negocio {
	private Long idTipoAsiento = new Long(0);
	private String descripcion = "";

	public TipoAsiento() {
		this(new Long(0),null);
	}
	
	public TipoAsiento(Long idTipoAsiento) {
		this(idTipoAsiento,null);
	}
	
	public TipoAsiento(Long idTipoAsiento, String descripcion) {
		super();
		this.idTipoAsiento = idTipoAsiento;
		this.descripcion = descripcion;
	}
	public Long getId() {
		return idTipoAsiento;
	}
	public Long getIdTipoAsiento() {
		return idTipoAsiento;
	}
	public void setIdTipoAsiento(Long idTipoAsiento) {
		this.idTipoAsiento = idTipoAsiento;
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

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof TipoAsiento) {
			TipoAsiento aux = (TipoAsiento)obj;
			if(aux.getId().equals(idTipoAsiento)) {
				result = true;
			}
		}
		return result;
	}
}
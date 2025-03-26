package com.bizitglobal.tarjetafiel.cobranzas.negocio;

public  class Etapa  {
	
	public Integer idEtapa;
	public String descripcion;	
	public String habilitado;
	public Integer orden;
	

	public Integer getIdEtapa() {
		return idEtapa;
	}

	public void setIdEtapa(Integer idEtapa) {
		this.idEtapa = idEtapa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}


}

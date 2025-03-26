package com.bizitglobal.tarjetafiel.planificadoremail.negocio;

/***** @Id:6958 ******/
public class TipoParamTemp {

	private Long idTipo;
	private String descripcion;
	public final static int TIPO_CADENA = 1;
	public final static int TIPO_IMAGEN = 2;


	public TipoParamTemp() {
	}


	public Long getIdTipo() {
		return idTipo;
	}


	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

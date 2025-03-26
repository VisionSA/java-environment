package com.bizitglobal.tarjetafiel.proveedores.negocio;

public class Grupo {
	private Long idGrupo;
	private String descripcion;
	
	public Grupo() {
		this(null,null);
	}
	
	public Grupo(Long idGrupo, String descripcion) {
		super();
		this.idGrupo = idGrupo;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Long getIdGrupo() {
		return idGrupo;
	}
	
	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	public String toString() {
		return "Id:"+idGrupo+"|Descripcion:"+descripcion;
	}
	
	public boolean equals(Object unGrupo) {
		boolean result = false;
		if(unGrupo instanceof Grupo) {
			Grupo aux = (Grupo)unGrupo;
			if(aux.getIdGrupo().equals(idGrupo)) {
				result = true;
			}
		}
		
		return result;
	}

}

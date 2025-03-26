package com.bizitglobal.tarjetafiel.fondos.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class Lugar  implements Negocio {
	
	public static final Long CAJA = 0L;
	public static final Long TESORERIA = 1L;
	
	private Long idLugar;
	private String descripcion = "";
	private Character tipo;
	private Long codigo;		
	
//	T_VIS_FON_LUGAR
//	C_ID_LUGAR              NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_DESCRIPCION                           NVARCHAR2(50)                                                                                                                                                                                 
//	C_TIPO                                  CHAR(1)         

	public Lugar() {
	}

	public Lugar(Long id) {
		idLugar = id;
	}

	public Long getId() {
		return idLugar;
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

	public Long getIdLugar() {
		return idLugar;
	}

	public void setIdLugar(Long idLugar) {
		this.idLugar = idLugar;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Lugar) {
			Lugar aux = (Lugar)obj;
			if(aux.getId().equals(idLugar)) {
				result = true;
			}
		}
		return result;
	}
	
	public String toString() {
		return "Id:"+idLugar+"|Descripcion:"+descripcion;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}


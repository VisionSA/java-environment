package com.bizitglobal.tarjetafiel.fondos.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class EstadoInterbank  implements Negocio {
	private Long idEstadoInterbank;
	private String descripcion;
	
//	T_VIS_FON_ESTADO_INTERBANK 
//	C_ID_ESTADO_INTERBANK          NOT NULL NUMBER(2)                                                                                                                                                                                     
//	C_DESCRIPCION                           NVARCHAR2(50)                                                                                                                                                                                 

	public EstadoInterbank() {
	}

	public EstadoInterbank(Long id) {
		idEstadoInterbank = id;
	}

	public Long getId() {
		return idEstadoInterbank;
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

	public Long getIdEstadoInterbank() {
		return idEstadoInterbank;
	}

	public void setIdEstadoInterbank(Long idEstadoInterbank) {
		this.idEstadoInterbank = idEstadoInterbank;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof EstadoInterbank) {
			EstadoInterbank aux = (EstadoInterbank)obj;
			if(aux.getId().equals(idEstadoInterbank)) {
				result = true;
			}
		}
		return result;
	}
	
	public String toString() {
		return "Id:"+idEstadoInterbank+"|Descripcion:"+descripcion;
	}

}


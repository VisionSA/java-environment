package com.bizitglobal.tarjetafiel.fondos.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class ChequeEstado  implements Negocio {
	private Long idChequeEstado;
	private String descripcion;
	private Character tipo;
	
//	T_VIS_FON_CHEQUES_ESTADOS
//	C_ID_CHEQUE_ESTADO             NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_DESCRIPCION                           NVARCHAR2(30)                                                                                                                                                                                 
//	C_TIPO                                  CHAR(1)                                                                                                         

	public ChequeEstado() {
	}

	public ChequeEstado(Long idChequeEstado, String descripcion, Character tipo) {
		super();
		this.descripcion = descripcion;
		this.idChequeEstado = idChequeEstado;
		this.tipo = tipo;
	}

	public ChequeEstado(Long id) {
		idChequeEstado = id;
	}

	public Long getId() {
		return idChequeEstado;
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
	
	public Long getIdChequeEstado() {
		return idChequeEstado;
	}

	public void setIdChequeEstado(Long idChequeEstado) {
		this.idChequeEstado = idChequeEstado;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof ChequeEstado) {
			ChequeEstado aux = (ChequeEstado)obj;
			if(aux.getId().equals(idChequeEstado)) {
				result = true;
			}
		}
		return result;
	}
	
	public String toString() {
		return "Id:"+idChequeEstado+"|Descripcion:"+descripcion;
	}

}


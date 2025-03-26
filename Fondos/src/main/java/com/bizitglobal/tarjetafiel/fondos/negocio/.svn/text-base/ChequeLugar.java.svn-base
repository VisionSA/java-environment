package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class ChequeLugar  implements Negocio {
	private Long idChequeLugar;
	private Date timestamp;
	private Lugar lugar;
	private Cheque cheque;
	
//	T_VIS_FON_CHEQUES_LUGAR
//	C_ID_CHEQUE_LUGAR              NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_TIMESTAMP                    NOT NULL DATE                                                                                                                                                                                          
//	C_ID_LUGAR                     NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_ID_CHEQUE                    NOT NULL NUMBER(10)        

	public ChequeLugar() {
	}

	public ChequeLugar(Long id) {
		idChequeLugar = id;
	}

	public Long getId() {
		return idChequeLugar;
	}

	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getIdChequeLugar() {
		return idChequeLugar;
	}

	public void setIdChequeLugar(Long idChequeLugar) {
		this.idChequeLugar = idChequeLugar;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public Cheque getCheque() {
		return cheque;
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof ChequeLugar) {
			ChequeLugar aux = (ChequeLugar)obj;
			if(aux.getId().equals(idChequeLugar)) {
				result = true;
			}
		}
		return result;
	}
	
	public String toString() {
		return "Id:"+idChequeLugar+"|Descripcion:";
	}


}


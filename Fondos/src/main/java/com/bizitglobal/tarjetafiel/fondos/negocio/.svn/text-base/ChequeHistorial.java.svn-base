package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class ChequeHistorial  implements Negocio,Cloneable {
	private Long idChequeHistorial;
	private Cheque cheque;
	private ChequeEstado chequeEstado;
	private MovimientoMP movimientoMP;
	private AsientoItem asientoItem;
	private Date timestamp;
	private Character conciliado = 'N';

	private static Logger logger = Logger.getLogger(ChequeHistorial.class);
	
//	T_VIS_FON_CHEQUES_HISTORIAL
//	C_ID_CHEQUE_HISTORIAL          NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_ID_CHEQUE                    NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_ID_CHEQUE_ESTADO             NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_ID_MOVIM_MP                           NUMBER(10)                                                                                                                                                                                    
//	C_ID_ASIENTO_ITEM                       NUMBER(10)                                                                                                                                                                                    
//	C_TIMESTAMP                    NOT NULL DATE                                                                                                                                                                                          


	public ChequeHistorial() {
	}

	public ChequeHistorial(Long id) {
		idChequeHistorial = id;
	}

	public ChequeHistorial(ChequeHistorial chequeHistorial) {
		super();
		this.asientoItem = chequeHistorial.getAsientoItem();
		this.cheque = chequeHistorial.getCheque();
		this.chequeEstado = chequeHistorial.getChequeEstado();
		this.movimientoMP = chequeHistorial.getMovimientoMP();
		this.timestamp = chequeHistorial.getTimestamp();
	}

	public Long getId() {
		return idChequeHistorial;
	}

	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getIdChequeHistorial() {
		return idChequeHistorial;
	}

	public void setIdChequeHistorial(Long idChequeHistorial) {
		this.idChequeHistorial = idChequeHistorial;
	}

	public Cheque getCheque() {
		return cheque;
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	public ChequeEstado getChequeEstado() {
		return chequeEstado;
	}

	public void setChequeEstado(ChequeEstado chequeEstado) {
		this.chequeEstado = chequeEstado;
	}

	public MovimientoMP getMovimientoMP() {
		return movimientoMP;
	}

	public void setMovimientoMP(MovimientoMP movimientoMP) {
		this.movimientoMP = movimientoMP;
	}

	public AsientoItem getAsientoItem() {
		return asientoItem;
	}

	public void setAsientoItem(AsientoItem asientoItem) {
		this.asientoItem = asientoItem;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getTimestampFormat() {
		Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(timestamp);
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof ChequeHistorial) {
			ChequeHistorial aux = (ChequeHistorial)obj;
			if(aux.getId().equals(idChequeHistorial)) {
				result = true;
			}
		}
		return result;
	}
	
	public String toString() {
		return "Id:"+idChequeHistorial+"|Descripcion:";
	}

	@Override
	public ChequeHistorial clone(){
		try {
			return (ChequeHistorial)super.clone();
		} catch (CloneNotSupportedException e) {
			logger.error(e,e);
			return null;
		}
	}

	public Character getConciliado() {
		return conciliado;
	}

	public void setConciliado(Character conciliado) {
		this.conciliado = conciliado;
	}
	
	
}


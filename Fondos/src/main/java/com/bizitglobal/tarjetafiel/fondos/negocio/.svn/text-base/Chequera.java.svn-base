package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public class Chequera  implements Negocio {
	private Long idChequera;
	private BancoPropio bancoPropio ;
	private Long nroInicio;
	private Long nroFin;
	private Long proximoAUsar;
	private Operador operador;
	private Date ultimaModificacion;
	private Character habilitado = new Character('N');
	
//	T_VIS_FON_CHEQUERAS
//	C_ID_CHEQUERA                  NOT NULL NUMBER                                                                                                                                                                                        
//	C_ID_BANCO_PROPIO                       NUMBER(10)                                                                                                                                                                                    
//	C_NUMERO_INICIO                         VARCHAR2(20)                                                                                                                                                                                    
//	C_NUMERO_FIN                            VARCHAR2(20)                                                                                                                                                                                    
//	C_PROXIMO_A_USAR                        VARCHAR2(20)                                                                                                                                                                                    
//	C_ID_OPERADOR                           NUMBER(10)                                                                                                                                                                                    
//	C_ULTIMA_MODIF                          TIMESTAMP(0)                                                                                                                                                                                  
//	C_HABILITADO                            CHAR(1)     
	
	public Chequera() {
	}

	public Chequera(Long id) {
		idChequera = id;
	}

	public Long getId() {
		return idChequera;
	}

	public String getLabel() {
		return null;
	}

	public Long getIdChequera() {
		return idChequera;
	}

	public void setIdChequera(Long idChequera) {
		this.idChequera = idChequera;
	}

	public BancoPropio getBancoPropio() {
		return bancoPropio;
	}

	public void setBancoPropio(BancoPropio bancoPropio) {
		this.bancoPropio = bancoPropio;
	}


	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public Character getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Character habilitado) {
		this.habilitado = habilitado;
	}


	public Long getNroFin() {
		return nroFin;
	}

	public void setNroFin(Long nroFin) {
		this.nroFin = nroFin;
	}

	public Long getNroInicio() {
		return nroInicio;
	}

	public void setNroInicio(Long nroInicio) {
		this.nroInicio = nroInicio;
	}

	public Long getProximoAUsar() {
		return proximoAUsar;
	}

	public void setProximoAUsar(Long proximoAUsar) {
		this.proximoAUsar = proximoAUsar;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Chequera) {
			Chequera aux = (Chequera)obj;
			if(aux.getId().equals(idChequera)) {
				result = true;
			}
		}
		return result;
	}
	
//	public String toString() {
//		return "Id:"+idCaja+"|Descripcion:"+descripcion;
//	}

}


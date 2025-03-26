package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.math.BigDecimal;
import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public class CajaApertura  implements Negocio {
	private Long idCajaApertura;
	private Double saldoInicial;
	private Date fechaApertura;
	private Character signoApertura;
	private Double saldoFinal;
	private Character signoFinal;
	private Date fechaCierre;
	private Character estado;
	private Caja caja;
	private Operador operador;
	
//	T_VIS_FON_CAJAS_APERTURAS
//	C_ID_CAJAAPERTURA              NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_SALDO_INICIAL                         NUMBER(10,2)                                                                                                                                                                                  
//	C_FECHA_APERTURA                        TIMESTAMP(0)                                                                                                                                                                                  
//	C_SIGNO_APERTURA                        CHAR(1)                                                                                                                                                                                       
//	C_SALDO_FINAL                           NUMBER(10,2)                                                                                                                                                                                  
//	C_SIGNO_FINAL                           CHAR(1)                                                                                                                                                                                       
//	C_FECHA_CIERRE                          TIMESTAMP(0)                                                                                                                                                                                  
//	C_ESTADO                                CHAR(1)                                                                                                                                                                                       
//	C_ID_CAJA                               NUMBER(10)                                                                                                                                                                                    
//	C_ID_OPERADOR                           NUMBER(10)       

	public CajaApertura() {
	}

	public CajaApertura(Long id) {
		idCajaApertura = id;
	}

	public Long getId() {
		return idCajaApertura;
	}

	public String getLabel() {
		return null;
	}	
	
	public Long getIdCajaApertura() {
		return idCajaApertura;
	}

	public void setIdCajaApertura(Long idCajaApertura) {
		this.idCajaApertura = idCajaApertura;
	}

	public Double getSaldoInicial() {
		return saldoInicial == null || Double.isNaN(saldoInicial) ? 0 : saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Character getSignoApertura() {
		return signoApertura;
	}

	public void setSignoApertura(Character signoApertura) {
		this.signoApertura = signoApertura;
	}

	public Double getSaldoFinal() {
		return saldoFinal == null || Double.isNaN(saldoFinal)  ? 0 : saldoFinal;
	}

	public void setSaldoFinal(Double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public Character getSignoFinal() {
		return signoFinal;
	}

	public void setSignoFinal(Character signoFinal) {
		this.signoFinal = signoFinal;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Character getEstado() {
		return estado;
	}

	public void setEstado(Character estado) {
		this.estado = estado;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof CajaApertura) {
			CajaApertura aux = (CajaApertura)obj;
			if(aux.getId().equals(idCajaApertura)) {
				result = true;
			}
		}
		return result;
	}
	
//	public String toString() {
//		return "Id:"+idCaja+"|Descripcion:"+descripcion;
//	}

}


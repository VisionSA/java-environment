package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Mapping MovXCuentaXConcepto.hbm.xml
 * @author sporta
 *
 */
public class MovXCuentaXConceptoId implements Serializable{
	
	private Long idConcepto;
	
	private Long idPlanCuenta;
	
	private Long idCaja;
	
	private Long idApertura;	
	
	
	public MovXCuentaXConceptoId() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdConcepto() {
		return idConcepto;
	}


	public void setIdConcepto(Long idConcepto) {
		this.idConcepto = idConcepto;
	}


	public Long getIdPlanCuenta() {
		return idPlanCuenta;
	}


	public void setIdPlanCuenta(Long idPlanCuenta) {
		this.idPlanCuenta = idPlanCuenta;
	}

	public Long getIdApertura() {
		return idApertura;
	}
	
	public void setIdApertura(Long idApertura) {
		this.idApertura = idApertura;
	}
	
	public Long getIdCaja() {
		return idCaja;
	}
	
	public void setIdCaja(Long idCaja) {
		this.idCaja = idCaja;
	}
	
}

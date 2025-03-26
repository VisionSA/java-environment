package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Mapping MovXCuentaXConcepto.hbm.xml
 * @author sporta
 *
 */
public class MovXCuentaXConcepto implements Serializable{
	
	private MovXCuentaXConceptoId id;		
	
	private String concepto;
	
	private String cuenta;
	
	private Double total;
	
	
	public MovXCuentaXConcepto() {
		// TODO Auto-generated constructor stub
	}


	
	public String getConcepto() {
		return concepto;
	}


	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}


	public String getCuenta() {
		return cuenta;
	}


	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}
	
	public MovXCuentaXConceptoId getId() {
		return id;
	}
	
	public void setId(MovXCuentaXConceptoId id) {
		this.id = id;
	}
}

package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import java.sql.Timestamp;
import java.util.Date;


public class AsientoComercio extends AsientoBase implements Importable  {
	
    private Long ctaCteComercio;
	private Long comercio;
	
	public final static String TRA_CTACTE_COMERCIO = "t_vis_tra_ctacte_comercios";
	public final static String NRO_CTA_CTE = "c_id_ctacte_comercio";
	public final static String COMERCIO = "idComercio";
	public final static String OPERADOR = "cta.c_id_operador";
	public AsientoComercio() {
		super();
		seleccionado = false;
	}
	
		
	
	
	
	//////implementacion interfaz
	
	public Long getNumeroAsiento() {
		return ctaCteComercio;
	}
	
	public String getConcepto() {
		return concepto;
	}

	public Date getFechaCarga() {
		return fechaContab;
	}
	
	public void setNumeroAsiento(Long nroAsiento) {
		// TODO Auto-generated method stub
		
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
	public void setFechaCarga(Date fechaContab) {
		this.fechaContab = fechaContab;
	}


	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
		
	}
	
	public boolean getSeleccionado() {
		return seleccionado;
	}
	
	public String getOperador() {
		return operador;
	}
  
	public Date getFechaContab() {
		return fechaContab;
	}

	public Long getIdNroComprobante() {
		return ctaCteComercio;
	}
	
	public Long getIdObjetoOrigen() {
		return this.comercio;
	}





	public Long getComercio() {
		return comercio;
	}





	public void setComercio(Long comercio) {
		this.comercio = comercio;
	}





	public Long getCtaCteComercio() {
		return ctaCteComercio;
	}





	public void setCtaCteComercio(Long ctaCteComercio) {
		this.ctaCteComercio = ctaCteComercio;
	}

		
}

package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import java.sql.Timestamp;
import java.util.Date;


public class AsientoCliente extends AsientoBase implements Importable  {
	
    private Long ctaCteCliente;
	private Long cliente;
	
	public final static String TRA_CTACTE_CLIENTE = "t_vis_tra_ctacte_clientes";
	public final static String NRO_CTA_CTE = "c_id_ctacte_cliente";
	//public final static String CLIENTE = "cta.c_id_cliente";
	public final static String CLIENTE = "idCliente";	
	public final static String OPERADOR = "cta.c_id_operador";
	
	
	public AsientoCliente() {
		super();
		seleccionado = false;
	}
	
		
	
	
	
	//////implemntacion interfaz
	
	public Long getNumeroAsiento() {
		return ctaCteCliente;
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
		return ctaCteCliente;
	}
	
	public Long getIdObjetoOrigen() {
		return this.cliente;
	}





	public Long getCliente() {
		return cliente;
	}





	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}





	public Long getCtaCteCliente() {
		return ctaCteCliente;
	}





	public void setCtaCteCliente(Long ctaCteCliente) {
		this.ctaCteCliente = ctaCteCliente;
	}

	

	
	
}

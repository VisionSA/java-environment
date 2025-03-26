package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import java.sql.Timestamp;
import java.util.Date;


public class AsientoProveedor extends AsientoBase implements Importable  {
	
    private Long comprobante;
	private Long proveedor;
	
	public final static String PROV_COMPROBANTES = "t_vis_prov_comprobantes";
	public final static String NRO_COMPROBANTE = "c_id_comprobante";
	public final static String PROVEEDOR = "c_id_proveedor";

	public AsientoProveedor() {
		super();
		seleccionado = false;
	}
	
	public Long getProveedor() {
		return proveedor;
	}

	public void setProveedor(Long proveedor) {
		this.proveedor = proveedor;
	}
    
	
	public Long getComprobante() {
		return comprobante;
	}

	public void setComprobante(Long comprobante) {
		this.comprobante = comprobante;
	}

	
	
	
	
	//////implemntacion interfaz
	
	public Long getNumeroAsiento() {
		return comprobante;
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
		return comprobante;
	}
	
	public Long getIdObjetoOrigen() {
		return this.proveedor;
	}

	

	
	
}

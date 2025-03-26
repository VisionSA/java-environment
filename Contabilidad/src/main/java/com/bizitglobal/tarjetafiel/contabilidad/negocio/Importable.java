package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import java.util.Date;


public interface Importable  {
	
	public Long getNumeroAsiento();
	public String getConcepto();
	public Date getFechaCarga();
	public void setNumeroAsiento(Long nroAsiento);
	public void setConcepto(String concepto);
	public void setFechaCarga(Date fechaCarga);
	public boolean getSeleccionado();
	public void setSeleccionado(boolean seleccionado);
	public String getOperador();
	public Date getFechaContab();
	public Long getIdNroComprobante();
	public Long getIdObjetoOrigen();
}

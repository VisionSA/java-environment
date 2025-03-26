package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.ArrayList;
import java.util.List;

import com.bizitglobal.tarjetafiel.transacciones.negocio.LoteComercio;

public class ConciliaCupon {
	
	
	
 private	int numeroDeCliente;
 private int movAceptados ;
 private int movRechazados;
	
 private String fechaArch ;	
 private String rtaEnergiaSJ ;
 private String rtaDirecTV ;
 private Long importe ;
 
 private boolean energiaSJ ;
 private	boolean direcTV ;
 private	boolean rtaNormal ;
 
 private LoteComercio lote;
 
 private List loteComercioItemsAprobados = null;
	private List loteComercioItemsRechazados = null;
	
	
	public  ConciliaCupon() {
	loteComercioItemsAprobados = new ArrayList();
	loteComercioItemsRechazados = new ArrayList();
	 fechaArch = "" ;	
	  rtaEnergiaSJ = "" ;
	  rtaDirecTV = "" ;
	  importe = 0L;
	
	}
 
public int getNumeroDeCliente() {
	return numeroDeCliente;
}
public void setNumeroDeCliente(int numeroDeCliente) {
	this.numeroDeCliente = numeroDeCliente;
}
public int getMovAceptados() {
	return movAceptados;
}
public void setMovAceptados(int movAceptados) {
	this.movAceptados = movAceptados;
}
public int getMovRechazados() {
	return movRechazados;
}
public void setMovRechazados(int movRechazados) {
	this.movRechazados = movRechazados;
}
public String getFechaArch() {
	return fechaArch;
}
public void setFechaArch(String fechaArch) {
	this.fechaArch = fechaArch;
}
public String getRtaEnergiaSJ() {
	return rtaEnergiaSJ;
}
public void setRtaEnergiaSJ(String rtaEnergiaSJ) {
	this.rtaEnergiaSJ = rtaEnergiaSJ;
}
public String getRtaDirecTV() {
	return rtaDirecTV;
}
public void setRtaDirecTV(String rtaDirecTV) {
	this.rtaDirecTV = rtaDirecTV;
}
public boolean isEnergiaSJ() {
	return energiaSJ;
}
public void setEnergiaSJ(boolean energiaSJ) {
	this.energiaSJ = energiaSJ;
}
public boolean isDirecTV() {
	return direcTV;
}
public void setDirecTV(boolean direcTV) {
	this.direcTV = direcTV;
}
public boolean isRtaNormal() {
	return rtaNormal;
}
public void setRtaNormal(boolean rtaNormal) {
	this.rtaNormal = rtaNormal;
}
public LoteComercio getLote() {
	return lote;
}
public void setLote(LoteComercio lote) {
	this.lote = lote;
}
public List getLoteComercioItemsAprobados() {
	return loteComercioItemsAprobados;
}
public void setLoteComercioItemsAprobados(List loteComercioItemsAprobados) {
	this.loteComercioItemsAprobados = loteComercioItemsAprobados;
}
public List getLoteComercioItemsRechazados() {
	return loteComercioItemsRechazados;
}
public void setLoteComercioItemsRechazados(List loteComercioItemsRechazados) {
	this.loteComercioItemsRechazados = loteComercioItemsRechazados;
}

public Long getImporte() {
	return importe;
}

public void setImporte(Long importe) {
	this.importe = importe;
}


 
 

}

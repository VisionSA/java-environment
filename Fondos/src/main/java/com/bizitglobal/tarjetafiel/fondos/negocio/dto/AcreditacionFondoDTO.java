package com.bizitglobal.tarjetafiel.fondos.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;

public class AcreditacionFondoDTO {

	private Long idBanco;
	private String banco;
	private Date fechaCargaMaxima;
	private String fechaCargaMaximaCadena;
	
	
	public AcreditacionFondoDTO() {
		super();
	}


	public Long getIdBanco() {
		return idBanco;
	}


	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}


	public String getBanco() {
		return banco;
	}


	public void setBanco(String banco) {
		this.banco = banco;
	}


	public Date getFechaCargaMaxima() {
		return fechaCargaMaxima;
	}


	public void setFechaCargaMaxima(Date fechaCargaMaxima) {
		this.fechaCargaMaxima = fechaCargaMaxima;
	}


	public String getFechaCargaMaximaCadena() {
		return fechaCargaMaximaCadena;
	}


	public void setFechaCargaMaximaCadena(String fechaCargaMaximaCadena) {
		this.fechaCargaMaximaCadena = fechaCargaMaximaCadena;
	}
	
	
}

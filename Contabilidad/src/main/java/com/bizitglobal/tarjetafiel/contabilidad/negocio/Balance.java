package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class Balance  implements Negocio{
	
	private String nroImputa;
	private String titulo;
	private Double saldoInicial;
	private Double saldoPeriodo;
	private int nivel;// para definir la tabulacion
	private boolean mostrar;
	
	
	public final static String NRO_IMPUTA = "nroImputa";
	public final static String TITULO = "titulo";
	public final static String SALDO_PERIODO = "saldoPeriodo";
	public final static String SALDO_INICIAL = "saldoInicial";

	
  
	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	public Balance() {
		
	}
	
	public Balance(String nroImputa,String titulo,Double saldoInicial,Double saldoPeriodo,int nivel,boolean mostrar) {
		setNroImputa(nroImputa);
		setSaldoInicial(saldoInicial);
		setSaldoPeriodo(saldoPeriodo);
		setTitulo(titulo);
		setNivel(nivel);
		setMostrar(mostrar);
		
		
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	  
	public String getNroImputa() {
		return nroImputa;
	}

	public void setNroImputa(String nroImputa) {
		this.nroImputa = nroImputa;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Double getSaldoPeriodo() {
		return saldoPeriodo;
	}

	public void setSaldoPeriodo(Double saldoPeriodo) {
		this.saldoPeriodo = saldoPeriodo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

}

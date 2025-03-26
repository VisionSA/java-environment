package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class SumasYSaldos  implements Negocio{
	
	private String nroImputa;
	private String titulo;
	private Double debeInicial;
	private Double haberInicial;
	private Double debePeriodo;
	private Double haberPeriodo;
	private int nivel;// para definir la tabulacion
	private boolean mostrar;
	
	
	public final static String NRO_IMPUTA = "nroImputa";
	public final static String TITULO = "titulo";
	public final static String DEBE_INICIAL = "debeInicial";
	public final static String HABER_INICIAL = "haberInicial";
	public final static String DEBE_PERIODO = "debePeriodo";
	public final static String HABER_PERIODO = "haberPeriodo";
	

	
  
	public SumasYSaldos() {
		
	}
	
	/**
	 * @param nroImputa
	 * @param titulo
	 * @param debeInicial
	 * @param haberInicial
	 * @param haberPeriodo
	 * @param debePeriodo
	 * @param nivel
	 */
	public SumasYSaldos(String nroImputa,String titulo,Double debeInicial,Double haberInicial,Double haberPeriodo,Double debePeriodo,int nivel, boolean mostrar) {
		setNroImputa(nroImputa);
		setTitulo(titulo);
		setNivel(nivel);
		setDebeInicial(debeInicial);
		setHaberInicial(haberInicial);
		setDebePeriodo(debePeriodo);
		setHaberPeriodo(haberPeriodo);
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

	public Double getDebeInicial() {
		return debeInicial;
	}

	public void setDebeInicial(Double debeInicial) {
		this.debeInicial = debeInicial;
	}

	public Double getDebePeriodo() {
		return debePeriodo;
	}

	public void setDebePeriodo(Double debePeriodo) {
		this.debePeriodo = debePeriodo;
	}

	public Double getHaberInicial() {
		return haberInicial;
	}

	public void setHaberInicial(Double haberInicial) {
		this.haberInicial = haberInicial;
	}

	public Double getHaberPeriodo() {
		return haberPeriodo;
	}

	public void setHaberPeriodo(Double haberPeriodo) {
		this.haberPeriodo = haberPeriodo;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

}

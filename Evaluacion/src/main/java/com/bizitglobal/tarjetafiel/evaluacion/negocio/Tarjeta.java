package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;

public class Tarjeta implements Negocio {
	
	private Long idTarjeta = new Long(0);
	private String descripcion = "";
	private Banco banco;
	private IndividuoEvaluacion individuoEvaluacion;
	private String nro = "";

	public Tarjeta() {
		this(null,null,null,null,null);
	}
	
	public Tarjeta(Long idTarjeta) {
		this(idTarjeta,null,null,null,null);
	}

	public Tarjeta(Long idTarjeta, String descripcion, Banco banco, IndividuoEvaluacion individuoEvaluacion, String nro) {
		super();
		this.idTarjeta = idTarjeta;
		this.descripcion = descripcion;
		this.banco = banco;
		this.individuoEvaluacion = individuoEvaluacion;
		this.nro = nro;
	}

	public Long getId() {
		return idTarjeta;
	}
	
	public Long getIdTarjeta() {
		return idTarjeta;
	}
	
	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getLabel() {
		return descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public IndividuoEvaluacion getIndividuoEvaluacion() {
		return individuoEvaluacion;
	}

	public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}

	public String getNro() {
		return nro;
	}
	
	public void setNro(String nro) {
		this.nro = nro;
	}
	
	public String toString() {
		
		return "Tarjeta: " + 
				"id: " + idTarjeta + 
				", descripcion: " + descripcion + 
				", bancos: " + banco.toString() + 
				", individuoEvaluacion: " + individuoEvaluacion.toString() +
				", nro: " + nro;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
}


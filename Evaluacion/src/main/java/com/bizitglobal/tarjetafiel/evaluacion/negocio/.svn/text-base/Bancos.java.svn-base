package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;

public class Bancos implements Negocio {
	private Long idBanco = new Long(0);
	private Banco banco;
	private IndividuoEvaluacion individuoEvaluacion;
	private String vinculacion = "";
	private Set evaTarjetas;

	public Bancos() {
		this(null,new Banco(),new IndividuoEvaluacion(),null);
	}
	
	public Bancos(Long idEvaBanco) {
		this(idEvaBanco,new Banco(),new IndividuoEvaluacion(),null);
	}

	public Bancos(Long idBanco, Banco banco, IndividuoEvaluacion individuoEvaluacion, String vinculacion) {
		super();
		this.idBanco = idBanco;
		this.banco = banco;
		this.individuoEvaluacion = individuoEvaluacion;
		this.vinculacion = vinculacion;

	}

	public Long getId() {
		return idBanco;
	}
	
	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public IndividuoEvaluacion getIndividuo() {
		return individuoEvaluacion;
	}

	public void setIndividuo(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}

	public String getVinculacion() {
		return vinculacion;
	}

	public void setVinculacion(String vinculacion) {
		this.vinculacion = vinculacion;
	}

	public String toString() {
		
		return  "Bancos: " +
				"id banco: " + idBanco +
				", individuoEvaluacion: " + individuoEvaluacion.toString() +
				", vinculacion: " + vinculacion;
	}
}


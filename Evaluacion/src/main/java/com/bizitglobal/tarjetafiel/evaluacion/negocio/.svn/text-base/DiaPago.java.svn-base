package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Partido;

public class DiaPago implements Negocio {
	private Long idDiaPago = new Long(0);
	private Integer diaPago = new Integer(0);
	private Partido partido;
	private Set evaIndividuos;
/*@I13*/	private String habilitado_lista;

	public String getHabilitado_lista() {
		return habilitado_lista;
	}

	public void setHabilitado_lista(String habilitado_lista) {
		this.habilitado_lista = habilitado_lista;
/*@F13*/	}

	public DiaPago() {
		this(null,null,null,null);
	}
	
	public DiaPago(Long idDiaPago) {
		this(idDiaPago,null,null,null);
	}

	public DiaPago(Long idDiaPago, Integer diaPago, Partido partido, Set evaIndividuos) {
		super();
		this.idDiaPago = idDiaPago;
		this.diaPago = diaPago;
		this.partido = partido;
		this.evaIndividuos = evaIndividuos;
	}
		

	public DiaPago(Long idDiaPago, Integer diaPago) {
		super();
		this.idDiaPago = idDiaPago;
		this.diaPago = diaPago;
	}

	public Long getId() {
		return idDiaPago;
	}
	public Long getIdDiaPago() {
		return idDiaPago;
	}
	public void setIdDiaPago(Long idDiaPago) {
		this.idDiaPago = idDiaPago;
	}

	public String getLabel() {
		return diaPago.toString();
	}
	public Integer getDiaPago() {
		return diaPago;
	}
	public void setDiaPago(Integer diaPago) {
		this.diaPago = diaPago;
	}

	public Partido getPartido() {
		return partido;
	}
	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Set getEvaIndividuos() {
		return evaIndividuos;
	}

	public void setEvaIndividuos(Set evaIndividuos) {
		this.evaIndividuos = evaIndividuos;
	}

	public String toString() {
		
		return "Generar el metodo toString() de la clase DiaPago cuando sea necesario.";
	}
}


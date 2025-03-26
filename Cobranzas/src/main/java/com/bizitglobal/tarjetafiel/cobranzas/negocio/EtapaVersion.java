package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.Iterator;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.util.SimpleList;

public  class EtapaVersion implements Comparable {
	
	public Integer idEtapaVersion;
	public Etapa etapa;	
	public String nombreEtapa;
	public String descripcion;
	public Integer dias;
	public PlanVersion planVersion;
	public Set<AccionVersion> accionesVersion;
	
	public SimpleList listaAccionesOrdenada;
	
	public EtapaVersion() {

	}

	public EtapaVersion(String descripcion, Integer dias, Etapa etapa, String nombreEtapa,Set<AccionVersion> acciones) {
		super();
		this.descripcion = descripcion;
		this.dias = dias;
		this.etapa = etapa;
		this.nombreEtapa = nombreEtapa;
		this.accionesVersion = acciones;
		disponerListaAccionesOrdenada();
	}
	
	
	public EtapaVersion(Integer idEtapaVersion, String descripcion, Integer dias, Etapa etapa, String nombreEtapa) {
		this.descripcion = descripcion;
		this.dias = dias;
		this.etapa = etapa;
		this.idEtapaVersion = idEtapaVersion;
		this.nombreEtapa = nombreEtapa;
	}

	/**
	 * Ordena las etapas en funcion del Objeto etapa básico.
	 * */
	public void disponerListaAccionesOrdenada() {
		listaAccionesOrdenada = new SimpleList();
		Iterator<AccionVersion> iterAccio = accionesVersion.iterator();
		while(iterAccio.hasNext()) {
		     listaAccionesOrdenada.addInOrder(iterAccio.next());
		}
	}

	public Integer getIdEtapaVersion() {
		return idEtapaVersion;
	}

	public void setIdEtapaVersion(Integer idEtapaVersion) {
		this.idEtapaVersion = idEtapaVersion;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public String getNombreEtapa() {
		return nombreEtapa;
	}

	public void setNombreEtapa(String nombreEtapa) {
		this.nombreEtapa = nombreEtapa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public PlanVersion getPlanVersion() {
		return planVersion;
	}

	public void setPlanVersion(PlanVersion planVersion) {
		this.planVersion = planVersion;
	}
	
	

	public Set<AccionVersion> getAccionesVersion() {
		return accionesVersion;
	}

	public void setAccionesVersion(Set<AccionVersion> accionesVersion) {
		this.accionesVersion = accionesVersion;
	}

	public SimpleList getListaAccionesOrdenada() {
		return listaAccionesOrdenada;
	}

	public void setListaAccionesOrdenada(SimpleList listaAccionesOrdenada) {
		this.listaAccionesOrdenada = listaAccionesOrdenada;
	}

	public int compareTo(Object o) {
		if (((EtapaVersion)o).getEtapa().getIdEtapa()>this.getEtapa().getIdEtapa()) return -1;
		if (((EtapaVersion)o).getEtapa().getIdEtapa()<this.getEtapa().getIdEtapa()) return 1;
		return 0;
	}
}

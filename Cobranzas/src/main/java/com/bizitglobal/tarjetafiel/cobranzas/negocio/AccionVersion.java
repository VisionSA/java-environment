package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCabecera;

public class AccionVersion implements Comparable  {
	
	public Integer idAccionVersion;
	public Accion accion;	
	public Integer dias;
	public EtapaVersion etapaVersion;
	public ConceptoCabecera conceptoCabecera;
	
	public AccionVersion() {

	}
	
		

	public AccionVersion(Integer idAccionVersion, Accion accion, Integer dias, ConceptoCabecera conceptoCabecera) {
		this.accion = accion;
		this.conceptoCabecera = conceptoCabecera;
		this.dias = dias;
		this.idAccionVersion = idAccionVersion;
	}



	public AccionVersion(Accion accion, Integer dias, EtapaVersion etapaVersion) {
		super();
		this.accion = accion;
		this.dias = dias;
		this.etapaVersion = etapaVersion;
	}

	public Integer getIdAccionVersion() {
		return idAccionVersion;
	}

	public void setIdAccionVersion(Integer idAccionVersion) {
		this.idAccionVersion = idAccionVersion;
	}

	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public EtapaVersion getEtapaVersion() {
		return etapaVersion;
	}

	public void setEtapaVersion(EtapaVersion etapaVersion) {
		this.etapaVersion = etapaVersion;
	}


	public ConceptoCabecera getConceptoCabecera() {
		return conceptoCabecera;
	}

	public void setConceptoCabecera(ConceptoCabecera conceptoCabecera) {
		this.conceptoCabecera = conceptoCabecera;
	}

	public int compareTo(Object o) {
		if (((AccionVersion)o).getDias()>this.getDias()) return -1;
		if (((AccionVersion)o).getDias()<this.getDias()) return 1;
		return 0;
	}

}

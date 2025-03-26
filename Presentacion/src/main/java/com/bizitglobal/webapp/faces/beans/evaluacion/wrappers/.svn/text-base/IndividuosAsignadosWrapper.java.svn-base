package com.bizitglobal.webapp.faces.beans.evaluacion.wrappers;

import org.apache.log4j.Logger;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;


public class IndividuosAsignadosWrapper {
	private static final Logger log = Logger.getLogger(IndividuosAsignadosWrapper.class);

	IndividuoEvaluacion ind;
	private String tipoIndividuo;


	public IndividuosAsignadosWrapper(IndividuoEvaluacion indi, String tipoIndi) {
		this.ind = indi;
		this.tipoIndividuo = tipoIndi;
	}


	public String getTipoIndividuo() {
		return tipoIndividuo;
	}


	public void setTipoIndividuo(String tipoIndividuo) {
		this.tipoIndividuo = tipoIndividuo;
	}


	public String getApellido() {
		return ind.getApellido();
	}


	public String getApellidoMaterno() {
		return ind.getApellidoMaterno();
	}


	public String getNombres() {
		return ind.getNombres();
	}


	public Long getIdIndividuo() {
		return ind.getIdIndividuo();
	}

}

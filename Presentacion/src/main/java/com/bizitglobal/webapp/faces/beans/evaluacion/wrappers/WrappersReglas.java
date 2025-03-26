package com.bizitglobal.webapp.faces.beans.evaluacion.wrappers;

import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.EsquemaIndividuo;


public class WrappersReglas {

	private EsquemaIndividuo esquemaIndividuo;


	public WrappersReglas(EsquemaIndividuo esquemaIndividuo) {
		this.esquemaIndividuo = esquemaIndividuo;
	}


	public WrappersReglas() {
		this(null);
	}


	public boolean getValorBooleano(String cadena) {
		if (cadena.compareTo("S") == 0)
			return true;
		return false;
	}


	public boolean getSeleccionado() {
		return Convertidores.getBoolean(esquemaIndividuo.getResultado());
	}


	public void setSeleccionado(boolean seleccionado) {
		esquemaIndividuo.setResultado(Convertidores.getSorN(seleccionado));
	}


	public EsquemaIndividuo getEsquemaIndividuo() {
		return esquemaIndividuo;
	}


	public void setEsquemaIndividuo(EsquemaIndividuo esquemaIndividuo) {
		this.esquemaIndividuo = esquemaIndividuo;
	}


	public boolean getPonderacion() {
		boolean result = false;

		if (esquemaIndividuo.getEsquemaRegla().getPonderacion().equals(new Integer(1)) &&
				!getSeleccionado())
			result = true;

		return result;
	}
}

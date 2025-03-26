package com.bizitglobal.webapp.faces.util;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.proveedores.negocio.AsientoContable;


public class AsientoCont {
	private AsientoContable asiento = new AsientoContable();
	private String titulo;
	private String leyenda;


	public AsientoCont() {
		asiento = new AsientoContable();
		titulo = "SIN TITULO";
	}


	public AsientoCont(PlanCuentaDos planCuenta) {
		this.asiento.setNroImputa(planCuenta.getIdPlanCuenta());
		this.titulo = planCuenta.getTitulo();
	}


	public AsientoContable getAsiento() {
		return asiento;
	}


	public void setAsiento(AsientoContable asiento) {
		this.asiento = asiento;
	}


	public String getLeyenda() {
		return asiento.getLeyenda();
	}


	public void setLeyenda(String leyenda) {
		if (!Validador.esNuloVacio(leyenda)) {
			asiento.setLeyenda(leyenda);
		}
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof AsientoCont) {
			AsientoCont aux = (AsientoCont) obj;
			if (aux.getAsiento().getNroImputa().equals(asiento.getNroImputa())) {
				result = true;
			}
		}
		return result;
	}

}
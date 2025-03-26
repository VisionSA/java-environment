package com.bizitglobal.webapp.faces.util;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;


public class PlanCuentaSeleccionable {
	private PlanCuentaDos planCuenta;
	private boolean seleccionado;


	public PlanCuentaSeleccionable() {
		this(null, false);
	}


	public PlanCuentaSeleccionable(PlanCuentaDos planCuenta, boolean seleccionado) {
		super();
		this.planCuenta = planCuenta;
		this.seleccionado = seleccionado;
	}


	public PlanCuentaDos getPlanCuenta() {
		return planCuenta;
	}


	public void setPlanCuenta(PlanCuentaDos planCuenta) {
		this.planCuenta = planCuenta;
	}


	public boolean getSeleccionado() {
		return seleccionado;
	}


	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}


	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof PlanCuentaSeleccionable) {
			PlanCuentaSeleccionable aux = (PlanCuentaSeleccionable) obj;
			if (aux.getPlanCuenta().getIdPlanCuenta().equals(planCuenta.getIdPlanCuenta())) {
				result = true;
			}
		}
		return result;
	}

}

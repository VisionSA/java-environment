package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Plan {

	public Integer idPlan;
	public String descripcion;
	public String habilitado;
	public Integer sucursal;
	public String esPlanPorDefecto;
	public Set planesVersion;

	public Plan() {
		this.planesVersion = new HashSet();
	}

	public Plan(String descripcion, String habilitado, Integer sucursal) {
		super();
		this.descripcion = descripcion;
		this.habilitado = habilitado;
		this.sucursal = sucursal;
	}

	public Plan(Integer idPlan, String descripcion, String esPlanPorDefecto,
			String habilitado) {
		this.descripcion = descripcion;
		this.esPlanPorDefecto = esPlanPorDefecto;
		this.habilitado = habilitado;
		this.idPlan = idPlan;
	}

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Set getPlanesVersion() {
		return planesVersion;
	}

	public void setPlanesVersion(Set planesVersion) {
		this.planesVersion = planesVersion;
	}

	public String getEsPlanPorDefecto() {
		return esPlanPorDefecto;
	}

	public void setEsPlanPorDefecto(String esPlanPorDefecto) {
		this.esPlanPorDefecto = esPlanPorDefecto;
	}

	/**
	 * @return La version actual del plan
	 * */
	public PlanVersion getVersionActual() {
		PlanVersion verAct = null;
		if (this.getPlanesVersion() != null) {
			Iterator<PlanVersion> iterPlanVersion = this.getPlanesVersion()
					.iterator();
			while (iterPlanVersion.hasNext()) {
				PlanVersion pV = iterPlanVersion.next();
				if (pV.getFechaDesde().compareTo(new Date()) < 0) {
					if (verAct == null){
						verAct = pV;
					}else if (pV.compareTo(verAct) > 0){
						verAct = pV;
					}	
				}
			}
		}

		return verAct;
	}

	/**
	 * @return true si entro en vigencia alguna version. Esto limitara acciones
	 *         como por ejemplo el borrado. false en caso contrario.
	 * */
	public boolean entroEnVigenciaAlgunaVersion() {
		return (getVersionActual() != null);
	}

	public boolean sePuedeIniciarNuevaVersion(PlanVersion planVersion) {
		boolean result = true;
		Iterator<PlanVersion> iterPlanVersion = this.getPlanesVersion()
				.iterator();
		while (iterPlanVersion.hasNext()) {
			PlanVersion pV = iterPlanVersion.next();
			if (pV.getIdPlanVersion().intValue() != planVersion
					.getIdPlanVersion().intValue()
					&& pV.getFechaDesde()
							.compareTo(planVersion.getFechaDesde()) == 0) {
				result = false;
				break;
			}
		}
		return result;
	}

}

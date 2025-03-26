package com.bizitglobal.tarjetafiel.planificadoremail.negocio;

public class PlanColumnas implements Comparable<PlanColumnas> {

	private Long idPlanColum;
	private PlanProcesoEmail planProcesoEmail;
	private ParamTemplate paramTemplate;
	private String campo;


	public PlanColumnas() {

	}


	public Long getIdPlanColum() {
		return idPlanColum;
	}


	public void setIdPlanColum(Long idPlanColum) {
		this.idPlanColum = idPlanColum;
	}


	public PlanProcesoEmail getPlanProcesoEmail() {
		return planProcesoEmail;
	}


	public void setPlanProcesoEmail(PlanProcesoEmail planProcesoEmail) {
		this.planProcesoEmail = planProcesoEmail;
	}


	public ParamTemplate getParamTemplate() {
		return paramTemplate;
	}


	public void setParamTemplate(ParamTemplate paramTemplate) {
		this.paramTemplate = paramTemplate;
	}


	public String getCampo() {
		return campo;
	}


	public void setCampo(String campo) {
		this.campo = campo;
	}


	public int compareTo(PlanColumnas o) {
		int resultado = 0;

		if (this.idPlanColum < o.getIdPlanColum()) {
			resultado = -1;
		}
		else if (this.idPlanColum > o.getIdPlanColum()) {
			resultado = 1;
		}
		else {
			resultado = 0;
		}
		return resultado;
	}
}

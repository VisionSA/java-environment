package com.bizitglobal.tarjetafiel.planificadoremail.negocio;

import java.util.Date;


/***** @Id:6958 ******/
public class HistoricoPlan {

	private Long idHistorico;
	private PlanProcesoEmail planProcesoEmail;
	private Date fecEjecucion;
	private String claveUnica;
	private String queryEjecucion;
	private String archivoTemp;


	public HistoricoPlan() {

	}


	public Long getIdHistorico() {
		return idHistorico;
	}


	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}


	public PlanProcesoEmail getPlanProcesoEmail() {
		return planProcesoEmail;
	}


	public void setPlanProcesoEmail(PlanProcesoEmail planProcesoEmail) {
		this.planProcesoEmail = planProcesoEmail;
	}


	public Date getFecEjecucion() {
		return fecEjecucion;
	}


	public void setFecEjecucion(Date fecEjecucion) {
		this.fecEjecucion = fecEjecucion;
	}


	public String getClaveUnica() {
		return claveUnica;
	}


	public void setClaveUnica(String claveUnica) {
		this.claveUnica = claveUnica;
	}


	public String getQueryEjecucion() {
		return queryEjecucion;
	}


	public void setQueryEjecucion(String queryEjecucion) {
		this.queryEjecucion = queryEjecucion;
	}


	public String getArchivoTemp() {
		return archivoTemp;
	}


	public void setArchivoTemp(String archivoTemp) {
		this.archivoTemp = archivoTemp;
	}

}

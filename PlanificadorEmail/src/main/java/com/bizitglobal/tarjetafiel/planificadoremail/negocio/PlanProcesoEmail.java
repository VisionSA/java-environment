package com.bizitglobal.tarjetafiel.planificadoremail.negocio;

import java.util.Date;
import java.util.Set;


public class PlanProcesoEmail {

	private Long idPlan;
	private String descripcion;
	private Date fecCreacion;
	private Date fecUltMod;
	private Long idOperUltMod;
	private Boolean activo;
	private String query;
	private String queryFrom;
	private String queryWhere;
	private Template template;
	private String frecuencia;
	private Date fecVigDesde;
	private Date fecVigHasta;
	@SuppressWarnings("rawtypes")
	private Set planColumnas;
	private String expressionEjecucion;


	public PlanProcesoEmail() {

	}


	public Long getIdPlan() {
		return idPlan;
	}


	public void setIdPlan(Long idPlan) {
		this.idPlan = idPlan;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Date getFecCreacion() {
		return fecCreacion;
	}


	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}


	public Date getFecUltMod() {
		return fecUltMod;
	}


	public void setFecUltMod(Date fecUltMod) {
		this.fecUltMod = fecUltMod;
	}


	public Long getIdOperUltMod() {
		return idOperUltMod;
	}


	public void setIdOperUltMod(Long idOperUltMod) {
		this.idOperUltMod = idOperUltMod;
	}


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public Template getTemplate() {
		return template;
	}


	public void setTemplate(Template template) {
		this.template = template;
	}


	public String getFrecuencia() {
		return frecuencia;
	}


	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}


	public Date getFecVigDesde() {
		return fecVigDesde;
	}


	public void setFecVigDesde(Date fecVigDesde) {
		this.fecVigDesde = fecVigDesde;
	}


	public Date getFecVigHasta() {
		return fecVigHasta;
	}


	public void setFecVigHasta(Date fecVigHasta) {
		this.fecVigHasta = fecVigHasta;
	}


	public String getQueryFrom() {
		return queryFrom;
	}


	public void setQueryFrom(String queryFrom) {
		this.queryFrom = queryFrom;
	}


	public String getQueryWhere() {
		return queryWhere;
	}


	public void setQueryWhere(String queryWhere) {
		this.queryWhere = queryWhere;
	}


	@SuppressWarnings("rawtypes")
	public Set getPlanColumnas() {
		return planColumnas;
	}


	@SuppressWarnings("rawtypes")
	public void setPlanColumnas(Set planColumnas) {
		this.planColumnas = planColumnas;
	}


	public String getExpressionEjecucion() {
		return expressionEjecucion;
	}


	public void setExpressionEjecucion(String expressionEjecucion) {
		this.expressionEjecucion = expressionEjecucion;
	}
}

package com.bizitglobal.tarjetafiel.planificadoremail.negocio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/***** @Id:6958 ******/
public class EmailInCola {

	private Long idEmail;
	private PlanProcesoEmail planProcesoEmail;
	private String email;
	private Boolean pendiente;
	private Date fecCreacion;
	private Date fecEnvio;
	private String asunto;
	private Set valoresParam = new HashSet();
	private String claveUnica;
	private String estado;
	private HistoricoPlan historicoPlan;
	private Long idIdentificador;
	public final static String ESTADO_EMAIL_PENDIENTE = "P";
	public final static String ESTADO_EMAIL_INICIADO = "I";
	public final static String ESTADO_EMAIL_ENVIADO = "E";
	public final static String ESTADO_EMAIL_COLA = "C";
	public final static String ESTADO_EMAIL_REBOTADO = "R";


	public EmailInCola() {
	}


	public Long getIdEmail() {
		return idEmail;
	}


	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}


	public PlanProcesoEmail getPlanProcesoEmail() {
		return planProcesoEmail;
	}


	public void setPlanProcesoEmail(PlanProcesoEmail planProcesoEmail) {
		this.planProcesoEmail = planProcesoEmail;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getPendiente() {
		return pendiente;
	}


	public void setPendiente(Boolean pendiente) {
		this.pendiente = pendiente;
	}


	public Date getFecCreacion() {
		return fecCreacion;
	}


	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}


	public Date getFecEnvio() {
		return fecEnvio;
	}


	public void setFecEnvio(Date fecEnvio) {
		this.fecEnvio = fecEnvio;
	}


	public String getAsunto() {
		return asunto;
	}


	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}


	public Set getValoresParam() {
		return valoresParam;
	}


	public void setValoresParam(Set valoresParam) {
		this.valoresParam = valoresParam;
	}


	public String getClaveUnica() {
		return claveUnica;
	}


	public void setClaveUnica(String claveUnica) {
		this.claveUnica = claveUnica;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public HistoricoPlan getHistoricoPlan() {
		return historicoPlan;
	}


	public void setHistoricoPlan(HistoricoPlan historicoPlan) {
		this.historicoPlan = historicoPlan;
	}


	public Long getIdIdentificador() {
		return idIdentificador;
	}


	public void setIdIdentificador(Long idIdentificador) {
		this.idIdentificador = idIdentificador;
	}
}

package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.Date;



public class Moroso  {
	private Long idCliente;
	private Long idPlanDeMora;
	private Date fechaMora;
	// campo sin mapear, representa la cantidad de dias que el cliente lleva en mora hasta el dia actual
	private Integer diasEnMora;
	public Moroso() {

	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getIdPlanDeMora() {
		return idPlanDeMora;
	}
	public void setIdPlanDeMora(Long idPlanDeMora) {
		this.idPlanDeMora = idPlanDeMora;
	}
	public Date getFechaMora() {
		return fechaMora;
	}
	public void setFechaMora(Date fechaMora) {
		this.fechaMora = fechaMora;
	}
	public Integer getDiasEnMora() {
		return diasEnMora;
	}
	public void setDiasEnMora(Integer diasEnMora) {
		this.diasEnMora = diasEnMora;
	}

	
	
}

package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.general.negocio.Partido;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;

public class EjecucionPlan  {
	
	public Long idEjecucionPlan;
	public Date fechaEjecucion;
	public Plan plan;
	public Etapa etapa;
	public Accion accion;
	public String confirmoAccion;
	public String comentario;
	public String urlArchivo;
	public Colaborador cobrador;
	public Long idUsuario;
	private Partido partido;
	private Long idAbogado;
	private Long idParent;
	
	
	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public void ejecutarAccion() {
		
	}
	
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	
	public String getConfirmoAccion() {
		return confirmoAccion;
	}

	public void setConfirmoAccion(String confirmoAccion) {
		this.confirmoAccion = confirmoAccion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getUrlArchivo() {
		return urlArchivo;
	}

	public void setUrlArchivo(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdEjecucionPlan() {
		return idEjecucionPlan;
	}

	public void setIdEjecucionPlan(Long idEjecucionPlan) {
		this.idEjecucionPlan = idEjecucionPlan;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	public Colaborador getCobrador() {
		return cobrador;
	}

	public void setCobrador(Colaborador cobrador) {
		this.cobrador = cobrador;
	}

	public Long getIdAbogado() {
		return idAbogado;
	}

	public void setIdAbogado(Long idAbogado) {
		this.idAbogado = idAbogado;
	}

	public Long getIdParent() {
		return idParent;
	}

	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}
	
	

}

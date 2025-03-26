package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.sql.Timestamp;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Regla;

public class AlertaTipoIndividuo implements Negocio {
	private Long idAlertaTipoIndiv = new Long(0);
	private String activa = "";
	private String descripcion = "";
	private String esAutomatico = "";
	private String esInmediata = "";
	private Estados estados;
	private Long idOperador = new Long(0);
	private Regla regla;
	private Long idReglaParentExito = new Long(0);
	private Long idReglaParentFracaso = new Long(0);
	private TipoIndividuo tipoIndividuo;
	private String produceCierre = "";
	private String requiereAutorizacion = "";
	private Timestamp timestamp = null;
	private Set alertasSolicitudes;

	public AlertaTipoIndividuo() {
		this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public AlertaTipoIndividuo(Long idAlertaTipoIndiv) {
		this(idAlertaTipoIndiv, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public AlertaTipoIndividuo(Long idAlertaTipoIndiv, String activa, String descripcion, String esAutomatico, String esInmediata, Estados estados, Long idOperador, Regla regla, Long idReglaParentExito, Long idReglaParentFracaso, TipoIndividuo tipoIndividuo, String produceCierre, String requiereAutorizacion, Timestamp timestamp, Set alertasSolicitudes) {
		super();
		this.idAlertaTipoIndiv = idAlertaTipoIndiv;
		this.activa = activa;
		this.descripcion = descripcion;
		this.esAutomatico = esAutomatico;
		this.esInmediata = esInmediata;
		this.estados = estados;
		this.idOperador = idOperador;
		this.regla = regla;
		this.idReglaParentExito = idReglaParentExito;
		this.idReglaParentFracaso = idReglaParentFracaso;
		this.tipoIndividuo = tipoIndividuo;
		this.produceCierre = produceCierre;
		this.requiereAutorizacion = requiereAutorizacion;
		this.timestamp = timestamp;
		this.alertasSolicitudes = alertasSolicitudes;
	}


	public Long getId() {
		return idAlertaTipoIndiv;
	}
	
	public String getLabel() {
		return activa;
	}
	
	
	public String getActiva() {
		return activa;
	}

	public void setActiva(String activa) {
		this.activa = activa;
	}

	public Set getAlertasSolicitudes() {
		return alertasSolicitudes;
	}

	public void setAlertasSolicitudes(Set alertasSolicitudes) {
		this.alertasSolicitudes = alertasSolicitudes;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEsAutomatico() {
		return esAutomatico;
	}

	public void setEsAutomatico(String esAutomatico) {
		this.esAutomatico = esAutomatico;
	}

	public String getEsInmediata() {
		return esInmediata;
	}

	public void setEsInmediata(String esInmediata) {
		this.esInmediata = esInmediata;
	}

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	public Long getIdAlertaTipoIndiv() {
		return idAlertaTipoIndiv;
	}

	public void setIdAlertaTipoIndiv(Long idAlertaTipoIndiv) {
		this.idAlertaTipoIndiv = idAlertaTipoIndiv;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public Long getIdReglaParentExito() {
		return idReglaParentExito;
	}

	public void setIdReglaParentExito(Long idReglaParentExito) {
		this.idReglaParentExito = idReglaParentExito;
	}

	public Long getIdReglaParentFracaso() {
		return idReglaParentFracaso;
	}

	public void setIdReglaParentFracaso(Long idReglaParentFracaso) {
		this.idReglaParentFracaso = idReglaParentFracaso;
	}

	public String getProduceCierre() {
		return produceCierre;
	}

	public void setProduceCierre(String produceCierre) {
		this.produceCierre = produceCierre;
	}

	public Regla getRegla() {
		return regla;
	}

	public void setRegla(Regla regla) {
		this.regla = regla;
	}

	public String getRequiereAutorizacion() {
		return requiereAutorizacion;
	}

	public void setRequiereAutorizacion(String requiereAutorizacion) {
		this.requiereAutorizacion = requiereAutorizacion;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public TipoIndividuo getTipoIndividuo() {
		return tipoIndividuo;
	}

	public void setTipoIndividuo(TipoIndividuo tipoIndividuo) {
		this.tipoIndividuo = tipoIndividuo;
	}

	public String toString() {
		
		return  "Alerta Tipo IndividuoEvaluacion: " +
				"id: " + idAlertaTipoIndiv + 
				", activa: " + activa +
				", descripcion: " + descripcion + 
				", es automatico: " + esAutomatico +
				", es inmediato: " + esInmediata +
				", estado: " + estados.toString()+
				", id operador: " + idOperador +
				", regla: " + regla.toString() + 
				", id Regla Parent. Exito: " + idReglaParentExito +
				", id Regla Parent. Fracaso: " + idReglaParentFracaso +
				", tipo individuo: " + tipoIndividuo.toString() +
				", produce cierre: " + produceCierre +
				", requiere Autorizacion: " + requiereAutorizacion +
				", timestamp: " + timestamp;
	}
}


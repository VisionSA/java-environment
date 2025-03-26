package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.sql.Timestamp;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class Clearing implements Negocio {
	private Long idClearing = new Long(0);
	private Integer afecActiComerc = new Integer(0);
	private Integer afecActiEntfin = new Integer(0);
	private Integer afecCancelComerc = new Integer(0);
	private Integer afecCancelEntfin = new Integer(0);
	private String correcDatosLab = "";
	private String correcDniCuit = "";
	private String correcDomicilio = "";
	private String correcFecNac = "";
	private String correcNombre = "";
	private Long idOperador = new Long(0);
	private SolicitudIndividuo solicitudIndividuo;
	private TipoClearing tipoClearing;
	private String observacion = "";
	private Integer situaAnteBcra = new Integer(0);
	private String tieneChkRech = "";
	private Timestamp timestamp = null;

	public Clearing() {
	}
	
	public Clearing(Long idClearing) {
		this(idClearing,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
	}
	
	public Clearing(Long idClearing, Integer afecActiComerc, Integer afecActiEntfin, Integer afecCancelComerc, Integer afecCancelEntfin, String correcDatosLab, String correcDniCuit, String correcDomicilio, String correcFecNac, String correcNombre, Long idOperador, SolicitudIndividuo solicitudIndividuo, TipoClearing tipoClearing, String observacion, Integer situaAnteBcra, String tieneChkRech, Timestamp timestamp) {
		super();
		this.idClearing = idClearing;
		this.afecActiComerc = afecActiComerc;
		this.afecActiEntfin = afecActiEntfin;
		this.afecCancelComerc = afecCancelComerc;
		this.afecCancelEntfin = afecCancelEntfin;
		this.correcDatosLab = correcDatosLab;
		this.correcDniCuit = correcDniCuit;
		this.correcDomicilio = correcDomicilio;
		this.correcFecNac = correcFecNac;
		this.correcNombre = correcNombre;
		this.idOperador = idOperador;
		this.solicitudIndividuo = solicitudIndividuo;
		this.tipoClearing = tipoClearing;
		this.observacion = observacion;
		this.situaAnteBcra = situaAnteBcra;
		this.tieneChkRech = tieneChkRech;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return idClearing;
	}
	
	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	public Integer getAfecActiComerc() {
		return afecActiComerc;
	}

	public void setAfecActiComerc(Integer afecActiComerc) {
		this.afecActiComerc = afecActiComerc;
	}

	public Integer getAfecActiEntfin() {
		return afecActiEntfin;
	}

	public void setAfecActiEntfin(Integer afecActiEntfin) {
		this.afecActiEntfin = afecActiEntfin;
	}

	public Integer getAfecCancelComerc() {
		return afecCancelComerc;
	}

	public void setAfecCancelComerc(Integer afecCancelComerc) {
		this.afecCancelComerc = afecCancelComerc;
	}

	public Integer getAfecCancelEntfin() {
		return afecCancelEntfin;
	}

	public void setAfecCancelEntfin(Integer afecCancelEntfin) {
		this.afecCancelEntfin = afecCancelEntfin;
	}

	public String getCorrecDatosLab() {
		return correcDatosLab;
	}

	public void setCorrecDatosLab(String correcDatosLab) {
		this.correcDatosLab = correcDatosLab;
	}

	public String getCorrecDniCuit() {
		return correcDniCuit;
	}

	public void setCorrecDniCuit(String correcDniCuit) {
		this.correcDniCuit = correcDniCuit;
	}

	public String getCorrecDomicilio() {
		return correcDomicilio;
	}

	public void setCorrecDomicilio(String correcDomicilio) {
		this.correcDomicilio = correcDomicilio;
	}

	public String getCorrecFecNac() {
		return correcFecNac;
	}

	public void setCorrecFecNac(String correcFecNac) {
		this.correcFecNac = correcFecNac;
	}

	public String getCorrecNombre() {
		return correcNombre;
	}

	public void setCorrecNombre(String correcNombre) {
		this.correcNombre = correcNombre;
	}

	public Long getIdClearing() {
		return idClearing;
	}

	public void setIdClearing(Long idClearing) {
		this.idClearing = idClearing;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getSituaAnteBcra() {
		return situaAnteBcra;
	}

	public void setSituaAnteBcra(Integer situaAnteBcra) {
		this.situaAnteBcra = situaAnteBcra;
	}

	public SolicitudIndividuo getSolicitudIndividuo() {
		return solicitudIndividuo;
	}

	public void setSolicitudIndividuo(SolicitudIndividuo solicitudIndividuo) {
		this.solicitudIndividuo = solicitudIndividuo;
	}

	public String getTieneChkRech() {
		return tieneChkRech;
	}

	public void setTieneChkRech(String tieneChkRech) {
		this.tieneChkRech = tieneChkRech;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public TipoClearing getTipoClearing() {
		return tipoClearing;
	}

	public void setTipoClearing(TipoClearing tipoClearing) {
		this.tipoClearing = tipoClearing;
	}

	public String toString() {
		
		return "Generar el toString() si hace falta. clase Clearing";
	}
}


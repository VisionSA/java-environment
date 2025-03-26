package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.sql.Timestamp;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class Solicitud implements Negocio {
	
	private Long idSolicitud = new Long(0);
	private String caja = "";
	private String estaImpreso = "";
	private Timestamp fechaRecepcion = null;
	private String folio = "";
	private Estados estados;
	private Long idOperador = new Long(0);
	private Promotor promotor;
	private Timestamp timestamp = null;
	private String nroSolicitud = "";
	private String DV = "";
	private Set solicIndividuos;
	private Long idTipoSolicitud; //Tipo=1 Solicitud general, Tipo=2 Solicitud Modificacion cuenta. 
	
	public Solicitud() {
		this(null,null,null,null,null,null,null,null,null,null, null,null,null);
	}

	public Solicitud(Long idSolicitud) {
		this(idSolicitud,null,null,null,null,null,null,null,null,null, null,null,null);
	}

	public Solicitud(Long idSolicitud, String caja, String estaImpreso, Timestamp fechaRecepcion, String folio, 
			Estados estados, Long idOperador, Promotor promotor, Timestamp timestamp, Set solicIndividuos, String nroSolicitud, String DV,
			Long idTipoSolicitud) {
		
		super();
		this.idSolicitud = idSolicitud;
		this.caja = caja;
		this.estaImpreso = estaImpreso;
		this.fechaRecepcion = fechaRecepcion;
		this.folio = folio;
		this.estados = estados;
		this.idOperador = idOperador;
		this.promotor = promotor;
		this.timestamp = timestamp;
		this.solicIndividuos = solicIndividuos;
		this.nroSolicitud = nroSolicitud;
		this.DV = DV;
		this.idTipoSolicitud = idTipoSolicitud;
	}


	public Long getId() {
		return idSolicitud;
	}
	
	public String getLabel() {
		return caja;
	}
	
	public Long getIdTipoSolicitud() {
		return idTipoSolicitud;
	}

	public void setIdTipoSolicitud(Long idTipoSolicitud) {
		this.idTipoSolicitud = idTipoSolicitud;
	}

	public String getCaja() {
		return caja;
	}

	public void setCaja(String caja) {
		this.caja = caja;
	}

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	public String getEstaImpreso() {
		return estaImpreso;
	}

	public void setEstaImpreso(String estaImpreso) {
		this.estaImpreso = estaImpreso;
	}

	public Timestamp getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public Long getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Promotor getPromotor() {
		return promotor;
	}

	public void setPromotor(Promotor promotor) {
		this.promotor = promotor;
	}

	public Set getSolicIndividuos() {
		return solicIndividuos;
	}

	public void setSolicIndividuos(Set solicIndividuos) {
		this.solicIndividuos = solicIndividuos;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getNroSolicitud() {
		return nroSolicitud;
	}

	public void setNroSolicitud(String nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}
	
	public String getDV() {
		return DV;
	}

	public void setDV(String dv) {
		DV = dv;
	}

	public String toString() {
		
		return "Solicitud: " + 
				"id: " + idSolicitud +
				", caja: " + caja + 
				", esta impreso: " + estaImpreso + 
				", fecha resepcion: " + fechaRecepcion + 
				", folio: " + folio +
				", estado: " + estados +
				", id Operador: " + idOperador +
				", promotor: " + promotor + 
				", timestamp: " + timestamp +
				", nro Solicitud: " + nroSolicitud;
	}

}


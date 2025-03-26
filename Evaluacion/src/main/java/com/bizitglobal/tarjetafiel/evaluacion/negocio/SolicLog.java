package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class SolicLog implements Negocio {
	
	private Long idSolicLog = new Long(0);
	private SolicitudIndividuo solicitudIndividuo;
	private Estados estados;
	private Long idOperador;
	private Timestamp timestamp = null;

	
	public SolicLog() {
		this(null,null,null,null,null);
	}

	public SolicLog(Long idSolicLog) {
		this(idSolicLog,null,null,null,null);
	}
	
	public SolicLog(Estados estados, Long idOperador, 
			SolicitudIndividuo solicitudIndividuo) {
		this(null,estados,idOperador,solicitudIndividuo,null);
		timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
		
	}

	public SolicLog(Long idSolicLog, Estados estados, Long idOperador, 
			SolicitudIndividuo solicitudIndividuo, Timestamp timestamp) {
		super();
		this.idSolicLog = idSolicLog;
		this.estados = estados;
		this.idOperador = idOperador;
		this.solicitudIndividuo = solicitudIndividuo;
		this.timestamp = timestamp;
	}
	
	public Long getId() {
		return idSolicLog;
	}
	
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Long getIdSolicLog() {
		return idSolicLog;
	}

	public void setIdSolicLog(Long idSolicLog) {
		this.idSolicLog = idSolicLog;
	}

	public SolicitudIndividuo getSolicitudIndividuo() {
		return solicitudIndividuo;
	}

	public void setSolicitudIndividuo(SolicitudIndividuo solicitudIndividuo) {
		this.solicitudIndividuo = solicitudIndividuo;
	}

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
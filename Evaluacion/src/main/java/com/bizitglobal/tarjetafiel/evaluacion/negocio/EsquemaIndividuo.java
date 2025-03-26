package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.sql.Timestamp;
import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.EsquemaRegla;


public class EsquemaIndividuo implements Negocio {

	private Long idEsquemaIndividuo = null;
	private String resultado;
	private Timestamp timestamp;
	private EsquemaRegla esquemaRegla;
	private SolicitudIndividuo solicitudIndividuo;
	
	public EsquemaIndividuo() {	
		this(null,null,null,null,null);
	}
	
	public EsquemaIndividuo(Long idEsquemaIndividuo, String resultado, Timestamp timestamp, 
			EsquemaRegla esquemaRegla,SolicitudIndividuo solicitudIndividuo) {
		this.idEsquemaIndividuo = idEsquemaIndividuo;
		this.resultado = resultado;
		this.timestamp = timestamp;
		this.esquemaRegla = esquemaRegla;
		this.solicitudIndividuo = solicitudIndividuo;
	}
	
	public EsquemaRegla getEsquemaRegla() {
		return esquemaRegla;
	}

	public void setEsquemaRegla(EsquemaRegla esquemaRegla) {
		this.esquemaRegla = esquemaRegla;
	}

	public Long getIdEsquemaIndividuo() {
		return idEsquemaIndividuo;
	}

	public void setIdEsquemaIndividuo(Long idEsquemaIndividuo) {
		this.idEsquemaIndividuo = idEsquemaIndividuo;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Timestamp getTimesTamp() {
		return timestamp;
	}

	public void setTimesTamp(Timestamp timesTamp) {
		this.timestamp = timesTamp;
	}

	public Long getId() {
		return idEsquemaIndividuo;
	}

	public String getLabel() {
		return null;
	}
	
    public String toString() {
		
		return  "EsquemaIndividuo : " +
				"idEsquemaIndividuo: " + idEsquemaIndividuo+
				", timestamp: " +  timestamp +
				", resultado: " + resultado+ 
				", EsquemaRegla: " + esquemaRegla+ 
				", SolicitudIndividuo: " + solicitudIndividuo;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public SolicitudIndividuo getSolicitudIndividuo() {
		return solicitudIndividuo;
	}

	public void setSolicitudIndividuo(SolicitudIndividuo solicitudIndividuo) {
		this.solicitudIndividuo = solicitudIndividuo;
	}
	

}


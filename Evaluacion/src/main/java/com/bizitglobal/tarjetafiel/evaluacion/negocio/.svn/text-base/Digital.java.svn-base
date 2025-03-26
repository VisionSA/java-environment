package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.sql.Timestamp;
import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDigital;

public class Digital implements Negocio {
	private Long idDigital = new Long(0);
	private IndividuoEvaluacion individuoEvaluacion;
	private Long idOperador = new Long(0);
	private TipoDigital tipoDigital;
	private Timestamp timestamp = null;
	private String url = "";
	private String descripcion;
	private Date timestampFlex = null;

	public Digital() {
		this(null,null,null,null,null,null,null);
	}
	
	public Digital(Long idDigital) {
		this(idDigital,null,null,null,null,null,null);
	}

	public Digital(Long idDigital, IndividuoEvaluacion individuoEvaluacion, Long idOperador, 
			TipoDigital tipoDigital, Timestamp timestamp, String url, String descripcion) {
		super();
		this.idDigital = idDigital;
		this.individuoEvaluacion = individuoEvaluacion;
		this.idOperador = idOperador;
		this.tipoDigital = tipoDigital;
		this.timestamp = timestamp;
		this.url = url;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return idDigital;
	}
	
	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	public Long getIdDigital() {
		return idDigital;
	}

	public void setIdDigital(Long idDigital) {
		this.idDigital = idDigital;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public IndividuoEvaluacion getIndividuoEvaluacion() {
		return individuoEvaluacion;
	}

	public void setIndividuoEvaluacion(IndividuoEvaluacion individuoEvaluacion) {
		this.individuoEvaluacion = individuoEvaluacion;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public TipoDigital getTipoDigital() {
		return tipoDigital;
	}

	public void setTipoDigital(TipoDigital tipoDigital) {
		this.tipoDigital = tipoDigital;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String toString(){
		
		return  "Digital: " +
				"idDigital " + idDigital+
				", individuos " + individuoEvaluacion.toString()+
				", idOperador " + idOperador+
				", tipo digital " + tipoDigital.toString()+
				", timestamp " + timestamp+
				", url " + url+
				", descripcion: " + descripcion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public Date getTimestampFlex() {
		if(timestamp!=null){
			timestampFlex = new Date(timestamp.getTime());
		}
		return timestampFlex;
	}
	public void setTimestampFlex(Date timestampFlex) {
		this.timestampFlex = timestampFlex;
		if(timestampFlex!=null){
			timestamp = new Timestamp(timestampFlex.getTime());
		}
	}
}


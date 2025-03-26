package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import java.sql.Timestamp;
import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDigital;

public class DocAdjunto implements Negocio {
	private Long idDocAdjunto = null;
	private Long idOperador = null;
	private TipoDigital tipoDigital = null;
	private Timestamp timestamp = null;
	private String url = "";
	private String descripcion;
	private String isAsiento; // si se encuentra en la tabla de lotes o asientos.
	private Long idAsiento; // 
	private Integer idEmpresa;
	private Integer idEjercicio;
	
	//para Spring
	public final static String DOC_ADJUNTO = "t_cont_doc_adjuntos";
	public final static String ID_DOC_ADJUNTO = "c_id_doc_adjunto";
	public final static String IS_ASIENTO = "c_is_asiento";
	public final static String ID_EMPRESA = "c_empresa";
	public final static String ID_EJERCICIO = "c_ejercicio";
	public final static String ID_ASIENTO = "c_asiento";	
	public final static String OPERADOR = "c_id_operador";	
	public final static String TIMESTAMP = "c_timestamp";
	public final static String URL = "c_url";
	public final static String DESCRIPCION = "c_descripcion";
	public final static String TIPO_DIGITAL = "c_id_tipo_digital";
	
	public DocAdjunto() {
         this(null,null,null,null,null,null,null,null,null,null);
	}
	
	public DocAdjunto(Long idDocAdjunto, Long idOperador, TipoDigital tipoDigital, Timestamp timestamp, String url, String descripcion, String isAsiento, Long idAsiento, Integer idEmpresa, Integer idEjercicio) {
        this.idDocAdjunto = idDocAdjunto;
        this.idOperador = idOperador;
        this.tipoDigital = tipoDigital;
        this.timestamp = timestamp;
        this.url = url;
        this.descripcion = descripcion;
        this.isAsiento =isAsiento;
        this.idAsiento = idAsiento;
        this.idEjercicio = idEjercicio;
        this.idEmpresa = idEmpresa;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLabel() {
		return url;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getIdAsiento() {
		return idAsiento;
	}

	public void setIdAsiento(Long idAsiento) {
		this.idAsiento = idAsiento;
	}

	public Long getIdDocAdjunto() {
		return idDocAdjunto;
	}

	public void setIdDocAdjunto(Long idDocAdjunto) {
		this.idDocAdjunto = idDocAdjunto;
	}

	public Integer getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(Integer idEjercicio) {
		this.idEjercicio = idEjercicio;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public String getIsAsiento() {
		return isAsiento;
	}

	public void setIsAsiento(String isAsiento) {
		this.isAsiento = isAsiento;
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
	
//
//	public boolean equals(Object obj) {
//		boolean result = false;
//		if(obj instanceof DocAdjunto) {
//			DocAdjunto aux = (DocAdjunto)obj;
//			if(aux.getId().equals(idOrigen)) {
//				result = true;
//			}
//		}
//		return result;
//	}
}
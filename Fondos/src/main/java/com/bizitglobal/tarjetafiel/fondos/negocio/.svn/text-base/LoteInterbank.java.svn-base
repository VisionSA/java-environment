package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.Date;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public class LoteInterbank  implements Negocio {
	private Long idLoteInterbank;
	private String cabecera;
	private Date fechaGenerado;
	private Date fechaSolicitud;
	private BancoPropio bancoPropio;
	private Operador operador;
	private Set registrosUpload;
	
	public static String C_ID_LOTE_INTERBANK = "C_ID_LOTE_INTERBANK";
	public static String C_CABECERA = "C_CABECERA";
	public static String C_FECHA_GENERADO = "C_FECHA_GENERADO";
	public static String C_FECHA_SOLICITUD = "C_FECHA_SOLICITUD";
	public static String C_ID_BANCO_PROPIO = "C_ID_BANCO_PROPIO";
	public static String C_ID_OPERADOR = "C_ID_OPERADOR";
	
//	T_VIS_FON_LOTE_INTERBANK 
//	C_ID_LOTE_INTERBANK            NOT NULL NUMBER(10)
//	C_CABECERA						VARCHAR2
//	C_FECHA_GENERADO               NOT NULL DATE                                                                                                                                                                                          
//	C_FECHA_SOLICITUD                      DATE
//	C_ID_BANCO_PROPIO				NUMBER(10)

	public LoteInterbank() {
	}

	public LoteInterbank(Long id) {
		idLoteInterbank = id;
	}

	public Long getId() {
		return idLoteInterbank;
	}

	public String getLabel() {
		return "";
	}

	public Long getIdLoteInterbank() {
		return idLoteInterbank;
	}

	public void setIdLoteInterbank(Long idLoteInterbank) {
		this.idLoteInterbank = idLoteInterbank;
	}

	public Date getFechaGenerado() {
		return fechaGenerado;
	}

	public void setFechaGenerado(Date fechaGenerado) {
		this.fechaGenerado = fechaGenerado;
	}

	public String getCabecera() {
		return cabecera;
	}

	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public BancoPropio getBancoPropio() {
		return bancoPropio;
	}

	public void setBancoPropio(BancoPropio bancoPropio) {
		this.bancoPropio = bancoPropio;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Set getRegistrosUpload() {
		return registrosUpload;
	}

	public void setRegistrosUpload(Set registrosUpload) {
		this.registrosUpload = registrosUpload;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof LoteInterbank) {
			LoteInterbank aux = (LoteInterbank)obj;
			if(aux.getId().equals(idLoteInterbank))
				result = true;
		}
		return result;
	}
	
	public String toString() {
		return "Id:"+idLoteInterbank;
	}

}


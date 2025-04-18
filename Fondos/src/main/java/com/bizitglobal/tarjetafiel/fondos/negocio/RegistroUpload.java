package com.bizitglobal.tarjetafiel.fondos.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class RegistroUpload  implements Negocio {
	private Long idRegistroUpload;
	private String registro;
	private LoteInterbank loteInterbank;
	private Cheque cheque;
	private Integer orden;
	
	private String cbu;
	private String cuit;
	private String monto;
	private String opcional;
	private String nombreFantacia;
	private String mails;

//	T_VIS_FON_REGISTRO_UPLOAD
//	C_ID_REGISTRO_UPLOAD           NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_REGISTRO                     NOT NULL VARCHAR2(240)                                                                                                                                                                                 
//	C_ID_LOTE_INTERBANK            NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_ID_CHEQUE                    NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_ORDEN                        NOT NULL NUMBER(4)        

	public RegistroUpload() {
	}

	public RegistroUpload(Long idRegistroUpload, Cheque cheque, 
			LoteInterbank loteInterbank, Integer orden, String registro) {
		super();
		this.idRegistroUpload = idRegistroUpload;
		this.cheque = cheque;
		this.loteInterbank = loteInterbank;
		this.orden = orden;
		this.registro = registro;
	}

	public RegistroUpload(Long id) {
		idRegistroUpload = id;
	}

	public Long getId() {
		return idRegistroUpload;
	}

	public String getLabel() {
		return "";
	}

	public Long getIdRegistroUpload() {
		return idRegistroUpload;
	}

	public void setIdRegistroUpload(Long idRegistroUpload) {
		this.idRegistroUpload = idRegistroUpload;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public LoteInterbank getLoteInterbank() {
		return loteInterbank;
	}

	public void setLoteInterbank(LoteInterbank loteInterbank) {
		this.loteInterbank = loteInterbank;
	}

	public Cheque getCheque() {
		return cheque;
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof RegistroUpload) {
			RegistroUpload aux = (RegistroUpload)obj;
			if(aux.getId().equals(idRegistroUpload))
				result = true;
		}
		return result;
	}
	
	public String toString() {
		return "Id:"+idRegistroUpload;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getOpcional() {
		return opcional;
	}

	public void setOpcional(String opcional) {
		this.opcional = opcional;
	}

	public String getNombreFantacia() {
		return nombreFantacia;
	}

	public void setNombreFantacia(String nombreFantacia) {
		this.nombreFantacia = nombreFantacia;
	}

	public String getMails() {
		return mails;
	}

	public void setMails(String mails) {
		this.mails = mails;
	}
	
	

}


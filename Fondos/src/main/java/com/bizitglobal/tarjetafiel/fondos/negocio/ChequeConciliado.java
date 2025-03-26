package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class ChequeConciliado  implements Negocio {
	private Long idChequeConciliado;
	private Double importeCancela;
	private Date fechaConcilia;
	private DetalleExtracto detalleExtracto;
	private Cheque cheque;

//	T_VIS_FON_CHEQUES_CONCILIADO
//	C_ID_CHEQUE_CONCILIADO         NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_IMPORTE_CANCELA              NOT NULL NUMBER(10,2)                                                                                                                                                                                  
//	C_FECHA_CONCILIA               NOT NULL DATE                                                                                                                                                                                          
//	C_ID_DETALLE_EXTRACTO          NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_ID_CHEQUE                    NOT NULL NUMBER(10)    

	public ChequeConciliado() {
	}

	public ChequeConciliado(Long id) {
		idChequeConciliado = id;
	}

	public Long getId() {
		return idChequeConciliado;
	}

	public String getLabel() {
		return "";
	}

	public Long getIdChequeConciliado() {
		return idChequeConciliado;
	}

	public void setIdChequeConciliado(Long idChequeConciliado) {
		this.idChequeConciliado = idChequeConciliado;
	}

	public Double getImporteCancela() {
		return importeCancela;
	}

	public void setImporteCancela(Double importeCancela) {
		this.importeCancela = importeCancela;
	}

	public Date getFechaConcilia() {
		return fechaConcilia;
	}

	public void setFechaConcilia(Date fechaConcilia) {
		this.fechaConcilia = fechaConcilia;
	}

	public DetalleExtracto getDetalleExtracto() {
		return detalleExtracto;
	}

	public void setDetalleExtracto(DetalleExtracto detalleExtracto) {
		this.detalleExtracto = detalleExtracto;
	}

	public Cheque getCheque() {
		return cheque;
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof ChequeConciliado) {
			ChequeConciliado aux = (ChequeConciliado)obj;
			if(aux.getId().equals(idChequeConciliado)) {
				result = true;
			}
		}
		return result;
	}
	
//	public String toString() {
//		return "Tipo: "+tipo+"|Numero:"+numero+"|Beneficiario:"+beneficiario;
//	}

}


package com.bizitglobal.tarjetafiel.fondos.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;

public class FormaPagoValor  implements Negocio {
	private Long idFormaPagoValor;
	private String descripcion = "";
	private FormaPago formaPago;
	private Double multiplo;
	
//	T_VIS_FON_FORMAS_PAGO_VALORES
//	C_ID_FORMA_PAGO                         NUMBER(10)                                                                                                                                                                                    
//	C_ID_FORMA_PAGO_VALOR          NOT NULL NUMBER(10,2)                                                                                                                                                                                  
//	C_DESCRIPCION                           NVARCHAR2(15)       

	public FormaPagoValor() {
	}

	public FormaPagoValor(Long id) {
		idFormaPagoValor = id;
	}

	public Long getId() {
		return idFormaPagoValor;
	}

	public String getLabel() {
		return descripcion;
	}
	
	public Long getIdFormaPagoValor() {
		return idFormaPagoValor;
	}

	public void setIdFormaPagoValor(Long idFormaPagoValor) {
		this.idFormaPagoValor = idFormaPagoValor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof FormaPagoValor) {
			FormaPagoValor aux = (FormaPagoValor)obj;
			if(aux.getId().equals(idFormaPagoValor)) {
				result = true;
			}
		}
		return result;
	}
	
	public String toString() {
		return "Id:"+idFormaPagoValor+"|Descripcion:"+descripcion;
	}
	
	public Double getMultiplo() {
		return multiplo;
	}
	
	public void setMultiplo(Double multiplo) {
		this.multiplo = multiplo;
	}
}


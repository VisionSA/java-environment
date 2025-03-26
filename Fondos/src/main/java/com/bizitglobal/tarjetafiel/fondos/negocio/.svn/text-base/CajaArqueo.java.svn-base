package com.bizitglobal.tarjetafiel.fondos.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class CajaArqueo  implements Negocio {
	private Long idCajaArqueo;
	private CajaCierre cajaCierre;
	private FormaPagoValor formaPagoValor;
	private Double monto;
	private Integer cantidad;
	
	
//	T_VIS_FON_CAJAS_ARQUEOS
//	C_ID_CAJAARQUEO                NOT NULL NUMBER(10)                                                                                                                                                                                  
//	C_ID_CAJACIERRE                         NUMBER(10)                                                                                                                                                                                  
//	C_ID_FORMA_PAGO_VALOR                   NUMBER(10)                                                                                                                                                                                  
//	C_MONTO                                 NUMBER(10,2)        

	public CajaArqueo() {
	}

	public CajaArqueo(Long id) {
		idCajaArqueo = id;
	}

	public Long getId() {
		return idCajaArqueo;
	}

	public String getLabel() {
		return null;
	}
	
	public Long getIdCajaArqueo() {
		return idCajaArqueo;
	}

	public void setIdCajaArqueo(Long idCajaArqueo) {
		this.idCajaArqueo = idCajaArqueo;
	}

	public CajaCierre getCajaCierre() {
		return cajaCierre;
	}

	public void setCajaCierre(CajaCierre cajaCierre) {
		this.cajaCierre = cajaCierre;
	}

	public FormaPagoValor getFormaPagoValor() {
		return formaPagoValor;
	}

	public void setFormaPagoValor(FormaPagoValor formaPagoValor) {
		this.formaPagoValor = formaPagoValor;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof CajaArqueo) {
			CajaArqueo aux = (CajaArqueo)obj;
			if(aux.getId().equals(idCajaArqueo)) {
				result = true;
			}
		}
		return result;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
//	public String toString() {
//		return "Id:"+idCaja+"|Descripcion:"+descripcion;
//	}

}


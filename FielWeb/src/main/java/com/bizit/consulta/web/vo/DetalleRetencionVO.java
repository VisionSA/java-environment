package com.bizit.consulta.web.vo;

import java.math.BigDecimal;

public class DetalleRetencionVO {
	private String descripcion;
	private BigDecimal monto;
/*@I4923*/	private String codigoPosnet;
/*@F4923*/
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

/*@I4923*/	public String getCodigoPosnet() {
		return codigoPosnet;
	}

	public void setCodigoPosnet(String codigoPosnet) {
		this.codigoPosnet = codigoPosnet;
	}
/*@F4923*/
}

package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import com.bizitglobal.tarjetafiel.evaluacion.negocio.Cobrador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CtaCteCliente;

public class Recibo {

	private Long idRecibo;
	private Long codigoRecibo;
	private CtaCteCliente ctaCteCliente;
	private Cobrador cobrador;
	private Character esEstadoAnulado;
	private Long desde;
	private Long hasta;

	public Long getDesde() {
		return desde;
	}

	public void setDesde(Long desde) {
		this.desde = desde;
	}
	
	
	public Long getHasta() {
		return hasta;
	}

	public void setHasta(Long hasta) {
		this.hasta = hasta;
	}

	public Recibo() {
		super();
	}

	
	
	
	public Recibo(Long desde, Long hasta,Cobrador cobrador) {
		super();
		this.cobrador = cobrador;
		this.desde = desde;
		this.hasta = hasta;
	}

	public Recibo(Long codigoRecibo, Long desde, Long hasta, Cobrador cobrador,Character esEstadoAnulado) {
		this.cobrador = cobrador;
		this.desde = desde;
		this.hasta = hasta;
		this.codigoRecibo = codigoRecibo;
		this.esEstadoAnulado = esEstadoAnulado;
	}

	public Long getIdRecibo() {
		return idRecibo;
	}

	public void setIdRecibo(Long idRecibo) {
		this.idRecibo = idRecibo;
	}

	public Long getCodigoRecibo() {
		return codigoRecibo;
	}

	public void setCodigoRecibo(Long codigoRecibo) {
		this.codigoRecibo = codigoRecibo;
	}

	public CtaCteCliente getCtaCteCliente() {
		return ctaCteCliente;
	}

	public void setCtaCteCliente(CtaCteCliente ctaCteCliente) {
		this.ctaCteCliente = ctaCteCliente;
	}

	public Cobrador getCobrador() {
		return cobrador;
	}

	public void setCobrador(Cobrador cobrador) {
		this.cobrador = cobrador;
	}

	public Character getEsEstadoAnulado() {
		return esEstadoAnulado;
	}

	public void setEsEstadoAnulado(Character esEstadoAnulado) {
		this.esEstadoAnulado = esEstadoAnulado;
	}
}

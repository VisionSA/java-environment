package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public class CambioDiaPagoHistorico implements Negocio {
	
	private Long idCambioDiaPagoHis=new Long(0);
	private Long idCliente;
	private DiaPago diaPagoAnterior;
	private DiaPago diaPagoCambio;
	private Date fecha;
	private Operador operador;
	private String Observaciones;
	
	
	public CambioDiaPagoHistorico() {
		this(null,null,null,null,null,null,null);
	}
	
	public CambioDiaPagoHistorico(Long idCambioDiaPago) {
		this(null,null,null,null,idCambioDiaPago,null,null);
	}

	public CambioDiaPagoHistorico(String observaciones,
			DiaPago diaPagoAnterior, DiaPago diaPagoCambio, Date fecha,
			Long idCambioDiaPagoHis,Long idCliente, Operador operador) {
		super();
		Observaciones = observaciones;
		this.diaPagoAnterior = diaPagoAnterior;
		this.diaPagoCambio = diaPagoCambio;
		this.fecha = fecha;
		this.idCambioDiaPagoHis = idCambioDiaPagoHis;
		this.idCliente = idCliente;
		this.operador = operador;
	}


	@Override
	public Long getId() {
		return idCambioDiaPagoHis;
	}

	@Override
	public String getLabel() {
		return diaPagoAnterior.getLabel();
	}
	
	public Long getIdCambioDiaPagoHis() {
		return idCambioDiaPagoHis;
	}

	public void setIdCambioDiaPagoHis(Long idCambioDiaPagoHis) {
		this.idCambioDiaPagoHis = idCambioDiaPagoHis;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public DiaPago getDiaPagoAnterior() {
		return diaPagoAnterior;
	}

	public void setDiaPagoAnterior(DiaPago diaPagoAnterior) {
		this.diaPagoAnterior = diaPagoAnterior;
	}

	public DiaPago getDiaPagoCambio() {
		return diaPagoCambio;
	}

	public void setDiaPagoCambio(DiaPago diaPagoCambio) {
		this.diaPagoCambio = diaPagoCambio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}

}

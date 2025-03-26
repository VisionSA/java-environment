package com.bizitglobal.webapp.faces.beans.proveedores.wrappers;

import com.bizitglobal.tarjetafiel.proveedores.negocio.CuotaComprobante;


public class CalendarioComprobanteCuota {
	private CuotaComprobante cuota;
	private double montoImputado;
	private double montoResta;


	public CalendarioComprobanteCuota() {
		this(null, 0, 0);
	}


	public CalendarioComprobanteCuota(CuotaComprobante cuota, double montoImputado, double montoResta) {
		super();
		this.cuota = cuota;
		this.montoImputado = montoImputado;
		this.montoResta = montoResta;
	}


	public CuotaComprobante getCuota() {
		return cuota;
	}


	public void setCuota(CuotaComprobante cuota) {
		this.cuota = cuota;
	}


	public double getMontoImputado() {
		return montoImputado;
	}


	public void setMontoImputado(double montoImputado) {
		this.montoImputado = montoImputado;
	}


	public double getMontoResta() {
		return montoResta;
	}


	public void setMontoResta(double montoResta) {
		this.montoResta = montoResta;
	}

}

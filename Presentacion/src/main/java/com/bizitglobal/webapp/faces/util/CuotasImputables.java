package com.bizitglobal.webapp.faces.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;

import com.bizitglobal.tarjetafiel.proveedores.negocio.CuotaComprobante;


public class CuotasImputables {
	private String tipo;
	private CuotaComprobante cuota;
	private String numero;
	private Float imputado;
	private Float resto;
	private Float cuenta;
	private Timestamp fechaEmision;


	public CuotasImputables() {
		this(null, null, null, null, new Float(0), null);
	}


	public CuotasImputables(CuotaComprobante cuota, String numero, Float imputado, Float resto,
			Float cuenta, Timestamp fecha) {
		super();
		this.cuota = cuota;
		this.numero = numero;
		this.imputado = imputado;
		this.resto = resto;
		this.cuenta = cuenta;
		this.fechaEmision = fecha;
	}


	public CuotasImputables(String idImputable) {
		this();
		cuota = new CuotaComprobante();
		cuota.setIdCuotaComprobante(new Long(idImputable));
	}


	public CuotaComprobante getCuota() {
		return cuota;
	}


	public void setCuota(CuotaComprobante cuota) {
		this.cuota = cuota;
	}


	public Long getIdImputable() {
		return cuota.getIdCuotaComprobante();
	}


	public Float getImputado() {
		BigDecimal imputadoMostrar = new BigDecimal(imputado.floatValue()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return new Float(imputadoMostrar.floatValue());
	}


	public void setImputado(Float imputado) {
		this.imputado = imputado;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public Float getResto() {
		BigDecimal restoMostrar = new BigDecimal(resto.floatValue()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return new Float(restoMostrar.floatValue());
	}


	public void setResto(Float resto) {
		this.resto = resto;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getFechaEmision() {
		Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(fechaEmision);
	}


	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}


	public String getFechaVencimiento() {
		Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(cuota.getFechaVencimiento());
	}


	public Float getCuenta() {
		BigDecimal cuentaMostrar = new BigDecimal(cuenta.floatValue()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return new Float(cuentaMostrar.floatValue());
	}


	public void setCuenta(Float cuenta) {
		this.cuenta = cuenta;
	}


	public boolean equals(Object unObj) {
		boolean result = false;
		if (unObj instanceof CuotasImputables) {
			CuotasImputables cuotaImp = (CuotasImputables) unObj;
			if (cuotaImp.getIdImputable().equals(getIdImputable())) {
				result = true;
			}
		}

		return result;
	}
}

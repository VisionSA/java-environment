package com.bizitglobal.tarjetafiel.proveedores.negocio;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class CuotaComprobante {
	private Long idCuotaComprobante;
	private Timestamp fechaVencimiento;
	private Comprobante comprobante;
	private Float importe;
	private Character activo;
	private Set cuotaComprobanteD = new HashSet();
	private Set cuotaComprobanteH = new HashSet();
	
	public CuotaComprobante() {
		this(null,null,null,null,null);
	}
	
	public CuotaComprobante(Long idCuotaComprobante, Timestamp fechaVencimiento, 
			Comprobante comprobante, Float importe, Character activo) {
		super();
		this.idCuotaComprobante = idCuotaComprobante;
		this.fechaVencimiento = fechaVencimiento;
		this.comprobante = comprobante;
		this.importe = importe;
		this.activo = activo;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

	public Timestamp getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Timestamp fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Long getIdCuotaComprobante() {
		return idCuotaComprobante;
	}

	public void setIdCuotaComprobante(Long idCuotaComprobante) {
		this.idCuotaComprobante = idCuotaComprobante;
	}

	public Float getImporte() {
		return importe;
	}

	public void setImporte(Float importe) {
		this.importe = importe;
	}

	public String toString() {
		return "Id:"+idCuotaComprobante+"|FechaVto:"+fechaVencimiento+"|Importe:"+importe;
	}
	
	public Set getCuotaComprobanteD() {
		return cuotaComprobanteD;
	}

	public void setCuotaComprobanteD(Set cuotaComprobanteD) {
		this.cuotaComprobanteD = cuotaComprobanteD;
	}

	public Set getCuotaComprobanteH() {
		return cuotaComprobanteH;
	}

	public void setCuotaComprobanteH(Set cuotaComprobanteH) {
		this.cuotaComprobanteH = cuotaComprobanteH;
	}	
		
	public Character getActivo() {
		return activo;
	}

	public void setActivo(Character activo) {
		this.activo = activo;
	}

	
	
	public boolean equals(Object cuota) {
		boolean result = false;
		if(cuota instanceof CuotaComprobante) {
			CuotaComprobante aux = (CuotaComprobante)cuota;
			if(aux.getIdCuotaComprobante().equals(idCuotaComprobante)) {
				result = true;
			}
		}
		
		return result;
	}

}

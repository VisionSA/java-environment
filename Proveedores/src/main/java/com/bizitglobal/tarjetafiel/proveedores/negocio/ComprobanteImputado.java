package com.bizitglobal.tarjetafiel.proveedores.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ComprobanteImputado {
	private Long idComprobanteImputado;
 	private BigDecimal importeCancela;
 	private Timestamp fechaEmision;
 	private CuotaComprobante cuotaComprobanteD;
 	private CuotaComprobante cuotaComprobanteH;
 	
 	public ComprobanteImputado() {
		this(null,null,null,null,null);
	}
 	
	public ComprobanteImputado(Long idComprobanteImputado, BigDecimal importeCancela, 
			Timestamp fechaEmision, CuotaComprobante cuotaComprobanteD, CuotaComprobante cuotaComprobanteH) {
		super();
		this.idComprobanteImputado = idComprobanteImputado;
		this.importeCancela = importeCancela;
		this.fechaEmision = fechaEmision;
		this.cuotaComprobanteD = cuotaComprobanteD;
		this.cuotaComprobanteH = cuotaComprobanteH;
	}
	
	public ComprobanteImputado(ComprobanteImputado imputado) {
		super();
		this.idComprobanteImputado = imputado.idComprobanteImputado;
		this.importeCancela = imputado.importeCancela;
		this.fechaEmision = imputado.fechaEmision;
		this.cuotaComprobanteD = imputado.cuotaComprobanteD;
		this.cuotaComprobanteH = imputado.cuotaComprobanteH;;
	}

	public Timestamp getFechaEmision() {
		return fechaEmision;
	}
	
	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	public Long getIdComprobanteImputado() {
		return idComprobanteImputado;
	}
	
	public void setIdComprobanteImputado(Long idComprobanteImputado) {
		this.idComprobanteImputado = idComprobanteImputado;
	}
	
	public BigDecimal getImporteCancela() {
		return importeCancela;
	}
	
	public CuotaComprobante getCuotaComprobanteD() {
		return cuotaComprobanteD;
	}

	public void setCuotaComprobanteD(CuotaComprobante cuotaComprobanteD) {
		this.cuotaComprobanteD = cuotaComprobanteD;
	}

	public CuotaComprobante getCuotaComprobanteH() {
		return cuotaComprobanteH;
	}

	public void setCuotaComprobanteH(CuotaComprobante cuotaComprobanteH) {
		this.cuotaComprobanteH = cuotaComprobanteH;
	}	
	
	public void setImporteCancela(BigDecimal importeCancela) {
		this.importeCancela = importeCancela;
	}
	
	public String toString() {
		return "Id:"+idComprobanteImputado+"|Importe:"+importeCancela+"|FechaEmision:"+fechaEmision;
	}
	
	public boolean equals(Object cteImputado) {
		boolean result = false;
		if(cteImputado instanceof ComprobanteImputado) {
			ComprobanteImputado aux = (ComprobanteImputado)cteImputado;
			if(aux.idComprobanteImputado.equals(idComprobanteImputado)) {
				result = true;
			}
		}
		
		return result;
	}
 	
}

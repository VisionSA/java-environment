package com.bizitglobal.tarjetafiel.proveedores.negocio;

import java.math.BigDecimal;

import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;

public class ProveedorImpuesto {
	private Long idProvImpuesto;
	private BigDecimal importe;
	private String esPercepcion;
	private Impuesto impuesto;
	private Comprobante  comprobante;
	
	public ProveedorImpuesto() {
		this(null,null,null,null,null);
	}
	
	public ProveedorImpuesto(Long idProvImpuesto, BigDecimal importe, 
			String esPercepcion, Impuesto impuesto, Proveedor proveedor) {
		super();
		this.idProvImpuesto = idProvImpuesto;
		this.importe = importe;
		this.esPercepcion = esPercepcion;
		this.impuesto = impuesto;
	}

	public String getEsPercepcion() {
		return esPercepcion;
	}
	
	public void setEsPercepcion(String esPercepcion) {
		this.esPercepcion = esPercepcion;
	}
	
	public Long getIdProvImpuesto() {
		return idProvImpuesto;
	}
	
	public void setIdProvImpuesto(Long idProvImpuesto) {
		this.idProvImpuesto = idProvImpuesto;
	}
	
	public BigDecimal getImporte() {
		return importe;
	}
	
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	public Impuesto getImpuesto() {
		return impuesto;
	}
	
	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}
	
	public String toString() {
		return "Id:"+idProvImpuesto+"|Importe:"+importe+"|Percepcion:"+esPercepcion;
	}
	
	public boolean equals(Object unProvImpuesto) {
		boolean result = false;
		if(unProvImpuesto instanceof ProveedorImpuesto) {
			ProveedorImpuesto aux = (ProveedorImpuesto)unProvImpuesto;
			if(aux.getIdProvImpuesto().equals(idProvImpuesto)) {
				result = true;
			}
		}
		
		return result;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

}

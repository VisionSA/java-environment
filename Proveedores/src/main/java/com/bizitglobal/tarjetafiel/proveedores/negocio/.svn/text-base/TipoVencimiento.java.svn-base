package com.bizitglobal.tarjetafiel.proveedores.negocio;

import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;

public class TipoVencimiento {
	private Long idTipoVencimiento;
	private Integer orden;
	private Integer porcentajeMonto;
	private Integer dias;
	private Proveedor proveedor;
	
	public TipoVencimiento() {
		this(null,null,null,null,null);
	}
	
	public TipoVencimiento(Long idTipoVencimiento, Integer orden, Integer porcentajeMonto, 
			Integer dias, Proveedor proveedor) {
		super();
		this.idTipoVencimiento = idTipoVencimiento;
		this.orden = orden;
		this.porcentajeMonto = porcentajeMonto;
		this.dias = dias;
		this.proveedor = proveedor;
	}

	public Integer getDias() {
		return dias;
	}
	
	public void setDias(Integer dias) {
		this.dias = dias;
	}
	
	public Long getIdTipoVencimiento() {
		return idTipoVencimiento;
	}
	
	public void setIdTipoVencimiento(Long idTipoVencimiento) {
		this.idTipoVencimiento = idTipoVencimiento;
	}
	
	public Integer getOrden() {
		return orden;
	}
	
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	public Integer getPorcentajeMonto() {
		return porcentajeMonto;
	}
	
	public void setPorcentajeMonto(Integer porcentajeMonto) {
		this.porcentajeMonto = porcentajeMonto;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public String toString() {
		return "Id:"+idTipoVencimiento+"|Orden:"+orden+"|Porcetanje:"+porcentajeMonto+"|dias:"+dias;
	}
	
	public boolean equals(Object tipo) {
		boolean result = false;
		if(tipo instanceof TipoVencimiento) {
			TipoVencimiento aux = (TipoVencimiento)tipo;
			if(aux.getIdTipoVencimiento().equals(idTipoVencimiento)) {
				result = true;
			}
		}
		
		return result;
	}

}

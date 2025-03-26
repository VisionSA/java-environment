package com.bizitglobal.tarjetafiel.proveedores.negocio;

import com.bizitglobal.tarjetafiel.general.negocio.Telefono;

public class ProveedorTelefono {
	private Long idTelefono;
	private Telefono telefono;
	private Proveedor proveedor;
	
	public ProveedorTelefono() {
		this(null,null);
	}
	
	public ProveedorTelefono(Telefono telefono, Proveedor proveedor) {
		super();
		idTelefono = null;
		this.telefono = telefono;
		this.proveedor = proveedor;
	}

	public Long getIdTelefono() {
		return idTelefono;
	}
	
	public void setIdTelefono(Long idTelefono) {
		this.idTelefono = idTelefono;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public Telefono getTelefono() {
		return telefono;
	}
	
	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

}

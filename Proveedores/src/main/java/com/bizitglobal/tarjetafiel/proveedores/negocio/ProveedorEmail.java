package com.bizitglobal.tarjetafiel.proveedores.negocio;

import com.bizitglobal.tarjetafiel.general.negocio.Email;

public class ProveedorEmail {
	private Long idProvEmail;
	private Proveedor proveedor ;
	private Email email;
	
	public ProveedorEmail() {
		this(null,null);
	}

	public ProveedorEmail(Proveedor proveedor, Email email) {
		super();
		idProvEmail = null;
		this.proveedor = proveedor;
		this.email = email;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Long getIdProvEmail() {
		return idProvEmail;
	}

	public void setIdProvEmail(Long idProvEmail) {
		this.idProvEmail = idProvEmail;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}

package com.bizitglobal.webapp.faces.util;

import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;


/**
 * Represanta una instancia de un proveedor seleccionable, para los listados de proveedores.
 * 
 * @author Daniel
 */
public class ProveedorSeleccionable {
	private Proveedor proveedor;
	private boolean seleccionado;


	public ProveedorSeleccionable() {
		this(null, false);
	}


	public ProveedorSeleccionable(Proveedor proveedor, boolean seleccionado) {
		super();
		this.proveedor = proveedor;
		this.seleccionado = seleccionado;
		this.proveedor.getGrupo().getDescripcion();
	}


	public Proveedor getProveedor() {
		return proveedor;
	}


	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}


	public boolean getSeleccionado() {
		return seleccionado;
	}


	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}


	public String toString() {
		return "Cuit:" + proveedor.getCuit() + "Seleccionado:" + (seleccionado ? "true" : "false");
	}


	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof ProveedorSeleccionable) {
			ProveedorSeleccionable aux = (ProveedorSeleccionable) obj;
			if (aux.getProveedor().getIdProveedor().equals(proveedor.getIdProveedor())) {
				result = true;
			}
		}

		return result;
	}

}

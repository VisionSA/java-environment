package com.bizitglobal.tarjetafiel.proveedores.negocio;

import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisdiccionActividad;

public class ProveedorCategoria {
	private Long idProvCategoria;
	private Proveedor proveedor;
	private String activo;
	
	// Objeto jurisdiccion-actividad que identifica la actividad del proveedor.
	private JurisdiccionActividad jurisdiccionActividad;
	
	public ProveedorCategoria() {
		this(null,null,null);
	}
	
	public ProveedorCategoria(Proveedor proveedor,String activo, JurisdiccionActividad jurisdiccionActividad) {
		super();
		this.proveedor = proveedor;
		this.activo = activo;
		this.jurisdiccionActividad = jurisdiccionActividad;
	}

	public JurisdiccionActividad getJurisdiccionActividad() {
		return jurisdiccionActividad;
	}

	public void setJurisdiccionActividad(JurisdiccionActividad jurisdiccionActividad) {
		this.jurisdiccionActividad = jurisdiccionActividad;
	}

	public Long getIdProvCategoria() {
		return idProvCategoria;
	}
	
	public void setIdProvCategoria(Long idProvCategoria) {
		this.idProvCategoria = idProvCategoria;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	public String toString() {
		return "Id: " + idProvCategoria + "jurisdiccionActividad" + jurisdiccionActividad;
	}
	
	public boolean equals(Object unProveedorCategoria) {
		boolean result = false;
		if(unProveedorCategoria instanceof ProveedorCategoria) {
			ProveedorCategoria aux = (ProveedorCategoria)unProveedorCategoria;
			if(aux.getIdProvCategoria().equals(idProvCategoria)) {
				result = true;
			}
		}
		return result;
	}
}

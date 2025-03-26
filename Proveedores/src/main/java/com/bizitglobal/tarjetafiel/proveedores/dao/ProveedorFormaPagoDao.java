package com.bizitglobal.tarjetafiel.proveedores.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorFormaPago;

public interface ProveedorFormaPagoDao {
	
	public void grabarProveedorFormaPago(ProveedorFormaPago unProveedorFormaPago);
	
	public ProveedorFormaPago buscarProveedorFormaPago(Integer id);
	
	public void borrarProveedorFormaPago(Integer id);
	
	public void borrarProveedorFormaPago(ProveedorFormaPago unProveedorFormaPago);
	
	public List listarTodos(Filtro filtro);
	
	public void actualizarProveedorFormaPago(ProveedorFormaPago unProveedorFormaPago);
	
}

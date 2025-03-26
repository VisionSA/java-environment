package com.bizitglobal.tarjetafiel.proveedores.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;

public interface ProveedorDao {
	
	public void grabarProveedor(Proveedor unProveedor);
	
	public Proveedor buscarProveedor(Long id);
	
	public void borrarProveedor(Long id);
	
	public void borrarProveedor(Proveedor unProveedor);
	
	public List listarTodos(Filtro filtro);
	
	public void actualizarProveedor(Proveedor unProveedor);
	
	public void grabarYActualizarProveedor(Proveedor unProveedor);
	
	public Long maxIdProveedor();
	
}

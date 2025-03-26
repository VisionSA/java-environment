package com.bizitglobal.tarjetafiel.proveedores.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.negocio.RelacionProveedor;

public interface RelacionDao {
	
	public void grabarRelacion(RelacionProveedor unaRelacion);
	
	public RelacionProveedor buscarRelacion(Integer id);
	
	public void borrarRelacion(Integer id);
	
	public void borrarRelacion(RelacionProveedor unaRelacion);
	
	public List listarTodos(Filtro filtro);
	
	public void actualizarRelacion(RelacionProveedor unaRelacion);
	
}

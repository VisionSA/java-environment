package com.bizitglobal.tarjetafiel.proveedores.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.negocio.AsientoContable;

public interface AsientoContableDao {
	
	public void grabarAsientoContable(AsientoContable unAsientoContable);
	
	public AsientoContable buscarAsientoContable(Integer id);
	
	public void borrarAsientoContable(Integer id);
	
	public void borrarAsientoContable(AsientoContable unAsientoContable);
	
	public List listarTodos();
	
	public void actualizarAsientoContable(AsientoContable unAsientoContable);
	
}

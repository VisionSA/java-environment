package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ObservoSucursal;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface ObservoSucursalDao {
	public void grabarEvaObsSucursales (ObservoSucursal pObject);
	public ObservoSucursal buscarEvaObsSucursales (Long id);
	public void borrarEvaObsSucursales (Long id);
	public void borrarEvaObsSucursales (ObservoSucursal pObject);
	public void actualizarEvaObsSucursales (ObservoSucursal pObject);
	public List listarTodos(Filtro filtro);
}


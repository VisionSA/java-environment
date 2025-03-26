package com.bizitglobal.tarjetafiel.contabilidad.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.CentroCostos;

public interface CentroCostosDao {
	public void grabarCentroCostos (CentroCostos pObject);
	public CentroCostos buscarCentroCostos (Long id);
	public void borrarCentroCostos (Long id);
	public void borrarCentroCostos(CentroCostos pObject);
	public void actualizarCentroCostos (CentroCostos pObject);
	public List listarTodos(Filtro filtro);
	public CentroCostos leerCentroCostos(Long id);

}

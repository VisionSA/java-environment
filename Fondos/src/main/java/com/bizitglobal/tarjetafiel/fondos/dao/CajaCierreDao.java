package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.Collection;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaCierre;

public interface CajaCierreDao {
	public void grabarCajaCierre (CajaCierre pObject);
	public void grabarCajaCierreCollection(Collection<CajaCierre> collection);
	public CajaCierre buscarCajaCierre (Long id);
	public void borrarCajaCierre (Long id);
	public void borrarCajaCierre (CajaCierre pObject);
	public void actualizarCajaCierre (CajaCierre pObject);
	public List listarTodos(Filtro filtro);
}


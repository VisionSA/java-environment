package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;

public interface LugarDao {
	public void grabarLugar (Lugar pObject);
	public Lugar buscarLugar (Long id);
	public void borrarLugar (Long id);
	public void borrarLugar (Lugar pObject);
	public void actualizarLugar (Lugar pObject);
	public List listarTodos(Filtro filtro);
	public Lugar buscarLugarPorCodigo(final Long codigo);
}


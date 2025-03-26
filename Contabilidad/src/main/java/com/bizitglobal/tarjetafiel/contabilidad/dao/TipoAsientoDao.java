package com.bizitglobal.tarjetafiel.contabilidad.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.TipoAsiento;

public interface TipoAsientoDao {
	public void grabarTipoAsiento (TipoAsiento pObject);
	public TipoAsiento buscarTipoAsiento (Long id);
	public void borrarTipoAsiento (Long id);
	public void borrarTipoAsiento(TipoAsiento pObject);
	public void actualizarTipoAsiento (TipoAsiento pObject);
	public List listarTodos(Filtro filtro);
	public TipoAsiento leerTipoAsiento(Long id);

}

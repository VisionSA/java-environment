package com.bizitglobal.tarjetafiel.cobranzas.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.cobranzas.negocio.AccionVersion;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface AccionVersionDao {
	public void grabarAccionVersion (AccionVersion pObject);
	public AccionVersion buscarAccionVersion (Long id);
	public void borrarAccionVersion (Long id);
	public void borrarAccionVersion (AccionVersion pObject);
	public void actualizarAccionVersion(AccionVersion pObject);
	public List listarTodos(Filtro filtro);
}

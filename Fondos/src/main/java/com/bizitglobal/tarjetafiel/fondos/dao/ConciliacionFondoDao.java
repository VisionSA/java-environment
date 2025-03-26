package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondo;

public interface ConciliacionFondoDao {
	public void grabarConciliacionFondo (ConciliacionFondo pObject);
	public ConciliacionFondo buscarConciliacionFondo (Long id);
	public void borrarConciliacionFondo (Long id);
	public void borrarConciliacionFondo (ConciliacionFondo pObject);
	public void actualizarConciliacionFondo (ConciliacionFondo pObject);
	public List listarTodos(Filtro filtro);
}


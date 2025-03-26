package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;

public interface MovimientoMPDao {
	public void grabarMovimientoMP (MovimientoMP pObject);
	public MovimientoMP buscarMovimientoMP (Long id);
	public void borrarMovimientoMP (Long id);
	public void borrarMovimientoMP (MovimientoMP pObject);
	public void actualizarMovimientoMP (MovimientoMP pObject);
	public List listarTodos(Filtro filtro);
}


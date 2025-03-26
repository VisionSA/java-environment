package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.paginacion.Paginador;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;

public interface MovimientoDao {
	public void grabarMovimiento (Movimiento pObject);
	public Movimiento buscarMovimiento (Long id);
	public void borrarMovimiento (Long id);
	public void borrarMovimiento (Movimiento pObject);
	public void actualizarMovimiento (Movimiento pObject);
	public List listarTodos(Filtro filtro);
	public List listarTodosPagina(Filtro filtro, final Paginador paginador);
	public Page listarTodosPage(Filtro filtro, final int pageNumber, final int pageSize);
	public Long insertarMovimiento(Movimiento movimiento);
}


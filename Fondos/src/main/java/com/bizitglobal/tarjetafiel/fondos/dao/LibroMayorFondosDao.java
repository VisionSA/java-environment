package com.bizitglobal.tarjetafiel.fondos.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public interface LibroMayorFondosDao {

	List listarTodos(Filtro filtro);
	List listarTodosFondos(Date inicio, Date cierre, Long idCuenta,
			Date inicioEjercicio);
	BigDecimal getSaldoAC(Date fin, Long idCuenta);
}


package com.bizitglobal.tarjetafiel.contabilidad.dao;

import java.util.Date;
import java.util.List;

public interface RenglonLibroMayorDao {
	
	public List listarTodos(Long idEmpresa, Long idEjercicion, Date inicio, Date cierre, Long idCuenta, Date inicioEjercicio);

	public Long getSaldoInicial(Long idEmpresa, Long idEjercicion, Date inicioEjercicio, Date inicio, Long idCuenta);
	
	public List listarTodosFondos(Date inicio, Date cierre, Long idCuenta,Date inicioEjercicio) ;
	
	public Long getSaldoInicial(Date inicioEjercicio, Date inicio, Long idCuenta);

}

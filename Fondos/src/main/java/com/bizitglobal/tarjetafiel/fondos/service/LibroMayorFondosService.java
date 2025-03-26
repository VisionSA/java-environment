package com.bizitglobal.tarjetafiel.fondos.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioException;
import com.bizitglobal.tarjetafiel.fondos.exception.LibroMayorExeption;

public interface LibroMayorFondosService{
	public List getCuenta(Filtro filtro) throws LibroMayorExeption;
	/**
	* Obtiene un Ejercicio que se encuentra abierto y activo.
	 * @throws LibroMayorExeption 
	*/
	public List getRenglonesLibroMayorFondos(final Date inicio, final Date cierre,final Long idCuenta,final Date inicioEjercicio)  throws EjercicioException, LibroMayorExeption ;
	
	public	BigDecimal getSaldoAC( Date fin,Long idCuenta);
}

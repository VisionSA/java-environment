package com.bizitglobal.tarjetafiel.proveedores.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.proveedores.exception.AsientoContableException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.AsientoContable;

/**
 * @author Daniel
 * Interface de servicios para los asientos contables del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface AsientoContableService {
	
	/**
	 * Graba un asiento contable en la base de datos.
	 * @param unAsientoContable, AsientoContable a grabar.
	 */
	public void grabarAsientoContable(AsientoContable unAsientoContable) 
			throws AsientoContableException;
	
	/**
	 * Obtiene un asiento contable de la base de datos dado su id.
	 * @param id, Identificador del asiento contable buscado.
	 * @return El asiento contable buscado.
	 */
	public AsientoContable leerAsientoContable(Integer id) throws AsientoContableException;
	
	/**
	 * Borra un asiento contable de la base de datos dado su id.
	 * @param id, Identificador del asiento contable.
	 */
	public void borrarAsientoContable(Integer id) throws AsientoContableException;
	
	/**
	 * Borra un asiento contable de la base de datos dado el mismo.
	 * @param unAsientoContable, AsientoContable a borrar.
	 */
	public void borrarAsientoContable(AsientoContable unAsientoContable) 
			throws AsientoContableException;
	
	/**
	 * Actualiza un asiento contable en la base de datos.
	 * @param unAsientoContable, AsientoContable a actualizar.
	 */
	public void actualizarAsientoContable(AsientoContable unAsientoContable) 
			throws AsientoContableException;
	
	/**
	 * Obtiene una lista de todos los asientos contables.
	 * @return Una lista de asientos contables.
	 */
	public List getAsientoContables() throws AsientoContableException;
	
}

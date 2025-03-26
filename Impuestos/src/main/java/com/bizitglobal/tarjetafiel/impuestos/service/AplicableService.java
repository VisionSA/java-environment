package com.bizitglobal.tarjetafiel.impuestos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.AplicableException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Aplicable;

/**
 * @author Daniel
 * Interface de servicios para las aplicablees del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface AplicableService {
	
	/**
	 * Graba una aplicable en la base de datos.
	 * @param unaAplicable, Aplicable a grabar.
	 */
	public void grabarAplicable(Aplicable unaAplicable) throws AplicableException;
	
	/**
	 * Obtiene una aplicable de la base de datos dado su id.
	 * @param id, Identificador de la aplicable buscada.
	 * @return La aplicable buscada.
	 */
	public Aplicable leerAplicable(Long id) throws AplicableException;
	
	/**
	 * Borra una aplicable de la base de datos dado su id.
	 * @param id, Identificador de la aplicable.
	 */
	public void borrarAplicable(Long id) throws AplicableException;
	
	/**
	 * Borra una aplicable de la base de datos dado el mismo.
	 * @param unaAplicable, Aplicable a borrar.
	 */
	public void borrarAplicable(Aplicable unaAplicable) throws AplicableException;
	
	/**
	 * Actualiza una aplicable en la base de datos.
	 * @param unaAplicable, Aplicable a actualizar.
	 */
	public void actualizarAplicable(Aplicable unaAplicable) throws AplicableException;
	
	/**
	 * Obtiene una lista de todas las aplicablees.
	 * @return Una lista de aplicablees.
	 */
	public List getAplicables(Filtro unFiltro) throws AplicableException;
	
}

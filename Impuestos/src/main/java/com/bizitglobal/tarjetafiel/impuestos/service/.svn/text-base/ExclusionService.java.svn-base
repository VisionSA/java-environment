package com.bizitglobal.tarjetafiel.impuestos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.ExclusionException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;

/**
 * @author Daniel
 * Interface de servicios para las exclusiones del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ExclusionService {
	
	/**
	 * Graba una exclusion en la base de datos.
	 * @param unaExclusion, Exclusion a grabar.
	 */
	public void grabarExclusion(Exclusion unaExclusion) throws ExclusionException;
	
	/**
	 * Obtiene una exclusion de la base de datos dado su id.
	 * @param id, Identificador de la exclusion buscada.
	 * @return La exclusion buscada.
	 */
	public Exclusion leerExclusion(Long id) throws ExclusionException;
	
	/**
	 * Borra una exclusion de la base de datos dado su id.
	 * @param id, Identificador de la exclusion.
	 */
	public void borrarExclusion(Long id) throws ExclusionException;
	
	/**
	 * Borra una exclusion de la base de datos dado el mismo.
	 * @param unaExclusion, Exclusion a borrar.
	 */
	public void borrarExclusion(Exclusion unaExclusion) throws ExclusionException;
	
	/**
	 * Actualiza un exclusion en la base de datos.
	 * @param unExclusion, Exclusion a actualizar.
	 */
	public void actualizarExclusion(Exclusion unaExclusion) throws ExclusionException;
	
	/**
	 * Obtiene una lista de todos las exclusion.
	 * @return Una lista de exclusiones.
	 */
	public List getExclusiones(Filtro filtro) throws ExclusionException;
	
}

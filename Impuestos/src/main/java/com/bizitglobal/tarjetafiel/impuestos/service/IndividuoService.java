package com.bizitglobal.tarjetafiel.impuestos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.IndividuoException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Individuo;

/**
 * @author Daniel
 * Interface de servicios para los individuos del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface IndividuoService {
	
	/**
	 * Graba un individuo en la base de datos.
	 * @param unIndividuo, Individuo a grabar.
	 */
	public Long grabarIndividuo(Individuo unaIndividuo) throws IndividuoException;
	
	/**
	 * Obtiene un individuo de la base de datos dado su id.
	 * @param id, Identificador del individuo buscado.
	 * @return El individuo buscado.
	 */
	public Individuo leerIndividuo(Long id) throws IndividuoException;
	
	/**
	 * Borra un individuo de la base de datos dado su id.
	 * @param id, Identificador del individuo.
	 */
	public void borrarIndividuo(Long id) throws IndividuoException;
	
	/**
	 * Borra un individuo de la base de datos dado el mismo.
	 * @param unIndividuo, Individuo a borrar.
	 */
	public void borrarIndividuo(Individuo unIndividuo) throws IndividuoException;
	
	/**
	 * Actualiza un individuo en la base de datos.
	 * @param unIndividuo, Individuo a actualizar.
	 */
	public void actualizarIndividuo(Individuo unIndividuo) throws IndividuoException;
	
	/**
	 * Obtiene una lista de todos los individuos.
	 * @return Una lista de individuos.
	 */
	public List getIndividuos(Filtro filtro) throws IndividuoException;
	
}

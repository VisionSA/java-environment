package com.bizitglobal.tarjetafiel.impuestos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.ActividadException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Actividad;

/**@deprecated
 * @author Daniel
 * Interface de servicios para las actividades del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ActividadService {
	
	/**
	 * Graba una actividad en la base de datos.
	 * @param unaActividad, Actividad a grabar.
	 */
	public void grabarActividad(Actividad unaActividad) throws ActividadException;
	
	/**
	 * Obtiene una actividad de la base de datos dado su id.
	 * @param id, Identificador de la actividad buscada.
	 * @return La actividad buscada.
	 */
	public Actividad leerActividad(Long id) throws ActividadException;
	
	/**
	 * Borra una actividad de la base de datos dado su id.
	 * @param id, Identificador de la actividad.
	 */
	public void borrarActividad(Long id) throws ActividadException;
	
	/**
	 * Borra una actividad de la base de datos dado el mismo.
	 * @param unaActividad, Actividad a borrar.
	 */
	public void borrarActividad(Actividad unaActividad) throws ActividadException;
	
	/**
	 * Actualiza una actividad en la base de datos.
	 * @param unaActividad, Actividad a actualizar.
	 */
	public void actualizarActividad(Actividad unaActividad) throws ActividadException;
	
	/**
	 * Obtiene una lista de todas las actividades.
	 * @return Una lista de actividades.
	 */
	public List getActividades(Filtro unFiltro) throws ActividadException;
	
}

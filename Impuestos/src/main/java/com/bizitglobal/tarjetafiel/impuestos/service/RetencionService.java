package com.bizitglobal.tarjetafiel.impuestos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.RetencionException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;

/**
 * @author Daniel
 * Interface de servicios para las retenciones del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface RetencionService {
	
	/**
	 * Graba una retencion en la base de datos.
	 * @param unaRetencion, Retencion a grabar.
	 */
	public void grabarRetencion(Retencion unaRetencion) throws RetencionException;
	
	/**
	 * Obtiene una retencion de la base de datos dado su id.
	 * @param id, Identificador de la retencion buscada.
	 * @return La retencion buscada.
	 */
	public Retencion leerRetencion(Long id) throws RetencionException;
	
	/**
	 * Borra una retencion de la base de datos dado su id.
	 * @param id, Identificador de la retencion.
	 */
	public void borrarRetencion(Long id) throws RetencionException;
	
	/**
	 * Borra una retencion de la base de datos dado el mismo.
	 * @param unaRetencion, Retencion a borrar.
	 */
	public void borrarRetencion(Retencion unaRetencion) throws RetencionException;
	
	/**
	 * Actualiza un retencion en la base de datos.
	 * @param unaRetencion, Retencion a actualizar.
	 */
	public void actualizarRetencion(Retencion unaRetencion) throws RetencionException;
	
	/**
	 * Obtiene una lista de todas las retenciones.
	 * @return Una lista de retenciones.
	 */
	public List getRetenciones(Filtro filtro) throws RetencionException;
	
}

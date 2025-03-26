package com.bizitglobal.tarjetafiel.impuestos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.TramosRetencionException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TramosRetencion;

/**
 * @author Daniel
 * Interface de servicios para las categorias del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface TramosRetencionService {
	
	/**
	 * Graba una categoria en la base de datos.
	 * @param unaCategoria, Categoria a grabar.
	 */
	public void grabarTramosRetencion(TramosRetencion unaTramosRetencion) throws TramosRetencionException;
	
	/**
	 * Obtiene una categoria de la base de datos dado su id.
	 * @param id, Identificador de la categoria buscada.
	 * @return La categoria buscada.
	 */
	public TramosRetencion leerTramosRetencion(Long id) throws TramosRetencionException;
	
	/**
	 * Borra una categoria de la base de datos dado su id.
	 * @param id, Identificador de la categoria.
	 */
	public void borrarTramosRetencion(Long id) throws TramosRetencionException;
	
	/**
	 * Borra una categoria de la base de datos dado el mismo.
	 * @param unaCategoria, Categoria a borrar.
	 */
	public void borrarTramosRetencion(TramosRetencion unTramosRetencion) throws TramosRetencionException;
	
	/**
	 * Actualiza un proveedor en la base de datos.
	 * @param unProveedor, Proveedor a actualizar.
	 */
	public void actualizarTramosRetencion(TramosRetencion unTramosRetencion) throws TramosRetencionException;
	
	/**
	 * Obtiene una lista de todos las categorias.
	 * @return Una lista de categorias.
	 */
	public List getTramosRetenciones(Filtro unFiltro) throws TramosRetencionException;
	
}

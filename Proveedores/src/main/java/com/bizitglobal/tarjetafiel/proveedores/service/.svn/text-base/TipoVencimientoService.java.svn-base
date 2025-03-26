package com.bizitglobal.tarjetafiel.proveedores.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.exception.TipoVencimientoException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.TipoVencimiento;

/**
 * @author Daniel
 * Interface de servicios para los tipos de vencimiento del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface TipoVencimientoService {
	
	/**
	 * Graba un tipo vencimiento en la base de datos.
	 * @param unTipoVencimiento, TipoVencimiento a grabar.
	 */
	public void grabarTipoVencimiento(TipoVencimiento unTipoVencimiento) 
			throws TipoVencimientoException;
	
	/**
	 * Obtiene un tipo vencimiento de la base de datos dado su id.
	 * @param id, Identificador del tipo vencimiento buscado.
	 * @return El tipo vencimiento buscado.
	 */
	public TipoVencimiento leerTipoVencimiento(Integer id) throws TipoVencimientoException;
	
	/**
	 * Borra un tipo vencimiento de la base de datos dado su id.
	 * @param id, Identificador del tipo vencimiento.
	 */
	public void borrarTipoVencimiento(Integer id) throws TipoVencimientoException;
	
	/**
	 * Borra un tipo vencimiento de la base de datos dado el mismo.
	 * @param unTipoVencimiento, TipoVencimiento a borrar.
	 */
	public void borrarTipoVencimiento(TipoVencimiento unTipoVencimiento) throws TipoVencimientoException;
	
	/**
	 * Actualiza un tipo vencimiento en la base de datos.
	 * @param unTipoVencimiento, TipoVencimiento a actualizar.
	 */
	public void actualizarTipoVencimiento(TipoVencimiento unTipoVencimiento) 
			throws TipoVencimientoException;
	
	/**
	 * Obtiene una lista de todos los tipos de vencimientos.
	 * @return Una lista de tipos de vencimientos.
	 */
	public List getTipoVencimientos(Filtro filtro) throws TipoVencimientoException;
	
}

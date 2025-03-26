package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.AsientoItemException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface AsientoItemService {
	
	/**
	 * Graba una AsientoItem en la base de datos.
	 * @param unaAsientoItem, AsientoItem a grabar.
	 */
	public void grabarAsientoItem(AsientoItem unaAsientoItem)throws AsientoItemException ;
	
	/**
	 * Obtiene una AsientoItem de la base de datos dado su id.
	 * @param id, Identificador de la AsientoItem buscada.
	 * @return La AsientoItem buscada.
	 */
	public AsientoItem leerAsientoItem(Long id) throws AsientoItemException;
	
	/**
	 * Borra un AsientoItem de la base de datos dado su id.
	 * @param id, Identificador de la AsientoItem.
	 */
	public void borrarAsientoItem(Long id) throws AsientoItemException;
	
	/**
	 * Borra una AsientoItem de la base de datos dado el mismo.
	 * @param unaAsientoItem, AsientoItem a borrar.
	 */
	public void borrarAsientoItem(AsientoItem unaAsientoItem) throws AsientoItemException;
	
	/**
	 * Actualiza una AsientoItem en la base de datos.
	 * @param unaAsientoItem, AsientoItem a actualizar.
	 */
	public void actualizarAsientoItem(AsientoItem unaAsientoItem) throws AsientoItemException;
	
	/**
	 * Obtiene una lista de todas las AsientoItems.
	 * @return Una lista de AsientoItems.
	 */
	public List getAsientoItemes() throws AsientoItemException;
	
	/**
	 * Obtiene una lista de todas los tipos de AsientoItems según el filtro.
	 * @return Una lista de AsientoItems.
	 */
	public List getAsientoItems(Filtro filtro) throws AsientoItemException;
	
}

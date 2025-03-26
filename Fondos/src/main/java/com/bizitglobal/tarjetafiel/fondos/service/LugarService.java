package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.LugarException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface LugarService {
	
	/**
	 * Graba una Lugar en la base de datos.
	 * @param unaLugar, Lugar a grabar.
	 */
	public void grabarLugar(Lugar unaLugar)throws LugarException ;
	
	/**
	 * Obtiene una Lugar de la base de datos dado su id.
	 * @param id, Identificador de la Lugar buscada.
	 * @return La Lugar buscada.
	 */
	public Lugar leerLugar(Long id) throws LugarException;
	
	/**
	 * Borra un Lugar de la base de datos dado su id.
	 * @param id, Identificador de la Lugar.
	 */
	public void borrarLugar(Long id) throws LugarException;
	
	/**
	 * Borra una Lugar de la base de datos dado el mismo.
	 * @param unaLugar, Lugar a borrar.
	 */
	public void borrarLugar(Lugar unaLugar) throws LugarException;
	
	/**
	 * Actualiza una Lugar en la base de datos.
	 * @param unaLugar, Lugar a actualizar.
	 */
	public void actualizarLugar(Lugar unaLugar) throws LugarException;
	
	/**
	 * Obtiene una lista de todas las Lugars.
	 * @return Una lista de Lugars.
	 */
	public List getLugares() throws LugarException;
	
	/**
	 * Obtiene una lista de todas los tipos de Lugars según el filtro.
	 * @return Una lista de Lugars.
	 */
	public List getLugars(Filtro filtro) throws LugarException;
	
}

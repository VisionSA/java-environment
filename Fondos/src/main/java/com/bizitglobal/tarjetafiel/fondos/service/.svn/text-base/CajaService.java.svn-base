package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Caja;

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface CajaService {
	
	/**
	 * Graba una Caja en la base de datos.
	 * @param unaCaja, Caja a grabar.
	 */
	public void grabarCaja(Caja unaCaja)throws CajaException ;
	
	/**
	 * Obtiene una Caja de la base de datos dado su id.
	 * @param id, Identificador de la Caja buscada.
	 * @return La Caja buscada.
	 */
	public Caja leerCaja(Long id) throws CajaException;
	
	/**
	 * Borra un Caja de la base de datos dado su id.
	 * @param id, Identificador de la Caja.
	 */
	public void borrarCaja(Long id) throws CajaException;
	
	/**
	 * Borra una Caja de la base de datos dado el mismo.
	 * @param unaCaja, Caja a borrar.
	 */
	public void borrarCaja(Caja unaCaja) throws CajaException;
	
	/**
	 * Actualiza una Caja en la base de datos.
	 * @param unaCaja, Caja a actualizar.
	 */
	public void actualizarCaja(Caja unaCaja) throws CajaException;
	
	/**
	 * Obtiene una lista de todas las Cajas.
	 * @return Una lista de Cajas.
	 */
	public List getCajas() throws CajaException;
	
	/**
	 * Obtiene una lista de todas los tipos de Cajas según el filtro.
	 * @return Una lista de Cajas.
	 */
	public List getCajas(Filtro filtro) throws CajaException;
	
}

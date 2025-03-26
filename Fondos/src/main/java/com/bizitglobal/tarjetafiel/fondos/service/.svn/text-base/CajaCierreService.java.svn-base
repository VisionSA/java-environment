package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaCierreException;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaCierre;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface CajaCierreService {
	
	/**
	 * Graba una CajaCierre en la base de datos.
	 * @param unaCajaCierre, CajaCierre a grabar.
	 */
	public void grabarCajaCierre(CajaCierre unaCajaCierre)throws CajaCierreException ;
	
	/**
	 * Obtiene una CajaCierre de la base de datos dado su id.
	 * @param id, Identificador de la CajaCierre buscada.
	 * @return La CajaCierre buscada.
	 */
	public CajaCierre leerCajaCierre(Long id) throws CajaCierreException;
	
	/**
	 * Borra un CajaCierre de la base de datos dado su id.
	 * @param id, Identificador de la CajaCierre.
	 */
	public void borrarCajaCierre(Long id) throws CajaCierreException;
	
	/**
	 * Borra una CajaCierre de la base de datos dado el mismo.
	 * @param unaCajaCierre, CajaCierre a borrar.
	 */
	public void borrarCajaCierre(CajaCierre unaCajaCierre) throws CajaCierreException;
	
	/**
	 * Actualiza una CajaCierre en la base de datos.
	 * @param unaCajaCierre, CajaCierre a actualizar.
	 */
	public void actualizarCajaCierre(CajaCierre unaCajaCierre) throws CajaCierreException;
	
	/**
	 * Obtiene una lista de todas las CajaCierres.
	 * @return Una lista de CajaCierres.
	 */
	public List getCajaCierrees() throws CajaCierreException;
	
	/**
	 * Obtiene una lista de todas los tipos de CajaCierres según el filtro.
	 * @return Una lista de CajaCierres.
	 */
	public List getCajaCierres(Filtro filtro) throws CajaCierreException;
	
}

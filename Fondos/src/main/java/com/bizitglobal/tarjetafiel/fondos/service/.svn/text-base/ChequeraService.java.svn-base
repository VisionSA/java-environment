package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeraException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Chequera;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ChequeraService {
	
	/**
	 * Graba una Chequera en la base de datos.
	 * @param unaChequera, Chequera a grabar.
	 */
	public void grabarChequera(Chequera unaChequera)throws ChequeraException ;
	
	/**
	 * Obtiene una Chequera de la base de datos dado su id.
	 * @param id, Identificador de la Chequera buscada.
	 * @return La Chequera buscada.
	 */
	public Chequera leerChequera(Long id) throws ChequeraException;
	
	/**
	 * Borra un Chequera de la base de datos dado su id.
	 * @param id, Identificador de la Chequera.
	 */
	public void borrarChequera(Long id) throws ChequeraException;
	
	/**
	 * Borra una Chequera de la base de datos dado el mismo.
	 * @param unaChequera, Chequera a borrar.
	 */
	public void borrarChequera(Chequera unaChequera) throws ChequeraException;
	
	/**
	 * Actualiza una Chequera en la base de datos.
	 * @param unaChequera, Chequera a actualizar.
	 */
	public void actualizarChequera(Chequera unaChequera) throws ChequeraException;
	
	/**
	 * Obtiene una lista de todas las Chequeras.
	 * @return Una lista de Chequeras.
	 */
	public List getChequeraes() throws ChequeraException;
	
	/**
	 * Obtiene una lista de todas los tipos de Chequeras según el filtro.
	 * @return Una lista de Chequeras.
	 */
	public List getChequeras(Filtro filtro) throws ChequeraException;
	
}

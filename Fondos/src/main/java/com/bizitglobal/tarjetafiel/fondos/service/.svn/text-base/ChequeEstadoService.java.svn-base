package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeEstadoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ChequeEstadoService {
	
	/**
	 * Graba una ChequeEstado en la base de datos.
	 * @param unaChequeEstado, ChequeEstado a grabar.
	 */
	public void grabarChequeEstado(ChequeEstado unaChequeEstado)throws ChequeEstadoException ;
	
	/**
	 * Obtiene una ChequeEstado de la base de datos dado su id.
	 * @param id, Identificador de la ChequeEstado buscada.
	 * @return La ChequeEstado buscada.
	 */
	public ChequeEstado leerChequeEstado(Long id) throws ChequeEstadoException;
	
	/**
	 * Borra un ChequeEstado de la base de datos dado su id.
	 * @param id, Identificador de la ChequeEstado.
	 */
	public void borrarChequeEstado(Long id) throws ChequeEstadoException;
	
	/**
	 * Borra una ChequeEstado de la base de datos dado el mismo.
	 * @param unaChequeEstado, ChequeEstado a borrar.
	 */
	public void borrarChequeEstado(ChequeEstado unaChequeEstado) throws ChequeEstadoException;
	
	/**
	 * Actualiza una ChequeEstado en la base de datos.
	 * @param unaChequeEstado, ChequeEstado a actualizar.
	 */
	public void actualizarChequeEstado(ChequeEstado unaChequeEstado) throws ChequeEstadoException;
	
	/**
	 * Obtiene una lista de todas las ChequeEstados.
	 * @return Una lista de ChequeEstados.
	 */
	public List getChequeEstadoes() throws ChequeEstadoException;
	
	/**
	 * Obtiene una lista de todas los tipos de ChequeEstados según el filtro.
	 * @return Una lista de ChequeEstados.
	 */
	public List getChequeEstados(Filtro filtro) throws ChequeEstadoException;
	
}

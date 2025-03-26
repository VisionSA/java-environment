package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;
import java.util.Map;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.interfaces.Paginacion;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeException;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ChequeService extends Paginacion  {
	
	/**
	 * Graba una Cheque en la base de datos.
	 * @param unaCheque, Cheque a grabar.
	 */
	public void grabarCheque(Cheque unaCheque)throws ChequeException ;
	
	/**
	 * Obtiene una Cheque de la base de datos dado su id.
	 * @param id, Identificador de la Cheque buscada.
	 * @return La Cheque buscada.
	 */
	public Cheque leerCheque(Long id) throws ChequeException;
	
	/**
	 * Borra un Cheque de la base de datos dado su id.
	 * @param id, Identificador de la Cheque.
	 */
	public void borrarCheque(Long id) throws ChequeException;
	
	/**
	 * Borra una Cheque de la base de datos dado el mismo.
	 * @param unaCheque, Cheque a borrar.
	 */
	public void borrarCheque(Cheque unaCheque) throws ChequeException;
	
	/**
	 * Actualiza una Cheque en la base de datos.
	 * @param unaCheque, Cheque a actualizar.
	 */
	public void actualizarCheque(Cheque unaCheque) throws ChequeException;
	
	/**
	 * Obtiene una lista de todas las Cheques.
	 * @return Una lista de Cheques.
	 */
	public List getChequees() throws ChequeException;
	
	/**
	 * Obtiene una lista de todas los tipos de Cheques según el filtro.
	 * @return Una lista de Cheques.
	 */
	public List getCheques(Filtro filtro) throws ChequeException;
	
	public Page getChequePage(final Filtro filtro, final int pageNumber, final int pageSize) throws ChequeException;
	
	public Map obtenerUpload(final String listINidCheque ) throws ChequeException;
	
	public void actualizarTodosProcesados(final String listINidCheque) throws ChequeException;
	
	public Long contarChequesPendiente(final Filtro filtro) throws ChequeException;
	
	public List getChequesByParam(BancoPropio bp, String chequeNumero, Short digValCheque) throws ChequeException;
}

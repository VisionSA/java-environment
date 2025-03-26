package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioException;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface BancoPropioService {
	
	/**
	 * Graba una BancoPropio en la base de datos.
	 * @param unaBancoPropio, BancoPropio a grabar.
	 */
	public void grabarBancoPropio(BancoPropio unaBancoPropio)throws BancoPropioException ;
	
	/**
	 * Obtiene una BancoPropio de la base de datos dado su id.
	 * @param id, Identificador de la BancoPropio buscada.
	 * @return La BancoPropio buscada.
	 */
	public BancoPropio leerBancoPropio(Long id) throws BancoPropioException;
	
	/**
	 * Borra un BancoPropio de la base de datos dado su id.
	 * @param id, Identificador de la BancoPropio.
	 */
	public void borrarBancoPropio(Long id) throws BancoPropioException;
	
	/**
	 * Borra una BancoPropio de la base de datos dado el mismo.
	 * @param unaBancoPropio, BancoPropio a borrar.
	 */
	public void borrarBancoPropio(BancoPropio unaBancoPropio) throws BancoPropioException;
	
	/**
	 * Actualiza una BancoPropio en la base de datos.
	 * @param unaBancoPropio, BancoPropio a actualizar.
	 */
	public void actualizarBancoPropio(BancoPropio unaBancoPropio) throws BancoPropioException;
	
	/**
	 * Obtiene una lista de todas las BancoPropios.
	 * @return Una lista de BancoPropios.
	 */
	public List getBancoPropios() throws BancoPropioException;
	
	/**
	 * Obtiene una lista de todas los tipos de BancoPropios según el filtro.
	 * @return Una lista de BancoPropios.
	 */
	public List getBancoPropios(Filtro filtro) throws BancoPropioException;
	
}

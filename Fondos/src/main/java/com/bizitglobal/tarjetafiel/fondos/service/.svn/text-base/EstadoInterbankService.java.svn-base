package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.EstadoInterbankException;
import com.bizitglobal.tarjetafiel.fondos.negocio.EstadoInterbank;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface EstadoInterbankService {
	
	/**
	 * Graba una EstadoInterbank en la base de datos.
	 * @param unaEstadoInterbank, EstadoInterbank a grabar.
	 */
	public void grabarEstadoInterbank(EstadoInterbank unaEstadoInterbank)throws EstadoInterbankException ;
	
	/**
	 * Obtiene una EstadoInterbank de la base de datos dado su id.
	 * @param id, Identificador de la EstadoInterbank buscada.
	 * @return La EstadoInterbank buscada.
	 */
	public EstadoInterbank leerEstadoInterbank(Long id) throws EstadoInterbankException;
	
	/**
	 * Borra un EstadoInterbank de la base de datos dado su id.
	 * @param id, Identificador de la EstadoInterbank.
	 */
	public void borrarEstadoInterbank(Long id) throws EstadoInterbankException;
	
	/**
	 * Borra una EstadoInterbank de la base de datos dado el mismo.
	 * @param unaEstadoInterbank, EstadoInterbank a borrar.
	 */
	public void borrarEstadoInterbank(EstadoInterbank unaEstadoInterbank) throws EstadoInterbankException;
	
	/**
	 * Actualiza una EstadoInterbank en la base de datos.
	 * @param unaEstadoInterbank, EstadoInterbank a actualizar.
	 */
	public void actualizarEstadoInterbank(EstadoInterbank unaEstadoInterbank) throws EstadoInterbankException;
	
	/**
	 * Obtiene una lista de todas las EstadoInterbanks.
	 * @return Una lista de EstadoInterbanks.
	 */
	public List getEstadoInterbankes() throws EstadoInterbankException;
	
	/**
	 * Obtiene una lista de todas los tipos de EstadoInterbanks según el filtro.
	 * @return Una lista de EstadoInterbanks.
	 */
	public List getEstadoInterbanks(Filtro filtro) throws EstadoInterbankException;
	
}

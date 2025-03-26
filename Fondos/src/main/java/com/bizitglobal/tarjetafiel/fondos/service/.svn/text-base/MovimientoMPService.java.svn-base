package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoMPException;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface MovimientoMPService {
	
	/**
	 * Graba una MovimientoMP en la base de datos.
	 * @param unaMovimientoMP, MovimientoMP a grabar.
	 */
	public void grabarMovimientoMP(MovimientoMP unaMovimientoMP)throws MovimientoMPException ;
	
	/**
	 * Obtiene una MovimientoMP de la base de datos dado su id.
	 * @param id, Identificador de la MovimientoMP buscada.
	 * @return La MovimientoMP buscada.
	 */
	public MovimientoMP leerMovimientoMP(Long id) throws MovimientoMPException;
	
	/**
	 * Borra un MovimientoMP de la base de datos dado su id.
	 * @param id, Identificador de la MovimientoMP.
	 */
	public void borrarMovimientoMP(Long id) throws MovimientoMPException;
	
	/**
	 * Borra una MovimientoMP de la base de datos dado el mismo.
	 * @param unaMovimientoMP, MovimientoMP a borrar.
	 */
	public void borrarMovimientoMP(MovimientoMP unaMovimientoMP) throws MovimientoMPException;
	
	/**
	 * Actualiza una MovimientoMP en la base de datos.
	 * @param unaMovimientoMP, MovimientoMP a actualizar.
	 */
	public void actualizarMovimientoMP(MovimientoMP unaMovimientoMP) throws MovimientoMPException;
	
	/**
	 * Obtiene una lista de todas las MovimientoMPs.
	 * @return Una lista de MovimientoMPs.
	 */
	public List getMovimientoMPes() throws MovimientoMPException;
	
	/**
	 * Obtiene una lista de todas los tipos de MovimientoMPs según el filtro.
	 * @return Una lista de MovimientoMPs.
	 */
	public List getMovimientoMPs(Filtro filtro) throws MovimientoMPException;
	
}

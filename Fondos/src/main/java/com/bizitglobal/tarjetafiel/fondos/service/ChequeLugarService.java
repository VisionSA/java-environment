package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeLugarException;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeLugar;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ChequeLugarService {
	
	/**
	 * Graba una ChequeLugar en la base de datos.
	 * @param unaChequeLugar, ChequeLugar a grabar.
	 */
	public void grabarChequeLugar(ChequeLugar unaChequeLugar)throws ChequeLugarException ;
	
	/**
	 * Obtiene una ChequeLugar de la base de datos dado su id.
	 * @param id, Identificador de la ChequeLugar buscada.
	 * @return La ChequeLugar buscada.
	 */
	public ChequeLugar leerChequeLugar(Long id) throws ChequeLugarException;
	
	/**
	 * Borra un ChequeLugar de la base de datos dado su id.
	 * @param id, Identificador de la ChequeLugar.
	 */
	public void borrarChequeLugar(Long id) throws ChequeLugarException;
	
	/**
	 * Borra una ChequeLugar de la base de datos dado el mismo.
	 * @param unaChequeLugar, ChequeLugar a borrar.
	 */
	public void borrarChequeLugar(ChequeLugar unaChequeLugar) throws ChequeLugarException;
	
	/**
	 * Actualiza una ChequeLugar en la base de datos.
	 * @param unaChequeLugar, ChequeLugar a actualizar.
	 */
	public void actualizarChequeLugar(ChequeLugar unaChequeLugar) throws ChequeLugarException;
	
	/**
	 * Obtiene una lista de todas las ChequeLugars.
	 * @return Una lista de ChequeLugars.
	 */
	public List getChequeLugares() throws ChequeLugarException;
	
	/**
	 * Obtiene una lista de todas los tipos de ChequeLugars según el filtro.
	 * @return Una lista de ChequeLugars.
	 */
	public List getChequeLugars(Filtro filtro) throws ChequeLugarException;
	
}

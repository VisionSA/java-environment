package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaArqueoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaArqueo;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface CajaArqueoService {
	
	/**
	 * Graba una CajaArqueo en la base de datos.
	 * @param unaCajaArqueo, CajaArqueo a grabar.
	 */
	public void grabarCajaArqueo(CajaArqueo unaCajaArqueo)throws CajaArqueoException ;
	
	/**
	 * Obtiene una CajaArqueo de la base de datos dado su id.
	 * @param id, Identificador de la CajaArqueo buscada.
	 * @return La CajaArqueo buscada.
	 */
	public CajaArqueo leerCajaArqueo(Long id) throws CajaArqueoException;
	
	/**
	 * Borra un CajaArqueo de la base de datos dado su id.
	 * @param id, Identificador de la CajaArqueo.
	 */
	public void borrarCajaArqueo(Long id) throws CajaArqueoException;
	
	/**
	 * Borra una CajaArqueo de la base de datos dado el mismo.
	 * @param unaCajaArqueo, CajaArqueo a borrar.
	 */
	public void borrarCajaArqueo(CajaArqueo unaCajaArqueo) throws CajaArqueoException;
	
	/**
	 * Actualiza una CajaArqueo en la base de datos.
	 * @param unaCajaArqueo, CajaArqueo a actualizar.
	 */
	public void actualizarCajaArqueo(CajaArqueo unaCajaArqueo) throws CajaArqueoException;
	
	/**
	 * Obtiene una lista de todas las CajaArqueos.
	 * @return Una lista de CajaArqueos.
	 */
	public List getCajaArqueoes() throws CajaArqueoException;
	
	/**
	 * Obtiene una lista de todas los tipos de CajaArqueos según el filtro.
	 * @return Una lista de CajaArqueos.
	 */
	public List getCajaArqueos(Filtro filtro) throws CajaArqueoException;
	
}

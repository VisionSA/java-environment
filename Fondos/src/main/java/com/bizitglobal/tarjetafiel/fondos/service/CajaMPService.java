package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaMPException;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaMP;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface CajaMPService {
	
	/**
	 * Graba una CajaMP en la base de datos.
	 * @param unaCajaMP, CajaMP a grabar.
	 */
	public void grabarCajaMP(CajaMP unaCajaMP)throws CajaMPException ;
	
	/**
	 * Obtiene una CajaMP de la base de datos dado su id.
	 * @param id, Identificador de la CajaMP buscada.
	 * @return La CajaMP buscada.
	 */
	public CajaMP leerCajaMP(Long id) throws CajaMPException;
	
	/**
	 * Borra un CajaMP de la base de datos dado su id.
	 * @param id, Identificador de la CajaMP.
	 */
	public void borrarCajaMP(Long id) throws CajaMPException;
	
	/**
	 * Borra una CajaMP de la base de datos dado el mismo.
	 * @param unaCajaMP, CajaMP a borrar.
	 */
	public void borrarCajaMP(CajaMP unaCajaMP) throws CajaMPException;
	
	/**
	 * Actualiza una CajaMP en la base de datos.
	 * @param unaCajaMP, CajaMP a actualizar.
	 */
	public void actualizarCajaMP(CajaMP unaCajaMP) throws CajaMPException;
	
	/**
	 * Obtiene una lista de todas las CajaMPs.
	 * @return Una lista de CajaMPs.
	 */
	public List getCajaMPes() throws CajaMPException;
	
	/**
	 * Obtiene una lista de todas los tipos de CajaMPs según el filtro.
	 * @return Una lista de CajaMPs.
	 */
	public List getCajaMPs(Filtro filtro) throws CajaMPException;
	
}

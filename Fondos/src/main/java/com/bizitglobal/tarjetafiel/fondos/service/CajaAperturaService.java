package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaAperturaException;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaApertura;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface CajaAperturaService {
	
	/**
	 * Graba una CajaApertura en la base de datos.
	 * @param unaCajaApertura, CajaApertura a grabar.
	 */
	public void grabarCajaApertura(CajaApertura unaCajaApertura)throws CajaAperturaException ;
	
	/**
	 * Obtiene una CajaApertura de la base de datos dado su id.
	 * @param id, Identificador de la CajaApertura buscada.
	 * @return La CajaApertura buscada.
	 */
	public CajaApertura leerCajaApertura(Long id) throws CajaAperturaException;
	
	/**
	 * Borra un CajaApertura de la base de datos dado su id.
	 * @param id, Identificador de la CajaApertura.
	 */
	public void borrarCajaApertura(Long id) throws CajaAperturaException;
	
	/**
	 * Borra una CajaApertura de la base de datos dado el mismo.
	 * @param unaCajaApertura, CajaApertura a borrar.
	 */
	public void borrarCajaApertura(CajaApertura unaCajaApertura) throws CajaAperturaException;
	
	/**
	 * Actualiza una CajaApertura en la base de datos.
	 * @param unaCajaApertura, CajaApertura a actualizar.
	 */
	public void actualizarCajaApertura(CajaApertura unaCajaApertura) throws CajaAperturaException;
	
	/**
	 * Obtiene una lista de todas las CajaAperturas.
	 * @return Una lista de CajaAperturas.
	 */
	public List getCajaAperturaes() throws CajaAperturaException;
	
	/**
	 * Obtiene una lista de todas los tipos de CajaAperturas según el filtro.
	 * @return Una lista de CajaAperturas.
	 */
	public List getCajaAperturas(Filtro filtro) throws CajaAperturaException;
	
	
	/**
	 * Obtiene los id de los ultimos cierre de caja.
	 * @return un String  de id CajaAperturas.
	 */
	public String ultimoCierreCajas(boolean esNvaApertura,String cajasAbiertas)  throws CajaAperturaException; 
	
	/**
	 * Obtiene los id de las ultimas aperturas de caja.
	 * @return un String  de id CajaAperturas.
	 */
	
	public String ultimaAperturaCajas()throws CajaAperturaException;  
	
	
	/**
	 * Obtiene los id de las cajas que nunca fueron abiertas.
	 * @return un String  de ids Cajas.
	 */
	public String getCajasSinAbrir() throws CajaAperturaException;

	
	
	
	/**
	 * Obtiene los id de  los cajeros q tienen actualmente una  caja asignada.
	 * @return un String  de ids cajeros.
	 */
	public String cajerosAsignadosUltimaAperturaCajas() throws CajaAperturaException;
		
	/**
	 * @id: 5953
	 * Method: getAperturaVigente
	 * Description: Busca la apertura vigente de una caja segun su id 
	 * @param idCaja
	 * @return Un CajaApertura, si no esta avierta retorn null
	 * @throws CajaAperturaException
	 */
	public CajaApertura getAperturaVigente(Long idCaja) throws CajaAperturaException;
	
}

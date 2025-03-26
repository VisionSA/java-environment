package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeHistorialException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeHistorialNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeHistorial;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ChequeHistorialService {
	
	/**
	 * Graba una ChequeHistorial en la base de datos.
	 * @param unaChequeHistorial, ChequeHistorial a grabar.
	 */
	public void grabarChequeHistorial(ChequeHistorial unaChequeHistorial)throws ChequeHistorialException ;
	
	public String buscarEmailComercio(final Long  codComercio) throws ChequeHistorialException;
	public String buscarEmailProveedor(final Long  codComercio) throws ChequeHistorialException;
	
	/**
	 * Obtiene una ChequeHistorial de la base de datos dado su id.
	 * @param id, Identificador de la ChequeHistorial buscada.
	 * @return La ChequeHistorial buscada.
	 */
	public ChequeHistorial leerChequeHistorial(Long id) throws ChequeHistorialException;
	
	/**
	 * Borra un ChequeHistorial de la base de datos dado su id.
	 * @param id, Identificador de la ChequeHistorial.
	 */
	public void borrarChequeHistorial(Long id) throws ChequeHistorialException;
	
	/**
	 * Borra una ChequeHistorial de la base de datos dado el mismo.
	 * @param unaChequeHistorial, ChequeHistorial a borrar.
	 */
	public void borrarChequeHistorial(ChequeHistorial unaChequeHistorial) throws ChequeHistorialException;
	
	/**
	 * Actualiza una ChequeHistorial en la base de datos.
	 * @param unaChequeHistorial, ChequeHistorial a actualizar.
	 */
	public void actualizarChequeHistorial(ChequeHistorial unaChequeHistorial) throws ChequeHistorialException;
	
	/**
	 * Obtiene una lista de todas las ChequeHistorials.
	 * @return Una lista de ChequeHistorials.
	 */
	public List getChequeHistoriales() throws ChequeHistorialException;
	
	/**
	 * Obtiene una lista de todas los tipos de ChequeHistorials según el filtro.
	 * @return Una lista de ChequeHistorials.
	 */
	public List getChequeHistorials(Filtro filtro) throws ChequeHistorialException;
	
//	public void validarEstadoChequesPropios();
	public void validarEstadoChequesPropios(Date fecha);
	
	public ChequeHistorial buscarUltimo(Cheque cheque) throws ChequeHistorialException ;
	
	public ChequeEstado getChequeEstadoByIdCheque(Long idCheque) throws ChequeHistorialNotFoundException, ChequeHistorialException;
	
}

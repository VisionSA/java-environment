package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.LoteInterbankException;
import com.bizitglobal.tarjetafiel.fondos.negocio.LoteInterbank;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface LoteInterbankService {
	
	/**
	 * Graba una LoteInterbank en la base de datos.
	 * @param unaLoteInterbank, LoteInterbank a grabar.
	 */
	public void grabarLoteInterbank(LoteInterbank unaLoteInterbank)throws LoteInterbankException ;
	
	/**
	 * Obtiene una LoteInterbank de la base de datos dado su id.
	 * @param id, Identificador de la LoteInterbank buscada.
	 * @return La LoteInterbank buscada.
	 */
	public LoteInterbank leerLoteInterbank(Long id) throws LoteInterbankException;
	
	/**
	 * Borra un LoteInterbank de la base de datos dado su id.
	 * @param id, Identificador de la LoteInterbank.
	 */
	public void borrarLoteInterbank(Long id) throws LoteInterbankException;
	
	/**
	 * Borra una LoteInterbank de la base de datos dado el mismo.
	 * @param unaLoteInterbank, LoteInterbank a borrar.
	 */
	public void borrarLoteInterbank(LoteInterbank unaLoteInterbank) throws LoteInterbankException;
	
	/**
	 * Actualiza una LoteInterbank en la base de datos.
	 * @param unaLoteInterbank, LoteInterbank a actualizar.
	 */
	public void actualizarLoteInterbank(LoteInterbank unaLoteInterbank) throws LoteInterbankException;
	
	/**
	 * Obtiene una lista de todas las LoteInterbanks.
	 * @return Una lista de LoteInterbanks.
	 */
	public List getLoteInterbankes() throws LoteInterbankException;
	
	/**
	 * Obtiene una lista de todas los tipos de LoteInterbanks según el filtro.
	 * @return Una lista de LoteInterbanks.
	 */
	public List getLoteInterbanks(Filtro filtro) throws LoteInterbankException;

	/**
	 * Obtiene una lista de todas los tipos de LoteInterbanks según el la fechaGenerado,
	 * , fechaSolicitud y idBanco
	 * @return Una lista de LoteInterbanks.
	 */
	public List getLoteInterbanks(Date fechaGenerado,Date fechaSolicitud, Long idBanco) throws LoteInterbankException;
	/**
	 * Obtiene una lista de todas los LOTE_INTERBANK y LOTE_INTERBANK  según el id_lote_interbank,
	 * @return Una lista de LoteInterbanks.
	 * @throws LoteInterbankException 
	 */
	public List generarlistaInterbank(Long id_lote_interbank) throws LoteInterbankException;
	
}

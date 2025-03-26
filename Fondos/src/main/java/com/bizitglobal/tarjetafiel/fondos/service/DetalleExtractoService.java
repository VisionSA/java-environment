package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.DetalleExtractoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface DetalleExtractoService {
	
	/**
	 * Graba una DetalleExtracto en la base de datos.
	 * @param unaDetalleExtracto, DetalleExtracto a grabar.
	 */
	public void grabarDetalleExtracto(DetalleExtracto unaDetalleExtracto)throws DetalleExtractoException ;
	
	/**
	 * Obtiene una DetalleExtracto de la base de datos dado su id.
	 * @param id, Identificador de la DetalleExtracto buscada.
	 * @return La DetalleExtracto buscada.
	 */
	public DetalleExtracto leerDetalleExtracto(Long id) throws DetalleExtractoException;
	
	/**
	 * Borra un DetalleExtracto de la base de datos dado su id.
	 * @param id, Identificador de la DetalleExtracto.
	 */
	public void borrarDetalleExtracto(Long id) throws DetalleExtractoException;
	
	/**
	 * Borra una DetalleExtracto de la base de datos dado el mismo.
	 * @param unaDetalleExtracto, DetalleExtracto a borrar.
	 */
	public void borrarDetalleExtracto(DetalleExtracto unaDetalleExtracto) throws DetalleExtractoException;
	
	/**
	 * Actualiza una DetalleExtracto en la base de datos.
	 * @param unaDetalleExtracto, DetalleExtracto a actualizar.
	 */
	public void actualizarDetalleExtracto(DetalleExtracto unaDetalleExtracto) throws DetalleExtractoException;
	
	/**
	 * Obtiene una lista de todas las DetalleExtractos.
	 * @return Una lista de DetalleExtractos.
	 */
	public List getDetalleExtractoes() throws DetalleExtractoException;
	
	/**
	 * Obtiene una lista de todas los tipos de DetalleExtractos según el filtro.
	 * @return Una lista de DetalleExtractos.
	 */
	public List getDetalleExtractos(Filtro filtro) throws DetalleExtractoException;
	
	public List getDetalleExtractosFlex(Filtro filtro) throws DetalleExtractoException;
	
	public double saldoBancoFechaCorte(final Long idBancoPropio, final Date fechaCorte) throws DetalleExtractoException;
	
	public double saldoMovimientosNoConciliadosHastaFecha(final Long idBancoPropio, final Date fechaCorte) throws DetalleExtractoException;
}

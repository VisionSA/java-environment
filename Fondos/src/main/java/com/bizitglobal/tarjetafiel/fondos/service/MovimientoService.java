package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.interfaces.Paginacion;
import com.bizitglobal.tarjetafiel.commons.paginacion.Paginador;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

/*import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.exception.MonedaException;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;*/

/**
 * @author Daniel
 * Interface de servicios para las formas de pago del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface MovimientoService extends Paginacion {
	
	/**
	 * Graba una Movimiento en la base de datos.
	 * @param unaMovimiento, Movimiento a grabar.
	 */
	public void grabarMovimiento(Movimiento unaMovimiento)throws MovimientoException ;
	
	/**
	 * Obtiene una Movimiento de la base de datos dado su id.
	 * @param id, Identificador de la Movimiento buscada.
	 * @return La Movimiento buscada.
	 */
	public Movimiento leerMovimiento(Long id) throws MovimientoException;
	
	/**
	 * Borra un Movimiento de la base de datos dado su id.
	 * @param id, Identificador de la Movimiento.
	 */
	public void borrarMovimiento(Long id) throws MovimientoException;
	
	/**
	 * Borra una Movimiento de la base de datos dado el mismo.
	 * @param unaMovimiento, Movimiento a borrar.
	 */
	public void borrarMovimiento(Movimiento unaMovimiento) throws MovimientoException;
	
	/**
	 * Actualiza una Movimiento en la base de datos.
	 * @param unaMovimiento, Movimiento a actualizar.
	 */
	public void actualizarMovimiento(Movimiento unaMovimiento) throws MovimientoException;
	
	/**
	 * Obtiene una lista de todas las Movimientos.
	 * @return Una lista de Movimientos.
	 */
	public List getMovimientoes() throws MovimientoException;
	
	/**
	 * Obtiene una lista de todas los tipos de Movimientos según el filtro.
	 * @return Una lista de Movimientos.
	 */
	public List getMovimientos(Filtro filtro) throws MovimientoException;
	
	/**
	 * Obtiene una lista de todas los Movimientos según el filtro paginado.
	 * @return Una lista de Movimientos.
	 */
	public Paginador getMovimientosPagina(final Filtro filtro, final Paginador paginador) throws MovimientoException;
	
	public Page getMovimientosPage(final Filtro filtro, final int pageNumber, final int pageSize) throws MovimientoException;
	
	public String generarReversion(Long movOriginal, Date fechaAsiento, ConceptoGen concepto, Operador operador)throws MovimientoException;
	
}

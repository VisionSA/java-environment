package com.bizitglobal.tarjetafiel.proveedores.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Comprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ComprobantesNoCancelados;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;

/**
 * @author Daniel
 * Interface de servicios para los comprobantes del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ComprobanteService {
	
	/**
	 * Graba un comprobante en la base de datos.
	 * @param unComprobante, Comprobante a grabar.
	 */
	public void grabarComprobante(Comprobante unComprobante) throws ComprobanteException;
	
	/**
	 * Obtiene un comprobante de la base de datos dado su id.
	 * @param id, Identificador del comprobante buscado.
	 * @return El banco buscado.
	 */
	public Comprobante leerComprobante(Long id) throws ComprobanteException;
	
	/**
	 * Borra un comprobante de la base de datos dado su id.
	 * @param id, Identificador del comprobante.
	 */
	public void borrarComprobante(Long id) throws ComprobanteException;
	
	/**
	 * Borra un comprobante de la base de datos dado el mismo.
	 * @param unComprobante, Comprobante a borrar.
	 */
	public void borrarComprobante(Comprobante unComprobante) throws ComprobanteException;
	
	/**
	 * Actualiza un comprobante en la base de datos.
	 * @param unComprobante, comprobante a actualizar.
	 */
	public void actualizarComprobante(Comprobante unComprobante) throws ComprobanteException;
	
	/**
	 * Obtiene una lista de todos los comprobantes.
	 * @param filtro, Filtro a aplicar a la busqueda.
	 * @return Una lista de comprobantes.
	 */
	public List getComprobantes(Filtro filtro) throws ComprobanteException;
	
	public Long buscarNro(Integer nroCorto, Integer nroLargo, Proveedor proveedor) throws ComprobanteException;
	
	/**
	 * Obtiene un objeto que contiene una lista de comprobantes no cancelados y
	 * una lista de cuotas dado un Proveedor.
	 * @param unProveedor, proveedor del cual se quieren obtener los comprobantes.
	 * @return un objeto ComprobantesNoCancelados.
	 * @throws ComprobanteException se lanza si existe un error al obtener la lista de comprobantes.
	 */
	public ComprobantesNoCancelados getComprobantesNoCancelados(Long idProveedor) throws ComprobanteException;
	
	
	public ComprobantesNoCancelados getComprobantesNoCanceladosSec(Long idProveedor) throws ComprobanteException;

	
	public ComprobantesNoCancelados getOrdenesNoCanceladasSec(Long idProveedor) throws ComprobanteException;
	
}

package com.bizitglobal.tarjetafiel.proveedores.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.proveedores.exception.CuotaComprobanteException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuotaComprobante;

/**
 * @author Daniel
 * Interface de servicios para la cuota de comprobantes del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface CuotaComprobanteService {
	
	/**
	 * Graba una cuota comprobante en la base de datos.
	 * @param unCuotaComprobante, CuotaComprobante a grabar.
	 */
	public void grabarCuotaComprobante(CuotaComprobante unaCuotaComprobante) throws CuotaComprobanteException;
	
	/**
	 * Obtiene una cuota comprobante de la base de datos dado su id.
	 * @param id, Identificador de la cuota comprobante buscado.
	 * @return La cuota comprobante buscada.
	 */
	public CuotaComprobante leerCuotaComprobante(Long id) throws CuotaComprobanteException;
	
	/**
	 * Borra una cuota comprobante de la base de datos dado su id.
	 * @param id, Identificador de la cuota comprobante.
	 */
	public void borrarCuotaComprobante(Long id) throws CuotaComprobanteException;
	
	/**
	 * Borra una cuota comprobante de la base de datos dado el mismo.
	 * @param unaCuotaComprobante, CuotaComprobante a borrar.
	 */
	public void borrarCuotaComprobante(CuotaComprobante unaCuotaComprobante) throws CuotaComprobanteException;
	
	/**
	 * Actualiza una cuota comprobante en la base de datos.
	 * @param unaCuotaComprobante, CuotaComprobante a actualizar.
	 */
	public void actualizarCuotaComprobante(CuotaComprobante unaCuotaComprobante) throws CuotaComprobanteException;
	
	/**
	 * Obtiene una lista de todos las cuota comprobante.
	 * @return Una lista de cuotas comprobantes.
	 */
	public List getCuotaComprobante() throws CuotaComprobanteException;
	
	/**
	 * Retorna la cantidad de registros por cuit.
	 * @param cuit
	 * @return Cantidad de registros por cuit.
	 * @throws CuotaComprobanteException
	 */
	public Long countCuotaComprobante(String cuit) throws CuotaComprobanteException;
	
}

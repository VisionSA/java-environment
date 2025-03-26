package com.bizitglobal.tarjetafiel.proveedores.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorFormaPagoException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorFormaPago;

/**
 * @author Daniel
 * Interface de servicios para los comprobantes imputados del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ProveedorFormaPagoService {
	
	/**
	 * Graba un comprobante imputado en la base de datos.
	 * @param unProveedorFormaPago, ProveedorFormaPago a grabar.
	 */
	public void grabarProveedorFormaPago(ProveedorFormaPago unProveedorFormaPago) 
			throws ProveedorFormaPagoException;
	
	/**
	 * Obtiene un comprobante imputado de la base de datos dado su id.
	 * @param id, Identificador del comprobante buscado.
	 * @return El banco buscado.
	 */
	public ProveedorFormaPago leerProveedorFormaPago(Integer id) 
			throws ProveedorFormaPagoException;
	
	/**
	 * Borra un comprobante imputado de la base de datos dado su id.
	 * @param id, Identificador del comprobante.
	 */
	public void borrarProveedorFormaPago(Integer id) throws ProveedorFormaPagoException;
	
	/**
	 * Borra un comprobante imputado de la base de datos dado el mismo.
	 * @param unProveedorFormaPago, Comprobante imputado a borrar.
	 */
	public void borrarProveedorFormaPago(ProveedorFormaPago unProveedorFormaPago) 
			throws ProveedorFormaPagoException;
	
	/**
	 * Actualiza un comprobante imputado en la base de datos.
	 * @param unProveedorFormaPago, comprobante imputado a actualizar.
	 */
	public void actualizarProveedorFormaPago(ProveedorFormaPago unProveedorFormaPago) 
			throws ProveedorFormaPagoException;
	
	/**
	 * Obtiene una lista de todas las formas de pago.
	 * @return Una lista de formas de pago.
	 * @param filtro, 
	 */
	public List getProveedorFormaPagos(Filtro filtro) throws ProveedorFormaPagoException;
	
}

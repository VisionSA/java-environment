package com.bizitglobal.tarjetafiel.proveedores.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.exception.ComprobanteImputadoException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ComprobanteImputado;

/**
 * @author Daniel
 * Interface de servicios para los comprobantes imputados del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ComprobanteImputadoService {
	
	/**
	 * Graba un comprobante imputado en la base de datos.
	 * @param unComprobanteImputado, ComprobanteImputado a grabar.
	 */
	public void grabarComprobanteImputado(ComprobanteImputado unComprobanteImputado) 
			throws ComprobanteImputadoException;
	
	/**
	 * Obtiene un comprobante imputado de la base de datos dado su id.
	 * @param id, Identificador del comprobante buscado.
	 * @return El banco buscado.
	 */
	public ComprobanteImputado leerComprobanteImputado(Long id) 
			throws ComprobanteImputadoException;
	
	/**
	 * Borra un comprobante imputado de la base de datos dado su id.
	 * @param id, Identificador del comprobante.
	 */
	public void borrarComprobanteImputado(Long id) throws ComprobanteImputadoException;
	
	/**
	 * Borra un comprobante imputado de la base de datos dado el mismo.
	 * @param unComprobanteImputado, Comprobante imputado a borrar.
	 */
	public void borrarComprobanteImputado(ComprobanteImputado unComprobanteImputado) 
			throws ComprobanteImputadoException;
	
	/**
	 * Actualiza un comprobante imputado en la base de datos.
	 * @param unComprobanteImputado, comprobante imputado a actualizar.
	 */
	public void actualizarComprobanteImputado(ComprobanteImputado unComprobanteImputado) 
			throws ComprobanteImputadoException;
	
	/**
	 * Obtiene una lista de todos los comprobantes imputados.
	 * @return Una lista de comprobantes imputados.
	 */
	public List getComprobantesImputados(Filtro filtro) throws ComprobanteImputadoException;
	
}

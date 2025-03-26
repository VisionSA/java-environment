package com.bizitglobal.tarjetafiel.impuestos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;

/**
 * @author Daniel
 * Interface de servicios para los impuestos del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ImpuestoService {
	
	/**
	 * Graba un impuesto en la base de datos.
	 * @param unaImpuesto, Impuesto a grabar.
	 */
	public void grabarImpuesto(Impuesto unImpuesto) throws ImpuestoException;
	
	/**
	 * Obtiene un impuesto de la base de datos dado su id.
	 * @param id, Identificador del impuesto buscado.
	 * @return El impuesto buscado.
	 */
	public Impuesto leerImpuesto(Long id) throws ImpuestoException;
	
	/**
	 * Borra un impuesto de la base de datos dado su id.
	 * @param id, Identificador del impuesto.
	 */
	public void borrarImpuesto(Long id) throws ImpuestoException;
	
	/**
	 * Borra un impuesto de la base de datos dado el mismo.
	 * @param unImpuesto, Impuesto a borrar.
	 */
	public void borrarImpuesto(Impuesto unImpuesto) throws ImpuestoException;
	
	/**
	 * Actualiza un impuesto en la base de datos.
	 * @param unImpuesto, Impuesto a actualizar.
	 */
	public void actualizarImpuesto(Impuesto unImpuesto) throws ImpuestoException;
	
	/**
	 * Obtiene una lista de todos los impuestos.
	 * @return Una lista de impuestos.
	 */
	public List getImpuestos(Filtro filtro) throws ImpuestoException;
	
}

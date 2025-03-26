package com.bizitglobal.tarjetafiel.impuestos.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestosIndividuoException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;
import com.bizitglobal.tarjetafiel.impuestos.negocio.ImpuestosIndividuo;

/**
 * @author Daniel
 * Interface de servicios para los impuestos del sistema. Define un conjunto de puntos de entrada
 * para utilizarse en la capa de presentación.
 */
public interface ImpuestosIndividuoService {

	
	/**
	 * Graba un impuesto en la base de datos.
	 * @param unaImpuesto, Impuesto a grabar.
	 */
	public void grabarImpuestosIndividuo(ImpuestosIndividuo unImpuestosIndividuo) throws ImpuestosIndividuoException;
	
	/**
	 * Obtiene un impuesto de la base de datos dado su id.
	 * @param id, Identificador del impuesto buscado.
	 * @return El impuesto buscado.
	 */
	public ImpuestosIndividuo leerImpuestosIndividuo(Long id) throws ImpuestosIndividuoException;
	
	/**
	 * Borra un impuesto de la base de datos dado su id.
	 * @param id, Identificador del impuesto.
	 */
	public void borrarImpuestosIndividuo(Long id) throws ImpuestosIndividuoException;
	
	/**
	 * Borra un impuesto de la base de datos dado el mismo.
	 * @param unImpuesto, Impuesto a borrar.
	 */
	public void borrarImpuestosIndividuo(ImpuestosIndividuo unImpuestosIndividuo) throws ImpuestosIndividuoException;
	
	/**
	 * Obtiene una lista de todos los impuestos.
	 * @return Una lista de impuestos.
	 */
	public List getImpuestos(Filtro filtro) throws ImpuestosIndividuoException;
	
}

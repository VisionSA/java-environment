package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoLaboralException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ObservoLaboral;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface ObservoLaboralService {
	/**
	* Graba una ObservoLaboral en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarObservoLaboral(ObservoLaboral pObject) throws ObservoLaboralException;
	/**
	* Obtiene una ObservoLaboral de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public ObservoLaboral leerObservoLaboral(Long id) throws ObservoLaboralException;
	/**
	* Borra una ObservoLaboral de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarObservoLaboral(Long id) throws ObservoLaboralException;
	/**
	* Borra una ObservoLaboral de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarObservoLaboral(ObservoLaboral pObject) throws ObservoLaboralException;
	/**
	* Actualiza una ObservoLaboral de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarObservoLaboral(ObservoLaboral pObject) throws ObservoLaboralException;
	/**
	* Obtiene una lista de todas las ObservoLaboral de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getObservoLaboral(Filtro filtro) throws ObservoLaboralException;
}


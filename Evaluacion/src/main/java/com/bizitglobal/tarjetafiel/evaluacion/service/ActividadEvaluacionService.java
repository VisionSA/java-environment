package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ActividadEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ActividadEvaluacion;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface ActividadEvaluacionService {
	/**
	* Graba una ActividadEvaluacion en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarActividad (ActividadEvaluacion pObject) throws ActividadEvaluacionException;
	/**
	* Obtiene una ActividadEvaluacion de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public ActividadEvaluacion leerActividad (Long id) throws ActividadEvaluacionException;
	/**
	* Borra una ActividadEvaluacion de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarActividad (Long id) throws ActividadEvaluacionException;
	/**
	* Borra una ActividadEvaluacion de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarActividad (ActividadEvaluacion pObject) throws ActividadEvaluacionException;
	/**
	* Actualiza una ActividadEvaluacion de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarActividad (ActividadEvaluacion pObject) throws ActividadEvaluacionException;
	/**
	* Obtiene una lista de todas las ActividadEvaluacion de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getActividad(Filtro filtro) throws ActividadEvaluacionException;
}


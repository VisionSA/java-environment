package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoEvaluacionException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.general.negocio.Empresa;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface IndividuoEvaluacionService {
	/**
	* Graba una IndividuoEvaluacion en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarIndividuo (IndividuoEvaluacion pObject) throws IndividuoEvaluacionException;
	/**
	* Obtiene una IndividuoEvaluacion de la base de datos dado su id.0
	* @param id, Identificador del objeto buscado.0
	*/
	public IndividuoEvaluacion leerIndividuo (Long id) throws IndividuoEvaluacionException;
	/**
	* Borra una IndividuoEvaluacion de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarIndividuo (Long id) throws IndividuoEvaluacionException;
	/**
	* Borra una IndividuoEvaluacion de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarIndividuo (IndividuoEvaluacion pObject) throws IndividuoEvaluacionException;
	/**
	* Actualiza una IndividuoEvaluacion de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarIndividuo (IndividuoEvaluacion pObject) throws IndividuoEvaluacionException;
	/**
	* Obtiene una lista de todas las IndividuoEvaluacion de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getIndividuo(Filtro filtro) throws IndividuoEvaluacionException;
	/**
	* Obtiene una IndividuoEvaluacion de la base de datos dado su cuil.
	* @param cuil, Identificador del individuo buscado.
	*/
	public IndividuoEvaluacion buscarIndividuo (String cuil) throws IndividuoEvaluacionException;
	
	public List<IndividuoEvaluacion> buscarIndividuosHabilitadosCuit(Empresa empresa) throws IndividuoEvaluacionException;
}


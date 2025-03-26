package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoDomicilioException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface IndividuoDomicilioService {
	/**
	* Graba una IndividuoDomicilio en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarIndividuoDomicilio (IndividuoDomicilio pObject) throws IndividuoDomicilioException;
	/**
	* Obtiene una IndividuoDomicilio de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public IndividuoDomicilio leerIndividuoDomicilio (Long id) throws IndividuoDomicilioException;
	/**
	* Borra una IndividuoDomicilio de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarIndividuoDomicilio (Long id) throws IndividuoDomicilioException;
	/**
	* Borra una IndividuoDomicilio de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarIndividuoDomicilio (IndividuoDomicilio pObject) throws IndividuoDomicilioException;
	/**
	* Actualiza una IndividuoDomicilio de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarIndividuoDomicilio (IndividuoDomicilio pObject) throws IndividuoDomicilioException;
	/**
	* Obtiene una lista de todas las IndividuoDomicilio de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getIndividuoDomicilio(Filtro filtro) throws IndividuoDomicilioException;
	
	
	public Domicilio getDomicilioByIdIndividuo(Long idIndividuo) throws IndividuoDomicilioException;
	
}


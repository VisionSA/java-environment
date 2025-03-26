package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.IndividuoVehiculoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoVehiculo;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface IndividuoVehiculoService {
	/**
	* Graba una IndividuoVehiculo en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarIndividuoVehiculo (IndividuoVehiculo pObject) throws IndividuoVehiculoException;
	/**
	* Obtiene una IndividuoVehiculo de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public IndividuoVehiculo leerIndividuoVehiculo (Long id) throws IndividuoVehiculoException;
	/**
	* Borra una IndividuoVehiculo de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarIndividuoVehiculo (Long id) throws IndividuoVehiculoException;
	/**
	* Borra una IndividuoVehiculo de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarIndividuoVehiculo (IndividuoVehiculo pObject) throws IndividuoVehiculoException;
	/**
	* Actualiza una IndividuoVehiculo de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarIndividuoVehiculo (IndividuoVehiculo pObject) throws IndividuoVehiculoException;
	/**
	* Obtiene una lista de todas las IndividuoVehiculo de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getIndividuoVehiculo(Filtro filtro) throws IndividuoVehiculoException;
}


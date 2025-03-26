package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InfoParticularVehiculoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InfoParticularVehiculo;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface InfoParticularVehiculoService {
	/**
	* Graba una InfoParticularVehiculo en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarInfoParticularVehiculo (InfoParticularVehiculo pObject) throws InfoParticularVehiculoException;
	/**
	* Obtiene una InfoParticularVehiculo de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public InfoParticularVehiculo leerInfoParticularVehiculo (Long id) throws InfoParticularVehiculoException;
	/**
	* Borra una InfoParticularVehiculo de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarInfoParticularVehiculo (Long id) throws InfoParticularVehiculoException;
	/**
	* Borra una InfoParticularVehiculo de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarInfoParticularVehiculo (InfoParticularVehiculo pObject) throws InfoParticularVehiculoException;
	/**
	* Actualiza una InfoParticularVehiculo de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarInfoParticularVehiculo (InfoParticularVehiculo pObject) throws InfoParticularVehiculoException;
	/**
	* Obtiene una lista de todas las InfoParticularVehiculo de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getInfoParticularVehiculo(Filtro filtro) throws InfoParticularVehiculoException;
}


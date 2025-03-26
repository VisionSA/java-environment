package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoSucursalException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ObservoSucursal;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface ObservoSucursalService {
	/**
	* Graba una ObservoSucursal en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarObservoSucursal(ObservoSucursal pObject) throws ObservoSucursalException;
	/**
	* Obtiene una ObservoSucursal de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public ObservoSucursal leerObservoSucursal(Long id) throws ObservoSucursalException;
	/**
	* Borra una ObservoSucursal de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarObservoSucursal(Long id) throws ObservoSucursalException;
	/**
	* Borra una ObservoSucursal de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarObservoSucursal(ObservoSucursal pObject) throws ObservoSucursalException;
	/**
	* Actualiza una ObservoSucursal de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarObservoSucursal(ObservoSucursal pObject) throws ObservoSucursalException;
	/**
	* Obtiene una lista de todas las ObservoSucursal de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getObservoSucursal(Filtro filtro) throws ObservoSucursalException;
}


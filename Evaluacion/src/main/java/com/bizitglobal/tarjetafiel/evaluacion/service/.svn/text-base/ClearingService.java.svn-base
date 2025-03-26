package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ClearingException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Clearing;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface ClearingService {
	/**
	* Graba una Clearing en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarClearing (Clearing pObject) throws ClearingException;
	/**
	* Obtiene una Clearing de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Clearing leerClearing (Long id) throws ClearingException;
	/**
	* Borra una Clearing de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarClearing (Long id) throws ClearingException;
	/**
	* Borra una Clearing de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarClearing (Clearing pObject) throws ClearingException;
	/**
	* Actualiza una Clearing de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarClearing (Clearing pObject) throws ClearingException;
	/**
	* Obtiene una lista de todas las Clearing de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getClearing(Filtro filtro) throws ClearingException;
}


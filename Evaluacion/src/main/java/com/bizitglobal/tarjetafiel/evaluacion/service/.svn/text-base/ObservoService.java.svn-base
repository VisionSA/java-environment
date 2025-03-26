package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Observo;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface ObservoService {
	/**
	* Graba una Observo en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarObservo(Observo pObject) throws ObservoException;
	/**
	* Obtiene una Observo de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Observo leerObservo(Long id) throws ObservoException;
	/**
	* Borra una Observo de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarObservo(Long id) throws ObservoException;
	/**
	* Borra una Observo de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarObservo(Observo pObject) throws ObservoException;
	/**
	* Actualiza una Observo de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarObservo(Observo pObject) throws ObservoException;
	/**
	* Obtiene una lista de todas las Observo de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getObservo(Filtro filtro) throws ObservoException;
}


package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EstadosException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Estados;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface EstadosService {
	/**
	* Graba una Estados en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarEstados (Estados pObject) throws EstadosException;
	/**
	* Obtiene una Estados de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Estados leerEstados (Long id) throws EstadosException;
	/**
	* Borra una Estados de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarEstados (Long id) throws EstadosException;
	/**
	* Borra una Estados de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarEstados (Estados pObject) throws EstadosException;
	/**
	* Actualiza una Estados de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarEstados (Estados pObject) throws EstadosException;
	/**
	* Obtiene una lista de todas las Estados de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getEstados(Filtro filtro) throws EstadosException;
}


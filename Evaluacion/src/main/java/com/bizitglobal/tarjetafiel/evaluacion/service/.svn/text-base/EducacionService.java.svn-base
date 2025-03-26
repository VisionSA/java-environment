package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EducacionException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Educacion;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface EducacionService {
	/**
	* Graba una Educacion en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarEducacion (Educacion pObject) throws EducacionException;
	/**
	* Obtiene una Educacion de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Educacion leerEducacion (Long id) throws EducacionException;
	/**
	* Borra una Educacion de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarEducacion (Long id) throws EducacionException;
	/**
	* Borra una Educacion de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarEducacion (Educacion pObject) throws EducacionException;
	/**
	* Actualiza una Educacion de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarEducacion (Educacion pObject) throws EducacionException;
	/**
	* Obtiene una lista de todas las Educacion de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getEducacion(Filtro filtro) throws EducacionException;
}


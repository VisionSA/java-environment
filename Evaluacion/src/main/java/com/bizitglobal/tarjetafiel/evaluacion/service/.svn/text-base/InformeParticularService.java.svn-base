package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeParticularException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InformeParticular;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface InformeParticularService {
	/**
	* Graba una InformeParticular en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarInformeParticular (InformeParticular pObject) throws InformeParticularException;
	/**
	* Obtiene una InformeParticular de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public InformeParticular leerInformeParticular (Long id) throws InformeParticularException;
	/**
	* Borra una InformeParticular de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarInformeParticular (Long id) throws InformeParticularException;
	/**
	* Borra una InformeParticular de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarInformeParticular (InformeParticular pObject) throws InformeParticularException;
	/**
	* Actualiza una InformeParticular de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarInformeParticular (InformeParticular pObject) throws InformeParticularException;
	/**
	* Obtiene una lista de todas las InformeParticular de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getInformeParticular(Filtro filtro) throws InformeParticularException;
}


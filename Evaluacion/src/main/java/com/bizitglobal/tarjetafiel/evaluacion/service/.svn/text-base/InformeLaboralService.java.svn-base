package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.InformeLaboralException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InformeLaboral;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface InformeLaboralService {
	/**
	* Graba una InformeLaboral en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarInformeLaboral (InformeLaboral pObject) throws InformeLaboralException;
	/**
	* Obtiene una InformeLaboral de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public InformeLaboral leerInformeLaboral (Long id) throws InformeLaboralException;
	/**
	* Borra una InformeLaboral de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarInformeLaboral (Long id) throws InformeLaboralException;
	/**
	* Borra una InformeLaboral de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarInformeLaboral (InformeLaboral pObject) throws InformeLaboralException;
	/**
	* Actualiza una InformeLaboral de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarInformeLaboral (InformeLaboral pObject) throws InformeLaboralException;
	/**
	* Obtiene una lista de todas las InformeLaboral de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getInformeLaboral(Filtro filtro) throws InformeLaboralException;
}


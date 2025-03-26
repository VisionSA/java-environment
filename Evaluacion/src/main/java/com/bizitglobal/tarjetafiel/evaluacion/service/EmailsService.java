package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.EmailsException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Emails;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface EmailsService {
	/**
	* Graba una Emails en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarEmails (Emails pObject) throws EmailsException;
	/**
	* Obtiene una Emails de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Emails leerEmails (Long id) throws EmailsException;
	/**
	* Borra una Emails de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarEmails (Long id) throws EmailsException;
	/**
	* Borra una Emails de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarEmails (Emails pObject) throws EmailsException;
	/**
	* Actualiza una Emails de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarEmails (Emails pObject) throws EmailsException;
	/**
	* Obtiene una lista de todas las Emails de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getEmails(Filtro filtro) throws EmailsException;
}


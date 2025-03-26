package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.AlertaSolicitudException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.AlertaSolicitud;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface AlertaSolicitudService {
	/**
	* Graba una AlertaSolicitud en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarAlertaSolicitud (AlertaSolicitud pObject) throws AlertaSolicitudException;
	/**
	* Obtiene una AlertaSolicitud de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public AlertaSolicitud leerAlertaSolicitud (Long id) throws AlertaSolicitudException;
	/**
	* Borra una AlertaSolicitud de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarAlertaSolicitud (Long id) throws AlertaSolicitudException;
	/**
	* Borra una AlertaSolicitud de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarAlertaSolicitud (AlertaSolicitud pObject) throws AlertaSolicitudException;
	/**
	* Actualiza una AlertaSolicitud de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarAlertaSolicitud (AlertaSolicitud pObject) throws AlertaSolicitudException;
	/**
	* Obtiene una lista de todas las AlertaSolicitud de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getAlertaSolicitud(Filtro filtro) throws AlertaSolicitudException;
}


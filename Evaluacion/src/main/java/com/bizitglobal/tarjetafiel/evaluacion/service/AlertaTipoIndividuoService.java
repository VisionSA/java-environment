package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.AlertaTipoIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.AlertaTipoIndividuo;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface AlertaTipoIndividuoService {
	/**
	* Graba una AlertaTipoIndividuo en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarAlertaTipoIndividuo (AlertaTipoIndividuo pObject) throws AlertaTipoIndividuoException;
	/**
	* Obtiene una AlertaTipoIndividuo de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public AlertaTipoIndividuo leerAlertaTipoIndividuo (Long id) throws AlertaTipoIndividuoException;
	/**
	* Borra una AlertaTipoIndividuo de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarAlertaTipoIndividuo (Long id) throws AlertaTipoIndividuoException;
	/**
	* Borra una AlertaTipoIndividuo de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarAlertaTipoIndividuo (AlertaTipoIndividuo pObject) throws AlertaTipoIndividuoException;
	/**
	* Actualiza una AlertaTipoIndividuo de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarAlertaTipoIndividuo (AlertaTipoIndividuo pObject) throws AlertaTipoIndividuoException;
	/**
	* Obtiene una lista de todas las AlertaTipoIndividuo de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getAlertaTipoIndividuo(Filtro filtro) throws AlertaTipoIndividuoException;
}


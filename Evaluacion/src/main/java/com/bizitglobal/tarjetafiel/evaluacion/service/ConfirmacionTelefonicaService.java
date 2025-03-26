package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ConfirmacionTelefonicaException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ConfirmacionTelefonica;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface ConfirmacionTelefonicaService {
	/**
	* Graba una ConfirmacionTelefonica en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarConfirmacionTelefonica (ConfirmacionTelefonica pObject) throws ConfirmacionTelefonicaException;
	/**
	* Obtiene una ConfirmacionTelefonica de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public ConfirmacionTelefonica leerConfirmacionTelefonica (Long id) throws ConfirmacionTelefonicaException;
	/**
	* Borra una ConfirmacionTelefonica de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarConfirmacionTelefonica (Long id) throws ConfirmacionTelefonicaException;
	/**
	* Borra una ConfirmacionTelefonica de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarConfirmacionTelefonica (ConfirmacionTelefonica pObject) throws ConfirmacionTelefonicaException;
	/**
	* Actualiza una ConfirmacionTelefonica de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarConfirmacionTelefonica (ConfirmacionTelefonica pObject) throws ConfirmacionTelefonicaException;
	/**
	* Obtiene una lista de todas las ConfirmacionTelefonica de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getConfirmacionTelefonica(Filtro filtro) throws ConfirmacionTelefonicaException;
}


package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.VerificadorTelefonoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.VerificadorTelefono;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface VerificadorTelefonoService {
	/**
	* Graba una VerificadorTelefono en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarVerificadorTelefono(VerificadorTelefono pObject) throws VerificadorTelefonoException;
	/**
	* Obtiene una VerificadorTelefono de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public VerificadorTelefono leerVerificadorTelefono(Long id) throws VerificadorTelefonoException;
	/**
	* Borra una VerificadorTelefono de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarVerificadorTelefono(Long id) throws VerificadorTelefonoException;
	/**
	* Borra una VerificadorTelefono de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarVerificadorTelefono(VerificadorTelefono pObject) throws VerificadorTelefonoException;
	/**
	* Actualiza una VerificadorTelefono de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarVerificadorTelefono(VerificadorTelefono pObject) throws VerificadorTelefonoException;
	/**
	* Obtiene una lista de todas las VerificadorTelefono de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getVerificadorTelefono(Filtro filtro) throws VerificadorTelefonoException;
}


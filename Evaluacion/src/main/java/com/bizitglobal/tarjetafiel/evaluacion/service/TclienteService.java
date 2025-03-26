package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TclienteException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tcliente;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface TclienteService {
	/**
	* Graba una Tcliente en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarTcliente (Tcliente pObject) throws TclienteException;
	/**
	* Obtiene una Tcliente de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Tcliente leerTcliente (Long id) throws TclienteException;
	/**
	* Borra una Tcliente de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarTcliente (Long id) throws TclienteException;
	/**
	* Borra una Tcliente de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarTcliente (Tcliente pObject) throws TclienteException;
	/**
	* Actualiza una Tcliente de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarTcliente (Tcliente pObject) throws TclienteException;
	/**
	* Obtiene una lista de todas las Tcliente de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getTcliente(Filtro filtro) throws TclienteException;
}


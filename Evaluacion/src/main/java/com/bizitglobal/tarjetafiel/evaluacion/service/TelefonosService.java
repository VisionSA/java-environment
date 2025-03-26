package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TelefonosException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface TelefonosService {
	/**
	* Graba una Telefonos en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarTelefonos(Telefonos pObject) throws TelefonosException;
	/**
	* Obtiene una Telefonos de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Telefonos leerTelefonos(Long id) throws TelefonosException;
	/**
	* Borra una Telefonos de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarTelefonos(Long id) throws TelefonosException;
	/**
	* Borra una Telefonos de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarTelefonos(Telefonos pObject) throws TelefonosException;
	/**
	* Actualiza una Telefonos de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarTelefonos(Telefonos pObject) throws TelefonosException;
	/**
	* Obtiene una lista de todas las Telefonos de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getTelefonos(Filtro filtro) throws TelefonosException;
	
	public boolean tieneTelefono(Long idCliente);
}


package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.TarjetaException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tarjeta;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface TarjetaService {
	/**
	* Graba una Tarjeta en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarTarjeta(Tarjeta pObject) throws TarjetaException;
	/**
	* Obtiene una Tarjeta de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Tarjeta leerTarjeta(Long id) throws TarjetaException;
	/**
	* Borra una Tarjeta de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarTarjeta(Long id) throws TarjetaException;
	/**
	* Borra una Tarjeta de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarTarjeta(Tarjeta pObject) throws TarjetaException;
	/**
	* Actualiza una Tarjeta de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarTarjeta(Tarjeta pObject) throws TarjetaException;
	/**
	* Obtiene una lista de todas las Tarjeta de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getTarjeta(Filtro filtro) throws TarjetaException;
}


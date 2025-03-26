package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.sql.Timestamp;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.DigitalException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Digital;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface DigitalService {
	/**
	* Graba una Digital en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarDigital (Digital pObject) throws DigitalException;
	/**
	* Obtiene una Digital de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Digital leerDigital (Long id) throws DigitalException;
	/**
	* Borra una Digital de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarDigital (Long id) throws DigitalException;
	/**
	* Borra una Digital de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarDigital (Digital pObject) throws DigitalException;
	/**
	* Actualiza una Digital de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarDigital (Digital pObject) throws DigitalException;
	/**
	* Obtiene una lista de todas las Digital de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getDigital(Filtro filtro) throws DigitalException;
	
	public List buscarPorFecha(Timestamp desde,Timestamp hasta);
}


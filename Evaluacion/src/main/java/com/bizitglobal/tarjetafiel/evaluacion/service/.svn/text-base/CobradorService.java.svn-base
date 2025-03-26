package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.CobradorException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Cobrador;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface CobradorService {
	/**
	* Graba una Cobrador en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarCobrador(Cobrador pObject) throws CobradorException;
	/**
	* Obtiene una Cobrador de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Cobrador leerCobrador(Long id) throws CobradorException;
	/**
	* Borra una Cobrador de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarCobrador(Long id) throws CobradorException;
	/**
	* Borra una Cobrador de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarCobrador(Cobrador pObject) throws CobradorException;
	/**
	* Actualiza una Cobrador de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarCobrador(Cobrador pObject) throws CobradorException;
	/**
	* Obtiene una lista de todas las Cobrador de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getCobrador(Filtro filtro) throws CobradorException;
}


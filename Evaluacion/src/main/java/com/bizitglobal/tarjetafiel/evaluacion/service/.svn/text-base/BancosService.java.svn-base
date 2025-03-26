package com.bizitglobal.tarjetafiel.evaluacion.service;
import java.util.List;
import java.util.Iterator;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.BancosException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Bancos;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Evaluacion del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface BancosService {
	/**
	* Graba una Bancos en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarBancos (Bancos pObject) throws BancosException;
	/**
	* Obtiene una Bancos de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Bancos leerBancos (Long id) throws BancosException;
	/**
	* Borra una Bancos de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarBancos (Long id) throws BancosException;
	/**
	* Borra una Bancos de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarBancos (Bancos pObject) throws BancosException;
	/**
	* Actualiza una Bancos de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarBancos (Bancos pObject) throws BancosException;
	/**
	* Obtiene una lista de todas las Bancos de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getBancos(Filtro filtro) throws BancosException;
}


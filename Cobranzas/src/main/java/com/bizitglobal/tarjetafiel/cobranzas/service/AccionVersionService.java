package com.bizitglobal.tarjetafiel.cobranzas.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.cobranzas.exception.AccionVersionException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.AccionVersion;

public interface AccionVersionService {
	/**
	* Graba una AccionVersion en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarAccionVersion (AccionVersion pObject) throws AccionVersionException;
	/**
	* Obtiene una AccionVersion de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public AccionVersion leerAccionVersion (Long id) throws AccionVersionException;
	/**
	* Borra una AccionVersion de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarAccionVersion (Long id) throws AccionVersionException;
	/**
	* Borra una AccionVersion de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarAccionVersion (AccionVersion pObject) throws AccionVersionException;
	/**
	* Actualiza una AccionVersion de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarAccionVersion (AccionVersion pObject) throws AccionVersionException;
	/**
	* Obtiene una lista de todas las AccionVersion de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getAccionVersion(Filtro filtro) throws AccionVersionException;
}

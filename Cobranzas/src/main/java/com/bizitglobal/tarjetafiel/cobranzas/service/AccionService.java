package com.bizitglobal.tarjetafiel.cobranzas.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.cobranzas.exception.AccionException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Accion;

public interface AccionService {
	/**
	* Graba una Accion en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarAccion (Accion pObject) throws AccionException;
	/**
	* Obtiene una Accion de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Accion leerAccion (Long id) throws AccionException;
	/**
	* Borra una Accion de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarAccion (Long id) throws AccionException;
	/**
	* Borra una Accion de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarAccion (Accion pObject) throws AccionException;
	/**
	* Actualiza una Accion de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarAccion (Accion pObject) throws AccionException;
	/**
	* Obtiene una lista de todas las Accion de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getAccion(Filtro filtro) throws AccionException;
	
	
	public List listarAcciones() throws AccionException;
}

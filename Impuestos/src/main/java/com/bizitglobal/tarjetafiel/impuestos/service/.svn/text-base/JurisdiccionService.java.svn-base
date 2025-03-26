package com.bizitglobal.tarjetafiel.impuestos.service;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.exception.JurisdiccionException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;

/**
* @author BizItGlobal Generator
* Interface de servicios para las Impuesto del sistema. Define un conjunto de puntos de entrada
* para utilizarse en la capa de presentación.
*/
public interface JurisdiccionService {
	/**
	* Graba una Jurisdiccion en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarJurisdiccion (Jurisdiccion pObject) throws JurisdiccionException;
	/**
	* Obtiene una Jurisdiccion de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Jurisdiccion leerJurisdiccion (Long id) throws JurisdiccionException;
	/**
	* Borra una Jurisdiccion de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarJurisdiccion (Long id) throws JurisdiccionException;
	/**
	* Borra una Jurisdiccion de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarJurisdiccion (Jurisdiccion pObject) throws JurisdiccionException;
	/**
	* Actualiza una Jurisdiccion de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarJurisdiccion (Jurisdiccion pObject) throws JurisdiccionException;
	/**
	* Obtiene una lista de todas las Jurisdiccion de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getJurisdiccion(Filtro filtro) throws JurisdiccionException;
}


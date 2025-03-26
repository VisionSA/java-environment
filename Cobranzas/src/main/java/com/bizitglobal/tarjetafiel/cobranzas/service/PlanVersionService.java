package com.bizitglobal.tarjetafiel.cobranzas.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.cobranzas.exception.PlanVersionException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.PlanVersion;

public interface PlanVersionService {
	/**
	* Graba una PlanVersion en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarPlanVersion (PlanVersion pObject) throws PlanVersionException;
	/**
	* Obtiene una PlanVersion de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public PlanVersion leerPlanVersion (Long id) throws PlanVersionException;
	/**
	* Borra una PlanVersion de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarPlanVersion (Long id) throws PlanVersionException;
	/**
	* Borra una PlanVersion de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarPlanVersion (PlanVersion pObject) throws PlanVersionException;
	/**
	* Actualiza una PlanVersion de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarPlanVersion (PlanVersion pObject) throws PlanVersionException;
	/**
	* Obtiene una lista de todas las PlanVersion de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getPlanVersion(Filtro filtro) throws PlanVersionException;
	
	public List getPlanesVersionByFiltro(Filtro filtro) throws PlanVersionException;
}

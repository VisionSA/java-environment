package com.bizitglobal.tarjetafiel.cobranzas.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.cobranzas.exception.EtapaVersionException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EtapaVersion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.PlanVersion;

public interface EtapaVersionService {
	/**
	* Graba una EtapaVersion en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarEtapaVersion (EtapaVersion pObject) throws EtapaVersionException;
	/**
	* Obtiene una EtapaVersion de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public EtapaVersion leerEtapaVersion (Long id) throws EtapaVersionException;
	/**
	* Borra una EtapaVersion de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarEtapaVersion (Long id) throws EtapaVersionException;
	/**
	* Borra una EtapaVersion de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarEtapaVersion (EtapaVersion pObject) throws EtapaVersionException;
	/**
	* Actualiza una EtapaVersion de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarEtapaVersion (EtapaVersion pObject) throws EtapaVersionException;
	/**
	* Obtiene una lista de todas las EtapaVersion de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getEtapaVersion(Filtro filtro) throws EtapaVersionException;
	
	public List getEtapasVersionByFiltro(PlanVersion planVersion) throws EtapaVersionException;
	
	
}

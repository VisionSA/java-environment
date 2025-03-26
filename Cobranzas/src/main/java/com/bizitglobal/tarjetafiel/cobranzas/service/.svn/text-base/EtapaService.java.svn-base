package com.bizitglobal.tarjetafiel.cobranzas.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.cobranzas.exception.EtapaException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Etapa;

public interface EtapaService {
	/**
	* Graba una Etapa en la base de datos.
	* @param pObject, objeto a grabar.
	*/
	public void grabarEtapa (Etapa pObject) throws EtapaException;
	/**
	* Obtiene una Etapa de la base de datos dado su id.
	* @param id, Identificador del objeto buscado.
	*/
	public Etapa leerEtapa (Long id) throws EtapaException;
	/**
	* Borra una Etapa de la base de datos dado su id.
	* @param id, Identificador del objeto a Borrar.
	*/
	public void borrarEtapa (Long id) throws EtapaException;
	/**
	* Borra una Etapa de la base de datos dado el mismo.
	* @param pObject, Identificador del objeto a Borrar.
	*/
	public void borrarEtapa (Etapa pObject) throws EtapaException;
	/**
	* Actualiza una Etapa de la base de datos.
	* @param pObject, Identificador del objeto a Actualizar.
	*/
	public void actualizarEtapa (Etapa pObject) throws EtapaException;
	/**
	* Obtiene una lista de todas las Etapa de la base de datos.
	* @return Una lista de objetos.
	*/
	public List getEtapa(Filtro filtro) throws EtapaException;
	
	public List listarEtapas() throws EtapaException;
}

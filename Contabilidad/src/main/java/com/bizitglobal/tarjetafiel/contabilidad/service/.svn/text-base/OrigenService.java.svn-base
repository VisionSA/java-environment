package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.OrigenException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Origen;

public interface OrigenService {
		/**
		* Graba un Origen en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabarOrigen (Origen pObject) throws OrigenException;
		/**
		* Obtiene un Origen de la base de datos dado su id.
		* @param id, Identificador del objeto buscado.
		*/
		public Origen leerOrigen (Long id) throws OrigenException;
		/**
		* Borra un Origen de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrarOrigen (Long id) throws OrigenException;
		/**
		* Borra un Origen de la base de datos dado el mismo.
		* @param pObject, Identificador del objeto a Borrar.
		*/
		public void borrarOrigen (Origen pObject) throws OrigenException;
		/**
		* Actualiza un Origen de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarOrigen (Origen pObject) throws OrigenException;
		/**
		* Obtiene una lista de todas los Origen de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getOrigen(Filtro filtro) throws OrigenException;
	}


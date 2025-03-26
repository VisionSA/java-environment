package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostosException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.CentroCostos;

public interface CentroCostosService {
		/**
		* Graba un CentroCostos en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabarCentroCostos (CentroCostos pObject) throws CentroCostosException;
		/**
		* Obtiene un CentroCostos de la base de datos dado su id.
		* @param id, Identificador del objeto buscado.
		*/
		public CentroCostos leerCentroCostos (Long id) throws CentroCostosException;
		/**
		* Borra un CentroCostos de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrarCentroCostos (Long id) throws CentroCostosException;
		/**
		* Borra un CentroCostos de la base de datos dado el mismo.
		* @param pObject, Identificador del objeto a Borrar.
		*/
		public void borrarCentroCostos (CentroCostos pObject) throws CentroCostosException;
		/**
		* Actualiza un CentroCostos de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarCentroCostos (CentroCostos pObject) throws CentroCostosException;
		/**
		* Obtiene una lista de todas los CentroCostos de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getCentroCostos(Filtro filtro) throws CentroCostosException;
	}


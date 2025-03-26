package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.TipoAsientoException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.TipoAsiento;

public interface TipoAsientoService {
		/**
		* Graba un TipoAsiento en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabarTipoAsiento (TipoAsiento pObject) throws TipoAsientoException;
		/**
		* Obtiene un TipoAsiento de la base de datos dado su id.
		* @param id, Identificador del objeto buscado.
		*/
		public TipoAsiento leerTipoAsiento (Long id) throws TipoAsientoException;
		/**
		* Borra un TipoAsiento de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrarTipoAsiento (Long id) throws TipoAsientoException;
		/**
		* Borra un TipoAsiento de la base de datos dado el mismo.
		* @param pObject, Identificador del objeto a Borrar.
		*/
		public void borrarTipoAsiento (TipoAsiento pObject) throws TipoAsientoException;
		/**
		* Actualiza un TipoAsiento de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarTipoAsiento (TipoAsiento pObject) throws TipoAsientoException;
		/**
		* Obtiene una lista de todas los TipoAsiento de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getTipoAsiento(Filtro filtro) throws TipoAsientoException;
	}


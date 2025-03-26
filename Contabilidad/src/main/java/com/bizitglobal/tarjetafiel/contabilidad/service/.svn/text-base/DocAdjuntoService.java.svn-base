package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.DocAdjuntoException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.DocAdjunto;

public interface DocAdjuntoService {
		/**
		* Graba un DocAdjunto en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabarDocAdjunto (DocAdjunto pObject) throws DocAdjuntoException;
		/**
		* Obtiene un DocAdjunto de la base de datos dado su id.
		* @param id, Identificador del objeto buscado.
		*/
		public DocAdjunto leerDocAdjunto (Long id) throws DocAdjuntoException;
		/**
		* Borra un DocAdjunto de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrarDocAdjunto (Long id) throws DocAdjuntoException;
		/**
		* Borra un DocAdjunto de la base de datos dado el mismo.
		* @param pObject, Identificador del objeto a Borrar.
		*/
		public void borrarDocAdjunto (DocAdjunto pObject) throws DocAdjuntoException;
		/**
		* Actualiza un DocAdjunto de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarDocAdjunto (DocAdjunto pObject) throws DocAdjuntoException;
		/**
		* Obtiene una lista de todas los DocAdjunto de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getDocAdjunto(Filtro filtro) throws DocAdjuntoException;
	}


package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;;

public interface EjercicioService {
		/**
		* Graba un Ejercicio en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabarEjercicio (Ejercicio pObject) throws EjercicioException;
		/**
		* Obtiene un Ejercicio de la base de datos dado su id.
		* @param id, Identificador del objeto buscado.
		*/
		public Ejercicio leerEjercicio (Integer id) throws EjercicioException;
		/**
		* Borra un Ejercicio de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrarEjercicio (Integer id) throws EjercicioException;
		/**
		* Borra un Ejercicio de la base de datos dado el mismo.
		* @param pObject, Identificador del objeto a Borrar.
		*/
		public void borrarEjercicio (Ejercicio pObject) throws EjercicioException;
		/**
		* Actualiza un Ejercicio de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarEjercicio (Ejercicio pObject) throws EjercicioException;
		/**
		* Obtiene una lista de todas los Ejercicios de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getEjercicio(Filtro filtro) throws EjercicioException;
		/**
		* Obtiene un Ejercicio que se encuentra abierto y activo.
		*/
		public Ejercicio ejercicioActual () throws EjercicioException;
	}


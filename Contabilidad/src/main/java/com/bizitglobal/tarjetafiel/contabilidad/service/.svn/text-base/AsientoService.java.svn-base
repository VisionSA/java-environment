package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Asiento;;

public interface AsientoService {
		/**
		* Graba un Asiento en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabarAsiento (Asiento pObject) throws AsientoException;
		/**
		* Obtiene un Asiento de la base de datos dado su id.
		* @param id, Identificador del objeto buscado.
		*/
		public Asiento leerAsiento (Long id) throws AsientoException;
		/**
		* Borra un Asiento de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrarAsiento (Long id) throws AsientoException;
		/**
		* Borra un Asiento de la base de datos dado el mismo.
		* @param pObject, Identificador del objeto a Borrar.
		*/
		public void borrarAsiento (Asiento pObject) throws AsientoException;
		/**
		* Actualiza un Asiento de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarAsiento (Asiento pObject) throws AsientoException;
		/**
		* Obtiene una lista de todas los Asientos de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getAsiento(Filtro filtro) throws AsientoException;
		
		public List getAsientoConsultaManual(Filtro filtro) throws AsientoException;
		
//		obtener el maximo id para grabar
		public Long getLastIdDeAsientos(Long idEjercicio, Long idEmpresa);
		
		public Long contarAsientos(final Filtro filtro) throws AsientoException;
	}


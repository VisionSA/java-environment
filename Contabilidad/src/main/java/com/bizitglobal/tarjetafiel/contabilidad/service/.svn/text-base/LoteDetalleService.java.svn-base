package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;

public interface LoteDetalleService {
		/**
		* Graba un LoteDetalle en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabar(LoteDetalle pObject) throws LoteDetalleException;
		/**
		* Obtiene un LoteDetalle de la base de datos dado su id.
		* @param id, Identificador del objeto buscado.
		*/
//		public LoteDetalle leerLoteDetalle (Long id) throws LoteDetalleException;
		/**
		* Borra un LoteDetalle de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrar(Long idEjercicio, Long idEmpresa, Long idAsiento, Long renglon) throws LoteDetalleException;
		/**
		* Borra un LoteDetalle de la base de datos dado el mismo.
		* @param pObject, Identificador del objeto a Borrar.
		*/
//		public void borrarLoteDetalle (LoteDetalle pObject) throws LoteDetalleException;
		/**
		* Actualiza un LoteDetalle de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizar(LoteDetalle pObject) throws LoteDetalleException;
		/**
		* Obtiene una lista de todas los LoteDetalles de la base de datos.
		* @return Una lista de objetos.
		*/
//		public List getLoteDetalle(Filtro filtro) throws LoteDetalleException;
		
		/**
		* Obtiene una lista de todas los LoteDetalles de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getLoteDetalleConsultaManual(Filtro filtro) throws LoteDetalleException;
		
		
		public Long getLastIdDeRenglon(Long idEjercicio, Long idEmpresa, Long idAsiento);
		
		public long getSumaDelTotal(Long idEjercicio, Long idEmpresa, Long idAsiento, String comparacion);
		
		/*
		 * la suma del debe menos el haber.
		 * */
		public long getSumaDelTotal(Long idEjercicio, Long idEmpresa, Long idAsiento);
		
		public void borrarTodosLosDetallesDelLote(Long idEjercicio, Long idEmpresa, Long idAsiento);
	}


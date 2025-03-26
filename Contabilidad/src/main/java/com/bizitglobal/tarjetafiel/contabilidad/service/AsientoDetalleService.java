package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;

public interface AsientoDetalleService {
	
	
		/**
		* Graba un LoteDetalle en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabar(AsientoDetalle pObject) throws AsientoDetalleException;	
		
		public void actualizar(AsientoDetalle pObject) throws AsientoDetalleException;
	    /**
		* Graba un AsientoDetalle en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabarAsientoDetalle (AsientoDetalle pObject) throws AsientoDetalleException;
		/**
		* Obtiene un AsientoDetalle de la base de datos dado su id.
		* @param id, Identificador del objeto buscado.
		*/
		public AsientoDetalle leerAsientoDetalle (Long id) throws AsientoDetalleException;
		/**
		* Borra un AsientoDetalle de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrarAsientoDetalle (Long id) throws AsientoDetalleException;
		/**
		* Borra un AsientoDetalle de la base de datos dado el mismo.
		* @param pObject, Identificador del objeto a Borrar.
		*/
		public void borrarAsientoDetalle (AsientoDetalle pObject) throws AsientoDetalleException;
		/**
		* Actualiza un AsientoDetalle de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarAsientoDetalle (AsientoDetalle pObject) throws AsientoDetalleException;
		/**
		* Obtiene una lista de todas los AsientoDetalles de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getAsientoDetalle(Filtro filtro) throws AsientoDetalleException;
		
		/**
		* Obtiene una lista de todas los AsientoDetalles de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getAsientoDetalleConsultaManual(Filtro filtro) throws AsientoDetalleException;
		
		public Long getLastIdDeRenglon(Long idEjercicio, Long idEmpresa, Long idAsiento);
		
		public void borrarTodosLosDetallesDelAsiento(Long idEjercicio, Long idEmpresa, Long idAsiento);
		
		/**
		* Borra un AsientoDetalle de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrar(Long idEjercicio, Long idEmpresa, Long idAsiento, Long renglon) throws AsientoDetalleException;
		
	}


package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Lote;

public interface LoteService {
		/**
		* Graba un Lote en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabarLote (Lote pObject) throws LoteException;
		/**
		* Obtiene un Lote de la base de datos dado su id.
		* @param id, Identificador del objeto buscado.
		*/
		public Lote leerLote (Long id) throws LoteException;
		/**
		* Borra un Lote de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrarLote (Long id) throws LoteException;
		/**
		* Borra un Lote de la base de datos dado el mismo.
		* @param pObject, Identificador del objeto a Borrar.
		*/
		public void borrarLote (Lote pObject) throws LoteException;
		/**
		* Actualiza un Lote de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarLote (Lote pObject) throws LoteException;
		/**
		* Obtiene una lista de todas los Lotes de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getLote(Filtro filtro) throws LoteException;
		
		/**
		 * Movemos el registro a la tabla de asientos
		 * */
		public void asentarLote(Long idEjercicio, Long idEmpresa, Long idAsiento) throws LoteException;
		
		public List getLoteConsultaManual(Filtro filtro) throws LoteException;
//		public void asentarDetallesDelLote(Long idEjercicio, Long idEmpresa, Long idAsiento) throws LoteException;
	
		//obtener el maximo id para grabar
		public Long getLastIdDeAsientos();
		
		public Long getLastIdDeLotes();

		public void impactarOrigenContab(Long nroAsiento, Long idProveedor, Long idComprobante);
		
		public Long contarLotes(Filtro filtro) throws LoteException;
		
	}


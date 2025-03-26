package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostoAsientoException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.CentroCostoAsiento;

public interface CentroCostoAsientoService {
		/**
		* Graba un CentroCostoAsiento en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabarCentroCostoAsiento (CentroCostoAsiento pObject) throws CentroCostoAsientoException;
		/**
		* Obtiene un CentroCostoAsiento de la base de datos dado su id.
		* @param id, Identificador del objeto buscado.
		*/
		public CentroCostoAsiento leerCentroCostoAsiento (Long id) throws CentroCostoAsientoException;
		/**
		* Borra un CentroCostoAsiento de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrarCentroCostoAsiento (Long id) throws CentroCostoAsientoException;
		/**
		* Borra un CentroCostoAsiento de la base de datos dado el mismo.
		* @param pObject, Identificador del objeto a Borrar.
		*/
		public void borrarCentroCostoAsiento (CentroCostoAsiento pObject) throws CentroCostoAsientoException;
		/**
		* Actualiza un CentroCostoAsiento de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarCentroCostoAsiento (CentroCostoAsiento pObject) throws CentroCostoAsientoException;
		/**
		* Obtiene una lista de todas los CentroCostoAsiento de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getCentroCostoAsiento(Filtro filtro) throws CentroCostoAsientoException;
	}


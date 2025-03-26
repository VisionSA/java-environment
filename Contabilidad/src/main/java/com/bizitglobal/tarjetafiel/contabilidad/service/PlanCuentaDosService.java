package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;

public interface PlanCuentaDosService {
		/**
		* Graba un Plan Cuenta en la base de datos.
		* @param pObject, objeto a grabar.
		*/
		public void grabarPlanCuenta (PlanCuentaDos pObject) throws PlanCuentaDosException;
		/**
		* Obtiene un Plan Cuenta de la base de datos dado su id.
		* @param id, Identificador del objeto buscado.
		*/
		public PlanCuentaDos leerPlanCuenta (Long id) throws PlanCuentaDosException;
		/**
		* Borra un Plan Cuenta de la base de datos dado su id.
		* @param id, Identificador del objeto a Borrar.
		*/
		public void borrarPlanCuenta (Long id) throws PlanCuentaDosException;
		/**
		* Borra un Plan Cuenta de la base de datos dado el mismo.
		* @param pObject, Identificador del objeto a Borrar.
		*/
		public void borrarPlanCuenta (PlanCuentaDos pObject) throws PlanCuentaDosException;
		/**
		* Actualiza un Plan Cuenta de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarPlanCuenta (PlanCuentaDos pObject) throws PlanCuentaDosException;
		/**
		* Obtiene una lista de todas los Plan Cuenta de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getPlanCuenta(Filtro filtro) throws PlanCuentaDosException;
		
		/**
		* Obtiene una lista dimplifaicada de todos los Plan Cuenta de la base de datos.
		* @return Una lista de objetos.
		*/
		public List getPlanCuentaSimple(Filtro filtro) throws PlanCuentaDosException;
	} 


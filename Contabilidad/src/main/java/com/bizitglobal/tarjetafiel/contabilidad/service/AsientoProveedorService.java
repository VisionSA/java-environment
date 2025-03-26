package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoProveedorException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoProveedor;

public interface AsientoProveedorService {
		
	
		/**
		* Actualiza un Asiento de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarAsientoProveedor (AsientoProveedor pObject) throws AsientoProveedorException;
		
		public List getAsientoProveedorImportables(Date inicioEjercicio, Date finEjercicio) throws AsientoProveedorException;
	
		public List getDetallesAsientoProveedorImportado(Long id) throws AsientoProveedorException;
		
	}


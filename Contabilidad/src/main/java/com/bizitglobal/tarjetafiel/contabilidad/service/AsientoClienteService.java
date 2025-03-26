package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoClienteException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoCliente;

public interface AsientoClienteService {
		
	
		/**
		* Actualiza un Asiento de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarAsientoCliente (AsientoCliente pObject) throws AsientoClienteException;
		
		public List getAsientoClienteImportables(Date inicioEjercicio, Date finEjercicio) throws AsientoClienteException;
	
		public List getDetallesAsientoClienteImportado(Long id) throws AsientoClienteException;
		
	}


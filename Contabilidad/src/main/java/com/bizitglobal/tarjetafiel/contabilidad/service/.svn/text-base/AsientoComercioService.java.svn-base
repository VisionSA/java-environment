package com.bizitglobal.tarjetafiel.contabilidad.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoClienteException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoComercioException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoCliente;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoComercio;

public interface AsientoComercioService {
		
	
		/**
		* Actualiza un Asiento de la base de datos.
		* @param pObject, Identificador del objeto a Actualizar.
		*/
		public void actualizarAsientoComercio (AsientoComercio pObject) throws AsientoComercioException;
		
		public List getAsientoComercioImportables(Date inicioEjercicio, Date finEjercicio) throws AsientoComercioException;
	
		public List getDetallesAsientoComercioImportado(Long id) throws AsientoComercioException;
		
	}


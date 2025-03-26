package com.bizitglobal.tarjetafiel.proveedores.service;

import java.util.List;
import java.sql.Timestamp;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorSICOREException;

public interface ProveedorSICOREService {
	
	public List obtenerSICORE(Timestamp fechaDesde, Timestamp fechaHasta) 
	throws ProveedorSICOREException;
}
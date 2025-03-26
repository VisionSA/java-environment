package com.bizitglobal.tarjetafiel.proveedores.service;

import java.util.List;
import java.sql.Timestamp;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorCtaCteException;

public interface ProveedorCtaCteService {
	
	public List obtenerCtaCte(Long idProveedor, Timestamp fechaDesde, Timestamp fechaHasta) 
	throws ProveedorCtaCteException;
}
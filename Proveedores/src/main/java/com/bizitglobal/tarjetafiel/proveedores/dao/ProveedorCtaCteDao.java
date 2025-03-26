package com.bizitglobal.tarjetafiel.proveedores.dao;

import java.sql.Timestamp;
import java.util.List;

public interface ProveedorCtaCteDao {
	public List obtenerCtaCte(Long idProveedor, Timestamp fechaDesde, Timestamp fechaHasta);
}
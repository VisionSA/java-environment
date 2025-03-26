package com.bizitglobal.tarjetafiel.proveedores.historial;

import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;

public interface ProveedorHistorialDao {
	
	public void grabarHistorialProveedor(Proveedor unProveedor) throws ProveedorException;
	
}

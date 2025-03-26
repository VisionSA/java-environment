package com.bizitglobal.tarjetafiel.proveedores.service.impl;

import java.util.List;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorCtaCteDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorCtaCteException;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorCtaCteService;


public class ProveedorCtaCteServiceImpl implements ProveedorCtaCteService{
	private Logger log = Logger.getLogger(ProveedorCtaCteServiceImpl.class);

	private ProveedorCtaCteDao proveedorCtaCteDao;
	
	public List obtenerCtaCte(Long idProveedor, Timestamp fechaDesde, Timestamp fechaHasta) 
	throws ProveedorCtaCteException {
		
		try {
			return proveedorCtaCteDao.obtenerCtaCte(idProveedor, fechaDesde, fechaHasta);
		}catch (DataAccessException ex) {
			String msg = "La cuenta corriente no pudo ser leida.";
			throw new ProveedorCtaCteException(msg,ex);
		}catch (Exception e) {
			String msg = "Error general.";
			throw new ProveedorCtaCteException(msg,e);
		}
	}

	public ProveedorCtaCteDao getProveedorCtaCteDao() {
		return proveedorCtaCteDao;
	}

	public void setProveedorCtaCteDao(ProveedorCtaCteDao proveedorCtaCteDao) {
		this.proveedorCtaCteDao = proveedorCtaCteDao;
	}	
}


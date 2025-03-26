package com.bizitglobal.tarjetafiel.proveedores.service.impl;

import java.util.List;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorSICOREDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorSICOREException;
import com.bizitglobal.tarjetafiel.proveedores.service.ProveedorSICOREService;


public class ProveedorSICOREServiceImpl implements ProveedorSICOREService{
	private Logger log = Logger.getLogger(ProveedorSICOREServiceImpl.class);

	private ProveedorSICOREDao proveedorSICOREDao;
	
	public List obtenerSICORE(Timestamp fechaDesde, Timestamp fechaHasta) 
	throws ProveedorSICOREException {
		
		try {
			return proveedorSICOREDao.obtenerSICORE(fechaDesde, fechaHasta);
		}catch (DataAccessException ex) {
			String msg = "La estructura del SICORE no pudo ser leida.";
			throw new ProveedorSICOREException(msg,ex);
		}catch (Exception e) {
			String msg = "Error general.";
			throw new ProveedorSICOREException(msg,e);
		}
	}

	public ProveedorSICOREDao getProveedorSICOREDao() {
		return proveedorSICOREDao;
	}

	public void setProveedorSICOREDao(ProveedorSICOREDao proveedorSICOREDao) {
		this.proveedorSICOREDao = proveedorSICOREDao;
	}
	
}


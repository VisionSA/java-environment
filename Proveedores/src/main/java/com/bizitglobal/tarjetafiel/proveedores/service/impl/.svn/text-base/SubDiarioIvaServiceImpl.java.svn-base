package com.bizitglobal.tarjetafiel.proveedores.service.impl;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.bizitglobal.tarjetafiel.proveedores.dao.SubDiarioIvaDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.SubDiarioIvaException;
import com.bizitglobal.tarjetafiel.proveedores.service.SubDiarioIvaService;

public class SubDiarioIvaServiceImpl implements SubDiarioIvaService{
	private Logger log = Logger.getLogger(SubDiarioIvaServiceImpl.class);

	private SubDiarioIvaDao subDiarioIvaDao;
		
	public void proveedoresSubDiarioIva(Long idOperador, Timestamp desde_em, Timestamp hasta_em,Timestamp desde_co, Timestamp hasta_co)
	throws SubDiarioIvaException {
		try {
			subDiarioIvaDao.proveedoresSubDiarioIva(idOperador, desde_em,hasta_em,desde_co,hasta_co);
		}catch (DataAccessException ex) {
			String msg = "La tabla de ranking no pudo ser generada.";
			throw new SubDiarioIvaException(msg,ex);
		}catch (Exception e) {
			String msg = "Error general.";
			throw new SubDiarioIvaException(msg,e);
		}
	}

	public SubDiarioIvaDao getSubDiarioIvaDao() {
		return subDiarioIvaDao;
	}

	public void setSubDiarioIvaDao(SubDiarioIvaDao subDiarioIvaDao) {
		this.subDiarioIvaDao = subDiarioIvaDao;
	}
	
}

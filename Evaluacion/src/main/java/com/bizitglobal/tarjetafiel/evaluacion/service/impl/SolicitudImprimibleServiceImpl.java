package com.bizitglobal.tarjetafiel.evaluacion.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.evaluacion.dao.SolicitudImprimibleDao;
import com.bizitglobal.tarjetafiel.evaluacion.dao.impl.SolicitudImprimibleDaoImpl;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudImprimibleDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudImprimibleException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudImprimibleNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudImprimible;
import com.bizitglobal.tarjetafiel.evaluacion.service.SolicitudImprimibleService;

public class SolicitudImprimibleServiceImpl implements SolicitudImprimibleService{
	private Logger log = Logger.getLogger(SolicitudImprimibleDaoImpl.class);
	
	private SolicitudImprimibleDao solicitudImprimibleDao;
	private PlatformTransactionManager transactionManager;	
    private TransactionTemplate transactionTemplate;
	
	
	public void cargarSolicitudes(SolicitudImprimible sol) throws SolicitudImprimibleException{
		if (sol != null && (sol.getId()==null || sol.getId().equals(new Long(0)))){
			sol.setId(new Long(solicitudImprimibleDao.getLastId().longValue() + 1 ));
		}
		log.info("SolicitudImprimibleID: "+sol.getId());
		
		solicitudImprimibleDao.guardar(sol);
	}
	
	public void borrarSolicitudes(Long idOperador) throws SolicitudImprimibleException{
		if (idOperador != null){
			try {
				solicitudImprimibleDao.limpiar(idOperador);
			} catch (DataAccessException ex) {
				String msg = "La Prioridad no pudo borrase.";
				throw new SolicitudImprimibleNotFoundException(msg,ex);
			}catch (Exception e) {
				String msg = "Error general.";
				throw new SolicitudImprimibleException(msg,e);
			}		
		}
	}

	public SolicitudImprimibleDao getSolicitudImprimibleDao() {
		return solicitudImprimibleDao;
	}

	public void setSolicitudImprimibleDao(
			SolicitudImprimibleDao solicitudImprimibleDao) {
		this.solicitudImprimibleDao = solicitudImprimibleDao;
	}

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
	
}
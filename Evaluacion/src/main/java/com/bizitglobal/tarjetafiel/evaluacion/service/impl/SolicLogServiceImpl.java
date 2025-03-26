package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.SolicLogDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicLogDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicLogException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicLogNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicLog;
import com.bizitglobal.tarjetafiel.evaluacion.service.SolicLogService;

/**
*	Implementacion de la interfaz SolicLogService.
*/
public class SolicLogServiceImpl implements SolicLogService {
	private static Logger log = Logger.getLogger(SolicLogServiceImpl.class);
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private SolicLogDao SolicLogDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarSolicLog(final SolicLog pObject) throws SolicLogException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				SolicLogDao.grabarSolicLog(pObject);

			}
		});
	}
	
	public List getSolicLog(Filtro filtro) throws SolicLogException {
		try {
			return SolicLogDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de SolicLog no pudo ser leida.";
			throw new SolicLogException(msg,e);
		}
	}
	
	public SolicLog leerSolicLog(Long id) throws SolicLogException {
		SolicLog result = null;
		try {
			result = SolicLogDao.buscarSolicLog(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La SolicLog no existe en la base de datos.";
			throw new SolicLogNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaSolicLog no pudo leerse desde la base de datos.";
			throw new SolicLogException(msg,e);
		}
		return result;
	}
	
	public void borrarSolicLog(final Long id) throws SolicLogException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				SolicLogDao.borrarSolicLog(id);

			}
		});
	}
	
	public void borrarSolicLog(final SolicLog pObject) throws SolicLogException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				SolicLogDao.borrarSolicLog(pObject);

			}
		});
	}
	
	public void actualizarSolicLog(final SolicLog pObject) throws SolicLogException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				SolicLogDao.actualizarSolicLog(pObject);

			}
		});
	}

	public SolicLogDao getSolicLogDao() {
		return SolicLogDao;
	}

	public void setSolicLogDao(SolicLogDao SolicLogDao) {
		this.SolicLogDao = SolicLogDao;
	}

	/**
	* Necesario para spring.
	* @return Retorna el objeto transactionManager.
	*/
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}
	/**
	* Necesario para spring
	* @param transactionManager, Objeto a setear.
	*/
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
}


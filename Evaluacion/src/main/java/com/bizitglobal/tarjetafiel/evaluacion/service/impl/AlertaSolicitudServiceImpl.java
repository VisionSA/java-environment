package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.AlertaSolicitudDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.AlertaSolicitudDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.AlertaSolicitudException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.AlertaSolicitudNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.AlertaSolicitud;
import com.bizitglobal.tarjetafiel.evaluacion.service.AlertaSolicitudService;

/**
*	Implementacion de la interfaz AlertaSolicitudService.
*/
public class AlertaSolicitudServiceImpl implements AlertaSolicitudService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private AlertaSolicitudDao alertaSolicitudDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarAlertaSolicitud (final AlertaSolicitud pObject) throws AlertaSolicitudException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				alertaSolicitudDao.grabarEvaAlertasSolicitudes(pObject);

			}
		});
	}
	
	public List getAlertaSolicitud(Filtro filtro) throws AlertaSolicitudException {
		try {
			return alertaSolicitudDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaalertassolicitudes no pudo ser leida.";
			throw new AlertaSolicitudException(msg,e);
		}
	}
	
	public AlertaSolicitud leerAlertaSolicitud (Long id) throws AlertaSolicitudException {
		AlertaSolicitud result = null;
		try {
			result = alertaSolicitudDao.buscarEvaAlertasSolicitudes(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La AlertaSolicitud no existe en la base de datos.";
			throw new AlertaSolicitudNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaalertassolicitudes no pudo leerse desde la base de datos.";
			throw new AlertaSolicitudException(msg,e);
		}
		return result;
	}
	
	public void borrarAlertaSolicitud (final Long id) throws AlertaSolicitudException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				alertaSolicitudDao.borrarEvaAlertasSolicitudes(id);

			}
		});
	}
	
	public void borrarAlertaSolicitud(final AlertaSolicitud pObject) throws AlertaSolicitudException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				alertaSolicitudDao.borrarEvaAlertasSolicitudes(pObject);

			}
		});
	}
	
	public void actualizarAlertaSolicitud (final AlertaSolicitud pObject) throws AlertaSolicitudException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				alertaSolicitudDao.actualizarEvaAlertasSolicitudes(pObject);

			}
		});
	}
	
	
	
	public AlertaSolicitudDao getAlertaSolicitudDao() {
		return alertaSolicitudDao;
	}

	public void setAlertaSolicitudDao(AlertaSolicitudDao alertaSolicitudDao) {
		this.alertaSolicitudDao = alertaSolicitudDao;
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


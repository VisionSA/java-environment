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
import com.bizitglobal.tarjetafiel.evaluacion.dao.AlertaTipoIndividuoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.AlertaTipoIndividuoDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.AlertaTipoIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.AlertaTipoIndividuoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.AlertaTipoIndividuo;
import com.bizitglobal.tarjetafiel.evaluacion.service.AlertaTipoIndividuoService;

/**
*	Implementacion de la interfaz AlertaTipoIndividuoService.
*/
public class AlertaTipoIndividuoServiceImpl implements AlertaTipoIndividuoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private AlertaTipoIndividuoDao alertaTipoIndividuoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarAlertaTipoIndividuo (final AlertaTipoIndividuo pObject) throws AlertaTipoIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				alertaTipoIndividuoDao.grabarEvaAlertasTipoIndiv(pObject);

			}
		});
	}
	
	public List getAlertaTipoIndividuo(Filtro filtro) throws AlertaTipoIndividuoException {
		try {
			return alertaTipoIndividuoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaalertastipoindiv no pudo ser leida.";
			throw new AlertaTipoIndividuoException(msg,e);
		}
	}
	
	public AlertaTipoIndividuo leerAlertaTipoIndividuo (Long id) throws AlertaTipoIndividuoException {
		AlertaTipoIndividuo result = null;
		try {
			result = alertaTipoIndividuoDao.buscarEvaAlertasTipoIndiv(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La AlertaTipoIndividuo no existe en la base de datos.";
			throw new AlertaTipoIndividuoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaalertastipoindiv no pudo leerse desde la base de datos.";
			throw new AlertaTipoIndividuoException(msg,e);
		}
		return result;
	}
	
	public void borrarAlertaTipoIndividuo (final Long id) throws AlertaTipoIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				alertaTipoIndividuoDao.borrarEvaAlertasTipoIndiv(id);

			}
		});
	}
	
	public void borrarAlertaTipoIndividuo(final AlertaTipoIndividuo pObject) throws AlertaTipoIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				alertaTipoIndividuoDao.borrarEvaAlertasTipoIndiv(pObject);

			}
		});
	}
	
	public void actualizarAlertaTipoIndividuo (final AlertaTipoIndividuo pObject) throws AlertaTipoIndividuoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				alertaTipoIndividuoDao.actualizarEvaAlertasTipoIndiv(pObject);

			}
		});
	}
	
	
	
	public AlertaTipoIndividuoDao getAlertaTipoIndividuoDao() {
		return alertaTipoIndividuoDao;
	}

	public void setAlertaTipoIndividuoDao(
			AlertaTipoIndividuoDao alertaTipoIndividuoDao) {
		this.alertaTipoIndividuoDao = alertaTipoIndividuoDao;
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


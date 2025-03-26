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
import com.bizitglobal.tarjetafiel.evaluacion.dao.ObservoLaboralDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoLaboralDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoLaboralException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoLaboralNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ObservoLaboral;
import com.bizitglobal.tarjetafiel.evaluacion.service.ObservoLaboralService;

/**
*	Implementacion de la interfaz ObservoLaboralService.
*/
public class ObservoLaboralServiceImpl implements ObservoLaboralService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private ObservoLaboralDao observoLaboralDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarObservoLaboral(final ObservoLaboral pObject) throws ObservoLaboralException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoLaboralDao.grabarEvaObsLaborales(pObject);

			}
		});
	}
	
	public List getObservoLaboral(Filtro filtro) throws ObservoLaboralException {
		try {
			return observoLaboralDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaobslaborales no pudo ser leida.";
			throw new ObservoLaboralException(msg,e);
		}
	}
	
	public ObservoLaboral leerObservoLaboral(Long id) throws ObservoLaboralException {
		ObservoLaboral result = null;
		try {
			result = observoLaboralDao.buscarEvaObsLaborales(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La ObservoLaboral no existe en la base de datos.";
			throw new ObservoLaboralNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaobslaborales no pudo leerse desde la base de datos.";
			throw new ObservoLaboralException(msg,e);
		}
		return result;
	}
	
	public void borrarObservoLaboral(final Long id) throws ObservoLaboralException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoLaboralDao.borrarEvaObsLaborales(id);

			}
		});
	}
	
	public void borrarObservoLaboral(final ObservoLaboral pObject) throws ObservoLaboralException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoLaboralDao.borrarEvaObsLaborales(pObject);

			}
		});
	}
	
	public void actualizarObservoLaboral(final ObservoLaboral pObject) throws ObservoLaboralException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoLaboralDao.actualizarEvaObsLaborales(pObject);

			}
		});
	}
	
	
	
	public ObservoLaboralDao getObservoLaboralDao() {
		return observoLaboralDao;
	}

	public void setObservoLaboralDao(ObservoLaboralDao observoLaboralDao) {
		this.observoLaboralDao = observoLaboralDao;
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


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
import com.bizitglobal.tarjetafiel.evaluacion.dao.ObservoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ObservoNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Observo;
import com.bizitglobal.tarjetafiel.evaluacion.service.ObservoService;

/**
*	Implementacion de la interfaz ObservoService.
*/
public class ObservoServiceImpl implements ObservoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private ObservoDao observoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarObservo(final Observo pObject) throws ObservoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoDao.grabarEvaObservos(pObject);

			}
		});
	}
	
	public List getObservo(Filtro filtro) throws ObservoException {
		try {
			return observoDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaobservos no pudo ser leida.";
			throw new ObservoException(msg,e);
		}
	}
	
	public Observo leerObservo(Long id) throws ObservoException {
		Observo result = null;
		try {
			result = observoDao.buscarEvaObservos(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Observo no existe en la base de datos.";
			throw new ObservoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaobservos no pudo leerse desde la base de datos.";
			throw new ObservoException(msg,e);
		}
		return result;
	}
	public void borrarObservo(final Long id) throws ObservoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoDao.borrarEvaObservos(id);

			}
		});
	}
	
	public void borrarObservo(final Observo pObject) throws ObservoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoDao.borrarEvaObservos(pObject);

			}
		});
	}
	
	public void actualizarObservo(final Observo pObject) throws ObservoException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				observoDao.actualizarEvaObservos(pObject);

			}
		});
	}

	public ObservoDao getObservoDao() {
		return observoDao;
	}

	public void setObservoDao(ObservoDao observoDao) {
		this.observoDao = observoDao;
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


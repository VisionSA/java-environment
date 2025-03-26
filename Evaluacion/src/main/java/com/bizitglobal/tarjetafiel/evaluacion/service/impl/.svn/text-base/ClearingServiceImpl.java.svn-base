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
import com.bizitglobal.tarjetafiel.evaluacion.dao.ClearingDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ClearingDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ClearingException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.ClearingNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Clearing;
import com.bizitglobal.tarjetafiel.evaluacion.service.ClearingService;

/**
*	Implementacion de la interfaz ClearingService.
*/
public class ClearingServiceImpl implements ClearingService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private ClearingDao clearingDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarClearing (final Clearing pObject) throws ClearingException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				clearingDao.grabarEvaClearings(pObject);

			}
		});
	}
	
	public List getClearing(Filtro filtro) throws ClearingException {
		try {
			return clearingDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evaclearings no pudo ser leida.";
			throw new ClearingException(msg,e);
		}
	}
	
	public Clearing leerClearing (Long id) throws ClearingException {
		Clearing result = null;
		try {
			result = clearingDao.buscarEvaClearings(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Clearing no existe en la base de datos.";
			throw new ClearingNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evaclearings no pudo leerse desde la base de datos.";
			throw new ClearingException(msg,e);
		}
		return result;
	}
	
	public void borrarClearing (final Long id) throws ClearingException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				clearingDao.borrarEvaClearings(id);

			}
		});
	}
	
	public void borrarClearing(final Clearing pObject) throws ClearingException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				clearingDao.borrarEvaClearings(pObject);

			}
		});
	}
	
	public void actualizarClearing (final Clearing pObject) throws ClearingException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				clearingDao.actualizarEvaClearings(pObject);

			}
		});
	}
	

	public ClearingDao getClearingDao() {
		return clearingDao;
	}

	public void setClearingDao(ClearingDao clearingDao) {
		this.clearingDao = clearingDao;
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


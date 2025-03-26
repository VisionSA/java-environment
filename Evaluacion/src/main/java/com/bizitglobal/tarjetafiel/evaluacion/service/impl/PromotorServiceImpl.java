package com.bizitglobal.tarjetafiel.evaluacion.service.impl;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.PromotorDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.PromotorDuplicateException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.PromotorException;
import com.bizitglobal.tarjetafiel.evaluacion.exception.PromotorNotFoundException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Promotor;
import com.bizitglobal.tarjetafiel.evaluacion.service.PromotorService;

/**
*	Implementacion de la interfaz PromotorService.
*/
public class PromotorServiceImpl implements PromotorService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private PromotorDao promotorDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

	public void grabarPromotor(final Promotor pObject) throws PromotorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				promotorDao.grabarEvaPromotores(pObject);

			}
		});
	}
	
	public List getPromotor(Filtro filtro) throws PromotorException {
		try {
			return promotorDao.listarTodos(filtro);
		} catch (Exception e) {
			String msg = "La lista de evapromotores no pudo ser leida.";
			throw new PromotorException(msg,e);
		}
	}
	
	public Promotor leerPromotor(Long id) throws PromotorException {
		Promotor result = null;
		try {
			result = promotorDao.buscarEvaPromotores(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La Promotor no existe en la base de datos.";
			throw new PromotorNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La evapromotores no pudo leerse desde la base de datos.";
			throw new PromotorException(msg,e);
		}
		return result;
	}
	
	public void borrarPromotor(final Long id) throws PromotorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				promotorDao.borrarEvaPromotores(id);

			}
		});
	}
	
	public void borrarPromotor(final Promotor pObject) throws PromotorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				promotorDao.borrarEvaPromotores(pObject);
			}
		});
	}
	
	public void actualizarPromotor(final Promotor pObject) throws PromotorException {
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
				promotorDao.actualizarEvaPromotores(pObject);
			}
		});
	}
	
	public PromotorDao getPromotorDao() {
		return promotorDao;
	}

	public void setPromotorDao(PromotorDao promotorDao) {
		this.promotorDao = promotorDao;
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


package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.FormaPagoValorDao;
import com.bizitglobal.tarjetafiel.fondos.exception.FormaPagoValorDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.FormaPagoValorException;
import com.bizitglobal.tarjetafiel.fondos.exception.FormaPagoValorNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.FormaPagoValor;
import com.bizitglobal.tarjetafiel.fondos.service.FormaPagoValorService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class FormaPagoValorServiceImpl implements FormaPagoValorService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private FormaPagoValorDao formaPagoValorDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarFormaPagoValor(final FormaPagoValor unaFormaPagoValor) throws FormaPagoValorException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					formaPagoValorDao.grabarFormaPagoValor(unaFormaPagoValor);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La FormaPagoValor ya existe en la base de datos.";
			throw new FormaPagoValorDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La FormaPagoValor no pudo ser grabada.";
			throw new FormaPagoValorException(msg,e);
		}
		
	}
	
	public List getFormaPagoValores() throws FormaPagoValorException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return formaPagoValorDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de FormaPagoValor no pudo ser leida.";
			throw new FormaPagoValorException(msg,e);
		}
	}
	
	public FormaPagoValor leerFormaPagoValor(Long id) throws FormaPagoValorException {
		try {
			return formaPagoValorDao.buscarFormaPagoValor(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La formaPagoValor no existe en la base de datos.";
			throw new FormaPagoValorNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La FormaPagoValor no pudo leerse desde la base de datos.";
			throw new FormaPagoValorException(msg,e);
		}
	}
	
	public void borrarFormaPagoValor(final Long idFormaPagoValor) throws FormaPagoValorException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					formaPagoValorDao.borrarFormaPagoValor(idFormaPagoValor);
				}
			});
		} catch (Exception e) {
			String msg = "La FormaPagoValor no pudo borrase.";
			throw new FormaPagoValorException(msg,e);
		}
	}
	
	public void borrarFormaPagoValor(final FormaPagoValor unaFormaPagoValor) throws FormaPagoValorException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					formaPagoValorDao.borrarFormaPagoValor(unaFormaPagoValor);
				}
			});
		} catch (Exception e) {
			String msg = "La FormaPagoValor no pudo borrase.";
			throw new FormaPagoValorException(msg,e);
		}
	}
	
	public void actualizarFormaPagoValor(final FormaPagoValor unaFormaPagoValor) throws FormaPagoValorException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					formaPagoValorDao.actualizarFormaPagoValor(unaFormaPagoValor);
				}
			});
		} catch (Exception e) {
			String msg = "La FormaPagoValor no pudo actualizarse.";
			throw new FormaPagoValorException(msg,e);
		}
	}
	
	public List getFormaPagoValors(final Filtro filtro) throws FormaPagoValorException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return formaPagoValorDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de FormaPagoValor no pudo ser leida.";
			throw new FormaPagoValorException(msg,e);
		}
	}
	
	public List getFormaPagoValoresFlex(final Filtro filtro) throws FormaPagoValorException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return formaPagoValorDao.listarTodosFlex(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de FormaPagoValor no pudo ser leida.";
			throw new FormaPagoValorException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public FormaPagoValorDao getFormaPagoValorDao() {
		return formaPagoValorDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setFormaPagoValorDao(FormaPagoValorDao formaPagoValorDao) {
		this.formaPagoValorDao = formaPagoValorDao;
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

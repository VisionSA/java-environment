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
import com.bizitglobal.tarjetafiel.fondos.dao.AsientoItemDao;
import com.bizitglobal.tarjetafiel.fondos.exception.AsientoItemDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.AsientoItemException;
import com.bizitglobal.tarjetafiel.fondos.exception.AsientoItemNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;
import com.bizitglobal.tarjetafiel.fondos.service.AsientoItemService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class AsientoItemServiceImpl implements AsientoItemService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private AsientoItemDao asientoItemDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarAsientoItem(final AsientoItem unaAsientoItem) throws AsientoItemException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoItemDao.grabarAsientoItem(unaAsientoItem);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La AsientoItem ya existe en la base de datos.";
			throw new AsientoItemDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La AsientoItem no pudo ser grabada.";
			throw new AsientoItemException(msg,e);
		}
		
	}
	
	public List getAsientoItemes() throws AsientoItemException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return asientoItemDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientoItem no pudo ser leida.";
			throw new AsientoItemException(msg,e);
		}
	}
	
	public AsientoItem leerAsientoItem(Long id) throws AsientoItemException {
		try {
			return asientoItemDao.buscarAsientoItem(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La asientoItem no existe en la base de datos.";
			throw new AsientoItemNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La AsientoItem no pudo leerse desde la base de datos.";
			throw new AsientoItemException(msg,e);
		}
	}
	
	public void borrarAsientoItem(final Long idAsientoItem) throws AsientoItemException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoItemDao.borrarAsientoItem(idAsientoItem);
				}
			});
		} catch (Exception e) {
			String msg = "La AsientoItem no pudo borrase.";
			throw new AsientoItemException(msg,e);
		}
	}
	
	public void borrarAsientoItem(final AsientoItem unaAsientoItem) throws AsientoItemException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoItemDao.borrarAsientoItem(unaAsientoItem);
				}
			});
		} catch (Exception e) {
			String msg = "La AsientoItem no pudo borrase.";
			throw new AsientoItemException(msg,e);
		}
	}
	
	public void actualizarAsientoItem(final AsientoItem unaAsientoItem) throws AsientoItemException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoItemDao.actualizarAsientoItem(unaAsientoItem);
				}
			});
		} catch (Exception e) {
			String msg = "La AsientoItem no pudo actualizarse.";
			throw new AsientoItemException(msg,e);
		}
	}
	
	public List getAsientoItems(final Filtro filtro) throws AsientoItemException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return asientoItemDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de AsientoItem no pudo ser leida.";
			throw new AsientoItemException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public AsientoItemDao getAsientoItemDao() {
		return asientoItemDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setAsientoItemDao(AsientoItemDao asientoItemDao) {
		this.asientoItemDao = asientoItemDao;
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

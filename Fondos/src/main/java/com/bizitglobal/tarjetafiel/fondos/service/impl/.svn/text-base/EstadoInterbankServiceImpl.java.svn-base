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
import com.bizitglobal.tarjetafiel.fondos.dao.EstadoInterbankDao;
import com.bizitglobal.tarjetafiel.fondos.exception.EstadoInterbankDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.EstadoInterbankException;
import com.bizitglobal.tarjetafiel.fondos.exception.EstadoInterbankNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.EstadoInterbank;
import com.bizitglobal.tarjetafiel.fondos.service.EstadoInterbankService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class EstadoInterbankServiceImpl implements EstadoInterbankService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private EstadoInterbankDao estadoInterbankDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarEstadoInterbank(final EstadoInterbank unaEstadoInterbank) throws EstadoInterbankException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					estadoInterbankDao.grabarEstadoInterbank(unaEstadoInterbank);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La EstadoInterbank ya existe en la base de datos.";
			throw new EstadoInterbankDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La EstadoInterbank no pudo ser grabada.";
			throw new EstadoInterbankException(msg,e);
		}
		
	}
	
	public List getEstadoInterbankes() throws EstadoInterbankException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return estadoInterbankDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de EstadoInterbank no pudo ser leida.";
			throw new EstadoInterbankException(msg,e);
		}
	}
	
	public EstadoInterbank leerEstadoInterbank(Long id) throws EstadoInterbankException {
		try {
			return estadoInterbankDao.buscarEstadoInterbank(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La estadoInterbank no existe en la base de datos.";
			throw new EstadoInterbankNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La EstadoInterbank no pudo leerse desde la base de datos.";
			throw new EstadoInterbankException(msg,e);
		}
	}
	
	public void borrarEstadoInterbank(final Long idEstadoInterbank) throws EstadoInterbankException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					estadoInterbankDao.borrarEstadoInterbank(idEstadoInterbank);
				}
			});
		} catch (Exception e) {
			String msg = "La EstadoInterbank no pudo borrase.";
			throw new EstadoInterbankException(msg,e);
		}
	}
	
	public void borrarEstadoInterbank(final EstadoInterbank unaEstadoInterbank) throws EstadoInterbankException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					estadoInterbankDao.borrarEstadoInterbank(unaEstadoInterbank);
				}
			});
		} catch (Exception e) {
			String msg = "La EstadoInterbank no pudo borrase.";
			throw new EstadoInterbankException(msg,e);
		}
	}
	
	public void actualizarEstadoInterbank(final EstadoInterbank unaEstadoInterbank) throws EstadoInterbankException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					estadoInterbankDao.actualizarEstadoInterbank(unaEstadoInterbank);
				}
			});
		} catch (Exception e) {
			String msg = "La EstadoInterbank no pudo actualizarse.";
			throw new EstadoInterbankException(msg,e);
		}
	}
	
	public List getEstadoInterbanks(final Filtro filtro) throws EstadoInterbankException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return estadoInterbankDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de EstadoInterbank no pudo ser leida.";
			throw new EstadoInterbankException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public EstadoInterbankDao getEstadoInterbankDao() {
		return estadoInterbankDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setEstadoInterbankDao(EstadoInterbankDao estadoInterbankDao) {
		this.estadoInterbankDao = estadoInterbankDao;
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

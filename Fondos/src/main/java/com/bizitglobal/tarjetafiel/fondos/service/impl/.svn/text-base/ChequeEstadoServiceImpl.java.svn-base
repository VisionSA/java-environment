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
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeEstadoDao;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeEstadoDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeEstadoException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeEstadoNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeEstadoService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class ChequeEstadoServiceImpl implements ChequeEstadoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ChequeEstadoDao chequeEstadoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarChequeEstado(final ChequeEstado unaChequeEstado) throws ChequeEstadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeEstadoDao.grabarChequeEstado(unaChequeEstado);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La ChequeEstado ya existe en la base de datos.";
			throw new ChequeEstadoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La ChequeEstado no pudo ser grabada.";
			throw new ChequeEstadoException(msg,e);
		}
		
	}
	
	public List getChequeEstadoes() throws ChequeEstadoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeEstadoDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ChequeEstado no pudo ser leida.";
			throw new ChequeEstadoException(msg,e);
		}
	}
	
	public ChequeEstado leerChequeEstado(Long id) throws ChequeEstadoException {
		try {
			return chequeEstadoDao.buscarChequeEstado(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La chequeEstado no existe en la base de datos.";
			throw new ChequeEstadoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La ChequeEstado no pudo leerse desde la base de datos.";
			throw new ChequeEstadoException(msg,e);
		}
	}
	
	public void borrarChequeEstado(final Long idChequeEstado) throws ChequeEstadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeEstadoDao.borrarChequeEstado(idChequeEstado);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeEstado no pudo borrase.";
			throw new ChequeEstadoException(msg,e);
		}
	}
	
	public void borrarChequeEstado(final ChequeEstado unaChequeEstado) throws ChequeEstadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeEstadoDao.borrarChequeEstado(unaChequeEstado);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeEstado no pudo borrase.";
			throw new ChequeEstadoException(msg,e);
		}
	}
	
	public void actualizarChequeEstado(final ChequeEstado unaChequeEstado) throws ChequeEstadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeEstadoDao.actualizarChequeEstado(unaChequeEstado);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeEstado no pudo actualizarse.";
			throw new ChequeEstadoException(msg,e);
		}
	}
	
	public List getChequeEstados(final Filtro filtro) throws ChequeEstadoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeEstadoDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ChequeEstado no pudo ser leida.";
			throw new ChequeEstadoException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ChequeEstadoDao getChequeEstadoDao() {
		return chequeEstadoDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setChequeEstadoDao(ChequeEstadoDao chequeEstadoDao) {
		this.chequeEstadoDao = chequeEstadoDao;
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

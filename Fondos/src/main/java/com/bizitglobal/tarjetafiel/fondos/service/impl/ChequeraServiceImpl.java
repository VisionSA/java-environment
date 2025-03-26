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
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeraDao;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeraDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeraException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeraNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Chequera;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeraService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class ChequeraServiceImpl implements ChequeraService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ChequeraDao chequeraDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarChequera(final Chequera unaChequera) throws ChequeraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeraDao.grabarChequera(unaChequera);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La Chequera ya existe en la base de datos.";
			throw new ChequeraDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La Chequera no pudo ser grabada.";
			throw new ChequeraException(msg,e);
		}
		
	}
	
	public List getChequeraes() throws ChequeraException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeraDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Chequera no pudo ser leida.";
			throw new ChequeraException(msg,e);
		}
	}
	
	public Chequera leerChequera(Long id) throws ChequeraException {
		try {
			return chequeraDao.buscarChequera(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La chequera no existe en la base de datos.";
			throw new ChequeraNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La Chequera no pudo leerse desde la base de datos.";
			throw new ChequeraException(msg,e);
		}
	}
	
	public void borrarChequera(final Long idChequera) throws ChequeraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeraDao.borrarChequera(idChequera);
				}
			});
		} catch (Exception e) {
			String msg = "La Chequera no pudo borrase.";
			throw new ChequeraException(msg,e);
		}
	}
	
	public void borrarChequera(final Chequera unaChequera) throws ChequeraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeraDao.borrarChequera(unaChequera);
				}
			});
		} catch (Exception e) {
			String msg = "La Chequera no pudo borrase.";
			throw new ChequeraException(msg,e);
		}
	}
	
	public void actualizarChequera(final Chequera unaChequera) throws ChequeraException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeraDao.actualizarChequera(unaChequera);
				}
			});
		} catch (Exception e) {
			String msg = "La Chequera no pudo actualizarse.";
			throw new ChequeraException(msg,e);
		}
	}
	
	public List getChequeras(final Filtro filtro) throws ChequeraException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeraDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de Chequera no pudo ser leida.";
			throw new ChequeraException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ChequeraDao getChequeraDao() {
		return chequeraDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setChequeraDao(ChequeraDao chequeraDao) {
		this.chequeraDao = chequeraDao;
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

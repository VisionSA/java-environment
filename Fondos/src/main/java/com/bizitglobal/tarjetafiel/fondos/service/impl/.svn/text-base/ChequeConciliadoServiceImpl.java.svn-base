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
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeConciliadoDao;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeConciliadoDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeConciliadoException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeConciliadoNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeConciliado;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeConciliadoService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class ChequeConciliadoServiceImpl implements ChequeConciliadoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ChequeConciliadoDao chequeConciliadoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarChequeConciliado(final ChequeConciliado unaChequeConciliado) throws ChequeConciliadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeConciliadoDao.grabarChequeConciliado(unaChequeConciliado);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La ChequeConciliado ya existe en la base de datos.";
			throw new ChequeConciliadoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La ChequeConciliado no pudo ser grabada.";
			throw new ChequeConciliadoException(msg,e);
		}
		
	}
	
	public List getChequeConciliadoes() throws ChequeConciliadoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeConciliadoDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ChequeConciliado no pudo ser leida.";
			throw new ChequeConciliadoException(msg,e);
		}
	}
	
	public ChequeConciliado leerChequeConciliado(Long id) throws ChequeConciliadoException {
		try {
			return chequeConciliadoDao.buscarChequeConciliado(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La chequeConciliado no existe en la base de datos.";
			throw new ChequeConciliadoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La ChequeConciliado no pudo leerse desde la base de datos.";
			throw new ChequeConciliadoException(msg,e);
		}
	}
	
	public void borrarChequeConciliado(final Long idChequeConciliado) throws ChequeConciliadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeConciliadoDao.borrarChequeConciliado(idChequeConciliado);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeConciliado no pudo borrase.";
			throw new ChequeConciliadoException(msg,e);
		}
	}
	
	public void borrarChequeConciliado(final ChequeConciliado unaChequeConciliado) throws ChequeConciliadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeConciliadoDao.borrarChequeConciliado(unaChequeConciliado);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeConciliado no pudo borrase.";
			throw new ChequeConciliadoException(msg,e);
		}
	}
	
	public void actualizarChequeConciliado(final ChequeConciliado unaChequeConciliado) throws ChequeConciliadoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeConciliadoDao.actualizarChequeConciliado(unaChequeConciliado);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeConciliado no pudo actualizarse.";
			throw new ChequeConciliadoException(msg,e);
		}
	}
	
	public List getChequeConciliados(final Filtro filtro) throws ChequeConciliadoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeConciliadoDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ChequeConciliado no pudo ser leida.";
			throw new ChequeConciliadoException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ChequeConciliadoDao getChequeConciliadoDao() {
		return chequeConciliadoDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setChequeConciliadoDao(ChequeConciliadoDao chequeConciliadoDao) {
		this.chequeConciliadoDao = chequeConciliadoDao;
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

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
import com.bizitglobal.tarjetafiel.fondos.dao.CajaCierreDao;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaCierreDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaCierreException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaCierreNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaCierre;
import com.bizitglobal.tarjetafiel.fondos.service.CajaCierreService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class CajaCierreServiceImpl implements CajaCierreService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private CajaCierreDao cajaCierreDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarCajaCierre(final CajaCierre unaCajaCierre) throws CajaCierreException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaCierreDao.grabarCajaCierre(unaCajaCierre);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La CajaCierre ya existe en la base de datos.";
			throw new CajaCierreDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La CajaCierre no pudo ser grabada.";
			throw new CajaCierreException(msg,e);
		}
		
	}
	
	
	
	
	public List getCajaCierrees() throws CajaCierreException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return cajaCierreDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de CajaCierre no pudo ser leida.";
			throw new CajaCierreException(msg,e);
		}
	}
	
	public CajaCierre leerCajaCierre(Long id) throws CajaCierreException {
		try {
			return cajaCierreDao.buscarCajaCierre(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La cajaCierre no existe en la base de datos.";
			throw new CajaCierreNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La CajaCierre no pudo leerse desde la base de datos.";
			throw new CajaCierreException(msg,e);
		}
	}
	
	public void borrarCajaCierre(final Long idCajaCierre) throws CajaCierreException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaCierreDao.borrarCajaCierre(idCajaCierre);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaCierre no pudo borrase.";
			throw new CajaCierreException(msg,e);
		}
	}
	
	public void borrarCajaCierre(final CajaCierre unaCajaCierre) throws CajaCierreException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaCierreDao.borrarCajaCierre(unaCajaCierre);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaCierre no pudo borrase.";
			throw new CajaCierreException(msg,e);
		}
	}
	
	public void actualizarCajaCierre(final CajaCierre unaCajaCierre) throws CajaCierreException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					cajaCierreDao.actualizarCajaCierre(unaCajaCierre);
				}
			});
		} catch (Exception e) {
			String msg = "La CajaCierre no pudo actualizarse.";
			throw new CajaCierreException(msg,e);
		}
	}
	
	public List getCajaCierres(final Filtro filtro) throws CajaCierreException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return cajaCierreDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de CajaCierre no pudo ser leida.";
			throw new CajaCierreException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public CajaCierreDao getCajaCierreDao() {
		return cajaCierreDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setCajaCierreDao(CajaCierreDao cajaCierreDao) {
		this.cajaCierreDao = cajaCierreDao;
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

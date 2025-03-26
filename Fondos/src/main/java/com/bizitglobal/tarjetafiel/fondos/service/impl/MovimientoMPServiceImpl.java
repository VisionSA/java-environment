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
import com.bizitglobal.tarjetafiel.fondos.dao.MovimientoMPDao;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoMPDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoMPException;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoMPNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;
import com.bizitglobal.tarjetafiel.fondos.service.MovimientoMPService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class MovimientoMPServiceImpl implements MovimientoMPService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private MovimientoMPDao movimientoMPDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarMovimientoMP(final MovimientoMP unaMovimientoMP) throws MovimientoMPException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					movimientoMPDao.grabarMovimientoMP(unaMovimientoMP);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La MovimientoMP ya existe en la base de datos.";
			throw new MovimientoMPDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La MovimientoMP no pudo ser grabada.";
			throw new MovimientoMPException(msg,e);
		}
		
	}
	
	public List getMovimientoMPes() throws MovimientoMPException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return movimientoMPDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de MovimientoMP no pudo ser leida.";
			throw new MovimientoMPException(msg,e);
		}
	}
	
	public MovimientoMP leerMovimientoMP(Long id) throws MovimientoMPException {
		try {
			return movimientoMPDao.buscarMovimientoMP(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La movimientoMP no existe en la base de datos.";
			throw new MovimientoMPNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La MovimientoMP no pudo leerse desde la base de datos.";
			throw new MovimientoMPException(msg,e);
		}
	}
	
	public void borrarMovimientoMP(final Long idMovimientoMP) throws MovimientoMPException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					movimientoMPDao.borrarMovimientoMP(idMovimientoMP);
				}
			});
		} catch (Exception e) {
			String msg = "La MovimientoMP no pudo borrase.";
			throw new MovimientoMPException(msg,e);
		}
	}
	
	public void borrarMovimientoMP(final MovimientoMP unaMovimientoMP) throws MovimientoMPException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					movimientoMPDao.borrarMovimientoMP(unaMovimientoMP);
				}
			});
		} catch (Exception e) {
			String msg = "La MovimientoMP no pudo borrase.";
			throw new MovimientoMPException(msg,e);
		}
	}
	
	public void actualizarMovimientoMP(final MovimientoMP unaMovimientoMP) throws MovimientoMPException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					movimientoMPDao.actualizarMovimientoMP(unaMovimientoMP);
				}
			});
		} catch (Exception e) {
			String msg = "La MovimientoMP no pudo actualizarse.";
			throw new MovimientoMPException(msg,e);
		}
	}
	
	public List getMovimientoMPs(final Filtro filtro) throws MovimientoMPException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return movimientoMPDao.listarTodos(filtro);
				}
			});
		} catch (Exception e) {
			String msg = "La lista de MovimientoMP no pudo ser leida.";
			throw new MovimientoMPException(msg,e);
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public MovimientoMPDao getMovimientoMPDao() {
		return movimientoMPDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setMovimientoMPDao(MovimientoMPDao movimientoMPDao) {
		this.movimientoMPDao = movimientoMPDao;
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

package com.bizitglobal.tarjetafiel.fondos.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeLugarDao;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeLugarDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeLugarException;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeLugarNotFoundException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeLugar;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeLugarService;

/**
 *	Implementacion de la interfaz FormaPagoService.
 */
public class ChequeLugarServiceImpl implements ChequeLugarService {
	
	private static Logger logger = Logger.getLogger(ChequeLugarServiceImpl.class);
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ChequeLugarDao chequeLugarDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
	
	public void grabarChequeLugar(final ChequeLugar unaChequeLugar) throws ChequeLugarException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeLugarDao.grabarChequeLugar(unaChequeLugar);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La ChequeLugar ya existe en la base de datos.";
			throw new ChequeLugarDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La ChequeLugar no pudo ser grabada.";
			throw new ChequeLugarException(msg,e);
		}
		
	}
	
	public List getChequeLugares() throws ChequeLugarException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeLugarDao.listarTodos(new Filtro());
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ChequeLugar no pudo ser leida.";
			throw new ChequeLugarException(msg,e);
		}
	}
	
	public ChequeLugar leerChequeLugar(Long id) throws ChequeLugarException {
		try {
			return chequeLugarDao.buscarChequeLugar(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La chequeLugar no existe en la base de datos.";
			throw new ChequeLugarNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La ChequeLugar no pudo leerse desde la base de datos.";
			throw new ChequeLugarException(msg,e);
		}
	}
	
	public void borrarChequeLugar(final Long idChequeLugar) throws ChequeLugarException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeLugarDao.borrarChequeLugar(idChequeLugar);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeLugar no pudo borrase.";
			throw new ChequeLugarException(msg,e);
		}
	}
	
	public void borrarChequeLugar(final ChequeLugar unaChequeLugar) throws ChequeLugarException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeLugarDao.borrarChequeLugar(unaChequeLugar);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeLugar no pudo borrase.";
			throw new ChequeLugarException(msg,e);
		}
	}
	
	public void actualizarChequeLugar(final ChequeLugar unaChequeLugar) throws ChequeLugarException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					chequeLugarDao.actualizarChequeLugar(unaChequeLugar);
				}
			});
		} catch (Exception e) {
			String msg = "La ChequeLugar no pudo actualizarse.";
			throw new ChequeLugarException(msg,e);
		}
	}
	
	public List getChequeLugars(final Filtro filtro) throws ChequeLugarException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return chequeLugarDao.listarTodos(filtro);					
				}
			});
		} catch (Exception e) {
			String msg = "La lista de ChequeLugar no pudo ser leida.";
			throw new ChequeLugarException(msg,e);
		}
	}
	
	public List<Cheque> buscarChequesEnLugar(final Lugar lugar) throws Exception{
		try {
			
			List list = (List)transactionTemplate.execute(new TransactionCallback(){
				public Object doInTransaction(TransactionStatus arg0) {
					return chequeLugarDao.buscarChequesEnLugar(lugar);
				}
			});
			
			return list;
			
		}catch (Exception e) {
			logger.error(e,e);
			throw e;
		}
	}
	
	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ChequeLugarDao getChequeLugarDao() {
		return chequeLugarDao;
	}

	/**
	 * Necesario para spring.
	 * @param monedaDao, Objeto dao a setear.
	 */
	public void setChequeLugarDao(ChequeLugarDao chequeLugarDao) {
		this.chequeLugarDao = chequeLugarDao;
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

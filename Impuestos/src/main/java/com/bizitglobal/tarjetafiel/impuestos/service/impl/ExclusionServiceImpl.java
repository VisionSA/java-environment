package com.bizitglobal.tarjetafiel.impuestos.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.ExclusionDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.ExclusionDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ExclusionException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ExclusionNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;
import com.bizitglobal.tarjetafiel.impuestos.service.ExclusionService;

/**
 *	Implementacion de la interfaz ExclusionService.
 */
public class ExclusionServiceImpl implements ExclusionService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ExclusionDao exclusionDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	
	public void grabarExclusion(final  Exclusion unaExclusion) throws ExclusionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					exclusionDao.grabarExclusion(unaExclusion);
				}
			});
			
		} catch (DataIntegrityViolationException ex) {
			String msg = "La exclusion ya existe en la base de datos.";
			throw new ExclusionDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La exclusion no pudo ser grabada.";
			throw new ExclusionException(msg,e);
		}
	}

	public List getExclusion(final Filtro unFiltro) throws ExclusionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = exclusionDao.listarTodos(unFiltro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de exclusiones no pudo ser leida.";
			throw new ExclusionException(msg,e);
		}
	}
	
	public Exclusion leerExclusion(final Long id) throws ExclusionException {
		
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (Exclusion) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  Exclusion exclusion  =exclusionDao.buscarExclusion(id);
					return exclusion;
					}
			});	
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La exclusion no existe en la base de datos.";
			throw new ExclusionNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La exclusion no pudo leerse desde la base de datos.";
			throw new ExclusionException(msg,e);
		}
		
	}
	
	public void borrarExclusion(final Long idExclusion) throws ExclusionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					exclusionDao.borrarExclusion(idExclusion);
				}
			});
		} catch (Exception e) {
			String msg = "La exclusion no pudo borrase.";
			throw new ExclusionException(msg,e);
		}
	}
	
	public void borrarExclusion(final Exclusion unaExclusion) throws ExclusionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					exclusionDao.borrarExclusion(unaExclusion);
				}
			});
		} catch (Exception e) {
			String msg = "La exclusion no pudo borrase.";
			throw new ExclusionException(msg,e);
		}
	}	
	
	public void actualizarExclusion(final Exclusion unaExclusion) throws ExclusionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					exclusionDao.actualizarExclusion(unaExclusion);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar la exclusion.";
			throw new ExclusionException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ExclusionDao getExclusionDao() {
		return exclusionDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setExclusionDao(ExclusionDao exclusionDao) {
		this.exclusionDao = exclusionDao;
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
	
	public List getExclusiones(Filtro unFiltro) throws ExclusionException {
		return exclusionDao.listarTodos(unFiltro);
	}

}

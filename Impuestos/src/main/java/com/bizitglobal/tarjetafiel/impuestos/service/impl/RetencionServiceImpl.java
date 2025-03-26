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
import com.bizitglobal.tarjetafiel.impuestos.dao.RetencionDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.RetencionDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.RetencionException;
import com.bizitglobal.tarjetafiel.impuestos.exception.RetencionNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TramosRetencionDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TramosRetencionException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;
import com.bizitglobal.tarjetafiel.impuestos.service.RetencionService;

/**
 *	Implementacion de la interfaz RetencionService.
 */
public class RetencionServiceImpl implements RetencionService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private RetencionDao retencionDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	
	public void grabarRetencion(final Retencion unaRetencion) throws RetencionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					retencionDao.grabarRetencion(unaRetencion);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La retencion ya existe en la base de datos.";
			throw new RetencionDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La retencion no pudo ser grabado.";
			throw new RetencionException(msg,e);
		}
	}

	public List getRetenciones(final Filtro filtro) throws RetencionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = retencionDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de retenciones no pudo ser leida.";
			throw new RetencionException(msg,e);
		}
	}
	
	public Retencion leerRetencion(final Long id) throws RetencionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (Retencion) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  Retencion retencion  = retencionDao.buscarRetencion(id);
					return retencion;
					}
			});	
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La retencion no existe en la base de datos.";
			throw new RetencionNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La retencion no pudo leerse desde la base de datos.";
			throw new RetencionException(msg,e);
		}
		
	}
	
	public void borrarRetencion(final Long idRetencion) throws RetencionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					retencionDao.borrarRetencion(idRetencion);
				}
			});
		} catch (Exception e) {
			String msg = "La retencion no pudo borrase.";
			throw new RetencionException(msg,e);
		}
	}
	
	public void borrarRetencion(final Retencion unaRetencion) throws RetencionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					retencionDao.borrarRetencion(unaRetencion);
				}
			});
				} catch (Exception e) {
			String msg = "La retencion no pudo borrase.";
			throw new RetencionException(msg,e);
		}
	}	
	
	public void actualizarRetencion(final Retencion unaRetencion) throws RetencionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					retencionDao.actualizarRetencion(unaRetencion);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar la retencion.";
			throw new RetencionException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public RetencionDao getRetencionDao() {
		return retencionDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setRetencionDao(RetencionDao retencionDao) {
		this.retencionDao = retencionDao;
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

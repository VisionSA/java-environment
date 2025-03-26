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
import com.bizitglobal.tarjetafiel.impuestos.dao.AplicableDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.AplicableDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.AplicableException;
import com.bizitglobal.tarjetafiel.impuestos.exception.AplicableNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Actividad;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Aplicable;
import com.bizitglobal.tarjetafiel.impuestos.service.AplicableService;

/**
 *	Implementacion de la interfaz ProveedorService.
 */
public class AplicableServiceImpl implements AplicableService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private AplicableDao aplicableDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarAplicable(final  Aplicable unaAplicable) throws AplicableException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					aplicableDao.grabarAplicable(unaAplicable);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La aplicable ya existe en la base de datos.";
			throw new AplicableDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La aplicable no pudo ser grabado.";
			throw new AplicableException(msg,e);
		}
	}

	public List getAplicables(final Filtro unFiltro) throws AplicableException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = aplicableDao.listarTodos(unFiltro);
				return lista;
				}
			});
			
		} catch (Exception e) {
			String msg = "La lista de aplicables no pudo ser leida.";
			throw new AplicableException(msg,e);
		}
	}
	
	public Aplicable leerAplicable(final Long id) throws AplicableException {
	
		try {
			 transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (Aplicable) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  Aplicable aplicable = aplicableDao.buscarAplicable(id);
					return aplicable;
					}
				});	
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La aplicable no existe en la base de datos.";
			throw new AplicableNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La aplicable no pudo leerse desde la base de datos.";
			throw new AplicableException(msg,e);
		}
	}
	
	public void borrarAplicable(final Long idAplicable) throws AplicableException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					aplicableDao.borrarAplicable(idAplicable);
				}
			});
		} catch (Exception e) {
			String msg = "La aplicable no pudo borrase.";
			throw new AplicableException(msg,e);
		}
	}
	
	public void borrarAplicable(final Aplicable unaAplicable) throws AplicableException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					aplicableDao.borrarAplicable(unaAplicable);
				}
			});
			
		} catch (Exception e) {
			String msg = "La aplicable no pudo borrase.";
			throw new AplicableException(msg,e);
		}
	}	
	
	public void actualizarAplicable(final Aplicable unaAplicable) throws AplicableException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					aplicableDao.actualizarAplicable(unaAplicable);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar la aplicable.";
			throw new AplicableException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public AplicableDao getAplicableDao() {
		return aplicableDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setAplicableDao(AplicableDao aplicableDao) {
		this.aplicableDao = aplicableDao;
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

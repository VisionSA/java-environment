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

import com.bizitglobal.tarjetafiel.impuestos.dao.IndividuoDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.IndividuoDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.IndividuoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.IndividuoNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.ImpuestosIndividuo;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Individuo;
import com.bizitglobal.tarjetafiel.impuestos.service.IndividuoService;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

/**
 *	Implementacion de la interfaz IndividuoService.
 */
public class IndividuoServiceImpl implements IndividuoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private IndividuoDao individuoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public Long grabarIndividuo(final Individuo unIndividuo) throws IndividuoException {
	/*	final Long result = null;
		try {transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected Object doInTransactionWithoutResult(TransactionStatus arg0) {				
				 Long valor= individuoDao.grabarIndividuo(unIndividuo);
			}
		   });
		} catch (DataIntegrityViolationException ex) {
			String msg = "El individuo existe en la base de datos.";
			throw new IndividuoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El individuo no pudo ser grabado.";
			throw new IndividuoException(msg,e);
		}
		
		return result;*/
		return null;
	}

	public List getIndividuos(final Filtro unFiltro) throws IndividuoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = individuoDao.listarTodos(unFiltro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de individuos no pudo ser leida.";
			throw new IndividuoException(msg,e);
		}
	}
	
	public Individuo leerIndividuo(final Long id) throws IndividuoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (Individuo) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  Individuo individuo  =individuoDao.buscarIndividuo(id);
					return individuo;
					}
			});	
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El individuo no existe en la base de datos.";
			throw new IndividuoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El individuo no pudo leerse desde la base de datos.";
			throw new IndividuoException(msg,e);
		}
	}
	
	public void borrarIndividuo(final Long idIndividuo) throws IndividuoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					individuoDao.borrarIndividuo(idIndividuo);
				}
			});
		} catch (Exception e) {
			String msg = "El individuo no pudo borrase.";
			throw new IndividuoException(msg,e);
		}
	}
	
	public void borrarIndividuo(final Individuo unIndividuo) throws IndividuoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					individuoDao.borrarIndividuo(unIndividuo);
				}
			});
		} catch (Exception e) {
			String msg = "El individuo no pudo borrase.";
			throw new IndividuoException(msg,e);
		}
	}	
	
	public void actualizarIndividuo(final Individuo unIndividuo) throws IndividuoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					individuoDao.actualizarIndividuo(unIndividuo);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar el individuo.";
			throw new IndividuoException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public IndividuoDao getIndividuoDao() {
		return individuoDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setCategoriaDao(IndividuoDao individuoDao) {
		this.individuoDao = individuoDao;
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

	/**
	 * Necesario para spring.
	 * @param individuoDao, Objeto setear.
	 */
	public void setIndividuoDao(IndividuoDao individuoDao) {
		this.individuoDao = individuoDao;
	}

}

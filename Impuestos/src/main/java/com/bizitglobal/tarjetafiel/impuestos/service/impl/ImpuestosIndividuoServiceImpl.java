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
import com.bizitglobal.tarjetafiel.impuestos.dao.ImpuestosIndividuoDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestosIndividuoDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestosIndividuoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestosIndividuoNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;
import com.bizitglobal.tarjetafiel.impuestos.negocio.ImpuestosIndividuo;
import com.bizitglobal.tarjetafiel.impuestos.service.ImpuestosIndividuoService;

/**
 *	Implementacion de la interfaz ProveedorService.
 */
public class ImpuestosIndividuoServiceImpl implements ImpuestosIndividuoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ImpuestosIndividuoDao impuestosIndividuoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	
	
	public void grabarImpuestosIndividuo(final ImpuestosIndividuo unImpuestosIndividuo) throws ImpuestosIndividuoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					impuestosIndividuoDao.grabarImpuestosIndividuo(unImpuestosIndividuo);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El impuesto individuo ya existe en la base de datos.";
			throw new ImpuestosIndividuoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El impuesto individuos no pudo ser grabado.";
			throw new ImpuestosIndividuoException(msg,e);
		}
	}

	public List getImpuestosIndividuo(final Filtro unFiltro) throws ImpuestosIndividuoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = impuestosIndividuoDao.listarTodos(unFiltro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de impuestos individuos no pudo ser leida.";
			throw new ImpuestosIndividuoException(msg,e);
		}
	}
	
	public ImpuestosIndividuo leerImpuestosIndividuo(final Long id) throws ImpuestosIndividuoException {
		
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (ImpuestosIndividuo) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  ImpuestosIndividuo impuesto  =impuestosIndividuoDao.buscarImpuestosIndividuo(id);
					return impuesto;
					}
			});	
			
			
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El impuesto Individuo no existe en la base de datos.";
			throw new ImpuestosIndividuoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El impuesto individuo no pudo leerse desde la base de datos.";
			throw new ImpuestosIndividuoException(msg,e);
		}
		
	}
	
	public void borrarImpuestosIndividuo(final Long idImpuestosIndividuo) throws ImpuestosIndividuoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					impuestosIndividuoDao.borrarImpuestosIndividuo(idImpuestosIndividuo);
				}
			});
		} catch (Exception e) {
			String msg = "El impuesto Individuo no pudo borrase.";
			throw new ImpuestosIndividuoException(msg,e);
		}
	}
	
	public void borrarImpuestosIndividuo(final ImpuestosIndividuo unImpuestosIndividuo) throws ImpuestosIndividuoException {
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					impuestosIndividuoDao.borrarImpuestosIndividuo(unImpuestosIndividuo);
				}
			});
		} catch (Exception e) {
			String msg = "El impuesto individuos no pudo borrase.";
			throw new ImpuestosIndividuoException(msg,e);
		}
	}	
	

	public List getImpuestos(final Filtro unFiltro) throws ImpuestosIndividuoException {
		try {
			return impuestosIndividuoDao.listarTodos(unFiltro);
		} catch (Exception e) {
			String msg = "La lista de impuestos individuos no pudo ser leida.";
			throw new ImpuestosIndividuoException(msg,e);
		}
	}
	
	
	

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ImpuestosIndividuoDao getImpuestosIndividuoDao() {
		return impuestosIndividuoDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setImpuestosIndividuoDao(ImpuestosIndividuoDao impuestosIndividuoDao) {
		this.impuestosIndividuoDao = impuestosIndividuoDao;
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

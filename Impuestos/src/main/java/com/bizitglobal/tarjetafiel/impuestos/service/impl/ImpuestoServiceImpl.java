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
import com.bizitglobal.tarjetafiel.impuestos.dao.ImpuestoDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestoDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.ImpuestoNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;
import com.bizitglobal.tarjetafiel.impuestos.service.ImpuestoService;

/**
 *	Implementacion de la interfaz ProveedorService.
 */
public class ImpuestoServiceImpl implements ImpuestoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private ImpuestoDao impuestoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarImpuesto(final Impuesto unImpuesto) throws ImpuestoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					impuestoDao.grabarImpuesto(unImpuesto);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El impuesto ya existe en la base de datos.";
			throw new ImpuestoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El impuesto no pudo ser grabado.";
			throw new ImpuestoException(msg,e);
		}
	}

	public List getImpuestos(final Filtro unFiltro) throws ImpuestoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = impuestoDao.listarTodos(unFiltro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de impuestos no pudo ser leida.";
			throw new ImpuestoException(msg,e);
		}
	}
	
	public Impuesto leerImpuesto(final Long id) throws ImpuestoException {
		
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (Impuesto) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  Impuesto impuesto  =impuestoDao.buscarImpuesto(id);
					return impuesto;
					}
			});	
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El impuesto no existe en la base de datos.";
			throw new ImpuestoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El impuesto no pudo leerse desde la base de datos.";
			throw new ImpuestoException(msg,e);
		}
	}
	
	public void borrarImpuesto(final Long idImpuesto) throws ImpuestoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					impuestoDao.borrarImpuesto(idImpuesto);
				}
			});
		} catch (Exception e) {
			String msg = "El impuesto no pudo borrase.";
			throw new ImpuestoException(msg,e);
		}
	}
	
	public void borrarImpuesto(final Impuesto unImpuesto) throws ImpuestoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					impuestoDao.borrarImpuesto(unImpuesto);
				}
			});
		} catch (Exception e) {
			String msg = "El impuesto no pudo borrase.";
			throw new ImpuestoException(msg,e);
		}
	}	
	
	public void actualizarImpuesto(final Impuesto unImpuesto) throws ImpuestoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					impuestoDao.actualizarImpuesto(unImpuesto);
				}
			});
		} catch (Exception e) {
			String msg = "No se pudo actualizar el impuesto.";
			throw new ImpuestoException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public ImpuestoDao getImpuestoDao() {
		return impuestoDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setImpuestoDao(ImpuestoDao impuestoDao) {
		this.impuestoDao = impuestoDao;
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

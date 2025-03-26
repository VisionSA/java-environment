package com.bizitglobal.tarjetafiel.impuestos.service.impl;
import java.util.List;

import org.hibernate.NonUniqueObjectException;
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
import com.bizitglobal.tarjetafiel.impuestos.dao.TipoImpuestoDao;
import com.bizitglobal.tarjetafiel.impuestos.exception.TipoImpuestoDuplicateException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TipoImpuestoException;
import com.bizitglobal.tarjetafiel.impuestos.exception.TipoImpuestoNotFoundException;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;
import com.bizitglobal.tarjetafiel.impuestos.service.TipoImpuestoService;

/**
*	Implementacion de la interfaz TipoImpuestoService.
*/
public class TipoImpuestoServiceImpl implements TipoImpuestoService {
	/**
	* Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	*/
	private TipoImpuestoDao tipoImpuestoDao;

	/**
	* Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	*/
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	

	public void grabarTipoImpuesto (final TipoImpuesto pObject) throws TipoImpuestoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoImpuestoDao.grabarTipoImpuesto(pObject);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "La tipoimpuesto ya existe en la base de datos.";
			throw new TipoImpuestoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "La tipoimpuesto no pudo ser grabada.";
			throw new TipoImpuestoException(msg,e);
		}
	}
	
	public List getTipoImpuesto(final Filtro filtro) throws TipoImpuestoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = tipoImpuestoDao.listarTodos(filtro);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de tipoimpuesto no pudo ser leida.";
			throw new TipoImpuestoException(msg,e);
		}
	}
	
	public TipoImpuesto leerTipoImpuesto (final Long id) throws TipoImpuestoException {
		TipoImpuesto result = null;
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (TipoImpuesto) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  TipoImpuesto tipoImpuesto  = tipoImpuestoDao.buscarTipoImpuesto(id);
					return tipoImpuesto;
					}
			});	
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "La TipoImpuesto no existe en la base de datos.";
			throw new TipoImpuestoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "La tipoimpuesto no pudo leerse desde la base de datos.";
			throw new TipoImpuestoException(msg,e);
		}
	}
	
	public void borrarTipoImpuesto (final Long id) throws TipoImpuestoException {
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoImpuestoDao.borrarTipoImpuesto(id);
				}
			});
		} catch (Exception e) {
			String msg = "La tipoimpuesto no pudo borrase.";
			throw new TipoImpuestoException(msg,e);
		}
	}
	public void borrarTipoImpuesto(final TipoImpuesto pObject) throws TipoImpuestoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoImpuestoDao.borrarTipoImpuesto(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "La tipoimpuesto no pudo borrase.";
			throw new TipoImpuestoException(msg,e);
		}
	}
	
	public void actualizarTipoImpuesto (final TipoImpuesto pObject) throws TipoImpuestoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					tipoImpuestoDao.actualizarTipoImpuesto(pObject);
				}
			});
		} catch (Exception e) {
			String msg = "La tipoimpuesto no pudo actualizarse.";
			throw new TipoImpuestoException(msg,e);
		}
	}
	/**
	* Necesario para spring.
	* @return Retorna el objeto dao.
	*/
	public TipoImpuestoDao getTipoImpuestoDao() {
		return tipoImpuestoDao;
	}
	/**
	* Necesario para spring.
	* @param permisoDao, Objeto dao a setear.
	*/
	public void setTipoImpuestoDao(TipoImpuestoDao tipoImpuestoDao) {
		this.tipoImpuestoDao = tipoImpuestoDao;
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


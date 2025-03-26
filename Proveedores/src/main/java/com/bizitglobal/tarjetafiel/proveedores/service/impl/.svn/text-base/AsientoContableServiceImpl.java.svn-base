package com.bizitglobal.tarjetafiel.proveedores.service.impl;

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
import com.bizitglobal.tarjetafiel.proveedores.dao.AsientoContableDao;
import com.bizitglobal.tarjetafiel.proveedores.exception.AsientoContableDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.exception.AsientoContableException;
import com.bizitglobal.tarjetafiel.proveedores.exception.AsientoContableNotFoundException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.AsientoContable;
import com.bizitglobal.tarjetafiel.proveedores.service.AsientoContableService;

/**
 *	Implementacion de la interfaz AsientoContableService.
 */
public class AsientoContableServiceImpl implements AsientoContableService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private AsientoContableDao asientoContableDao;
	private TransactionTemplate transactionTemplate;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	
	public void grabarAsientoContable(final AsientoContable unAsientoContable) 
			throws AsientoContableException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoContableDao.grabarAsientoContable(unAsientoContable);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El asiento contable ya existe en la base de datos.";
			throw new AsientoContableDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El asiento contable no pudo ser grabado.";
			throw new AsientoContableException(msg,e);
		}
	}

	public List getAsientoContables() throws AsientoContableException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return asientoContableDao.listarTodos();
				}
			});
		
		}
		catch (Exception e) {
			String msg = "La lista de asientos contables no pudo ser leida.";
			throw new AsientoContableException(msg,e);
		}
	}
	
	public AsientoContable leerAsientoContable(Integer id) throws AsientoContableException {
		AsientoContable result = null;
		
		try {
			result = asientoContableDao.buscarAsientoContable(id);
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El asiento contable no existe en la base de datos.";
			throw new AsientoContableNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El asiento contable no pudo leerse desde la base de datos.";
			throw new AsientoContableException(msg,e);
		}
		
		return result;
	}
	
	public void borrarAsientoContable(final Integer idAsientoContable) 
			throws AsientoContableException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoContableDao.borrarAsientoContable(idAsientoContable);
				}
			});
		}
		catch (Exception e) {
			String msg = "El asiento contable no pudo borrase.";
			throw new AsientoContableException(msg,e);
		}
	}
	
	public void borrarAsientoContable(final AsientoContable unAsientoContable) 
			throws AsientoContableException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoContableDao.borrarAsientoContable(unAsientoContable);
				}
			});
		}
		 catch (Exception e) {
			String msg = "El asiento contable no pudo borrase.";
			throw new AsientoContableException(msg,e);
		}
	}	
	
	public void actualizarAsientoContable(final AsientoContable unAsientoContable) 
			throws AsientoContableException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					asientoContableDao.actualizarAsientoContable(unAsientoContable);
				}
			});
		} 
		catch (Exception e) {
			String msg = "No se pudo actualizar el asiento contable.";
			throw new AsientoContableException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public AsientoContableDao getAsientoContableDao() {
		return asientoContableDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setAsientoContableDao(AsientoContableDao AsientoContableDao) {
		this.asientoContableDao = AsientoContableDao;
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

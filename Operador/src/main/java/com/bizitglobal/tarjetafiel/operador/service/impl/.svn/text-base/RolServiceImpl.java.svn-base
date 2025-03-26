package com.bizitglobal.tarjetafiel.operador.service.impl;

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

import com.bizitglobal.tarjetafiel.operador.dao.RolDao;
import com.bizitglobal.tarjetafiel.operador.exeption.RolDuplicateException;
import com.bizitglobal.tarjetafiel.operador.exeption.RolException;
import com.bizitglobal.tarjetafiel.operador.exeption.RolNotFoundException;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItem;
import com.bizitglobal.tarjetafiel.operador.negocio.Rol;
import com.bizitglobal.tarjetafiel.operador.service.RolService;

/**
 *	Implementacion de la interfaz RolService.
 */
public class RolServiceImpl implements RolService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private RolDao rolDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarRol(final Rol unRol) throws RolException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					rolDao.grabarRol(unRol);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El rol ya existe en la base de datos.";
			throw new RolDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El rol no pudo ser grabado.";
			throw new RolException(msg,e);
		}
	}

	public List getRoles() throws RolException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = rolDao.listarTodos();
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de rols no pudo ser leida.";
			throw new RolException(msg,e);
		}
	}
	
	public Rol leerRol(final Long id) throws RolException {
		
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (Rol) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  Rol rol =rolDao.buscarRol(id);
					return rol;
					}
				});	 
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El rol no existe en la base de datos.";
			throw new RolNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El rol no pudo leerse desde la base de datos.";
			throw new RolException(msg,e);
		}
	}
	
	public void borrarRol(final Long idRol) throws RolException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					rolDao.borrarRol(idRol);
				}
			});
		} catch (Exception e) {
			String msg = "El rol no pudo borrase.";
			throw new RolException(msg,e);
		}
	}
	
	public void grabarOActualizarRol(final Rol unRol) throws RolException {
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					rolDao.grabarOActualizarRol(unRol);
				}
			});
		} catch (Exception e) {
			String msg = "El rol no pudo ser grabado.";
			throw new RolException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public RolDao getRolDao() {
		return rolDao;
	}

	/**
	 * Necesario para spring.
	 * @param rolDao, Objeto dao a setear.
	 */
	public void setRolDao(RolDao rolDao) {
		this.rolDao = rolDao;
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
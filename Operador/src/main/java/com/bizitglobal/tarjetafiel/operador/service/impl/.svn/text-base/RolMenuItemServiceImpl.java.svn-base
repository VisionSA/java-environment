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

import com.bizitglobal.tarjetafiel.operador.dao.RolMenuItemDao;
import com.bizitglobal.tarjetafiel.operador.exeption.RolMenuItemDuplicateException;
import com.bizitglobal.tarjetafiel.operador.exeption.RolMenuItemException;
import com.bizitglobal.tarjetafiel.operador.exeption.RolMenuItemNotFoundException;
import com.bizitglobal.tarjetafiel.operador.negocio.Permiso;
import com.bizitglobal.tarjetafiel.operador.negocio.RolMenuItem;
import com.bizitglobal.tarjetafiel.operador.service.RolMenuItemService;

/**
 *	Implementacion de la interfaz RolMenuItemService.
 */
public class RolMenuItemServiceImpl implements RolMenuItemService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private RolMenuItemDao rolMenuItemDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.RolMenuItemService#grabarRolMenuItem(com.bizitglobal.tarjetafiel.acceso.negocio.RolMenuItem, java.util.Iterator)
	 */
	public void grabarRolMenuItem(final RolMenuItem unRolMenuItem) throws RolMenuItemException {
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					rolMenuItemDao.saveRolMenuItem(unRolMenuItem);
				}
			});
			
		} catch (DataIntegrityViolationException ex) {
			String msg = "El rolMenuItem ya existe en la base de datos.";
			throw new RolMenuItemDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El rolMenuItem no pudo ser grabado.";
			throw new RolMenuItemException(msg,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.RolMenuItemService#getRolMenuItems(java.util.Iterator)
	 */
	public List getRolMenuItems(final Long idRol) throws RolMenuItemException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista =  rolMenuItemDao.listAll(idRol);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de rolMenuItems no pudo ser leida.";
			throw new RolMenuItemException(msg,e);
		}
	}
	
	public List getRolMenuItems(final Long idRol,final Long idMenu) throws RolMenuItemException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista =  rolMenuItemDao.listAll(idRol,idMenu);
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de rolMenuItems no pudo ser leida.";
			throw new RolMenuItemException(msg,e);
		}
	}	
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.RolMenuItemService#leerRolMenuItem(java.lang.Integer, java.util.Iterator)
	 */
	public RolMenuItem leerRolMenuItem(final Long id) throws RolMenuItemException {
		
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (RolMenuItem) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  RolMenuItem rolMenuItem = rolMenuItemDao.findRolMenuItem(id);
					return rolMenuItem;
					}
				});	 
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El rolMenuItem no existe en la base de datos.";
			throw new RolMenuItemNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El rolMenuItem no pudo leerse desde la base de datos.";
			throw new RolMenuItemException(msg,e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.RolMenuItemService#borrarRolMenuItem(java.lang.Integer)
	 */
	public void borrarRolMenuItem(final Long idRolMenuItem) throws RolMenuItemException {
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					rolMenuItemDao.deleteRolMenuItem(idRolMenuItem);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			//transactionManager.rollback(status);
			String msg = "El rolMenuItem no pudo borrase.";
			throw new RolMenuItemException(msg,e);
		}
	}
	
	public void borrarRolMenuItem(RolMenuItem obj) throws RolMenuItemException {
		try {
			rolMenuItemDao.deleteRolMenuItem(obj);
		} catch (Exception e) {
			e.printStackTrace();
			//transactionManager.rollback(status);
			String msg = "El rolMenuItem no pudo borrase.";
			throw new RolMenuItemException(msg,e);
		}
	}	

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public RolMenuItemDao getRolMenuItemDao() {
		return rolMenuItemDao;
	}

	/**
	 * Necesario para spring.
	 * @param rolMenuItemDao, Objeto dao a setear.
	 */
	public void setRolMenuItemDao(RolMenuItemDao rolMenuItemDao) {
		this.rolMenuItemDao = rolMenuItemDao;
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
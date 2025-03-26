package com.bizitglobal.tarjetafiel.operador.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.operador.dao.PermisoDao;
import com.bizitglobal.tarjetafiel.operador.exeption.PermisoDuplicateException;
import com.bizitglobal.tarjetafiel.operador.exeption.PermisoException;
import com.bizitglobal.tarjetafiel.operador.exeption.PermisoNotFoundException;
import com.bizitglobal.tarjetafiel.operador.negocio.Pagina;
import com.bizitglobal.tarjetafiel.operador.negocio.Permiso;
import com.bizitglobal.tarjetafiel.operador.service.PermisoService;

/**
 *	Implementacion de la interfaz PermisoService.
 */
public class PermisoServiceImpl implements PermisoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private PermisoDao permisoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.PermisoService#grabarPermiso(com.bizitglobal.tarjetafiel.acceso.negocio.Permiso, java.util.Iterator)
	 */
	public void grabarPermiso(final Permiso unPermiso) throws PermisoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					permisoDao.savePermiso(unPermiso);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El permiso ya existe en la base de datos.";
			throw new PermisoDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El permiso no pudo ser grabado.";
			throw new PermisoException(msg,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.PermisoService#getPermisos(java.util.Iterator)
	 */
	public List getPermisos() throws PermisoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista =  permisoDao.listAll();
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de permisos no pudo ser leida.";
			throw new PermisoException(msg,e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.PermisoService#leerPermiso(java.lang.Integer, java.util.Iterator)
	 */
	public Permiso leerPermiso(final Long id) throws PermisoException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (Permiso) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  Permiso permiso = permisoDao.findPermiso(id);
					return permiso;
					}
				});	 
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El permiso no existe en la base de datos.";
			throw new PermisoNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El permiso no pudo leerse desde la base de datos.";
			throw new PermisoException(msg,e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.PermisoService#borrarPermiso(java.lang.Integer)
	 */
	public void borrarPermiso(final Long idPermiso) throws PermisoException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					permisoDao.deletePermiso(idPermiso);
				}
			});
		} catch (Exception e) {
			String msg = "El permiso no pudo borrase.";
			throw new PermisoException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public PermisoDao getPermisoDao() {
		return permisoDao;
	}

	/**
	 * Necesario para spring.
	 * @param permisoDao, Objeto dao a setear.
	 */
	public void setPermisoDao(PermisoDao permisoDao) {
		this.permisoDao = permisoDao;
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
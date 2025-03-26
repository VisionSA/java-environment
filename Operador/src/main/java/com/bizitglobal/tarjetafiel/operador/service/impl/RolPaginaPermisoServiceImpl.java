package com.bizitglobal.tarjetafiel.operador.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bizitglobal.tarjetafiel.operador.dao.RolPaginaPermisoDao;
import com.bizitglobal.tarjetafiel.operador.exeption.RolPaginaPermisoException;
import com.bizitglobal.tarjetafiel.operador.negocio.Permiso;
import com.bizitglobal.tarjetafiel.operador.negocio.RolPaginaPermiso;
import com.bizitglobal.tarjetafiel.operador.service.RolPaginaPermisoService;

/**
 *	Implementacion de la interfaz PermisoService.
 */
public class RolPaginaPermisoServiceImpl implements RolPaginaPermisoService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private RolPaginaPermisoDao rolPaginaPermisoDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	public void grabarRolPaginaPermiso(final RolPaginaPermiso obj) throws RolPaginaPermisoException {
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					rolPaginaPermisoDao.saveRolPaginaPermiso(obj);
				}
			});

			
		} catch (DataIntegrityViolationException ex) {
			String msg = "El operador ya existe en la base de datos.";
			throw new RolPaginaPermisoException(msg,ex);
		} catch (Exception e) {
			String msg = "El operador no pudo ser grabado.";
			throw new RolPaginaPermisoException(msg,e);
		}
	}
	
	public void borrarRolPaginaPermiso(final Long id) throws RolPaginaPermisoException {
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					rolPaginaPermisoDao.deleteRolPaginaPermiso(id);
				}
			});
		} catch (Exception e) {
			String msg = "El operador no pudo ser borrado.";
			throw new RolPaginaPermisoException(msg,e);
		}		
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.operador.service.RolPaginaService#getPermisosPorPaginaYRol(java.lang.String, java.lang.Long)
	 */
	public List getPermisosPorPaginaYRol(final String unaPagina,final Long idRol) throws RolPaginaPermisoException {
		
		
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List result = new ArrayList();	
				List rolPermisos = rolPaginaPermisoDao.listAll(unaPagina, idRol);
				if(!rolPermisos.isEmpty()) {
					Iterator rolP = rolPermisos.iterator();
					while(rolP.hasNext()) {
						Permiso permiso = ((RolPaginaPermiso)rolP.next()).getPermiso(); 
						if(!result.contains(permiso)) {
							result.add(permiso);
						}
					}
				}
				return result;
				}
			});
						
			
		} catch (Exception e) {
			String msg = "El rolPagina no pudo leerse.";
			throw new RolPaginaPermisoException(msg,e);
		}
	}
	
	public List getPaginasPorRol(final Long idRol) throws RolPaginaPermisoException {
		
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista =  rolPaginaPermisoDao.listAll(idRol);
				return lista;
				}
			});
			
		} catch (Exception e) {
			String msg = "El rolPagina no pudo leerse.";
			throw new RolPaginaPermisoException(msg,e);
		}
	}	

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public RolPaginaPermisoDao getRolPaginaPermisoDao() {
		return rolPaginaPermisoDao;
	}

	public void setRolPaginaPermisoDao(RolPaginaPermisoDao rolPaginaPermisoDao) {
		this.rolPaginaPermisoDao = rolPaginaPermisoDao;
	}
	
}
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

import com.bizitglobal.tarjetafiel.operador.dao.MenuItemRelacionDao;
import com.bizitglobal.tarjetafiel.operador.exeption.MenuItemRelacionDuplicateException;
import com.bizitglobal.tarjetafiel.operador.exeption.MenuItemRelacionException;
import com.bizitglobal.tarjetafiel.operador.exeption.MenuItemRelacionNotFoundException;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItemRelacion;
import com.bizitglobal.tarjetafiel.operador.service.MenuItemRelacionService;

/**
 *	Implementacion de la interfaz MenuItemRelacionService.
 */
public class MenuItemRelacionServiceImpl implements MenuItemRelacionService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private MenuItemRelacionDao menuItemRelacionDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	
	
	
	public void grabarMenuItemRelacion(final MenuItemRelacion unMenuItemRelacion) throws MenuItemRelacionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					menuItemRelacionDao.saveMenuItemRelacion(unMenuItemRelacion);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El MenuItemRelacion ya existe en la base de datos.";
			throw new MenuItemRelacionDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El MenuItemRelacion no pudo ser grabado.";
			throw new MenuItemRelacionException(msg,e);
		}
	}

	
	public List getMenuItemRelaciones() throws MenuItemRelacionException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = menuItemRelacionDao.listAll();
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de MenuItemRelacions no pudo ser leida.";
			throw new MenuItemRelacionException(msg,e);
		}
	}
	
	
	public MenuItemRelacion leerMenuItemRelacion(final Long id) throws MenuItemRelacionException {
		
		try { transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (MenuItemRelacion) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  MenuItemRelacion menuItemRelacion = menuItemRelacionDao.findMenuItemRelacion(id);
					return menuItemRelacion;
					}
				});	  
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El MenuItemRelacion no existe en la base de datos.";
			throw new MenuItemRelacionNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El MenuItemRelacion no pudo leerse desde la base de datos.";
			throw new MenuItemRelacionException(msg,e);
		}
	}
	
	public void borrarMenuItemRelacion(final Long idMenuItemRelacion) throws MenuItemRelacionException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					menuItemRelacionDao.deleteMenuItemRelacion(idMenuItemRelacion);
				}
			});
		} catch (Exception e) {
			String msg = "El MenuItemRelacion no pudo borrase.";
			throw new MenuItemRelacionException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public MenuItemRelacionDao getMenuItemRelacionDao() {
		return menuItemRelacionDao;
	}

	/**
	 * Necesario para spring.
	 * @param menuItemRelacionDao, Objeto dao a setear.
	 */
	public void setMenuItemRelacionDao(MenuItemRelacionDao menuItemRelacionDao) {
		this.menuItemRelacionDao = menuItemRelacionDao;
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
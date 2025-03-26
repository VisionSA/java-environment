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

import com.bizitglobal.tarjetafiel.operador.dao.MenuItemDao;
import com.bizitglobal.tarjetafiel.operador.exeption.MenuItemDuplicateException;
import com.bizitglobal.tarjetafiel.operador.exeption.MenuItemException;
import com.bizitglobal.tarjetafiel.operador.exeption.MenuItemNotFoundException;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItem;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItemRelacion;
import com.bizitglobal.tarjetafiel.operador.service.MenuItemService;

/**
 *	Implementacion de la interfaz MenuItemService.
 */
public class MenuItemServiceImpl implements MenuItemService {
	
	/**
	 * Objeto pasado por el contenedor de spring, permite ejecutar las operaciones con la base de datos.
	 */
	private MenuItemDao menuItemDao;
	
	/**
	 * Objeto pasado por el contenedor de spring, permite manejar las transacciones. 
	 */
	private PlatformTransactionManager transactionManager;
	private TransactionTemplate transactionTemplate;
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.MenuItemService#grabarMenuItem(com.bizitglobal.tarjetafiel.acceso.negocio.MenuItem, java.util.Iterator)
	 */
	public void grabarMenuItem(final MenuItem unMenuItem) throws MenuItemException {
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					menuItemDao.saveMenuItem(unMenuItem);
				}
			});
		} catch (DataIntegrityViolationException ex) {
			String msg = "El menuItem ya existe en la base de datos.";
			throw new MenuItemDuplicateException(msg,ex);
		} catch (Exception e) {
			String msg = "El menuItem no pudo ser grabado.";
			throw new MenuItemException(msg,e);
		}
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.MenuItemService#getMenuItems(java.util.Iterator)
	 */
	public List getMenuItems() throws MenuItemException {
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			return (List) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
				List lista = menuItemDao.listAll();
				return lista;
				}
			});
		} catch (Exception e) {
			String msg = "La lista de menuItems no pudo ser leida.";
			throw new MenuItemException(msg,e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.MenuItemService#leerMenuItem(java.lang.Integer, java.util.Iterator)
	 */
	public MenuItem leerMenuItem(final Long id) throws MenuItemException {
		
		try {
			transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		      return (MenuItem) transactionTemplate.execute(new TransactionCallback() { 
		    	  public Object doInTransaction(TransactionStatus status) {
		    		  MenuItem menuItem = menuItemDao.findMenuItem(id);
					return menuItem;
					}
				});	 
		} catch (HibernateObjectRetrievalFailureException ex) {
			String msg = "El menuItem no existe en la base de datos.";
			throw new MenuItemNotFoundException(msg,ex);
		} catch (Exception e) {
			String msg = "El menuItem no pudo leerse desde la base de datos.";
			throw new MenuItemException(msg,e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.service.MenuItemService#borrarMenuItem(java.lang.Integer)
	 */
	public void borrarMenuItem(final Long idMenuItem) throws MenuItemException {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {				
					menuItemDao.deleteMenuItem(idMenuItem);
				}
			});
		} catch (Exception e) {
			String msg = "El menuItem no pudo borrase.";
			throw new MenuItemException(msg,e);
		}
	}

	/**
	 * Necesario para spring.
	 * @return Retorna el objeto dao.
	 */
	public MenuItemDao getMenuItemDao() {
		return menuItemDao;
	}

	/**
	 * Necesario para spring.
	 * @param menuItemDao, Objeto dao a setear.
	 */
	public void setMenuItemDao(MenuItemDao menuItemDao) {
		this.menuItemDao = menuItemDao;
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
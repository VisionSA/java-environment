package com.bizitglobal.tarjetafiel.operador.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.operador.dao.*;
import com.bizitglobal.tarjetafiel.operador.negocio.*;


/**
 *	Esta clase implementa la interface de acceso a datos para el objeto cliente.
 */
public class MenuItemRelacionDaoHibernateImpl extends HibernateDaoSupport implements MenuItemRelacionDao {

	/**
	 * Constructor por defecto.
	 */
	public MenuItemRelacionDaoHibernateImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.operador.dao.MenuItemRelacionDao#saveMenuItemRelacion(com.bizitglobal.tarjetafiel.operador.negocio.MenuItemRelacion)
	 */
	public void saveMenuItemRelacion(MenuItemRelacion unMenuItemRelacion) {
		this.getHibernateTemplate().save(unMenuItemRelacion);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.operador.dao.MenuItemRelacionDao#findMenuItemRelacion(java.lang.Long)
	 */
	public MenuItemRelacion findMenuItemRelacion(Long id) {
		return (MenuItemRelacion) this.getHibernateTemplate().get(MenuItemRelacion.class,id);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.operador.dao.MenuItemRelacionDao#deleteMenuItemRelacion(java.lang.Long)
	 */
	public void deleteMenuItemRelacion(Long id) {
		deleteMenuItemRelacion(findMenuItemRelacion(id));
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.operador.dao.MenuItemRelacionDao#deleteMenuItemRelacion(com.bizitglobal.tarjetafiel.operador.negocio.MenuItemRelacion)
	 */
	public void deleteMenuItemRelacion(MenuItemRelacion unMenuItemRelacion) {
		this.getHibernateTemplate().delete(unMenuItemRelacion);
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.operador.dao.MenuItemRelacionDao#updateMenuItemRelacion(com.bizitglobal.tarjetafiel.operador.negocio.MenuItemRelacion)
	 */
	public void updateMenuItemRelacion(MenuItemRelacion unMenuItemRelacion) {
		this.getHibernateTemplate().update(unMenuItemRelacion);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.operador.dao.MenuItemRelacionDao#listAll()
	 */
	public List listAll() {
		return new ArrayList();
	}
		

}

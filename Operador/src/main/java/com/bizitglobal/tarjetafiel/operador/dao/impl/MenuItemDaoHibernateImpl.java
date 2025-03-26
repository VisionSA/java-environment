package com.bizitglobal.tarjetafiel.operador.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.operador.dao.*;
import com.bizitglobal.tarjetafiel.operador.negocio.*;


/**
 *	Esta clase implementa la interface de acceso a datos para el objeto cliente.
 */
public class MenuItemDaoHibernateImpl extends HibernateDaoSupport implements MenuItemDao {

	/**
	 * Constructor por defecto.
	 */
	public MenuItemDaoHibernateImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.dao.MenuItemDao#saveMenuItem(com.bizitglobal.tarjetafiel.acceso.negocio.MenuItem)
	 */
	public void saveMenuItem(MenuItem unMenuItem) {
		this.getHibernateTemplate().save(unMenuItem);
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.MenuItemDao#findMenuItem(java.lang.Integer)
	 */
	public MenuItem findMenuItem(Long id) {
		return (MenuItem) this.getHibernateTemplate().get(MenuItem.class,id);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.MenuItemDao#deleteMenuItem(java.lang.Integer)
	 */
	public void deleteMenuItem(Long id) {
		deleteMenuItem(findMenuItem(id));
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.MenuItemDao#deleteMenuItem(com.bizitglobal.tajetafiel.acceso.negocio.MenuItem)
	 */
	public void deleteMenuItem(MenuItem unMenuItem) {
		this.getHibernateTemplate().delete(unMenuItem);
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.MenuItemDao#updateMenuItem(com.bizitglobal.tajetafiel.acceso.negocio.MenuItem)
	 */
	public void updateMenuItem(MenuItem unMenuItem) {
		this.getHibernateTemplate().update(unMenuItem);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.MenuItemDao#listAll()
	 */
	public List listAll() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT menuItem ");
				sb.append("FROM MenuItem menuItem ");
				sb.append("ORDER BY menuItem.label ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}

}

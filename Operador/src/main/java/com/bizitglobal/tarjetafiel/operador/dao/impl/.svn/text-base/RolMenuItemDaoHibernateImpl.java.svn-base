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
public class RolMenuItemDaoHibernateImpl extends HibernateDaoSupport implements RolMenuItemDao {

	/**
	 * Constructor por defecto.
	 */
	public RolMenuItemDaoHibernateImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.dao.RolMenuItemDao#saveRolMenuItem(com.bizitglobal.tarjetafiel.acceso.negocio.RolMenuItem)
	 */
	public void saveRolMenuItem(RolMenuItem unRolMenuItem) {
		this.getHibernateTemplate().save(unRolMenuItem);
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.RolMenuItemDao#findRolMenuItem(java.lang.Integer)
	 */
	public RolMenuItem findRolMenuItem(Long id) {
		return (RolMenuItem) this.getHibernateTemplate().get(RolMenuItem.class,id);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.RolMenuItemDao#deleteRolMenuItem(java.lang.Integer)
	 */
	public void deleteRolMenuItem(Long id) {
		deleteRolMenuItem(findRolMenuItem(id));
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.RolMenuItemDao#deleteRolMenuItem(com.bizitglobal.tajetafiel.acceso.negocio.RolMenuItem)
	 */
	public void deleteRolMenuItem(RolMenuItem unRolMenuItem) {
		this.getHibernateTemplate().delete(unRolMenuItem);
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.RolMenuItemDao#updateRolMenuItem(com.bizitglobal.tajetafiel.acceso.negocio.RolMenuItem)
	 */
	public void updateRolMenuItem(RolMenuItem unRolMenuItem) {
		this.getHibernateTemplate().update(unRolMenuItem);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.RolMenuItemDao#listAll()
	 */
	public List listAll(Long idRol) {
		final Long id = idRol;
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT rolMenuItem ");
				sb.append("FROM RolMenuItem rolMenuItem ");
				sb.append("WHERE rolMenuItem.rol.idRol ="+id+" ");
				sb.append("ORDER BY rolMenuItem.id ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public List listAll(Long idRol,Long idMenu) {
		final Long idR = idRol;
		final Long idM = idMenu;
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT rolMenuItem ");
				sb.append("FROM RolMenuItem rolMenuItem ");
				sb.append("WHERE rolMenuItem.rol.idRol ="+idR+" "+" AND rolMenuItem.menuItem.idMenuItem = "+idM+" ");
				sb.append("ORDER BY rolMenuItem.id ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}	
	

}

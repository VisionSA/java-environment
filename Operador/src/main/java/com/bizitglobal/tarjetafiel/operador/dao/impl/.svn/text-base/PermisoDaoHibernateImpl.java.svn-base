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
public class PermisoDaoHibernateImpl extends HibernateDaoSupport implements PermisoDao {

	/**
	 * Constructor por defecto.
	 */
	public PermisoDaoHibernateImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.dao.PermisoDao#savePermiso(com.bizitglobal.tarjetafiel.acceso.negocio.Permiso)
	 */
	public void savePermiso(Permiso unPermiso) {
		this.getHibernateTemplate().save(unPermiso);
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.PermisoDao#findPermiso(java.lang.Integer)
	 */
	public Permiso findPermiso(Long id) {
		return (Permiso) this.getHibernateTemplate().get(Permiso.class,id);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.PermisoDao#deletePermiso(java.lang.Integer)
	 */
	public void deletePermiso(Long id) {
		deletePermiso(findPermiso(id));
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.PermisoDao#deletePermiso(com.bizitglobal.tajetafiel.acceso.negocio.Permiso)
	 */
	public void deletePermiso(Permiso unPermiso) {
		this.getHibernateTemplate().delete(unPermiso);
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.PermisoDao#updatePermiso(com.bizitglobal.tajetafiel.acceso.negocio.Permiso)
	 */
	public void updatePermiso(Permiso unPermiso) {
		this.getHibernateTemplate().update(unPermiso);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.PermisoDao#listAll()
	 */
	public List listAll() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT permiso ");
				sb.append("FROM Permiso permiso ");
				sb.append("ORDER BY permiso.id ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}

}

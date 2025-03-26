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
public class RolPaginaPermisoDaoHibernateImpl extends HibernateDaoSupport implements RolPaginaPermisoDao {

	/**
	 * Constructor por defecto.
	 */
	public RolPaginaPermisoDaoHibernateImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.dao.RolPaginaPermisoDao#saveRolPaginaPermiso(com.bizitglobal.tarjetafiel.acceso.negocio.RolPaginaPermiso)
	 */
	public void saveRolPaginaPermiso(RolPaginaPermiso unRolPaginaPermiso) {
		this.getHibernateTemplate().save(unRolPaginaPermiso);
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.RolPaginaPermisoDao#findRolPaginaPermiso(java.lang.Integer)
	 */
	public RolPaginaPermiso findRolPaginaPermiso(Long id) {
		return (RolPaginaPermiso) this.getHibernateTemplate().get(RolPaginaPermiso.class,id);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.RolPaginaPermisoDao#deleteRolPaginaPermiso(java.lang.Integer)
	 */
	public void deleteRolPaginaPermiso(Long id) {
		deleteRolPaginaPermiso(findRolPaginaPermiso(id));
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.RolPaginaPermisoDao#deleteRolPaginaPermiso(com.bizitglobal.tajetafiel.acceso.negocio.RolPaginaPermiso)
	 */
	public void deleteRolPaginaPermiso(RolPaginaPermiso unRolPaginaPermiso) {
		this.getHibernateTemplate().delete(unRolPaginaPermiso);
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.RolPaginaPermisoDao#updateRolPaginaPermiso(com.bizitglobal.tajetafiel.acceso.negocio.RolPaginaPermiso)
	 */
	public void updateRolPaginaPermiso(RolPaginaPermiso unRolPaginaPermiso) {
		this.getHibernateTemplate().update(unRolPaginaPermiso);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.RolPaginaPermisoDao#listAll()
	 */
	public List listAll(String pagina, Long idRol) {
		final String unaPagina = pagina;
		final Long unId = idRol;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT rolPaginaPermiso ");
				sb.append("FROM RolPaginaPermiso rolPaginaPermiso ");
				sb.append("WHERE rolPaginaPermiso.rol.idRol ="+unId+" AND rolPaginaPermiso.pagina.pagina = '"+unaPagina+"' ");
				sb.append("ORDER BY rolPaginaPermiso.idRolPaginaPermiso ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				
				return list;
			}
		});		
	}
	
	public List listAll(Long idRol) {
		final Long unId = idRol;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT rolPaginaPermiso ");
				sb.append("FROM RolPaginaPermiso rolPaginaPermiso ");
				sb.append("WHERE rolPaginaPermiso.rol.idRol ="+unId+" ");
				sb.append("ORDER BY rolPaginaPermiso.idRolPaginaPermiso ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				
				return list;
			}
		});		
	}	
	
}

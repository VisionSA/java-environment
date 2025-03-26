package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.ActividadDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Actividad;

/**@deprecated
 * @author hernan
 *
 */
public class ActividadDaoHibernateImpl extends HibernateDaoSupport implements ActividadDao {

	public ActividadDaoHibernateImpl() {
		super();
	}

	public void grabarActividad(Actividad unaActividad) {
		this.getHibernateTemplate().save(unaActividad);
	}	

	public Actividad buscarActividad(Long id) {
		return (Actividad) this.getHibernateTemplate().get(Actividad.class,id);
	}
	
	public void borrarActividad(Long id) {
		borrarActividad(buscarActividad(id));
	}	

	public void borrarActividad(Actividad unaActividad) {
		this.getHibernateTemplate().delete(unaActividad);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Actividad obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.descripcion ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarActividad(Actividad unaActividad) {
		this.getHibernateTemplate().update(unaActividad);
	}

}

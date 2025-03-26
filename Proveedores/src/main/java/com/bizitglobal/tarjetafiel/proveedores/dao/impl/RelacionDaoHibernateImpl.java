package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.RelacionDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.RelacionProveedor;

public class RelacionDaoHibernateImpl extends HibernateDaoSupport implements RelacionDao {

	public RelacionDaoHibernateImpl() {
		super();
	}

	public void grabarRelacion(RelacionProveedor unaRelacion) {
		this.getHibernateTemplate().save(unaRelacion);
	}	

	public RelacionProveedor buscarRelacion(Integer id) {
		return (RelacionProveedor) this.getHibernateTemplate().get(RelacionProveedor.class,id);
	}
	
	public void borrarRelacion(Integer id) {
		borrarRelacion(buscarRelacion(id));
	}	

	public void borrarRelacion(RelacionProveedor unRelacion) {
		this.getHibernateTemplate().delete(unRelacion);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM RelacionProveedor obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.idRelacion ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarRelacion(RelacionProveedor unaRelacion) {
		this.getHibernateTemplate().update(unaRelacion);
	}

}

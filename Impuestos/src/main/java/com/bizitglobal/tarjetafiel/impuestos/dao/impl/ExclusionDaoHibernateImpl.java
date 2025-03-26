package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.ExclusionDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;

public class ExclusionDaoHibernateImpl extends HibernateDaoSupport implements ExclusionDao {

	public ExclusionDaoHibernateImpl() {
		super();
	}

	public void grabarExclusion(Exclusion unaExclusion) {
		this.getHibernateTemplate().save(unaExclusion);
	}	

	public Exclusion buscarExclusion(Long id) {
		return (Exclusion) this.getHibernateTemplate().get(Exclusion.class,id);
	}
	
	public void borrarExclusion(Long id) {
		borrarExclusion(buscarExclusion(id));
	}	

	public void borrarExclusion(Exclusion unaExclusion) {
		this.getHibernateTemplate().delete(unaExclusion);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Exclusion obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.descripcion ASC");

				Query query = session.createQuery(sb.toString());
				System.out.println(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}

	public void actualizarExclusion(Exclusion unaExclusion) {
		this.getHibernateTemplate().update(unaExclusion);
	}
	
}

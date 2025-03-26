package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.AplicableDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Aplicable;

public class AplicableDaoHibernateImpl extends HibernateDaoSupport implements AplicableDao {

	public AplicableDaoHibernateImpl() {
		super();
	}

	public void grabarAplicable(Aplicable unAplicable) {
		this.getHibernateTemplate().save(unAplicable);
	}	

	public Aplicable buscarAplicable(Long id) {
		return (Aplicable) this.getHibernateTemplate().get(Aplicable.class,id);
	}
	
	public void borrarAplicable(Long id) {
		borrarAplicable(buscarAplicable(id));
	}	

	public void borrarAplicable(Aplicable unAplicable) {
		this.getHibernateTemplate().delete(unAplicable);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Aplicable obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.descripcion ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarAplicable(Aplicable unAplicable) {
		this.getHibernateTemplate().update(unAplicable);
	}

}

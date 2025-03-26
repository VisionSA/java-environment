package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.EstadoInterbankDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.EstadoInterbank;

public class EstadoInterbankDaoHibernateImpl extends HibernateDaoSupport implements EstadoInterbankDao  {
	public EstadoInterbankDaoHibernateImpl() {
		super();
	}

	public void grabarEstadoInterbank (EstadoInterbank pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public EstadoInterbank buscarEstadoInterbank (Long id) {
		return (EstadoInterbank) this.getHibernateTemplate().get(EstadoInterbank.class, id);
	}
	public void borrarEstadoInterbank (Long id) {
		borrarEstadoInterbank(buscarEstadoInterbank(id));
	}
	public void borrarEstadoInterbank (EstadoInterbank pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEstadoInterbank (EstadoInterbank pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM EstadoInterbank obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


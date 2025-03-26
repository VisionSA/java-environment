package com.bizitglobal.tarjetafiel.cobranzas.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.cobranzas.dao.AccionVersionDao;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.AccionVersion;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public class AccionVersionDaoImpl extends HibernateDaoSupport implements AccionVersionDao {
	
	public AccionVersionDaoImpl() {
		super();
	}

	public void grabarAccionVersion (AccionVersion pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public AccionVersion buscarAccionVersion (Long id) {
		return (AccionVersion) this.getHibernateTemplate().get(AccionVersion.class, id);
	}
	public void borrarAccionVersion (Long id) {
		borrarAccionVersion(buscarAccionVersion(id));
	}
	public void borrarAccionVersion (AccionVersion pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarAccionVersion (AccionVersion pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM AccionVersion obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

}

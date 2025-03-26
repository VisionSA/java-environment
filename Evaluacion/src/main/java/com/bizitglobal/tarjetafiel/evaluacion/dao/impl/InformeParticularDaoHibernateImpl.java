package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.InformeParticularDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InformeParticular;

public class InformeParticularDaoHibernateImpl extends HibernateDaoSupport implements InformeParticularDao {
	public InformeParticularDaoHibernateImpl() {
		super();
	}

	public void grabarEvaInfoParticulares (InformeParticular pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public InformeParticular buscarEvaInfoParticulares (Long id) {
		return (InformeParticular) this.getHibernateTemplate().get(InformeParticular.class, id);
	}
	public void borrarEvaInfoParticulares (Long id) {
		borrarEvaInfoParticulares(buscarEvaInfoParticulares(id));
	}
	public void borrarEvaInfoParticulares (InformeParticular pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaInfoParticulares (InformeParticular pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM InformeParticular obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ActividadEvaluacionDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ActividadEvaluacion;

public class ActividadEvaluacionDaoHibernateImpl extends HibernateDaoSupport implements ActividadEvaluacionDao {
	public ActividadEvaluacionDaoHibernateImpl() {
		super();
	}

	public void grabarEvaActividades (ActividadEvaluacion pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public ActividadEvaluacion buscarEvaActividades (Long id) {
		return (ActividadEvaluacion) this.getHibernateTemplate().get(ActividadEvaluacion.class, id);
	}
	public void borrarEvaActividades (Long id) {
		borrarEvaActividades(buscarEvaActividades(id));
	}
	public void borrarEvaActividades (ActividadEvaluacion pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaActividades (ActividadEvaluacion pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ActividadEvaluacion obj ");
				sb.append(hql);
				
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


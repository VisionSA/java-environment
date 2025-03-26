package com.bizitglobal.tarjetafiel.cobranzas.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.cobranzas.dao.AccionDao;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Accion;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public class AccionDaoImpl extends HibernateDaoSupport implements AccionDao {
	
	public AccionDaoImpl() {
		super();
	}

	public void grabarAccion (Accion pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Accion buscarAccion (Long id) {
		return (Accion) this.getHibernateTemplate().get(Accion.class, id);
	}
	public void borrarAccion (Long id) {
		borrarAccion(buscarAccion(id));
	}
	public void borrarAccion (Accion pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarAccion (Accion pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Accion obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.descripcion asc ");

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}

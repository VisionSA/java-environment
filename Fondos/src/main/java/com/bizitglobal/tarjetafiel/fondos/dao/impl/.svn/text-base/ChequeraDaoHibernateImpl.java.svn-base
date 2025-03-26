package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeraDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.Chequera;

public class ChequeraDaoHibernateImpl extends HibernateDaoSupport implements ChequeraDao  {
	public ChequeraDaoHibernateImpl() {
		super();
	}

	public void grabarChequera (Chequera pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Chequera buscarChequera (Long id) {
		return (Chequera) this.getHibernateTemplate().get(Chequera.class, id);
	}
	public void borrarChequera (Long id) {
		borrarChequera(buscarChequera(id));
	}
	public void borrarChequera (Chequera pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarChequera (Chequera pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Chequera obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


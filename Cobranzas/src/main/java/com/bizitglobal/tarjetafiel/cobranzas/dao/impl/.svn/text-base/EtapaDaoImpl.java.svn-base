package com.bizitglobal.tarjetafiel.cobranzas.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.cobranzas.dao.EtapaDao;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Etapa;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public class EtapaDaoImpl extends HibernateDaoSupport implements EtapaDao {
	
	public EtapaDaoImpl() {
		super();
	}

	public void grabarEtapa (Etapa pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Etapa buscarEtapa (Long id) {
		return (Etapa) this.getHibernateTemplate().get(Etapa.class, id);
	}
	public void borrarEtapa (Long id) {
		borrarEtapa(buscarEtapa(id));
	}
	public void borrarEtapa (Etapa pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEtapa (Etapa pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Etapa obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}

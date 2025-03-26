package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.AsientoItemDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;

public class AsientoItemDaoHibernateImpl extends HibernateDaoSupport implements AsientoItemDao  {
	public AsientoItemDaoHibernateImpl() {
		super();
	}

	public void grabarAsientoItem (AsientoItem pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public AsientoItem buscarAsientoItem (Long id) {
		return (AsientoItem) this.getHibernateTemplate().get(AsientoItem.class, id);
	}
	public void borrarAsientoItem (Long id) {
		borrarAsientoItem(buscarAsientoItem(id));
	}
	public void borrarAsientoItem (AsientoItem pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarAsientoItem (AsientoItem pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM AsientoItem obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


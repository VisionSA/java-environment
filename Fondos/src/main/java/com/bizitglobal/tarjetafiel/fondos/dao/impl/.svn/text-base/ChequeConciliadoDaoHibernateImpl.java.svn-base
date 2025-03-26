package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeConciliadoDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeConciliado;

public class ChequeConciliadoDaoHibernateImpl extends HibernateDaoSupport implements ChequeConciliadoDao  {
	public ChequeConciliadoDaoHibernateImpl() {
		super();
	}

	public void grabarChequeConciliado (ChequeConciliado pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public ChequeConciliado buscarChequeConciliado (Long id) {
		return (ChequeConciliado) this.getHibernateTemplate().get(ChequeConciliado.class, id);
	}
	public void borrarChequeConciliado (Long id) {
		borrarChequeConciliado(buscarChequeConciliado(id));
	}
	public void borrarChequeConciliado (ChequeConciliado pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarChequeConciliado (ChequeConciliado pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ChequeConciliado obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


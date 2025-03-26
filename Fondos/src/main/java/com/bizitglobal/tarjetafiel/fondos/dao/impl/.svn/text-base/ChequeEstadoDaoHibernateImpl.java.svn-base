package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeEstadoDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado;

public class ChequeEstadoDaoHibernateImpl extends HibernateDaoSupport implements ChequeEstadoDao  {
	public ChequeEstadoDaoHibernateImpl() {
		super();
	}

	public void grabarChequeEstado (ChequeEstado pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public ChequeEstado buscarChequeEstado (Long id) {
		return (ChequeEstado) this.getHibernateTemplate().get(ChequeEstado.class, id);
	}
	public void borrarChequeEstado (Long id) {
		borrarChequeEstado(buscarChequeEstado(id));
	}
	public void borrarChequeEstado (ChequeEstado pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarChequeEstado (ChequeEstado pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ChequeEstado obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


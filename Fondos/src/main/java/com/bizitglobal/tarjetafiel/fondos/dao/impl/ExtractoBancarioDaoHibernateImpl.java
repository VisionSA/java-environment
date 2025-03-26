package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.ExtractoBancarioDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancario;

public class ExtractoBancarioDaoHibernateImpl extends HibernateDaoSupport implements ExtractoBancarioDao  {
	public ExtractoBancarioDaoHibernateImpl() {
		super();
	}

	public void grabarExtractoBancario (ExtractoBancario pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public ExtractoBancario buscarExtractoBancario (Long id) {
		return (ExtractoBancario) this.getHibernateTemplate().get(ExtractoBancario.class, id);
	}
	public void borrarExtractoBancario (Long id) {
		borrarExtractoBancario(buscarExtractoBancario(id));
	}
	public void borrarExtractoBancario (ExtractoBancario pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarExtractoBancario (ExtractoBancario pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ExtractoBancario obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


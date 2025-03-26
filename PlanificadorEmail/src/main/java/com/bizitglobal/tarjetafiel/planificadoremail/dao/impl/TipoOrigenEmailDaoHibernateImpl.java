package com.bizitglobal.tarjetafiel.planificadoremail.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.planificadoremail.dao.TipoOrigenEmailDao;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.TipoOrigen;


public class TipoOrigenEmailDaoHibernateImpl extends HibernateDaoSupport
		implements TipoOrigenEmailDao {

	public TipoOrigenEmailDaoHibernateImpl() {
		super();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TipoOrigen> find(Filtro filtro) {
		final String hq = filtro.getHQL();
		return (List<TipoOrigen>) getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						StringBuffer sb = new StringBuffer(100);
						sb.append("SELECT DISTINCT obj ");
						sb.append("FROM TipoOrigen obj ");
						sb.append(hq);
						sb.append("ORDER BY obj.descripcion ASC");

						Query query = session.createQuery(sb.toString());
						System.out.println(sb.toString());
						List<TipoOrigen> list = query.list();

						return list;
					}
				});
	}

}

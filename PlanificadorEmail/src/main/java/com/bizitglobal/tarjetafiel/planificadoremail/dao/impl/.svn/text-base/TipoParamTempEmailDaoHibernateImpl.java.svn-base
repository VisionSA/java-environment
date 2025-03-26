package com.bizitglobal.tarjetafiel.planificadoremail.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.planificadoremail.dao.TipoParamTempEmailDao;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.TipoParamTemp;


public class TipoParamTempEmailDaoHibernateImpl extends HibernateDaoSupport
		implements TipoParamTempEmailDao {

	public TipoParamTempEmailDaoHibernateImpl() {
		super();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TipoParamTemp> find(Filtro filtro) {
		final String hq = filtro.getHQL();
		return (List<TipoParamTemp>) getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						StringBuffer sb = new StringBuffer(100);
						sb.append("SELECT DISTINCT obj ");
						sb.append("FROM TipoParamTemp obj ");
						sb.append(hq);
						sb.append("ORDER BY obj.descripcion ASC");

						Query query = session.createQuery(sb.toString());
						System.out.println(sb.toString());
						List<TipoParamTemp> list = query.list();

						return list;
					}
				});
	}

}

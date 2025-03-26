package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.ImpuestosIndividuoDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;
import com.bizitglobal.tarjetafiel.impuestos.negocio.ImpuestosIndividuo;

public class ImpuestosIndividuoDaoHibernateImpl extends HibernateDaoSupport implements ImpuestosIndividuoDao {

	public ImpuestosIndividuoDaoHibernateImpl() {
		super();
	}

	public void grabarImpuestosIndividuo(ImpuestosIndividuo unImpuestosIndividuo) {
		this.getHibernateTemplate().save(unImpuestosIndividuo);
	}	

	public ImpuestosIndividuo buscarImpuestosIndividuo(Long id) {
		return (ImpuestosIndividuo) this.getHibernateTemplate().get(ImpuestosIndividuo.class,id);
	}
	
	public void borrarImpuestosIndividuo(Long id) {
		borrarImpuestosIndividuo(buscarImpuestosIndividuo(id));
	}	

	public void borrarImpuestosIndividuo(ImpuestosIndividuo unImpuestosIndividuo) {
		this.getHibernateTemplate().delete(unImpuestosIndividuo);
	}
	
	
	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ImpuestosIndividuo obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}


}

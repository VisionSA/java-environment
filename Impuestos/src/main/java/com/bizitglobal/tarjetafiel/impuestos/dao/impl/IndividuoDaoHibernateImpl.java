package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.impuestos.dao.IndividuoDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Individuo;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class IndividuoDaoHibernateImpl extends HibernateDaoSupport implements IndividuoDao {

	public IndividuoDaoHibernateImpl() {
		super();
	}

	public Long grabarIndividuo(Individuo unIndividuo) {
		this.getHibernateTemplate().save(unIndividuo);
		return maxIdIndividuo();
	}	

	public Individuo buscarIndividuo(Long id) {
		return (Individuo) this.getHibernateTemplate().get(Individuo.class,id);
	}
	
	public void borrarIndividuo(Long id) {
		borrarIndividuo(buscarIndividuo(id));
	}	

	public void borrarIndividuo(Individuo unIndividuo) {
		this.getHibernateTemplate().delete(unIndividuo);
	}

	public List listarTodos(Filtro unFiltro) {
		final String filtro = unFiltro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Individuo obj ");
				sb.append(filtro);
				sb.append("ORDER BY obj.cuit ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarIndividuo(Individuo unIndividuo) {
		this.getHibernateTemplate().update(unIndividuo);
	}
	
	private Long maxIdIndividuo() {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				Query query = session.createQuery("SELECT MAX(obj.idIndividuo) FROM Individuo obj");
				Object result = query.uniqueResult();

				return new Long(result.toString());
			}
		});		
	}	

}

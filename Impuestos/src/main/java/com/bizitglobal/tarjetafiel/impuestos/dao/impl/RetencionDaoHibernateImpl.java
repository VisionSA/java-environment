package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.RetencionDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;

public class RetencionDaoHibernateImpl extends HibernateDaoSupport implements RetencionDao {

	public RetencionDaoHibernateImpl() {
		super();
	}

	public void grabarRetencion(Retencion unaRetencion) {
		this.getHibernateTemplate().save(unaRetencion);
	}	

	public Retencion buscarRetencion(Long id) {
		return (Retencion) this.getHibernateTemplate().get(Retencion.class,id);
	}
	
	public void borrarRetencion(Long id) {
		borrarRetencion(buscarRetencion(id));
	}	

	public void borrarRetencion(Retencion unaRetencion) {
		this.getHibernateTemplate().delete(unaRetencion);
	}

	public List listarTodos(Filtro filtro) {
		final String hqlFiltro = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Retencion obj ");
				sb.append(hqlFiltro);
				
				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});
	}
	
	public void actualizarRetencion(Retencion unaRetencion) {
		this.getHibernateTemplate().update(unaRetencion);
	}
	
	private Long maxIdRetencion() {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				Query query = session.createQuery("SELECT MAX(obj.idRetencion) FROM Retencion obj");
				Object result = query.uniqueResult();

				return new Long(result.toString());
			}
		});		
	}	

}

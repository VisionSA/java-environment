package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.TramosRetencionDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TramosRetencion;

public class TramosRetencionDaoHibernateImpl extends HibernateDaoSupport implements TramosRetencionDao {

	public TramosRetencionDaoHibernateImpl() {
		super();
	}

	public void grabarTramosRetencion(TramosRetencion unTramosRetencion) {
		this.getHibernateTemplate().save(unTramosRetencion);
	}	

	public TramosRetencion buscarTramosRetencion(Long id) {
		return (TramosRetencion) this.getHibernateTemplate().get(TramosRetencion.class,id);
	}
	
	public void borrarTramosRetencion(Long id) {
		borrarTramosRetencion(buscarTramosRetencion(id));
	}	

	public void borrarTramosRetencion(TramosRetencion unTramosRetencion) {
		this.getHibernateTemplate().delete(unTramosRetencion);
	}

	public List listarTodos(Filtro filtro) {
		final String sql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM TramosRetencion obj ");
				sb.append(sql);
				sb.append("ORDER BY obj.idTramoRetencion ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarTramosRetencion(TramosRetencion unTramosRetencion) {
		this.getHibernateTemplate().update(unTramosRetencion);
	}

}

package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.TipoImpuestoDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TipoImpuesto;

public class TipoImpuestoDaoHibernateImpl extends HibernateDaoSupport implements TipoImpuestoDao {

	public TipoImpuestoDaoHibernateImpl() {
		super();
	}

	public void grabarTipoImpuesto(TipoImpuesto unTipoImpuesto) {
		this.getHibernateTemplate().save(unTipoImpuesto);
	}	

	public TipoImpuesto buscarTipoImpuesto(Long id) {
		return (TipoImpuesto) this.getHibernateTemplate().get(TipoImpuesto.class,id);
	}
	
	public void borrarTipoImpuesto(Long id) {
		borrarTipoImpuesto(buscarTipoImpuesto(id));
	}	

	public void borrarTipoImpuesto(TipoImpuesto unTipoImpuesto) {
		this.getHibernateTemplate().delete(unTipoImpuesto);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM TipoImpuesto obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.descripcion ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarTipoImpuesto(TipoImpuesto unaTipoImpuesto) {
		this.getHibernateTemplate().update(unaTipoImpuesto);
	}

}

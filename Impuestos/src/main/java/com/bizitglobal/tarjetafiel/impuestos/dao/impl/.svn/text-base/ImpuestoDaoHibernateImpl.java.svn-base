package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.ImpuestoDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;

public class ImpuestoDaoHibernateImpl extends HibernateDaoSupport implements ImpuestoDao {

	public ImpuestoDaoHibernateImpl() {
		super();
	}

	public void grabarImpuesto(Impuesto unImpuesto) {
		this.getHibernateTemplate().save(unImpuesto);
	}	

	public Impuesto buscarImpuesto(Long id) {
		return (Impuesto) this.getHibernateTemplate().get(Impuesto.class,id);
	}
	
	public void borrarImpuesto(Long id) {
		borrarImpuesto(buscarImpuesto(id));
	}	

	public void borrarImpuesto(Impuesto unImpuesto) {
		this.getHibernateTemplate().delete(unImpuesto);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Impuesto obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.descripcion ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarImpuesto(Impuesto unImpuesto) {
		this.getHibernateTemplate().update(unImpuesto);
	}

}

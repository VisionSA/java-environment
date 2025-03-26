package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.AsientoContableDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.AsientoContable;

public class AsientoContableDaoHibernateImpl extends HibernateDaoSupport implements AsientoContableDao {

	public AsientoContableDaoHibernateImpl() {
		super();
	}

	public void grabarAsientoContable(AsientoContable unAsientoContable) {
		this.getHibernateTemplate().save(unAsientoContable);
	}	

	public AsientoContable buscarAsientoContable(Integer id) {
		return (AsientoContable) this.getHibernateTemplate().get(AsientoContable.class,id);
	}
	
	public void borrarAsientoContable(Integer id) {
		borrarAsientoContable(buscarAsientoContable(id));
	}	

	public void borrarAsientoContable(AsientoContable unAsientoContable) {
		this.getHibernateTemplate().delete(unAsientoContable);
	}

	public List listarTodos() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM AsientoContable obj ");
				sb.append("ORDER BY obj.idAsiento ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarAsientoContable(AsientoContable unAsientoContable) {
		this.getHibernateTemplate().update(unAsientoContable);
	}

}

package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorDomicilioDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorDomicilio;

public class ProveedorDomicilioDaoHibernateImpl extends HibernateDaoSupport implements ProveedorDomicilioDao {

	public ProveedorDomicilioDaoHibernateImpl() {
		super();
	}

	public void grabarProveedorDomicilio(ProveedorDomicilio unProveedorDomicilio) {
		this.getHibernateTemplate().save(unProveedorDomicilio);
	}	

	public ProveedorDomicilio buscarProveedorDomicilio(Long id) {
		return (ProveedorDomicilio) this.getHibernateTemplate().get(ProveedorDomicilio.class,id);
	}
	
	public void borrarProveedorDomicilio(Long id) {
		borrarProveedorDomicilio(buscarProveedorDomicilio(id));
	}	

	public void borrarProveedorDomicilio(ProveedorDomicilio unProveedorDomicilio) {
		this.getHibernateTemplate().delete(unProveedorDomicilio);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ProveedorDomicilio obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.idProvDomicilio ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarProveedorDomicilio(ProveedorDomicilio unProveedorDomicilio) {
		this.getHibernateTemplate().update(unProveedorDomicilio);
	}

}

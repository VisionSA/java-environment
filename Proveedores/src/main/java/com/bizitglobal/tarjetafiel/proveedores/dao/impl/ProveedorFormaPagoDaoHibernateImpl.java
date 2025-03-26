package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorFormaPagoDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorFormaPago;

public class ProveedorFormaPagoDaoHibernateImpl extends HibernateDaoSupport implements ProveedorFormaPagoDao {

	public ProveedorFormaPagoDaoHibernateImpl() {
		super();
	}

	public void grabarProveedorFormaPago(ProveedorFormaPago unProveedorFormaPago) {
		this.getHibernateTemplate().save(unProveedorFormaPago);
	}	

	public ProveedorFormaPago buscarProveedorFormaPago(Integer id) {
		return (ProveedorFormaPago) this.getHibernateTemplate().get(ProveedorFormaPago.class,id);
	}
	
	public void borrarProveedorFormaPago(Integer id) {
		borrarProveedorFormaPago(buscarProveedorFormaPago(id));
	}	

	public void borrarProveedorFormaPago(ProveedorFormaPago unProveedorFormaPago) {
		this.getHibernateTemplate().delete(unProveedorFormaPago);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ProveedorFormaPago obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.idProvFormaPago ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarProveedorFormaPago(ProveedorFormaPago unProveedorFormaPago) {
		this.getHibernateTemplate().update(unProveedorFormaPago);
	}

}

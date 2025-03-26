package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;

public class ProveedorDaoHibernateImpl extends HibernateDaoSupport implements ProveedorDao {

	public ProveedorDaoHibernateImpl() {
		super();
	}

	public void grabarProveedor(Proveedor unProveedor) {
		this.getHibernateTemplate().save(unProveedor);
	}	

	public Proveedor buscarProveedor(Long id) {
		return (Proveedor) this.getHibernateTemplate().get(Proveedor.class,id);
	}
	
	public void borrarProveedor(Long id) {
		borrarProveedor(buscarProveedor(id));
	}	

	public void borrarProveedor(Proveedor unProveedor) {
		this.getHibernateTemplate().delete(unProveedor);
	}

	public List listarTodos(Filtro filtro) {
		final String hqlFiltro = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Proveedor obj ");
				sb.append(hqlFiltro);
				sb.append(" ORDER BY obj.idProveedor ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public Long maxIdProveedor() {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				Query query = session.createQuery("SELECT MAX(obj.idProveedor) FROM Proveedor obj");
				Object result = query.uniqueResult();

				return new Long(result.toString());
			}
		});		
	}
	
	public void actualizarProveedor(Proveedor unProveedor) {
		this.getHibernateTemplate().update(unProveedor);
	}
	
	public void grabarYActualizarProveedor(Proveedor unProveedor) {
		this.getHibernateTemplate().saveOrUpdate(unProveedor);		
	}

}

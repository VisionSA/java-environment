package com.bizitglobal.tarjetafiel.operador.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.operador.dao.RolDao;
import com.bizitglobal.tarjetafiel.operador.negocio.Rol;

public class RolDaoHibernateImpl extends HibernateDaoSupport implements RolDao {

	public RolDaoHibernateImpl() {
		super();
	}
	
	public void grabarRol(Rol rol) {
		this.getHibernateTemplate().save(rol);
	}
	
	public Rol buscarRol(Long id) {
		return (Rol) this.getHibernateTemplate().get(Rol.class,id);
	}
	
	public void borrarRol(Long id) {
		borrarRol(buscarRol(id));
	}
	
	public void borrarRol(Rol rol) {
		this.getHibernateTemplate().delete(rol);
	}

	public List listarTodos() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT rol ");
				sb.append("FROM Rol rol ");
				sb.append("ORDER BY rol.idRol ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarRol(Rol rol) {
		this.getHibernateTemplate().update(rol);
	}
	
	public void grabarOActualizarRol(Rol rol) {
		this.getHibernateTemplate().saveOrUpdate(rol);
	}
	
}

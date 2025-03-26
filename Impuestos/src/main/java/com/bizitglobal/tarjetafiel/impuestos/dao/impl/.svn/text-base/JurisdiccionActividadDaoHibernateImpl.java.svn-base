package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionActividadDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisdiccionActividad;

public class JurisdiccionActividadDaoHibernateImpl extends HibernateDaoSupport implements JurisdiccionActividadDao {

	public JurisdiccionActividadDaoHibernateImpl() {
		super();
	}

	public void grabarJurisdiccionActividad(JurisdiccionActividad unJurisdiccionActividad) {
		this.getHibernateTemplate().save(unJurisdiccionActividad);
	}	

	public JurisdiccionActividad buscarJurisdiccionActividad(Long id) {
		return (JurisdiccionActividad) this.getHibernateTemplate().get(JurisdiccionActividad.class,id);
	}
	
	public void borrarJurisdiccionActividad(Long id) {
		borrarJurisdiccionActividad(buscarJurisdiccionActividad(id));
	}	

	public void borrarJurisdiccionActividad(JurisdiccionActividad unJurisdiccionActividad) {
		this.getHibernateTemplate().delete(unJurisdiccionActividad);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM JurisdiccionActividad obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.idJurisdiccionActividad ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarJurisdiccionActividad(JurisdiccionActividad unJurisdiccionActividad) {
		this.getHibernateTemplate().update(unJurisdiccionActividad);
	}

}

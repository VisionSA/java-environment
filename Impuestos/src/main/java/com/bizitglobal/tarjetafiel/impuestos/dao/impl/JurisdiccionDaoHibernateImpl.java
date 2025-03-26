package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisdiccionDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;

public class JurisdiccionDaoHibernateImpl extends HibernateDaoSupport implements JurisdiccionDao {

	public JurisdiccionDaoHibernateImpl() {
		super();
	}

	public void grabarJurisdiccion(Jurisdiccion unJurisdiccion) {
		this.getHibernateTemplate().save(unJurisdiccion);
	}	

	public Jurisdiccion buscarJurisdiccion(Long id) {
		return (Jurisdiccion) this.getHibernateTemplate().get(Jurisdiccion.class,id);
	}
	
	public void borrarJurisdiccion(Long id) {
		borrarJurisdiccion(buscarJurisdiccion(id));
	}	

	public void borrarJurisdiccion(Jurisdiccion unJurisdiccion) {
		this.getHibernateTemplate().delete(unJurisdiccion);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Jurisdiccion obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.descripcion ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarJurisdiccion(Jurisdiccion unJurisdiccion) {
		this.getHibernateTemplate().update(unJurisdiccion);
	}

}

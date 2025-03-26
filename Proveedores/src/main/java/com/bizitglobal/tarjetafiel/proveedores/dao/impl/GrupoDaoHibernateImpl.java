package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.GrupoDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Grupo;

public class GrupoDaoHibernateImpl extends HibernateDaoSupport implements GrupoDao {

	public GrupoDaoHibernateImpl() {
		super();
	}

	public void grabarGrupo(Grupo unGrupo) {
		this.getHibernateTemplate().save(unGrupo);
	}	

	public Grupo buscarGrupo(Long id) {
		return (Grupo) this.getHibernateTemplate().get(Grupo.class,id);
	}
	
	public void borrarGrupo(Long id) {
		borrarGrupo(buscarGrupo(id));
	}	

	public void borrarGrupo(Grupo unGrupo) {
		this.getHibernateTemplate().delete(unGrupo);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Grupo obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.idGrupo ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarGrupo(Grupo unGrupo) {
		this.getHibernateTemplate().update(unGrupo);
	}

}

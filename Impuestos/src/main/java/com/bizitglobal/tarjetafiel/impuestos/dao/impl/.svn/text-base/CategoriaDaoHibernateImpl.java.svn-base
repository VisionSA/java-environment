package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.dao.CategoriaDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;

public class CategoriaDaoHibernateImpl extends HibernateDaoSupport implements CategoriaDao {

	public CategoriaDaoHibernateImpl() {
		super();
	}

	public void grabarCategoria(Categoria unCategoria) {
		this.getHibernateTemplate().save(unCategoria);
	}	

	public Categoria buscarCategoria(Long id) {
		return (Categoria) this.getHibernateTemplate().get(Categoria.class,id);
	}
	
	public void borrarCategoria(Long id) {
		borrarCategoria(buscarCategoria(id));
	}	

	public void borrarCategoria(Categoria unCategoria) {
		this.getHibernateTemplate().delete(unCategoria);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Categoria obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.descripcion ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarCategoria(Categoria unCategoria) {
		this.getHibernateTemplate().update(unCategoria);
	}

}

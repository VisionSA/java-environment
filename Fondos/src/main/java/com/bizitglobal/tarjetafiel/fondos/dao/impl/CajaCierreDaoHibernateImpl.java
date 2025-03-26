package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.CajaCierreDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaCierre;

public class CajaCierreDaoHibernateImpl extends HibernateDaoSupport implements CajaCierreDao  {
	public CajaCierreDaoHibernateImpl() {
		super();
	}

	public void grabarCajaCierre (CajaCierre pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	
	public void grabarCajaCierreCollection(Collection<CajaCierre> collection){
		this.getHibernateTemplate().saveOrUpdateAll(collection);
	}
	
	public CajaCierre buscarCajaCierre (Long id) {
		return (CajaCierre) this.getHibernateTemplate().get(CajaCierre.class, id);
	}
	public void borrarCajaCierre (Long id) {
		borrarCajaCierre(buscarCajaCierre(id));
	}
	public void borrarCajaCierre (CajaCierre pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarCajaCierre (CajaCierre pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM CajaCierre obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


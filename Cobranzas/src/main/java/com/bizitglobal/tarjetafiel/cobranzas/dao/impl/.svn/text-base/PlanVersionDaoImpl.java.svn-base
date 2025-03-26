package com.bizitglobal.tarjetafiel.cobranzas.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.cobranzas.dao.PlanVersionDao;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.PlanVersion;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public class PlanVersionDaoImpl extends HibernateDaoSupport implements PlanVersionDao {
	
	public PlanVersionDaoImpl() {
		super();
	}

	public void grabarPlanVersion (PlanVersion pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public PlanVersion buscarPlanVersion (Long id) {
		return (PlanVersion) this.getHibernateTemplate().get(PlanVersion.class, id);
	}
	public void borrarPlanVersion (Long id) {
		borrarPlanVersion(buscarPlanVersion(id));
	}
	public void borrarPlanVersion (PlanVersion pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarPlanVersion (PlanVersion pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM PlanVersion obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public List getPlanesVersionByFiltro(Filtro filtro) {
		
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT new PlanVersion(obj.idPlanVersion, obj.descripcion, obj.fechaDesde,obj.queryClientesQueAplican) ");
				sb.append("FROM PlanVersion obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}

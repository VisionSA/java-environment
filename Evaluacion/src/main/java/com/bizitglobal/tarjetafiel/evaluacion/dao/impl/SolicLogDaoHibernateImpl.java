package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.SolicLogDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicLog;

public class SolicLogDaoHibernateImpl extends HibernateDaoSupport implements SolicLogDao {
	private static final Logger log = Logger.getLogger(SolicLogDaoHibernateImpl.class);
	
	public SolicLogDaoHibernateImpl() {
		super();
	}

	public void grabarSolicLog (SolicLog pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	
	public SolicLog buscarSolicLog (Long id) {
		return (SolicLog) this.getHibernateTemplate().get(SolicLog.class, id);
	}
	
	public void borrarSolicLog (Long id) {
		borrarSolicLog(buscarSolicLog(id));
	}
	
	public void borrarSolicLog (SolicLog pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	
	public void actualizarSolicLog (SolicLog pObject) {
		this.getHibernateTemplate().update(pObject);
	}

	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM SolicLog obj ");
				sb.append(hql);
				
				Query query = session.createQuery(sb.toString());
				return query.list();
			}
		});
	}

	public SolicLog busNroSolicLog(Filtro filtro) {
		
		final String hql = filtro.getHQL();
		
		return (SolicLog) getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate (Session session) 
				throws HibernateException, SQLException{
				
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM SolicLog obj ");
				sb.append(hql);
				
				Query query = session.createQuery(sb.toString());
				
				return (SolicLog)query.uniqueResult();
			}
		});
	}
//	
//	public Long maxNroSolicLog() {
//		log.info("Ejecutando ==> maxNroSolicLog()");
//		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
//			public Object doInHibernate(Session session)
//				throws HibernateException, SQLException {
//
//				Query query = session.createQuery("SELECT MAX(obj.nroSolicLog) FROM SolicLog obj");
//				Object result = query.uniqueResult();
//				
//				if(result != null)
//					return new Long(result.toString());
//				
//				return new Long(0);
//			}
//		});		
//	}	
}


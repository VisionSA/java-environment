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
import com.bizitglobal.tarjetafiel.evaluacion.dao.SolicitudDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;

public class SolicitudDaoHibernateImpl extends HibernateDaoSupport implements SolicitudDao {
	private static final Logger log = Logger.getLogger(SolicitudDaoHibernateImpl.class);
	
	public SolicitudDaoHibernateImpl() {
		super();
	}

	public void grabarSolicitudes (Solicitud pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	
	public Solicitud buscarSolicitudes (Long id) {
		return (Solicitud) this.getHibernateTemplate().get(Solicitud.class, id);
	}
	
	public void borrarSolicitudes (Long id) {
		borrarSolicitudes(buscarSolicitudes(id));
	}
	
	public void borrarSolicitudes (Solicitud pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	
	public void actualizarSolicitudes (Solicitud pObject) {
		this.getHibernateTemplate().update(pObject);
	}

	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Solicitud obj ");
				sb.append(hql);
				
				Query query = session.createQuery(sb.toString());
				return query.list();
			}
		});
	}

	public Solicitud busNroSolicitud(Filtro filtro) {
		
		final String hql = filtro.getHQL();
		
		return (Solicitud) getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate (Session session) 
				throws HibernateException, SQLException{
				
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Solicitud obj ");
				sb.append(hql);
				
				Query query = session.createQuery(sb.toString());
				
				return (Solicitud)query.uniqueResult();
			}
		});
	}
	
	public Long maxNroSolicitud() {
		log.info("Ejecutando ==> maxNroSolicitud()");
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				Query query = session.createQuery("SELECT MAX(obj.nroSolicitud) FROM Solicitud obj");
				Object result = query.uniqueResult();
				
				if(result != null)
					return new Long(result.toString());
				
				return new Long(0);
			}
		});		
	}	
}


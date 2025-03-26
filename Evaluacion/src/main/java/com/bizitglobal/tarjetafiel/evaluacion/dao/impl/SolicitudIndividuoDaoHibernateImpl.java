package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.SolicitudIndividuoDao;
import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudIndividuo;

public class SolicitudIndividuoDaoHibernateImpl extends HibernateDaoSupport implements SolicitudIndividuoDao {
	
	public SolicitudIndividuoDaoHibernateImpl() {
		super();
	}

	public void grabarEvaSolicIndividuos (SolicitudIndividuo pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public SolicitudIndividuo buscarEvaSolicIndividuos (Long id) {
		return (SolicitudIndividuo) this.getHibernateTemplate().get(SolicitudIndividuo.class, id);
	}
	public void borrarEvaSolicIndividuos (Long id) {
		borrarEvaSolicIndividuos(buscarEvaSolicIndividuos(id));
	}
	public void borrarEvaSolicIndividuos (SolicitudIndividuo pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaSolicIndividuos (SolicitudIndividuo pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM SolicitudIndividuo obj ");
                sb.append(hql);
				Query query = session.createQuery(sb.toString());
				return query.list();
			}
		});
	}

	@Override
	public boolean isCargarAdicionales(final Solicitud solicitud) {
		Long rdo =(Long)getHibernateTemplate().execute(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
	
				String sqlAdicional = 
					"select count(*) " +  
					"from t_vis_eva_solic_individuos si " + 
					"inner join t_vis_eva_individuos ind on (ind.c_id_individuo=si.c_id_individuo) " + 
					"inner join t_vis_tra_clientes cli on (cli.c_id_individuo=ind.c_id_individuo) " + 
					"where si.c_id_tipo_individuo=1 " + 
					"and si.c_id_solicitud=" + solicitud.getIdSolicitud().toString() + " " +
					"and exists (select * from t_vis_tra_clientes cli2 where cli2.c_id_titular=cli.c_id_cliente) " + 
					"and not exists ( " +
					            "select * " + 
					            "from t_vis_eva_solic_individuos si1 " + 
					            "inner join t_vis_eva_individuos ind1 on (ind1.c_id_individuo=si1.c_id_individuo) " + 
					            "where si1.c_id_tipo_individuo=3 and si1.c_id_solicitud=si.c_id_solicitud " + 
					") "; 
					
				Long adicional = new Long(session.createSQLQuery(sqlAdicional).uniqueResult().toString());
				
				return adicional;
			}
		});
		  
 	    return (rdo.intValue()>0); 
		
	}

	@Override
	public boolean isCargarGarantes(final Solicitud solicitud) {
		Long rdo =(Long)getHibernateTemplate().execute(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
	
				String sqlGarantes = "select count(*) " +
				"from t_vis_eva_solic_individuos si " +  
				"inner join t_vis_eva_individuos ind on (ind.c_id_individuo=si.c_id_individuo) " +
				"inner join t_vis_tra_clientes cli on (cli.c_id_individuo=ind.c_id_individuo) " +
				"where si.c_id_tipo_individuo=1 " +
				"and si.c_id_solicitud=" + solicitud.getIdSolicitud().toString() + " " +
				"and exists (select * from t_vis_tra_garantes ga where ga.c_id_cliente=cli.c_id_cliente and ga.c_activo='S') " + 
				"and not exists ( " + 
				            "select * " + 
				            "from t_vis_eva_solic_individuos si1 " + 
				            "inner join t_vis_eva_individuos ind1 on (ind1.c_id_individuo=si1.c_id_individuo) " + 
				            "where si1.c_id_tipo_individuo=2 and si1.c_id_solicitud=si.c_id_solicitud " + 
				") "; 
				Long garantes = new Long(session.createSQLQuery(sqlGarantes).uniqueResult().toString()); 
				return garantes;
			}
		});
		  
 	    return (rdo.intValue()>0); 
	}
	
}


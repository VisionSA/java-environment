package com.bizitglobal.tarjetafiel.cobranzas.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.cobranzas.dao.PlanDao;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Plan;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;


public class PlanDaoImpl extends HibernateDaoSupport implements PlanDao {
	
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	public PlanDaoImpl() {
		super();
	}
	
	public void grabarPlan (Plan pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Plan buscarPlan (Long id) {
		return (Plan) this.getHibernateTemplate().get(Plan.class, id);
	}
	public void borrarPlan (Long id) {
		borrarPlan(buscarPlan(id));
	}
	public void borrarPlan (Plan pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarPlan (Plan pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	
	public void updatePlan (Plan plan){
		
		
		Session session = getSession();
		
		StringBuffer hql = new StringBuffer("");
		hql.append("update Plan " +
				"set habilitado = :newHab, " +
				"esPlanPorDefecto = :newXDef " +
				"where idPlan = :newIdPlan");		
		Query query = session.createQuery(hql.toString());
		query.setString("newHab", plan.getHabilitado());
		query.setString("newXDef", plan.getEsPlanPorDefecto());
		query.setInteger("newIdPlan", plan.getIdPlan());
		query.executeUpdate();
		
	}
	
	
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Plan obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	/**
	 * @return El unico plan por defecto que se encuentra habilitado.
	 * */
	public Plan getPlanPorDefecto() {
		return (Plan) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Plan obj ");
				sb.append("WHERE obj.habilitado LIKE 'S' and obj.esPlanPorDefecto LIKE 'S'");

				Query query = session.createQuery(sb.toString());
				Plan plan = (Plan)query.uniqueResult();
				return plan;
			}
		});
	}
	
	/**
	 * @param plan A partir del plan, obtiene la version actual y ejecuta su query.
	 * @return Una lista de Long con los id de clientes que recupera para el plan en cuestion
	 * */
	public List<Long> ejecutarQuery(final Plan plan) {

		   List<Long> result = new ArrayList<Long>();
		    StringBuffer sb = new StringBuffer(500);
			sb.append(plan.getVersionActual().getQueryClientesQueAplican());
			jt = new JdbcTemplate(dataSource);
			List rows = jt.queryForList(sb.toString());
			Iterator iter = rows.iterator();
			while (iter.hasNext()) {
				Map map = (Map) iter.next();
				Long idCliente = null;
				if (map.get("c_id_cliente")!=null) {
					idCliente = Long.valueOf(map.get("c_id_cliente").toString());
				} 
				result.add(idCliente);
			}
			return result;
	}
	
	public void borrarPlanesPorDefecto() {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				Query query = session.getNamedQuery("nulearPlanesPorDefecto");
				return query.executeUpdate();
			}
		});
	}
	

	public void cambiarEstadoPlan(final Plan p) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				Query query = session.getNamedQuery("cambiarEstadoPlan");
				String habi = "S";
				if (p.getHabilitado().compareTo(habi)==0) habi = "N";
				query.setString("habilitad", habi);
				query.setInteger("idP", p.getIdPlan());
				return query.executeUpdate();
			}
		});
	}
	
	public void desmarcarPlanPorDefecto() {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				Query query = session.getNamedQuery("desmarcarPlanPorDefecto");
				return query.executeUpdate();
			}
		});
	}

	public void marcarPlanPorDefecto(final Plan p) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				Query query = session.getNamedQuery("marcarPlanPorDefecto");
				query.setInteger("idP", p.getIdPlan());
				return query.executeUpdate();
			}
		});
	}
	
	
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	@Override
	public List getPlanes(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT new Plan(obj.idPlan, obj.descripcion, obj.esPlanPorDefecto,obj.habilitado) ");
				sb.append("FROM Plan obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
}

package com.bizitglobal.tarjetafiel.cobranzas.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.cobranzas.dao.EjecucionPlanDao;
import com.bizitglobal.tarjetafiel.cobranzas.exception.EjecucionPlanException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EjecucionPlan;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Moroso;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Plan;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Abogado;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteLiquidacion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LiquidacionClientes;

public class EjecucionPlanDaoImpl extends HibernateDaoSupport implements
		EjecucionPlanDao {

	private DataSource dataSource;
	private JdbcTemplate jt;

	public EjecucionPlanDaoImpl() {
		super();
	}

	public void grabarEjecucionPlan(EjecucionPlan pObject) {
		this.getHibernateTemplate().save(pObject);
	}

	/**
	 * Graba un Objeto en la base de datos.
	 * 
	 * @param pObject
	 *            , objeto a grabar.
	 */
	public void grabarObjeto(Object pObject) {
		this.getHibernateTemplate().save(pObject);
	}

	public EjecucionPlan buscarEjecucionPlan(Long id) {
		return (EjecucionPlan) this.getHibernateTemplate().get(
				EjecucionPlan.class, id);
	}

	public void borrarEjecucionPlan(Long id) {
		borrarEjecucionPlan(buscarEjecucionPlan(id));
	}

	public void borrarEjecucionPlan(EjecucionPlan pObject) {
		this.getHibernateTemplate().delete(pObject);
	}

	public void actualizarEjecucionPlan(EjecucionPlan pObject) {
		this.getHibernateTemplate().update(pObject);
	}

	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM EjecucionPlan obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

	/**
	 * Lista los clientes que entran en mora. (se identifican porque tienen el
	 * c_id_plan_mora en null.
	 * 
	 * @param montoDeGracia
	 *            Un monto de tolerancia, para que no recupere clientes que
	 *            adeudan centavos nada mas, poniendolos en mora.
	 * @return Una lista de objetos Long, con el id de cada cliente al cual se
	 *         lo debe iniciar en la mora, seteandole el id_plan_mora y la fecha
	 *         de entrada en mora.
	 * */
	public List<ClienteLiquidacion> listarIdClientesQueEntranEnMora(
			final Double montoDeGracia) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.getNamedQuery("clientesQueEntranEnMora");
				query.setDouble("importeDeGracia", montoDeGracia);
				return query.list();
			}
		});
	}

	/**
	 * Realiza un update del cliente poniendole el id del plan de mora, la fecha
	 * del dia como inicio para el id de cliente pasado
	 * */
	public void asociarPlanACliente(final Long idCliente, final Plan plan) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.getNamedQuery("asociarPlanMoraACliente");
				query.setLong("idCliente", idCliente);
				query.setLong("idPlanMora", plan.getIdPlan());
				// query.setDate("fecha", new Date());
				return query.executeUpdate();
			}
		});

	}

	// /**
	// * Devuelve los clientes en mora
	// * */
	// public List<ClienteLiquidacion> getClientesEnMora() {
	// return getHibernateTemplate().executeFind(new HibernateCallback() {
	// public Object doInHibernate(Session session)
	// throws HibernateException, SQLException {
	// Query query = session.getNamedQuery("getClientesEnMora");
	// return query.list();
	// }
	// });
	// }

	/**
	 * Devuelve los clientes en mora
	 * */
	public List<Moroso> getClientesEnMora() {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(300);
		sql
				.append("Select clientes.c_id_cliente as idCliente ,clientes.c_id_plan_mora as idPlanDeMora, clientes.c_fecha_mora as fechaMora, trunc(trunc(sysdate) - trunc(clientes.c_fecha_mora)) as diasEnMora from t_vis_tra_clientes clientes ");
		sql.append(" where clientes.c_id_plan_mora is not null ");
		sql.append(" order by clientes.c_id_cliente ");
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			Moroso moroso = new Moroso();
			if (map.get("idCliente") != null) {
				moroso.setIdCliente((Long.valueOf(map.get("idCliente")
						.toString())));
			} else {
				moroso.setIdCliente(null);
			}
			if (map.get("idPlanDeMora") != null) {
				moroso.setIdPlanDeMora(Long.valueOf(map.get("idPlanDeMora")
						.toString()));
			} else {
				moroso.setIdPlanDeMora(null);
			}
			if (map.get("fechaMora") != null) {
				moroso.setFechaMora((Date) map.get("fechaMora"));
			} else {
				moroso.setFechaMora(null);
			}
			if (map.get("diasEnMora") != null) {
				moroso.setDiasEnMora(Integer.valueOf(map.get("diasEnMora")
						.toString()));
			} else {
				moroso.setDiasEnMora(null);
			}
			result.add(moroso);
		}
		return result;
	}

	/**
	 * Ejecuta una query en la base de datos.
	 * 
	 * @param query
	 *            , query a ejecutar.
	 */
	public void ejecutarQuery(final String queryParametro) {
		jt = new JdbcTemplate(dataSource);
		jt.execute(queryParametro);
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

	public Boolean procesarPagosRealizados(int mesInicial, int mesFinal,
			int anioInicial, int anioFinal) {
		mesInicial = mesInicial + 1;
		mesFinal = mesFinal + 1;
		// hacer logica para meses y años con un for.
		for (int i = anioInicial; i <= anioFinal; i++) {
			if (i == anioFinal && i == anioInicial) {
				for (int j = mesInicial; j <= mesFinal; j++) {
					getSession().createSQLQuery(
							"{CALL SP_BUSCAR_PAGOS_A_COBRADORES(" + j + "," + i
									+ ")}").executeUpdate();
				}
			} else if (i == anioFinal) {
				for (int j = 1; j <= mesFinal; j++) {
					getSession().createSQLQuery(
							"{CALL SP_BUSCAR_PAGOS_A_COBRADORES(" + j + "," + i
									+ ")}").executeUpdate();
				}
			} else if (i == anioInicial) {
				for (int j = mesInicial; j <= 12; j++) {
					getSession().createSQLQuery(
							"{CALL SP_BUSCAR_PAGOS_A_COBRADORES(" + j + "," + i
									+ ")}").executeUpdate();
				}
			} else {
				for (int j = 1; j <= 12; j++) {
					getSession().createSQLQuery(
							"{CALL SP_BUSCAR_PAGOS_A_COBRADORES(" + j + "," + i
									+ ")}").executeUpdate();
				}
			}
		}
		return true;
	}

	@Override
	public void cambiarEstadoYAddFile(final Long idEjecucionPlan,
			String confirmoAccion, final StringBuffer pathRelativoPDFCobradores) {

		StringBuffer sql = new StringBuffer(100);
		sql.append("UPDATE t_vis_cob_ejecucion_plan ");
		sql.append("SET c_confirmo_accion = '" + confirmoAccion + "'");
		sql.append(", c_url_archivo = '" + pathRelativoPDFCobradores + "' ");
		sql.append("WHERE c_id_ejecucion_plan = " + idEjecucionPlan);
		jt = new JdbcTemplate(dataSource);
		jt.update(sql.toString());

	}

	@Override
	public Long cambiarCobrador(Long idCobrador, Long idEjecPlan) {

		jt = new JdbcTemplate(dataSource);

		StringBuffer sql = new StringBuffer(100);

		List rows = null;

		if (idCobrador != null) {
			sql.append("select unique colab.c_id_colaborador as idColaborador ");
			sql.append("from t_vis_tra_colaboradores colab ");
			sql.append("where colab.c_id_cobrador = " + idCobrador);
			rows = jt.queryForList(sql.toString());
		}

		Long idColaborador = null;

		if (rows != null && rows.size() > 0) {
			Map map = (Map) rows.get(0);
			if (map.get("IDCOLABORADOR") != null) {
				idColaborador = Long.valueOf(map.get("IDCOLABORADOR").toString());
			}
		}
		
		sql = new StringBuffer(100);
		sql.append("UPDATE T_VIS_COB_EJECUCION_PLAN ");
		sql.append("SET C_ID_COBRADOR = " + idColaborador);
		sql.append(" WHERE C_ID_EJECUCION_PLAN = " + idEjecPlan);
		jt.update(sql.toString());

		return idColaborador;
	}

	@Override
	public void cambiarCobradoresTareasPendientes(Long idCobIN, Long idCobOUT, Long idPartido) {
		StringBuffer sql = new StringBuffer("");
		sql.append("UPDATE T_VIS_COB_EJECUCION_PLAN ");
		
		//Setear por
		sql.append("SET c_id_cobrador = ( SELECT UNIQUE colab.c_id_colaborador " +
				"FROM t_vis_tra_colaboradores colab " +
				"INNER JOIN t_vis_eva_cobradores cob " +
				"ON colab.c_id_cobrador  = cob.c_id_cobrador " +
				"WHERE cob.c_id_cobrador = " + idCobIN +" ) ");
		
		// Where de UPDATE
		sql.append("WHERE C_ID_EJECUCION_PLAN IN ");
		sql.append("( SELECT ejec.c_id_ejecucion_plan ");
		sql.append("FROM t_vis_cob_ejecucion_plan ejec ");
		sql.append("INNER JOIN t_vis_tra_colaboradores colab ");
		sql.append("ON ejec.c_id_cobrador = colab.c_id_colaborador ");
		sql.append("INNER JOIN t_vis_eva_cobradores cob ");
		sql.append("ON colab.c_id_cobrador = cob.c_id_cobrador ");
		sql.append("WHERE cob.c_id_cobrador = " + idCobOUT + " ");
		sql.append("AND ejec.c_confirmo_accion = 'N' ");
		sql.append("AND ejec.c_id_accion = 8 ");
		sql.append("AND ejec.c_id_partido = "+ idPartido +" )");
		
	
		jt = new JdbcTemplate(dataSource);
		jt.update(sql.toString());
		
	}

	@Override
	public void cambiarAbogadosTareasPendientes(Long idAbogIN, Long idAbogOUT,
			Long idPartido) {
		StringBuffer sql = new StringBuffer("");
		sql.append("UPDATE T_VIS_COB_EJECUCION_PLAN ");
		
		//Setear por
		sql.append("SET c_id_abogado = " + idAbogIN +" ");
		
		// Where de UPDATE
		sql.append("WHERE C_ID_EJECUCION_PLAN IN ");
		sql.append("( SELECT ejec.c_id_ejecucion_plan ");
		sql.append("FROM t_vis_cob_ejecucion_plan ejec ");
		sql.append("WHERE ejec.c_id_abogado = " + idAbogOUT + " ");
		sql.append("AND ejec.c_confirmo_accion = 'N' ");
		sql.append("AND ejec.c_id_accion = 15 ");
		sql.append("AND ejec.c_id_partido = "+ idPartido +" )");
		jt = new JdbcTemplate(dataSource);
		jt.update(sql.toString());
		
	}

	@Override
	public Abogado buscarAbogadoTarea(final Long idEjecucionPlan) {
		
		return (Abogado) getHibernateTemplate().execute((new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer();
				sb.append("SELECT ejec.idAbogado, ");
				sb.append("ind.apellido, ");
				sb.append("ind.nombres ");
				sb.append("FROM EjecucionPlan ejec, Colaborador col ");
				sb.append("INNER JOIN col.individuo ind ");
				sb.append("WHERE ejec.idEjecucionPlan = :idEjecucionPlan ");
				sb.append("AND ejec.idAbogado = col.idColaborador ");
				Query query = session.createQuery(sb.toString());
				query.setLong("idEjecucionPlan", idEjecucionPlan);
				Object[] result = (Object[]) query.uniqueResult();
				
				Abogado ab = null;
				
				if (result != null){
					ab = new Abogado((Long)result[0],(String)result[1],(String)result[2]);
				}
				
				
				
				return ab;
			}
		}));
		
	}

	@Override
	public void cambiarAbogadoClienteTarea(final Long idEjecucionPlan, final Long idAbogIN) {
		
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Transaction trx = session.beginTransaction();
				StringBuffer hqlUpdate = new StringBuffer();
				hqlUpdate
						.append("UPDATE EjecucionPlan ejec SET ejec.idAbogado = :idAbogIN ");
				hqlUpdate.append("WHERE ejec.idEjecucionPlan = :idEjecucionPlan ");
				session.createQuery(hqlUpdate.toString())
						.setLong("idEjecucionPlan", idEjecucionPlan).setLong("idAbogIN", idAbogIN).executeUpdate();
				trx.commit();
				return null;
			}
		});
		
	}

	@Override
	public void insertarHistoricoMora(final Long idCliente) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.getNamedQuery("insertarHistoricoMora");
				query.setLong("idCliente", idCliente);
				return query.executeUpdate();
			}
		});
		
	}

	@Override
	public Long getIdEjecPlanCobradorAsignado(final Long idCliente) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ejec1.c_id_ejecucion_plan ");
		sql.append("FROM t_vis_cob_ejecucion_plan ejec1 ");
		sql.append("WHERE ejec1.c_id_accion = 8 ");
		sql.append("AND ejec1.c_id_usuario = "+idCliente);
		sql.append(" AND ejec1.c_id_ejecucion_plan not in ");		
		sql.append("(SELECT ejec.c_id_parent "
				    + "FROM t_vis_cob_ejecucion_plan ejec " 
					+ "WHERE ejec.c_id_accion = 9 "
					+ "AND ejec.c_id_usuario = "+idCliente
					+ " AND ejec.c_id_parent is not null) ");
		sql.append("AND rownum = 1");				
		
		jt = new JdbcTemplate(dataSource);
		return jt.queryForLong(sql.toString());			
	}

	@Override
	public void insertarErrorEjecucion(final Long idCliente, final Long idAccion, final String descripcion) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				Query query = session.getNamedQuery("insertarErrorEjecucion");
				query.setLong("idCliente", idCliente);
				query.setLong("idAccion", idAccion);
				query.setString("descripcion", descripcion);
				return query.executeUpdate();
			}
		});
		
	}
}

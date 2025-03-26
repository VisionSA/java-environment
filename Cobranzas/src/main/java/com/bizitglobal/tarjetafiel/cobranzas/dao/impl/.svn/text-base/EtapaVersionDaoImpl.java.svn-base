package com.bizitglobal.tarjetafiel.cobranzas.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.cobranzas.dao.EtapaVersionDao;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.Accion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.AccionVersion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EtapaVersion;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.PlanVersion;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ConceptoCabecera;


public class EtapaVersionDaoImpl extends HibernateDaoSupport implements EtapaVersionDao {
	
	private DataSource dataSource;
	private JdbcTemplate jt;

	public EtapaVersionDaoImpl() {
		super();
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
	
	
	public void grabarEtapaVersion (EtapaVersion pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public EtapaVersion buscarEtapaVersion (Long id) {
		return (EtapaVersion) this.getHibernateTemplate().get(EtapaVersion.class, id);
	}
	public void borrarEtapaVersion (Long id) {
		borrarEtapaVersion(buscarEtapaVersion(id));
	}
	public void borrarEtapaVersion (EtapaVersion pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEtapaVersion (EtapaVersion pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM EtapaVersion obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public List getEtapasVersionByFiltro(final PlanVersion planVersion) {

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT new EtapaVersion(ev.idEtapaVersion, ev.descripcion, ev.dias, ev.etapa, ev.nombreEtapa)");
				sb.append("FROM EtapaVersion ev ");
				sb.append("INNER JOIN ev.etapa e ");
				sb.append("WHERE ev.planVersion=:idPlanVersion ");
				sb.append("ORDER BY e.idEtapa asc");
				Query query = session.createQuery(sb.toString());
				query.setInteger("idPlanVersion", planVersion.getIdPlanVersion());
				List list = query.list();
				Iterator<EtapaVersion> it = list.iterator();
				while (it.hasNext()) {
					EtapaVersion ev = (EtapaVersion) it.next();
					ev.setAccionesVersion(seteoAccionesParaEtapaVersion(ev));
				}
				
				return list;
			}

			private Set<AccionVersion> seteoAccionesParaEtapaVersion(EtapaVersion ev) {
				
				
				Set<AccionVersion> resultado = new HashSet<AccionVersion>();
				
				StringBuffer sql = new StringBuffer("");
				sql.append("SELECT av.c_id_accion_version ID_ACCION_VERSION, " +
						"ACC.C_ID_ACCION ID_ACCION, " +
						"av.c_dias CANT_DIAS, " +
						"acc.c_descripcion DESC_ACCION, " +
						"con.c_id_concepto ID_CONCEPTO, " +
						"con.c_descripcion DESC_CONCEPTO, " +
						"con.c_codigo_concepto COD_CONCEPTO " +
						"FROM t_vis_cob_acciones_version av " +
						"INNER JOIN t_vis_cob_etapas_version ev " +
						"ON av.c_id_etapa_version=ev.c_id_etapa_version " +
						"INNER JOIN t_vis_cob_acciones acc " +
						"ON acc.c_id_accion = av.c_id_accion " +
						"LEFT OUTER JOIN t_vis_tra_conceptos con " +
						"on av.c_id_concepto=con.c_id_concepto " +
						"WHERE ev.c_id_etapa_version = "+ev.getIdEtapaVersion() +
						" ORDER BY CANT_DIAS ASC");
				
				jt = new JdbcTemplate(dataSource);
				List rows = jt.queryForList(sql.toString());
				
				Iterator iter = rows.iterator();
				while (iter.hasNext()) {
					Map map = (Map) iter.next();
					AccionVersion av = new AccionVersion();
					
					if (map.get("ID_ACCION_VERSION") != null) {
						av.setIdAccionVersion(Integer.valueOf(map.get("ID_ACCION_VERSION").toString()));
					}
					
					if (map.get("ID_ACCION") != null) {
						Accion acc = new Accion();
						acc.setIdAccion(Integer.valueOf(map.get("ID_ACCION").toString()));
						if (map.get("DESC_ACCION") != null){
							acc.setDescripcion(map.get("DESC_ACCION").toString());
						}
						av.setAccion(acc);
					}
					
					if (map.get("CANT_DIAS") != null) {
						av.setDias(Integer.valueOf(map.get("CANT_DIAS").toString()));
					}
					
					
					if (map.get("ID_CONCEPTO") != null) {
						ConceptoCabecera con = new ConceptoCabecera();
						con.setIdConcepto(Long.valueOf(map.get("ID_CONCEPTO").toString()));
						if (map.get("COD_CONCEPTO") != null) {
							con.setCodigoConcepto(Long.valueOf(map.get("COD_CONCEPTO").toString()));
						}
						if (map.get("DESC_CONCEPTO") != null) {
							con.setDescripcion(map.get("DESC_CONCEPTO").toString());
						}
						av.setConceptoCabecera(con);
					}
					
					av.setEtapaVersion(ev);
					
					resultado.add(av);
				}
				
				return resultado;				
				
			}
		});
	}
}

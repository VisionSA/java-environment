package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Asiento;



public class AsientoDaoHibernateImpl  extends HibernateDaoSupport implements AsientoDao {
	private Logger log = Logger.getLogger(AsientoDaoHibernateImpl.class);
	
	
	private DataSource dataSource;
	private JdbcTemplate jt;

	public void grabarAsiento(Asiento pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Asiento buscarAsiento(Long id) {
		return (Asiento)	this.getHibernateTemplate().get(Asiento.class, id);
	}
    public void borrarAsiento(Long id) {
        this.getHibernateTemplate().delete(buscarAsiento(id));
     }
    public void borrarAsiento(Asiento pObject) {
   	 this.getHibernateTemplate().delete(pObject);
  	}
	public void actualizarAsiento(Asiento pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	
	public List listarTodosConsultaEspecial(Filtro filtro) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(100);
		sql.append("SELECT c.c_asiento, c.c_empresa, c.c_ejercicio, c.c_concepto, c.c_tipo_asiento, c.c_fecha_contab, c.c_hora_carga, c.c_fecha_carga FROM t_cont_asientos_c c LEFT JOIN t_cont_asientos_d d ON d.c_asiento = c.c_asiento ");
		sql.append(filtro.getConsultaAMano());
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			Asiento asiento = new Asiento();
			asiento.setIdAsiento(new Long(map.get(Asiento.ID_ASIENTO).toString()));
			if (map.get(Asiento.ID_CONCEPTO)!=null) {
				asiento.setConcepto(map.get(Asiento.ID_CONCEPTO).toString());
			} else {
				asiento.setConcepto(null);
			}
			asiento.setFechaCarga((Date)map.get(Asiento.FECHA_CARGA));
			asiento.setFechaContab((Date)map.get(Asiento.FECHA_CONTAB));
			asiento.setHoraCarga(new Timestamp(((Date)map.get(Asiento.HORA_CARGA)).getTime()));
			asiento.setIdEjercicio(new Integer(map.get(Asiento.ID_EJERCICIO).toString()));
			asiento.setIdEmpresa(new Integer(map.get(Asiento.ID_EMPRESA).toString()));
			if (map.get(Asiento.TIPO_ASIENTO)!=null) {
				asiento.setIdTipoAsiento(new Integer(map.get(Asiento.TIPO_ASIENTO).toString()));
			} else {
				asiento.setIdTipoAsiento(null);
			}
			if (map.get(Asiento.OPERADOR)!=null) {
				asiento.setOperador(map.get(Asiento.OPERADOR).toString());
			} else {
				asiento.setOperador(null);
			}
			result.add(asiento);
		}
		return result;
	}
	
	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Asiento obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

	public Long contarAsientos(Filtro filtro) {
		final String hql = filtro.getHQL();
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT count(*) ");
				sb.append("FROM Asiento obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				Object res = query.uniqueResult();
				return new Long(res.toString());
			}
		});
	}
	
	public Asiento leerAsiento(Long id) {
		
		return null;
	}
	
	
	public Long getLastIdDeAsientos(Long idEjercicio, Long idEmpresa) {
		StringBuffer sql = new StringBuffer(100);
		sql.append("SELECT MAX(" + Asiento.ID_ASIENTO +") AS id ");
		sql.append(" FROM " + Asiento.ASIENTO);
		
		jt = new JdbcTemplate(dataSource);
		int id = jt.queryForInt(sql.toString());
		return new Long(id);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
       

   
   	
	
	
	
	



package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.LoteDetalleDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;
//import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle.Id;
//import com.bizitglobal.workflow.dao.impl.ClaseJavaDaoImpl;

public class LoteDetalleDaoHibernateImpl  extends HibernateDaoSupport implements LoteDetalleDao {
	private Logger log = Logger.getLogger(LoteDetalleDaoHibernateImpl.class);

	private DataSource dataSource;
	private JdbcTemplate jt;
	


	public List listarTodos(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT  t_cont_lote_d ");
				sb.append("FROM LoteDetalle t_cont_lote_d ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	

	
	public List listarTodosConsultaEspecial(Filtro filtro) {
		final String hql = filtro.getConsultaAMano();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT  t_cont_lote_d ");
				sb.append("FROM LoteDetalle t_cont_lote_d ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	

	public void grabar(LoteDetalle loteDetalle) {
		jt = new JdbcTemplate(dataSource);
		
		StringBuffer sqlInsert = new StringBuffer(500);
		//sqlInsert.append("INSERT INTO " + LoteDetalle.LOTE_DETALLE + "(");
		sqlInsert.append("INSERT INTO t_cont_asientos_d (");
		sqlInsert.append(LoteDetalle.ID_ASIENTO);
		sqlInsert.append(", " + LoteDetalle.ID_EJERCICIO);
		sqlInsert.append(", " + LoteDetalle.ID_EMPRESA);
		sqlInsert.append(", " + LoteDetalle.RENGLON);
		sqlInsert.append(", " + LoteDetalle.NUMERO_IMPUTA);
		sqlInsert.append(", " + LoteDetalle.SIGNO);
		sqlInsert.append(", " + LoteDetalle.IMPORTE);
		sqlInsert.append(", " + LoteDetalle.LEYENDA);
		sqlInsert.append(", " + LoteDetalle.FECHA_CONTAB);
		sqlInsert.append(", " + LoteDetalle.FECHA_CARGA);
		sqlInsert.append(", " + LoteDetalle.OPERADOR);
		sqlInsert.append(")");
		sqlInsert.append("VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		

		Object[] values = new Object[] {
				loteDetalle.getId().getIdAsiento(),
				loteDetalle.getId().getIdEjercicio(),
				loteDetalle.getId().getIdEmpresa(),
				loteDetalle.getId().getRenglon(),
				loteDetalle.getNumeroImputa(),
				loteDetalle.getSigno(),
				Double.valueOf(loteDetalle.getImporte()),
				loteDetalle.getLeyenda(),
				loteDetalle.getFechaContab(),
				loteDetalle.getFechaCarga(),
				loteDetalle.getOperador()};
		
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt.update(sqlInsert.toString(),values);
	}
	
	public long getBalance(Long idEjercicio, Long idEmpresa, Long idAsiento, String comparacion) {
		jt = new JdbcTemplate(dataSource);
		StringBuffer sqlUpdate = new StringBuffer(300);
		sqlUpdate.append("SELECT SUM("+ LoteDetalle.IMPORTE + ") AS Suma FROM " + LoteDetalle.LOTE_DETALLE);
		sqlUpdate.append(" WHERE " + LoteDetalle.ID_EJERCICIO + " = " + idEjercicio);
		sqlUpdate.append(" AND " + LoteDetalle.ID_ASIENTO + " = " + idAsiento);
		sqlUpdate.append(" AND " + LoteDetalle.ID_EMPRESA + " = " + idEmpresa);
		sqlUpdate.append(" AND " + LoteDetalle.SIGNO + " LIKE '" + comparacion + "'");
		System.out.println("Ejecute: " + sqlUpdate.toString());
		long resultado = jt.queryForLong(sqlUpdate.toString());
		return resultado;
	}
	
	public long getBalance(Long idEjercicio, Long idEmpresa, Long idAsiento) {
		jt = new JdbcTemplate(dataSource);
		StringBuffer sqlUpdate = new StringBuffer(300);
		sqlUpdate.append("SELECT SUM(nvl(DECODE(c_signo, 'D',-1, 'C',1)*" + LoteDetalle.IMPORTE + ",0)) AS Suma FROM " + LoteDetalle.LOTE_DETALLE);
		sqlUpdate.append(" WHERE " + LoteDetalle.ID_EJERCICIO + " = " + idEjercicio);
		sqlUpdate.append(" AND " + LoteDetalle.ID_ASIENTO + " = " + idAsiento);
		sqlUpdate.append(" AND " + LoteDetalle.ID_EMPRESA + " = " + idEmpresa);
		System.out.println("Ejecute: " + sqlUpdate.toString());
		long resultado = jt.queryForLong(sqlUpdate.toString());
		return resultado;
	}
	
	public void actualizar(LoteDetalle loteDetalle) {
		jt = new JdbcTemplate(dataSource);
		
		StringBuffer sqlUpdate = new StringBuffer(300);
		sqlUpdate.append("UPDATE " + LoteDetalle.LOTE_DETALLE + " SET ");
		sqlUpdate.append(LoteDetalle.NUMERO_IMPUTA + " = ?");
		sqlUpdate.append(", " + LoteDetalle.SIGNO + " = ?");
		sqlUpdate.append(", " + LoteDetalle.IMPORTE + " = ?");
		sqlUpdate.append(", " + LoteDetalle.LEYENDA + " = ?");
		sqlUpdate.append(", " + LoteDetalle.FECHA_CONTAB + " = ?");
		sqlUpdate.append(", " + LoteDetalle.FECHA_CARGA + " = ?");
		sqlUpdate.append(", " + LoteDetalle.OPERADOR + " = ?");
		
		sqlUpdate.append(" WHERE " + LoteDetalle.ID_EMPRESA + " = ?");
		sqlUpdate.append(" AND " + LoteDetalle.ID_EJERCICIO + " = ?");
		sqlUpdate.append(" AND " + LoteDetalle.RENGLON + " = ?");
        sqlUpdate.append(" AND " + LoteDetalle.ID_ASIENTO + " =  ?");
		
		Object[] values = new Object[] {
				loteDetalle.getNumeroImputa(),
				loteDetalle.getSigno(),
				Double.valueOf(loteDetalle.getImporte()),
				loteDetalle.getLeyenda(),
				loteDetalle.getFechaContab(),
				loteDetalle.getFechaCarga(),
				loteDetalle.getOperador(),
				loteDetalle.getId().getIdEmpresa(),
                loteDetalle.getId().getIdEjercicio(),				
				loteDetalle.getId().getRenglon(),
				loteDetalle.getId().getIdAsiento()};
		
		
		log.info("SQL Ejecutado ==> " + sqlUpdate.toString());
		jt.update(sqlUpdate.toString(),values);
	}
	
	public void borrar(Long idEjercicio, Long idEmpresa, Long idAsiento, Long idRenglon) {
		StringBuffer sql = new StringBuffer(150);
		sql.append("DELETE FROM " + LoteDetalle.LOTE_DETALLE);
		sql.append(" WHERE " + LoteDetalle.ID_EJERCICIO + " = " + idEjercicio);
		sql.append(" AND " + LoteDetalle.ID_EMPRESA + " = " + idEmpresa);
		sql.append(" AND " + LoteDetalle.ID_ASIENTO + " = " + idAsiento);
		sql.append(" AND " + LoteDetalle.RENGLON + " = " + idRenglon);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sql.toString());
	}
	
	public Long getLastIdDeRenglon(Long idEjercicio, Long idEmpresa, Long idAsiento) {
		StringBuffer sql = new StringBuffer(100);
		sql.append("SELECT MAX(" + LoteDetalle.RENGLON +") AS id ");
		sql.append(" FROM " + LoteDetalle.LOTE_DETALLE);
		sql.append(" WHERE " + LoteDetalle.ID_EJERCICIO + " = " + idEjercicio);
		sql.append(" AND " + LoteDetalle.ID_EMPRESA + " = " + idEmpresa);
		sql.append(" AND " + LoteDetalle.ID_ASIENTO + " = " + idAsiento);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
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

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
	
	public void borrarLosDetalles(Long idEjercicio, Long idEmpresa, Long idAsiento) {
		StringBuffer sql = new StringBuffer(100);
		sql.append("DELETE");
		sql.append(" FROM " + LoteDetalle.LOTE_DETALLE);
		sql.append(" WHERE " + LoteDetalle.ID_EJERCICIO + " = " + idEjercicio);
		sql.append(" AND " + LoteDetalle.ID_EMPRESA + " = " + idEmpresa);
		sql.append(" AND " + LoteDetalle.ID_ASIENTO + " = " + idAsiento);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sql.toString());
	}
	
}
       

   
   	
	
	
	
	



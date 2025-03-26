package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoDetalleDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoDetalle;
//import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoDetalle.Id;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;



public class AsientoDetalleDaoHibernateImpl  extends HibernateDaoSupport implements AsientoDetalleDao {


	private Logger log = Logger.getLogger(AsientoDetalleDaoHibernateImpl.class);

	private DataSource dataSource;
	private JdbcTemplate jt;
	
	

	public void grabarAsientoDetalle(AsientoDetalle pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public AsientoDetalle buscarAsientoDetalle(Long id) {
		return (AsientoDetalle)	this.getHibernateTemplate().get(AsientoDetalle.class, id);
	}
    public void borrarAsientoDetalle(Long id) {
        this.getHibernateTemplate().delete(buscarAsientoDetalle(id));
     }
    public void borrarAsientoDetalle(AsientoDetalle pObject) {
   	 this.getHibernateTemplate().delete(pObject);
  	}
	public void actualizarAsientoDetalle(AsientoDetalle pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT  t_cont_asientos_d ");
				sb.append("FROM AsientoDetalle t_cont_asientos_d ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	public AsientoDetalle leerAsientoDetalle(Long id) {
		
		return null;
	}
	public List listarTodosConsultaEspecial(Filtro filtro) {
		final String hql = filtro.getConsultaAMano();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT  t_cont_asientos_d ");
				sb.append("FROM AsientoDetalle t_cont_asientos_d ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	public Long getLastIdDeRenglon(Long idEjercicio, Long idEmpresa, Long idAsiento) {
		StringBuffer sql = new StringBuffer(100);
		sql.append("SELECT MAX(" + AsientoDetalle.RENGLON +") AS id ");
		sql.append(" FROM " + AsientoDetalle.ASIENTO_DETALLE);
		sql.append(" WHERE " + AsientoDetalle.ID_EJERCICIO + " = " + idEjercicio);
		sql.append(" AND " + AsientoDetalle.ID_EMPRESA + " = " + idEmpresa);
		sql.append(" AND " + AsientoDetalle.ID_ASIENTO + " = " + idAsiento);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		int id = jt.queryForInt(sql.toString());
		return new Long(id);
	}
	
	
	public void grabar(AsientoDetalle asientoDetalle) {
		jt = new JdbcTemplate(dataSource);
		
		StringBuffer sqlInsert = new StringBuffer(500);
		sqlInsert.append("INSERT INTO " + AsientoDetalle.ASIENTO_DETALLE + "(");
		sqlInsert.append(AsientoDetalle.ID_ASIENTO);
		sqlInsert.append(", " + AsientoDetalle.ID_EJERCICIO);
		sqlInsert.append(", " + AsientoDetalle.ID_EMPRESA);
		sqlInsert.append(", " + AsientoDetalle.RENGLON);
		sqlInsert.append(", " + AsientoDetalle.NUMERO_IMPUTA);
		sqlInsert.append(", " + AsientoDetalle.SIGNO);
		sqlInsert.append(", " + AsientoDetalle.IMPORTE);
		sqlInsert.append(", " + AsientoDetalle.LEYENDA);
		sqlInsert.append(", " + AsientoDetalle.FECHA_CONTAB);
		sqlInsert.append(", " + AsientoDetalle.FECHA_CARGA);
		sqlInsert.append(", " + AsientoDetalle.OPERADOR);
		sqlInsert.append(")");
		sqlInsert.append("VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		

		Object[] values = new Object[] {
				asientoDetalle.getId().getIdAsiento(),
				asientoDetalle.getId().getIdEjercicio(),
				asientoDetalle.getId().getIdEmpresa(),
				asientoDetalle.getId().getRenglon(),
				asientoDetalle.getNumeroImputa(),
				asientoDetalle.getSigno(),
				Double.valueOf(asientoDetalle.getImporte()),
				asientoDetalle.getLeyenda(),
				asientoDetalle.getFechaContab(),
				asientoDetalle.getFechaCarga(),
				asientoDetalle.getOperador()};
		
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt.update(sqlInsert.toString(),values);
	}
	
	public void actualizar(AsientoDetalle asientoDetalle) {
		jt = new JdbcTemplate(dataSource);
		
		StringBuffer sqlUpdate = new StringBuffer(300);
		sqlUpdate.append("UPDATE " + AsientoDetalle.ASIENTO_DETALLE + " SET ");
		sqlUpdate.append(AsientoDetalle.NUMERO_IMPUTA + " = ?");
		sqlUpdate.append(", " + AsientoDetalle.SIGNO + " = ?");
		sqlUpdate.append(", " + AsientoDetalle.IMPORTE + " = ?");
		sqlUpdate.append(", " + AsientoDetalle.LEYENDA + " = ?");
		sqlUpdate.append(", " + AsientoDetalle.FECHA_CONTAB + " = ?");
		sqlUpdate.append(", " + AsientoDetalle.FECHA_CARGA + " = ?");
		sqlUpdate.append(", " + AsientoDetalle.OPERADOR + " = ?");
		
		sqlUpdate.append(" WHERE " + AsientoDetalle.ID_EMPRESA + " = ?");
		sqlUpdate.append(" AND " + AsientoDetalle.ID_EJERCICIO + " = ?");
		sqlUpdate.append(" AND " + AsientoDetalle.RENGLON + " = ?");
        sqlUpdate.append(" AND " + AsientoDetalle.ID_ASIENTO + " =  ?");
		
		Object[] values = new Object[] {
				asientoDetalle.getNumeroImputa(),
				asientoDetalle.getSigno(),
				Double.valueOf(asientoDetalle.getImporte()),
				asientoDetalle.getLeyenda(),
				asientoDetalle.getFechaContab(),
				asientoDetalle.getFechaCarga(),
				asientoDetalle.getOperador(),
				asientoDetalle.getId().getIdEmpresa(),
                asientoDetalle.getId().getIdEjercicio(),				
				asientoDetalle.getId().getRenglon(),
				asientoDetalle.getId().getIdAsiento()};
		
		
		log.info("SQL Ejecutado ==> " + sqlUpdate.toString());
		jt.update(sqlUpdate.toString(),values);
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
		sql.append(" FROM " + AsientoDetalle.ASIENTO_DETALLE);
		sql.append(" WHERE " + AsientoDetalle.ID_EJERCICIO + " = " + idEjercicio);
		sql.append(" AND " + AsientoDetalle.ID_EMPRESA + " = " + idEmpresa);
		sql.append(" AND " + AsientoDetalle.ID_ASIENTO + " = " + idAsiento);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sql.toString());
	}
	
	public void borrar(Long idEjercicio, Long idEmpresa, Long idAsiento, Long idRenglon) {
		StringBuffer sql = new StringBuffer(150);
		sql.append("DELETE FROM " + AsientoDetalle.ASIENTO_DETALLE);
		sql.append(" WHERE " + AsientoDetalle.ID_EJERCICIO + " = " + idEjercicio);
		sql.append(" AND " + AsientoDetalle.ID_EMPRESA + " = " + idEmpresa);
		sql.append(" AND " + AsientoDetalle.ID_ASIENTO + " = " + idAsiento);
		sql.append(" AND " + AsientoDetalle.RENGLON + " = " + idRenglon);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sql.toString());
	}
	
}
       

   
   	
	
	
	
	



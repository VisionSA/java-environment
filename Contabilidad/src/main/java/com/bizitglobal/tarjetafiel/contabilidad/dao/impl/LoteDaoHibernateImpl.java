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
import com.bizitglobal.tarjetafiel.contabilidad.dao.LoteDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Asiento;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Lote;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;



public class LoteDaoHibernateImpl  extends HibernateDaoSupport implements LoteDao {
	private Logger log = Logger.getLogger(LoteDaoHibernateImpl.class);
	
	private DataSource dataSource;
	private JdbcTemplate jt;


	public void grabarLote(Lote lote) {
		jt = new JdbcTemplate(dataSource);
		
		StringBuffer sqlInsert = new StringBuffer(300);
		//sqlInsert.append("INSERT INTO " + Lote.LOTE + "(");
		sqlInsert.append("INSERT INTO t_cont_asientos_c (");
		sqlInsert.append(LoteDetalle.ID_ASIENTO);
		sqlInsert.append(", " + Lote.ID_EJERCICIO);
		sqlInsert.append(", " + Lote.ID_EMPRESA);
		sqlInsert.append(", " + Lote.ID_CONCEPTO);
		sqlInsert.append(", " + Lote.TIPO_ASIENTO);
		sqlInsert.append(", " + Lote.HORA_CARGA);
		sqlInsert.append(", " + Lote.FECHA_CONTAB);
		sqlInsert.append(", " + Lote.FECHA_CARGA);
		sqlInsert.append(", " + Lote.OPERADOR);
		
		sqlInsert.append(")");
		sqlInsert.append("VALUES(?,?,?,?,?,?,?,?,?)");
		

		Object[] values = new Object[] {
				lote.getIdAsiento(),
				lote.getIdEjercicio(),
				lote.getIdEmpresa(),
				lote.getConcepto(),
				lote.getIdTipoAsiento(),
				lote.getHoraCarga(),
				lote.getFechaContab(),
				lote.getFechaCarga(),
				lote.getOperador()};
		
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt.update(sqlInsert.toString(),values);
	}
	
	//Este metodo es para impactar las tablas del sistema viejo, la table es t_origen_contab
	public void grabarOrigenContab(Long nroAsiento, Long idProveedor, Long idComprobante) {
		jt = new JdbcTemplate(dataSource);
		
		StringBuffer sqlInsert = new StringBuffer(300);
		sqlInsert.append("INSERT INTO t_origen_contab (");
		sqlInsert.append("c_nro_asien_c");
		sqlInsert.append(", c_origen");
		sqlInsert.append(", c_proveedor");
		sqlInsert.append(", c_nro_cpte2_p");
		sqlInsert.append(")");
		sqlInsert.append("VALUES(?,?,?,?)");
		

		Object[] values = new Object[] {
				nroAsiento,
				"P",
				idProveedor,
				idComprobante};
		
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt.update(sqlInsert.toString(),values);
	}
	
	public Lote buscarLote(Long id) {
		return (Lote)	this.getHibernateTemplate().get(Lote.class, id);
	}
    public void borrarLote(Long id) {
        this.getHibernateTemplate().delete(buscarLote(id));
     }
    public void borrarLote(Lote pObject) {
   	 this.getHibernateTemplate().delete(pObject);
  	}
	public void actualizarLote(Lote pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	
	
	
	public List listarTodos(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Lote obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	public Long contarLotes(Filtro filtro) {
		final String hql = filtro.getHQL();
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT count(*) ");
				sb.append("FROM Lote obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				Object res = query.uniqueResult();
				return new Long(res.toString());
			}
		});
	}
	
	public Lote leerLote(Long id) {
		return null;
	}
	
	public void moverLote(Long idEjercicio, Long idEmpresa, Long idAsiento) {
		jt = new JdbcTemplate(dataSource);
		StringBuffer sqlInsert = new StringBuffer(300);
		sqlInsert.append("INSERT INTO t_cont_asientos_c(");
		sqlInsert.append("c_empresa, c_ejercicio, c_asiento, c_concepto, c_tipo_asiento, c_fecha_contab, c_fecha_carga, c_hora_carga, c_operador)");
		sqlInsert.append(" SELECT Lote." +Lote.ID_EMPRESA+ ", Lote." +Lote.ID_EJERCICIO+ ", Lote."+ Lote.ID_ASIENTO + ", Lote."+Lote.ID_CONCEPTO+ ", Lote." +Lote.TIPO_ASIENTO + ", Lote." + Lote.FECHA_CONTAB + ", Lote." + Lote.FECHA_CARGA +", Lote." + Lote.HORA_CARGA +", Lote." + Lote.OPERADOR);
		sqlInsert.append(" FROM " + Lote.LOTE + " Lote");
		sqlInsert.append(" WHERE Lote." + Lote.ID_ASIENTO + " = " + idAsiento);
		sqlInsert.append(" GROUP BY Lote." + Lote.ID_EMPRESA + ", Lote." +Lote.ID_EJERCICIO + ", Lote." +Lote.ID_ASIENTO+ ", Lote."+Lote.ID_CONCEPTO+ ", Lote." +Lote.TIPO_ASIENTO + ", Lote." + Lote.FECHA_CONTAB + ", Lote." + Lote.FECHA_CARGA +", Lote." + Lote.HORA_CARGA +", Lote." + Lote.OPERADOR);
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sqlInsert.toString());
			
		moverDetallesDelLote(idEjercicio,idEmpresa,idAsiento);
		
		jt = new JdbcTemplate(dataSource);
		sqlInsert = new StringBuffer(300);
		sqlInsert.append("DELETE FROM " + Lote.LOTE + " WHERE " + Lote.ID_ASIENTO + " = " + idAsiento);
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sqlInsert.toString());
	}
	
	public void moverDetallesDelLote(Long idEjercicio, Long idEmpresa, Long idAsiento) {
		jt = new JdbcTemplate(dataSource);
		StringBuffer sqlInsert = new StringBuffer(300);
		sqlInsert.append("INSERT INTO t_cont_asientos_d(");
		sqlInsert.append("c_empresa, c_ejercicio, c_asiento, c_renglon, c_numero_imputa, c_signo, c_leyenda, c_importe, c_fecha_contab, c_fecha_carga, c_hora_carga, c_operador)");
		sqlInsert.append(" SELECT c_empresa, c_ejercicio, c_asiento, c_renglon, c_numero_imputa, c_signo, c_leyenda, c_importe, c_fecha_contab, c_fecha_carga, c_hora_carga, c_operador");
		sqlInsert.append(" FROM t_cont_lote_d");
		sqlInsert.append(" WHERE " + LoteDetalle.ID_EJERCICIO + " = " + idEjercicio);
		sqlInsert.append(" AND " + LoteDetalle.ID_EMPRESA + " = " + idEmpresa);
		sqlInsert.append(" AND " + LoteDetalle.ID_ASIENTO + " = " + idAsiento);
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sqlInsert.toString());
			
		sqlInsert = new StringBuffer(300);
		sqlInsert.append("DELETE FROM t_cont_lote_d");
		sqlInsert.append(" WHERE " + LoteDetalle.ID_EJERCICIO + " = " + idEjercicio);
		sqlInsert.append(" AND " + LoteDetalle.ID_EMPRESA + " = " + idEmpresa);
		sqlInsert.append(" AND " + LoteDetalle.ID_ASIENTO + " = " + idAsiento);
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sqlInsert.toString());

	}
	
	public Long getLastIdDeAsiento() {
		//recupero el maximo id de la tabla t_empre.
		StringBuffer sql = new StringBuffer(100);
		sql.append("SELECT c_ultimo_asiento AS id ");
		sql.append(" FROM t_empre WHERE c_empresa = 1");		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		int id = jt.queryForInt(sql.toString());
		
		//Actualizo el maximo c_ultimo_asiento de la tabla t_empre
		jt = new JdbcTemplate(dataSource);
		StringBuffer sqlInsert = new StringBuffer(300);
		sqlInsert.append("UPDATE t_empre SET c_ultimo_asiento = " + new Long(id + 1));
		sqlInsert.append(" WHERE c_empresa = 1 AND c_sucursal = 0");
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sqlInsert.toString());
		
		return new Long(id);
	}

	public Long getLastIdDeLote() {
//		recupero el maximo id de la tabla t_empre.
		StringBuffer sql = new StringBuffer(100);
		sql.append("SELECT c_ultimo_asiento AS id ");
		sql.append(" FROM t_empre WHERE c_empresa = 1");
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		int id = jt.queryForInt(sql.toString());
		
		//Actualizo el maximo c_ultimo_asiento de la tabla t_empre
		jt = new JdbcTemplate(dataSource);
		StringBuffer sqlInsert = new StringBuffer(300);
		sqlInsert.append("UPDATE t_empre SET c_ultimo_asiento = " + new Long(id + 1));
		sqlInsert.append(" WHERE c_empresa = 1 AND c_sucursal = 0");
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sqlInsert.toString());
		
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
	
	public List listarTodosConsultaEspecial(Filtro filtro) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(100);
		sql.append("SELECT c.c_asiento, c.c_empresa, c.c_ejercicio, c.c_concepto, c.c_tipo_asiento, c.c_fecha_contab, c.c_hora_carga, c.c_fecha_carga FROM t_cont_lote_c c LEFT JOIN t_cont_lote_d d ON d.c_asiento = c.c_asiento ");
		sql.append(filtro.getConsultaAMano());
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			Lote lote = new Lote();
			lote.setIdAsiento(new Long(map.get(Lote.ID_ASIENTO).toString()));
			if (map.get(Asiento.ID_CONCEPTO)!=null) {
				lote.setConcepto(map.get(Lote.ID_CONCEPTO).toString());
			} else {
				lote.setConcepto("");
			}
			lote.setFechaCarga((Date)map.get(Lote.FECHA_CARGA));
			lote.setFechaContab((Date)map.get(Lote.FECHA_CONTAB));
		    lote.setHoraCarga(new Timestamp(((Date)map.get(Lote.HORA_CARGA)).getTime()));
			lote.setIdEjercicio(new Integer(map.get(Lote.ID_EJERCICIO).toString()));
			lote.setIdEmpresa(new Integer(map.get(Lote.ID_EMPRESA).toString()));
			if (map.get(Lote.TIPO_ASIENTO)!=null) {
				lote.setIdTipoAsiento(new Integer(map.get(Lote.TIPO_ASIENTO).toString()));
			} else {
				lote.setIdTipoAsiento(null);
			}
			if (map.get(Lote.OPERADOR)!=null) {
				lote.setOperador(map.get(Lote.OPERADOR).toString());
			} else {
				lote.setOperador(null);
			}
			result.add(lote);
		}
		return result;
	}
}
       

   
   	
	
	
	
	



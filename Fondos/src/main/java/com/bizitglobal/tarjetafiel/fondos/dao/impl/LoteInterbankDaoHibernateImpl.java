package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
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
import com.bizitglobal.tarjetafiel.fondos.dao.LoteInterbankDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.LoteInterbank;
import com.bizitglobal.tarjetafiel.fondos.negocio.RenglonLibroMayor;

public class LoteInterbankDaoHibernateImpl extends HibernateDaoSupport implements LoteInterbankDao  {
	private static final Logger log = Logger.getLogger(LoteInterbankDaoHibernateImpl.class);
	private DataSource dataSource;
	private JdbcTemplate jt;
	public LoteInterbankDaoHibernateImpl() {
		super();
	}

	public void grabarLoteInterbank (LoteInterbank pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public LoteInterbank buscarLoteInterbank (Long id) {
		return (LoteInterbank) this.getHibernateTemplate().get(LoteInterbank.class, id);
	}
	public void borrarLoteInterbank (Long id) {
		borrarLoteInterbank(buscarLoteInterbank(id));
	}
	public void borrarLoteInterbank (LoteInterbank pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarLoteInterbank (LoteInterbank pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM LoteInterbank obj ");
				sb.append(hql);
				
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	/********Emiliano*************/
	public List listarTodos(Date fechaGenerado,Date fechaSolicitud, Long idBanco){
		 
		StringBuffer sql = new StringBuffer(100);
		SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");

		sql.append(" SELECT DISTINCT loteinterb0_.C_ID_LOTE_INTERBANK,");
		sql.append("  loteinterb0_.C_CABECERA                       ," );
		sql.append("  loteinterb0_.C_FECHA_GENERADO                 ," );
		sql.append("  loteinterb0_.C_FECHA_SOLICITUD                ," );
		sql.append("  loteinterb0_.C_ID_BANCO_PROPIO                ," );
		sql.append("  loteinterb0_.C_ID_OPERADOR                     " );
		sql.append("   FROM T_VIS_FON_LOTE_INTERBANK loteinterb0_	 " );
		sql.append("  WHERE trunc(loteinterb0_.C_FECHA_GENERADO)=to_date('"+fechaFormat.format(fechaGenerado)+"', 'DD/MM/YYYY')" );
		sql.append("  AND loteinterb0_.C_FECHA_SOLICITUD =to_date('"+fechaFormat.format(fechaSolicitud)+"', 'DD/MM/YYYY')" );
		sql.append("  AND loteinterb0_.C_ID_BANCO_PROPIO   ="+ idBanco.longValue());
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());		
		return rows;
	}
	
	public List generarlistaInterbank(Long id_lote_interbank){
		StringBuffer sql = new StringBuffer(100);
		
		sql.append("  SELECT C_CABECERA");
		sql.append("  FROM T_VIS_FON_LOTE_INTERBANK" );
		sql.append("  WHERE C_ID_LOTE_INTERBANK = "+id_lote_interbank.longValue() );
		sql.append("  UNION");
		sql.append("  SELECT C_REGISTRO" );
		sql.append("  FROM T_VIS_FON_REGISTRO_UPLOAD " );
		sql.append("  WHERE C_ID_LOTE_INTERBANK = "+id_lote_interbank.longValue() );
		sql.append("  order by C_CABECERA desc");
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());		
		return rows;
	}
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/*****************************/
}


package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;



import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bizitglobal.tarjetafiel.evaluacion.dao.SolicitudImprimibleDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudImprimible;


public class SolicitudImprimibleDaoImpl implements SolicitudImprimibleDao{
	private Logger log = Logger.getLogger(SolicitudImprimibleDaoImpl.class);
	
	private DataSource dataSource;
	private JdbcTemplate jt;
	

	public void guardar(SolicitudImprimible sol){
		jt = new JdbcTemplate(dataSource);
		
		StringBuffer sqlInsert = new StringBuffer(200);
		sqlInsert.append("INSERT INTO " + SolicitudImprimible.SOLICITUD_IMPRIMIBLE + "(");
		sqlInsert.append(SolicitudImprimible.ID);
		sqlInsert.append(", " +SolicitudImprimible.ID_OPERADOR);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD1);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD2);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD3);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD4);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD5);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD6);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD7);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD8);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD9);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD10);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD11);
		sqlInsert.append(", " + SolicitudImprimible.NRO_SOLICITUD12);
		sqlInsert.append(")");
		sqlInsert.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		


		Object[] values = new Object[] {
				sol.getId(),
				sol.getIdOperador(),
				sol.getNroSolicitud1(),
				sol.getNroSolicitud2(),
				sol.getNroSolicitud3(),
				sol.getNroSolicitud4(),
				sol.getNroSolicitud5(),
				sol.getNroSolicitud6(),
				sol.getNroSolicitud7(),
				sol.getNroSolicitud8(),
				sol.getNroSolicitud9(),
				sol.getNroSolicitud10(),
				sol.getNroSolicitud11(),
				sol.getNroSolicitud12()	
				};
		
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt.update(sqlInsert.toString(),values);				
	}
	
	public void limpiar(Long idOperador){
		StringBuffer sql = new StringBuffer(100);
		sql.append("DELETE FROM " + SolicitudImprimible.SOLICITUD_IMPRIMIBLE);
		sql.append(" WHERE " + SolicitudImprimible.ID_OPERADOR + " = " + idOperador);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sql.toString());		
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}	

	public Long getLastId() {
		StringBuffer sql = new StringBuffer(100);
		sql.append("SELECT MAX(" + SolicitudImprimible.ID +") AS id ");
		sql.append(" FROM " + SolicitudImprimible.SOLICITUD_IMPRIMIBLE);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		int id = jt.queryForInt(sql.toString());
		return new Long(id);
	}	
}
package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoProveedorDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoProveedor;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoProveedorDetalle;




public class AsientoProveedorDaoHibernateImpl  extends HibernateDaoSupport implements AsientoProveedorDao {
	private Logger log = Logger.getLogger(AsientoProveedorDaoHibernateImpl.class);
	
	
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void actualizarAsientoProveedor(AsientoProveedor asientoProveedor) {
		StringBuffer sql = new StringBuffer(100);
		sql.append("UPDATE " + AsientoProveedor.PROV_COMPROBANTES);
		sql.append(" SET " + AsientoProveedor.CONTABILIZADO+ " = 'S'");
		sql.append(" WHERE " + AsientoProveedor.NRO_COMPROBANTE + " = " + asientoProveedor.getComprobante());
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		jt.update(sql.toString());
	}
	
	public Long getLastIdDeAsientos(Long idEjercicio, Long idEmpresa) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Pasar las dos fechas, la de inicio y la de fin del ejercicio para filtrar solamente los que se pueden importar.
	 * */
	public List listarTodosImportables(Date inicioEjercicio, Date finEjercicio) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(100);
/*@I3824*/	sql.append("SELECT com." + AsientoProveedor.PROVEEDOR +", com." +AsientoProveedor.CONTABILIZADO +", com." + AsientoProveedor.NRO_COMPROBANTE);
		sql.append(", com." + AsientoProveedor.FECHA_CONTAB+ ", com."+ AsientoProveedor.OPERADOR + ", com." + " c_concepto_asiento as concepto ");
		sql.append(" FROM " + AsientoProveedor.PROV_COMPROBANTES +" com ");
		sql.append(" WHERE com." + AsientoProveedor.CONTABILIZADO + " = 'N' AND com." + AsientoProveedor.FECHA_CONTAB + " >= " + Filtro.getTO_DATE(new Timestamp(inicioEjercicio.getTime())) + " AND com."+ AsientoProveedor.FECHA_CONTAB + " <= " + Filtro.getTO_DATE(new Timestamp(finEjercicio.getTime())));
		sql.append("AND EXISTS ( SELECT * FROM t_vis_prov_asiento_cte asi WHERE asi.c_id_comprobante = com.c_id_comprobante )");
//		sql.append(" and  c_id_tipo_cte not in (1,27)" );
/*@F3824*/		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			AsientoProveedor asientoProveedor = new AsientoProveedor();
			asientoProveedor.setProveedor(new Long(map.get(AsientoProveedor.PROVEEDOR).toString()));
			asientoProveedor.setComprobante(new Long(map.get(AsientoProveedor.NRO_COMPROBANTE).toString()));
			if (map.get(AsientoProveedor.CONCEPTO_ASIENTO)!=null) { 
				asientoProveedor.setConcepto(map.get(AsientoProveedor.CONCEPTO_ASIENTO).toString());
			} else {
				asientoProveedor.setConcepto(null);
			} 
			asientoProveedor.setFechaContab(new Date(((Timestamp)map.get(AsientoProveedor.FECHA_CONTAB)).getTime()));
			if (map.get(AsientoProveedor.OPERADOR)!=null) {
				asientoProveedor.setOperador(map.get(AsientoProveedor.OPERADOR).toString());
			} else {
				asientoProveedor.setOperador(null);
			}
			result.add(asientoProveedor);
		}
		return result;
	}
  
	public List listarDetallesDelImportable(Long id) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(300);
		sql.append("SELECT " +AsientoProveedorDetalle.DETALLE_ASIENTO +", " + AsientoProveedorDetalle.DETALLE_COMPROBANTE);
		sql.append(", " + AsientoProveedorDetalle.DETALLE_IMPORTE_DEBE+ ", "+AsientoProveedorDetalle.DETALLE_IMPORTE_HABER + ", " + AsientoProveedorDetalle.DETALLE_LEYENDA);
        sql.append(", " + AsientoProveedorDetalle.DETALLE_NUMERO_IMPUTA);
		sql.append(" FROM " + AsientoProveedorDetalle.PROV_ASIENTO_CTE);
		sql.append(" WHERE " + AsientoProveedorDetalle.DETALLE_COMPROBANTE + " = " + id);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			AsientoProveedorDetalle asientoProveedorDetalle = new AsientoProveedorDetalle();
			asientoProveedorDetalle.setComprobante(new Long(map.get(AsientoProveedorDetalle.DETALLE_COMPROBANTE).toString()));
			if (map.get(AsientoProveedorDetalle.DETALLE_NUMERO_IMPUTA)!=null) {
				asientoProveedorDetalle.setNumeroImputa(new Long(map.get(AsientoProveedorDetalle.DETALLE_NUMERO_IMPUTA).toString()));
			} else {
				asientoProveedorDetalle.setNumeroImputa(null);
			}
			if (map.get(AsientoProveedorDetalle.DETALLE_LEYENDA)!=null) {
				asientoProveedorDetalle.setLeyenda(map.get(AsientoProveedorDetalle.DETALLE_LEYENDA).toString());
			} else {
				asientoProveedorDetalle.setLeyenda(null);
			}
			if (map.get(AsientoProveedorDetalle.DETALLE_IMPORTE_DEBE)!=null) {
				asientoProveedorDetalle.setImporteDebe((new Double(map.get(AsientoProveedorDetalle.DETALLE_IMPORTE_DEBE).toString())).doubleValue());
			} 
			if (map.get(AsientoProveedorDetalle.DETALLE_IMPORTE_HABER)!=null) {
				asientoProveedorDetalle.setImporteHaber((new Double(map.get(AsientoProveedorDetalle.DETALLE_IMPORTE_HABER).toString())).doubleValue());
			} 
			result.add(asientoProveedorDetalle);
		}
		return result;
	}
	
	

}
       

   
   	
	
	
	
	



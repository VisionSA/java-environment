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
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoClienteDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoComercioDao;
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoProveedorDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoBase;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoCliente;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoClienteDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoComercio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoComercioDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoProveedor;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoProveedorDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Importable;




public class AsientoComercioDaoHibernateImpl  extends HibernateDaoSupport implements AsientoComercioDao {
	private Logger log = Logger.getLogger(AsientoComercioDaoHibernateImpl.class);
	
	
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void actualizarAsientoComercio(AsientoComercio asientoComercio) {
		StringBuffer sql = new StringBuffer(100);
		sql.append("UPDATE " + AsientoComercio.TRA_CTACTE_COMERCIO);
		sql.append(" SET " + AsientoComercio.CONTABILIZADO+ " = 'S'");
		sql.append(" WHERE " + AsientoComercio.NRO_CTA_CTE + " = " + asientoComercio.getCtaCteComercio());
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
		sql.append("SELECT cta.c_id_cod_comercio as idComercio, " +AsientoComercio.CONTABILIZADO +", " + AsientoComercio.NRO_CTA_CTE +" ,"+ AsientoComercio.FECHA_CONTAB +" , cta.c_id_operador , cta.c_nro_cuota || '  ' ||  cdet.c_nombre || '  ' || cta.c_id_cod_comercio as concepto ");
		sql.append(" FROM " + AsientoComercio.TRA_CTACTE_COMERCIO+" cta , t_vis_tra_transacciones t,  t_vis_tra_conceptos_detalle  cdet ");
		sql.append(" WHERE  cta.c_id_tranascciones =  t.c_id_tranascciones  and    cta.c_id_concepto_detalle =  cdet.c_id_concepto_detalle and " );
		sql.append(AsientoComercio.CONTABILIZADO + " = 'N' AND " + AsientoComercio.FECHA_CONTAB + " >= " + Filtro.getTO_DATE(new Timestamp(inicioEjercicio.getTime())) + " AND "+ AsientoComercio.FECHA_CONTAB + " <= " + Filtro.getTO_DATE(new Timestamp(finEjercicio.getTime())));
		//and t.c_estado_impacto ='C'
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			AsientoComercio asientoComercio = new AsientoComercio();
			asientoComercio.setComercio(new Long(map.get(AsientoComercio.COMERCIO).toString()));
			asientoComercio.setCtaCteComercio(new Long(map.get(AsientoComercio.NRO_CTA_CTE).toString()));
			if (map.get(AsientoComercio.CONCEPTO_ASIENTO)!=null) {
				asientoComercio.setConcepto(map.get(AsientoComercio.CONCEPTO_ASIENTO).toString());
			} else {
				asientoComercio.setConcepto(null);
			} 
			asientoComercio.setFechaContab(new Date(((Timestamp)map.get(AsientoComercio.FECHA_CONTAB)).getTime()));
			if (map.get(AsientoComercio.OPERADOR)!=null) {
				asientoComercio.setOperador(map.get(asientoComercio.OPERADOR).toString());
			} else {
				asientoComercio.setOperador(null);
			}
			result.add(asientoComercio);
		}
		return result;
	}
  
	public List listarDetallesDelImportable(Long id) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(300);

	//	SELECT c_id_ctacte_cliente, c_importe ,c_ctacontabledebe, c_ctacontablehaber
	//	 FROM t_vis_tra_ctacte_clientes
	//	 WHERE c_id_ctacte_cliente = 12

		sql.append("SELECT " + AsientoComercioDetalle.DETALLE_COMPROBANTE);
		sql.append(", " + AsientoComercioDetalle.DETALLE_IMPORTE+ ", "+ AsientoComercioDetalle.DETALLE_NUMERO_IMPUTA_DEBE);
        sql.append(", " + AsientoComercioDetalle.DETALLE_NUMERO_IMPUTA_HABER);
		sql.append(" FROM " + AsientoComercioDetalle.TRA_ASIENTO_CTACTE_CLIENTE);
		sql.append(" WHERE " + AsientoComercioDetalle.DETALLE_COMPROBANTE + " = " + id);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			AsientoComercioDetalle asientoComercioDetalle = new AsientoComercioDetalle();
			asientoComercioDetalle.setComprobante(new Long(map.get(AsientoComercioDetalle.DETALLE_COMPROBANTE).toString()));
			if (map.get(asientoComercioDetalle.DETALLE_NUMERO_IMPUTA_DEBE)!=null) {
				asientoComercioDetalle.setNumeroImputa(new Long(map.get(AsientoComercioDetalle.DETALLE_NUMERO_IMPUTA_DEBE).toString()));
			} else {
				asientoComercioDetalle.setNumeroImputa(null);
			}
			if (map.get(AsientoComercioDetalle.DETALLE_NUMERO_IMPUTA_HABER)!=null) {
				asientoComercioDetalle.setNumeroImputaHaber((new Long(map.get(AsientoComercioDetalle.DETALLE_NUMERO_IMPUTA_HABER).toString())));
			} else {
				asientoComercioDetalle.setNumeroImputaHaber(null);
			}
			if (map.get(AsientoComercioDetalle.DETALLE_IMPORTE)!=null) {
				asientoComercioDetalle.setImporte(new Double(map.get(AsientoComercioDetalle.DETALLE_IMPORTE).toString()).doubleValue());
			}				
			result.add(asientoComercioDetalle);
		}
		return result;
	}
	
	

}
       

   
   	
	
	
	
	



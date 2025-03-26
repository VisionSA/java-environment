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
import com.bizitglobal.tarjetafiel.contabilidad.dao.AsientoProveedorDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoBase;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoCliente;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoClienteDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoProveedor;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoProveedorDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Importable;




public class AsientoClienteDaoHibernateImpl  extends HibernateDaoSupport implements AsientoClienteDao {
	private Logger log = Logger.getLogger(AsientoClienteDaoHibernateImpl.class);
	
	
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void actualizarAsientoCliente(AsientoCliente asientoCliente) {
		StringBuffer sql = new StringBuffer(100);
		sql.append("UPDATE " + AsientoCliente.TRA_CTACTE_CLIENTE);
		sql.append(" SET " + AsientoCliente.CONTABILIZADO+ " = 'S'");
		sql.append(" WHERE " + AsientoCliente.NRO_CTA_CTE + " = " + asientoCliente.getCtaCteCliente());
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
		sql.append("SELECT cta.c_id_cliente as idCliente, " +AsientoCliente.CONTABILIZADO +", " + AsientoCliente.NRO_CTA_CTE +" ,"+ AsientoCliente.FECHA_CONTAB +" ,"+AsientoCliente.OPERADOR +", cta.c_nro_cuota || '  ' ||  cdet.c_nombre || '  ' || cta.c_id_cliente as concepto ");
		sql.append(" FROM " + AsientoCliente.TRA_CTACTE_CLIENTE+" cta , t_vis_tra_transacciones t,  t_vis_tra_conceptos_detalle  cdet ");
		sql.append(" WHERE  cta.c_id_tranascciones =  t.c_id_tranascciones  and    cta.c_id_concepto_detalle =  cdet.c_id_concepto_detalle and cta.c_estado_impacto ='C' and " );
		sql.append(AsientoCliente.CONTABILIZADO + " <> 'S' AND " + AsientoCliente.FECHA_CONTAB + " >= " + Filtro.getTO_DATE(new Timestamp(inicioEjercicio.getTime())) + " AND "+ AsientoCliente.FECHA_CONTAB + " <= " + Filtro.getTO_DATE(new Timestamp(finEjercicio.getTime())));
		//and t.c_estado_impacto ='C'
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			AsientoCliente asientoCliente = new AsientoCliente();
			asientoCliente.setCliente(new Long(map.get(AsientoCliente.CLIENTE).toString()));
			asientoCliente.setCtaCteCliente(new Long(map.get(AsientoCliente.NRO_CTA_CTE).toString()));
			if (map.get(AsientoCliente.CONCEPTO_ASIENTO)!=null) {
				asientoCliente.setConcepto(map.get(AsientoCliente.CONCEPTO_ASIENTO).toString());
			} else {
				asientoCliente.setConcepto(null);
			} 
			asientoCliente.setFechaContab(new Date(((Timestamp)map.get(AsientoCliente.FECHA_CONTAB)).getTime()));
			if (map.get(AsientoCliente.OPERADOR)!=null) {
				asientoCliente.setOperador(map.get(AsientoCliente.OPERADOR).toString());
			} else {
				asientoCliente.setOperador(null);
			}
			result.add(asientoCliente);
		}
		return result;
	}
  
	public List listarDetallesDelImportable(Long id) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(300);

	//	SELECT c_id_ctacte_cliente, c_importe ,c_ctacontabledebe, c_ctacontablehaber
	//	 FROM t_vis_tra_ctacte_clientes
	//	 WHERE c_id_ctacte_cliente = 12

		sql.append("SELECT " + AsientoClienteDetalle.DETALLE_COMPROBANTE);
		sql.append(", " + AsientoClienteDetalle.DETALLE_IMPORTE+ ", "+ AsientoClienteDetalle.DETALLE_NUMERO_IMPUTA_DEBE);
        sql.append(", " + AsientoClienteDetalle.DETALLE_NUMERO_IMPUTA_HABER);
		sql.append(" FROM " + AsientoClienteDetalle.TRA_ASIENTO_CTACTE_CLIENTE);
		sql.append(" WHERE " + AsientoClienteDetalle.DETALLE_COMPROBANTE + " = " + id);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			AsientoClienteDetalle asientoClienteDetalle = new AsientoClienteDetalle();
			asientoClienteDetalle.setComprobante(new Long(map.get(AsientoClienteDetalle.DETALLE_COMPROBANTE).toString()));
			if (map.get(AsientoClienteDetalle.DETALLE_NUMERO_IMPUTA_DEBE)!=null) {
				asientoClienteDetalle.setNumeroImputa(new Long(map.get(AsientoClienteDetalle.DETALLE_NUMERO_IMPUTA_DEBE).toString()));
			} else {
				asientoClienteDetalle.setNumeroImputa(null);
			}
			if (map.get(AsientoClienteDetalle.DETALLE_NUMERO_IMPUTA_HABER)!=null) {
				asientoClienteDetalle.setNumeroImputaHaber((new Long(map.get(AsientoClienteDetalle.DETALLE_NUMERO_IMPUTA_HABER).toString())));
			} else {
				asientoClienteDetalle.setNumeroImputaHaber(null);
			}
			if (map.get(AsientoClienteDetalle.DETALLE_IMPORTE)!=null) {
				asientoClienteDetalle.setImporte(new Double(map.get(AsientoClienteDetalle.DETALLE_IMPORTE).toString()).doubleValue());
			}				
			result.add(asientoClienteDetalle);
		}
		return result;
	}
	
	

}
       

   
   	
	
	
	
	



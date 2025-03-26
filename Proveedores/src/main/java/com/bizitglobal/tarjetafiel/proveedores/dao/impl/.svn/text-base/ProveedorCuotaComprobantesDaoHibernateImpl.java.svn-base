package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorCuotaComprobantesDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorCuotaComprobante;

public class ProveedorCuotaComprobantesDaoHibernateImpl  implements ProveedorCuotaComprobantesDao{

	
	
private Logger log = Logger.getLogger(ProveedorCuotaComprobantesDaoHibernateImpl.class);
	
	private DataSource dataSource;
	private JdbcTemplate jt;


	
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
	
/*	public List listarCuotaComprobante(Date fecha) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(100);
        
		Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fec = dateFormat.format(fecha);
		
		
		
		sql.append("select  ctac.c_id_cuota_comprobante,pro.c_cuit,pro.c_razon_social,pro.c_nombre_fantasia,ctac.c_importe, " +
                     "sum(case  when (tc.c_signo= -1 and cimp.c_id_cuota_comprobante_d= ctac.c_id_cuota_comprobante)   then  nvl(cimp.c_importe_cancela,0) end) as sumaImp, "+
				     "sum(case  when (tc.c_signo= -1 and com.c_en_fondos like 'N' and cimp.c_id_cuota_comprobante_d= ctac.c_id_cuota_comprobante)  then  nvl(cimp.c_importe_cancela,0) end) as sumaImpFacImputadas, "+ 
                     "sum(case  when (tc.c_signo= 1 and  cimp.c_id_cuota_comprobante_h= ctac.c_id_cuota_comprobante)   then  nvl(cimp.c_importe_cancela,0) end) as sumaImpOpFacImputadas " + 
                   "from t_vis_prov_cuotas_comprobantes ctac, t_vis_prov_comprobantes com,t_vis_prov_proveedores pro, "+
                      "t_vis_gen_tipos_comprobantes tc , t_vis_prov_ctes_imputados cimp,t_vis_gen_modalidades_pagos mp " +
                   "WHERE  com.c_id_tipo_cte= tc.c_id_tipo_cte and "+
                     "com.c_id_proveedor=pro.c_id_proveedor and "+ 
        	         "ctac.c_id_comprobante=com.c_id_comprobante and "+
        	         "pro.c_id_modalidad_pago= mp.c_id_modalidad_pago and "+
        	         "ctac.c_activo='S'  and " +
        	         //"ctac.c_fecha_vencimiento<=TO_DATE('"+fec+"','%d/%m/%Y') "+ 
        	         "ctac.c_fecha_vencimiento<=TO_DATE('1/10/2008', '%d/%m/%Y')"+ 
        	         "group by  ctac.c_id_cuota_comprobante,pro.c_cuit,pro.c_razon_social ,ctac.c_importe,pro.c_nombre_fantasia");
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
		       ProveedorCuotaComprobante cuotaComprobante = new ProveedorCuotaComprobante();
			if (map.get(ProveedorCuotaComprobante.CUIT)!=null) {
				cuotaComprobante.setCuit(map.get(ProveedorCuotaComprobante.CUIT).toString());
			} else {
				cuotaComprobante.setCuit(null);
			}
			if (map.get(ProveedorCuotaComprobante.RAZONSOCIAL)!=null) {
				cuotaComprobante.setRazonSocial(map.get(ProveedorCuotaComprobante.RAZONSOCIAL).toString());
			} else {
				cuotaComprobante.setRazonSocial(null);
			}
			if (map.get(ProveedorCuotaComprobante.SUMAIMP)!=null) 
	    	 	cuotaComprobante.setSumaImp(new Float(map.get(ProveedorCuotaComprobante.SUMAIMP).toString()));
	     	else   	cuotaComprobante.setSumaImp(null);
			if (map.get(ProveedorCuotaComprobante.SUMAIMPFACIMPUTADAS)!=null) 
	    		cuotaComprobante.setSumaImpFacImputadas(new Float(map.get(ProveedorCuotaComprobante.SUMAIMPFACIMPUTADAS).toString()));
			else  cuotaComprobante.setSumaImpFacImputadas(null);
			if (map.get(ProveedorCuotaComprobante.SUMAIMPOPFACIMPUTADAS)!=null) 
		       cuotaComprobante.setSumaImpOpFacImputadas(new Float(map.get(ProveedorCuotaComprobante.SUMAIMPOPFACIMPUTADAS).toString()));
			else  cuotaComprobante.setSumaImpOpFacImputadas(null);
			
			result.add(cuotaComprobante);

		}
		return result;
	}*/
	
	
	public List listarCuotaComprobante(String fecha) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(100);
        
		sql.append( "SELECT   ctac.c_id_cuota_comprobante as idCuotaCompr, ctac.c_fecha_vencimiento as fechaVencimiento,  " +
				    "ctac.c_importe as importe, SUM(nvl(c_importe_cancela,0)) as sumaImp, "+
				    "com.c_signo as signo ,com.c_id_proveedor as idProveedor,com.c_nro_largo as nroLargo, com.c_nro_corto as nroCorto "+
				    "FROM t_vis_prov_comprobantes com  "+
				    "inner join  t_vis_prov_cuotas_comprobantes ctac  on com.c_id_comprobante = ctac.c_id_comprobante " +
				    " LEFT OUTER JOIN t_vis_prov_ctes_imputados cteimp ON ctac.c_id_cuota_comprobante = cteimp.c_id_cuota_comprobante_d "+
				    "WHERE c_activo = 'S'   and com.c_signo = -1  and   ctac.c_fecha_vencimiento<="+fecha+ 
				    " GROUP BY ctac.c_id_cuota_comprobante, ctac.c_fecha_vencimiento, ctac.c_importe, com.c_signo, com.c_id_tipo_cte, com.c_id_proveedor,com.c_nro_largo,com.c_nro_corto HAVING c_importe <> SUM(nvl(c_importe_cancela,0)) "+
				    "order by com.c_id_proveedor " );
		           log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
		       ProveedorCuotaComprobante cuotaComprobante = new ProveedorCuotaComprobante();
			if (map.get(ProveedorCuotaComprobante.IDPROVEEDOR)!=null) {
				cuotaComprobante.setIdProveedor(new Long(map.get((ProveedorCuotaComprobante.IDPROVEEDOR)).toString()));
			} else {
				cuotaComprobante.setIdProveedor(null);
			}
			if (map.get(ProveedorCuotaComprobante.IMPORTE)!=null) 
	    		cuotaComprobante.setImporte(new Float(map.get(ProveedorCuotaComprobante.IMPORTE).toString()));
			else  cuotaComprobante.setImporte(null);
			if (map.get(ProveedorCuotaComprobante.NROCORTO)!=null) 
	    		cuotaComprobante.setNroCorto(new Integer(map.get(ProveedorCuotaComprobante.NROCORTO).toString()));
			else  cuotaComprobante.setImporte(null);
			if (map.get(ProveedorCuotaComprobante.NROLARGO)!=null)
				cuotaComprobante.setNroLargo(new Integer(map.get(ProveedorCuotaComprobante.NROLARGO).toString()));
			else  cuotaComprobante.setImporte(null);
			if (map.get(ProveedorCuotaComprobante.FECHAVENCIMIENTO)!=null)
				cuotaComprobante.setFechaVencimiento( (Date)map.get(ProveedorCuotaComprobante.FECHAVENCIMIENTO));
			else  cuotaComprobante.setFechaVencimiento(null);
			
			if (map.get(ProveedorCuotaComprobante.SUMAIMP)!=null) 
	    	 	cuotaComprobante.setSumaImp(new Float(map.get(ProveedorCuotaComprobante.SUMAIMP).toString()));
	     	else   	cuotaComprobante.setSumaImp(null);
			if (map.get(ProveedorCuotaComprobante.SUMAIMPOPFACIMPUTADAS)!=null) 
		       cuotaComprobante.setSumaImpOpFacImputadas(new Float(map.get(ProveedorCuotaComprobante.SUMAIMPOPFACIMPUTADAS).toString()));
			else  cuotaComprobante.setSumaImpOpFacImputadas(null);
			result.add(cuotaComprobante);

		}
		return result;
	}
	
	
	
	public List listarCuotaOrdenPago(String fecha) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(100);
        
		sql.append( "SELECT   ctac.c_id_cuota_comprobante as idCuotaCompr, ctac.c_fecha_vencimiento as fechaVencimiento,  " +
				    "ctac.c_importe as importe, SUM(nvl(c_importe_cancela,0)) as sumaImpOpFacImputadas, "+
				    "com.c_signo as signo ,com.c_id_proveedor as idProveedor,com.c_nro_largo as nroLargo, com.c_nro_corto as nroCorto "+
				    "FROM t_vis_prov_comprobantes com  "+
				    "inner join  t_vis_prov_cuotas_comprobantes ctac  on com.c_id_comprobante = ctac.c_id_comprobante " +
				    " LEFT OUTER JOIN t_vis_prov_ctes_imputados cteimp ON ctac.c_id_cuota_comprobante = cteimp.c_id_cuota_comprobante_h "+
				    "WHERE c_activo = 'S'   and com.c_signo =1 and  c_cte_revertido is null and   ctac.c_fecha_vencimiento<="+fecha+
				    " GROUP BY ctac.c_id_cuota_comprobante, ctac.c_fecha_vencimiento, ctac.c_importe, com.c_signo, com.c_id_tipo_cte, com.c_id_proveedor,com.c_nro_largo,com.c_nro_corto HAVING c_importe <> SUM(nvl(c_importe_cancela,0)) "+
				    "order by com.c_id_proveedor " );
		           log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		 List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
		       ProveedorCuotaComprobante cuotaComprobante = new ProveedorCuotaComprobante();
			if (map.get(ProveedorCuotaComprobante.IDPROVEEDOR)!=null) {
				cuotaComprobante.setIdProveedor(new Long(map.get((ProveedorCuotaComprobante.IDPROVEEDOR)).toString()));
			} else {
				cuotaComprobante.setIdProveedor(null);
			}
			if (map.get(ProveedorCuotaComprobante.IMPORTE)!=null) 
	    		cuotaComprobante.setImporte(new Float(map.get(ProveedorCuotaComprobante.IMPORTE).toString()));
			else  cuotaComprobante.setImporte(null);
			if (map.get(ProveedorCuotaComprobante.NROCORTO)!=null) 
	    		cuotaComprobante.setNroCorto(new Integer(map.get(ProveedorCuotaComprobante.NROCORTO).toString()));
			else  cuotaComprobante.setImporte(null);
			if (map.get(ProveedorCuotaComprobante.NROLARGO)!=null)
				cuotaComprobante.setNroLargo(new Integer(map.get(ProveedorCuotaComprobante.NROLARGO).toString()));
			else  cuotaComprobante.setImporte(null);
			if (map.get(ProveedorCuotaComprobante.FECHAVENCIMIENTO)!=null)
				cuotaComprobante.setFechaVencimiento( (Date)map.get(ProveedorCuotaComprobante.FECHAVENCIMIENTO));
			else  cuotaComprobante.setFechaVencimiento(null);
			if (map.get(ProveedorCuotaComprobante.SUMAIMP)!=null) 
	    	 	cuotaComprobante.setSumaImp(new Float(map.get(ProveedorCuotaComprobante.SUMAIMP).toString()));
	     	else   	cuotaComprobante.setSumaImp(null);
			if (map.get(ProveedorCuotaComprobante.SUMAIMPOPFACIMPUTADAS)!=null) 
		       cuotaComprobante.setSumaImpOpFacImputadas(new Float(map.get(ProveedorCuotaComprobante.SUMAIMPOPFACIMPUTADAS).toString()));
			else  cuotaComprobante.setSumaImpOpFacImputadas(null);
			result.add(cuotaComprobante);

		}
		return result;
	}

	
}

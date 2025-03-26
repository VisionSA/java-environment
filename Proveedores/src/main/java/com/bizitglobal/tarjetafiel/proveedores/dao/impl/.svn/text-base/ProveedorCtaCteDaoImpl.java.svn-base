package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorCtaCteDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorCtaCte;


public class ProveedorCtaCteDaoImpl implements ProveedorCtaCteDao {
	private Logger log = Logger.getLogger(ProveedorCtaCteDaoImpl.class);
	
	private DataSource dataSource;
	private JdbcTemplate jt;	

	public List obtenerCtaCte(Long idProveedor, Timestamp fechaDesde, Timestamp fechaHasta) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(100);
		
		sql.append("SELECT c."+ProveedorCtaCte.FECHA_EMISION+", ");
		sql.append(		  "c."+ProveedorCtaCte.ID_COMPROBANTE+", ");
		sql.append(		  "'Codigo: ' || p.c_id_proveedor ||' Razon Social: ' || p.c_razon_social || ' CUIT: ' || p.c_cuit as "+ProveedorCtaCte.DATOS_PROVEEDOR+", ");
		sql.append(		  "TO_CHAR(c.c_fecha_emision, 'DD/MM/YYYY') as "+ProveedorCtaCte.FECHA+", ");
		sql.append(		  "LPAD(c.c_nro_corto, 4, '0') || '-' || LPAD(c.c_nro_largo, 8, '0') as "+ProveedorCtaCte.NUMERO_CTE+", ");
		sql.append(		  "tc.c_descripcion_larga as "+ProveedorCtaCte.TIPO_CTE+", ");
		sql.append(		  "DECODE(tc.c_signo, -1, c.c_importe_total, 0) as "+ProveedorCtaCte.HABER+", ");
		sql.append(		  "DECODE(tc.c_signo, 1, c.c_importe_total, 0) as "+ProveedorCtaCte.DEBE+", ");
		sql.append(		  "sp_get_prov_saldo(c.c_id_comprobante, p.c_id_proveedor,	"+ Filtro.getTO_DATE(fechaDesde)+", "+Filtro.getTO_DATE(fechaHasta)+") as "+ProveedorCtaCte.SALDO+", ");
		sql.append(		  "sp_get_prov_saldo_ant(p.c_id_proveedor, "+Filtro.getTO_DATE(fechaDesde)+") as "+ProveedorCtaCte.SALDO_ANTERIOR+", ");
		sql.append(		  "p."+ProveedorCtaCte.ID_PROVEEDOR+" ");
		sql.append("FROM   t_vis_prov_proveedores p, ");
		sql.append(		  "t_vis_gen_tipos_comprobantes tc, ");
		sql.append(		  "t_vis_prov_comprobantes c ");
		sql.append("WHERE  p.c_id_proveedor = c.c_id_proveedor ");
		sql.append("AND    c.c_id_tipo_cte = tc.c_id_tipo_cte ");
		sql.append("AND    p.c_id_proveedor between "+idProveedor+" and  "+idProveedor+" ");
		sql.append("AND    c.c_fecha_emision between "+Filtro.getTO_DATE(fechaDesde)+" and "+Filtro.getTO_DATE(fechaHasta)+" ");
//		sql.append("ORDER BY p.c_id_proveedor, c.c_fecha_emision, c.c_id_comprobante");
		sql.append("ORDER BY c.c_fecha_emision ASC, c.c_id_comprobante ASC");

		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();        

		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			ProveedorCtaCte proveedorCtaCte = new ProveedorCtaCte();
//			log.info("El id de comprobante es: "+map.get(ProveedorCtaCte.ID_COMPROBANTE));
			String cad = eliminarPuntosEnCadena(map.get(ProveedorCtaCte.ID_COMPROBANTE).toString());
			proveedorCtaCte.setIdComprobante(new Long(cad));
//			log.info("El id de proveedor es: "+map.get(ProveedorCtaCte.ID_PROVEEDOR));
			proveedorCtaCte.setIdProveedor(new Long(map.get(ProveedorCtaCte.ID_PROVEEDOR).toString()));
//			log.info("El id de datos proveedor es: "+ProveedorCtaCte.DATOS_PROVEEDOR);
			
			proveedorCtaCte.setDatosProveedor(map.get(ProveedorCtaCte.DATOS_PROVEEDOR).toString());
//			log.info("El id de fecha emision es: "+ProveedorCtaCte.FECHA_EMISION);
			
			proveedorCtaCte.setFechaEmision((Timestamp) map.get(ProveedorCtaCte.FECHA_EMISION));
//			log.info("El id de fecha es: "+ProveedorCtaCte.FECHA);
			
			proveedorCtaCte.setFecha(map.get(ProveedorCtaCte.FECHA).toString());
//			log.info("El id de numero de cuenta corriente es: "+ProveedorCtaCte.NUMERO_CTE);
			
			proveedorCtaCte.setNumeroCte(map.get(ProveedorCtaCte.NUMERO_CTE).toString());
//			log.info("El id de tipo constante es: "+ProveedorCtaCte.TIPO_CTE);
			
			proveedorCtaCte.setTipoCte(map.get(ProveedorCtaCte.TIPO_CTE).toString());
//			log.info("El id de haber es: "+ProveedorCtaCte.HABER);
			
			proveedorCtaCte.setHaber((BigDecimal)(map.get(ProveedorCtaCte.HABER)));
//			log.info("El id de debe es: "+ProveedorCtaCte.DEBE);

			proveedorCtaCte.setDebe((BigDecimal)(map.get(ProveedorCtaCte.DEBE)));

//			log.info("El id de salde es: "+ProveedorCtaCte.SALDO);
			proveedorCtaCte.setSaldo((BigDecimal)(map.get(ProveedorCtaCte.SALDO)));
//			log.info("El id de salde anterior es: "+ProveedorCtaCte.SALDO_ANTERIOR);
			
			proveedorCtaCte.setSaldoAnterior((BigDecimal)(map.get(ProveedorCtaCte.SALDO_ANTERIOR)));
			
			
			
			result.add(proveedorCtaCte);			
		}
		
		return result;
	}
	
	/*
	 * Este metodo retorna la cadena que se le otorga por parametros
	 * pero sin contener puntos.
	 */
	public String eliminarPuntosEnCadena(String cadena) {
		String cadenaLimpia = "";
		for (int i=0; i<cadena.length();i++) {
			char letra = cadena.charAt(i);
			if (letra!='.') cadenaLimpia = cadenaLimpia + letra;  
		}	
		return cadenaLimpia;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}

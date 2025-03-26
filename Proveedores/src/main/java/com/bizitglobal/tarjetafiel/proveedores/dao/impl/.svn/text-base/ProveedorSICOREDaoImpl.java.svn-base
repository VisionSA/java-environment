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
import com.bizitglobal.tarjetafiel.proveedores.dao.ProveedorSICOREDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorCtaCte;


public class ProveedorSICOREDaoImpl implements ProveedorSICOREDao {
	private Logger log = Logger.getLogger(ProveedorSICOREDaoImpl.class);
	
	private DataSource dataSource;
	private JdbcTemplate jt;	

	public List obtenerSICORE(Timestamp fechaDesde, Timestamp fechaHasta) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(100);
		
		sql.append("SELECT distinct '06' as codcomp,");
		sql.append("TO_CHAR(cc.c_fecha_emision,'DD/MM/YYYY') as fechacomp,"); 
		sql.append("LPAD(cc.c_nro_largo, 12, '0') || '    ' as numero,");
		sql.append("LPAD(REPLACE(cc.c_importe_total, '.', ','), 16, ' ') as impcomp,");
		sql.append("LPAD(ir.c_codigo_norma, 3, '0') as codimpu,");
		sql.append("LPAD(ir.c_codigo_regimen, 3, '0') as codreg,");
		sql.append("'1' as codoper,");
		sql.append("LPAD(REPLACE(cc.c_importe_neto, '.', ','), 14, ' ') as basecal,");
		sql.append("TO_CHAR(cc.c_fecha_emision,'DD/MM/YYYY') as fecharet,");
		sql.append("'01' as codcond,");
		sql.append("' ' as codretss,");		
		sql.append("LPAD(REPLACE(r.c_monto, '.', ','), 14, ' ') as impret,");		
		sql.append("LPAD(REPLACE(nvl(e.c_porc_aplicado, 0.00), '.', ','), 6, ' ') as porexc,");
		sql.append("nvl(TO_CHAR(ie.c_fecha_emision,'DD/MM/YYYY'),'         ') as fechaemiexc,"); 
		sql.append("'80' as coddoc,");		
		sql.append("pro.c_cuit || '         ' as cuit,");		
		sql.append("RPAD(nvl(ie.c_nro_certificado, ' 0'), 15, ' ') as nrocert ");

		sql.append(" FROM 	t_vis_prov_proveedores          pro, ");
		sql.append("        t_vis_prov_comprobantes         cc,");
		sql.append("        t_vis_gen_tipos_comprobantes    tc,");
		sql.append("        t_vis_prov_retenciones          r");
		sql.append("	LEFT OUTER JOIN t_vis_prov_exclusiones e ON (r.c_id_prov_retencion = e.c_id_prov_retencion)");
		sql.append("    LEFT OUTER JOIN t_vis_imp_ret_exclusion ie ON (ie.c_id_ret_exclusion = e.c_id_ret_exclusion),");
		sql.append("        t_vis_imp_retenciones           ir,");
		sql.append("	t_vis_prov_grupos		g ");

		sql.append("WHERE 	pro.c_id_proveedor 	= cc.c_id_proveedor ");
		sql.append(" AND   	cc.c_id_comprobante     = r.c_id_comprobante");
		sql.append(" AND     r.c_cod_retencion       = ir.c_cod_retencion");
		sql.append(" AND   	cc.c_id_tipo_cte        = tc.c_id_tipo_cte");
		sql.append(" AND     r.c_monto > 0");
		sql.append(" AND     cc.c_fecha_emision BETWEEN "+Filtro.getTO_DATE(fechaDesde)+" AND "+Filtro.getTO_DATE(fechaHasta));
		sql.append(" AND     cc.c_cte_revertido IS NULL ");
		sql.append(" ORDER BY 3,2,4");

		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();        

		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			StringBuffer lineaSICORE = new StringBuffer(144);
			lineaSICORE.append(map.get("codcomp"));
			lineaSICORE.append(map.get("fechacomp"));
			lineaSICORE.append(map.get("numero"));
			lineaSICORE.append(map.get("impcomp"));
			lineaSICORE.append(map.get("codimpu"));
			lineaSICORE.append(map.get("codreg"));
			lineaSICORE.append(map.get("codoper"));
			lineaSICORE.append(map.get("basecal"));
			lineaSICORE.append(map.get("fecharet"));
			lineaSICORE.append(map.get("codcond"));
			lineaSICORE.append(map.get("codretss"));
			lineaSICORE.append(map.get("impret"));
			lineaSICORE.append(map.get("porexc"));
			lineaSICORE.append(map.get("fechaemiexc"));
			lineaSICORE.append(map.get("coddoc"));
			lineaSICORE.append(map.get("cuit"));
			lineaSICORE.append(map.get("nrocert"));

			result.add(lineaSICORE);			
		}
		
		return result;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}

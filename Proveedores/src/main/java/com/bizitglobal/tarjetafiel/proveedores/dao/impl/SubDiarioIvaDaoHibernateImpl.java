package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.ExecuteSP;
import com.bizitglobal.tarjetafiel.proveedores.dao.SubDiarioIvaDao;

 

public class SubDiarioIvaDaoHibernateImpl implements SubDiarioIvaDao {
	private Logger log = Logger.getLogger(SubDiarioIvaDaoHibernateImpl.class);
	
	private DataSource dataSource;
//	private JdbcTemplate jt;
	
	private ExecuteSP sp;
	private static final String SPROC_NAME = "sp_get_prov_list_iva_compra";
	private static final String OPERADOR_NUMBER_PARAM = "v_id_operador";
	private static final String FEC_DESDE_EM_DATE_PARAM = "v_fec_desde_em";
	private static final String FEC_HASTA_EM_DATE_PARAM = "v_fec_hasta_em";
	private static final String FEC_DESDE_CO_DATE_PARAM = "v_fec_desde_co";
	private static final String FEC_HASTA_CO_DATE_PARAM = "v_fec_hasta_co";
	
	public void proveedoresSubDiarioIva(Long idOperador, Timestamp desde_em, Timestamp hasta_em,Timestamp desde_co, Timestamp hasta_co) {		
			sp = new ExecuteSP(dataSource);	
			sp.setSql(SPROC_NAME);
			sp.declareParameter(new SqlParameter(OPERADOR_NUMBER_PARAM,Types.NUMERIC));
			sp.declareParameter(new SqlParameter(FEC_DESDE_EM_DATE_PARAM,Types.DATE));
			sp.declareParameter(new SqlParameter(FEC_HASTA_EM_DATE_PARAM,Types.DATE));
			sp.declareParameter(new SqlParameter(FEC_DESDE_CO_DATE_PARAM,Types.DATE));
			sp.declareParameter(new SqlParameter(FEC_HASTA_CO_DATE_PARAM,Types.DATE));
			sp.compile();
			Map inputs = new HashMap();
	        inputs.put(OPERADOR_NUMBER_PARAM, idOperador);
	        inputs.put(FEC_DESDE_EM_DATE_PARAM, desde_em);
	        inputs.put(FEC_HASTA_EM_DATE_PARAM, hasta_em);
	        inputs.put(FEC_DESDE_CO_DATE_PARAM, desde_co);
	        inputs.put(FEC_HASTA_CO_DATE_PARAM, hasta_co);
			log.info("SP Ejecutado ==> " + sp.toString());
	        sp.execute(inputs); 
	        
//		StringBuffer sql = new StringBuffer(100);		
//		sql.append("EXECUTE FUNCTION informix.sp_get_prov_list_iva_compra("
//				   + idOperador + ", " + Filtro.getTO_DATE(desde_em) + ", " + Filtro.getTO_DATE(hasta_em)+ ", " + Filtro.getTO_DATE(desde_co) + ", " + Filtro.getTO_DATE(hasta_co)+")");
//		
//		log.info("SQL Ejecutado ==> " + sql.toString());
//		jt = new JdbcTemplate(dataSource);
//		List rows = jt.queryForList(sql.toString());
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
}


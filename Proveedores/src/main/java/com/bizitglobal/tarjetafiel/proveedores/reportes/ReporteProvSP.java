package com.bizitglobal.tarjetafiel.proveedores.reportes;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.bizitglobal.tarjetafiel.commons.util.ExecuteSP;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReporteProvSP {
	private static final Logger log = Logger.getLogger(ReporteProvSP.class);
	private DataSource dataSource;
	private ExecuteSP sp;
	private static final String SPROC_NAME = "sp_get_prov_comp_saldo";
	private static final String OPERADOR_NUMBER_PARAM = "v_operador";
	private static final String PROV_DESDE_NUMBER_PARAM = "v_proveedor_desde";
	private static final String PROV_HASTA_NUMBER_PARAM = "v_proveedor_hasta";
	private static final String FEC_DESDE_DATE_PARAM = "v_fec_desde";
	private static final String FEC_HASTA_DATE_PARAM = "v_fec_hasta";
	
	public boolean InicializarReporteCompSaldo(long pIdOperador, long pIdProveedor, Date pFechaDesde, Date pFechaHasta ) {
		try {
			sp = new ExecuteSP(dataSource);	
			sp.setSql(SPROC_NAME);
			sp.declareParameter(new SqlParameter(OPERADOR_NUMBER_PARAM,Types.NUMERIC));
			sp.declareParameter(new SqlParameter(PROV_DESDE_NUMBER_PARAM,Types.NUMERIC));
			sp.declareParameter(new SqlParameter(PROV_HASTA_NUMBER_PARAM,Types.NUMERIC));
			sp.declareParameter(new SqlParameter(FEC_DESDE_DATE_PARAM, Types.DATE));
			sp.declareParameter(new SqlParameter(FEC_HASTA_DATE_PARAM,Types.DATE));
			sp.compile();
			Map inputs = new HashMap();
	        inputs.put(OPERADOR_NUMBER_PARAM, new Long(pIdOperador));
	        inputs.put(PROV_DESDE_NUMBER_PARAM, new Long(pIdProveedor));
	        inputs.put(PROV_HASTA_NUMBER_PARAM, new Long(pIdProveedor));
	        inputs.put(FEC_DESDE_DATE_PARAM, pFechaDesde);
	        inputs.put(FEC_HASTA_DATE_PARAM, pFechaHasta);
	        sp.execute(inputs); 
//			SPCompSaldo spCompSaldo  = new SPCompSaldo(dataSource);
//			spCompSaldo.execute(
//					new Long(pIdOperador),
//					new Long(pIdProveedor),
//					new Long(pIdProveedor),
//					pFechaDesde,
//					pFechaHasta);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean InicializarReporteAuditoriaComp(long pIdOperador, long pIdProveedorDesde, long pIdProveedorHasta, Date pFechaDesde, Date pFechaHasta) {
//		JdbcTemplate jt = new JdbcTemplate(dataSource);
		
//		Format dateFormat = new SimpleDateFormat("''dd/MM/yyyy''");
//		String fechaDesde = dateFormat.format(pFechaDesde); 
//		String fechaHasta = dateFormat.format(pFechaHasta);
		
//		String fd = Filtro.getTO_DATE(pFechaDesde);//"TO_DATE("+fechaDesde+",'%d/%m/%Y')";
//		String fh = Filtro.getTO_DATE(pFechaHasta);//"TO_DATE("+fechaHasta+",'%d/%m/%Y')";
//
//		String sql = "execute sp_get_prov_comp_saldo("+pIdOperador+","+pIdProveedorDesde+","+pIdProveedorDesde+","+fd+","+fh+")";
//
//		try {
//			jt.execute(sql);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		try {
			SPCompSaldo spCompSaldo  = new SPCompSaldo(dataSource);
			spCompSaldo.execute(
					new Long(pIdOperador),
					new Long(pIdProveedorDesde),
					new Long(pIdProveedorHasta),
					pFechaDesde,
					pFechaHasta);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean generarRankingProveedores(Long pIdOperador, Date pFechaHasta ) {
		try {
			sp = new ExecuteSP(dataSource);	
			sp.setSql("sp_get_prov_list_ranking");
			sp.declareParameter(new SqlParameter(OPERADOR_NUMBER_PARAM,Types.NUMERIC));
			sp.declareParameter(new SqlParameter(FEC_HASTA_DATE_PARAM,Types.DATE));
			sp.compile();
			Map inputs = new HashMap();
	        inputs.put(OPERADOR_NUMBER_PARAM, pIdOperador);
	        inputs.put(FEC_HASTA_DATE_PARAM, pFechaHasta);
	        sp.execute(inputs); 
//			SPCompSaldo spCompSaldo  = new SPCompSaldo(dataSource);
//			spCompSaldo.execute(
//					new Long(pIdOperador),
//					new Long(pIdProveedor),
//					new Long(pIdProveedor),
//					pFechaDesde,
//					pFechaHasta);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}

	}
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private class SPCompSaldo extends StoredProcedure {
//
//	    private static final String SPROC_NAME = "TitlesAfterDate";
//	    private static final String CUTOFF_DATE_PARAM = "cutoffDate";
		private static final String SPROC_NAME = "sp_get_prov_comp_saldo";
		private static final String OPERADOR_NUMBER_PARAM = "v_operador";
		private static final String PROV_DESDE_NUMBER_PARAM = "v_proveedor_desde";
		private static final String PROV_HASTA_NUMBER_PARAM = "v_proveedor_hasta";
		private static final String FEC_DESDE_DATE_PARAM = "v_fec_desde";
		private static final String FEC_HASTA_DATE_PARAM = "v_fec_hasta";
//		PROCEDURE SP_GET_PROV_COMP_SALDO
//		( v_operador IN NUMBER
//		, v_proveedor_desde IN NUMBER
//		, v_proveedor_hasta IN NUMBER
//		, v_fec_desde IN DATE
//		, v_fec_hasta IN DATE
//		) AS
		
	    public SPCompSaldo(DataSource dataSource) {
	        super(dataSource, SPROC_NAME);
            declareParameter(new SqlParameter(OPERADOR_NUMBER_PARAM,Types.NUMERIC));
            declareParameter(new SqlParameter(PROV_DESDE_NUMBER_PARAM,Types.NUMERIC));
            declareParameter(new SqlParameter(PROV_HASTA_NUMBER_PARAM,Types.NUMERIC));
            declareParameter(new SqlParameter(FEC_DESDE_DATE_PARAM, Types.DATE));
            declareParameter(new SqlParameter(FEC_HASTA_DATE_PARAM,Types.DATE));
	        compile();
	    }

	    public Map execute(Long pIdOperador, Long pIdProveedorDesde, Long pIdProveedorHasta, Date pFechaDesde, Date pFechaHasta ) {
	        Map inputs = new HashMap();
	        inputs.put(OPERADOR_NUMBER_PARAM, pIdOperador);
	        inputs.put(PROV_DESDE_NUMBER_PARAM, pIdProveedorDesde);
	        inputs.put(PROV_HASTA_NUMBER_PARAM, pIdProveedorHasta);
	        inputs.put(FEC_DESDE_DATE_PARAM, pFechaDesde);
	        inputs.put(FEC_HASTA_DATE_PARAM, pFechaHasta);
	        return super.execute(inputs); 
	    }
	
	}

	

}

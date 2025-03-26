package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.contabilidad.dao.BalanceDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Asiento;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Balance;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.SumasYSaldos;




public class BalanceDaoHibernateImpl  implements BalanceDao {
	private Logger log = Logger.getLogger(BalanceDaoHibernateImpl.class);
	
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
	
	public List listarBalanceHojas(Ejercicio ejercicio, Date fechaDesde, Date fechaHasta) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(100);
        
		Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fecDesde = dateFormat.format(fechaDesde);
		String fecHasta = dateFormat.format(fechaHasta); 
		String fecIniEjer=dateFormat.format(ejercicio.getFechaInicio());
	    sql.append("SELECT  ad.c_numero_imputa as nroImputa, pc.titulo as titulo, " +
                   "sum(DECODE(ad.c_signo, 'D',1, 'C',0)*ad.c_importe) -sum(DECODE(ad.c_signo, 'D',0, 'C',1)*ad.c_importe) as saldoPeriodo, "+
				   "nvl(sum( case  when TRUNC(c_fecha_contab)   >= to_date('" +fecIniEjer +"','yyyy-MM-dd') AND  TRUNC(c_fecha_contab) < to_date('" +fecDesde+"','yyyy-MM-dd') " + 
                   "then DECODE(ad.c_signo, 'D',1, 'C',0)*ad.c_importe end ) -sum( case  when TRUNC(c_fecha_contab)   >= to_date('" +fecIniEjer +"','yyyy-MM-dd') and  TRUNC(c_fecha_contab) < to_date('" +fecDesde+"','yyyy-MM-dd') " + 
                   "then DECODE(ad.c_signo, 'D',0, 'C',1)*ad.c_importe end ) ,0)    as saldoInicial " +
                   "FROM t_cont_asientos_d ad inner JOIN t_vis_cont_plan_cuenta pc ON ad.c_numero_imputa = pc.c_id_plan_cuenta " +
                   "WHERE  ad.c_empresa = "+ String.valueOf(ejercicio.getSucursalFiel().intValue())+
                   " AND ad.c_ejercicio = "+String.valueOf(ejercicio.getIdEjercicio().intValue()) +
                   " and pc.c_uso='I' "+
                   "AND TRUNC(ad.c_fecha_contab) between  to_date('"+fecIniEjer+"','yyyy-MM-dd') and to_date('"+fecHasta+"','yyyy-MM-dd') "+
                   "group by  ad.c_numero_imputa , pc.titulo"); 
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			Balance balance = new Balance();
			if (map.get(Balance.NRO_IMPUTA)!=null) {
				balance.setNroImputa(map.get(balance.NRO_IMPUTA).toString());
			} else {
				balance.setNroImputa(null);
			}
			if (map.get(Balance.TITULO)!=null) {
				balance.setTitulo(map.get(Balance.TITULO).toString());
			} else {
				balance.setTitulo(null);
			}
		
			balance.setSaldoInicial(new Double(map.get(balance.SALDO_INICIAL).toString()));
			
			balance.setSaldoPeriodo(new Double(map.get(balance.SALDO_PERIODO).toString()));
			result.add(balance);

		}
		return result;
	}
	
	public List listarBalanceHojasSumasYSaldos(Ejercicio ejercicio, Date fechaDesde, Date fechaHasta) {
		List result = new ArrayList();
		StringBuffer sql = new StringBuffer(100);
        
		Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fecDesde = dateFormat.format(fechaDesde);
		String fecHasta = dateFormat.format(fechaHasta);
		String fecIniEjer=dateFormat.format(ejercicio.getFechaInicio());
		
	
		   
		sql.append("SELECT  ad.c_numero_imputa as nroImputa, pc.titulo as titulo, " +
                   "nvl(sum(case  when trunc(c_fecha_contab) >=  to_date('" +fecDesde +"','yyyy-MM-dd') AND  trunc(c_fecha_contab) <= to_date('"+fecHasta+"','yyyy-MM-dd')  then DECODE(ad.c_signo, 'D',0, 'C',1)*nvl(ad.c_importe,0) end),0) as haberPeriodo," +
                   "nvl(sum( case  when trunc(c_fecha_contab) >= to_date('" +fecDesde +"','yyyy-MM-dd') AND  trunc(c_fecha_contab) <= to_date('"+fecHasta+"','yyyy-MM-dd') then DECODE(ad.c_signo, 'D',1, 'C',0)* nvl(ad.c_importe,0) end ),0) as debePeriodo,"+
                   " nvl(sum(case  when trunc(c_fecha_contab) >=  to_date('" +fecIniEjer +"','yyyy-MM-dd') AND  trunc(c_fecha_contab) < to_date('" +fecDesde+"','yyyy-MM-dd')  then DECODE(ad.c_signo, 'D',0, 'C',1)*nvl(ad.c_importe,0) end),0) as haberInicial, "+
                   " nvl(sum( case  when trunc(c_fecha_contab) >= to_date('" +fecIniEjer +"','yyyy-MM-dd') AND  trunc(c_fecha_contab) < to_date('" +fecDesde+"','yyyy-MM-dd') then DECODE(ad.c_signo, 'D',1, 'C',0)* nvl(ad.c_importe,0) end ),0) as debeInicial "+
       		       "FROM t_cont_asientos_d ad inner JOIN t_vis_cont_plan_cuenta pc ON ad.c_numero_imputa = pc.c_id_plan_cuenta "+
                   "WHERE  ad.c_empresa = "+ String.valueOf(ejercicio.getSucursalFiel().intValue())+
                   " AND ad.c_ejercicio = "+String.valueOf(ejercicio.getIdEjercicio().intValue()) +
                   " and pc.c_uso='I' "+
                   "AND trunc(ad.c_fecha_contab) between  to_date('"+fecIniEjer+"','yyyy-MM-dd') and (to_date('"+fecHasta+"','yyyy-MM-dd')+1)"+
                   "group by  ad.c_numero_imputa , pc.titulo"); 
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			SumasYSaldos sumasYSaldos =  new SumasYSaldos();
			if (map.get(SumasYSaldos.NRO_IMPUTA)!=null) {
				sumasYSaldos.setNroImputa(map.get(SumasYSaldos.NRO_IMPUTA).toString());
			} else {
				sumasYSaldos.setNroImputa(null);
			}
			if (map.get(SumasYSaldos.TITULO)!=null) {
				sumasYSaldos.setTitulo(map.get(SumasYSaldos.TITULO).toString());
			} else {
				sumasYSaldos.setTitulo(null);
			}
		
			sumasYSaldos.setDebeInicial(new Double(map.get(sumasYSaldos.DEBE_INICIAL).toString()));
			sumasYSaldos.setHaberInicial(new Double(map.get(sumasYSaldos.HABER_INICIAL).toString()));
			sumasYSaldos.setDebePeriodo(new Double(map.get(sumasYSaldos.DEBE_PERIODO).toString()));
			sumasYSaldos.setHaberPeriodo(new Double(map.get(sumasYSaldos.HABER_PERIODO).toString()));
			result.add(sumasYSaldos);

		}
		return result;
	}

	
	
	
}
       

   
   	
	
	
	
	



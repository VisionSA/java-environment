package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.ExtractoBancarioDao;
import com.bizitglobal.tarjetafiel.fondos.dao.MovimientoConciliableDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancario;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoConciliable;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;

public class MovimientoConciliableDaoHibernateImpl extends HibernateDaoSupport implements MovimientoConciliableDao  {

	private static final Logger log = Logger.getLogger(MovimientoConciliableDaoHibernateImpl.class);
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	
	public MovimientoConciliableDaoHibernateImpl() {
		super();
	}
/** @author Hernan Guillen
 * En toda esta clase remplace las relaciones en los joins para obtener el BancoPropio y el Banco.
 * Donde:
 * join t_vis_fon_bancos_propios bp on (ch.c_id_banco_propio=bp.c_id_banco_propio) 
 * lo reemplace con
 * JOIN t_vis_fon_bancos_propios bp ON (ai.c_id_plan_cuenta = bp.c_id_plan_cuenta)
 * y join t_vis_gen_bancos bco on (ch.c_id_banco=bco.c_id_banco)
 * lo reemplace con
 * JOIN t_vis_gen_bancos bco ON (bp.c_id_banco=bco.c_id_banco)
 */
	@Override
	public List listarNoConciliados(Long idPlanCuenta, Date fechaDesde, Date fechaHasta) {
		StringBuffer sql = new StringBuffer(100);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		
		sql.append("  select ch.c_numero, ch.c_importe,  ch.c_cuenta, ch.c_tipo, ch.c_beneficiario, ch.c_id_banco ");
		sql.append("  , a.c_fecha, ai.c_signo,ai.c_leyenda, bp.c_id_plan_cuenta, bco.c_codigo, bco.c_descripcion descripcionBanco, chh.C_ID_CHEQUE_HISTORIAL, a.C_ID_ASIENTO ");
		sql.append("  from t_vis_fon_cheques_historial chh ");
		sql.append("  join t_vis_fon_asientos_item ai on(chh.c_id_asiento_item=ai.c_id_asiento_item) ");
		sql.append("  join t_vis_fon_cheques ch on (chh.c_id_cheque=ch.c_id_cheque) ");
		sql.append("  JOIN t_vis_fon_bancos_propios bp ON (ai.c_id_plan_cuenta = bp.c_id_plan_cuenta) ");
		sql.append("  JOIN t_vis_gen_bancos bco ON (bp.c_id_banco=bco.c_id_banco) ");
		sql.append("  join t_vis_fon_asientos a on (ai.c_id_asiento=a.c_id_asiento) ");
		sql.append("  where ai.c_id_plan_cuenta = " +  idPlanCuenta + " " );
		sql.append("  and trunc(a.c_fecha) between ");   
		sql.append("  TO_DATE('" + simpleDateFormat.format(fechaDesde) + "','DD/MM/YYYY') ");
		sql.append("  and TO_DATE('" + simpleDateFormat.format(fechaHasta) + "','DD/MM/YYYY') ");
		sql.append("  and chh.c_conciliado like 'N' ");
		sql.append("  order by a.c_fecha ");
																		
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		List result = new ArrayList();
		while(iter.hasNext()){
			Map map = (Map)iter.next();
			result.add(armarMovimientoConciliable(map));
		}
		return result;
	}

	@Override
	public MovimientoConciliable buscarMovimientoConciliable(Long idChequeHistorial) {
		StringBuffer sql = new StringBuffer(100);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		
		sql.append("  select ch.c_numero, ch.c_importe,  ch.c_cuenta, ch.c_tipo, ch.c_beneficiario, ch.c_id_banco ");
		sql.append("  , a.c_fecha, ai.c_signo,ai.c_leyenda, bp.c_id_plan_cuenta, bco.c_codigo, bco.c_descripcion descripcionBanco, chh.C_ID_CHEQUE_HISTORIAL, a.C_ID_ASIENTO ");
		sql.append("  from t_vis_fon_cheques_historial chh ");
		sql.append("  join t_vis_fon_asientos_item ai on(chh.c_id_asiento_item=ai.c_id_asiento_item) ");
		sql.append("  join t_vis_fon_cheques ch on (chh.c_id_cheque=ch.c_id_cheque) ");
		sql.append("  JOIN t_vis_fon_bancos_propios bp ON (ai.c_id_plan_cuenta = bp.c_id_plan_cuenta) ");
		sql.append("  JOIN t_vis_gen_bancos bco ON (bp.c_id_banco=bco.c_id_banco) ");
		sql.append("  join t_vis_fon_asientos a on (ai.c_id_asiento=a.c_id_asiento) ");
		sql.append("  where chh.c_id_cheque_historial = " +  idChequeHistorial + " " );
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		return armarMovimientoConciliable((Map)rows.iterator().next());
	}
	
	private MovimientoConciliable armarMovimientoConciliable(Map map) {
		MovimientoConciliable mov = new MovimientoConciliable();
		mov.setDescripcion(map.get("c_leyenda").toString());
		mov.setBeneficiario(map.get("c_beneficiario").toString());
		mov.setFecha((Date)map.get("c_fecha"));
		mov.setImporte(new Double(map.get("c_importe").toString()));
		mov.setNumero(map.get("c_numero").toString());
		mov.setNumeroCuenta(map.get("c_cuenta").toString());
		if(map.get("c_signo").toString().equals("1"))
			mov.setSigno(new Character('C'));
		else
			mov.setSigno(new Character('D'));
		mov.setTipo(new Character(map.get("c_tipo").toString().charAt(0)));
		mov.setBanco(new Banco(new Long(map.get("c_id_banco").toString()),
				map.get("descripcionBanco").toString(),map.get("c_codigo").toString()));
		mov.setIdChequeHistorial(new Long(map.get("C_ID_CHEQUE_HISTORIAL").toString()));
		mov.setIdAsiento(new Long(map.get("C_ID_ASIENTO").toString()));
		
		return mov;
	}
	
	public double saldoMovContabilidadNoConciliadosHastaFecha(Long idPlanCuenta, Date fechaHasta, Character conciliado)
	{
		StringBuffer sql = new StringBuffer(100);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		
		sql.append("  select NVL(sum(ch.c_importe *  ai.c_signo),0)saldo ");
		sql.append("  from t_vis_fon_cheques_historial chh ");
		sql.append("  join t_vis_fon_asientos_item ai on(chh.c_id_asiento_item=ai.c_id_asiento_item) ");
		sql.append("  join t_vis_fon_cheques ch on (chh.c_id_cheque=ch.c_id_cheque) ");
		sql.append("  JOIN t_vis_fon_bancos_propios bp ON (ai.c_id_plan_cuenta = bp.c_id_plan_cuenta) ");
		sql.append("  JOIN t_vis_gen_bancos bco ON (bp.c_id_banco=bco.c_id_banco) ");
		sql.append("  join t_vis_fon_asientos a on (ai.c_id_asiento=a.c_id_asiento) ");
		sql.append("  where ai.c_id_plan_cuenta = " +  idPlanCuenta + " " );
		sql.append("  and trunc(a.c_fecha) <= TO_DATE('" + simpleDateFormat.format(fechaHasta) + "','DD/MM/YYYY') ");
		sql.append("  and chh.c_conciliado like '" + conciliado +"' ");
																		
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		Map map = jt.queryForMap(sql.toString());
		BigDecimal result=new BigDecimal(0);
		if(map!=null)
		{
			result = (BigDecimal)map.get("saldo");
		}
		
		return result.doubleValue();
	}
	
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


}


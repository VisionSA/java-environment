package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.ConciliacionFondoCabeceraDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ConciliacionFondoDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ExtractoBancarioDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondoCabecera;
import com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancario;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public class ConciliacionFondoCabeceraDaoHibernateImpl extends HibernateDaoSupport implements ConciliacionFondoCabeceraDao  {
	
	private static final Logger log = Logger.getLogger(ConciliacionFondoCabeceraDaoHibernateImpl.class);
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	public ConciliacionFondoCabeceraDaoHibernateImpl() {
		super();
	}
	
	@Override
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ConciliacionFondoCabecera obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	
/*@I3918*/	public List<ConciliacionFondoCabecera> buscarConciliacionCabeceraPorFecha(int tipoFecha, Date fechaDesde, Date fechaHasta, Long idBancoPropio, String conciliado, int firstResult, int maxResults, String numero, Double importe){
		
		StringBuffer sql = armarQueryPorTipoFecha(tipoFecha, fechaDesde, fechaHasta, idBancoPropio,conciliado,firstResult, maxResults, numero, importe);
/*@F3918*/		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		List result = new ArrayList();
		while(iter.hasNext()){
			Map map = (Map) iter.next();
			
			ConciliacionFondoCabecera cabecera = new ConciliacionFondoCabecera();
			cabecera.setIdCabeceraConciliacion(new Long(map.get("C_ID_CABECERA_CONCILIACION").toString()));
			cabecera.setComentario(map.get("C_COMENTARIO")!=null ? map.get("C_COMENTARIO").toString(): null);
			cabecera.setFechaGeneracion((Date)map.get("C_FECHA_GENERACION"));
			cabecera.setFechaConfirmacion(map.get("C_FECHA_CONFIRMACION")!=null ? (Date)map.get("C_FECHA_CONFIRMACION"): null);
			cabecera.setOperadorConfirmo(map.get("C_ID_OPERADOR")!=null ? new Operador(new Long(map.get("C_ID_OPERADOR").toString())) : new Operador());
			cabecera.setConciliado(map.get("C_CONCILIADO").toString().charAt(0));
			cabecera.setFechaReversion(map.get("C_FECHA_REVERSION")!=null ? (Date)map.get("C_FECHA_REVERSION"):null);
			cabecera.setOperadorReversion(map.get("C_ID_OPERADOR_REVERSION") !=null ? new Operador(new Long(map.get("C_ID_OPERADOR_REVERSION").toString())):null);
			cabecera.setBancoPropio(new BancoPropio(new Long(map.get("C_ID_BANCO_PROPIO").toString())));
			
			result.add(cabecera);
		}

		return result;
	}
	
/*@I3918*/	public Long cantidadRegistrosPorFecha(int tipoFecha, Date fechaDesde, Date fechaHasta, Long idBancoPropio, String conciliado, String numero, Double importe){
		
		StringBuffer sql = armarQueryContarRegistrosPorTipoFecha(tipoFecha, fechaDesde, fechaHasta, idBancoPropio,conciliado, numero, importe);
/*@F3918*/		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		Long cantidad = 0L;
		while(iter.hasNext()){
			Map map = (Map) iter.next();
			cantidad+= new Long(map.get("registros").toString());
		}
		return cantidad;
	}

/*@I3918*/	private StringBuffer armarQueryPorTipoFecha(int tipoFecha, Date fechaDesde, Date fechaHasta, Long idBancoPropio, String conciliado, int firstResult, int maxResults, String numero, Double importe)
/*@F3918*/	{
		StringBuffer sql = new StringBuffer(500);
		StringBuffer sqlPaginado = new StringBuffer(500);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		if(ConciliacionFondoCabecera.FECHA_CONCILIACION == tipoFecha)
		{

			sql.append(" SELECT CAB.C_ID_CABECERA_CONCILIACION,CAB.C_COMENTARIO,CAB.C_FECHA_GENERACION ");
			sql.append(" ,CAB.C_FECHA_CONFIRMACION,CAB.C_ID_OPERADOR,CAB.C_CONCILIADO ");
			sql.append(" ,CAB.C_FECHA_REVERSION,CAB.C_ID_OPERADOR_REVERSION,CAB.C_ID_BANCO_PROPIO ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_DETALLE_EXTRACTO DET ON DET.C_ID_DETALLE_EXTRACTO = ITEM.C_ID_REGISTRO ");
			sql.append(" WHERE ITEM.C_TIPO LIKE 'E' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
			sql.append(" AND trunc(cab.C_FECHA_GENERACION) between ");
			sql.append(" TO_DATE('" + simpleDateFormat.format(fechaDesde) + "','DD/MM/YYYY') ");
			sql.append(" and TO_DATE('" + simpleDateFormat.format(fechaHasta) + "','DD/MM/YYYY') ");
/*@I3918*/			if (!numero.equals("")) sql.append(" and to_number(DET.C_NRO_COMPROBANTE) = to_number('" + numero +"')" );
/*@F3918*/			if (!importe.equals(0D)) sql.append(" AND to_number(DET.C_IMPORTE) = " + importe);

			sql.append(" UNION ");
			
			sql.append(" SELECT CAB.C_ID_CABECERA_CONCILIACION,CAB.C_COMENTARIO,CAB.C_FECHA_GENERACION ");
			sql.append(" ,CAB.C_FECHA_CONFIRMACION,CAB.C_ID_OPERADOR,CAB.C_CONCILIADO ");
			sql.append(" ,CAB.C_FECHA_REVERSION,CAB.C_ID_OPERADOR_REVERSION,CAB.C_ID_BANCO_PROPIO ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_CHEQUES_HISTORIAL HIS ON HIS.C_ID_CHEQUE_HISTORIAL = ITEM.C_ID_REGISTRO ");
			sql.append(" JOIN t_vis_fon_asientos_item aitem ON his.c_id_asiento_item=aitem.c_id_asiento_item ");
			sql.append(" JOIN t_vis_fon_asientos asi ON aitem.c_id_asiento = asi.c_id_asiento ");
/*@I3918*/			sql.append(" inner join t_vis_fon_cheques chq on CHQ.C_ID_CHEQUE = HIS.C_ID_CHEQUE ");
/*@F3918*/			sql.append(" WHERE ITEM.C_TIPO LIKE 'F' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
			sql.append(" AND trunc(cab.C_FECHA_GENERACION) between ");
			sql.append(" TO_DATE('" + simpleDateFormat.format(fechaDesde) + "','DD/MM/YYYY') ");
			sql.append(" and TO_DATE('" + simpleDateFormat.format(fechaHasta) + "','DD/MM/YYYY') ");
/*@I3918*/			if (!numero.equals("")) sql.append("and to_number(CHQ.C_NUMERO) = to_number('" + numero +"')" );
/*@F3918*/			if (!importe.equals(0D)) sql.append(" AND to_number(CHQ.C_IMPORTE) = " + importe);
		}
		else if(ConciliacionFondoCabecera.FECHA_MOVIMIENTO == tipoFecha)
		{
			sql.append(" SELECT CAB.C_ID_CABECERA_CONCILIACION,CAB.C_COMENTARIO,CAB.C_FECHA_GENERACION ");
			sql.append(" ,CAB.C_FECHA_CONFIRMACION,CAB.C_ID_OPERADOR,CAB.C_CONCILIADO ");
			sql.append(" ,CAB.C_FECHA_REVERSION,CAB.C_ID_OPERADOR_REVERSION,CAB.C_ID_BANCO_PROPIO ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_CHEQUES_HISTORIAL HIS ON HIS.C_ID_CHEQUE_HISTORIAL = ITEM.C_ID_REGISTRO ");
			sql.append(" JOIN t_vis_fon_asientos_item aitem ON his.c_id_asiento_item=aitem.c_id_asiento_item ");
			sql.append(" JOIN t_vis_fon_asientos asi ON aitem.c_id_asiento = asi.c_id_asiento ");
/*@I3918*/			sql.append(" INNER JOIN T_VIS_FON_CHEQUES CHQ ON CHQ.C_ID_CHEQUE = HIS.C_id_cheque ");
/*@F3918*/			sql.append(" WHERE ITEM.C_TIPO LIKE 'F' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
			sql.append(" AND trunc(asi.c_fecha) between ");
			sql.append(" TO_DATE('" + simpleDateFormat.format(fechaDesde) + "','DD/MM/YYYY') ");
			sql.append(" and TO_DATE('" + simpleDateFormat.format(fechaHasta) + "','DD/MM/YYYY') ");
/*@I3918*/			if (!numero.equals(""))sql.append(" and to_number(CHQ.C_NUMERO) = to_number('"+numero+"') ");
/*@F3918*/			if (!importe.equals(0D)) sql.append(" AND to_number(CHQ.C_IMPORTE) = " + importe);
		}
		else if(ConciliacionFondoCabecera.FECHA_EXTRACTO == tipoFecha)
		{
			sql.append(" SELECT CAB.C_ID_CABECERA_CONCILIACION,CAB.C_COMENTARIO,CAB.C_FECHA_GENERACION ");
			sql.append(" ,CAB.C_FECHA_CONFIRMACION,CAB.C_ID_OPERADOR,CAB.C_CONCILIADO ");
			sql.append(" ,CAB.C_FECHA_REVERSION,CAB.C_ID_OPERADOR_REVERSION,CAB.C_ID_BANCO_PROPIO ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_DETALLE_EXTRACTO DET ON DET.C_ID_DETALLE_EXTRACTO = ITEM.C_ID_REGISTRO ");
			sql.append(" WHERE ITEM.C_TIPO LIKE 'E' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
			sql.append(" AND trunc(det.c_fecha_movimiento) between ");
			sql.append(" TO_DATE('" + simpleDateFormat.format(fechaDesde) + "','DD/MM/YYYY') ");
			sql.append(" and TO_DATE('" + simpleDateFormat.format(fechaHasta) + "','DD/MM/YYYY') ");
/*@I3918*/			if (!numero.equals(""))sql.append(" AND to_number(DET.C_NRO_COMPROBANTE) = to_number('" + numero + "') ");
			if (!importe.equals(0D)) sql.append(" AND to_number(DET.C_IMPORTE) = " + importe);
/*@F3918*/			
			
		}else if(ConciliacionFondoCabecera.SIN_FECHA == tipoFecha)
		{

			sql.append(" SELECT CAB.C_ID_CABECERA_CONCILIACION,CAB.C_COMENTARIO,CAB.C_FECHA_GENERACION ");
			sql.append(" ,CAB.C_FECHA_CONFIRMACION,CAB.C_ID_OPERADOR,CAB.C_CONCILIADO ");
			sql.append(" ,CAB.C_FECHA_REVERSION,CAB.C_ID_OPERADOR_REVERSION,CAB.C_ID_BANCO_PROPIO ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_DETALLE_EXTRACTO DET ON DET.C_ID_DETALLE_EXTRACTO = ITEM.C_ID_REGISTRO ");
			sql.append(" WHERE ITEM.C_TIPO LIKE 'E' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
/*@I3918*/			if (!numero.equals("")) sql.append(" and to_number(DET.C_NRO_COMPROBANTE) = to_number('" + numero +"')" );
/*@F3918*/			if (!importe.equals(0D)) sql.append(" AND to_number(DET.C_IMPORTE) = " + importe);

			sql.append(" UNION ");
			
			sql.append(" SELECT CAB.C_ID_CABECERA_CONCILIACION,CAB.C_COMENTARIO,CAB.C_FECHA_GENERACION ");
			sql.append(" ,CAB.C_FECHA_CONFIRMACION,CAB.C_ID_OPERADOR,CAB.C_CONCILIADO ");
			sql.append(" ,CAB.C_FECHA_REVERSION,CAB.C_ID_OPERADOR_REVERSION,CAB.C_ID_BANCO_PROPIO ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_CHEQUES_HISTORIAL HIS ON HIS.C_ID_CHEQUE_HISTORIAL = ITEM.C_ID_REGISTRO ");
			sql.append(" JOIN t_vis_fon_asientos_item aitem ON his.c_id_asiento_item=aitem.c_id_asiento_item ");
			sql.append(" JOIN t_vis_fon_asientos asi ON aitem.c_id_asiento = asi.c_id_asiento ");
			sql.append(" WHERE ITEM.C_TIPO LIKE 'F' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
/*@I3918*/			if (!numero.equals("")) sql.append("and to_number(CHQ.C_NUMERO) = to_number('" + numero +"')" );
/*@F3918*/			if (!importe.equals(0D)) sql.append(" AND to_number(CHQ.C_IMPORTE) = " + importe);
		}
		sqlPaginado.append(" SELECT * FROM (SELECT row_.*, rownum rownum_  FROM ( ");
		sqlPaginado.append(sql.toString());
/*@I3918*/		sqlPaginado.append(" ) row_   WHERE rownum <= " + ((firstResult) + maxResults) + "   )WHERE rownum_ > " + firstResult + " order by 1");
/*@F3918*/		return sqlPaginado;
	}
	
/*@I3918*/	private StringBuffer armarQueryContarRegistrosPorTipoFecha(int tipoFecha, Date fechaDesde, Date fechaHasta, Long idBancoPropio, String conciliado, String numero, Double importe)
/*@F3918*/	{
		StringBuffer sql = new StringBuffer(500);
		StringBuffer sqlPaginado = new StringBuffer(500);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		if(ConciliacionFondoCabecera.FECHA_CONCILIACION == tipoFecha)
		{

			sql.append(" SELECT count(*)registros ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_DETALLE_EXTRACTO DET ON DET.C_ID_DETALLE_EXTRACTO = ITEM.C_ID_REGISTRO ");
			sql.append(" WHERE ITEM.C_TIPO LIKE 'E' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
			sql.append(" AND trunc(cab.C_FECHA_GENERACION) between ");
			sql.append(" TO_DATE('" + simpleDateFormat.format(fechaDesde) + "','DD/MM/YYYY') ");
			sql.append(" and TO_DATE('" + simpleDateFormat.format(fechaHasta) + "','DD/MM/YYYY') ");
/*@I3918*/			if (!numero.equals("")) sql.append(" and to_number(DET.C_NRO_COMPROBANTE) = to_number('" + numero +"')" );
/*@F3918*/			if (!importe.equals(0D)) sql.append(" AND to_number(DET.C_IMPORTE) = " + importe);

			sql.append(" UNION ");
			
			sql.append(" SELECT count(*)registros ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_CHEQUES_HISTORIAL HIS ON HIS.C_ID_CHEQUE_HISTORIAL = ITEM.C_ID_REGISTRO ");
			sql.append(" JOIN t_vis_fon_asientos_item aitem ON his.c_id_asiento_item=aitem.c_id_asiento_item ");
			sql.append(" JOIN t_vis_fon_asientos asi ON aitem.c_id_asiento = asi.c_id_asiento ");
/*@I3918*/			sql.append(" inner join t_vis_fon_cheques chq on CHQ.C_ID_CHEQUE = HIS.C_ID_CHEQUE ");
/*@F3918*/			sql.append(" WHERE ITEM.C_TIPO LIKE 'F' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
			sql.append(" AND trunc(cab.C_FECHA_GENERACION) between ");
			sql.append(" TO_DATE('" + simpleDateFormat.format(fechaDesde) + "','DD/MM/YYYY') ");
			sql.append(" and TO_DATE('" + simpleDateFormat.format(fechaHasta) + "','DD/MM/YYYY') ");
/*@I3918*/			if (!numero.equals("")) sql.append("and to_number(CHQ.C_NUMERO) = to_number('" + numero +"')" );
/*@F3918*/			if (!importe.equals(0D)) sql.append(" AND to_number(CHQ.C_IMPORTE) = " + importe);
		}
		else if(ConciliacionFondoCabecera.FECHA_MOVIMIENTO == tipoFecha)
		{
			sql.append(" SELECT count(*)registros ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_CHEQUES_HISTORIAL HIS ON HIS.C_ID_CHEQUE_HISTORIAL = ITEM.C_ID_REGISTRO ");
			sql.append(" JOIN t_vis_fon_asientos_item aitem ON his.c_id_asiento_item=aitem.c_id_asiento_item ");
			sql.append(" JOIN t_vis_fon_asientos asi ON aitem.c_id_asiento = asi.c_id_asiento ");
/*@I3918*/			sql.append(" INNER JOIN T_VIS_FON_CHEQUES CHQ ON CHQ.C_ID_CHEQUE = HIS.C_id_cheque ");
/*@F3918*/			sql.append(" WHERE ITEM.C_TIPO LIKE 'F' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
			sql.append(" AND trunc(asi.c_fecha) between ");
			sql.append(" TO_DATE('" + simpleDateFormat.format(fechaDesde) + "','DD/MM/YYYY') ");
			sql.append(" and TO_DATE('" + simpleDateFormat.format(fechaHasta) + "','DD/MM/YYYY') ");
/*@I3918*/			if (!numero.equals(""))sql.append(" and to_number(CHQ.C_NUMERO) = to_number('"+numero+"') ");
/*@F3918*/			if (!importe.equals(0D)) sql.append(" AND to_number(CHQ.C_IMPORTE) = " + importe);
		}
		else if(ConciliacionFondoCabecera.FECHA_EXTRACTO == tipoFecha)
		{
			sql.append(" SELECT count(*)registros ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_DETALLE_EXTRACTO DET ON DET.C_ID_DETALLE_EXTRACTO = ITEM.C_ID_REGISTRO ");
			sql.append(" WHERE ITEM.C_TIPO LIKE 'E' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
			sql.append(" AND trunc(det.c_fecha_movimiento) between ");
			sql.append(" TO_DATE('" + simpleDateFormat.format(fechaDesde) + "','DD/MM/YYYY') ");
			sql.append(" and TO_DATE('" + simpleDateFormat.format(fechaHasta) + "','DD/MM/YYYY') ");
/*@I3918*/			if (!numero.equals(""))sql.append(" AND to_number(DET.C_NRO_COMPROBANTE) = to_number('" + numero + "') ");
/*@F3918*/			if (!importe.equals(0D)) sql.append(" AND to_number(DET.C_IMPORTE) = " + importe);
			
		}else if(ConciliacionFondoCabecera.SIN_FECHA == tipoFecha)
		{

			sql.append(" SELECT count(*)registros ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_DETALLE_EXTRACTO DET ON DET.C_ID_DETALLE_EXTRACTO = ITEM.C_ID_REGISTRO ");
			sql.append(" WHERE ITEM.C_TIPO LIKE 'E' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
			if (!numero.equals("")) sql.append(" and to_number(DET.C_NRO_COMPROBANTE) = to_number('" + numero +"')" );
			if (!importe.equals(0D)) sql.append(" AND to_number(DET.C_IMPORTE) = " + importe);

			sql.append(" UNION ");
			
			sql.append(" SELECT count(*)registros ");
			sql.append(" FROM T_VIS_FON_CONCILIA_CABECERA CAB ");
			sql.append(" JOIN T_VIS_FON_CONCILIACION ITEM ON CAB.C_ID_CABECERA_CONCILIACION = ITEM.C_ID_CABECERA_CONCILIACION ");
			sql.append(" JOIN T_VIS_FON_CHEQUES_HISTORIAL HIS ON HIS.C_ID_CHEQUE_HISTORIAL = ITEM.C_ID_REGISTRO ");
			sql.append(" JOIN t_vis_fon_asientos_item aitem ON his.c_id_asiento_item=aitem.c_id_asiento_item ");
			sql.append(" JOIN t_vis_fon_asientos asi ON aitem.c_id_asiento = asi.c_id_asiento ");
/*@I3918*/			sql.append(" inner join t_vis_fon_cheques chq on CHQ.C_ID_CHEQUE = HIS.C_ID_CHEQUE ");
/*@F3918*/			sql.append(" WHERE ITEM.C_TIPO LIKE 'F' ");
			sql.append(" AND CAB.C_ID_BANCO_PROPIO ="  + idBancoPropio + " ");
			sql.append(" AND CAB.C_CONCILIADO ='"  + conciliado + "' ");
/*@I3918*/			if (!numero.equals("")) sql.append("and to_number(CHQ.C_NUMERO) = to_number('" + numero +"')" );
/*@F3918*/			if (!importe.equals(0D)) sql.append(" AND to_number(CHQ.C_IMPORTE) = " + importe);
		}
		return sql;
	}
	
	public List<ConciliacionFondoCabecera> listarPaginado(final Filtro filtro, final int firstResult, final int maxResults) {
		
		final String hql = filtro.getHQL();

		return (List<ConciliacionFondoCabecera>)getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ConciliacionFondoCabecera obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString()).setFirstResult(firstResult).setMaxResults(maxResults);
				List list = query.list();
				Iterator iterator = query.list().iterator();
				
				List listNew = new ArrayList();
				while(iterator.hasNext()){
					ConciliacionFondoCabecera cabecera = (ConciliacionFondoCabecera)iterator.next();
					BancoPropio bancoPropio = new BancoPropio();
					bancoPropio.setIdBancoPropio(cabecera.getBancoPropio().getIdBancoPropio());
					cabecera.setBancoPropio(bancoPropio);
					cabecera.setOperadorConfirmo(null);
					cabecera.setOperadorReversion(null);
					listNew.add(cabecera);
				}
				return listNew;
				
			}
		});
		
    }
	
	public Long cantidadRegistros(Filtro filtro) {

		final String hql = filtro.getHQL();

		return (Long)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
					
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT count(DISTINCT obj) as cantidad ");
				sb.append("FROM ConciliacionFondoCabecera obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				Object obj = query.uniqueResult();
				Long result = 0L;
				if(obj!=null)
				{
					result = new Long(obj.toString());
				}
				return result;
			}
		});
	}
	
	@Override
	public void actualizarConciliacionFondoCabecera(ConciliacionFondoCabecera object) {
		this.getHibernateTemplate().update(object);
	}

	@Override
	public void borrarConciliacionFondoCabecera(Long id) {
		borrarConciliacionFondoCabecera(buscarConciliacionFondoCabecera(id));
	}

	@Override
	public void borrarConciliacionFondoCabecera(ConciliacionFondoCabecera object) {
		this.getHibernateTemplate().delete(object);
	}

	@Override
	public ConciliacionFondoCabecera buscarConciliacionFondoCabecera(Long id) {
		return (ConciliacionFondoCabecera) this.getHibernateTemplate().get(ConciliacionFondoCabecera.class, id);
	}

	@Override
	public void grabarConciliacionFondoCabecera(ConciliacionFondoCabecera object) {
		this.getHibernateTemplate().save(object);
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


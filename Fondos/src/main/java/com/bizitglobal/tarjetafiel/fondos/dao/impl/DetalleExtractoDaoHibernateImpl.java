package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.Format;
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
import com.bizitglobal.tarjetafiel.fondos.dao.DetalleExtractoDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto;

public class DetalleExtractoDaoHibernateImpl extends HibernateDaoSupport implements DetalleExtractoDao  {
	
	private static final Logger log = Logger.getLogger(DetalleExtractoDaoHibernateImpl.class);
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	public DetalleExtractoDaoHibernateImpl() {
		super();
	}

	public void grabarDetalleExtracto (DetalleExtracto pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public DetalleExtracto buscarDetalleExtracto (Long id) {
		return (DetalleExtracto) this.getHibernateTemplate().get(DetalleExtracto.class, id);
	}
	
	public DetalleExtracto buscarDetalleExtractoFlex (Long id) {
		return copiarDetalle(buscarDetalleExtracto(id));
	}
	
	public void borrarDetalleExtracto (Long id) {
		borrarDetalleExtracto(buscarDetalleExtracto(id));
	}
	public void borrarDetalleExtracto (DetalleExtracto pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarDetalleExtracto (DetalleExtracto pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM DetalleExtracto obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	public List listarTodosFlex(Filtro filtro) {
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM DetalleExtracto obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = new ArrayList();
				Iterator iterator = query.list().iterator();
				while(iterator.hasNext()){
					list.add(copiarDetalle((DetalleExtracto)iterator.next()));
				}
				return list;
			}
		});
	}
	
	public List listarTodosNoConciliadosFlex(Filtro filtro) {
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM DetalleExtracto obj ");
				sb.append(hql);
				//sb.append(" and not exists ( from ConciliacionFondo as objConci where objConci.idRegistro = obj.idDetalleExtracto ) ");
				
				Query query = session.createQuery(sb.toString());
				
				List list = new ArrayList();
				Iterator iterator = query.list().iterator();
				while(iterator.hasNext()){
					list.add(copiarDetalle((DetalleExtracto)iterator.next()));
				}
				return list;
			}
		});
	}
	
	
	private DetalleExtracto copiarDetalle(DetalleExtracto det){
		DetalleExtracto nuevoDetalle = new DetalleExtracto();
		nuevoDetalle.setIdDetalleExtracto(det.getIdDetalleExtracto());
		// el registro me parece incesario pasarlo, por eso no lo cargo
		nuevoDetalle.setTipoRegistro(det.getTipoRegistro());
		nuevoDetalle.setFechaProceso(det.getFechaProceso());
		nuevoDetalle.setNroComprobante(det.getNroComprobante());
		nuevoDetalle.setImporte(det.getImporte());
		nuevoDetalle.setNroCuentaCorto(det.getNroCuentaCorto());
		// conciliado no lo paso por que siempre va a ser 'N'
		// el extaracto tampoco tiene datos que me sirvan
		nuevoDetalle.setConcepto(det.getConcepto());
		//nuevoDetalle.setBancoPropio(det.getBancoPropio());
		nuevoDetalle.setFechaMovimiento(det.getFechaMovimiento());
		nuevoDetalle.setFechaValor(det.getFechaValor());
		nuevoDetalle.setSigno(det.getSigno());
		nuevoDetalle.setCodigoOperacion(det.getCodigoOperacion());
		nuevoDetalle.setDescripcion(det.getDescripcion());
		nuevoDetalle.setSucursalOrigen(det.getSucursalOrigen());
		nuevoDetalle.setCodigoDepositante(det.getCodigoDepositante());
		nuevoDetalle.setNroCuenta(det.getNroCuenta());
		nuevoDetalle.setCodigoOperacionBanco(det.getCodigoOperacionBanco());
		return nuevoDetalle;
	}
	public List buscarRangoConDatos(Long minFecha, Long maxFecha, Long idBancoPropio){
		StringBuffer sql = new StringBuffer(100);
		sql.append(" select c_fecha_mov_cadena, count(*)registros ");
		sql.append(" from t_vis_fon_detalle_extracto ");
		sql.append(" where C_ID_BANCO_PROPIO = " + idBancoPropio + " ");
		sql.append(" group by c_fecha_mov_cadena ");
		sql.append(" having c_fecha_mov_cadena >= " + minFecha + " and c_fecha_mov_cadena <= " + maxFecha + " ");
		sql.append(" order by c_fecha_mov_cadena ");
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		List result = new ArrayList();
		while(iter.hasNext()){
			Map map = (Map) iter.next();
			if (map.get("c_fecha_mov_cadena")!=null) {
				result.add(new Long(map.get("c_fecha_mov_cadena").toString()));
			}
		}

		return result;
	}
	
	public double traerSaldoExtractoHastaFecha(Long idBancoPropio, Date fechaCorte, Character conciliado)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		StringBuffer sql = new StringBuffer(100);
		sql.append(" SELECT NVL(SUM((CASE WHEN C_SIGNO='C' THEN 1 ELSE -1 END)* c_importe),0)saldo "); 
		sql.append(" FROM T_VIS_FON_DETALLE_EXTRACTO "); 
		sql.append(" where C_ID_BANCO_PROPIO = " + idBancoPropio + " ");
		sql.append(" AND trunc(C_FECHA_MOVIMIENTO) <= to_date('" + simpleDateFormat.format(fechaCorte) + "','DD/MM/YYYY')"); 
		sql.append(" AND c_conciliado='" + conciliado + "' ");
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		Map map = jt.queryForMap(sql.toString());
		BigDecimal result = new BigDecimal(0);
		if(map !=null)
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


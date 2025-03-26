package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.dao.CajaAperturaDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.Caja;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaApertura;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;
import com.bizitglobal.tarjetafiel.general.negocio.Impresora;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;



public class CajaAperturaDaoHibernateImpl extends HibernateDaoSupport implements CajaAperturaDao  {
	
	private static final Logger log = Logger.getLogger(CajaAperturaDaoHibernateImpl.class);

	private DataSource dataSource;
	private JdbcTemplate jt;
	
	public CajaAperturaDaoHibernateImpl() {
		super();
	}

	public void grabarCajaApertura (CajaApertura pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public CajaApertura buscarCajaApertura (Long id) {
		return (CajaApertura) this.getHibernateTemplate().get(CajaApertura.class, id);
	}
	public void borrarCajaApertura (Long id) {
		borrarCajaApertura(buscarCajaApertura(id));
	}
	public void borrarCajaApertura (CajaApertura pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarCajaApertura (CajaApertura pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM CajaApertura obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

	
	/**
	 * Devuelve los id de los ultimos cierres
	 * @param esNvaApertura indica si se se esta haciendo una nva apertura o si se esta consultando una apertura
	 * @param cajasAbiertas  String de cajas q estan acutalmente abierta para restringir la busqueda a  solo esas cajas
	 * @return String con ids de los ultimos cierres
	 */
	public String ultimoCierreCajas(boolean esNvaApertura,String cajasAbiertas){
        		 
		StringBuffer sql = new StringBuffer(100);
		/*sql.append(" SELECT  c_id_caja, max(c_id_cajaapertura) as cerrada");
		sql.append(" from t_vis_fon_cajas_aperturas" );
		sql.append(" WHERE c_fecha_cierre  is not NULL");
		sql.append(" GROUP by c_id_caja ");*/

		sql.append(" SELECT  ca.c_id_caja, max(ca.c_id_cajaapertura) as cerrada");
		sql.append(" from t_vis_fon_cajas_aperturas  ca" );
		
		if(!esNvaApertura){   //
			   sql.append(" WHERE ca.c_fecha_cierre  is  NULL");
			   sql.append(" AND ca.c_id_caja IN (" +cajasAbiertas+")");
		  } else	 sql.append(" WHERE ca.c_fecha_cierre  is NOT NULL");
		    sql.append(" GROUP by ca.c_id_caja ");
		  if(esNvaApertura){ 
			sql.append(" having max(ca.c_id_cajaapertura) >");
			sql.append("   (select nvl(max(ca2.c_id_cajaapertura),0) ");  //hacemos esta subconsulta para asegurarnos q la caja este realmente cerrada
			sql.append("      FROM  t_vis_fon_cajas_aperturas  ca2 ");
			sql.append("      WHERE ca2.c_fecha_cierre  is  NULL ");
			sql.append("      and  ca2.c_id_caja= ca.c_id_caja) ");
		   }	
			log.info("SQL Ejecutado ==> " + sql.toString());
			jt = new JdbcTemplate(dataSource);
			List rows = jt.queryForList(sql.toString());
			Iterator iter = rows.iterator();
			String idCajasApertura="";
			while(iter.hasNext()){
				Map map = (Map) iter.next();
				if (map.get("cerrada")!=null) {
				  Long numero = (new Long(map.get("cerrada").toString()));
				  idCajasApertura += numero;
				if (iter.hasNext()) idCajasApertura += ", ";
				}
			}
			
			return idCajasApertura;
		}

	
	public CajaApertura getMaxCajaApertura(Long codOperador){
		CajaApertura cajaApertura = null;
		String hql = "Select max(caja.idCajaApertura) From CajaApertura caja Where" +
				" caja.operador.codigo = :codOperador AND caja.fechaCierre is null"; 
		Query query = getSession().createQuery(hql);		
		query.setParameter("codOperador", codOperador);
		List result = query.list();		
		Object o = result.get(0);
		if(o == null) return null;
		Long cajaApe = result.size() > 0 ? Long.valueOf(result.get(0).toString()) : Long.valueOf("-1");
		cajaApertura = (CajaApertura) getHibernateTemplate().get(CajaApertura.class, cajaApe);
		Operador operador = cajaApertura.getOperador();
		cajaApertura.setOperador(new Operador());
		cajaApertura.getOperador().setCodigo(operador.getCodigo());
		Caja caja = cajaApertura.getCaja();
		if(caja != null){
			Caja newCaja = new Caja();
			newCaja.setDescripcion(caja.getDescripcion());
			newCaja.setFechaModificacion(caja.getFechaModificacion());
			newCaja.setHabilitada(caja.getHabilitada());
			newCaja.setIdCaja(caja.getIdCaja());			
			
			if(caja.getLugar() != null){
				newCaja.setLugar(new Lugar());
				newCaja.getLugar().setDescripcion(caja.getLugar().getDescripcion());
				newCaja.getLugar().setIdLugar(caja.getLugar().getIdLugar());
				newCaja.getLugar().setTipo(caja.getLugar().getTipo());
			}
			
			if(caja.getOperador() != null){
				newCaja.setOperador(new Operador());
				newCaja.getOperador().setCodigo(caja.getOperador().getCodigo());
				newCaja.getOperador().setApellido(caja.getOperador().getApellido());
				newCaja.getOperador().setNombre(caja.getOperador().getNombre());
			}
			
			if(caja.getImpresora() != null){
				newCaja.setImpresora(new Impresora());
				newCaja.getImpresora().setDescripcion(caja.getImpresora().getDescripcion());
				newCaja.getImpresora().setHabilitado(caja.getImpresora().getHabilitado());
				newCaja.getImpresora().setIdImpresora(caja.getImpresora().getIdImpresora());
				newCaja.getImpresora().setPath(caja.getImpresora().getPath());
				newCaja.getImpresora().setPort(caja.getImpresora().getPort());
				newCaja.getImpresora().setNombre(caja.getImpresora().getNombre());
			}

			if(caja.getPlanCuenta() != null){
				newCaja.setPlanCuenta(new PlanCuentaDos());
				newCaja.getPlanCuenta().setAjusteInflacion(caja.getPlanCuenta().getAjusteInflacion());
				newCaja.getPlanCuenta().setCaja(caja.getPlanCuenta().getCaja());
				newCaja.getPlanCuenta().setCentroCosto(caja.getPlanCuenta().getCentroCosto());
				newCaja.getPlanCuenta().setCodBco(caja.getPlanCuenta().getCodBco());
				newCaja.getPlanCuenta().setCodCtaBco(caja.getPlanCuenta().getCodCtaBco());
				newCaja.getPlanCuenta().setContab(caja.getPlanCuenta().getContab());
				newCaja.getPlanCuenta().setCuenta(caja.getPlanCuenta().getCuenta());
				newCaja.getPlanCuenta().setEstado(caja.getPlanCuenta().getEstado());
				newCaja.getPlanCuenta().setFecha_carga(caja.getPlanCuenta().getFecha_carga());
				newCaja.getPlanCuenta().setFlujoEfectivo(caja.getPlanCuenta().getFlujoEfectivo());
				newCaja.getPlanCuenta().setFondos(caja.getPlanCuenta().getFondos());
				newCaja.getPlanCuenta().setHabilitada(caja.getPlanCuenta().getHabilitada());
				newCaja.getPlanCuenta().setIdPadre(caja.getPlanCuenta().getIdPadre());
				newCaja.getPlanCuenta().setIdPlanCuenta(caja.getPlanCuenta().getIdPlanCuenta());
				newCaja.getPlanCuenta().setImporteMaximo(caja.getPlanCuenta().getImporteMaximo());
				newCaja.getPlanCuenta().setMoneda(caja.getPlanCuenta().getMoneda());
				newCaja.getPlanCuenta().setNivel(caja.getPlanCuenta().getNivel());
				newCaja.getPlanCuenta().setNumeroContable(caja.getPlanCuenta().getNumeroContable());
				newCaja.getPlanCuenta().setOperador(caja.getPlanCuenta().getOperador());
				newCaja.getPlanCuenta().setSaldo(caja.getPlanCuenta().getSaldo());
				newCaja.getPlanCuenta().setSaldoHabitual(caja.getPlanCuenta().getSaldoHabitual());
				newCaja.getPlanCuenta().setSeccion(caja.getPlanCuenta().getSeccion());
				newCaja.getPlanCuenta().setSigno(caja.getPlanCuenta().getSigno());
				newCaja.getPlanCuenta().setTipoCuenta(caja.getPlanCuenta().getTipoCuenta());
				newCaja.getPlanCuenta().setTipoDeCuenta(caja.getPlanCuenta().getTipoDeCuenta());
				newCaja.getPlanCuenta().setTitulo(caja.getPlanCuenta().getTitulo());
				newCaja.getPlanCuenta().setUso(caja.getPlanCuenta().getUso());						
			}
			
			if(caja.getSucursal() != null){
				newCaja.setSucursal(new SucursalFiel());
				newCaja.getSucursal().setIdSucursal(caja.getSucursal().getIdSucursal());
				newCaja.getSucursal().setNombre(caja.getSucursal().getNombre());
			}	
			
			cajaApertura.setCaja(newCaja);
			
		} else {
			return null;
		}
		return cajaApertura;
	}

		
	  
	
	
	
	public String ultimaAperturaCajas(){
		StringBuffer sql = new StringBuffer(100);
		sql.append(" SELECT  ca.c_id_caja, max(ca.c_id_cajaapertura) as abierta");
		sql.append(" from t_vis_fon_cajas_aperturas ca" );
		sql.append(" WHERE ca.c_fecha_cierre  is  NULL");
		sql.append(" GROUP by ca.c_id_caja ");
		sql.append(" having max(ca.c_id_cajaapertura) >");
		sql.append("   (select nvl(max(ca2.c_id_cajaapertura),0) ");  //hacemos esta subconsulta para asegurarnos q la caja este realmente cerrada
		sql.append("      FROM  t_vis_fon_cajas_aperturas  ca2 ");
		sql.append("      WHERE ca2.c_fecha_cierre  is NOT  NULL ");
		sql.append("      and  ca2.c_id_caja= ca.c_id_caja) ");
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		String idCajasApertura="";
		while(iter.hasNext()){
			Map map = (Map) iter.next();
			if (map.get("abierta")!=null) {
			  Long numero = (new Long(map.get("abierta").toString()));
			  idCajasApertura += numero;
			if (iter.hasNext()) idCajasApertura += ", ";
			}
		}
		
		return idCajasApertura;
	}
	
	
	public String cajerosAsignadosUltimaAperturaCajas(){
		StringBuffer sql = new StringBuffer(100);
		sql.append(" SELECT  ca.c_id_caja, ca.c_id_operador as operador, max(c_id_cajaapertura)");
		sql.append(" from t_vis_fon_cajas_aperturas ca  " );
		sql.append(" WHERE ca.c_fecha_cierre  is  NULL");
		sql.append(" GROUP by c_id_caja, c_id_operador ");
		sql.append(" having max(ca.c_id_cajaapertura) >");
		sql.append("   (select max(ca2.c_id_cajaapertura) ");  //hacemos esta subconsulta para asegurarnos q la caja este realmente cerrada
		sql.append("      FROM  t_vis_fon_cajas_aperturas  ca2 ");
		sql.append("      WHERE ca2.c_fecha_cierre  is NOT  NULL ");
		sql.append("      and  ca2.c_id_caja= ca.c_id_caja) ");
			log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		String idOperadores="";
		while(iter.hasNext()){
			Map map = (Map) iter.next();
			if (map.get("operador")!=null) {
			  Long numero = (new Long(map.get("operador").toString()));
			  idOperadores += numero;
			if (iter.hasNext()) idOperadores += ", ";
			}
		}
		
		return idOperadores;
	}

	
	
	/**
	 * Obtiene las cajas que nunca fueron abiertas
     * @return  String con los id de las cajas que no fueron abiertas
	 */
	public String getCajasSinAbrir(){
		StringBuffer sql = new StringBuffer(100);
		sql.append(" SELECT c_id_caja  as idCaja");
		sql.append(" FROM T_VIS_FON_CAJAS " );
		sql.append(" WHERE c_id_caja  not in ");
		sql.append("   (SELECT DISTINCT C_ID_CAJA ");
		sql.append("     FROM T_VIS_FON_CAJAS_APERTURAS) ");
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		String idCajas="";
		while(iter.hasNext()){
			
			Map map = (Map) iter.next();
			
			if (map.get("idCaja")!=null) {
			  Long numero = (new Long(map.get("idCaja").toString()));
			  idCajas += numero;
			if (iter.hasNext()) idCajas += ", ";
			}
		}
		
		return idCajas;
	}


	/* @id: 5953
	 * (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.fondos.dao.CajaAperturaDao#getAperturaVigente(java.lang.Long)
	 */
	public CajaApertura getAperturaVigente(final Long idCaja){
		return (CajaApertura) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT aper ");
				sb.append(" FROM CajaApertura aper ");
				sb.append(" WHERE aper.caja.idCaja = " + idCaja);
				sb.append(" AND aper.estado LIKE('A') ");
				sb.append(" ORDER BY aper.idCajaApertura DESC ");
				
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				if (list.isEmpty()) 
					return null;
				else
					return list.get(0);
			}
		});		
		
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


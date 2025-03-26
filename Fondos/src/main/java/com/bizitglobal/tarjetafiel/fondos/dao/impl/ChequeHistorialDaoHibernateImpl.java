package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeHistorialDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoItem;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeHistorial;
import com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;

public class ChequeHistorialDaoHibernateImpl extends HibernateDaoSupport implements ChequeHistorialDao  {
	
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	public ChequeHistorialDaoHibernateImpl() {
		super();
	}

	public void grabarChequeHistorial (ChequeHistorial pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public ChequeHistorial buscarChequeHistorial (Long id) {
		ChequeHistorial ch = (ChequeHistorial) this.getHibernateTemplate().get(ChequeHistorial.class, id);		
		return ch;
	}
	
	public List buscarChequeHistorial (final MovimientoMP movimientoMP) {
		return (List) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("Select obj From ChequeHistorial obj Where obj.movimientoMP.idMovimientoMP = :id");
				query.setParameter("id", movimientoMP.getIdMovimientoMP());
				return query.list();
			}
		});
	}	
	public void borrarChequeHistorial (Long id) {
		borrarChequeHistorial(buscarChequeHistorial(id));
	}
	public void borrarChequeHistorial (ChequeHistorial pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarChequeHistorial (ChequeHistorial pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ChequeHistorial obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
//	public List buscarParaPasarCorriente() {
	public List buscarParaPasarCorriente(final Date fecha) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
				sb.append("Select chis ");
				sb.append("from ChequeHistorial chis ");
				sb.append("where chis.cheque.tipo in ('P', 'A') ");
				sb.append("and chis.cheque.conciliado = 'N' ");
//				sb.append("and chis.cheque.fechaPago < sysdate +1 ");
				sb.append("and chis.cheque.fechaPago > "+ Filtro.getTO_DATE(22,11,2009));
				sb.append(" and chis.cheque.fechaPago < "+ Filtro.getTO_DATE(fecha) +" +1 ");
				sb.append("and chis.chequeEstado.idChequeEstado = 1 ");
				sb.append("AND chis.idChequeHistorial = ");
				sb.append("(SELECT MAX(aux.idChequeHistorial) ");
				sb.append("FROM ChequeHistorial aux ");
				sb.append("where aux.cheque = chis.cheque) ");
				
				Query query = session.createQuery(sb.toString());
				return query.list();
			}
		});
	}
	
	
	public String buscarEmailComercio(final Long  codComercio) {
		
				StringBuffer sb = new StringBuffer(100);
				
				sb.append("select LISTAGG (emails.c_email, ' ') WITHIN GROUP (ORDER BY emails.c_id_email) emails ");
				sb.append("from t_vis_tra_cod_comercios cod ");
				sb.append("inner join t_vis_gen_suc_empresas suc on suc.c_id_sucursal = cod.c_id_sucursal ");
				sb.append("inner join t_vis_gen_suc_emails email on email.c_id_sucursal = suc.c_id_sucursal ");
				sb.append("inner join t_vis_gen_emails emails on emails.c_id_email = email.c_id_email ");
				sb.append("where cod.c_id_cod_comercio = " + codComercio );
				
				jt = new JdbcTemplate(dataSource);
				List row = jt.queryForList(sb.toString());
				
				String result = null;
				if(row != null){
					Map map = (Map) row.get(0);
					if (map.get("emails") != null) {	
						result = map.get("emails").toString();
					}
				}
				return result;
			}
	
	
	
	public String buscarEmailProveedor(final Long  codComercio) {
		
				StringBuffer sb = new StringBuffer(100);
				
				sb.append("select LISTAGG (emails.c_email, ' ') WITHIN GROUP (ORDER BY emails.c_id_email) emails from t_vis_prov_proveedores pro ");
				sb.append("inner join t_vis_prov_emails email on email.c_id_proveedor = pro.c_id_proveedor ");
				sb.append("inner join t_vis_gen_emails emails on emails.c_id_email = email.c_id_email ");
				sb.append("where pro.c_id_proveedor = "+ codComercio );
								
				
				jt = new JdbcTemplate(dataSource);
				List row = jt.queryForList(sb.toString());
				
				String result = null;
				if(row != null){
					Map map = (Map) row.get(0);
					if (map.get("emails") != null) {	
						result = map.get("emails").toString();
					}
				}
				return result;
	}
	
	public List buscarParaPasarVencido() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
				sb.append("Select chis ");
				sb.append("from ChequeHistorial chis ");
				sb.append("where chis.cheque.tipo in ('P') ");
				sb.append("and chis.cheque.conciliado = 'N' ");
				sb.append("and chis.cheque.fechaPago + 30 < sysdate ");
				sb.append("and chis.chequeEstado.idChequeEstado = 2 ");
				sb.append("AND chis.idChequeHistorial = ");
				sb.append("(SELECT MAX(aux.idChequeHistorial) ");
				sb.append("FROM ChequeHistorial aux ");
				sb.append("where aux.cheque = chis.cheque) ");
				
				Query query = session.createQuery(sb.toString());
				return query.list();
			}
		});
	}
	
	public List buscarParaPasarBaja() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
				sb.append("Select chis ");
				sb.append("from ChequeHistorial chis ");
				sb.append("where chis.cheque.tipo in ('P', 'A') ");
				sb.append("and chis.cheque.conciliado = 'N' ");
				sb.append("and chis.cheque.fechaPago + 360 < sysdate ");
				sb.append("and chis.chequeEstado.idChequeEstado in (6, 2) ");
				sb.append("AND chis.idChequeHistorial = ");
				sb.append("(SELECT MAX(aux.idChequeHistorial) ");
				sb.append("FROM ChequeHistorial aux ");
				sb.append("where aux.cheque = chis.cheque) ");
				
				Query query = session.createQuery(sb.toString());
				return query.list();
			}
		});
	}
	
	public ChequeHistorial buscarUltimoHistorial (final Cheque cheque) {
		return (ChequeHistorial) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				SQLQuery sqlQuery = session.createSQLQuery(
					"Select max(C_ID_CHEQUE_HISTORIAL) From T_VIS_FON_CHEQUES_HISTORIAL " +
						"Where c_id_cheque = :idCheque");
				sqlQuery.setParameter("idCheque", cheque.getIdCheque());
				
				Object object = sqlQuery.uniqueResult();
				if(object == null)
					throw new HibernateException("No hay historiales");
				else
					return buscarChequeHistorial(new Long(object.toString()));
			}
		});
	}	
	
	@Override
	public List buscarChequesParaValidarEstados() {
		
		return (List) getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				String sql = "SELECT chis.c_id_cheque_estado, chis.c_id_cheque_historial " +
							 "FROM t_vis_fon_cheques_historial chis INNER JOIN t_vis_fon_cheques che ON chis.c_id_cheque = che.c_id_cheque " + 
							 "Where che.c_tipo = 'P' AND chis.c_id_cheque_estado in (1,2,6) AND che.c_conciliado = 'N' " + 
							 "AND chis.c_timestamp = (Select max(aux.c_timestamp) From t_vis_fon_cheques_historial aux Where aux.c_id_cheque = chis.c_id_cheque)"; 

				SQLQuery sqlQuery = session.createSQLQuery(sql);
				return sqlQuery.list();
			}
		});				
	}
	
	public PlanCuentaDos buscarPlanCuenta(final ChequeHistorial chequeHistorial) {
		
		return (PlanCuentaDos)getHibernateTemplate().execute(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String sql = "Select * From T_VIS_CONT_PLAN_CUENTA WHERE " +
				"C_ID_ESTADO_CHEQUE = " + chequeHistorial.getChequeEstado().getIdChequeEstado() +
				" AND C_COD_BCO = " + chequeHistorial.getCheque().getBancoPropio().getBanco().getIdBanco() +
				" AND C_COD_CTA_BCO = " + chequeHistorial.getCheque().getBancoPropio().getNumeroCuenta();
				if (!chequeHistorial.getChequeEstado().getIdChequeEstado().equals(2L)) {
					if(chequeHistorial.getCheque().getTipo().equals('P'))
						sql += " AND C_ID_FORMA_PAGO = 2 ";
					else 
						sql += " AND C_ID_FORMA_PAGO = 3 ";
				}
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.addEntity(PlanCuentaDos.class);
	
				PlanCuentaDos planCuentaDos = (PlanCuentaDos)sqlQuery.uniqueResult();
				return planCuentaDos;
			}
		});
	}
	
	public ChequeEstado getChequeEstadoByIdCheque(Long idCheque){
		
		final Long id = idCheque;
		ChequeEstado resultado = null;
		
		List l = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT new ChequeEstado(e.idChequeEstado, e.descripcion, e.tipo) ");
				sb.append("FROM ChequeEstado e, ChequeHistorial h ");
				sb.append("WHERE h.cheque.idCheque = ").append(id+" ");
				sb.append("AND e = h.chequeEstado ");
				sb.append("AND h.idChequeHistorial = ").append("(select max(idChequeHistorial) from ChequeHistorial where cheque.idCheque = ").append(id+")");
				
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
		if (l!=null && !l.isEmpty()){
			resultado = (ChequeEstado) l.get(0);
		}
		return resultado;
	}
	
	
	public List listarTodosFlex(Filtro filtro) {
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ChequeHistorial obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				Iterator iter = list.iterator();
				List listCheques = new ArrayList();
				while(iter.hasNext())
				{
					ChequeHistorial chequeHistorial = (ChequeHistorial)iter.next();
					
					chequeHistorial.getCheque().getId();
					
					listCheques.add(chequeHistorial);
				}
				return listCheques;
			}
		});
	}
	
	public List listarTodosConciliar(Filtro filtro) {
		final String hql = filtro.getHQL();
		//final Long idBancoPropio = (Long)filtro.getValores().get(1);
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ChequeHistorial obj ");
				sb.append(hql);
				sb.append(" and  exists ( from DetalleExtracto as det where det.conciliado = 'N' and TO_NUMBER(obj.cheque.numero) = TO_NUMBER(det.nroComprobante)) ");

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				Iterator iter = list.iterator();
				List listCheques = new ArrayList();
				while(iter.hasNext())
				{
					ChequeHistorial chequeHistorial = (ChequeHistorial)iter.next();
					
					chequeHistorial.getCheque().getId();
					
					listCheques.add(chequeHistorial);
				}
				return listCheques;
			}
		});
	}
	
/*@I3918*/	public List listarTodosConciliar(final Long idPlanCuenta) {
		//final Long idBancoPropio = (Long)filtro.getValores().get(1);
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				StringBuffer sb = new StringBuffer(10000);
//				sb.append("SELECT DISTINCT obj ");
//				sb.append("FROM ChequeHistorial obj ");
//				sb.append(hql);
//				sb.append(" and  exists ( from DetalleExtracto as det where det.conciliado = 'N' and TO_NUMBER(obj.cheque.numero) = TO_NUMBER(det.nroComprobante)) ");
				String sql = "SELECT DISTINCT  * " +
						" FROM T_VIS_FON_CHEQUES_HISTORIAL chequehist0_, " +
						" T_VIS_FON_ASIENTOS_ITEM asientoite1_ " +
						" WHERE chequehist0_.C_ID_ASIENTO_ITEM=asientoite1_.C_ID_ASIENTO_ITEM " +
						" AND asientoite1_.C_ID_PLAN_CUENTA   = " + idPlanCuenta +
						" AND (UPPER(CHEQUEHIST0_.C_CONCILIADO) LIKE 'N') " +
						" AND (EXISTS " +
							"(SELECT detalleext2_.C_ID_DETALLE_EXTRACTO " +
							"FROM T_VIS_FON_DETALLE_EXTRACTO detalleext2_, T_VIS_FON_CHEQUES cheque3_ " +
							"WHERE chequehist0_.C_ID_CHEQUE  =cheque3_.C_ID_CHEQUE " +
							"AND detalleext2_.C_CONCILIADO   ='N' " +
							"AND TO_NUMBER(CHEQUE3_.C_NUMERO)=TO_NUMBER(DETALLEEXT2_.C_NRO_COMPROBANTE) " +
						"))";
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.addEntity(ChequeHistorial.class);
				List list = (List)sqlQuery.list();
				Iterator iter = list.iterator();
				List listCheques = new ArrayList();
				while(iter.hasNext())
				{
					ChequeHistorial chequeHistorial = (ChequeHistorial)iter.next();
					
					chequeHistorial.getCheque().getId();
/*@I3918*/					if (chequeHistorial.getAsientoItem() != null)
					{
						chequeHistorial.getAsientoItem().getIdAsientoItem();
						chequeHistorial.getAsientoItem().getSigno();
/*@F3918*/					}
					listCheques.add(chequeHistorial);
				}
				return listCheques;
			}
		});
	}
/*@F3918*/

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


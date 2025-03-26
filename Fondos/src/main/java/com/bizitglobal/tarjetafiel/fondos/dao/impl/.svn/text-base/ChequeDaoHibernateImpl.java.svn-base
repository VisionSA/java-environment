package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.tarjetafiel.commons.paginacion2.ScrollPage;
import com.bizitglobal.tarjetafiel.fondos.dao.ChequeDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.BancoPropio;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;

public class ChequeDaoHibernateImpl extends HibernateDaoSupport implements ChequeDao  {
	private static final Logger log = Logger.getLogger(ChequeDaoHibernateImpl.class);

	private DataSource dataSource;
	private JdbcTemplate jt;
	public ChequeDaoHibernateImpl() {
		super();
	}

	public void grabarCheque (Cheque pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Cheque buscarCheque (Long id) {
		return (Cheque) this.getHibernateTemplate().get(Cheque.class, id);
	}
	public void borrarCheque (Long id) {
		borrarCheque(buscarCheque(id));
	}
	public void borrarCheque (Cheque pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarCheque (Cheque pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Cheque obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List<Cheque> list = query.list();
				return list;
			}
		});
	}
	
	public Cheque buscarChequePorNumero(final String numero, final String sucursal, final String codBanco, final String cuenta) {
		return (Cheque)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Cheque obj ");
				sb.append("Where to_number(numero) = to_number(:numero) AND " +
						"to_number(sucursalBanco) = to_number(:sucursal) AND " +
						"banco.codigo = to_number(:codBanco) AND " +
						"to_number(cuenta) = to_number(:cuenta)");
				
				Query query = session.createQuery(sb.toString());
				query.setParameter("numero", numero);
				query.setParameter("sucursal", sucursal);
				query.setParameter("codBanco", codBanco);
				query.setParameter("cuenta", cuenta);
				
				List<Cheque> list = query.list();

				Cheque newCheque = null;
				if(list.size()==1){
					Cheque cheque = null;
					cheque = list.get(0);

					newCheque = new Cheque();
					newCheque.setIdCheque(cheque.getIdCheque());
					newCheque.setNumero(cheque.getNumero());
					newCheque.setBanco(new Banco());
					newCheque.getBanco().setCodigo(cheque.getBanco().getCodigo());
					newCheque.getBanco().setDescripcion(cheque.getBanco().getDescripcion());
					newCheque.getBanco().setIdBanco(cheque.getBanco().getIdBanco());
					newCheque.setBeneficiario(cheque.getBeneficiario());
					newCheque.setChequeEstado(cheque.getChequeEstado());
					newCheque.setCodigoPostal(cheque.getCodigoPostal());
					newCheque.setEsCruzado(cheque.getEsCruzado());
					newCheque.setConciliado(cheque.getConciliado());
					newCheque.setCuenta(cheque.getCuenta());
					newCheque.setCodRed(cheque.getCodRed());
					newCheque.setTipo(cheque.getTipo());
					newCheque.setProcesado(cheque.getProcesado());
					newCheque.setImporte(cheque.getImporte());
					newCheque.setFechaPago(cheque.getFechaPago());
					newCheque.setFechaEmision(cheque.getFechaEmision());
					newCheque.setSucursalBanco(cheque.getSucursalBanco());
					newCheque.setCruzado(cheque.getCruzado());
					newCheque.setImprimir(cheque.getImprimir());
					newCheque.setNoOrden(cheque.getNoOrden());
					
					if(cheque.getBancoPropio() != null){
						newCheque.setBancoPropio(new BancoPropio());
						newCheque.getBancoPropio().setBanco(new Banco());
						newCheque.getBancoPropio().getBanco().setIdBanco(cheque.getBancoPropio().getBanco().getIdBanco());
						newCheque.getBancoPropio().setNumeroCuenta(cheque.getBancoPropio().getNumeroCuenta());
						newCheque.getBancoPropio().setIdBancoPropio(cheque.getBancoPropio().getIdBancoPropio());
					}

				}
								
				return newCheque;				
			}
		});
	}

	
	public Page listarTodosPage(Filtro filtro, final int pageNumber, final int pageSize) {
		final String hql = filtro.getHQL();
        return (Page)getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
            	StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Cheque obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
                return new ScrollPage(query, pageNumber, pageSize);
            }
        });
    }
	
	public Map obtenerUpload(String listINidCheque ) {
		Map result = new HashMap();
		StringBuffer sql = new StringBuffer(100);
		
		sql.append("SELECT ch.C_ID_CHEQUE ID_CHEQUE,");
		sql.append("  '*M*' REGISTRO,");
		sql.append("  LPAD(ch.C_ID_BANCO, 3, '0') COD_BANCO,");
		sql.append("  LPAD(ch.C_ID_TIPO_CTA_BANCO, 2, '0') TIPO_CTA,");
		sql.append("  SUBSTR(ch.C_CUENTA || '                 ', 0,17) NUM_CTA,");
		sql.append("  REPLACE(REPLACE(to_char(ch.C_IMPORTE, '99999999999999.99'), '.', ''), ' ', '0') IMPORTE,");
		sql.append("  LPAD('#' ||ch.C_ID_CHEQUE || '#', 60, ' ') OBS,");
		sql.append("  '                                          000000000000            0000000000           '|| LPAD(NVL(ch.c_cbu, ' '), 22, ' ') ||'                             ' CBU");
		sql.append(" FROM T_VIS_FON_CHEQUES ch");
		sql.append(" WHERE ch.C_ID_CHEQUE        IN ("+ listINidCheque +")");
		sql.append(" ORDER BY ch.C_ID_CHEQUE");

		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();        

		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			Long id = new Long(map.get("ID_CHEQUE").toString());
			StringBuffer lineaSICORE = new StringBuffer(240);
			lineaSICORE.append(map.get("REGISTRO"));
			lineaSICORE.append(map.get("COD_BANCO"));
			lineaSICORE.append(map.get("TIPO_CTA"));
			lineaSICORE.append(map.get("NUM_CTA"));
			lineaSICORE.append(map.get("IMPORTE"));
			lineaSICORE.append(map.get("OBS"));
			lineaSICORE.append(map.get("CBU"));
			result.put(id, lineaSICORE);			
		}
		
		return result;
	}
	
	public void actualizarTodosProcesados(String listINidCheque) {
		StringBuffer sql = new StringBuffer(100);
		
		sql.append("UPDATE T_VIS_FON_CHEQUES SET C_PROCESADO = 'S' ");
		sql.append(" WHERE C_ID_CHEQUE IN ("+ listINidCheque +")");

		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sql.toString());
	}

//	public void actualizarAcreditacionesConciliadas(String listINidCheque) {
//		StringBuffer sql = new StringBuffer(100);
//		
//		if(!listINidCheque.isEmpty())
//		{
//	
//			sql.append(" UPDATE T_VIS_FON_CHEQUES ch SET ch.C_CONCILIADO = 'C' "); 
//			sql.append(" WHERE ch.C_TIPO='A' "); 
//			sql.append(" AND exists (select * from T_VIS_FON_ACREDIT_DETALLE ac where ac.c_id_cheque=ch.c_id_cheque) ");
//			
//			log.info("SQL Ejecutado ==> " + sql.toString());
//			jt = new JdbcTemplate(dataSource);
//			jt.execute(sql.toString());
//		}
//	}
	
	public Long actualizarAcreditacionesConciliadas() {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

/*@I3918*/				Query query = session.createQuery("Update Cheque ch set ch.numero=(select objAcre.nroTransaccion from AcreditacionFondoDetalle as objAcre where objAcre.idCheque = ch.idCheque and rownum=1) where  exists ( from AcreditacionFondoDetalle as objAcre where objAcre.idCheque = ch.idCheque ) ");/*@F3918*/ 
				int result = query.executeUpdate();
				return new Long(result);
			}
		});
	}
	
	/**
	 * @param filtro Debe contener el tipo, la fecha limite y si es necesario el banco propio
	 * @return la cantidad de registros que coinciden con el filtro y estan pendientes de proceso
	 */
	public Long contarChequePendiente (final Filtro filtro ) {
		filtro.agregarCampoOperValor("procesado", Filtro.LIKEXS, "N");
		return (Long) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("Select count(obj) From Cheque obj " + filtro.getHQL());
				return query.uniqueResult();
			}
		});
	}	
	
	public List getChequesByParam(Filtro filtro){
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Cheque obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List<Cheque> list = query.list();
				
				
				List<Cheque> listaCheque = new ArrayList<Cheque>();
				
				Cheque newCheque = null;
				
				for(Cheque cheque : list){
					newCheque = new Cheque();
					newCheque.setIdCheque(cheque.getIdCheque());
					newCheque.setNumero(cheque.getNumero());
					newCheque.setBanco(new Banco());
					newCheque.getBanco().setCodigo(cheque.getBanco().getCodigo());
					newCheque.getBanco().setDescripcion(cheque.getBanco().getDescripcion());
					newCheque.getBanco().setIdBanco(cheque.getBanco().getIdBanco());
					newCheque.setBeneficiario(cheque.getBeneficiario());
					newCheque.setChequeEstado(cheque.getChequeEstado());
					newCheque.setCodigoPostal(cheque.getCodigoPostal());
					newCheque.setEsCruzado(cheque.getEsCruzado());
					newCheque.setConciliado(cheque.getConciliado());
					newCheque.setCuenta(cheque.getCuenta());
					newCheque.setCodRed(cheque.getCodRed());
					newCheque.setTipo(cheque.getTipo());
					newCheque.setProcesado(cheque.getProcesado());
					newCheque.setImporte(cheque.getImporte());
					newCheque.setFechaPago(cheque.getFechaPago());
					newCheque.setFechaEmision(cheque.getFechaEmision());
					newCheque.setSucursalBanco(cheque.getSucursalBanco());
					newCheque.setCruzado(cheque.getCruzado());
					newCheque.setImprimir(cheque.getImprimir());
					newCheque.setNoOrden(cheque.getNoOrden());
					
					if(cheque.getBancoPropio() != null){
						newCheque.setBancoPropio(new BancoPropio());
						newCheque.getBancoPropio().setBanco(new Banco());
						newCheque.getBancoPropio().getBanco().setIdBanco(cheque.getBancoPropio().getBanco().getIdBanco());
						newCheque.getBancoPropio().setNumeroCuenta(cheque.getBancoPropio().getNumeroCuenta());
						newCheque.getBancoPropio().setIdBancoPropio(cheque.getBancoPropio().getIdBancoPropio());
					}
					listaCheque.add(newCheque);
				}

				return listaCheque;
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


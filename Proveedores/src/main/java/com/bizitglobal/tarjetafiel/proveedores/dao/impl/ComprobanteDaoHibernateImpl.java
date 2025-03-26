package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.ComprobanteDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Comprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;

public class ComprobanteDaoHibernateImpl extends HibernateDaoSupport implements ComprobanteDao {

	private static final Logger log = Logger.getLogger(ComprobanteDaoHibernateImpl.class);
	private DataSource dataSource;
	private JdbcTemplate jt;	
	private Connection con;
	private CallableStatement cs;
	
	public ComprobanteDaoHibernateImpl() {
		super();
	}

	public void grabarComprobante(Comprobante unComprobante) {
		this.getHibernateTemplate().save(unComprobante);
	}	

	public Comprobante buscarComprobante(Long id) {
		return (Comprobante) this.getHibernateTemplate().get(Comprobante.class,id);
	}
	
	public void borrarComprobante(Long id) {
		borrarComprobante(buscarComprobante(id));
	}	

	public void borrarComprobante(Comprobante unComprobante) {
		this.getHibernateTemplate().delete(unComprobante);
	}

	public List listarTodos(Filtro filtro) {
		final String hqlFiltro = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Comprobante obj ");
				sb.append(hqlFiltro);
				sb.append(" ORDER BY obj.idComprobante ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarComprobante(Comprobante unComprobante) {
		this.getHibernateTemplate().update(unComprobante);
	}
	public List buscarNro(Integer nroCorto, Integer nroLargo, Proveedor proveedor) {
		final String nCorto = nroCorto.toString();
		final String nLargo = nroLargo.toString();
		final String idProv = proveedor.getIdProveedor().toString();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

			StringBuffer sb = new StringBuffer(100);
			sb.append("SELECT DISTINCT comprobante.idComprobante ");
			sb.append("FROM Comprobante comprobante ");
			sb.append("WHERE comprobante.nroCorto = " + nCorto);
			sb.append(" AND comprobante.nroLargo = " + nLargo);
			sb.append(" AND comprobante.proveedor.idProveedor = " + idProv);
			
			Query query = session.createQuery(sb.toString());
			List list = query.list();

			return list;
		}
	});
	}
	
	public Integer leerNroTipo(String tipo) {
		final String dTipo = tipo;
		List resuList;
		resuList = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

			StringBuffer sb = new StringBuffer(100);
			sb.append("SELECT MAX(comprobante.nroLargo) ");
			sb.append("FROM Comprobante comprobante ");
			sb.append("WHERE comprobante.tipoComprobante.descripcionCorta = '" + dTipo + "'") ;
						
			Query query = session.createQuery(sb.toString());
			List list = query.list();

			return list;
			}
		});
		if (resuList.get(0) == null) {
			return new Integer(0);
		}else {
			return (Integer)resuList.get(0);
		}
	}
	
	
	public void generarPrintFormaPago(Long idOperador, Long nroOP) throws SQLException {		
//		StringBuffer sql = new StringBuffer(100);
		con = dataSource.getConnection();
		cs = con.prepareCall("{call sp_get_prov_forma_pago(?,?)}");
		cs.setLong(1, nroOP.longValue());
		cs.setLong(2, idOperador.longValue());
		cs.execute();
//		String sql ="{call nombrePaquete.procedimiento (?,?,?,?)}";
//		sql.append("EXECUTE FUNCTION sp_get_prov_forma_pago("+nroOP+", "+idOperador+")");

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

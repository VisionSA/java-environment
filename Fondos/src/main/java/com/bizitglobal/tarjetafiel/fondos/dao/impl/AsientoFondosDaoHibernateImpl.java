package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.bizitglobal.tarjetafiel.contabilidad.dao.impl.AsientoDaoHibernateImpl;
import com.bizitglobal.tarjetafiel.fondos.dao.AsientoFondosDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoFondos;

public class AsientoFondosDaoHibernateImpl extends HibernateDaoSupport implements AsientoFondosDao  {
	private Logger log = Logger.getLogger(AsientoFondosDaoHibernateImpl.class);
	
	
	private DataSource dataSource;
	private JdbcTemplate jt;
	public AsientoFondosDaoHibernateImpl() {
		super();
	}

	public void grabarAsiento (AsientoFondos pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public AsientoFondos buscarAsiento (Long id) {
		return (AsientoFondos) this.getHibernateTemplate().get(AsientoFondos.class, id);
	}
	public void borrarAsiento (Long id) {
		borrarAsiento(buscarAsiento(id));
	}
	public void borrarAsiento (AsientoFondos pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarAsiento (AsientoFondos pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Asiento obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	public double saldoContableRealAFecha(Long idPlanCuenta, Date fechaHasta)
	{
		StringBuffer sql = new StringBuffer(100);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		
		sql.append("  SELECT NVL(sum(item.c_importe * item.c_signo),0)saldo FROM t_vis_fon_asientos_item item ");
		sql.append("  join t_vis_fon_asientos asi on asi.c_id_asiento = item.c_id_asiento ");
		sql.append("  WHERE c_id_plan_cuenta = " +  idPlanCuenta + " " );
		sql.append("  and trunc(asi.c_fecha) <= TO_DATE('" + simpleDateFormat.format(fechaHasta) + "','DD/MM/YYYY') ");
		
		
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


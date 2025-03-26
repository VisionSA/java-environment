package com.bizitglobal.tarjetafiel.fondos.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
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
import com.bizitglobal.tarjetafiel.fondos.negocio.RenglonLibroMayor;
import com.bizitglobal.tarjetafiel.fondos.dao.LibroMayorFondosDao;


public class LibroMayorFondosDaoHibernateImpl extends HibernateDaoSupport implements  LibroMayorFondosDao{
	
	private Logger log = Logger.getLogger(LibroMayorFondosDaoHibernateImpl.class);
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	
	
	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Ejercicio obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}


	public List listarTodosFondos(Date inicio, Date cierre, Long idCuenta,Date inicioEjercicio) {
		
		List result = new LinkedList();
		Calendar fechaInicio = Calendar.getInstance();
		fechaInicio.setTime(inicio);
		
		fechaInicio.add(fechaInicio.DAY_OF_MONTH, -1);
		Calendar fechaFin = Calendar.getInstance();
		fechaFin.setTime(cierre);
		fechaFin.add(fechaFin.DAY_OF_MONTH, +1);
		StringBuffer sql = new StringBuffer(200);
		sql.append("SELECT "+RenglonLibroMayor.FECHA+", "+RenglonLibroMayor.FECHA_CONTABILIZADO+", "+RenglonLibroMayor.CABECERA_FONDOS+".c_id_asiento, ");
		sql.append(RenglonLibroMayor.LEYENDA+", "+RenglonLibroMayor.IMPORTE+", "+RenglonLibroMayor.SIGNO);
		sql.append(" FROM "+RenglonLibroMayor.CABECERA_FONDOS +" join "+ RenglonLibroMayor.RENGLON_FONDOS +" on ");
		sql.append(RenglonLibroMayor.CABECERA_FONDOS+".c_id_asiento  = "+RenglonLibroMayor.RENGLON_FONDOS+".c_id_asiento ");
		sql.append(" WHERE TRUNC(" + RenglonLibroMayor.FECHA + ") > " + Filtro.getTO_DATE(new Timestamp(fechaInicio.getTime().getTime())));
		sql.append(" AND TRUNC(" + RenglonLibroMayor.FECHA + ") < " + Filtro.getTO_DATE(new Timestamp(fechaFin.getTime().getTime())));
		sql.append(" AND " + RenglonLibroMayor.CUENTA_FONDOS + " = " + idCuenta);
		sql.append(" ORDER BY c_fecha");
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		Iterator iter = jt.queryForList(sql.toString()).iterator();
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/mm/yyyy") ; 
		Date auxDate = null;
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			RenglonLibroMayor renglonLibroMayor = new RenglonLibroMayor();
			if(map.get(RenglonLibroMayor.ID_ASIENTO_FONDOS)!=null)
			  renglonLibroMayor.setAsiento(new Long(map.get(RenglonLibroMayor.ID_ASIENTO_FONDOS).toString()));
			auxDate = (Date)map.get(RenglonLibroMayor.FECHA);
			try {
				renglonLibroMayor.setFechaCarga(sdf.parse(sdf.format(auxDate)));
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			renglonLibroMayor.setFechaContab((Date)map.get(RenglonLibroMayor.FECHA_CONTAB));
			renglonLibroMayor.setLeyenda(map.get(RenglonLibroMayor.LEYENDA).toString());
			renglonLibroMayor.setImporteCadena(map.get(RenglonLibroMayor.IMPORTE).toString());
			if(map.get(RenglonLibroMayor.SIGNO)!=null){
				renglonLibroMayor.setSigno((map.get(RenglonLibroMayor.SIGNO).toString().compareTo("-1")==0)? "C":"D");
			}
			RenglonLibroMayor aux = null;
			if (((LinkedList)result).size()!=0) {
				aux = (RenglonLibroMayor)((LinkedList)result).getLast();
			}
			BigDecimal saldoAc = null;
			if (aux!=null) {
				saldoAc = (aux).getSaldoAcumulado();
			} else {
				saldoAc= getSaldoAC(inicio, idCuenta);
			} 
			renglonLibroMayor.calcularDebeHaber(saldoAc);
			((LinkedList)result).addLast(renglonLibroMayor);
			
		}
		return result;
	}
	public BigDecimal getSaldoAC(Date fin, Long idCuenta) {
	StringBuffer sql = new StringBuffer(200);
	sql.append(" SELECT SUM(ITEM.C_IMPORTE * ITEM.C_SIGNO)");
	sql.append(" FROM T_VIS_FON_ASIENTOS_ITEM ITEM");
	sql.append(" INNER JOIN T_VIS_FON_ASIENTOS ASI ON asi.c_id_asiento = item.c_id_asiento");
	sql.append(" WHERE ITEM.C_ID_PLAN_CUENTA = " + idCuenta);
	sql.append("  AND ASI.C_FECHA < " + Filtro.getTO_DATE(new Timestamp(fin.getTime())));
	
	log.info("SQL Ejecutado ==> " + sql.toString());
	jt = new JdbcTemplate(dataSource);
	return new BigDecimal(jt.queryForLong(sql.toString()));
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}


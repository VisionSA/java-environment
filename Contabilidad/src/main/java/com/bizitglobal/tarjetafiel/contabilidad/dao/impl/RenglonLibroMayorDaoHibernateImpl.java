package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.RenglonLibroMayorDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.RenglonLibroMayor;

public class RenglonLibroMayorDaoHibernateImpl implements RenglonLibroMayorDao {
	private Logger log = Logger.getLogger(RenglonLibroMayorDaoHibernateImpl.class);
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	public List listarTodos(Long idEmpresa, Long idEjercicion, Date inicio, Date cierre, Long idCuenta, Date inicioEjercicio) {
		
		
		List result = new LinkedList();
		StringBuffer sql = new StringBuffer(200);
		sql.append("SELECT * FROM " + RenglonLibroMayor.RENGLON);
		sql.append(" WHERE " + RenglonLibroMayor.EJERCICIO + " = " + idEjercicion);
		sql.append(" AND " + RenglonLibroMayor.EMPRESA + " = " + idEmpresa);
		sql.append(" AND " + RenglonLibroMayor.FECHA_CONTAB + " >= " + Filtro.getTO_DATE(new Timestamp(inicio.getTime())));
		sql.append(" AND " + RenglonLibroMayor.FECHA_CONTAB + " <= " + Filtro.getTO_DATE(new Timestamp(cierre.getTime())));
		sql.append(" AND " + RenglonLibroMayor.CUENTA + " = " + idCuenta);
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			RenglonLibroMayor renglonLibroMayor = new RenglonLibroMayor();
			renglonLibroMayor.setAsiento(new Long(map.get(RenglonLibroMayor.ID_ASIENTO).toString()));
			renglonLibroMayor.setFechaCarga((Date)map.get(RenglonLibroMayor.FECHA_CARGA));
			renglonLibroMayor.setFechaContab((Date)map.get(RenglonLibroMayor.FECHA_CONTAB));
			renglonLibroMayor.setLeyenda(map.get(RenglonLibroMayor.LEYENDA).toString());
			renglonLibroMayor.setImporteCadena(map.get(RenglonLibroMayor.IMPORTE).toString());
			renglonLibroMayor.setSigno(map.get(RenglonLibroMayor.SIGNO).toString());
			RenglonLibroMayor aux = null;
			if (((LinkedList)result).size()!=0) {
				aux = (RenglonLibroMayor)((LinkedList)result).getLast();
			}
			BigDecimal saldoAc = null;
			if (aux!=null) {
				saldoAc = (aux).getSaldoAcumulado();
			} else {
				saldoAc = new BigDecimal(getSaldoInicial(idEmpresa,idEjercicion, inicioEjercicio, inicio, idCuenta).longValue());
			}
			renglonLibroMayor.calcularDebeHaber(saldoAc);
			((LinkedList)result).addLast(renglonLibroMayor);
		}
		return result;
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
		///tomamos los contabilizados o los no conta?//////////// y en concec en los filtros la fecha o fecha contab??
		sql.append(" WHERE " + RenglonLibroMayor.FECHA + " > " + Filtro.getTO_DATE(new Timestamp(fechaInicio.getTime().getTime())));
		sql.append(" AND " + RenglonLibroMayor.FECHA + " < " + Filtro.getTO_DATE(new Timestamp(fechaFin.getTime().getTime())));
		sql.append(" AND " + RenglonLibroMayor.CUENTA_FONDOS + " = " + idCuenta);
		log.info("SQL Ejecutado ==> " + sql.toString());
		String x= sql.toString();
		System.out.println(x);
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			RenglonLibroMayor renglonLibroMayor = new RenglonLibroMayor();
			if(map.get(RenglonLibroMayor.ID_ASIENTO_FONDOS)!=null)
			  renglonLibroMayor.setAsiento(new Long(map.get(RenglonLibroMayor.ID_ASIENTO_FONDOS).toString()));
			renglonLibroMayor.setFechaCarga((Date)map.get(RenglonLibroMayor.FECHA));
			renglonLibroMayor.setFechaContab((Date)map.get(RenglonLibroMayor.FECHA_CONTAB));
			renglonLibroMayor.setLeyenda(map.get(RenglonLibroMayor.LEYENDA).toString());
			renglonLibroMayor.setImporteCadena(map.get(RenglonLibroMayor.IMPORTE).toString());
			if(map.get(RenglonLibroMayor.SIGNO)!=null){
				renglonLibroMayor.setSigno((map.get(RenglonLibroMayor.SIGNO).toString().compareTo("-1")==0)? "D":"C");
			}
			RenglonLibroMayor aux = null;
			if (((LinkedList)result).size()!=0) {
				aux = (RenglonLibroMayor)((LinkedList)result).getLast();
			}
			BigDecimal saldoAc = null;
			if (aux!=null) {
				saldoAc = (aux).getSaldoAcumulado();
			} else {
	            /////aca tambien hay q sumarle el saldo acumulado que tiene la cuenta hasta el inicio del ejercicio
				// mas proximo a  la fecha inicial del filtro
				//saldoAc = new BigDecimal(getSaldoInicial(inicioEjercicio, inicio, idCuenta).longValue());
				//saldoAc= new BigDecimal(25);
				saldoAc= getSaldoAC(inicio, idCuenta);
			} 
			renglonLibroMayor.calcularDebeHaber(saldoAc);
			((LinkedList)result).addLast(renglonLibroMayor);
		}
		return result;
	}
	/***************************************************/
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
	/***************************************************/
	public Long getSaldoInicial(Long idEmpresa, Long idEjercicion, Date inicioEjercicio, Date inicio, Long idCuenta) {
		Long result = null;
		Long haber, debe;
		StringBuffer sql = new StringBuffer(200);
		sql.append("SELECT SUM(" + RenglonLibroMayor.IMPORTE + ") FROM " + RenglonLibroMayor.RENGLON);
		sql.append(" WHERE " + RenglonLibroMayor.EJERCICIO + " = " + idEjercicion);
		sql.append(" AND " + RenglonLibroMayor.EMPRESA + " = " + idEmpresa);
		sql.append(" AND " + RenglonLibroMayor.FECHA_CONTAB + " >= " + Filtro.getTO_DATE(new Timestamp(inicioEjercicio.getTime())));
		sql.append(" AND " + RenglonLibroMayor.FECHA_CONTAB + " < " + Filtro.getTO_DATE(new Timestamp(inicio.getTime())));
		sql.append(" AND " + RenglonLibroMayor.CUENTA + " = " + idCuenta);
		sql.append(" AND " + RenglonLibroMayor.SIGNO + " LIKE 'C'");
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		haber = new Long(jt.queryForLong(sql.toString()));
		
		sql = new StringBuffer(200);
		sql.append("SELECT SUM(" + RenglonLibroMayor.IMPORTE + ") FROM " + RenglonLibroMayor.RENGLON);
		sql.append(" WHERE " + RenglonLibroMayor.EJERCICIO + " = " + idEjercicion);
		sql.append(" AND " + RenglonLibroMayor.EMPRESA + " = " + idEmpresa);
		sql.append(" AND " + RenglonLibroMayor.FECHA_CONTAB + " >= " + Filtro.getTO_DATE(new Timestamp(inicioEjercicio.getTime())));
		sql.append(" AND " + RenglonLibroMayor.FECHA_CONTAB + " < " + Filtro.getTO_DATE(new Timestamp(inicio.getTime())));
		sql.append(" AND " + RenglonLibroMayor.CUENTA + " = " + idCuenta);
		sql.append(" AND " + RenglonLibroMayor.SIGNO + " LIKE 'D'");
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		debe = new Long(jt.queryForLong(sql.toString()));
		result = new Long(haber.longValue() - debe.longValue());
		return result;
	}
	
	
	
	public Long getSaldoInicial(Date inicioEjercicio, Date inicio, Long idCuenta) {
		
	////esto tiene que estar contabilizado????????????????????????????????	No!!!!!!!
		Long result = null;
		Long haber, debe;
		StringBuffer sql = new StringBuffer(200);		
/*		sql.append("SELECT SUM(" + RenglonLibroMayor.IMPORTE_FONDOS + ")" );
		sql.append(" FROM t_vis_fon_asientos asi join t_vis_fon_asientos_item asiItm on");
		sql.append(" asi.c_id_asiento = asiItm.c_id_asiento");
		sql.append(" WHERE " + RenglonLibroMayor.FECHA + " >= " + Filtro.getTO_DATE(new Timestamp(inicioEjercicio.getTime())));
		sql.append(" AND " + RenglonLibroMayor.FECHA + " < " + Filtro.getTO_DATE(new Timestamp(inicio.getTime())));
		sql.append(" AND " + RenglonLibroMayor.CUENTA_FONDOS + " = " + idCuenta);
		sql.append(" AND " + RenglonLibroMayor.SIGNO_FONDOS + " =-1");
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		haber = new Long(jt.queryForLong(sql.toString()));
		
		sql = new StringBuffer(200);
		sql.append("SELECT SUM(" + RenglonLibroMayor.IMPORTE_FONDOS + ")" );
		sql.append(" FROM t_vis_fon_asientos asi join t_vis_fon_asientos_item asiItm on");
		sql.append(" asi.c_id_asiento = asiItm.c_id_asiento");
		sql.append(" WHERE " + RenglonLibroMayor.FECHA + " >= " + Filtro.getTO_DATE(new Timestamp(inicioEjercicio.getTime())));
		sql.append(" AND " + RenglonLibroMayor.FECHA + " < " + Filtro.getTO_DATE(new Timestamp(inicio.getTime())));
		sql.append(" AND " + RenglonLibroMayor.CUENTA_FONDOS + " = " + idCuenta);
		sql.append(" AND " + RenglonLibroMayor.SIGNO_FONDOS + " <>-1");
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		debe = new Long(jt.queryForLong(sql.toString()));
		result = new Long(haber.longValue() - debe.longValue());
		
		//sumarle result el valor de las queries de abajo
		
		/*
			//obtenemos el ejercicio en el que cae el limite inf del filtro
		select  * from 
		t_cont_ejercicios 
		where  c_fecha_inicio <  TO_DATE('05/05/2007','%d/%m/%Y')
		and c_estado ='C'
		and  c_fecha_inicio >=all (select  c_fecha_inicio from 
		                            t_cont_ejercicios 
		                            where  c_fecha_inicio <  TO_DATE('05/05/2007','%d/%m/%Y'))
		
		
		/////otra fma de hacerlo es y  tomamos el primer registro
		select  * from 
		t_cont_ejercicios 
		where  c_fecha_inicio <  TO_DATE('05/05/2007','%d/%m/%Y')
		and c_estado ='C'
		order by  c_fecha_inicio desc
		
		////obtenemos ahora lo acumulado hasta ese ejercicio
		
		select  det.c_signo, det.c_importe,det.c_numero_imputa
		from   t_cont_asientos_c cab, t_cont_asientos_d det
		where  cab.c_empresa = det.c_empresa and
		       cab.c_asiento = det.c_asiento and
		       cab.c_tipo_asiento = 4 and  //4 constante 
		       cab.c_ejercicio = 8  and
		       det.c_numero_imputa =13 

       */

		
		
		return result;
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
       

   
   	
	
	
	
	



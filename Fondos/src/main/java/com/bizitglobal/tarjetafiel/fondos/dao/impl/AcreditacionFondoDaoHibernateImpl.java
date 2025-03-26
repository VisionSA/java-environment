package com.bizitglobal.tarjetafiel.fondos.dao.impl;

import java.sql.SQLException;
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
import com.bizitglobal.tarjetafiel.fondos.dao.AcreditacionFondoDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.AsientoFondos;
import com.bizitglobal.tarjetafiel.fondos.negocio.dto.AcreditacionFondoDTO;

public class AcreditacionFondoDaoHibernateImpl extends HibernateDaoSupport implements AcreditacionFondoDao {

	
	private static final Logger log = Logger.getLogger(AcreditacionFondoDaoHibernateImpl.class);

	private DataSource dataSource;
	private JdbcTemplate jt;
	
	public AcreditacionFondoDaoHibernateImpl() {
		super();
	}
	
	@Override
	public void actualizarAcreditacionFondo(AcreditacionFondo object) {
		this.getHibernateTemplate().update(object);
	}

	@Override
	public void borrarAcreditacionFondo(Long id) {
		borrarAcreditacionFondo(buscarAcreditacionFondo(id));
	}

	@Override
	public void borrarAcreditacionFondo(AcreditacionFondo object) {
		this.getHibernateTemplate().delete(object);
	}

	@Override
	public AcreditacionFondo buscarAcreditacionFondo(Long id) {
		return (AcreditacionFondo) this.getHibernateTemplate().get(AcreditacionFondo.class, id);
	}

	@Override
	public void grabarAcreditacionFondo(AcreditacionFondo object) {
		this.getHibernateTemplate().save(object);
	}

	@Override
	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM AcreditacionFondo obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	public List getUltimasAcreditacionesCargadas(){
		StringBuffer sql = new StringBuffer(100);

		sql.append("Select ac.c_id_banco,max(b.c_descripcion)banco,  max(to_number(ac.c_fecha_cadena))fechaCadenaMaxima, "); 
		sql.append("max(to_date(ac.c_fecha_cadena, 'YYYYMMDD'))fechaMaxima ");
		sql.append("from t_vis_fon_acreditaciones ac join t_vis_gen_bancos b on (ac.c_id_banco=b.c_id_banco) ");
		sql.append("group by ac.c_id_banco ");
		sql.append("order by max(ac.c_fecha_cadena) ");
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		List listAcreditacions = new ArrayList();
		while(iter.hasNext()){
			Map map = (Map) iter.next();
			
			AcreditacionFondoDTO fondoDTO = new AcreditacionFondoDTO();
			fondoDTO.setBanco(map.get("banco").toString());
			fondoDTO.setIdBanco(new Long(map.get("c_id_banco").toString()));
			fondoDTO.setFechaCargaMaxima((Date)map.get("fechaMaxima"));
			fondoDTO.setFechaCargaMaximaCadena(map.get("fechaCadenaMaxima").toString());
			
			listAcreditacions.add(fondoDTO);
		}
		
		return listAcreditacions;
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

package com.bizitglobal.tarjetafiel.fondos.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.bizitglobal.tarjetafiel.commons.paginacion.HibernatePage;
import com.bizitglobal.tarjetafiel.commons.paginacion.Paginador;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.tarjetafiel.commons.paginacion2.ScrollPage;
import com.bizitglobal.tarjetafiel.fondos.dao.AcreditacionFondoDetalleDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondoDetalle;

public class AcreditacionFondoDetalleDaoHibernateImpl extends HibernateDaoSupport implements AcreditacionFondoDetalleDao {

	private static final Logger log = Logger.getLogger(AcreditacionFondoDetalleDaoHibernateImpl.class);
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	public AcreditacionFondoDetalleDaoHibernateImpl() {
		super();
	}
	
	@Override
	public void actualizarAcreditacionFondoDetalle(AcreditacionFondoDetalle object) {
		this.getHibernateTemplate().update(object);
	}

	@Override
	public void borrarAcreditacionFondoDetalle(Long id) {
		borrarAcreditacionFondoDetalle(buscarAcreditacionFondoDetalle(id));
	}

	@Override
	public void borrarAcreditacionFondoDetalle(AcreditacionFondoDetalle object) {
		this.getHibernateTemplate().delete(object);
	}

	@Override
	public AcreditacionFondoDetalle buscarAcreditacionFondoDetalle(Long id) {
		return (AcreditacionFondoDetalle) this.getHibernateTemplate().get(AcreditacionFondoDetalle.class, id);
	}

	@Override
	public void grabarAcreditacionFondoDetalle(AcreditacionFondoDetalle object) {
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
				sb.append("FROM AcreditacionFondoDetalle obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	public Page listarTodosPage(Filtro filtro, final int pageNumber, final int pageSize) {
		final String hql = filtro.getHQL();
        return (Page)getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
            	StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT obj ");
				sb.append("FROM AcreditacionFondoDetalle obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
                return new ScrollPage(query, pageNumber, pageSize);
            }
        });
    }
	
	public List listarTodosPagina(Filtro filtro, final Paginador paginador) {

		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT obj ");
				sb.append("FROM AcreditacionFondoDetalle obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				HibernatePage hibernatePage = HibernatePage.getHibernatePageInstance(query, paginador.getPagina(), paginador.getCantidadRegistros());
				List list = hibernatePage.getThisPageElements();
				paginador.setCantidadPaginas(hibernatePage.getLastPageNumber());
				paginador.setCantidadRegistros(hibernatePage.getThisPageLastElementNumber());
				paginador.setPagina(hibernatePage.getPageNumber());
				
				return list;
			}
		});
	}

	public List buscarRangoConDatos(Long minFecha, Long maxFecha, Long idBanco){
		StringBuffer sql = new StringBuffer(100);
		sql.append(" select C_FECHA_SOLICITUD_CADENA, count(*)registros ");
		sql.append(" from t_vis_fon_acredit_detalle ");
		sql.append(" where C_ID_BANCO = " + idBanco + " ");
		sql.append(" group by C_FECHA_SOLICITUD_CADENA ");
		sql.append(" having TO_NUMBER(C_FECHA_SOLICITUD_CADENA) >= " + minFecha + " and TO_NUMBER(C_FECHA_SOLICITUD_CADENA) <= " + maxFecha + " ");
		sql.append(" order by TO_NUMBER(C_FECHA_SOLICITUD_CADENA) ");
		
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		List rows = jt.queryForList(sql.toString());
		Iterator iter = rows.iterator();
		List result = new ArrayList();
		while(iter.hasNext()){
			Map map = (Map) iter.next();
			if (map.get("C_FECHA_SOLICITUD_CADENA")!=null) {
				result.add(new Long(map.get("C_FECHA_SOLICITUD_CADENA").toString()));
			}
		}

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

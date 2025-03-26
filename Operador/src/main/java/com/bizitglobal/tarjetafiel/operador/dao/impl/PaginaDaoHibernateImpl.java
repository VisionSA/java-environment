package com.bizitglobal.tarjetafiel.operador.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.dao.*;
import com.bizitglobal.tarjetafiel.operador.negocio.*;


/**
 *	Esta clase implementa la interface de acceso a datos para el objeto cliente.
 */
public class PaginaDaoHibernateImpl extends HibernateDaoSupport implements PaginaDao {

	/**
	 * Constructor por defecto.
	 */
	public PaginaDaoHibernateImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.acceso.dao.PaginaDao#savePagina(com.bizitglobal.tarjetafiel.acceso.negocio.Pagina)
	 */
	public void savePagina(Pagina unPagina) {
		this.getHibernateTemplate().save(unPagina);
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.PaginaDao#findPagina(java.lang.Integer)
	 */
	public Pagina findPagina(Long id) {
		return (Pagina) this.getHibernateTemplate().get(Pagina.class,id);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.PaginaDao#deletePagina(java.lang.Integer)
	 */
	public void deletePagina(Long id) {
		deletePagina(findPagina(id));
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.PaginaDao#deletePagina(com.bizitglobal.tajetafiel.acceso.negocio.Pagina)
	 */
	public void deletePagina(Pagina unPagina) {
		this.getHibernateTemplate().delete(unPagina);
	}

	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.PaginaDao#updatePagina(com.bizitglobal.tajetafiel.acceso.negocio.Pagina)
	 */
	public void updatePagina(Pagina unPagina) {
		this.getHibernateTemplate().update(unPagina);
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tajetafiel.acceso.dao.PaginaDao#listAll()
	 */
	public List listAll() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT pagina ");
				sb.append("FROM Pagina pagina ");
				sb.append("ORDER BY pagina.pagina ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public List listAll(Filtro filtro) {
		final String hqlFiltro = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Pagina obj ");
				sb.append(hqlFiltro);
				sb.append(" ORDER BY obj.idPagina ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}	
	
}

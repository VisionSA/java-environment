package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.TipoAsientoDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.TipoAsiento;



public class TipoAsientoDaoHibernateImpl  extends HibernateDaoSupport implements TipoAsientoDao {
	


	public void grabarTipoAsiento(TipoAsiento pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public TipoAsiento buscarTipoAsiento(Long id) {
		return (TipoAsiento)	this.getHibernateTemplate().get(TipoAsiento.class, id);
	}
    public void borrarTipoAsiento(Long id) {
        this.getHibernateTemplate().delete(buscarTipoAsiento(id));
     }
    public void borrarTipoAsiento(TipoAsiento pObject) {
   	 this.getHibernateTemplate().delete(pObject);
  	}
	public void actualizarTipoAsiento(TipoAsiento pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM TipoAsiento obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	public TipoAsiento leerTipoAsiento(Long id) {
		
		return null;
	}
}
       

   
   	
	
	
	
	



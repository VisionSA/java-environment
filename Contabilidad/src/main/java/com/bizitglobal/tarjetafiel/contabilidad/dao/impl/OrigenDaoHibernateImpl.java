package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.OrigenDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Origen;



public class OrigenDaoHibernateImpl  extends HibernateDaoSupport implements OrigenDao {



	public void grabarOrigen(Origen pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Origen buscarOrigen(Long id) {
		return (Origen)	this.getHibernateTemplate().get(Origen.class, id);
	}
    public void borrarOrigen(Long id) {
        this.getHibernateTemplate().delete(buscarOrigen(id));
     }
    public void borrarOrigen(Origen pObject) {
   	 this.getHibernateTemplate().delete(pObject);
  	}
	public void actualizarOrigen(Origen pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Origen obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	public Origen leerOrigen(Long id) {
		
		return null;
	}
}
       

   
   	
	
	
	
	



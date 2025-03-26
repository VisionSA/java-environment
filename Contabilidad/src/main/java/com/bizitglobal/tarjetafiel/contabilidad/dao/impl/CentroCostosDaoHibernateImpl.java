package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.CentroCostosDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.CentroCostos;



public class CentroCostosDaoHibernateImpl  extends HibernateDaoSupport implements CentroCostosDao {



	public void grabarCentroCostos(CentroCostos pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public CentroCostos buscarCentroCostos(Long id) {
		return (CentroCostos)	this.getHibernateTemplate().get(CentroCostos.class, id);
	}
    public void borrarCentroCostos(Long id) {
        this.getHibernateTemplate().delete(buscarCentroCostos(id));
     }
    public void borrarCentroCostos(CentroCostos pObject) {
   	 this.getHibernateTemplate().delete(pObject);
  	}
	public void actualizarCentroCostos(CentroCostos pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM CentroCostos obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	public CentroCostos leerCentroCostos(Long id) {
		
		return null;
	}
}
       

   
   	
	
	
	
	



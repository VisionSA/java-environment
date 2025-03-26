package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.PlanCuentaDosDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;



public class PlanCuentaDosDaoHibernateImpl  extends HibernateDaoSupport implements PlanCuentaDosDao {



	public void grabarPlanCuenta(PlanCuentaDos pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public PlanCuentaDos buscarPlanCuenta(Long id) {
		return (PlanCuentaDos)	this.getHibernateTemplate().get(PlanCuentaDos.class, id);
	}
    public void borrarPlanCuenta(Long id) {
        this.getHibernateTemplate().delete(buscarPlanCuenta(id));
     }
    public void borrarPlanCuenta(PlanCuentaDos pObject) {
   	 this.getHibernateTemplate().delete(pObject);
  	}
	public void actualizarPlanCuenta(PlanCuentaDos pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM PlanCuentaDos obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
 public List listarTodosPlanCuentaSimple(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM PlanCuentaSimple obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	public PlanCuentaDos leerPlanCuenta(Long id) {
		
		return null;
	}
}
       

   
   	
	
	
	
	



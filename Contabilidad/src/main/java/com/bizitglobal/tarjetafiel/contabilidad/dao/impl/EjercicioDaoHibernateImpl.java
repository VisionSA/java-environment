package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.EjercicioDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;



public class EjercicioDaoHibernateImpl  extends HibernateDaoSupport implements EjercicioDao {



	public void grabarEjercicio(Ejercicio pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Ejercicio buscarEjercicio(Integer id) {
		return (Ejercicio)	this.getHibernateTemplate().get(Ejercicio.class, id);
	}
    public void borrarEjercicio(Integer id) {
        this.getHibernateTemplate().delete(buscarEjercicio(id));
     }
    public void borrarEjercicio(Ejercicio pObject) {
   	 this.getHibernateTemplate().delete(pObject);
  	}
	public void actualizarEjercicio(Ejercicio pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Ejercicio obj ");
				sb.append(hql);
				sb.append(" order by obj.idEjercicio ");

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	public Ejercicio leerEjercicio(Integer id) {
		
		return null;
	}
	
	
	
	
}
       

   
   	
	
	
	
	



package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.CentroCostoAsientoDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.CentroCostoAsiento;




public class CentroCostoAsientoDaoHibernateImpl  extends HibernateDaoSupport implements CentroCostoAsientoDao {



	public void grabarCentroCostoAsiento(CentroCostoAsiento pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public CentroCostoAsiento buscarCentroCostoAsiento(Long id) {
		return (CentroCostoAsiento)	this.getHibernateTemplate().get(CentroCostoAsiento.class, id);
	}
    public void borrarCentroCostoAsiento(Long id) {
        this.getHibernateTemplate().delete(buscarCentroCostoAsiento(id));
     }
    public void borrarCentroCostoAsiento(CentroCostoAsiento pObject) {
   	 this.getHibernateTemplate().delete(pObject);
  	}
	public void actualizarCentroCostoAsiento(CentroCostoAsiento pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM CentroCostoAsiento obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	public CentroCostoAsiento leerCentroCostoAsiento(Long id) {
		
		return null;
	}
}
       

   
   	
	
	
	
	



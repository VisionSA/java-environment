package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.LugarDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;

public class LugarDaoHibernateImpl extends HibernateDaoSupport implements LugarDao  {
	public LugarDaoHibernateImpl() {
		super();
	}

	public void grabarLugar (Lugar pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Lugar buscarLugar (Long id) {
		return (Lugar) this.getHibernateTemplate().get(Lugar.class, id);
	}
	public void borrarLugar (Long id) {
		borrarLugar(buscarLugar(id));
	}
	public void borrarLugar (Lugar pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarLugar (Lugar pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Lugar obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	public Lugar buscarLugarPorCodigo(final Long codigo){
		return (Lugar) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Criteria criteria = arg0.createCriteria(Lugar.class);
				criteria.add(Restrictions.eq("codigo", codigo));
				return criteria.uniqueResult();
			}
		});
	}
}


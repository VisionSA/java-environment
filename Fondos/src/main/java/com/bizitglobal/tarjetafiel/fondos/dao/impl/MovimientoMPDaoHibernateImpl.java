package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.MovimientoMPDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;

public class MovimientoMPDaoHibernateImpl extends HibernateDaoSupport implements MovimientoMPDao  {
	public MovimientoMPDaoHibernateImpl() {
		super();
	}

	public void grabarMovimientoMP (MovimientoMP pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public MovimientoMP buscarMovimientoMP (Long id) {
		return (MovimientoMP) this.getHibernateTemplate().get(MovimientoMP.class, id);
	}
	public void borrarMovimientoMP (Long id) {
		borrarMovimientoMP(buscarMovimientoMP(id));
	}
	public void borrarMovimientoMP (MovimientoMP pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarMovimientoMP (MovimientoMP pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM MovimientoMP obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


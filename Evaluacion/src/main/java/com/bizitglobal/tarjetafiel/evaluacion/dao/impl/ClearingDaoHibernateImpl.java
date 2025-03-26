package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ClearingDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Clearing;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class ClearingDaoHibernateImpl extends HibernateDaoSupport implements ClearingDao {
	public ClearingDaoHibernateImpl() {
		super();
	}

	public void grabarEvaClearings (Clearing pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Clearing buscarEvaClearings (Long id) {
		return (Clearing) this.getHibernateTemplate().get(Clearing.class, id);
	}
	public void borrarEvaClearings (Long id) {
		borrarEvaClearings(buscarEvaClearings(id));
	}
	public void borrarEvaClearings (Clearing pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaClearings (Clearing pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Clearing obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


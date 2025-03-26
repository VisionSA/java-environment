package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.PromotorDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Promotor;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class PromotorDaoHibernateImpl extends HibernateDaoSupport implements PromotorDao {
	public PromotorDaoHibernateImpl() {
		super();
	}

	public void grabarEvaPromotores (Promotor pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Promotor buscarEvaPromotores (Long id) {
		return (Promotor) this.getHibernateTemplate().get(Promotor.class, id);
	}
	public void borrarEvaPromotores (Long id) {
		borrarEvaPromotores(buscarEvaPromotores(id));
	}
	public void borrarEvaPromotores (Promotor pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaPromotores (Promotor pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Promotor obj ");
				sb.append(hql);
				
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


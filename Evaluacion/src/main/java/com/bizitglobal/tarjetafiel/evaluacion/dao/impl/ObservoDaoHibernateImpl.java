package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ObservoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Observo;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class ObservoDaoHibernateImpl extends HibernateDaoSupport implements ObservoDao {
	public ObservoDaoHibernateImpl() {
		super();
	}

	public void grabarEvaObservos (Observo pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Observo buscarEvaObservos (Long id) {
		return (Observo) this.getHibernateTemplate().get(Observo.class, id);
	}
	public void borrarEvaObservos (Long id) {
		borrarEvaObservos(buscarEvaObservos(id));
	}
	public void borrarEvaObservos (Observo pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaObservos (Observo pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Observo obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


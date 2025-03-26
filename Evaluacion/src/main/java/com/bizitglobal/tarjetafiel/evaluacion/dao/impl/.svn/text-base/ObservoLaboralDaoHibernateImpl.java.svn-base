package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ObservoLaboralDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ObservoLaboral;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class ObservoLaboralDaoHibernateImpl extends HibernateDaoSupport implements ObservoLaboralDao {
	public ObservoLaboralDaoHibernateImpl() {
		super();
	}

	public void grabarEvaObsLaborales (ObservoLaboral pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public ObservoLaboral buscarEvaObsLaborales (Long id) {
		return (ObservoLaboral) this.getHibernateTemplate().get(ObservoLaboral.class, id);
	}
	public void borrarEvaObsLaborales (Long id) {
		borrarEvaObsLaborales(buscarEvaObsLaborales(id));
	}
	public void borrarEvaObsLaborales (ObservoLaboral pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaObsLaborales (ObservoLaboral pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ObservoLaboral obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


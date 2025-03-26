package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.AlertaTipoIndividuoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.AlertaTipoIndividuo;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class AlertaTipoIndividuoDaoHibernateImpl extends HibernateDaoSupport implements AlertaTipoIndividuoDao {
	public AlertaTipoIndividuoDaoHibernateImpl() {
		super();
	}

	public void grabarEvaAlertasTipoIndiv (AlertaTipoIndividuo pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public AlertaTipoIndividuo buscarEvaAlertasTipoIndiv (Long id) {
		return (AlertaTipoIndividuo) this.getHibernateTemplate().get(AlertaTipoIndividuo.class, id);
	}
	public void borrarEvaAlertasTipoIndiv (Long id) {
		borrarEvaAlertasTipoIndiv(buscarEvaAlertasTipoIndiv(id));
	}
	public void borrarEvaAlertasTipoIndiv (AlertaTipoIndividuo pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaAlertasTipoIndiv (AlertaTipoIndividuo pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM AlertaTipoIndividuo obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


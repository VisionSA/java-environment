package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.AlertaSolicitudDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.AlertaSolicitud;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class AlertaSolicitudDaoHibernateImpl extends HibernateDaoSupport implements AlertaSolicitudDao {
	public AlertaSolicitudDaoHibernateImpl() {
		super();
	}

	public void grabarEvaAlertasSolicitudes (AlertaSolicitud pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public AlertaSolicitud buscarEvaAlertasSolicitudes (Long id) {
		return (AlertaSolicitud) this.getHibernateTemplate().get(AlertaSolicitud.class, id);
	}
	public void borrarEvaAlertasSolicitudes (Long id) {
		borrarEvaAlertasSolicitudes(buscarEvaAlertasSolicitudes(id));
	}
	public void borrarEvaAlertasSolicitudes (AlertaSolicitud pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaAlertasSolicitudes (AlertaSolicitud pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM AlertaSolicitud obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


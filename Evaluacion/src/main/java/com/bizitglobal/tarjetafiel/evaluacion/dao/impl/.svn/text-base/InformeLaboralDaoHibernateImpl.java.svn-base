package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.InformeLaboralDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InformeLaboral;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class InformeLaboralDaoHibernateImpl extends HibernateDaoSupport implements InformeLaboralDao {
	public InformeLaboralDaoHibernateImpl() {
		super();
	}

	public void grabarEvaInfoLaborales (InformeLaboral pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public InformeLaboral buscarEvaInfoLaborales (Long id) {
		return (InformeLaboral) this.getHibernateTemplate().get(InformeLaboral.class, id);
	}
	public void borrarEvaInfoLaborales (Long id) {
		borrarEvaInfoLaborales(buscarEvaInfoLaborales(id));
	}
	public void borrarEvaInfoLaborales (InformeLaboral pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaInfoLaborales (InformeLaboral pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM InformeLaboral obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


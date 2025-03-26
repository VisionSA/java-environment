package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.TipoIndividuoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.TipoIndividuo;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class TipoIndividuoDaoHibernateImpl extends HibernateDaoSupport implements TipoIndividuoDao {
	public TipoIndividuoDaoHibernateImpl() {
		super();
	}

	public void grabarEvaTiposIndividuos (TipoIndividuo pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public TipoIndividuo buscarEvaTiposIndividuos (Long id) {
		return (TipoIndividuo) this.getHibernateTemplate().get(TipoIndividuo.class, id);
	}
	public void borrarEvaTiposIndividuos (Long id) {
		borrarEvaTiposIndividuos(buscarEvaTiposIndividuos(id));
	}
	public void borrarEvaTiposIndividuos (TipoIndividuo pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaTiposIndividuos (TipoIndividuo pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM TipoIndividuo obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


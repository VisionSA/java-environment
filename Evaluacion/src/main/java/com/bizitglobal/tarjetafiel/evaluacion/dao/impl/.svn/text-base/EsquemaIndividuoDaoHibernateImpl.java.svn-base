package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.EsquemaIndividuoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.EsquemaIndividuo;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class EsquemaIndividuoDaoHibernateImpl extends HibernateDaoSupport implements EsquemaIndividuoDao {
	public EsquemaIndividuoDaoHibernateImpl() {
		super();
	}

	public void grabarEsquemaIndividuo (EsquemaIndividuo pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public EsquemaIndividuo buscarEsquemaIndividuo (Long id) {
		return (EsquemaIndividuo) this.getHibernateTemplate().get(EsquemaIndividuo.class, id);
	}
	public void borrarEsquemaIndividuo (Long id) {
		borrarEsquemaIndividuo(buscarEsquemaIndividuo(id));
	}
	public void borrarEsquemaIndividuo (EsquemaIndividuo pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEsquemaIndividuo (EsquemaIndividuo pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM EsquemaIndividuo obj ");
                sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


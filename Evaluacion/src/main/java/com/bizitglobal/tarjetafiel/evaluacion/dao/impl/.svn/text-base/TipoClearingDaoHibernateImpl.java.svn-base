package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.TipoClearingDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.TipoClearing;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class TipoClearingDaoHibernateImpl extends HibernateDaoSupport implements TipoClearingDao {
	public TipoClearingDaoHibernateImpl() {
		super();
	}

	public void grabarEvaTiposClearings (TipoClearing pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public TipoClearing buscarEvaTiposClearings (Long id) {
		return (TipoClearing) this.getHibernateTemplate().get(TipoClearing.class, id);
	}
	public void borrarEvaTiposClearings (Long id) {
		borrarEvaTiposClearings(buscarEvaTiposClearings(id));
	}
	public void borrarEvaTiposClearings (TipoClearing pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaTiposClearings (TipoClearing pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM TipoClearing obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


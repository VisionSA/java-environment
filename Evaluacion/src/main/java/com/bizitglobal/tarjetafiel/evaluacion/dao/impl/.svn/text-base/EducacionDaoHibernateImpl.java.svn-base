package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.EducacionDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Educacion;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class EducacionDaoHibernateImpl extends HibernateDaoSupport implements EducacionDao {
	public EducacionDaoHibernateImpl() {
		super();
	}

	public void grabarEvaEducaciones (Educacion pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Educacion buscarEvaEducaciones (Long id) {
		return (Educacion) this.getHibernateTemplate().get(Educacion.class, id);
	}
	public void borrarEvaEducaciones (Long id) {
		borrarEvaEducaciones(buscarEvaEducaciones(id));
	}
	public void borrarEvaEducaciones (Educacion pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaEducaciones (Educacion pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Educacion obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ConfirmacionTelefonicaDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ConfirmacionTelefonica;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class ConfirmacionTelefonicaDaoHibernateImpl extends HibernateDaoSupport implements ConfirmacionTelefonicaDao {
	public ConfirmacionTelefonicaDaoHibernateImpl() {
		super();
	}

	public void grabarEvaConfTelefonicas (ConfirmacionTelefonica pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public ConfirmacionTelefonica buscarEvaConfTelefonicas (Long id) {
		return (ConfirmacionTelefonica) this.getHibernateTemplate().get(ConfirmacionTelefonica.class, id);
	}
	public void borrarEvaConfTelefonicas (Long id) {
		borrarEvaConfTelefonicas(buscarEvaConfTelefonicas(id));
	}
	public void borrarEvaConfTelefonicas (ConfirmacionTelefonica pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaConfTelefonicas (ConfirmacionTelefonica pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ConfirmacionTelefonica obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


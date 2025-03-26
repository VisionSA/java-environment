package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.EmailsDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Emails;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class EmailsDaoHibernateImpl extends HibernateDaoSupport implements EmailsDao {
	public EmailsDaoHibernateImpl() {
		super();
	}

	public void grabarEvaEmails (Emails pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Emails buscarEvaEmails (Long id) {
		return (Emails) this.getHibernateTemplate().get(Emails.class, id);
	}
	public void borrarEvaEmails (Long id) {
		borrarEvaEmails(buscarEvaEmails(id));
	}
	public void borrarEvaEmails (Emails pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaEmails (Emails pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Emails obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


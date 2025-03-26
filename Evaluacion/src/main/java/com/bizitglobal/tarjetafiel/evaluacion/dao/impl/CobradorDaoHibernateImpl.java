package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.CobradorDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Cobrador;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class CobradorDaoHibernateImpl extends HibernateDaoSupport implements CobradorDao {
	public CobradorDaoHibernateImpl() {
		super();
	}

	public void grabarEvaCobradores (Cobrador pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Cobrador buscarEvaCobradores (Long id) {
		return (Cobrador) this.getHibernateTemplate().get(Cobrador.class, id);
	}
	public void borrarEvaCobradores (Long id) {
		borrarEvaCobradores(buscarEvaCobradores(id));
	}
	public void borrarEvaCobradores (Cobrador pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaCobradores (Cobrador pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Cobrador obj ");
				sb.append(hql);
				
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.VerificadorDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Verificador;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class VerificadorDaoHibernateImpl extends HibernateDaoSupport implements VerificadorDao {
	public VerificadorDaoHibernateImpl() {
		super();
	}

	public void grabarEvaVerificadores (Verificador pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Verificador buscarEvaVerificadores (Long id) {
		return (Verificador) this.getHibernateTemplate().get(Verificador.class, id);
	}
	public void borrarEvaVerificadores (Long id) {
		borrarEvaVerificadores(buscarEvaVerificadores(id));
	}
	public void borrarEvaVerificadores (Verificador pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaVerificadores (Verificador pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Verificador obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


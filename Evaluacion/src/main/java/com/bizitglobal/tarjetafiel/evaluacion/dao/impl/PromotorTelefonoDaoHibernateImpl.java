package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.PromotorTelefonoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.PromotorTelefono;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class PromotorTelefonoDaoHibernateImpl extends HibernateDaoSupport implements PromotorTelefonoDao {
	public PromotorTelefonoDaoHibernateImpl() {
		super();
	}

	public void grabarEvaPromoTelefonos (PromotorTelefono pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public PromotorTelefono buscarEvaPromoTelefonos (Long id) {
		return (PromotorTelefono) this.getHibernateTemplate().get(PromotorTelefono.class, id);
	}
	public void borrarEvaPromoTelefonos (Long id) {
		borrarEvaPromoTelefonos(buscarEvaPromoTelefonos(id));
	}
	public void borrarEvaPromoTelefonos (PromotorTelefono pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaPromoTelefonos (PromotorTelefono pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM PromotorTelefono obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


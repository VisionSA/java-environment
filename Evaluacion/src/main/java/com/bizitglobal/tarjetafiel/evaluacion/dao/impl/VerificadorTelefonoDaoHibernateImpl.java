package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.VerificadorTelefonoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.VerificadorTelefono;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class VerificadorTelefonoDaoHibernateImpl extends HibernateDaoSupport implements VerificadorTelefonoDao {
	public VerificadorTelefonoDaoHibernateImpl() {
		super();
	}

	public void grabarEvaVerifTelefonos (VerificadorTelefono pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public VerificadorTelefono buscarEvaVerifTelefonos (Long id) {
		return (VerificadorTelefono) this.getHibernateTemplate().get(VerificadorTelefono.class, id);
	}
	public void borrarEvaVerifTelefonos (Long id) {
		borrarEvaVerifTelefonos(buscarEvaVerifTelefonos(id));
	}
	public void borrarEvaVerifTelefonos (VerificadorTelefono pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaVerifTelefonos (VerificadorTelefono pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM VerificadorTelefono obj ");
				sb.append(hql);
				
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


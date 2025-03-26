package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.RegistroUploadDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.RegistroUpload;

public class RegistroUploadDaoHibernateImpl extends HibernateDaoSupport implements RegistroUploadDao  {
	public RegistroUploadDaoHibernateImpl() {
		super();
	}

	public void grabarRegistroUpload (RegistroUpload pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public RegistroUpload buscarRegistroUpload (Long id) {
		return (RegistroUpload) this.getHibernateTemplate().get(RegistroUpload.class, id);
	}
	public void borrarRegistroUpload (Long id) {
		borrarRegistroUpload(buscarRegistroUpload(id));
	}
	public void borrarRegistroUpload (RegistroUpload pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarRegistroUpload (RegistroUpload pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM RegistroUpload obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


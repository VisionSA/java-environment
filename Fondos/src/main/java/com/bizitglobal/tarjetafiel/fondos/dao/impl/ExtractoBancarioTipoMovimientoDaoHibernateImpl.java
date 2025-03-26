package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.ExtractoBancarioDao;
import com.bizitglobal.tarjetafiel.fondos.dao.ExtractoBancarioTipoMovimientoDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancario;
import com.bizitglobal.tarjetafiel.fondos.negocio.ExtractoBancarioTipoMovimiento;

public class ExtractoBancarioTipoMovimientoDaoHibernateImpl extends HibernateDaoSupport implements ExtractoBancarioTipoMovimientoDao  {
	public ExtractoBancarioTipoMovimientoDaoHibernateImpl() {
		super();
	}
	
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ExtractoBancarioTipoMovimiento obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public void actualizarExtractoBancarioTipoMovimiento(ExtractoBancarioTipoMovimiento pObject) {
		this.getHibernateTemplate().update(pObject);
		
	}

	@Override
	public void borrarExtractoBancarioTipoMovimiento(Long id) {
		borrarExtractoBancarioTipoMovimiento(buscarExtractoBancarioTipoMovimiento(id));
	}

	@Override
	public ExtractoBancarioTipoMovimiento buscarExtractoBancarioTipoMovimiento(Long id) {
		return (ExtractoBancarioTipoMovimiento) this.getHibernateTemplate().get(ExtractoBancarioTipoMovimiento.class, id);
	}

	@Override
	public void grabarExtractoBancarioTipoMovimiento(ExtractoBancarioTipoMovimiento pObject) {
		this.getHibernateTemplate().save(pObject);
	}

	@Override
	public void borrarExtractoBancarioTipoMovimiento(ExtractoBancarioTipoMovimiento object) {
		this.getHibernateTemplate().delete(object);
	}

	
}


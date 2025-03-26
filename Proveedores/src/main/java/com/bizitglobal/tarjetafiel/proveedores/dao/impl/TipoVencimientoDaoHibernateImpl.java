package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.TipoVencimientoDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.TipoVencimiento;

public class TipoVencimientoDaoHibernateImpl extends HibernateDaoSupport implements TipoVencimientoDao {

	public TipoVencimientoDaoHibernateImpl() {
		super();
	}

	public void grabarTipoVencimiento(TipoVencimiento unTipoVencimiento) {
		this.getHibernateTemplate().save(unTipoVencimiento);
	}	

	public TipoVencimiento buscarTipoVencimiento(Integer id) {
		return (TipoVencimiento) this.getHibernateTemplate().get(TipoVencimiento.class,id);
	}
	
	public void borrarTipoVencimiento(Integer id) {
		borrarTipoVencimiento(buscarTipoVencimiento(id));
	}	

	public void borrarTipoVencimiento(TipoVencimiento unTipoVencimiento) {
		this.getHibernateTemplate().delete(unTipoVencimiento);
	}

	public List listarTodos(Filtro filtro) {
		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM TipoVencimiento obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.idTipoVencimiento ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarTipoVencimiento(TipoVencimiento unTipoVencimiento) {
		this.getHibernateTemplate().update(unTipoVencimiento);
	}

}

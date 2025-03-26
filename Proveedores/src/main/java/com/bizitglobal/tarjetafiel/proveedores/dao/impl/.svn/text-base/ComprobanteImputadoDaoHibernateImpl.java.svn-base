package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.ComprobanteDao;
import com.bizitglobal.tarjetafiel.proveedores.dao.ComprobanteImputadoDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Comprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ComprobanteImputado;

public class ComprobanteImputadoDaoHibernateImpl extends HibernateDaoSupport implements ComprobanteImputadoDao {

	public ComprobanteImputadoDaoHibernateImpl() {
		super();
	}

	public void grabarComprobanteImputado(ComprobanteImputado unComprobanteImputado) {
		this.getHibernateTemplate().save(unComprobanteImputado);
	}	

	public ComprobanteImputado buscarComprobanteImputado(Long id) {
		return (ComprobanteImputado) this.getHibernateTemplate().get(ComprobanteImputado.class,id);
	}
	
	public void borrarComprobanteImputado(Long id) {
		borrarComprobanteImputado(buscarComprobanteImputado(id));
	}	

	public void borrarComprobanteImputado(ComprobanteImputado unComprobanteImputado) {
		this.getHibernateTemplate().delete(unComprobanteImputado);
	}

	public List listarTodos(Filtro unFiltro) {
		final String filtro = unFiltro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ComprobanteImputado obj ");
				sb.append(filtro);
				sb.append("ORDER BY obj.idComprobanteImputado ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public void actualizarComprobanteImputado(ComprobanteImputado unComprobanteImputado) {
		this.getHibernateTemplate().update(unComprobanteImputado);
	}
	
	public void grabarYActualizarComprobanteImputado(ComprobanteImputado unComprobanteImputado) {
		this.getHibernateTemplate().saveOrUpdate(unComprobanteImputado);
	}

}

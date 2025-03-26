package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.CuotaComprobanteDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuotaComprobante;

public class CuotaComprobanteDaoHibernateImpl extends HibernateDaoSupport implements CuotaComprobanteDao {

	public CuotaComprobanteDaoHibernateImpl() {
		super();
	}
	
	public void grabarCuotaComprobante(CuotaComprobante unaCuotaComprobante) {
		this.getHibernateTemplate().save(unaCuotaComprobante);
	}

	public CuotaComprobante buscarCuotaComprobante(Long id) {
		return (CuotaComprobante) this.getHibernateTemplate().get(CuotaComprobante.class,id);
	}
	
	public void borrarCuotaComprobante(CuotaComprobante unaCuotaComprobante) {
		this.getHibernateTemplate().delete(unaCuotaComprobante);
	}
		
	public void borrarCuotaComprobante(Long id) {
		borrarCuotaComprobante(buscarCuotaComprobante(id));
	}
	

	public List listarTodos(Filtro unFiltro) {
		final String filtro = unFiltro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM CuotaComprobante obj ");
				sb.append(filtro);
				sb.append("ORDER BY obj.idCuotaComprobante ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public Long countCuotaComprobante(String cuit) {
		final String unCuit = cuit;
		
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				
				Query query = session.createQuery("SELECT COUNT(obj.idCuotaComprobante) FROM CuotaComprobante obj WHERE obj.comprobante.proveedor.cuit LIKE '"+unCuit+"'");
				Object result = query.uniqueResult();

				return new Long(result.toString());
			}
		});		
	}
	
	public void actualizarCuotaComprobante(CuotaComprobante unaCuotaComprobante) {
		this.getHibernateTemplate().update(unaCuotaComprobante);
	}
	
	
}

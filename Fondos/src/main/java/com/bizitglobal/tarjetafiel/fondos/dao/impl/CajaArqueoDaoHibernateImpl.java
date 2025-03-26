package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.CajaArqueoDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaArqueo;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovXCuentaXConcepto;

public class CajaArqueoDaoHibernateImpl extends HibernateDaoSupport implements CajaArqueoDao  {
	public CajaArqueoDaoHibernateImpl() {
		super();
	}

	public void grabarCajaArqueo (CajaArqueo pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public CajaArqueo buscarCajaArqueo (Long id) {
		return (CajaArqueo) this.getHibernateTemplate().get(CajaArqueo.class, id);
	}
	public void borrarCajaArqueo (Long id) {
		borrarCajaArqueo(buscarCajaArqueo(id));
	}
	public void borrarCajaArqueo (CajaArqueo pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarCajaArqueo (CajaArqueo pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM CajaArqueo obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	public List buscarTotalesMovimientos(final Long caja, final Long idApertura){
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String hql = "Select obj From MovXCuentaXConcepto obj Where obj.id.idCaja = :caja AND obj.id.idApertura = :apertura";
				Query query = session.createQuery(hql);
				query.setParameter("caja", caja);
				query.setParameter("apertura", idApertura);
				return query.list();
			}
		});
	}
}


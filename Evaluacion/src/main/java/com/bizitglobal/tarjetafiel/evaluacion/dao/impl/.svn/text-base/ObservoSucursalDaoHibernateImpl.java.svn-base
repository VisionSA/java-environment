package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ObservoSucursalDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ObservoSucursal;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class ObservoSucursalDaoHibernateImpl extends HibernateDaoSupport implements ObservoSucursalDao {
	public ObservoSucursalDaoHibernateImpl() {
		super();
	}

	public void grabarEvaObsSucursales (ObservoSucursal pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public ObservoSucursal buscarEvaObsSucursales (Long id) {
		return (ObservoSucursal) this.getHibernateTemplate().get(ObservoSucursal.class, id);
	}
	public void borrarEvaObsSucursales (Long id) {
		borrarEvaObsSucursales(buscarEvaObsSucursales(id));
	}
	public void borrarEvaObsSucursales (ObservoSucursal pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaObsSucursales (ObservoSucursal pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ObservoSucursal obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


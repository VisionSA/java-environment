package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.InfoParticularVehiculoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InfoParticularVehiculo;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class InfoParticularVehiculoDaoHibernateImpl extends HibernateDaoSupport implements InfoParticularVehiculoDao {
	public InfoParticularVehiculoDaoHibernateImpl() {
		super();
	}

	public void grabarInfoParticularVehiculo (InfoParticularVehiculo pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public InfoParticularVehiculo buscarInfoParticularVehiculo (Long id) {
		return (InfoParticularVehiculo) this.getHibernateTemplate().get(InfoParticularVehiculo.class, id);
	}
	public void borrarInfoParticularVehiculo (Long id) {
		borrarInfoParticularVehiculo(buscarInfoParticularVehiculo(id));
	}
	public void borrarInfoParticularVehiculo (InfoParticularVehiculo pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarInfoParticularVehiculo (InfoParticularVehiculo pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM InfoParticularVehiculo obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


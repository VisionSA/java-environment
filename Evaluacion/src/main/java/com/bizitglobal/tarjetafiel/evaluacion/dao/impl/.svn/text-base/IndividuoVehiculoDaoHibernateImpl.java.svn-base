package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.IndividuoVehiculoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoVehiculo;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class IndividuoVehiculoDaoHibernateImpl extends HibernateDaoSupport implements IndividuoVehiculoDao {
	public IndividuoVehiculoDaoHibernateImpl() {
		super();
	}

	public void grabarEvaIndiVehiculos (IndividuoVehiculo pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public IndividuoVehiculo buscarEvaIndiVehiculos (Long id) {
		return (IndividuoVehiculo) this.getHibernateTemplate().get(IndividuoVehiculo.class, id);
	}
	public void borrarEvaIndiVehiculos (Long id) {
		borrarEvaIndiVehiculos(buscarEvaIndiVehiculos(id));
	}
	public void borrarEvaIndiVehiculos (IndividuoVehiculo pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaIndiVehiculos (IndividuoVehiculo pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM IndividuoVehiculo obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


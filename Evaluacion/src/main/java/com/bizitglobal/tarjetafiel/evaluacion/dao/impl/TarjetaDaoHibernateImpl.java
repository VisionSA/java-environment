package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.TarjetaDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tarjeta;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class TarjetaDaoHibernateImpl extends HibernateDaoSupport implements TarjetaDao {
	public TarjetaDaoHibernateImpl() {
		super();
	}

	public void grabarEvaTarjetas (Tarjeta pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Tarjeta buscarEvaTarjetas (Long id) {
		return (Tarjeta) this.getHibernateTemplate().get(Tarjeta.class, id);
	}
	public void borrarEvaTarjetas (Long id) {
		borrarEvaTarjetas(buscarEvaTarjetas(id));
	}
	public void borrarEvaTarjetas (Tarjeta pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaTarjetas (Tarjeta pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Tarjeta obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


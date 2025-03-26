package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.TelefonosDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class TelefonosDaoHibernateImpl extends HibernateDaoSupport implements TelefonosDao {
	public TelefonosDaoHibernateImpl() {
		super();
	}

	public void grabarEvaTelefonos (Telefonos pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Telefonos buscarEvaTelefonos (Long id) {
		return (Telefonos) this.getHibernateTemplate().get(Telefonos.class, id);
	}
	public void borrarEvaTelefonos (Long id) {
		borrarEvaTelefonos(buscarEvaTelefonos(id));
	}
	public void borrarEvaTelefonos (Telefonos pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaTelefonos (Telefonos pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Telefonos obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public boolean tieneTelefono(final Long idCliente) {
		
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT tel.idTelefono ");
				sb.append("FROM Telefonos tels , ClienteTransaccion cli ");
				sb.append("INNER JOIN cli.individuo ind ");
				sb.append("INNER JOIN tels.telefono tel ");
				sb.append("WHERE cli.idCliente=:idCliente ");
				sb.append("AND ind=tels.individuoEvaluacion ");				
				Query query = session.createQuery(sb.toString());
				query.setLong("idCliente", idCliente);
				List list = query.list();
				return list;
			}
		});
		return !list.isEmpty();
	}
}


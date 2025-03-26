package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.DiaPagoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.DiaPago;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class DiaPagoDaoHibernateImpl extends HibernateDaoSupport implements DiaPagoDao {
	public DiaPagoDaoHibernateImpl() {
		super();
	}

	public void grabarEvaDiasPago (DiaPago pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public DiaPago buscarEvaDiasPago (Long id) {
		return (DiaPago) this.getHibernateTemplate().get(DiaPago.class, id);
	}
	public void borrarEvaDiasPago (Long id) {
		borrarEvaDiasPago(buscarEvaDiasPago(id));
	}
	public void borrarEvaDiasPago (DiaPago pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaDiasPago (DiaPago pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM DiaPago obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

	@Override
	public DiaPago getDiaPagoByIdCliente(final Long idCliente) {
		
		return (DiaPago) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT new DiaPago(cli.individuo.diaPago.idDiaPago, cli.individuo.diaPago.diaPago) ");
				sb.append("FROM ClienteTransaccion cli ");
				sb.append("WHERE cli.idCliente=:idCliente");
				Query query = session.createQuery(sb.toString());
				query.setLong("idCliente", idCliente);
				return query.uniqueResult();
			}
		});
	}
}


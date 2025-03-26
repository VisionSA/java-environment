package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.TclienteDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Tcliente;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class TclienteDaoHibernateImpl extends HibernateDaoSupport implements TclienteDao {
	public TclienteDaoHibernateImpl() {
		super();
	}

	public void grabarTcliente (Tcliente pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Tcliente buscarTcliente (Long id) {
		return (Tcliente) this.getHibernateTemplate().get(Tcliente.class, id);
	}
	public void borrarTcliente (Long id) {
		borrarTcliente(buscarTcliente(id));
	}
	public void borrarTcliente (Tcliente pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarTcliente (Tcliente pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Tcliente obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


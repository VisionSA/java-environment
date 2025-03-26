package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.BancosDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Bancos;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class BancosDaoHibernateImpl extends HibernateDaoSupport implements BancosDao {
	public BancosDaoHibernateImpl() {
		super();
	}

	public void grabarEvaBancos (Bancos pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Bancos buscarEvaBancos (Long id) {
		return (Bancos) this.getHibernateTemplate().get(Bancos.class, id);
	}
	public void borrarEvaBancos (Long id) {
		borrarEvaBancos(buscarEvaBancos(id));
	}
	public void borrarEvaBancos (Bancos pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaBancos (Bancos pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Bancos obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


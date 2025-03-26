package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.ViviendaEstadoDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ViviendaEstado;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class ViviendaEstadoDaoHibernateImpl extends HibernateDaoSupport implements ViviendaEstadoDao {
	public ViviendaEstadoDaoHibernateImpl() {
		super();
	}

	public void grabarEvaVivEstados (ViviendaEstado pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public ViviendaEstado buscarEvaVivEstados (Long id) {
		return (ViviendaEstado) this.getHibernateTemplate().get(ViviendaEstado.class, id);
	}
	public void borrarEvaVivEstados (Long id) {
		borrarEvaVivEstados(buscarEvaVivEstados(id));
	}
	public void borrarEvaVivEstados (ViviendaEstado pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaVivEstados (ViviendaEstado pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM ViviendaEstado obj ");
				sb.append(hql);
				
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
}


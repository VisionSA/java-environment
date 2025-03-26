package com.bizitglobal.tarjetafiel.impuestos.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.impuestos.dao.IndividuoDao;
import com.bizitglobal.tarjetafiel.impuestos.dao.JurisTipoImpuestoDao;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Individuo;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisTipoImpuesto;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public class JurisTipoImpuestoDaoHibernateImpl extends HibernateDaoSupport implements JurisTipoImpuestoDao {

	public JurisTipoImpuestoDaoHibernateImpl() {
		super();
	}
	
	private Long maxIdJurisTipoImpuesto() {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				Query query = session.createQuery("SELECT MAX(obj.idJurisTipoImpuesto) FROM JurisTipoImpuesto obj");
				Object result = query.uniqueResult();

				return new Long(result.toString());
			}
		});		
	}

	public void actualizarJurisTipoImpuesto(JurisTipoImpuesto jurisTipoImpuesto) {
		this.getHibernateTemplate().update(jurisTipoImpuesto);
		
	}

	public void borrarJurisTipoImpuesto(Long idJurisTipoImpuesto) {
		borrarJurisTipoImpuesto(buscarJurisTipoImpuesto(idJurisTipoImpuesto));
		
	}
	
	public void borrarJurisTipoImpuesto(JurisTipoImpuesto jurisTipoImpuesto) {
		this.getHibernateTemplate().delete(jurisTipoImpuesto);
	}
	

	public JurisTipoImpuesto buscarJurisTipoImpuesto(Long idJurisTipoImpuesto) {
		return (JurisTipoImpuesto) this.getHibernateTemplate().get(JurisTipoImpuesto.class,idJurisTipoImpuesto);
	}

	public void grabarJurisTipoImpuesto(JurisTipoImpuesto jurisTipoImpuesto) {
		this.getHibernateTemplate().save(jurisTipoImpuesto);
//		return maxIdJurisTipoImpuesto();
	}


	public List listarTodos(Filtro unFiltro) {
        final String filtro = unFiltro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM JurisTipoImpuesto obj ");
				sb.append(filtro);
				sb.append("ORDER BY obj.idJurisTipoImpuesto ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});
	}	

}

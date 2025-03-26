package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bizitglobal.tarjetafiel.evaluacion.dao.DigitalDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Digital;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Archivo;

public class DigitalDaoHibernateImpl extends HibernateDaoSupport implements DigitalDao {
	public DigitalDaoHibernateImpl() {
		super();
	}

	public void grabarEvaDigitales (Digital pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public Digital buscarEvaDigitales (Long id) {
		return (Digital) this.getHibernateTemplate().get(Digital.class, id);
	}
	public void borrarEvaDigitales (Long id) {
		borrarEvaDigitales(buscarEvaDigitales(id));
	}
	public void borrarEvaDigitales (Digital pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaDigitales (Digital pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Digital obj ");
				sb.append(hql);
				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	
	public List buscarPorFecha(final Timestamp desde,final Timestamp hasta){
		logger.info("Ejecutando consulta");
		logger.info("Fecha desde: " + desde);
		logger.info("Fecha hasta: " + hasta);
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				
				List archivos = session.createCriteria(Digital.class)
					    .add(Restrictions.between("timestamp", desde, hasta))
					    .list();
				return archivos;
			}
		});
	}
}


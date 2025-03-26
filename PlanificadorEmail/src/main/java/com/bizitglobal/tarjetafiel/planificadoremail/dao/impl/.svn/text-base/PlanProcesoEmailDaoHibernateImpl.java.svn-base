package com.bizitglobal.tarjetafiel.planificadoremail.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.planificadoremail.dao.PlanProcesoEmailDao;
import com.bizitglobal.tarjetafiel.planificadoremail.negocio.ImagenEmail;


/***** @Id:6958 ******/

public class PlanProcesoEmailDaoHibernateImpl extends HibernateDaoSupport implements PlanProcesoEmailDao {
	private static final Logger log = Logger
			.getLogger(PlanProcesoEmailDaoHibernateImpl.class);


	public PlanProcesoEmailDaoHibernateImpl() {
		super();
	}


	public List findAll() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT obj ");
				sb.append("FROM PlanProcesoEmail obj ");
				sb.append("Order by obj.idPlan ");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});
	}


	public void grabarImagenEmail(ImagenEmail object) {
		this.getHibernateTemplate().save(object);
	}
}

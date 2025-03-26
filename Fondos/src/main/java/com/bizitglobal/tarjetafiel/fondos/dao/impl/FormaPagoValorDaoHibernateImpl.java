package com.bizitglobal.tarjetafiel.fondos.dao.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.dao.FormaPagoValorDao;
import com.bizitglobal.tarjetafiel.fondos.negocio.FormaPagoValor;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;

public class FormaPagoValorDaoHibernateImpl extends HibernateDaoSupport implements FormaPagoValorDao  {
	public FormaPagoValorDaoHibernateImpl() {
		super();
	}

	public void grabarFormaPagoValor (FormaPagoValor pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public FormaPagoValor buscarFormaPagoValor (Long id) {
		return (FormaPagoValor) this.getHibernateTemplate().get(FormaPagoValor.class, id);
	}
	public void borrarFormaPagoValor (Long id) {
		borrarFormaPagoValor(buscarFormaPagoValor(id));
	}
	public void borrarFormaPagoValor (FormaPagoValor pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarFormaPagoValor (FormaPagoValor pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM FormaPagoValor obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				return list;
			}
		});
	}

	public List listarTodosFlex(Filtro filtro) {
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM FormaPagoValor obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				List<FormaPagoValor> listReturn = new ArrayList<FormaPagoValor>();
				
				Iterator<FormaPagoValor> iterator = list.iterator();
				
				while(iterator.hasNext()){
					FormaPagoValor formaPagoValor = iterator.next();
					FormaPagoValor newFormaPagoValor = new FormaPagoValor();
					newFormaPagoValor.setDescripcion(formaPagoValor.getDescripcion());
					newFormaPagoValor.setIdFormaPagoValor(formaPagoValor.getIdFormaPagoValor());
					newFormaPagoValor.setFormaPago(new FormaPago());
					newFormaPagoValor.getFormaPago().setIdFormaPago(formaPagoValor.getFormaPago().getIdFormaPago());
					newFormaPagoValor.getFormaPago().setDescripcion(formaPagoValor.getFormaPago().getDescripcion());
					newFormaPagoValor.setMultiplo(formaPagoValor.getMultiplo());
					listReturn.add(newFormaPagoValor);
				}
				
				return listReturn;
			}
		});
	}
}


package com.bizitglobal.tarjetafiel.proveedores.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.proveedores.dao.DocumentoAdjuntoDao;
import com.bizitglobal.tarjetafiel.proveedores.negocio.DocumentoAdjunto;

public class DocumentoAdjuntoDaoHibernateImpl extends HibernateDaoSupport implements DocumentoAdjuntoDao {

	public DocumentoAdjuntoDaoHibernateImpl() {
		super();
	}
	
	public void grabarDocumentoAdjunto(DocumentoAdjunto unDocumentoAdjunto) {
		this.getHibernateTemplate().save(unDocumentoAdjunto);
	}

	public DocumentoAdjunto buscarDocumentoAdjunto(Long id) {
		return (DocumentoAdjunto) this.getHibernateTemplate().get(DocumentoAdjunto.class,id);
	}
	
	public void borrarDocumentoAdjunto(DocumentoAdjunto unDocumentoAdjunto) {
		this.getHibernateTemplate().delete(unDocumentoAdjunto);
	}
		
	public void borrarDocumentoAdjunto(Long id) {
		borrarDocumentoAdjunto(buscarDocumentoAdjunto(id));
	}
	

	public List listarTodos(Filtro unFiltro) {
		final String filtro = unFiltro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM DocumentoAdjunto obj ");
				sb.append(filtro);
				sb.append("ORDER BY obj.idDocumentoAdjunto ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
	
	public Long maxIdDocumentoAdjunto() {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				Query query = session.createQuery("SELECT MAX(obj.idDocumentoAdjunto) FROM DocumentoAdjunto obj");
				Object result = query.uniqueResult();

				return new Long(result.toString());
			}
		});		
	}
	
	public void actualizarDocumentoAdjunto(DocumentoAdjunto unDocumentoAdjunto) {
		this.getHibernateTemplate().update(unDocumentoAdjunto);
	}
	
	
}

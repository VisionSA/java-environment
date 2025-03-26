package com.bizitglobal.tarjetafiel.contabilidad.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.DocAdjuntoDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.DocAdjunto;



public class DocAdjuntoDaoHibernateImpl  extends HibernateDaoSupport implements DocAdjuntoDao {
	private Logger log = Logger.getLogger(DocAdjuntoDaoHibernateImpl.class);
	private DataSource dataSource;
	private JdbcTemplate jt;

	public void grabarDocAdjunto(DocAdjunto pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public DocAdjunto buscarDocAdjunto(Long id) {
		return (DocAdjunto)	this.getHibernateTemplate().get(DocAdjunto.class, id);
	}
    public void borrarDocAdjunto(Long id) {
        this.getHibernateTemplate().delete(buscarDocAdjunto(id));
     }
    public void borrarDocAdjunto(DocAdjunto pObject) {
   	 this.getHibernateTemplate().delete(pObject);
  	}
	public void actualizarDocAdjunto(DocAdjunto pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {
		
		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM DocAdjunto obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
                log.info("Consulta realizada: " + sb.toString());
				List list = query.list();
				return list;
			}
		});
	}
	public DocAdjunto leerDocAdjunto(Long id) {
		
		return null;
	}
	
	public void grabar(DocAdjunto docAdjunto) {
        jt = new JdbcTemplate(dataSource);
		StringBuffer sqlInsert = new StringBuffer(500);
		sqlInsert.append("INSERT INTO " + DocAdjunto.DOC_ADJUNTO + " (");
		sqlInsert.append(DocAdjunto.ID_ASIENTO);
		sqlInsert.append(", " + DocAdjunto.ID_EJERCICIO);
		sqlInsert.append(", " + DocAdjunto.ID_EMPRESA);
		sqlInsert.append(", " + DocAdjunto.DESCRIPCION);
		sqlInsert.append(", " + DocAdjunto.ID_DOC_ADJUNTO);
		sqlInsert.append(", " + DocAdjunto.IS_ASIENTO);
        sqlInsert.append(", " + DocAdjunto.OPERADOR);		
		sqlInsert.append(", " + DocAdjunto.TIMESTAMP);
		sqlInsert.append(", " + DocAdjunto.TIPO_DIGITAL);
		sqlInsert.append(", " + DocAdjunto.URL);
		sqlInsert.append(")");
		sqlInsert.append("VALUES(?,?,?,?,?,?,?,?,?,?)");
		
        docAdjunto.setIdDocAdjunto(new Long(getLastId().longValue()+1));
        
		Object[] values = new Object[] {
				docAdjunto.getIdAsiento(),
				docAdjunto.getIdEjercicio(),
				docAdjunto.getIdEmpresa(),
				docAdjunto.getDescripcion(),
				docAdjunto.getIdDocAdjunto(),
				docAdjunto.getIsAsiento(),
				docAdjunto.getIdOperador(),
				docAdjunto.getTimestamp(),
				null,
				docAdjunto.getUrl()};
		
		log.info("SQL Ejecutado ==> " + sqlInsert.toString());
		jt.update(sqlInsert.toString(),values);
	}
	
	public Long getLastId() {
		StringBuffer sql = new StringBuffer(100);
		sql.append("SELECT MAX(" + DocAdjunto.ID_DOC_ADJUNTO +") AS id ");
		sql.append(" FROM " + DocAdjunto.DOC_ADJUNTO);		
		jt = new JdbcTemplate(dataSource);
		int id = jt.queryForInt(sql.toString());
		return new Long(id);
	}
	
	public void borrar(Long idDocAdjunto) {
		StringBuffer sql = new StringBuffer(150);
		sql.append("DELETE FROM " + DocAdjunto.DOC_ADJUNTO);
		sql.append(" WHERE " + DocAdjunto.ID_DOC_ADJUNTO + " = " + idDocAdjunto);
		log.info("SQL Ejecutado ==> " + sql.toString());
		jt = new JdbcTemplate(dataSource);
		jt.execute(sql.toString());
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
}
       

   
   	
	
	
	
	



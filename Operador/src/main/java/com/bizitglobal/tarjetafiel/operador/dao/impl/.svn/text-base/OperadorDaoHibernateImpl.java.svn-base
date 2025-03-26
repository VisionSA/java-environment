package com.bizitglobal.tarjetafiel.operador.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import weborb.config.ORBConfig;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.operador.dao.OperadorDao;
import com.bizitglobal.tarjetafiel.operador.exeption.OperadorException;
import com.bizitglobal.tarjetafiel.operador.negocio.MenuItem;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.operador.negocio.Rol;
import com.bizitglobal.tarjetafiel.operador.negocio.RolPaginaPermiso;

public class OperadorDaoHibernateImpl extends HibernateDaoSupport implements OperadorDao {

	public OperadorDaoHibernateImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.bizitglobal.tarjetafiel.operador.dao.OperadorDao#grabarOperador(com.bizitglobal.tarjetafiel.operador.negocio.Operador)
	 */
	public void grabarOperador(Operador unOperador) {
		this.getHibernateTemplate().save(unOperador);
	}

	public Operador buscarOperador(Long id) {
		return (Operador) this.getHibernateTemplate().get(Operador.class,id);
	}
	
	public void borrarOperador(Long id) {
		borrarOperador(buscarOperador(id));
	}

	public void borrarOperador(Operador unOperador) {
		this.getHibernateTemplate().delete(unOperador);
	}

	public Operador getOperadorLogueado(Long codigo) throws Exception{
		
		Operador newOperador = null;	
		Operador operador;
		try {
					
			Object cod = ORBConfig.getServletContext().getAttribute(codigo.toString());
						
			if(cod != null){			
					
				operador = (Operador) this.getHibernateTemplate().get(Operador.class,codigo);
				newOperador = new Operador();
				newOperador.setApellido(operador.getApellido());
				newOperador.setCodigo(operador.getCodigo());
				newOperador.setEmail(operador.getEmail());
				newOperador.setEstado(operador.getEstado());
				newOperador.setFechaAlta(operador.getFechaAlta());
				newOperador.setNombre(operador.getNombre());				
				newOperador.setRol(new Rol());
				newOperador.getRol().setDescripcion(operador.getRol().getDescripcion());
				newOperador.getRol().setIdRol(operador.getRol().getIdRol());
				newOperador.setUsername(operador.getUsername());
				newOperador.setPermiteLinea(operador.getPermiteLinea());
				newOperador.setPermiteGrabar(operador.getPermiteGrabar());
				
	
				ORBConfig.getServletContext().removeAttribute(codigo.toString());
	
			} else {
				throw new OperadorException("Acceso Denegado");
			}			
			
		}catch(NumberFormatException ex){
			throw ex;
		} catch(Exception ex){
			throw ex;
		}		
		
		return newOperador;
		
	}
	
	public List listarTodos() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT operador ");
				sb.append("FROM Operador operador ");
				sb.append("ORDER BY operador.codigo ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}
/*@I4053*/	public List listarTodosFlex() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT operador ");
				sb.append("FROM Operador operador ");
				sb.append("where operador.estado = 'A' ");
				sb.append("ORDER BY operador.apellido ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();
				List listResult  = new ArrayList();
				Iterator iter = list.iterator();
				while(iter.hasNext()){
					Operador operIter = (Operador) iter.next();
					Operador operador = new Operador();
					
					operador.setApellido(operIter.getApellido());
					operador.setCodigo(operIter.getCodigo());
					operador.setEmail(operIter.getEmail());
					operador.setEstado(operIter.getEstado());
					operador.setFechaAlta(operIter.getFechaAlta());
					operador.setNombre(operIter.getNombre());
					operador.setUsername(operIter.getUsername());
					operador.setNombreCompleto(operIter.getApellido().trim() + " , "+ operIter.getNombre());
					Rol rol = new Rol();
					rol.setMenuItems(new HashSet());
					
					operador.setRol(rol);
					listResult.add(operador);
				}

				return listResult;
			}
		});		
	}
/*@F4053*/	public List listarTodos(Filtro filtro) {
		
		final String hql = filtro.getHQL();
		
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM Operador obj ");
				sb.append(hql);
				sb.append("ORDER BY obj.codigo ASC");

				Query query = session.createQuery(sb.toString());
				List list = query.list();

				return list;
			}
		});		
	}

	public void actualizarOperador(Operador unOperador) {
		this.getHibernateTemplate().update(unOperador);
	}	
	
	public List validarPermisoDesdeFlex(final Operador operador, final String pagina){
		return (List)getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "Select pagper.* From t_vis_seg_operadores ope " +
				"INNER JOIN t_vis_seg_roles rol ON rol.c_id_rol = ope.c_id_rol " +
				"INNER JOIN t_vis_seg_paginas_permisos pagper ON pagper.c_id_rol = rol.c_id_rol " +
				"INNER JOIN t_vis_seg_paginas pag ON pagper.c_id_pagina = pag.c_id_pagina " +
				"WHERE ope.c_codigo = :codigo AND rol.c_id_rol = :rol AND pag.c_pagina = :nombrePagina";
				
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.setParameter("codigo", operador.getCodigo());
				sqlQuery.setParameter("rol", operador.getRol().getIdRol());
				sqlQuery.setParameter("nombrePagina", pagina);
				sqlQuery.addEntity(RolPaginaPermiso.class);
				return sqlQuery.list();
			}
		});
	}

}

package com.bizitglobal.tarjetafiel.evaluacion.dao.impl;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.dao.IndividuoEvaluacionDao;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.general.negocio.Empresa;

public class IndividuoEvaluacionDaoHibernateImpl extends HibernateDaoSupport implements IndividuoEvaluacionDao {
	public IndividuoEvaluacionDaoHibernateImpl() {
		super();
	}

	public void grabarEvaIndividuos (IndividuoEvaluacion pObject) {
		this.getHibernateTemplate().save(pObject);
	}
	public IndividuoEvaluacion buscarEvaIndividuos (Long id) {
		return (IndividuoEvaluacion) this.getHibernateTemplate().get(IndividuoEvaluacion.class, id);
	}
	public void borrarEvaIndividuos (Long id) {
		borrarEvaIndividuos(buscarEvaIndividuos(id));
	}
	public void borrarEvaIndividuos (IndividuoEvaluacion pObject) {
		this.getHibernateTemplate().delete(pObject);
	}
	public void actualizarEvaIndividuos (IndividuoEvaluacion pObject) {
		this.getHibernateTemplate().update(pObject);
	}
	public List listarTodos(Filtro filtro) {

		final String hql = filtro.getHQL();

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {				
				StringBuffer sb = new StringBuffer(100);
				sb.append("SELECT DISTINCT obj ");
				sb.append("FROM IndividuoEvaluacion obj ");
				sb.append(hql);

				Query query = session.createQuery(sb.toString());
				List list = query.list();				
				return list;
			}
		});
	}
	
	public List<IndividuoEvaluacion> buscarIndividuosHabilitadosCuit(final Empresa empresa){
		List executeFind = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
/*@I4821*/				
//				String sql = "Select distinct ind.* from t_vis_eva_actividades act " +
//						"INNER JOIN t_vis_gen_suc_empresas suc ON suc.c_id_sucursal = act.c_id_sucursal " +
//						"AND suc.c_id_empresa = ? " +
//						"INNER JOIN t_vis_eva_individuos ind ON ind.c_cuil = act.c_cuil ";
				
				StringBuffer sql = new StringBuffer(); 
				sql.append(" SELECT DISTINCT IND.*, ACT.C_TIPO, ACT.C_ID_ACTIVIDAD, ACT.C_CUIL, ACT.C_HABILITADO_CUIT ");
				sql.append(" FROM t_vis_eva_actividades act ");
				sql.append(" INNER JOIN t_vis_gen_suc_empresas suc ");
				sql.append(" ON (SUC.C_ID_SUCURSAL = ACT.C_ID_SUCURSAL ");
				sql.append(" AND SUC.C_ID_EMPRESA = ?) ");
				sql.append(" INNER JOIN t_vis_tra_cod_comercios codCom ");
				sql.append(" ON SUC.c_id_sucursal= codcom.c_id_sucursal ");
				sql.append(" INNER JOIN t_vis_eva_individuos ind ");
				sql.append(" ON IND.C_CUIL = ACT.C_CUIL ");
				sql.append(" INNER JOIN t_vis_gen_tipos_documentos doc ");
				sql.append(" on ind.c_id_tipo_documento = doc.c_id_tipo_documento ");
				sql.append(" AND act.c_tipo           IN ('X','A') ");	
						
				if(empresa.getTipoLiquidacion().doubleValue() == Empresa.LIQUIDACION_POR_CUIT){
					sql.append(" AND act.c_habilitado_cuit = 'S' ");
				} 
						
				
				SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
/*@F4821*/				sqlQuery.addEntity(IndividuoEvaluacion.class);
				sqlQuery.setParameter(0, empresa.getIdEmpresa());
				List<IndividuoEvaluacion> list = sqlQuery.list();
				List<IndividuoEvaluacion> listReturn = new ArrayList<IndividuoEvaluacion>();
				
				for(IndividuoEvaluacion individuo : list){
					IndividuoEvaluacion individuoEvaluacion = new IndividuoEvaluacion();
					individuoEvaluacion.setApellido(individuo.getApellido());
					individuoEvaluacion.setNombres(individuo.getNombres());
					individuoEvaluacion.setCuil(individuo.getCuil());
					individuoEvaluacion.setNroDocumento(individuo.getNroDocumento());
					individuoEvaluacion.setFechaNacimientoFlex(individuo.getFechaNacimientoFlex());
					individuoEvaluacion.setIdIndividuo(individuo.getIdIndividuo());
					listReturn.add(individuoEvaluacion);
				}
				
				return listReturn;
			}
		});
		return executeFind;
	}
}

